package com.bme.syx.excel.web;


import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.net.URLEncoder;

@RestController
@RequestMapping(value="UploadExcelSSO")
public class UploadExcelSSO {



    @GetMapping("/listExcelPlus/{token}")
    public void listExcelPlus(HttpServletRequest request, HttpServletResponse response) throws Exception {



    }

}
