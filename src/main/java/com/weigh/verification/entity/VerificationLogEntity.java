package com.weigh.verification.entity;

import lombok.Data;

/**
 * @author xuyang
 */
@Data
public class VerificationLogEntity {
    private Integer id;
    private Integer userId;
    private Integer verificationId;
    private String key;
    private String formName;
    private String updateValue;
    private Integer createTime;
    private Integer updateTime;

    private String email;
    private String nickname;
}
