package org.lsq.service;

import org.lsq.annotation.Log;
import org.lsq.pojo.Dept;

import java.util.List;

public interface DeptService {

    public List<Dept> findAll();

    public void delete(Integer id) throws Exception;

   public void save(Dept dept);
}
