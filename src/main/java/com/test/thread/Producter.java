package com.test.thread;

import com.test.business.QuerBusi;
import com.test.constant.DataStatusConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @类名 Producter
 * @说明: 生产者
 * @作者 黄俊斌
 * @日期 2020/1/28
 **/
public class Producter implements Runnable{

    private static final Logger LOGGER = LoggerFactory.getLogger(Producter.class);
    private QuerBusi querBusi;
    private LinkedBlockingQueue<Runnable> consumers;
    private ThreadPoolExecutor executor;

    public Producter(QuerBusi querBusi, LinkedBlockingQueue<Runnable> consumers, ThreadPoolExecutor executor) {
        this.querBusi = querBusi;
        this.consumers = consumers;
        this.executor = executor;
    }

    public void run() {
        while (true) {
            List list = querBusi.queryList(10);
            if (list != null && !list.isEmpty()) {
                try {
                    querBusi.modifyListStatus(list, DataStatusConstant.DEALING);
                    Consumer consumer = (Consumer) consumers.take();
                    consumer.setData(list);
                    executor.execute(consumer);

                } catch (InterruptedException e) {
                    LOGGER.error(e.getStackTrace().toString());
                    querBusi.modifyListStatus(list, DataStatusConstant.ERROR);
                    e.printStackTrace();
                }
            }else{
                try {
                    TimeUnit.SECONDS.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
