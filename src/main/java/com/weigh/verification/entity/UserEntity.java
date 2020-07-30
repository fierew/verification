package com.weigh.verification.entity;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * @author xuyang
 */
@Data
public class UserEntity {

    @Pattern(regexp = "^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,20}$", message = "码至少包含 数字和英文，长度6-20")
    private String password;

    @Email(message = "请输入正确的邮箱账号")
    private String email;

    @NotBlank(message = "昵称不能为空")
    @Size(min = 1, max = 20, message = "昵称长度必须在1-20之间")
    private String nickname;
}
