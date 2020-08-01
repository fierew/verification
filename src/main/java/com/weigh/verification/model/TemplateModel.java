package com.weigh.verification.model;

import lombok.Data;

/**
 * @author xuyang
 */
@Data
public class TemplateModel {
    private Integer id;
    private Integer userId;
    private String name;
    private String describe;
    private Integer fileId;
    private String param;
    private Byte isDelete;
    private Integer createTime;
    private Integer updateTime;
}
