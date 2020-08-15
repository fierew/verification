package com.weigh.verification.service;

import com.github.pagehelper.PageInfo;
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
     * @param userId        用户ID
     * @param templateModel 模板信息
     * @return 结果
     */
    Integer add(Integer userId, TemplateModel templateModel);

    /**
     * 编辑模板
     *
     * @param id            模板id
     * @param userId        用户ID
     * @param templateModel 模板信息
     * @return 结果
     */
    Integer edit(Integer id, Integer userId, TemplateModel templateModel);

    /**
     * 删除模板
     *
     * @param id 模板id
     * @return 结果
     */
    Integer delete(Integer id);

    /**
     * 根据ID获取模板信息
     *
     * @param id 模板ID
     * @return 模板信息
     */
    TemplateModel getInfoById(Integer id);

    /**
     * 获取模板列表
     *
     * @param page          页数
     * @param pageSize      每页的条数
     * @param templateModel 模板条件
     * @return 模板列表
     */
    PageInfo<TemplateModel> getList(Integer page, Integer pageSize, TemplateModel templateModel);

    /**
     * 获取全部模板
     *
     * @return 模板列表
     */
    List<TemplateModel> getAll();

    /**
     * 修改模板状态
     *
     * @param id    模板id
     * @param state 状态
     * @return 结果
     */
    Integer modifyState(Integer id, Byte state);
}
