package org.lsq.controller;


import lombok.extern.slf4j.Slf4j;
import org.lsq.pojo.Dept;
import org.lsq.pojo.Result;
import org.lsq.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/depts")
public class DeptController {

    @Autowired
    private DeptService deptService;

    @GetMapping
    public Result list(){

        List<Dept> depts = deptService.findAll();
        log.info("成功返回数据！");
        return Result.success(depts);

    }

    @DeleteMapping("/{id}")

    public Result delete(@PathVariable Integer id) throws Exception {

        log.info("根据id来删除数据！");
        deptService.delete(id);
        return Result.success();
    }

    @PostMapping
    public Result save(@RequestBody Dept dept){
        log.info("保存信息！");
        deptService.save(dept);
        return Result.success();
    }



}
