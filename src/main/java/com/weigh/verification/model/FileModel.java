package com.weigh.verification.model;

import lombok.Data;

/**
 * @author xuyang
 */
@Data
public class FileModel {
    private Integer id;
    private Integer userId;
    private String name;
    private String type;
    private String path;
    private Integer size;
    private String hash;
    private Byte isDelete;
    private Integer createTime;
    private Integer updateTime;
}
