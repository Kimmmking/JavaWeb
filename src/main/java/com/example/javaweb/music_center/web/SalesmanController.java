package com.example.javaweb.music_center.web;

import com.example.javaweb.music_center.dao.History5DAO;
import com.example.javaweb.music_center.pojo.History5;
import com.example.javaweb.music_center.pojo.Salesman;
import com.example.javaweb.music_center.service.SalesmanService;
import com.example.javaweb.music_center.util.Page4Navigator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
public class SalesmanController {
    @Autowired
    SalesmanService salesmanService;
    @Autowired
    History5DAO history5DAO;

    private String[] operation = {"add","delete","update"};

    @GetMapping("/salesman")
    public Page4Navigator<Salesman> list(@RequestParam(value = "start", defaultValue = "0") int start, @RequestParam(value = "size", defaultValue = "5") int size) throws Exception {
        start = start < 0 ? 0 : start;
        return salesmanService.list(start,size,5);
    }

    @PostMapping("/salesman")
    public Object add(@RequestBody Salesman salesmanParam) throws Exception {
        Salesman salesman = new Salesman(salesmanParam.getName(), salesmanParam.getPassword());
        salesmanService.add(salesman);
        History5 history5 = new History5();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        String time = df.format(new Date());
        history5.setOperate(operation[0]);
        history5.setSname(salesman.getName());
        history5.setTime(time);
        return salesman;
    }

    @DeleteMapping("/salesman/{id}")
    public String delete(@PathVariable("id") int id)  throws Exception {
        if (id == 1){
            return null;
        }
        History5 history5 = new History5();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        String time = df.format(new Date());
        history5.setOperate(operation[1]);
        history5.setSname(salesmanService.get(id).getName());
        history5.setTime(time);
        salesmanService.delete(id);
        return null;
    }

    @GetMapping("/salesman/{id}")
    public Salesman get(@PathVariable("id") int id) throws Exception {
        return salesmanService.get(id);
    }

    @PostMapping("/salesman/{id}")
    public Object update(@RequestBody Salesman bean) throws Exception {
        History5 history5 = new History5();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        String time = df.format(new Date());
        history5.setOperate(operation[2]);
        history5.setSname(bean.getName());
        history5.setTime(time);
        salesmanService.update(bean);
        return bean;
    }
}
