package com.cloud.web.common.data.mapper;

import com.cloud.web.common.data.pojo.LicenceObj;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface LicenceMapper {
    int insert(LicenceObj obj);

    int deleteByPrimaryKey(String uuid);

    LicenceObj selectByPrimaryKey(String uuid);

    int updateByPrimaryKey(LicenceObj record);

    @Select("SELECT * FROM licence_info WHERE uuid=#{uuid}")
    List<LicenceObj> query(String uuid);

    @Select("select uuid, name, remark, num,used_num as usedNum,create_time createTime,sec_key secKey " +
            "from licence_info " +
            "where num > used_num and uuid=#{uuid}")
    LicenceObj loadValidateLicence(@Param("uuid") String id);
}
