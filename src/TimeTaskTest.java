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
import java.util.TimerTask;

/**
 * @author yinjimin
 * @Description: jdk自带定时任务测试
 * @date 2018年04月13日
 */
public class TimeTaskTest extends TimerTask {

    @Override
    public void run() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println("当前任务执行时间为: " + simpleDateFormat.format(scheduledExecutionTime()));
    }
}
