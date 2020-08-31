package com.weigh.verification.dao.rbac;

import com.weigh.verification.model.rbac.ResourceModel;
import org.apache.ibatis.annotations.Mapper;

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
}
