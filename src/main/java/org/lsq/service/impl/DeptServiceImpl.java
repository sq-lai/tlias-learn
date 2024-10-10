package org.lsq.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.lsq.annotation.Log;
import org.lsq.mapper.DeptMapper;
import org.lsq.mapper.EmpMapper;
import org.lsq.pojo.Dept;
import org.lsq.pojo.DeptLog;
import org.lsq.service.DeptLogService;
import org.lsq.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
public class DeptServiceImpl implements DeptService {


    @Autowired
    private DeptMapper deptMapper;
    @Autowired
    private EmpMapper empMapper;
    @Autowired
    private DeptLogService deptLogService;



    @Override
    @Log
    public List<Dept> findAll(){
        List<Dept> depts =  deptMapper.list();
        return depts;
    }



    @Override
    @Log
    @Transactional(rollbackFor = Exception.class)//所有异常都包括要,不加默认是运行时异常.
    public void delete(Integer id) throws Exception {
        try {
            deptMapper.delete(id);

//            //模拟：异常
//            if(true){
//                throw new Exception("出现异常了~~~");
//            }

            //    同时根据部门id删除其下的员工

            empMapper.deleteByDeptId(id);


        } finally {
            //不论是否有异常，最终都要执行的代码：记录日志
            DeptLog deptLog = new DeptLog();
            deptLog.setCreateTime(LocalDateTime.now());
            deptLog.setDescription("执行了解散部门的操作，此时解散的是"+id+"号部门");
            //调用其他业务类中的方法
            deptLogService.insert(deptLog);
        }
    }





    @Override
    @Log
    public void save(Dept dept) {
        dept.setCreateTime(LocalDateTime.now());
        dept.setUpdateTime(LocalDateTime.now());
        deptMapper.save(dept);
    }
}
