package com.haiyu.manager.dao;


import com.haiyu.manager.pojo.SettingsPort;
import com.haiyu.manager.query.PortQuery;
import com.haiyu.manager.vo.PortVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import tk.mapper.MyMapper;

import java.util.List;

@Repository
public interface SettingsPortMapper extends MyMapper<SettingsPort> {

    List<PortVO> getPortList(PortQuery payTypeSearchDTO);

    SettingsPort findByValue(@Param("value") Integer value);

    int updatePort(SettingsPort pojo);

    int updatePortStatus(@Param("id") Integer id, @Param("status") Integer status);

}