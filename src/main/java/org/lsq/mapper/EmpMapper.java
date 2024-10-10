package org.lsq.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.lsq.pojo.Emp;

import java.time.LocalDate;
import java.util.List;

@Mapper
public interface EmpMapper {

//    @Select("select count(*) from emp")
//    public Long page(Integer page);
//
//    @Select("select * from emp limit #{start},#{pageSize}")
//    public List<Emp> pageSize(Integer start, Integer pageSize);

    //使用pagehelper插件来做

    //条件分页查询，使用xml文件来做
    public List<Emp> page(String name, Short gender, LocalDate begin, LocalDate end);

    void deleteEmp(List<String> ids);

    @Insert("insert into emp (username, name, gender, image, job, entrydate, dept_id, create_time, update_time) " +
            "values (#{username}, #{name}, #{gender}, #{image}, #{job}, #{entrydate}, #{deptId}, #{createTime}, #{updateTime});")
    void save(Emp emp);


    @Select("select * from emp where id = #{id}")
    Emp query(Integer id);


    void update(Emp emp);


    @Select("select * from emp where username = #{username} and password = #{password}")
    Emp getinformation(Emp emp);


    @Delete("delete from emp where dept_id = #{id}")
    void deleteByDeptId(Integer id);
}
