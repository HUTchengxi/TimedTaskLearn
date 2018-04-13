/*
 * Copyright (C) 2011-2013 ShenZhen iBoxpay Information Technology Co. Ltd.
 *
 * All right reserved.
 *
 * This software is the confidential and proprietary information of iBoxPay Company of China.
 * ("Confidential Information").
 * You shall not disclose such Confidential Information and shall use it only
 * in accordance with the terms of the contract agreement you entered into with iBoxpay inc.
 *
 *
 */

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author yinjimin
 * @Description: Quartz的简单实例代码
 * @date 2018年04月13日
 */
public class QuartzTest {

    public static void main(String[] args) throws SchedulerException {

        //先打印当前时间
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date now = new Date();
        System.out.println("主线程打印当前时间为: " + simpleDateFormat.format(now));

        //创建一个JobDetail实例，绑定自定义的Job
        JobDetail jobDetail = JobBuilder.newJob(HelloJob.class)
                .withIdentity("helloName", "helloGroup").build();

        //创建一个SimpleTrigger触发器
        SimpleTrigger trigger = (SimpleTrigger) TriggerBuilder
                .newTrigger().withIdentity("helloTrigger", "helloGroup")
                .startNow().withSchedule(SimpleScheduleBuilder.simpleSchedule()
                .withIntervalInSeconds(2).repeatForever()).build();

        //创建一个schedule，绑定触发器和JobDetail
        SchedulerFactory schedulerFactory = new StdSchedulerFactory();
        Scheduler scheduler = schedulerFactory.getScheduler();
        scheduler.start();
        scheduler.scheduleJob(jobDetail, trigger);
    }
}
