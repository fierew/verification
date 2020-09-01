package com.weigh.verification.model.rbac;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * @author xuyang
 */
@Data
public class UserModel {
    @JsonProperty(value = "id")
    private Integer id;

    @JsonProperty(value = "deptId")
    private String deptId;

    @JsonProperty(value = "roleId")
    private String roleId;

    @JsonProperty(value = "password")
    private String password;

    @JsonProperty(value = "realName")
    private String realName;

    @JsonProperty(value = "email")
    private String email;

    @JsonProperty(value = "mobile")
    private String mobile;

    @JsonProperty(value = "sex")
    private String sex;

    @JsonProperty(value = "age")
    private String age;

    @JsonProperty(value = "loginNum")
    private String loginNum;

    @JsonProperty(value = "state")
    private Byte state;

    @JsonProperty(value = "sort")
    private Integer sort;

    @JsonProperty(value = "isDelete")
    private Byte isDelete;

    @JsonProperty(value = "createTime")
    private Integer createTime;

    @JsonProperty(value = "updateTime")
    private Integer updateTime;
}
