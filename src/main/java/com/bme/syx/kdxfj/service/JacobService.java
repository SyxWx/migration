package com.bme.syx.kdxfj.service;


import com.iflytek.cloud.speech.SpeechSynthesizer;
import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.Dispatch;
import com.jacob.com.Variant;
import org.springframework.stereotype.Service;

import java.util.Scanner;

@Service
public class JacobService {

    public String jacobTest(String message){
        String var1 ="测试数据一起飞";
        if(message!=null){
            var1 = message;
        }
        startSpeaking(var1);
        return "播放语音成功！！";
    }

    public void startSpeaking(String message) {
        // TODO Auto-generated method stub
        ActiveXComponent sap = new ActiveXComponent("Sapi.SpVoice");

        Dispatch sapo = sap.getObject();
        try {
            // 音量 0-100
            sap.setProperty("Volume", new Variant(100));
            // 语音朗读速度 -10 到 +10
            sap.setProperty("Rate", new Variant(-2));
            // 执行朗读
            Dispatch.call(sapo, "Speak", new Variant(message));

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sapo.safeRelease();
            sap.safeRelease();
        }
    }




    public static void main(String[] args) {
        // TODO Auto-generated method stub
        ActiveXComponent sap = new ActiveXComponent("Sapi.SpVoice");

        Dispatch sapo = sap.getObject();
        try {

            // 音量 0-100
            sap.setProperty("Volume", new Variant(100));
            // 语音朗读速度 -10 到 +10
            sap.setProperty("Rate", new Variant(-2));


            System.out.println("请输入要朗读的内容：");
            Scanner scan = new Scanner(System.in);
            String str = scan.next();
            // 执行朗读
            Dispatch.call(sapo, "Speak", new Variant(str));

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sapo.safeRelease();
            sap.safeRelease();
        }
    }
}
