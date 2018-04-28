package com.example.demo.kafka;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.component.file.GenericFileMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author Winby
 * @date 2017/9/5  17:02
 * @update
 * @since v1.0.0
 */
@Component
public class FtpProcessor implements Processor {
    private static Logger logger = LoggerFactory.getLogger(FtpProcessor.class);

    // 消息队列
    private BlockingQueue<Object> childActivityDataBlockingQueue = new LinkedBlockingQueue<>(100);

    public BlockingQueue<Object> getChildActivityDataBlockingQueue() {
        return childActivityDataBlockingQueue;
    }

    @Override
    public void process(Exchange exchange) {
        GenericFileMessage<RandomAccessFile> inFileMessage = (GenericFileMessage<RandomAccessFile>) exchange.getIn();
        String fileName = inFileMessage.getGenericFile().getFileName();// 文件名
        String splitTag = File.separator;// 系统文件分隔符
        logger.info("download file name is : " + splitTag + fileName);// 文件的绝对路径

        InputStream body = exchange.getIn().getBody(InputStream.class);
        BufferedReader in = null;
        try {
            // 按IOP规范，编码GBK
            in = new BufferedReader(new InputStreamReader(body, "GBK"));

            String str1;
//            while ((str1 = in.readLine()) != null) {
//                logger.info("message is : " + str1);
//                if (!StringUtils.isBlank(str1)) {
//                    String[] items = str1.split(String.valueOf((char) 0x80));
//                    if (null == items[2] || "".equals(items[2])) {
//                        // province字段为空, 无效数据
//                        continue;
//                    }
//                    // 将39 index后的指标数据用 | 拼接.
//                    String str2 = String.join("|", Arrays.copyOfRange(items, 39, items.length));
//
//                    DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
////                    ChildActivityData childActivityData = new ChildActivityData();
////                    childActivityData.setStatisticalTime(dateFormat.parse(items[1]));
////                    childActivityData.setProvince(items[2]);
////                    childActivityData.setCity(items[3]);
////                    childActivityData.setPolicyCode(items[11]);
////                    childActivityData.setChildActivityCode(items[4]);
////                    childActivityData.setChildActivityName(items[5]);
////                    childActivityData.setStartTime(dateFormat.parse(items[6]));
////                    childActivityData.setEndTime(dateFormat.parse(items[7]));
////                    childActivityData.setJsonData(str2);
//                    childActivityDataBlockingQueue.add(childActivityData);
//
//                }
//            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}