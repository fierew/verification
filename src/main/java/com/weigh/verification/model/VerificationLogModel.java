package com.weigh.verification.model;

import lombok.Data;

/**
 * @author xuyang
 */
@Data
public class VerificationLogModel {
    private Integer userId;
    private Integer verificationId;
    private String key;
    private String formName;
    private String updateValue;
    private Byte isDelete;
    private Integer createTime;
    private Integer updateTime;
}
