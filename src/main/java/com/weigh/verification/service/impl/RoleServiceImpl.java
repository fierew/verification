package com.weigh.verification.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.power.common.util.DateTimeUtil;
import com.weigh.verification.dao.RoleDao;
import com.weigh.verification.dao.RoleDeptDao;
import com.weigh.verification.dao.RoleResourceDao;
import com.weigh.verification.entity.Result;
import com.weigh.verification.model.RoleDeptModel;
import com.weigh.verification.model.RoleModel;
import com.weigh.verification.model.RoleResourceModel;
import com.weigh.verification.service.RoleService;
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
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleDao roleDao;

    @Autowired
    private RoleResourceDao roleResourceDao;

    @Autowired
    private RoleDeptDao roleDeptDao;

    @Override
    public PageInfo<RoleModel> getList(Integer page, Integer pageSize, RoleModel roleModel) {
        PageHelper.startPage(page, pageSize);
        List<RoleModel> list = roleDao.getList(roleModel);
        return new PageInfo<>(list);
    }

    @Transactional
    @Override
    public Result add(RoleModel roleModel) {
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
        roleDao.add(roleModel);

        Integer roleId = roleModel.getId();

        writeRoleResource(roleId, roleModel.getResourceArray());
        writeRoleDept(roleId, roleModel.getDataRange(), roleModel.getDeptArray());

        result.setCode(200);
        result.setMsg("新增角色成功");
        return result;
    }

    @Transactional
    @Override
    public Result edit(Integer id, RoleModel roleModel) {
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

        roleDao.edit(roleModel);

        // 删除角色资源
        roleResourceDao.delete(id);
        writeRoleResource(id, roleModel.getResourceArray());

        // 删除角色机构
        roleDeptDao.delete(id);
        writeRoleDept(id, roleModel.getDataRange(), roleModel.getDeptArray());

        result.setCode(200);
        result.setMsg("编辑角色成功");
        return result;
    }

    @Transactional
    @Override
    public Result delete(Integer id) {
        Integer time = (int) Math.floor(DateTimeUtil.getNowTime() / 1000);

        roleDao.delete(id, time);
        roleResourceDao.delete(id);
        roleDeptDao.delete(id);

        Result result = new Result();
        result.setCode(200);
        result.setMsg("删除角色成功");
        return result;
    }

    private void writeRoleResource(Integer roleId, List<Integer> roleResourceArray) {
        Integer time = (int) Math.floor(DateTimeUtil.getNowTime() / 1000);

        List<RoleResourceModel> roleResourceModels = new ArrayList<>();
        // 新增资源信息
        for (Integer resourceId : roleResourceArray) {
            RoleResourceModel roleResourceModel = new RoleResourceModel();
            roleResourceModel.setRoleId(roleId);
            roleResourceModel.setCreateTime(time);
            roleResourceModel.setResourceId(resourceId);

            roleResourceModels.add(roleResourceModel);
        }

        // 写入角色资源
        roleResourceDao.addAll(roleResourceModels);
    }

    private void writeRoleDept(Integer roleId, Integer dataRange, List<Integer> roleDeptArray) {
        if (dataRange == 3 && roleDeptArray.size() > 0) {
            Integer time = (int) Math.floor(DateTimeUtil.getNowTime() / 1000);

            List<RoleDeptModel> roleModels = new ArrayList<>();

            for (Integer deptId : roleDeptArray) {
                RoleDeptModel roleDeptModel = new RoleDeptModel();
                roleDeptModel.setCreateTime(time);
                roleDeptModel.setDeptId(deptId);
                roleDeptModel.setRoleId(roleId);

                roleModels.add(roleDeptModel);
            }

            // 写入角色机构
            roleDeptDao.addAll(roleModels);
        }

    }
}
