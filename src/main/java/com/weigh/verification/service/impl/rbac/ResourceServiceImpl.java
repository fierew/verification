package com.weigh.verification.service.impl.rbac;

import com.weigh.verification.entity.Result;
import com.weigh.verification.model.rbac.ResourceModel;
import com.weigh.verification.service.rbac.ResourceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 资源管理
 *
 * @author xuyang
 */
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class ResourceServiceImpl implements ResourceService {
    @Override
    public List<ResourceModel> getTree() {
        return null;
    }

    @Override
    public Result add(ResourceModel resourceModel) {
        return null;
    }

    @Override
    public Result edit(ResourceModel resourceModel) {
        return null;
    }

    @Override
    public Result delete(Integer id) {
        return null;
    }

    @Override
    public Result modifyState(Integer id) {
        return null;
    }
}
