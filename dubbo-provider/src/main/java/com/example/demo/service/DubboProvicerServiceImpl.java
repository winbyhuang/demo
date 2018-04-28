package com.example.demo.service;

import com.alibaba.dubbo.config.annotation.Service;

/**
 * Created by winby on 2017/5/9.
 */
@Service(version = "1.0.0")
public class DubboProvicerServiceImpl implements DubboProvicerService {
    @Override
    public String getName(String name) {
        return name;
    }
}
