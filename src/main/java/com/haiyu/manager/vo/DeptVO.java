package com.haiyu.manager.vo;

import lombok.Data;

/**
 * @Title: DeptVO
 * @Description:
 * @author: ChenXue
 * @version: 1.0
 * @date: 2018/12/3 18:51
 */
@Data
public class DeptVO {

    private Integer id;

    /**
     * 部门名称
     */
    private String deptName;

    /**
     * 上级部门id
     */
    private Integer parentID;

    /**
     * 排序asc
     */
    private Integer orderNum;

    /**
     * 创建时间
     */
    private String createdTime;

    /**
     * 更新时间
     */
    private String updatedTime;

    /**
     * 状态:1：有效;0：无效
     */
    private Integer status;


}
