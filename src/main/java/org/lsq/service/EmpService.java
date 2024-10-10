package org.lsq.service;

import org.lsq.annotation.Log;
import org.lsq.pojo.Emp;
import org.lsq.pojo.PageBean;

import java.time.LocalDate;
import java.util.List;

public interface EmpService {
    public PageBean page(Integer page, Integer pageSize, String name, Short gender, LocalDate begin, LocalDate end);


    void deleteEmp(List<String> ids);


    void save(Emp emp);


    Emp query(Integer id);


    void update(Emp emp);

    Emp login(Emp emp);
}
