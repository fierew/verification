package com.weigh.verification.dao;

import com.weigh.verification.model.RbacResourceModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author xuyang
 */
@Mapper
public interface RbacResourceDao {
    /**
     * 新增资源
     *
     * @param resourceModel 资源信息
     * @return 结果
     */
    Integer add(RbacResourceModel resourceModel);

    /**
     * 获取资源列表
     *
     * @param resourceModel 条件
     * @return 资源列表
     */
    List<RbacResourceModel> getList(RbacResourceModel resourceModel);

    /**
     * 编辑资源
     *
     * @param resourceModel 资源信息
     * @return 结果
     */
    Integer edit(RbacResourceModel resourceModel);

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
     * 批量删除资源
     *
     * @param ids        资源id
     * @param updateTime 更新时间
     * @return 结果
     */
    Integer deletes(@Param("ids") List<Integer> ids, @Param("updateTime") Integer updateTime);

    /**
     * 获取全部数据
     *
     * @return 全部数据
     */
    List<RbacResourceModel> getAll();

    /**
     * 根据多个id获取资源
     * @param ids 资源id
     * @param state 状态
     * @return 结果
     */
    List<RbacResourceModel> getInfoByIds(@Param("ids") List<Integer> ids, @Param("state") Byte state);
}
