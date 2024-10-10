package org.lsq.controller;


import lombok.extern.slf4j.Slf4j;
import org.lsq.pojo.Emp;
import org.lsq.pojo.Result;
import org.lsq.service.EmpService;
import org.lsq.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@Slf4j
@RequestMapping("/login")
public class LoginController {
    @Autowired
    private EmpService empService;

    @PostMapping
    public Result login(@RequestBody Emp emp) {
        Emp e = empService.login(emp);

//        登录成功
        if (e != null) {
            Map<String, Object> claim = new HashMap<String, Object>();
            claim.put("id", e.getId());
            claim.put("username", e.getName());
            claim.put("password", e.getPassword());

            String jwt = JwtUtils.generateJwt(claim);
            return Result.success(jwt);
        }

//        登录失败
        return Result.error("用户名或者密码有误！");
    }

}
