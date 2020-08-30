package com.weigh.verification.service.impl.rbac;

import com.github.pagehelper.PageInfo;
import com.weigh.verification.entity.Result;
import com.weigh.verification.model.rbac.RoleModel;
import com.weigh.verification.service.rbac.RoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 角色管理
 *
 * @author xuyang
 */
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class RoleServiceImpl implements RoleService {
    @Override
    public PageInfo<RoleModel> getList(Integer page, Integer pageSize, RoleModel roleModel) {
        return null;
    }

    @Override
    public Result add(RoleModel roleModel) {
        return null;
    }

    @Override
    public Result edit(RoleModel roleModel) {
        return null;
    }

    @Override
    public Result delete(Integer id) {
        return null;
    }
}
