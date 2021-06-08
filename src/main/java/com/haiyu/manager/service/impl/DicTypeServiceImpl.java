package com.haiyu.manager.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.haiyu.manager.common.utils.DateUtils;
import com.haiyu.manager.dao.SettingsDicTypeMapper;
import com.haiyu.manager.pojo.SettingsDicType;
import com.haiyu.manager.query.DicTypeQuery;
import com.haiyu.manager.response.PageDataResult;
import com.haiyu.manager.service.DicTypeService;
import com.haiyu.manager.vo.DicTypeVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Title: DicTypeServiceImpl
 * @Description:
 * @author: ChenXue
 * @version: 1.0
 * @date: 2021-6-2 15:14:38
 */
@Service
public class DicTypeServiceImpl implements DicTypeService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SettingsDicTypeMapper settingsDicTypeMapper;

    @Override
    public PageDataResult getDicTypeList(DicTypeQuery query, Integer pageNum, Integer pageSize) {
        PageDataResult pageDataResult = new PageDataResult();
        List<DicTypeVO> payTypes = settingsDicTypeMapper.getDicTypeList(query);

        PageHelper.startPage(pageNum, pageSize);

        if (payTypes.size() != 0) {
            PageInfo<DicTypeVO> pageInfo = new PageInfo<>(payTypes);
            pageDataResult.setList(payTypes);
            pageDataResult.setTotals((int) pageInfo.getTotal());
        }

        return pageDataResult;
    }


    @Override
    public Map<String, Object> addDicType(SettingsDicType pojo) {
        Map<String, Object> data = new HashMap();
        try {

            SettingsDicType old = settingsDicTypeMapper.findByDicTypeCode(pojo.getTypeCode());
            if (old != null) {
                data.put("code", 0);
                data.put("msg", "该字典类型代码已存在！");
                logger.error("字典类型[新增]，结果=该字典类型代码已存在！");
                return data;
            }

            old = settingsDicTypeMapper.findByDicTypeName(pojo.getTypeName());
            if (old != null) {
                data.put("code", 0);
                data.put("msg", "该字典类型名称已存在！");
                logger.error("字典类型[新增]，结果=该字典类型名称已存在！");
                return data;
            }


            pojo.setCreatedTime(DateUtils.getCurrentDate());
            pojo.setStatus(1);
            int result = settingsDicTypeMapper.insert(pojo);
            if (result == 0) {
                data.put("code", 0);
                data.put("msg", "新增失败！");
                logger.error("字典类型[新增]，结果=新增失败！");
                return data;
            }
            data.put("code", 1);
            data.put("msg", "新增成功！");
            logger.info("字典类型[新增]，结果=新增成功！");
        } catch (Exception e) {
            e.printStackTrace();
            data.put("msg", "字典类型[新增]异常！");
            logger.error("字典类型[新增]异常！", e);
            return data;
        }
        return data;
    }

    @Override
    public Map<String, Object> updateDicType(SettingsDicType pojo) {
        Map<String, Object> data = new HashMap();
        SettingsDicType old = settingsDicTypeMapper.findByDicTypeName(pojo.getTypeName());
        // 根据名称查询最多有一条数据,如果未查到,直接修改即可,如果存在一条数据需要判断前端id和数据库id是否相同,如果相同时同一条数据,可以修改,如果不同不允许修改成两条名称一样的数据
        if (old != null) {
            if (pojo.getId().equals(old.getId()) == false) {
                data.put("code", 0);
                data.put("msg", "字典类型名称已存在！");
                logger.error("字典类型[更新]，结果=字典类型名称已存在！");
                return data;
            }
        }

        pojo.setUpdatedTime(DateUtils.getCurrentDate());
        int rows = settingsDicTypeMapper.updateDicType(pojo);
        if (rows == 0) {
            data.put("code", 0);
            data.put("msg", "更新失败！");
            logger.error("字典类型[更新]，结果=更新失败！");
            return data;
        }
        data.put("code", 1);
        data.put("msg", "更新成功！");
        logger.info("字典类型[更新]，结果=更新成功！");
        return data;
    }

    @Override
    public Map<String, Object> delDicType(Integer id, Integer status) {
        Map<String, Object> data = new HashMap<>();
        try {
            // 删除字典类型
            int result = settingsDicTypeMapper.updateDicTypeStatus(id, status);
            if (result == 0) {
                data.put("code", 0);
                data.put("msg", "删除字典类型失败");
                logger.error("删除字典类型失败");
                return data;
            }
            data.put("code", 1);
            data.put("msg", "删除字典类型成功");
            logger.info("删除字典类型成功");
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("删除字典类型异常！", e);
        }
        return data;
    }

    @Override
    public Map<String, Object> recoverDicType(Integer id, Integer status) {
        Map<String, Object> data = new HashMap<>();
        try {
            int result = settingsDicTypeMapper.updateDicTypeStatus(id, status);
            if (result == 0) {
                data.put("code", 0);
                data.put("msg", "恢复字典类型失败");
            }
            data.put("code", 1);
            data.put("msg", "恢复字典类型成功");
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("恢复字典类型异常！", e);
        }
        return data;
    }
}
