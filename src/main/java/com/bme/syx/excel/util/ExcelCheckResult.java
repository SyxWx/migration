package com.bme.syx.excel.util;

import lombok.Data;
import org.apache.poi.ss.formula.functions.T;

import java.util.ArrayList;
import java.util.List;

@Data
public class ExcelCheckResult {
    private List<T> successDtos;

    private List<ExcelCheckErrDto<T>> errDtos;

    public ExcelCheckResult(List<T> successDtos, List<ExcelCheckErrDto<T>> errDtos){
        this.successDtos =successDtos;
        this.errDtos = errDtos;
    }

    public ExcelCheckResult(List<ExcelCheckErrDto<T>> errDtos){
        this.successDtos =new ArrayList<>();
        this.errDtos = errDtos;
    }
}
