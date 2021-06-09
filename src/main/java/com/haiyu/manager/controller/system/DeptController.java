package com.haiyu.manager.controller.system;

import com.haiyu.manager.pojo.BaseDept;
import com.haiyu.manager.response.PageDataResult;
import com.haiyu.manager.service.DeptService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @Title: DeptController
 * @Description: 部门管理
 * @author: ChenXue
 * @version: 1.0
 * @date: 2018/11/21 13:43
 */
@Controller
@RequestMapping("/base/dept")
public class DeptController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private DeptService deptService;

    /**
     * 跳转到部门管理
     *
     * @return
     */
    @RequestMapping("/deptManage")
    public String toPage() {
        logger.info("进入部门管理");
        return "/modules/base/dept/deptManage";
    }

    /**
     * 功能描述: 获取部门列表
     *
     * @param:
     * @return:
     * @auther: ChenXue
     * @date: 2018/11/21 14:29
     */
    @RequestMapping(value = "/getDeptList", method = RequestMethod.GET)
    @ResponseBody
    public PageDataResult getDeptList(@RequestParam("pageNum") Integer pageNum,
                                      @RequestParam("pageSize") Integer pageSize) {
        logger.info("获取部门列表");
        PageDataResult pdr = new PageDataResult();
        try {
            if (null == pageNum) {
                pageNum = 1;
            }
            if (null == pageSize) {
                pageSize = 10;
            }
            // 获取部门列表
            pdr = deptService.getDeptList(pageNum, pageSize);
            logger.info("部门列表查询=pdr:" + pdr);

        } catch (Exception e) {
            e.printStackTrace();
            logger.error("部门列表查询异常！", e);
        }
        return pdr;
    }

    /**
     * 功能描述: 获取部门列表
     *
     * @param:
     * @return:
     * @auther: ChenXue
     * @date: 2018/12/3 13:22
     */
    @GetMapping("getDepts")
    @ResponseBody
    public List<BaseDept> getDepts() {
        logger.info("获取部门列表");
        return deptService.getDepts();
    }

    /**
     * 述: 设置部门[新增或更新]
     *
     * @param:
     * @return:
     * @auther: ChenXue
     * @date: 2018/12/3 10:54
     */
    @PostMapping("setDept")
    @ResponseBody
    public Map<String, Object> setDept(BaseDept dept) {
        logger.info("设置部门[新增或更新]！dept:" + dept);
        Map<String, Object> data = new HashMap();
        if (dept.getId() == null) {
            //新增部门
            data = deptService.addDept(dept);
        } else {
            //修改部门
            data = deptService.updateDept(dept);
        }
        return data;
    }


    /**
     * 功能描述: 删除/恢复部门
     *
     * @param:
     * @return:
     * @auther: ChenXue
     * @date: 2018/11/21 16:00
     */
    @PostMapping("updateDeptStatus")
    @ResponseBody
    public Map<String, Object> updateDeptStatus(@RequestParam("id") int id, @RequestParam("status") Integer status) {
        logger.info("删除/恢复部门！id:" + id + " status:" + status);
        Map<String, Object> data = new HashMap<>();
        if (status == 0) {
            //删除部门
            data = deptService.delDept(id, status);
        } else {
            //恢复部门
            data = deptService.recoverDept(id, status);
        }
        return data;
    }

}
