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

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author yinjimin
 * @Description: ScheduledExecutorService定时任务测试类
 * @date 2018年04月13日
 */
public class ScheduledExecutorServiceTest {

    public static void main(String[] args){

        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(2);
        TimeTaskTest timeTaskTest = new TimeTaskTest();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date now = new Date();
        System.out.println("主线程记录当前时间为：" + simpleDateFormat.format(now));
        //延迟一秒后执行
        //TODO：mark一下，这里的第二个参数以第三个参数为单位，之前写成了3000，等了一万年
        scheduledExecutorService.schedule(timeTaskTest, 3, TimeUnit.SECONDS);


    }
}
