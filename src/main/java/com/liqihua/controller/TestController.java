package com.liqihua.controller;

import com.liqihua.dao.source1.interf.SysUserDao;
import com.liqihua.dao.source2.interf.SysProvinceDao;
import net.sf.json.JSONArray;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/testController")
public class TestController {
    @Resource
    private SysUserDao userDao;
    @Resource
    private SysProvinceDao provinceDao;


    @RequestMapping("/testDB1")
    public String testDB1(){
        System.out.println("-- testDB1 start --");
        List<Map<String,Object>> list = userDao.findListSQL("SELECT * FROM sys_user ");
        JSONArray jsonArr = JSONArray.fromObject(list);
        System.out.println(jsonArr.toString());
        System.out.println("-- testDB1 end --");
        return jsonArr.toString();
    }

    @RequestMapping("/testDB2")
    public String testDB2(){
        System.out.println("-- testDB2 start --");
        List<Map<String,Object>> list = provinceDao.findListSQL("SELECT * FROM sys_province ");
        JSONArray jsonArr = JSONArray.fromObject(list);
        System.out.println(jsonArr.toString());
        System.out.println("-- testDB2 end --");
        return jsonArr.toString();
    }





}
