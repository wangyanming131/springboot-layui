package com.haiyu.manager.controller.settings;

import com.haiyu.manager.pojo.SettingsDicType;
import com.haiyu.manager.query.DicTypeQuery;
import com.haiyu.manager.response.PageDataResult;
import com.haiyu.manager.service.DicTypeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * 基本设置-字典类型
 */
@Controller
@RequestMapping(value = "/settings/dicType")
public class DicTypeController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private DicTypeService dicTypeService;


    /**
     * 跳转到字典类型管理
     *
     * @return
     */
    @RequestMapping("/dicTypeManage")
    public String toPage() {
        logger.info("进入字典类型管理");
        return "/modules/settings/dicType/dicTypeManage";
    }

    /**
     * 功能描述: 分页查询字典类型列表
     *
     * @param:
     * @return:
     * @auther: ChenXue
     * @date: 2021-6-2 14:32:56
     */
    @RequestMapping(value = "/getDicTypeList", method = RequestMethod.POST)
    @ResponseBody
    public PageDataResult getDicTypeList(@RequestParam("pageNum") Integer pageNum,
                                         @RequestParam("pageSize") Integer pageSize,/*@Valid PageRequest page,*/ DicTypeQuery query) {
        /*logger.info("分页查询字典类型列表！搜索条件：dicTypeSearch：" + dicTypeSearch + ",pageNum:" + page.getPageNum()
                + ",每页记录数量pageSize:" + page.getPageSize());*/
        logger.info("url:/settings/dicType/getDicTypeList");
        PageDataResult pdr = new PageDataResult();
        try {
            if (null == pageNum) {
                pageNum = 1;
            }
            if (null == pageSize) {
                pageSize = 10;
            }
            // 获取字典类型列表
            pdr = dicTypeService.getDicTypeList(query, pageNum, pageSize);
            logger.info("字典类型列表查询=pdr:" + pdr);

        } catch (Exception e) {
            e.printStackTrace();
            logger.error("字典类型列表查询异常！", e);
        }
        return pdr;
    }

    /**
     * 功能描述: 新增和更新字典类型
     *
     * @param:
     * @return:
     * @auther: ChenXue
     * @date: 2021-6-3 09:49:03
     */
    @RequestMapping(value = "/setDicType", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> setDicType(SettingsDicType pojo) {
        logger.info("设置字典类型[新增或更新]！pojo:" + pojo);
        Map<String, Object> data = new HashMap();
        if (pojo.getId() == null) {
            data = dicTypeService.addDicType(pojo);
        } else {
            data = dicTypeService.updateDicType(pojo);
        }
        return data;
    }

    /**
     * 功能描述: 删除/恢复 字典类型
     *
     * @param:
     * @return:
     * @auther: ChenXue
     * @date: 2021-6-3 15:54:30
     */
    @RequestMapping(value = "/updateDicTypeStatus", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> updateDicTypeStatus(@RequestParam("id") Integer id, @RequestParam("status") Integer status) {
        logger.info("删除/恢复字典类型！id:" + id + " status:" + status);
        Map<String, Object> data = new HashMap<>();
        if (status == 0) {
            //删除字典类型
            data = dicTypeService.delDicType(id, status);
        } else {
            //恢复字典类型
            data = dicTypeService.recoverDicType(id, status);
        }
        return data;
    }


}
