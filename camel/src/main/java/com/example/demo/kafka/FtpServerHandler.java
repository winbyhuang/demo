package com.example.demo.kafka;

import org.apache.camel.main.Main;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @author Winby
 * @date 2017/9/5  16:59
 * @update
 * @since v1.0.0
 */
@Component
public class FtpServerHandler {
    private static final Logger log = LoggerFactory.getLogger(FtpServerHandler.class);

    @Value("${pop.synchronization:true}")
    private boolean popSynchronization;

    @Autowired
    private FtpServerRouteBuilder ftpServerRouteBuilder;

    @Autowired
    private FtpProcessor fileProcessor;

//    @Autowired
//    private ChildActivityDataService childActivityDataService;



    /**
     *
     */
    @PostConstruct
    public void init() {
        if (popSynchronization) {
            Main main = new Main();
            main.addRouteBuilder(ftpServerRouteBuilder);
            new Thread(() -> {
                try {
                    main.run();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }).start();
        }

        new Thread(() -> {
            while (true) {
                if (Thread.interrupted()) {
                    log.warn("Interrupted thread exit!");
                    return;
                }
                log.info("执行用户同步操作。");
                try {
//                        childActivityDataService.save(locationFileProcessor.getChildActivityDataBlockingQueue().take());
                    Thread.sleep(5000L);
                } catch (InterruptedException ie) {
                    log.warn("InterruptedException thread exit!", ie);
                    return;
                } catch (Exception e) {
                    log.warn("Exception when save childActivityData!", e);
                }
            }
        }).start();
    }
}