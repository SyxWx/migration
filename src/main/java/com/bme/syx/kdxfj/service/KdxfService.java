package com.bme.syx.kdxfj.service;


import com.iflytek.cloud.speech.SpeechConstant;
import com.iflytek.cloud.speech.SpeechError;
import com.iflytek.cloud.speech.SpeechSynthesizer;
import com.iflytek.cloud.speech.SynthesizerListener;
import org.springframework.stereotype.Service;

@Service
public class KdxfService {

    private SpeechSynthesizer mTts;

    public String kdxfTest(String message){


        String var1 ="测试数据一起飞";
        if(message!=null){
            var1 = message;
        }
        mTts = SpeechSynthesizer.createSynthesizer( );
        mTts.startSpeaking(var1,mSynListener);

        return "播放语音成功！！";
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
    private SynthesizerListener mSynListener = new  SynthesizerListener()
    {
        @Override
        public void onEvent(int arg1, int arg2, int arg3,int arg4, Object msg,Object msg2)
        {}
        @Override
        public void onSpeakBegin()
        {
            //button_start.setText("听写中...");
            //button_start.setEnabled(false);
        }
        @Override
        public void onCompleted(SpeechError error) {
            if (null != error)
            {
               // textArea.setText(error.getErrorDescription(true));
                //iatSpeechInitUI();
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
}
