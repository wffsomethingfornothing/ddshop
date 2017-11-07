package com.dhc.ddshop.pojo.vo;

import com.dhc.ddshop.pojo.po.TbItem;

/**
 * 自定义的商品显示类(VO)
 * User: DHC
 * Date: 2017/11/7
 * Time: 15:32
 * Version:V1.0
 */
public class TbItemCustom extends TbItem{

    private String catName;

    public String getCatName() {
        return catName;
    }

    public void setCatName(String catName) {
        this.catName = catName;
    }
}
