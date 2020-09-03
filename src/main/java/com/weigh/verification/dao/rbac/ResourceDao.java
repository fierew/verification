package com.weigh.verification.dao.rbac;

import com.weigh.verification.model.rbac.ResourceModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author xuyang
 */
@Mapper
public interface ResourceDao {
    /**
     * 新增资源
     *
     * @param resourceModel 资源信息
     * @return 结果
     */
    Integer add(ResourceModel resourceModel);

    /**
     * 获取资源列表
     *
     * @param resourceModel 条件
     * @return 资源列表
     */
    List<ResourceModel> getList(ResourceModel resourceModel);

    /**
     * 编辑资源
     *
     * @param resourceModel 资源信息
     * @return 结果
     */
    Integer edit(ResourceModel resourceModel);

    /**
     * 修改资源状态
     *
     * @param id         资源id
     * @param state      状态
     * @param updateTime 修改时间
     * @return 结果
     */
    Integer modifyState(@Param("id") Integer id, @Param("state") Byte state, @Param("updateTime") Integer updateTime);

    /**
     * 删除资源
     *
     * @param id         资源id
     * @param updateTime 更新时间
     * @return 结果
     */
    Integer delete(@Param("id") Integer id, @Param("updateTime") Integer updateTime);

    /**
     * 获取全部数据
     * @return 全部数据
     */
    List<ResourceModel> getAll();
}
