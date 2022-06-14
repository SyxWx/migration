package com.bme.syx.es.service;


import com.bme.syx.es.dao.DeviceTypeMapper;
import com.bme.syx.es.entity.EsDusterRealTime;
import com.bme.syx.es.entity.EsMonitorRealTime;
import com.bme.syx.es.entity.EsProductRealTime;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EsDeviceService {

    @Autowired
    private  EsQueryService esQueryService;
    @Autowired
    private DeviceTypeMapper deviceTypeMapper;


    /**
     *  根据设备号和客户查查询Es数据
     * @param deviceNo
     * @param customerId
     * @return
     */
    public String  getDeviceType(String deviceNo, long customerId){
        String res = "";
        String deviceType = deviceTypeMapper.getDeviceType(deviceNo,customerId+"");
        if(deviceType==null || "".equals(deviceType)) {
            return "未查到改设备，请核对设备信息！";
        }
        if("1".equals(deviceType)){
            List<EsProductRealTime> list  = esQueryService.queryProductEs(deviceNo,customerId);
            if(list!=null && list.size()>0){
                res = JSONObject.fromObject(list.get(0)).toString();
            }
        }else if("2".equals(deviceType)){
            List<EsDusterRealTime> list  = esQueryService.queryDusterEs(deviceNo,customerId);
            if(list!=null && list.size()>0){
                res = JSONObject.fromObject(list.get(0)).toString();
            }
        }else if("3".equals(deviceType)){
            List<EsMonitorRealTime> list  = esQueryService.queryMonitorEs(deviceNo,customerId);
            if(list!=null && list.size()>0){
                res = JSONObject.fromObject(list.get(0)).toString();
            }
        }else{
            return "设备类型错误，请核对设备信息！";
        }
        return res;
    }

}
