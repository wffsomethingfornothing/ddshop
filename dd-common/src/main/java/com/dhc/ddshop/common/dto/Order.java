package com.dhc.ddshop.common.dto;

import java.util.ArrayList;
import java.util.List;

/**
 * User: Administrator
 * Date: 2017/11/9
 * Time: 11:20
 * Version:V1.0
 */
public class Order {
    private String sort;
    private String order;

    public List<String> getOrderParams() {
        String[] sorts = sort.split(",");
        String[] orders= order.split(",");
        List<String> list=new ArrayList<String>();
        for(int i=0;i<sorts.length;i++){
            String s = sorts[i] + " " + orders[i];
            list.add(s);
        }
        return list;
    }

    public String getSort() {
        return sort;
    }

    public String getOrder() {
        return order;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public void setOrder(String order) {
        this.order = order;
    }
}
