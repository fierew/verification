package com.weigh.verification.dao;

import com.weigh.verification.model.TemplateModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author xuyang
 */
@Mapper
public interface TemplateDao {
    /**
     * 新增模板
     *
     * @param templateModel 模板信息
     * @return 结果
     */
    Integer add(TemplateModel templateModel);

    /**
     * 根据id获取模板
     *
     * @param id 模板id
     * @return 模板信息
     */
    TemplateModel getInfo(Integer id);

    /**
     * 编辑模板信息
     *
     * @param templateModel 模板信息
     * @return 结果
     */
    Integer edit(TemplateModel templateModel);

    /**
     * 删除模板
     *
     * @param id         模板id
     * @param updateTime 更新时间
     * @return 结果
     */
    Integer delete(@Param("id") Integer id, @Param("updateTime") Integer updateTime);

    /**
     * 获取模板列表
     *
     * @param limit  偏移量
     * @param offset 条数
     * @return 模板列表
     */
    List<TemplateModel> getList(@Param("limit") Integer limit, @Param("offset") Integer offset);

    /**
     * 获取所有模板
     *
     * @return 模板列表
     */
    List<TemplateModel> getAll();
}
