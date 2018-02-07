package com.vijay.ihc.demo.utils;

import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.*;


public class IdGenerator {

    private final static Logger logger = LoggerFactory.getLogger(IdGenerator.class);

    private final long workerId;

    // 时间起始标记点，作为基准，一般取系统的最近时间
    private final long epoch = 1403854494756L;
    // 机器标识位数
    private final long workerIdBits = 10L;
    // 机器ID最大值: 1023
    private final long maxWorkerId = -1L ^ -1L << this.workerIdBits;

    private long sequence = 0L;                   // 0，并发控制
    // 毫秒内自增位
    private final long sequenceBits = 12L;
    // 机器ID偏左移12位
    private final long workerIdShift = this.sequenceBits;
    // 数据中心ID左移22位
    private final long timestampLeftShift = this.sequenceBits + this.workerIdBits;// 22

    private final long sequenceMask = -1L ^ -1L << this.sequenceBits;                 // 4095,111111111111,12位

    private long lastTimestamp = -1L;

    private IdGenerator(long workerId) {
        if (workerId > this.maxWorkerId || workerId < 0) {
            throw new IllegalArgumentException(String.format("worker Id can't be greater than %d or less than 0", this.maxWorkerId));
        }
        this.workerId = workerId;
    }

    public synchronized long nextId() throws Exception {
        long timestamp = this.timeGen();
        if (this.lastTimestamp == timestamp) { // 如果上一个timestamp与新产生的相等，则sequence加一(0-4095循环); 对新的timestamp，sequence从0开始
            this.sequence = this.sequence + 1 & this.sequenceMask;
            if (this.sequence == 0) {
                timestamp = this.tilNextMillis(this.lastTimestamp);// 重新生成timestamp
            }
        } else {
            this.sequence = 0;
        }

        if (timestamp < this.lastTimestamp) {
            logger.error(String.format("clock moved backwards.Refusing to generate id for %d milliseconds", (this.lastTimestamp - timestamp)));
            throw new Exception(String.format("clock moved backwards.Refusing to generate id for %d milliseconds", (this.lastTimestamp - timestamp)));
        }

        this.lastTimestamp = timestamp;
        return timestamp - this.epoch << this.timestampLeftShift | this.workerId << this.workerIdShift | this.sequence;
    }

    private static IdGenerator flowIdWorker = new IdGenerator(1);
    public static IdGenerator getFlowIdWorkerInstance() {
        return flowIdWorker;
    }



    /**
     * 等待下一个毫秒的到来, 保证返回的毫秒数在参数lastTimestamp之后
     */
    private long tilNextMillis(long lastTimestamp) {
        long timestamp = this.timeGen();
        while (timestamp <= lastTimestamp) {
            timestamp = this.timeGen();
        }
        return timestamp;
    }

    /**
     * 获得系统当前毫秒数
     */
    private static long timeGen() {
        return System.currentTimeMillis();
    }


    public static Set<Long> idSet = new HashSet<>();

    public static void main(String[] args) throws Exception {
        System.out.println(timeGen());

        IdGenerator idWorker = IdGenerator.getFlowIdWorkerInstance();
        // System.out.println(Long.toBinaryString(idWorker.nextId()));
        ExecutorService pool = Executors.newCachedThreadPool();

       /* for (int i = 0; i < 1000000; i++) {

            pool.execute(()->{
                for (int j = 0; j < 1000000; j++) {
                    try {
                        System.out.println(idWorker.nextId());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        }*/

        ObjectIdGenerators.UUIDGenerator uuidGenerator = new ObjectIdGenerators.UUIDGenerator();

        UUID uuid = uuidGenerator.generateId(IdGenerator.class);

        System.out.println(uuid.toString());

        System.out.println(UUID.randomUUID());
        pool.shutdown();
        test();

    }



    public static void test(){
        final IdGenerator idWorker = IdGenerator.getFlowIdWorkerInstance();
        final CyclicBarrier cdl = new CyclicBarrier(100);

        for(int i = 0; i < 100000; i++){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        cdl.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (BrokenBarrierException e) {
                        e.printStackTrace();
                    }
                    try {
                        Long id = idWorker.nextId();

                        System.out.println(id);

                        if (!idSet.add(id)) {
                            System.err.println("存在重复id:" + id);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
