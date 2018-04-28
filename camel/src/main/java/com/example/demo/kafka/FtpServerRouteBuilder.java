package com.example.demo.kafka;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author Winby
 * @date 2017/9/5  17:00
 * @update
 * @since v1.0.0
 */
@Component
public class FtpServerRouteBuilder extends RouteBuilder {

    //    @Value("${pop.synchronization.server:ftp://localhost?delay=5000&preMove=success&move=done}")
    @Value("${ftp.synchronization.server:ftp://popidftp@172.23.21.93/pcrf/log/his?password=password&delay=5000&preMove=success&move=done}")
    private String ftpServer;

    @Value("${pop.activity.assess.localpath:/tmp/}")
    private String localTmpPath;

    @Autowired
    private FtpProcessor fileProcessor;

    @Override
    public void configure() throws Exception {
        from(ftpServer)
                .process(fileProcessor)
                .to("file:" + localTmpPath)
                .log("Downloaded file ${file:name} complete.");
    }
}
