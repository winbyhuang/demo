package com.example.demo.mongo;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Winby
 * @date 2017/4/24  17:28
 * @update
 * @since v1.0.0
 */
@RestController
@RequestMapping("mongo")
public class MongoController {

    @Autowired
    private DemoInfoRepository demoInfoRepository;

    @RequestMapping("save")
    public String save(){
        DemoDO demoInfo = new DemoDO();
        demoInfo.setName("张三");
        demoInfoRepository.save(demoInfo);

        demoInfo = new DemoDO();
        demoInfo.setName("李四");
        demoInfoRepository.save(demoInfo);

        return "ok";
    }

    @RequestMapping("find")
    public List<DemoDO> find(){
        return demoInfoRepository.findAll();
    }

    @RequestMapping("findByName")
    public DemoDO findByName(){
        return demoInfoRepository.findByName("张三");
    }

}