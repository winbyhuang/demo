package com.example.demo.common.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author Administrator
 *
 */
@Service
public class ScheduledThreadPoolUtil {
    
    private static Logger log = LoggerFactory.getLogger(ScheduledThreadPoolUtil.class);

    private static ScheduledThreadPoolExecutor executor = null;

    /**
     * 核心池的大小 运行线程的最大值  当线程池中的线程数目达到corePoolSize后，就会把多余的任务放到缓存队列当中；
     */
    private static int corePoolSize = 10;

    /**
     * 创建线程最大值
     */
    private static int maximumPoolSize = 15;

    /**
     * 线程没有执行任务时 被保留的最长时间 超过这个时间就会被销毁 直到线程数等于
     */
    private static long keepAliveTime = 200;
                                      // corePoolSize

    /**
     * 参数keepAliveTime的时间单位，有7种取值，在TimeUnit类中有7种静态属性：
     * 
     * TimeUnit.DAYS; 天
     * 
     * TimeUnit.HOURS; 小时
     * 
     * TimeUnit.MINUTES; 分钟
     * 
     * TimeUnit.SECONDS; 秒
     * 
     * TimeUnit.MILLISECONDS; 毫秒
     * 
     * TimeUnit.MICROSECONDS; 微妙
     * 
     * TimeUnit.NANOSECONDS; 纳秒
     ***/

    private TimeUnit unit = TimeUnit.MILLISECONDS;

    /**
     * 
     * 用来储存等待中的任务的容器
     * 
     * 
     * 
     * 几种选择：
     * 
     * ArrayBlockingQueue;
     * 
     * LinkedBlockingQueue;
     * 
     * SynchronousQueue;
     * 
     * 
     * 
     */

    private LinkedBlockingQueue<Runnable> workQueue = new LinkedBlockingQueue<>();
    
    /**
     * 
     */
    @PostConstruct
    public void init() {
        executor =  new ScheduledThreadPoolExecutor(corePoolSize);

        log.info("线程池初始化成功");
    }


    /**
     * 
     * 线程池获取方法
     * 
     * @return
     * 
     */

    public static ScheduledThreadPoolExecutor getExecutor() {

        return executor;

    }

    /**
     * 
     * @param t
     */
    public void execute(Runnable t) {

        executor.execute(t);

    }

    /**
     * 
     * 返回 Future
     * 
     * Future.get()可获得返回结果
     * 
     * @return
     * 
     */

    @SuppressWarnings({ "unchecked", "rawtypes" })

    public Future<?> submit(Callable t) {

        return getExecutor().submit(t);

    }

    /**
     * 
     * 销毁线程池
     * 
     */
    @PreDestroy
    public void shutdown() {
        log.info("线程池终止。");
        getExecutor().shutdown();

    }
}
