package com.example.demo.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author Winby
 * @date 2017/4/24  17:28
 * @update
 * @since v1.0.0
 */
public interface DemoInfoRepository  extends MongoRepository<DemoDO,String> {
    DemoDO findByName(String name);
}
