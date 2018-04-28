package com.example.demo.controller;

import com.example.demo.common.util.GsonUtil;
import com.example.demo.domain.DemoDO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/10/13.
 */
@RequestMapping("/common/VM")
@Controller
public class VMDemoController {

    @RequestMapping("/{name}")
    public String index(Model model, @PathVariable("name") String name) {
        DemoDO single = new DemoDO();
        single.setId(1L);
        single.setName("a");
        List<DemoDO> people = new ArrayList<DemoDO>();
        DemoDO p1 = new DemoDO();
        p1.setId(11L);
        p1.setName("aa");
        DemoDO p2 = new DemoDO();
        p2.setId(22L);
        p2.setName("bb");
        DemoDO p3 = new DemoDO();
        p3.setId(33L);
        p3.setName("cc");
        people.add(p1);
        people.add(p2);
        people.add(p3);
//        RedisUtil.set("aa","123");
//        System.out.println(RedisUtil.get("aa"));
        people.stream().filter(n -> n.getId()==11L).forEach(n -> System.out.println(GsonUtil.toJson(n)));
        System.out.println(11);
        model.addAttribute("singlePerson", single);
        model.addAttribute("people", people);
        return "/"+name;
    }

    public static void main(String[] args) {
        List<DemoDO> people = new ArrayList<DemoDO>();
        DemoDO p1 = new DemoDO();
        p1.setId(11L);
        p1.setName("aa");
        DemoDO p2 = new DemoDO();
        p2.setId(22L);
        p2.setName("bb");
        DemoDO p3 = new DemoDO();
        p3.setId(33L);
        p3.setName("cc");
        people.add(p1);
        people.add(p2);
        people.add(p3);
//        people.stream().filter(n -> n.getName().equals("aa")).forEach(n -> System.out.println(n.getName()));
//        people.forEach(n -> {
//            System.out.println(n.getName());
//        });
    }

}

