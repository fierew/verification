package com.weigh.verification.service;

import com.weigh.verification.model.TemplateModel;

import java.util.List;

/**
 * @author xuyang
 */
public interface TemplateService {
    /**
     * 分析docx文件
     *
     * @param filePath 文件路径
     * @return 数组
     */
    List<String> analysis(String filePath);

    /**
     * 新增模板
     *
     * @param templateModel 模板信息
     * @return 结果
     */
    Integer add(TemplateModel templateModel);

    /**
     * 编辑模板
     *
     * @param id            模板id
     * @param templateModel 模板信息
     * @return 结果
     */
    Integer edit(Integer id, TemplateModel templateModel);

    /**
     * 删除模板
     *
     * @param id 模板id
     * @return 结果
     */
    Integer delete(Integer id);

    /**
     * 获取模板列表
     *
     * @param page     页数
     * @param pageSize 每页的条数
     * @return 模板列表
     */
    List<TemplateModel> getList(Integer page, Integer pageSize);

    /**
     * 获取全部模板
     *
     * @return 模板列表
     */
    List<TemplateModel> getAll();
}
