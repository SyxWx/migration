package com.bme.syx.es.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarwashInfoData {

    private String carName;

    private String deviceNo;

    private Long customerId;
}
