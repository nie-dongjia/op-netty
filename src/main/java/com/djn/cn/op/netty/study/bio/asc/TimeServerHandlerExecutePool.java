package com.djn.cn.op.netty.study.bio.asc;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * <b>类   名：</b>com.djn.cn.op.netty.study.bio.asc.TimeServerHandlerExecutePool<br/>
 * <b>类描述：</b>TimeServerHandlerExecutePool  线程池 <br/>
 * <b>创建人：</b>op.nie-dongjia<br/>
 * <b>创建时间：</b>2020/2/1 19:36<br/>
 * <b>修改人：</b>op.nie-dongjia<br/>
 * <b>修改时间：</b>2020/2/1 19:36<br/>
 * <b>修改备注：</b><br/>
 *
 * @version 1.0<br       />
 */
public class TimeServerHandlerExecutePool {
    private ExecutorService executorService;

    public TimeServerHandlerExecutePool(int maxPoolSize, int queueSize) {
        executorService = new ThreadPoolExecutor(Runtime.getRuntime().availableProcessors(),
                maxPoolSize, 120L, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(queueSize));

    }
    public void execute(Runnable task) {
        executorService.execute(task);
    }
}
