package com.weigh.verification.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.power.common.util.DateTimeUtil;
import com.weigh.verification.dao.*;
import com.weigh.verification.entity.Result;
import com.weigh.verification.model.*;
import com.weigh.verification.service.RbacRoleService;
import com.weigh.verification.utils.TreeUtil;
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
    private RbacResourceDao rbacResourceDao;

    @Autowired
    private RbacDeptDao rbacDeptDao;

    @Autowired
    private RbacRoleResourceDao rbacRoleResourceDao;

    @Autowired
    private RbacRoleDeptDao rbacRoleDeptDao;

    @Override
    public Result getList(Integer page, Integer pageSize, RbacRoleModel roleModel) {
        PageHelper.startPage(page, pageSize);
        List<RbacRoleModel> list = rbacRoleDao.getList(roleModel);

        List<Integer> roleIds = new ArrayList<>();
        for(RbacRoleModel rbacRoleModel : list){
            roleIds.add(rbacRoleModel.getId());
        }

        List<RbacRoleDeptModel> deptModels = rbacRoleDeptDao.getInfoByRoleIds(roleIds);
        List<RbacRoleResourceModel> resourceModels = rbacRoleResourceDao.getInfoByRoleIds(roleIds);

        for(RbacRoleModel rbacRoleModel : list){
            List<Integer> deptArray = new ArrayList<>();
            for (RbacRoleDeptModel deptModel: deptModels) {
                if(rbacRoleModel.getId().equals(deptModel.getRoleId())){
                    deptArray.add(deptModel.getDeptId());
                }
            }
            rbacRoleModel.setDeptArray(deptArray);

            List<Integer> resourceArray = new ArrayList<>();
            for (RbacRoleResourceModel resourceModel: resourceModels) {
                if(rbacRoleModel.getId().equals(resourceModel.getRoleId())){
                    resourceArray.add(resourceModel.getResourceId());
                }
            }
            rbacRoleModel.setResourceArray(resourceArray);
        }

        Result result = new Result();

        result.setCode(200);
        result.setData(new PageInfo<>(list));
        result.setMsg("success");
        return result;
    }

    @Override
    public Result getAll() {
        List<RbacRoleModel> list = rbacRoleDao.getAll();

        Result result = new Result();
        result.setCode(200);
        result.setData(list);
        result.setMsg("success");
        return result;
    }

    @Transactional
    @Override
    public Result add(RbacRoleModel roleModel) {
        Result verifyResult = verifyParameter(roleModel);
        if (verifyResult.getCode() != 200) {
            return verifyResult;
        }

        Integer time = (int) Math.floor(DateTimeUtil.getNowTime() / 1000);

        roleModel.setCreateTime(time);
        roleModel.setUpdateTime(time);
        // 写入角色信息
        rbacRoleDao.add(roleModel);

        Integer roleId = roleModel.getId();

        writeRoleResource(roleId, roleModel.getResourceArray());
        writeRoleDept(roleId, roleModel.getDataRange(), roleModel.getDeptArray());

        Result result = new Result();
        result.setCode(200);
        result.setMsg("新增角色成功");
        return result;
    }

    @Transactional
    @Override
    public Result edit(Integer id, RbacRoleModel roleModel) {
        Result verifyResult = verifyParameter(roleModel);
        if (verifyResult.getCode() != 200) {
            return verifyResult;
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

        Result result = new Result();
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

    private Result verifyParameter(RbacRoleModel roleModel) {
        Result result = new Result();

        result.setCode(200);
        result.setMsg("success");

        if (roleModel.getResourceArray().size() <= 0) {
            result.setCode(400);
            result.setMsg("资源信息不能是空！");
        }

        if (roleModel.getDataRange() == 3 && roleModel.getDeptArray().size() <= 0) {
            result.setCode(400);
            result.setMsg("机构信息不能是空！");
        }

        return result;
    }

    private void writeRoleResource(Integer roleId, List<Integer> roleResourceArray) {
        if (roleResourceArray.size() <= 0) {
            return;
        }

        Integer time = (int) Math.floor(DateTimeUtil.getNowTime() / 1000);

        List<RbacResourceModel> all = rbacResourceDao.getAll();

        if (all == null) {
            return;
        }

        List<RbacRoleResourceModel> roleResourceModels = new ArrayList<>();

        for (Integer resourceId : roleResourceArray) {
            try {
                List<Integer> ids = new TreeUtil(all).buildTreeIds(resourceId);

                for (Integer id : ids) {
                    RbacRoleResourceModel roleResourceModel = new RbacRoleResourceModel();
                    roleResourceModel.setRoleId(roleId);
                    roleResourceModel.setCreateTime(time);
                    roleResourceModel.setResourceId(id);

                    roleResourceModels.add(roleResourceModel);
                }
            } catch (Exception e) {
                log.error(e.getMessage());
            }
        }

        // 写入角色资源
        rbacRoleResourceDao.addAll(roleResourceModels);
    }

    private void writeRoleDept(Integer roleId, Byte dataRange, List<Integer> roleDeptArray) {
        if (dataRange == 3 && roleDeptArray.size() > 0) {
            Integer time = (int) Math.floor(DateTimeUtil.getNowTime() / 1000);

            List<RbacDeptModel> all = rbacDeptDao.getAll();

            if (all == null) {
                return;
            }

            List<RbacRoleDeptModel> roleModels = new ArrayList<>();

            for (Integer deptId : roleDeptArray) {
                try {
                    List<Integer> ids = new TreeUtil(all).buildTreeIds(deptId);

                    for (Integer id : ids) {
                        RbacRoleDeptModel roleDeptModel = new RbacRoleDeptModel();
                        roleDeptModel.setCreateTime(time);
                        roleDeptModel.setDeptId(deptId);
                        roleDeptModel.setRoleId(roleId);

                        roleModels.add(roleDeptModel);
                    }
                } catch (Exception e) {
                    log.error(e.getMessage());
                }
            }

            // 写入角色机构
            rbacRoleDeptDao.addAll(roleModels);
        }
    }
}
