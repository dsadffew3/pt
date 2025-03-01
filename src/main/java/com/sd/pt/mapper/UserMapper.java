package com.sd.pt.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sd.pt.entity.Users;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

@Mapper
public interface UserMapper extends BaseMapper<Users> {
    @Select("select * from USERS")
    public List<Users> find();

    @Select("select ROLE from USERS where ID=#{id}")
    public String getRole(Long id);

    @Select("select IF_WORK from USERS where ID=#{id}")
    public int getava(Long id);
}
