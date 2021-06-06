package com.haiyu.manager.pojo;

import javax.persistence.*;

@Table(name = "settings_port")
public class SettingsPort {

    /**
     * ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 端口号值0-65535
     */
    @Column(name = "value")
    private Integer value;

    /**
     * 应用描述
     */
    @Column(name = "description")
    private String description;

    /**
     * 链接地址
     */
    @Column(name = "link")
    private String link;

    /**
     * 启动方式(1:手动;2:随机启动)
     */
    @Column(name = "starting_type")
    private Integer starting_type;


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

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public Integer getStarting_type() {
        return starting_type;
    }

    public void setStarting_type(Integer starting_type) {
        this.starting_type = starting_type;
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
                ", value='" + value + '\'' +
                ", description='" + description + '\'' +
                ", link='" + link + '\'' +
                ", starting_type='" + starting_type + '\'' +
                ", createdTime=" + createdTime +
                ", updatedTime='" + updatedTime + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}