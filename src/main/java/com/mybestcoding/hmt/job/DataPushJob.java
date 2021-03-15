package com.mybestcoding.hmt.job;

import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author: lixinkai
 * @description: 数据推送任务
 * @date: 2021/3/11 17:40
 * @GitHub: https://github.com/kk-lixinkai
 * @Gitee: https://gitee.com/bestbug
 * @version: 1.0
 */
@Slf4j
public class DataPushJob extends QuartzJobBean {

    private final AtomicInteger counts = new AtomicInteger();

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        log.info("【定时任务】执行了第【{}】次", counts.incrementAndGet());
    }
}
