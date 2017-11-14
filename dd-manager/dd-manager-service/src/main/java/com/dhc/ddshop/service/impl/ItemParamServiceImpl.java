package com.dhc.ddshop.service.impl;

import com.dhc.ddshop.common.dto.Page;
import com.dhc.ddshop.common.dto.Result;
import com.dhc.ddshop.dao.TbItemParamCustomMapper;
import com.dhc.ddshop.dao.TbItemParamMapper;
import com.dhc.ddshop.pojo.po.TbItemParam;
import com.dhc.ddshop.pojo.po.TbItemParamExample;
import com.dhc.ddshop.pojo.vo.TbItemParamCustom;
import com.dhc.ddshop.service.ItemParamService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * User: Administrator
 * Date: 2017/11/14
 * Time: 9:30
 * Version:V1.0
 */
@Service
public class ItemParamServiceImpl implements ItemParamService {
    private Logger logger= LoggerFactory.getLogger(getClass());
    @Autowired
    private TbItemParamCustomMapper itemParamCustomDao;
    @Autowired
    private TbItemParamMapper itemParamDao;
    @Override
    public Result<TbItemParamCustom> listItemParamsByPage(Page page) {
        Result<TbItemParamCustom> result=null;
        try{
            Map<String,Object> map= new HashMap<String,Object>();
            map.put("page",page);
            //取出tb_item_param这张表记录条数
            int count=itemParamCustomDao.countItemParams();
            //取出对应页码的记录集合
            List<TbItemParamCustom> list=itemParamCustomDao.listItemParamsByPage(map);
            result=new Result<TbItemParamCustom>();
            result.setTotal(1000);
            result.setRows(list);
        }catch (Exception e){
            logger.error(e.getMessage(),e);
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public TbItemParam getItemParamByCid(Long cid) {
        TbItemParam tbItemParam =null;
        try{
            TbItemParamExample example=new TbItemParamExample();
            TbItemParamExample.Criteria criteria=example.createCriteria();
            criteria.andItemCatIdEqualTo(cid);
            List<TbItemParam> list = itemParamDao.selectByExampleWithBLOBs(example);
            if(list!=null && list.size()>0){
                tbItemParam =list.get(0);
            }
        }catch (Exception e){
            logger.error(e.getMessage(),e);
            e.printStackTrace();
        }
        return tbItemParam;
    }

}
