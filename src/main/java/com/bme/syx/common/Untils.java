package com.bme.syx.common;

import com.bme.syx.cloud.entity.EmissionSource;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class Untils<T> {

    private T obj;

    public T getObj() {
        return obj;
    }

    public void setObj(T obj) {
        this.obj = obj;
    }
    // 拆分 list 1000个一组
    public  List<List<T>> groupList(List<T> list) {
        List<List<T>> listGroup = new ArrayList<List<T>>();
        int listSize = list.size();
        //子集合的长度
        int toIndex = 1000;
        for (int i = 0; i < list.size(); i += 1000) {
            if (i + 1000 > listSize) {
                toIndex = listSize - i;
            }
            List<T> newList = list.subList(i, i + toIndex);
            listGroup.add(newList);
        }
        return listGroup;
    }
}
