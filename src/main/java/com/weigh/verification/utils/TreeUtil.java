package com.weigh.verification.utils;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.*;

/**
 * 树工具
 *
 * @author xuyang
 */
public class TreeUtil {
    private final List<Map<String, Object>> treeMap;
    private final List<Integer> treeIds = new ArrayList<>();

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
        for (Map<String, Object> treeNode : getRootNode(0)) {
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

        if(childMenus.size() > 0){
            parentNode.put("children", childMenus);
        }

        return parentNode;
    }

    /**
     * 获取根节点
     *
     * @return
     */
    private List<Map<String, Object>> getRootNode(Integer id) {
        List<Map<String, Object>> rootTreeLists = new ArrayList<>();
        for (Map<String, Object> treeNode : treeMap) {
            if (treeNode.get("parentId").equals(id)) {
                rootTreeLists.add(treeNode);
            }
        }
        return rootTreeLists;
    }


    /**
     * 建立树形结构
     *
     * @return
     */
    public List<Integer> buildTreeIds(Integer id) {
        this.treeIds.add(id);

        for (Map<String, Object> treeNode : getRootNode(id)) {
            this.treeIds.add((Integer) treeNode.get("id"));
            buildChildTreeIds(treeNode);
        }

        LinkedHashSet<Integer> hashSet = new LinkedHashSet<>(this.treeIds);
        return new ArrayList<>(hashSet);
    }

    /**
     * 递归，建立子树形结构
     *
     * @param parentNode
     * @return
     */
    private void buildChildTreeIds(Map<String, Object> parentNode) {
        for (Map<String, Object> treeNode : treeMap) {
            if (treeNode.get("parentId").equals(parentNode.get("id"))) {
                this.treeIds.add((Integer) treeNode.get("id"));
                buildChildTreeIds(treeNode);
            }
        }
    }
}
