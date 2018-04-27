package org.angelica.core.log;

import java.util.TimerTask;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 日志管理器，开启线程池异步记录日志，不影响业务方法的执行速度
 */
public class LogManager {

    //日志记录操作延时
    private final int OPERATE_DELAY_TIME = 10;

    //异步操作记录日志的线程池
    private ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(10);

    private LogManager() {
    }

    public static LogManager logManager = new LogManager();

    public static LogManager getInstance() {
        return logManager;
    }

    public void executeLog(TimerTask task) {
        executor.schedule(task, OPERATE_DELAY_TIME, TimeUnit.MILLISECONDS);
    }
}
