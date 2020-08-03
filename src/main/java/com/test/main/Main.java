package com.test.main;

import com.test.business.DealBusi;
import com.test.business.QuerBusi;
import com.test.business.impl.DealBusiImpl;
import com.test.business.impl.QuerBusiImpl;
import com.test.thread.Consumer;
import com.test.thread.Producter;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @类名 Main
 * @说明:
 * @作者 黄俊斌
 * @日期 2020/2/1
 **/
public class Main {
    public static void main(String[] args) {
        QuerBusi querBusi = new QuerBusiImpl();
        DealBusi dealBusi = new DealBusiImpl();
        LinkedBlockingQueue<Runnable> runnables = new LinkedBlockingQueue<Runnable>(10);
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(10, 20, 5L, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>(20));

        for (int i = 0; i < 10; i++) {
            try {
                runnables.put(new Consumer(dealBusi, runnables));
            } catch (Exception e) {

            }
        }

        Producter producter = new Producter(querBusi, runnables, threadPoolExecutor);
        new Thread(producter).start();
    }
}
