package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by Administrator on 2016/10/13.
 */
@RequestMapping("/dubbo/")
@RestController
public class DubboController {
    @Autowired
    DubboConsumerService dubboConsumerService;

    @RequestMapping(value = "/b", method = RequestMethod.GET)
    public Object sessions (HttpSession session,HttpServletRequest request){
        return dubboConsumerService.getName("succ");
    }

}

