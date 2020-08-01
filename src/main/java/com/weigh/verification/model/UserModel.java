package com.weigh.verification.model;

import lombok.Data;

/**
 * @author xuyang
 */
@Data
public class UserModel {
    private Integer id;
    private String password;
    private String email;
    private String nickname;
    private Byte state;
    private Byte isDelete;
    private Integer createTime;
    private Integer updateTime;
}
