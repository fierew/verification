package com.weigh.verification.utils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 树工具
 *
 * @author xuyang
 */
public class TreeUtil {
    private final List<Map<String, Object>> treeMap;

    public TreeUtil(List<Object> lists) throws IllegalAccessException {
        List<Map<String, Object>> treeList = new ArrayList<>();

        for (Object list : lists) {
            Map<String, Object> map = new HashMap<>();

            Field[] declaredFields = list.getClass().getDeclaredFields();
            for (Field field : declaredFields) {
                field.setAccessible(true);
                map.put(field.getName(), field.get(list));
            }

            treeList.add(map);
        }

        this.treeMap = treeList;
    }

    /**
     * 建立树形结构
     *
     * @return
     */
    public List<Map<String, Object>> buildTree() {
        List<Map<String, Object>> treeLists = new ArrayList<>();
        for (Map<String, Object> treeNode : getRootNode()) {
            Map<String, Object> childNode = buildChildTree(treeNode);
            treeLists.add(childNode);
        }
        return treeLists;
    }

    /**
     * 递归，建立子树形结构
     *
     * @param parentNode
     * @return
     */
    private Map<String, Object> buildChildTree(Map<String, Object> parentNode) {
        List<Map<String, Object>> childMenus = new ArrayList<>();
        for (Map<String, Object> treeNode : treeMap) {

            if (treeNode.get("'parentId'").equals(parentNode.get("id"))) {
                childMenus.add(buildChildTree(treeNode));
            }
        }
        parentNode.put("children", childMenus);
        return parentNode;
    }

    /**
     * 获取根节点
     *
     * @return
     */
    private List<Map<String, Object>> getRootNode() {
        List<Map<String, Object>> rootTreeLists = new ArrayList<>();
        for (Map<String, Object> treeNode : treeMap) {
            if (treeNode.get("parentId").equals(0)) {
                rootTreeLists.add(treeNode);
            }
        }
        return rootTreeLists;
    }
}
