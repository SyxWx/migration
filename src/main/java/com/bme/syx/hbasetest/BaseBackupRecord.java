package com.bme.syx.hbasetest;

import lombok.Data;

/**
 * description:
 * <p></p>
 *
 * @author yanjiabo
 * @Date: 2022/11/2 9:19
 * Copyright: 2021, BME (Shanghai) Co., Ltd. All Rights Reserved.
 */
@Data
public class BaseBackupRecord {
    private Long preRecordId;
    private String version;
}
