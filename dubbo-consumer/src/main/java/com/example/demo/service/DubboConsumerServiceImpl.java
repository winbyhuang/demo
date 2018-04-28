package com.example.demo.service;

import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Component;

/**
 * Created by winby on 2017/5/9.
 */
@Component
public class DubboConsumerServiceImpl implements DubboConsumerService {
    @Reference(version = "1.0.0")
    DubboProvicerService dubboProvicerService;
    @Override
    public String getName(String name) {
        return dubboProvicerService.getName(name);
    }
}
