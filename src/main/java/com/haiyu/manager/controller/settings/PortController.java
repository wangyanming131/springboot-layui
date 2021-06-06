package com.haiyu.manager.controller.settings;

import com.haiyu.manager.pojo.SettingsPort;
import com.haiyu.manager.query.PortQuery;
import com.haiyu.manager.response.PageDataResult;
import com.haiyu.manager.service.PortService;
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
 * 基本设置-端口号统计controller
 */
@Controller
@RequestMapping(value = "/settings/port")
public class PortController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private PortService portService;


    /**
     * 跳转到端口号管理
     *
     * @return
     */
    @RequestMapping("/portManage")
    public String toPage() {
        logger.info("进入端口号管理");
        return "/settings/port/portManage";
    }

    /**
     * 功能描述: 分页查询端口号列表
     *
     * @param:
     * @return:
     * @auther: ChenXue
     * @date: 2021-6-2 14:32:56
     */
    @RequestMapping(value = "/getPortList", method = RequestMethod.POST)
    @ResponseBody
    public PageDataResult getPortList(@RequestParam("pageNum") Integer pageNum,
                                      @RequestParam("pageSize") Integer pageSize,/*@Valid PageRequest page,*/ PortQuery query) {
        /*logger.info("分页查询端口号列表！搜索条件：portSearch：" + portSearch + ",pageNum:" + page.getPageNum()
                + ",每页记录数量pageSize:" + page.getPageSize());*/
        logger.info("url:/settings/port/getPortList");
        PageDataResult pdr = new PageDataResult();
        try {
            if (null == pageNum) {
                pageNum = 1;
            }
            if (null == pageSize) {
                pageSize = 10;
            }
            // 获取端口号列表
            pdr = portService.getPortList(query, pageNum, pageSize);
            logger.info("端口号列表查询=pdr:" + pdr);

        } catch (Exception e) {
            e.printStackTrace();
            logger.error("端口号列表查询异常！", e);
        }
        return pdr;
    }

    /**
     * 功能描述: 新增和更新端口号
     *
     * @param:
     * @return:
     * @auther: ChenXue
     * @date: 2021-6-3 09:49:03
     */
    @RequestMapping(value = "/setPort", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> setPort(SettingsPort pojo) {
        logger.info("设置端口号[新增或更新]！pojo:" + pojo);
        Map<String, Object> data = new HashMap();
        if (pojo.getId() == null) {
            data = portService.addPort(pojo);
        } else {
            data = portService.updatePort(pojo);
        }
        return data;
    }

    /**
     * 功能描述: 删除/恢复 端口号
     *
     * @param:
     * @return:
     * @auther: ChenXue
     * @date: 2021-6-3 15:54:30
     */
    @RequestMapping(value = "/updatePortStatus", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> updatePortStatus(@RequestParam("id") Integer id, @RequestParam("status") Integer status) {
        logger.info("删除/恢复端口号！id:" + id + " status:" + status);
        Map<String, Object> data = new HashMap<>();
        if (status == 0) {
            //删除端口号
            data = portService.delPort(id, status);
        } else {
            //恢复端口号
            data = portService.recoverPort(id, status);
        }
        return data;
    }


}
