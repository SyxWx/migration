package com.bme.syx.module.productdata;

import com.bme.syx.module.productdata.dao.tdb.ProductHistoryMapper;
import com.bme.syx.module.productdata.service.ProductHistoryService;
import lombok.Data;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;

@Slf4j
@Data
public class ProductHistoryRunnable implements  Runnable{

    private ThreadLocal<List> threadLocal = new ThreadLocal<>();


    @Autowired
    private ProductHistoryService productHistoryService;
    private Long startTime;
    private Long endTime;
    private int customerId ;

    @Override
    public void run() {
        String threadName = Thread.currentThread().getName();
        threadLocal.set(Arrays.asList(startTime, endTime, customerId));
        while (true) {
            try {
                log.info("==========>ProductHistoryRunnable data Thread {} Running", threadName);
                //取数据开始/结束时间
                Long endTime = (Long) threadLocal.get().get(1);
                Long startTime =endTime - 60000;
                //更新开始时间
                threadLocal.get().set(1, startTime);
                //数据结束时间限制  startTime 2021-02-19%2019:00:00
                Long limitTime = (Long) threadLocal.get().get(0);
                if (endTime.compareTo(limitTime) > 0) {
                    Thread.sleep(10000);
                    this.loadDataTypes(customerId,startTime,endTime);
                } else {
                    log.info("==========>ProductHistoryRunnable data Thread {} success!", threadName);
                    break;
                }

            } catch (Exception e) {
                log.info("==========>ProductHistoryRunnable data Thread {} Occurred Error,Detail: {}", Thread.currentThread().getName(), e);
            }
        }
    }


    private void loadDataTypes(Integer customerId,Long startTime,Long endTime) {

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String startTime1 = formatter.format(startTime);
        String endTime1 = formatter.format(endTime);
        log.info("==========>ProductHistoryRunnable data 查询客户 {} ,开始时间{},结束时间{}", customerId,startTime1,endTime1);








    }
}
