package com.weigh.verification.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.power.common.util.DateTimeUtil;
import com.weigh.verification.dao.RbacRoleDao;
import com.weigh.verification.dao.RbacRoleDeptDao;
import com.weigh.verification.dao.RbacRoleResourceDao;
import com.weigh.verification.entity.Result;
import com.weigh.verification.model.RbacRoleDeptModel;
import com.weigh.verification.model.RbacRoleModel;
import com.weigh.verification.model.RbacRoleResourceModel;
import com.weigh.verification.service.RbacRoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 角色管理
 *
 * @author xuyang
 */
@Slf4j
@Service
public class RbacRoleServiceImpl implements RbacRoleService {
    @Autowired
    private RbacRoleDao rbacRoleDao;

    @Autowired
    private RbacRoleResourceDao rbacRoleResourceDao;

    @Autowired
    private RbacRoleDeptDao rbacRoleDeptDao;

    @Override
    public Result getList(Integer page, Integer pageSize, RbacRoleModel roleModel) {
        PageHelper.startPage(page, pageSize);
        List<RbacRoleModel> list = rbacRoleDao.getList(roleModel);

        Result result = new Result();

        result.setCode(200);
        result.setData(new PageInfo<>(list));
        result.setMsg("success");
        return result;
    }

    @Transactional
    @Override
    public Result add(RbacRoleModel roleModel) {
        Result result = new Result();
        if (roleModel.getResourceArray().size() <= 0) {
            result.setCode(400);
            result.setMsg("资源信息不能是空！");
            return result;
        }

        if (roleModel.getDataRange() == 3 && roleModel.getDeptArray().size() <= 0) {
            result.setCode(400);
            result.setMsg("机构信息不能是空！");
            return result;
        }

        Integer time = (int) Math.floor(DateTimeUtil.getNowTime() / 1000);

        roleModel.setCreateTime(time);
        roleModel.setUpdateTime(time);
        // 写入角色信息
        rbacRoleDao.add(roleModel);

        Integer roleId = roleModel.getId();

        writeRoleResource(roleId, roleModel.getResourceArray());
        writeRoleDept(roleId, roleModel.getDataRange(), roleModel.getDeptArray());

        result.setCode(200);
        result.setMsg("新增角色成功");
        return result;
    }

    @Transactional
    @Override
    public Result edit(Integer id, RbacRoleModel roleModel) {
        Result result = new Result();
        if (roleModel.getResourceArray().size() <= 0) {
            result.setCode(400);
            result.setMsg("资源信息不能是空！");
            return result;
        }

        if (roleModel.getDataRange() == 3 && roleModel.getDeptArray().size() <= 0) {
            result.setCode(400);
            result.setMsg("机构信息不能是空！");
            return result;
        }

        Integer time = (int) Math.floor(DateTimeUtil.getNowTime() / 1000);

        roleModel.setId(id);
        roleModel.setUpdateTime(time);

        rbacRoleDao.edit(roleModel);

        // 删除角色资源
        rbacRoleResourceDao.delete(id);
        writeRoleResource(id, roleModel.getResourceArray());

        // 删除角色机构
        rbacRoleDeptDao.delete(id);
        writeRoleDept(id, roleModel.getDataRange(), roleModel.getDeptArray());

        result.setCode(200);
        result.setMsg("编辑角色成功");
        return result;
    }

    @Transactional
    @Override
    public Result delete(Integer id) {
        Integer time = (int) Math.floor(DateTimeUtil.getNowTime() / 1000);

        rbacRoleDao.delete(id, time);
        rbacRoleResourceDao.delete(id);
        rbacRoleDeptDao.delete(id);

        Result result = new Result();
        result.setCode(200);
        result.setMsg("删除角色成功");
        return result;
    }

    private void writeRoleResource(Integer roleId, List<Integer> roleResourceArray) {
        Integer time = (int) Math.floor(DateTimeUtil.getNowTime() / 1000);

        List<RbacRoleResourceModel> roleResourceModels = new ArrayList<>();
        // 新增资源信息
        for (Integer resourceId : roleResourceArray) {
            RbacRoleResourceModel roleResourceModel = new RbacRoleResourceModel();
            roleResourceModel.setRoleId(roleId);
            roleResourceModel.setCreateTime(time);
            roleResourceModel.setResourceId(resourceId);

            roleResourceModels.add(roleResourceModel);
        }

        // 写入角色资源
        rbacRoleResourceDao.addAll(roleResourceModels);
    }

    private void writeRoleDept(Integer roleId, Integer dataRange, List<Integer> roleDeptArray) {
        if (dataRange == 3 && roleDeptArray.size() > 0) {
            Integer time = (int) Math.floor(DateTimeUtil.getNowTime() / 1000);

            List<RbacRoleDeptModel> roleModels = new ArrayList<>();

            for (Integer deptId : roleDeptArray) {
                RbacRoleDeptModel roleDeptModel = new RbacRoleDeptModel();
                roleDeptModel.setCreateTime(time);
                roleDeptModel.setDeptId(deptId);
                roleDeptModel.setRoleId(roleId);

                roleModels.add(roleDeptModel);
            }

            // 写入角色机构
            rbacRoleDeptDao.addAll(roleModels);
        }

    }
}
