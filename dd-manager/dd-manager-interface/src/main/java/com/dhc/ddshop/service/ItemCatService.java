package com.dhc.ddshop.service;


import com.dhc.ddshop.common.dto.TreeNode;

import java.util.List;

/**
 * User: Administrator
 * Date: 2017/11/10
 * Time: 21:19
 * Version:V1.0
 */
public interface ItemCatService {
    List<TreeNode> listItemCatsByPid(Long parentId);
}
