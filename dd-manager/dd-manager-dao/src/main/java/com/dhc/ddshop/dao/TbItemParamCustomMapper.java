package com.dhc.ddshop.dao;

import com.dhc.ddshop.pojo.vo.TbItemParamCustom;

import java.util.List;
import java.util.Map; /**
 * User: Administrator
 * Date: 2017/11/14
 * Time: 9:47
 * Version:V1.0
 */
public interface TbItemParamCustomMapper {
    int countItemParams();

    List<TbItemParamCustom> listItemParamsByPage(Map<String, Object> map);
}
