package org.lsq.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.lsq.pojo.Dept;

import java.util.List;

@Mapper
public interface DeptMapper {

    @Select("select * from dept")
    public List<Dept> list();

    @Delete("delete from dept where id = #{id}")
   public void delete(Integer id);

    @Insert("insert into dept (name, create_time, update_time) values (#{name}, #{createTime}, #{updateTime})")
    public void save(Dept dept);
}
