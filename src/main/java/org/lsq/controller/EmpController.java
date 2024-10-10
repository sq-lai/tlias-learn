package org.lsq.controller;

import lombok.extern.slf4j.Slf4j;
import org.lsq.pojo.Emp;
import org.lsq.pojo.PageBean;
import org.lsq.pojo.Result;
import org.lsq.service.EmpService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/emps")
@Slf4j
public class EmpController {


    @Autowired
    private EmpService empService;

    @GetMapping

    public Result page(@RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "10") Integer pageSize,
                       String name, Short gender, @DateTimeFormat(pattern = "YYYY-MM-DD") LocalDate begin,
                       @DateTimeFormat(pattern = "YYYY-MM-DD") LocalDate end) {
        log.info("分页查询开始");
        PageBean pb = empService.page(page,pageSize,name,gender,begin,end);
        return Result.success(pb);
    }

    @DeleteMapping("/{ids}")

    public Result delete(@PathVariable("ids") List<String> ids) {

        log.info("开启删除员工数据");
        empService.deleteEmp(ids);
        return Result.success();

    }

    @PostMapping
    public Result save(@RequestBody Emp emp) {

        log.info("添加员工");
        empService.save(emp);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result get(@PathVariable("id") Integer id) {
        Emp emp = empService.query(id);
        return Result.success(emp);
    }

    @PutMapping
    public Result update(@RequestBody Emp emp) {
        empService.update(emp);
        return Result.success();
    }
}
