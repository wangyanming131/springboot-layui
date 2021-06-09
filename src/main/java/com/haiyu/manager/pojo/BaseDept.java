package com.haiyu.manager.pojo;

import javax.persistence.*;

@Table(name = "base_department")
public class BaseDept {
    /**
     * ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 部门名称
     */
    @Column(name = "dept_name")
    private String deptName;

    /**
     * 上级部门id
     */
    @Column(name = "parent_id")
    private Integer parentID;

    /**
     * 排序asc
     */
    @Column(name = "order_num")
    private Integer orderNum;

    /**
     * 创建时间
     */
    @Column(name = "created_time")
    private String createdTime;

    /**
     * 更新时间
     */
    @Column(name = "updated_time")
    private String updatedTime;

    /**
     * 状态:1：有效;0：无效
     */
    @Column(name = "status")
    private Integer status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public Integer getParentID() {
        return parentID;
    }

    public void setParentID(Integer parentID) {
        this.parentID = parentID;
    }

    public Integer getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }

    public String getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(String createdTime) {
        this.createdTime = createdTime;
    }

    public String getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(String updatedTime) {
        this.updatedTime = updatedTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}