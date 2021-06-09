package com.haiyu.manager.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.haiyu.manager.common.utils.DateUtils;
import com.haiyu.manager.dao.DeptMapper;
import com.haiyu.manager.pojo.BaseDept;
import com.haiyu.manager.response.PageDataResult;
import com.haiyu.manager.service.DeptService;
import com.haiyu.manager.vo.DeptVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Title: DeptServiceImpl
 * @Description:
 * @author: ChenXue
 * @version: 1.0
 * @date: 2018/11/21 14:26
 */
@Service
public class DeptServiceImpl implements DeptService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private DeptMapper deptMapper;


    @Override
    public PageDataResult getDeptList(Integer pageNum, Integer pageSize) {
        PageDataResult pageDataResult = new PageDataResult();
        List<BaseDept> depts = deptMapper.getDeptList();

        List<DeptVO> deptList = new ArrayList<>();


        PageHelper.startPage(pageNum, pageSize);

        if (deptList.size() != 0) {
            PageInfo<DeptVO> pageInfo = new PageInfo<>(deptList);
            pageDataResult.setList(deptList);
            pageDataResult.setTotals((int) pageInfo.getTotal());
        }
        return pageDataResult;
    }

    @Override
    public Map<String, Object> addDept(BaseDept dept) {
        Map<String, Object> data = new HashMap();
        try {
            dept.setCreatedTime(DateUtils.getCurrentDate());
            dept.setUpdatedTime(DateUtils.getCurrentDate());
            dept.setStatus(1);
            int result = deptMapper.insert(dept);
            if (result == 0) {
                data.put("code", 0);
                data.put("msg", "新增部门失败");
                logger.error("新增部门失败");
                return data;
            }
            data.put("code", 1);
            data.put("msg", "新增部门成功");
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("添加部门并授权！异常！", e);
        }
        return data;

    }

    @Override
    public BaseDept findDeptById(Integer id) {
        return deptMapper.selectByPrimaryKey(id);
    }

    @Override
    public Map<String, Object> updateDept(BaseDept dept) {
        Map<String, Object> data = new HashMap();
        try {
            dept.setUpdatedTime(DateUtils.getCurrentDate());
            int result = deptMapper.updateDept(dept);
            if (result == 0) {
                data.put("code", 0);
                data.put("msg", "更新失败！");
                logger.error("部门[更新]，结果=更新失败！");
                return data;
            }
            data.put("code", 1);
            data.put("msg", "更新成功！");
            logger.info("部门[更新]，结果=更新成功！");
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("部门[更新]异常！", e);
            return data;
        }
        return data;
    }

    @Override
    public Map<String, Object> delDept(Integer id, Integer status) {
        Map<String, Object> data = new HashMap<>();
        try {
            int result = deptMapper.updateDeptStatus(id, status);
            if (result == 0) {
                data.put("code", 0);
                data.put("msg", "删除部门失败");
            }
            data.put("code", 1);
            data.put("msg", "删除部门成功");
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("删除部门异常！", e);
        }
        return data;
    }

    @Override
    public Map<String, Object> recoverDept(Integer id, Integer status) {
        Map<String, Object> data = new HashMap<>();
        try {
            int result = deptMapper.updateDeptStatus(id, status);
            if (result == 0) {
                data.put("code", 0);
                data.put("msg", "恢复部门失败");
            }
            data.put("code", 1);
            data.put("msg", "恢复部门成功");
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("恢复部门异常！", e);
        }
        return data;
    }

    @Override
    public List<BaseDept> getDepts() {
        return deptMapper.getDeptList();
    }
}
