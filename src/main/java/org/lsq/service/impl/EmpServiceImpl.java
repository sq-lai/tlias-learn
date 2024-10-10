package org.lsq.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.lsq.annotation.AnnotationTest;
import org.lsq.annotation.Log;
import org.lsq.mapper.EmpMapper;
import org.lsq.pojo.Emp;
import org.lsq.pojo.PageBean;
import org.lsq.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
public class EmpServiceImpl implements EmpService {

    @Autowired
    private EmpMapper empMapper;

    @Override
    public PageBean page(Integer page, Integer pageSize, String name, Short gender, LocalDate begin, LocalDate end) {

//        Long page1 = empMapper.page(page);
//
//        Integer start = (page - 1) * pageSize;
//        List<Emp> pageSize1 = empMapper.pageSize(start,pageSize);

        //使用pagehelper插件
        PageHelper.startPage(page, pageSize);
        List<Emp> list = empMapper.page(name,gender,begin,end);
        Page<Emp> pageList = (Page<Emp>) list;
        PageBean pageBean = new PageBean(pageList.getTotal(), pageList.getResult());
        return pageBean;
    }


    @Override
    @Log
    public void deleteEmp(List<String> ids) {
        empMapper.deleteEmp(ids);
    }


    @Override
    @Log
    public void save(Emp emp) {
        emp.setCreateTime(LocalDateTime.now());
        emp.setUpdateTime(LocalDateTime.now());
        empMapper.save(emp);
    }


    @Override
    @Log
    public Emp query(Integer id) {
        Emp emp = empMapper.query(id);
        return emp;
    }


    @AnnotationTest
    @Override
    @Log
    public void update(Emp emp) {
        emp.setUpdateTime(LocalDateTime.now());
        empMapper.update(emp);
    }


    @AnnotationTest
    @Override
    public Emp login(Emp emp) {
        return empMapper.getinformation(emp);
    }


}
