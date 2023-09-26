package com.bme.syx.statistics.web;


import com.bme.syx.statistics.entity.Customer;
import com.bme.syx.statistics.service.CountService;
import com.bme.syx.utils.CommonResult;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


@RestController
@RequestMapping(value = "count")
public class CountController {


    @Autowired
    private CountService countService;


    //http://localhost:8080/mig/count/export
    @RequestMapping(value="/export")
    public CommonResult export(){
        List<Customer> customerList =  countService.export();

        return  CommonResult.success("查询成功",customerList);
    }


    // http://localhost:8080/mig/count/exportExcel
    @RequestMapping(value="/exportExcel")
    public CommonResult exportExcel(){
        List<Customer> customerList =  countService.export();
        //创建工作薄对象
        HSSFWorkbook workbook=new HSSFWorkbook();//这里也可以设置sheet的Name
        //创建工作表对象
        HSSFSheet sheet = workbook.createSheet();
        //创建工作表的行
        HSSFRow herdRow = sheet.createRow(0);
        herdRow.createCell(0).setCellValue("客户号");
        herdRow.createCell(1).setCellValue("客户名称");
        herdRow.createCell(2).setCellValue("版本");
        herdRow.createCell(3).setCellValue("生产设备数量");
        herdRow.createCell(4).setCellValue("生产信号数量");
        herdRow.createCell(5).setCellValue("治理设备数量");
        herdRow.createCell(6).setCellValue("治理信号数量");
        herdRow.createCell(7).setCellValue("TSP设备数量");
        herdRow.createCell(8).setCellValue("TSP信号数量");
        herdRow.createCell(9).setCellValue("空气质量微站设备数量");
        herdRow.createCell(10).setCellValue("空气质量微站信号数量");
        herdRow.createCell(11).setCellValue("CEMS设备数量");
        herdRow.createCell(12).setCellValue("CEMS信号数量");
        herdRow.createCell(13).setCellValue("Vocs设备数量");
        herdRow.createCell(14).setCellValue("Vocs信号数量");
        herdRow.createCell(15).setCellValue("VDM设备数量");
        herdRow.createCell(16).setCellValue("VDM信号数量");
        herdRow.createCell(17).setCellValue("含水率检测仪设备数量");
        herdRow.createCell(18).setCellValue("含水率检测仪信号数量");


        for (int i = 1; i < customerList.size()+1; i++) {
            Customer customer = customerList.get(i-1);
            HSSFRow rowi = sheet.createRow(i);
            rowi.createCell(0).setCellValue(customer.getCustomerId());
            rowi.createCell(1).setCellValue(customer.getCustomerName());
            rowi.createCell(2).setCellValue(customer.getCustomerVersion());
            rowi.createCell(3).setCellValue(customer.getProductDevice());
            rowi.createCell(4).setCellValue(customer.getProductSignal());
            rowi.createCell(5).setCellValue(customer.getDustDevice());
            rowi.createCell(6).setCellValue(customer.getDustSignal());
            rowi.createCell(7).setCellValue(customer.getTsptDevice());
            rowi.createCell(8).setCellValue(customer.getTspSignal());
            rowi.createCell(9).setCellValue(customer.getWzDevice());
            rowi.createCell(10).setCellValue(customer.getWzSignal());
            rowi.createCell(11).setCellValue(customer.getCemsDevice());
            rowi.createCell(12).setCellValue(customer.getCemsSignal());
            rowi.createCell(13).setCellValue(customer.getVocsDevice());
            rowi.createCell(14).setCellValue(customer.getVocsSignal());
            rowi.createCell(15).setCellValue(customer.getVdmDevice());
            rowi.createCell(16).setCellValue(customer.getVdmSignal());
            rowi.createCell(17).setCellValue(customer.getHslDevice());
            rowi.createCell(18).setCellValue(customer.getHslSignal());
        }

        workbook.setSheetName(0,"信号统计");

        //文档输出
        FileOutputStream out = null;
        String name = "";
        try {
            name = "/信号统计" + new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()).toString() +".xls";
            System.out.println(name);
            out = new FileOutputStream(name);
            workbook.write(out);
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return  CommonResult.success("导出成功，文件地址：  D:"+name);
    }

}
