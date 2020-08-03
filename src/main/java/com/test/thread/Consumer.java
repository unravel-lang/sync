package com.test.thread;

import com.test.business.DealBusi;

import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @类名 Consumer
 * @说明:
 * @作者 黄俊斌
 * @日期 2020/2/1
 **/
public class Consumer implements Runnable {

    private List data;
    private DealBusi dealBusi;
    private LinkedBlockingQueue<Runnable> consumer;


    public Consumer(DealBusi dealBusi, LinkedBlockingQueue<Runnable> consumer) {
        this.dealBusi = dealBusi;
        this.consumer = consumer;
    }

    public void run() {

        try {
            dealBusi.deal(data);
        } finally {
            try {
                consumer.put(this);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

    public void setData(List data) {
        this.data = data;
    }
}
