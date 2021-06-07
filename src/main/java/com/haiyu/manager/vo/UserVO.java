package com.haiyu.manager.vo;

import lombok.Data;

/**
 * @Title: UserVO
 * @Description:
 * @author: ChenXue
 * @version: 1.0
 * @date: 2018/12/3 12:13
 */
@Data
public class UserVO {

    private Integer id;

    private String sysUserName;

    private String sysUserPwd;

    private Integer roleId;

    private String roleName;

    private String userPhone;


    private String regTime;


    private Integer userStatus;


}
