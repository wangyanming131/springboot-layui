package com.haiyu.manager.dao;


import com.haiyu.manager.pojo.SettingsDicType;
import com.haiyu.manager.query.DicTypeQuery;
import com.haiyu.manager.vo.DicTypeVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import tk.mapper.MyMapper;

import java.util.List;

@Repository
public interface SettingsDicTypeMapper extends MyMapper<SettingsDicType> {

    List<DicTypeVO> getDicTypeList(DicTypeQuery query);

    SettingsDicType findByDicTypeCode(@Param("typeCode") String typeCode);

    SettingsDicType findByDicTypeName(@Param("typeName") String typeName);

    int updateDicType(SettingsDicType pojo);

    int updateDicTypeStatus(@Param("id") Integer id, @Param("status") Integer status);

}