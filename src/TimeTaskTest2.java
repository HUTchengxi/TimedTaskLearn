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
import java.util.TimerTask;

/**
 * @author yinjimin
 * @Description: schedule和scheduleAtFixedRate的区别的展示
 * @date 2018年04月13日
 */
public class TimeTaskTest2 extends TimerTask {

    public static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public TimeTaskTest2(){
            System.out.println("time start:" + simpleDateFormat.format(new Date()));
    }

    @Override
    public void run() {
        try{
            System.out.println("next task -> " + simpleDateFormat.format(scheduledExecutionTime()));
            Thread.sleep(3000);
            System.out.println("当前任务执行时间为: " + simpleDateFormat.format(new Date()));
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
