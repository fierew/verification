package com.weigh.verification.utils;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
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

    public TreeUtil(List<?> lists) throws Exception {
        List<Map<String, Object>> treeList = new ArrayList<>();

        for (Object list : lists) {
            Map<String, Object> map = new HashMap<>();

            BeanInfo beanInfo = Introspector.getBeanInfo(list.getClass());
            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();

            for (PropertyDescriptor property : propertyDescriptors) {
                String key = property.getName();

                if(key.compareToIgnoreCase("class") == 0){
                    continue;
                }
                Method getter = property.getReadMethod();
                Object value = getter != null ? getter.invoke(list) : null;
                map.put(key, value);
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

            if (treeNode.get("parentId").equals(parentNode.get("id"))) {
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
