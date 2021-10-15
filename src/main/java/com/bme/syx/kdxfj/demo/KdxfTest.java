package com.bme.syx.kdxfj.demo;

import com.iflytek.cloud.speech.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.awt.*;

public class KdxfTest  extends  JFrame{
    private Logger DebugLog = LoggerFactory.getLogger(KdxfTest.class);

    private static final long serialVersionUID = 1L;
    JPanel panelNorth, panelSouth;
    JTextArea textArea;
    JButton button_start, button_stop;
    private SpeechSynthesizer mTts;

    public static void main(String[] args)
    {
        new KdxfTest();

    }

    public  KdxfTest()
    {
        initIfly();//初始化连接讯飞
        //画布设置
        Container con = this.getContentPane();
        con.setLayout(new BorderLayout());
        this.setSize(500, 200);
        this.setLocationRelativeTo(null);
        this.setResizable(true);
        this.setDefaultCloseOperation(3);
        this.setLayout(new BorderLayout());
        setFrame();
        this.add(panelSouth, BorderLayout.SOUTH);
        this.add(panelNorth, BorderLayout.NORTH);
        setVisible(true);
    }
    //初始化连接讯飞
    public void initIfly()
    {
        mTts = SpeechSynthesizer.createSynthesizer( );
        // SpeechUtility.createUtility("appid=XXXXXX");//冲突
        SpeechUtility.createUtility( SpeechConstant.APPID+"=5b491771");
    }

    public void setFrame()
    {
        //设置按钮
        panelNorth = new JPanel();
        panelSouth = new JPanel();
        textArea = new JTextArea(50, 30);
        button_start = new JButton("在线语音合成");
        button_start.addActionListener(e ->
        {
            setting();
            //textArea.setText("现在金一路微站PM2.5超标，超标值300mg");
            //获取画布的字符串，然后播放输出字符串
            String Text = textArea.getText();
            if(!mTts.isSpeaking()) {
                mTts.startSpeaking(Text,mSynListener);
            }else {
                mTts.stopSpeaking();
            };
        });
        button_stop = new JButton("停止");
        button_stop.addActionListener(e ->
        {
            mTts.stopSpeaking();
            iatSpeechInitUI();
        });
        panelNorth.add(textArea);
        panelSouth.add(button_start);
        panelSouth.add(button_stop);
    }

    void setting()// 属性设置
    {
        final String engType = "cloud";
        mTts.setParameter(SpeechConstant.VOICE_NAME, "aisjinger");//设置发音人
        mTts.setParameter(SpeechConstant.SPEED, "160");//设置语速
        mTts.setParameter(SpeechConstant.VOLUME, "80");//设置音量，范围0~100
        //设置合成音频保存位置（可自定义保存位置），保存在“./tts_test.pcm”
        //如果不需要保存合成音频，注释该行代码
        mTts.setParameter(SpeechConstant.TTS_AUDIO_PATH, "./tts_test.pcm");
    }

    //private RecognizerListener recognizerListener = new RecognizerListener()
    private  SynthesizerListener  mSynListener = new  SynthesizerListener()
    {
        @Override
        public void onEvent(int arg1, int arg2, int arg3,int arg4, Object msg,Object msg2)
        {}
        @Override
        public void onSpeakBegin()
        {
            button_start.setText("听写中...");
            button_start.setEnabled(false);
        }
        @Override
        public void onCompleted(SpeechError error) {
            if (null != error)
            {
                textArea.setText(error.getErrorDescription(true));
                iatSpeechInitUI();
            }
        }

        //讯飞SDK覆盖抽象类方法
        @Override
        public void onBufferProgress(int percent, int beginPos, int endPos, String info) {}

        @Override
        public void onSpeakPaused() {}

        @Override
        public void onSpeakProgress(int percent, int beginPos, int endPos) {}

        @Override
        public void onSpeakResumed() {}
    };

    public void iatSpeechInitUI()
    {
        button_start.setEnabled(true);
        button_start.setText("在线语音合成");
    }
}