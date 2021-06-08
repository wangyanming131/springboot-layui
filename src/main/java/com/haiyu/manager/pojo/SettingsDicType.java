package com.haiyu.manager.pojo;

import javax.persistence.*;

@Table(name = "settings_dictionary_type")
public class SettingsDicType {

    /**
     * ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 支付方式代码
     */
    @Column(name = "type_code")
    private String typeCode;

    /**
     * 支付方式中文名称
     */
    @Column(name = "type_name")
    private String typeName;


    /**
     * 创建时间
     */
    @Column(name = "created_time")
    private String createdTime;

    /**
     * 修改时间
     */
    @Column(name = "updated_time")
    private String updatedTime;

    /**
     * 状态(0:无效;1:有效)
     */
    @Column(name = "status")
    private Integer status;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTypeCode() {
        return typeCode;
    }

    public void setTypeCode(String typeCode) {
        this.typeCode = typeCode;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
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

    @Override
    public String toString() {
        return "SettingsPayType{" +
                "id=" + id +
                ", typeCode='" + typeCode + '\'' +
                ", typeName='" + typeName + '\'' +
                ", createdTime=" + createdTime +
                ", updatedTime='" + updatedTime + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}