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

import org.apache.log4j.Logger;
import org.apache.log4j.spi.LoggerFactory;
import sun.rmi.runtime.Log;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;

/**
 * @author yinjimin
 * @Description: jdk自带定时任务测试主类
 * @date 2018年04月13日
 */
public class Main {

    public static void main(String[] args){

        Timer timer = new Timer();
        TimeTaskTest timeTaskTest = new TimeTaskTest();

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println("当前任务执行时间为: " + simpleDateFormat.format(new Date()));

        //定时任务的第一种方式
        //只执行一次，但是不会结束应用，会一直卡在那里
//        timer.schedule(timeTaskTest, 1000);

        //定时任务的第二种方式
//        timer.schedule(timeTaskTest, 1000, 2000);

        //定时任务的第三种方式
//        Calendar calendar = Calendar.getInstance();
//        calendar.add(Calendar.SECOND, 3);
//        timer.schedule(timeTaskTest, calendar.getTime(),1000);

        //使用定义任务的第四种方式，通过scheduleAtFixedTime方法来实现
//        timer.scheduleAtFixedRate(timeTaskTest, 1000, 2000);

        //使用定时任务的第五种方式
//        Calendar calendar = Calendar.getInstance();
//        calendar.add(Calendar.SECOND, 3);
//        timer.scheduleAtFixedRate(timeTaskTest, calendar.getTime(), 1000);

//        通过timer的schedule方法和scheduleAtFixedRate方法都可以来实现定时任务，
//        他们两者还是有区别的
//        Calendar calendar = Calendar.getInstance();
//        calendar.add(Calendar.SECOND, -3);
//        timer.schedule(timeTaskTest, calendar.getTime(), 1000);
//        timer.scheduleAtFixedRate(timeTaskTest, calendar.getTime(), 2000 );

        Calendar calendar = Calendar.getInstance();
        TimeTaskTest2 timeTaskTest2 = new TimeTaskTest2();
//        timer.schedule(timeTaskTest2, calendar.getTime(), 2000);
        timer.scheduleAtFixedRate(timeTaskTest2, calendar.getTime(), 2000);
    }
}
