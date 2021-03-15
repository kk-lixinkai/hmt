package com.mybestcoding.hmt.config;

import com.mybestcoding.hmt.job.DataPushJob;
import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author: lixinkai
 * @description: Quartz 定时任务配置
 * @date: 2021/3/11 20:21
 * @GitHub: https://github.com/kk-lixinkai
 * @Gitee: https://gitee.com/bestbug
 * @version: 1.0
 */
@Configuration
public class QuartzScheduleConfig {

    public static class DataPushConfig {

        /**
         * 工作负责名称
         */
        private static final String JOB_NAME = "dataPushJob";

        /**
         * 触发器名称
         */
        private static final String TRIGGER_NAME = "dataPushJobTrigger";


        @Bean
        public JobDetail dataPushJob() {
            return JobBuilder.newJob(DataPushJob.class)
                    .withIdentity(JOB_NAME)
                    .storeDurably()
                    .build();
        }

        @Bean
        public Trigger dataPushJobTrigger() {
            SimpleScheduleBuilder scheduleBuilder = SimpleScheduleBuilder.simpleSchedule()
                    .withIntervalInSeconds(5)
                    .repeatForever();
            return TriggerBuilder.newTrigger()
                    .forJob(JOB_NAME)
                    .withIdentity(TRIGGER_NAME)
                    .withSchedule(scheduleBuilder)
                    .build();
        }
    }
}
