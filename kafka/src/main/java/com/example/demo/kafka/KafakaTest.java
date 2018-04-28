package com.example.demo.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by Administrator on 2016/10/13.
 */
@RequestMapping("kafka")
@RestController
public class KafakaTest {
    //    @Autowired
//    private KafkaProducerConfig kafkaProducerConfig;
    @Autowired
    private MsgProducer msgProducer;

    @RequestMapping(value = "a", method = RequestMethod.GET)
    public Object send(HttpSession session, HttpServletRequest request) {
        try {
            int i = 0;
            Long t1 = System.currentTimeMillis();
//            for (int j = 0; j < 1; j++) {
////                kafkaProducerConfig.kafkaTemplate().send("aac_stats","msg"+i);
//                i++;
//            }
            msgProducer.sendMessage("topic1", "topic--------1");
            msgProducer.sendMessage("topic2", "topic--------2");
            msgProducer.sendMessage("topic3", "topic--------3");
            System.out.print("耗时:");
            System.out.println(System.currentTimeMillis() - t1);
            System.out.println("=============================");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}