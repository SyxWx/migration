package com.bme.syx.kdxfj.web;


import com.bme.syx.kdxfj.service.JacobService;
import com.bme.syx.kdxfj.service.KdxfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("kdxf/")
public class KdxfController {


    @Autowired
    private KdxfService kdxfService;
    @Autowired
    private JacobService jacobService;




    @RequestMapping("test")
    public String kdxfTest(String str){
        String result =  kdxfService.kdxfTest(str);
        return result;
    }

    @RequestMapping("jacob")
    public String jacobTest(String str){
        String result = jacobService.jacobTest(str);
        return result;
    }


}
