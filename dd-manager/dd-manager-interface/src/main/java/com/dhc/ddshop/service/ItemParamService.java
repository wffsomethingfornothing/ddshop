package com.dhc.ddshop.service;

import com.dhc.ddshop.common.dto.Page;
import com.dhc.ddshop.common.dto.Result;
import com.dhc.ddshop.pojo.po.TbItemParam;
import com.dhc.ddshop.pojo.vo.TbItemParamCustom;

/**
 * User: Administrator
 * Date: 2017/11/14
 * Time: 9:28
 * Version:V1.0
 */
public interface ItemParamService {
    /**
     * 对规格进行分页
     * @param page
     * @return
     */


    Result<TbItemParamCustom> listItemParamsByPage(Page page);

    /**
     *
     * @param cid
     * @return
     */
    TbItemParam getItemParamByCid(Long cid);
}
