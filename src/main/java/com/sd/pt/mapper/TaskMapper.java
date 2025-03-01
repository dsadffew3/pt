package com.sd.pt.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sd.pt.entity.Tasks;
import com.sd.pt.entity.Users;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface TaskMapper extends BaseMapper<Tasks> {
    @Select("select * from TASKS ")
    public List<Tasks> find();
}
