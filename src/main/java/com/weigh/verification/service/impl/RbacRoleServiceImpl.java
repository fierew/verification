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
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
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
        for (RbacRoleModel rbacRoleModel : list) {
            roleIds.add(rbacRoleModel.getId());
        }

        for (RbacRoleModel rbacRoleModel : list) {
            String deptIdsText = rbacRoleModel.getDeptIds();
            List<Integer> deptIds = new ArrayList<>();
            if(!"".equals(deptIdsText)){
                String[] deptArray = deptIdsText.split(",");
                for (String deptId : deptArray) {
                    deptIds.add(Integer.parseInt(deptId));
                }
            }

            rbacRoleModel.setDeptArray(deptIds);

            String resourceIdsText = rbacRoleModel.getResourceIds();
            List<Integer> resourceIds = new ArrayList<>();
            if(!"".equals(resourceIdsText)){
                String[] resourceArray = resourceIdsText.split(",");
                for (String resourceId : resourceArray) {
                    resourceIds.add(Integer.parseInt(resourceId));
                }
            }
            rbacRoleModel.setResourceArray(resourceIds);

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
        roleModel.setResourceIds(StringUtils.join(roleModel.getResourceArray(), ","));
        roleModel.setDeptIds(StringUtils.join(roleModel.getDeptArray(), ","));


        // 写入角色信息
        rbacRoleDao.add(roleModel);

        Integer roleId = roleModel.getId();

        // 获取机构子节点
        // 合并机构子节点

        writeRoleResource(roleId, roleModel.getResourceArray(),roleModel.getResourceParent());
        writeRoleDept(roleId, roleModel.getDataRange(), roleModel.getDeptArray(),roleModel.getDeptParent());

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
        roleModel.setResourceIds(StringUtils.join(roleModel.getResourceArray(), ","));
        roleModel.setDeptIds(StringUtils.join(roleModel.getDeptArray(), ","));


        rbacRoleDao.edit(roleModel);

        // 删除角色资源
        rbacRoleResourceDao.delete(id);
        writeRoleResource(id, roleModel.getResourceArray(),roleModel.getResourceParent());

        // 删除角色机构
        rbacRoleDeptDao.delete(id);
        writeRoleDept(id, roleModel.getDataRange(), roleModel.getDeptArray(),roleModel.getDeptParent());

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

    private void writeRoleResource(Integer roleId, List<Integer> roleResourceArray, List<Integer> roleResourceParent) {
        if (roleResourceArray.size() <= 0) {
            return;
        }

        Integer time = (int) Math.floor(DateTimeUtil.getNowTime() / 1000);

        List<RbacResourceModel> all = rbacResourceDao.getAll();

        if (all == null) {
            return;
        }



        for (Integer resourceId : roleResourceArray) {
            try {
                List<Integer> ids = new TreeUtil(all).buildTreeIds(resourceId);

                roleResourceParent.addAll(ids);
            } catch (Exception e) {
                log.error(e.getMessage());
            }
        }

        LinkedHashSet<Integer> hashSet = new LinkedHashSet<>(roleResourceParent);
        List<Integer> resourceParent = new ArrayList<>(hashSet);

        List<RbacRoleResourceModel> roleResourceModels = new ArrayList<>();
        for (Integer parentId : resourceParent) {
            RbacRoleResourceModel roleResourceModel = new RbacRoleResourceModel();
            roleResourceModel.setRoleId(roleId);
            roleResourceModel.setCreateTime(time);
            roleResourceModel.setResourceId(parentId);

            roleResourceModels.add(roleResourceModel);
        }

        // 写入角色资源
        rbacRoleResourceDao.addAll(roleResourceModels);
    }

    private void writeRoleDept(Integer roleId, Byte dataRange, List<Integer> roleDeptArray, List<Integer> roleDeptParent) {
        if (dataRange == 3 && roleDeptArray.size() > 0) {
            Integer time = (int) Math.floor(DateTimeUtil.getNowTime() / 1000);

            List<RbacDeptModel> all = rbacDeptDao.getAll();

            if (all == null) {
                return;
            }


            for (Integer deptId : roleDeptArray) {
                try {
                    List<Integer> ids = new TreeUtil(all).buildTreeIds(deptId);
                    roleDeptParent.addAll(ids);
                } catch (Exception e) {
                    log.error(e.getMessage());
                }
            }

            LinkedHashSet<Integer> hashSet = new LinkedHashSet<>(roleDeptParent);
            List<Integer> deptParent = new ArrayList<>(hashSet);

            List<RbacRoleDeptModel> roleModels = new ArrayList<>();
            for (Integer parentId : deptParent) {
                RbacRoleDeptModel roleDeptModel = new RbacRoleDeptModel();
                roleDeptModel.setCreateTime(time);
                roleDeptModel.setDeptId(parentId);
                roleDeptModel.setRoleId(roleId);

                roleModels.add(roleDeptModel);
            }

            // 写入角色机构
            rbacRoleDeptDao.addAll(roleModels);
        }
    }
}
