package com.bme.syx.statistics.web;


import com.bme.syx.statistics.service.DeviceService;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("device/")
public class DeviceController {


    @Autowired
    private DeviceService deviceService;


    // http://localhost:8080/mig/device/deviceIndex
    @RequestMapping("deviceIndex")
    public ModelAndView deviceIndex(){
        return new ModelAndView("device");
    }

    // http://localhost:8080/mig/device/getOnline
    @GetMapping("getOnline")
    public List<Map<String, String>> queryCustomerdDeviceOnline(){
        return deviceService.queryCustomerdDeviceOnline();
    }


    // http://localhost:8080/mig/device/exportOnline
    @GetMapping("exportOnline")
    public String exportCustomerdDeviceOnline(HttpServletResponse response){

        List<Map<String,String>> dataList = new ArrayList<>();
        dataList = deviceService.queryCustomerdDeviceOnline();
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("设备在线率统计");
        HSSFRow heardRow = sheet.createRow(0);
        heardRow.createCell(0).setCellValue("客户名称");
        heardRow.createCell(1).setCellValue("客户编码");
        heardRow.createCell(2).setCellValue("设备总数");
        heardRow.createCell(3).setCellValue("设备在线数量");
        heardRow.createCell(4).setCellValue("设备在线率");
        for(int i=1;i<dataList.size()+1;i++){
            Map<String,String> map = dataList.get(i-1);
            HSSFRow rowi = sheet.createRow(i);
            rowi.createCell(0).setCellValue(map.get("customerName"));
            rowi.createCell(1).setCellValue(map.get("customerId"));
            rowi.createCell(2).setCellValue(map.get("totalDevice"));
            rowi.createCell(3).setCellValue(map.get("totalOnlineDevice"));
            rowi.createCell(4).setCellValue(map.get("totalValue"));
        }
        String name = "设备在线率"+ new SimpleDateFormat("yyyy-MM-dd_HH:mm").format(new Date()).toString()+".xls";

        //文档输出
        try {
            OutputStream outputStream = null;
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/vnd.ms-excel");
            response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(name, "UTF-8"));
            outputStream = response.getOutputStream();
            workbook.write(outputStream);
            if (outputStream != null) {
                outputStream.close();
            }
        }catch(Exception e){
            e.printStackTrace();
            return "error";
        }
        return "sucess";
    }
}
