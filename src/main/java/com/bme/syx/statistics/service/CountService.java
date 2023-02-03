package com.bme.syx.statistics.service;

import com.bme.syx.statistics.dao.CountMapper;
import com.bme.syx.statistics.entity.CountEntity;
import com.bme.syx.statistics.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class CountService {


    @Autowired
    private CountMapper countMapper;


    public List<Customer> export(){


        List<Customer> customerList =countMapper.selectAllCustomer();

        //生产设备数量统计
        Map<String, CountEntity> productDeviceList  =  queryDeviceCountByType(produtTypes,1);
        //生产信号数量统计
        Map<String, CountEntity> productSignalList  =  querySignalCountByType(produtTypes,1);
        //治理设备数量统计
        Map<String, CountEntity> dustDeviceList  =  queryDeviceCountByType(dustTypes,1);
        //治理信号数量统计
        Map<String, CountEntity> dustSignalList  =  querySignalCountByType(dustTypes,1);
        //tsp设备数量统计
        Map<String, CountEntity> tspDeviceList  =  queryDeviceCountByType(tspTypes,2);
        //tsp信号数量统计
        Map<String, CountEntity> tspSignalList  =  querySignalCountByType(tspTypes,2);
        //微站设备数量统计
        Map<String, CountEntity> wzDeviceList  =  queryDeviceCountByType(wzTypes,2);
        //微站信号数量统计
        Map<String, CountEntity> wzSignalList  =  querySignalCountByType(wzTypes,2);
        //CEMS设备数量统计
        Map<String, CountEntity> cemsDeviceList  =  queryDeviceCountByType(cemsTypes,2);
        //CEMS信号数量统计
        Map<String, CountEntity> cemsSignalList  =  querySignalCountByType(cemsTypes,2);
        //Vocs设备数量统计
        Map<String, CountEntity> vocsDeviceList  =  queryDeviceCountByType(vocsTypes,2);
        //Vocs信号数量统计
        Map<String, CountEntity> vocsSignalList  =  querySignalCountByType(vocsTypes,2);
        //含水率检测仪设备数量统计
        Map<String, CountEntity> hslDeviceList  =  queryDeviceCountByType(hslTypes,2);
        //含水率检测仪信号数量统计
        Map<String, CountEntity> hslSignalList  =  querySignalCountByType(hslTypes,2);
        //VDM检测仪设备数量统计
        Map<String, CountEntity> vdmDeviceList  =  queryDeviceCountByType(vdmTypes,2);
        //VDM检测仪信号数量统计
        Map<String, CountEntity> vdmSignalList  =  querySignalCountByType(vdmTypes,2);

        customerList.forEach(
                customer->{
                    String customerId = customer.getCustomerId()+"";

                    String version  = customerVersion.get(customerId);
                    if(version!=null){
                        customer.setCustomerVersion(version);
                    }
                    CountEntity productDevice = productDeviceList.get(customerId);
                    if(productDevice!=null && productDevice.getCount()>0){
                        customer.setProductDevice(productDevice.getCount());
                    }
                    CountEntity productSignal = productSignalList.get(customerId);
                    if(productSignal!=null && productSignal.getCount()>0){
                        customer.setProductSignal(productSignal.getCount());
                    }

                    CountEntity dustDevice = dustDeviceList.get(customerId);
                    if(dustDevice!=null && dustDevice.getCount()>0){
                        customer.setDustDevice(dustDevice.getCount());
                    }
                    CountEntity dustSignal = dustSignalList.get(customerId);
                    if(dustSignal!=null && dustSignal.getCount()>0){
                        customer.setDustSignal(dustSignal.getCount());
                    }

                    CountEntity tspDevice = tspDeviceList.get(customerId);
                    if(tspDevice!=null && tspDevice.getCount()>0){
                        customer.setTsptDevice(tspDevice.getCount());
                    }
                    CountEntity tspSignal = tspSignalList.get(customerId);
                    if(tspSignal!=null && tspSignal.getCount()>0){
                        customer.setTspSignal(tspSignal.getCount());
                    }

                    CountEntity wzDevice = wzDeviceList.get(customerId);
                    if(wzDevice!=null && wzDevice.getCount()>0){
                        customer.setWzDevice(wzDevice.getCount());
                    }
                    CountEntity wzSignal = wzSignalList.get(customerId);
                    if(wzSignal!=null && wzSignal.getCount()>0){
                        customer.setWzSignal(wzSignal.getCount());
                    }

                    CountEntity cemsDevice = cemsDeviceList.get(customerId);
                    if(cemsDevice!=null && cemsDevice.getCount()>0){
                        customer.setCemsDevice(cemsDevice.getCount());
                    }
                    CountEntity cemsSignal = cemsSignalList.get(customerId);
                    if(cemsSignal!=null && cemsSignal.getCount()>0){
                        customer.setCemsSignal(cemsSignal.getCount());
                    }

                    CountEntity vocsDevice = vocsDeviceList.get(customerId);
                    if(vocsDevice!=null && vocsDevice.getCount()>0){
                        customer.setVocsDevice(vocsDevice.getCount());
                    }
                    CountEntity vocsSignal = vocsSignalList.get(customerId);
                    if(vocsSignal!=null && vocsSignal.getCount()>0){
                        customer.setVocsSignal(vocsSignal.getCount());
                    }

                    CountEntity hslDevice = hslDeviceList.get(customerId);
                    if(hslDevice!=null && hslDevice.getCount()>0){
                        customer.setHslDevice(hslDevice.getCount());
                    }
                    CountEntity hslSignal = hslSignalList.get(customerId);
                    if(hslSignal!=null && hslSignal.getCount()>0){
                        customer.setHslSignal(hslSignal.getCount());
                    }

                    CountEntity vdmDevice = vdmDeviceList.get(customerId);
                    if(vdmDevice!=null && vdmDevice.getCount()>0){
                        customer.setVdmDevice(vdmDevice.getCount());
                    }
                    CountEntity vdmSignal = vdmSignalList.get(customerId);
                    if(vdmSignal!=null && vdmSignal.getCount()>0){
                        customer.setVdmSignal(vdmSignal.getCount());
                    }
                }
        );
        return customerList;
    }


    //按设备类型查询设备数量统计
    public Map<String, CountEntity> queryDeviceCountByType(String typeIds,int type){
        List<CountEntity> deviceList = new ArrayList<CountEntity>();
        if(1==type){
            //用 primary_type_id 查询
            CountEntity deviceEntity  = new CountEntity();
            deviceEntity.setTypeIds(typeIds);
            deviceList  =  countMapper.selectCountByPTypes(deviceEntity);
        }else{
            //用 type_id 查询
            CountEntity deviceEntity  = new CountEntity();
            deviceEntity.setTypeIds(typeIds);
            deviceList  =  countMapper.selectCountByTypes(deviceEntity);
        }
        //转换为Map
        Map<String, CountEntity> mapGroup = deviceList.stream().collect(Collectors.groupingBy(CountEntity::getCustomerId,
                Collectors.collectingAndThen(Collectors.toList(), list -> list.get(0))));
        return mapGroup;
    }

    //按设备类型查询信号数量统计
    public Map<String, CountEntity> querySignalCountByType(String typeIds,int type){
        List<CountEntity> signalList = new ArrayList<CountEntity>();
        if(1==type){
            //用 primary_type_id 查询
            CountEntity signaEntity  = new CountEntity();
            signaEntity.setTypeIds(typeIds);
            signalList  =  countMapper.selectCountSignalByPTypes(signaEntity);
        }else{
            //用 type_id 查询
            CountEntity signaEntity  = new CountEntity();
            signaEntity.setTypeIds(typeIds);
            signalList  =  countMapper.selectCountSignalByTypes(signaEntity);
        }
        //转换为Map
        Map<String, CountEntity> mapGroup = signalList.stream().collect(Collectors.groupingBy(CountEntity::getCustomerId,
                Collectors.collectingAndThen(Collectors.toList(), list -> list.get(0))));
        return mapGroup;
    }



    //排除的客户
    private static String noQueryCustomerId = "";
    //生产设备数量统计
    private  static String  produtTypes = "1";
    //治理设备数量统计
    private  static String  dustTypes = "2";
    //tsp设备数量统计
    private  static String  tspTypes =  "1539730699168001";
    //微站设备数量统计
    private  static String  wzTypes =   "1539730672078001";
    //cems设备数量统计
    private  static String  cemsTypes = "1539730750575002";
    //vocs设备数量统计
    private  static String  vocsTypes = "1539730750575001";
    //含水率设备数量统计
    private  static String  hslTypes =  "1626143139164001";
    //vdm设备数量统计
    private  static String  vdmTypes =  "1539730750575006,1539730750575007,1539730750575003,1539730750575004,1539730750575005," +
            "1539730750575008,1539730672078002,1539730672078003,1539730672078004,1539730672078005," +
            "1539730672078006,1539730672078007,1539730672078008,1539730672078009,1539730672078010";

    private static final Map<String,String> customerVersion = new HashMap(){{
        put("1","公有云/315版本");
        put("2","公有云/315版本");
        put("3","公有云/老版本大屏");
        put("4","公有云/老版本大屏");
        put("5","公有云/315版本");
        put("6","公有云/315版本");
        put("7","公有云/315版本");
        put("8","公有云/315版本");
        put("9","公有云/老版本大屏");
        put("10","公有云/315版本");
        put("11","公有云/315版本");
        put("12","公有云/315版本");
        put("13","公有云/315版本");
        put("14","公有云/315版本");
        put("15","公有云/315版本");
        put("16","公有云/315版本");
        put("17","公有云/315版本");
        put("18","公有云/315版本");
        put("19","私有云/标准版大屏");
        put("20","私有云/标准版大屏");
        put("21","混合云/标准版大屏");
        put("22","公有云/315版本");
        put("23","公有云/315版本");
        put("24","公有云/315版本");
        put("25","混合云/标准版大屏");
        put("26","公有云/315版本");
        put("27","公有云/315版本");
        put("28","公有云/315版本");
        put("29","公有云/315版本");
        put("30","公有云/315版本");
        put("32","公有云/315版本");
        put("33","公有云/315版本");
        put("34","公有云/标准版");
        put("35","私有云/315版本");
        put("36","公有云/315版本");
        put("37","公有云/315版本");
        put("38","公有云/315版本");
        put("39","公有云/315版本");
        put("40","公有云/315版本");
        put("41","公有云/315版本");
        put("42","公有云/315版本");
        put("44","公有云/315版本");
        put("45","公有云/315版本");
        put("46","公有云/315版本");
        put("47","公有云/315版本");
        put("48","公有云/315版本");
        put("49","公有云/无大屏");
        put("50","公有云/315版本");
        put("51","公有云/315版本");
        put("52","公有云/315版本");
        put("53","公有云/315版本");
        put("54","私有云/315版本");
        put("55","公有云/315版本");
        put("56","公有云/315版本");
        put("57","公有云/315版本");
        put("58","私有云/315版本");
        put("59","公有云/315版本");
        put("60","私有云/315版本");
        put("61","公有云/315版本");
        put("62","公有云/315版本");
        put("63","公有云/315版本");
        put("64","公有云/315版本");
        put("65","公有云/315版本");
        put("66","315大屏");
        put("67","公有云/315版本");
        put("68","公有云/315版本");
        put("69","公有云/315版本");
        put("70","公有云/315版本");
        put("71","公有云/315版本");
        put("72","公有云/315版本");
        put("73","公有云/315版本");
        put("74","公有云/315版本");
        put("75","公有云/315版本");
        put("76","公有云/315版本");
        put("77","公有云/315版本");
        put("78","公有云/315版本");
        put("79","公有云/315版本");
        put("80","公有云/315版本");
        put("81","公有云/315版本");
        put("82","公有云/315版本");
        put("83","315大屏");
        put("84","315大屏");
        put("85","315大屏");
        put("86","315大屏");
        put("87","315大屏");
        put("88","315大屏");
        put("89","315大屏");
        put("90","315大屏");
        put("91","315大屏");
        put("92","315大屏");
        put("93","315大屏");
        put("94","315大屏");
        put("95","315大屏");
        put("96","315大屏");
        put("97","315大屏");
        put("98","315大屏");
        put("99","315大屏");
        put("100","315大屏");
    }};



    public  String  checkVersion(){
        /**
         * 3	烘熔
         * 4	龙凤山
         * 7	鑫汇
         * 9	文丰
         * 19	山钢日照
         * 20	莱钢集团
         * 21	山西建龙
         * 25	东海钢铁
         * 31	中铁渤海煤焦化
         * 34	龙腾特钢
         * 35	日照钢铁
         * 49	迁钢矿业
         * 54	镔鑫钢铁
         * 58	中天钢铁
         * 60	三宝钢铁
         */

        return null;

    }
}
