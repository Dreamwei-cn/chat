package com.chat.auth.utils;

public class SnowflakeIdGenerator {
    // 41 bits of timestamp (the maximum that can fit into a signed long timestamp is 41 bits)
    private static final int TIMESTAMP_BITS = 41;

    // 10 bits of worker id
    private static final int WORKER_ID_BITS = 10;

    // 12 bits of sequence number (per worker)
    private static final int SEQUENCE_BITS = 12;

    // Max values for worker id and sequence number
    private static final int MAX_WORKER_ID = (int) Math.pow(2, WORKER_ID_BITS) - 1;
    private static final int MAX_SEQUENCE = (int) Math.pow(2, SEQUENCE_BITS) - 1;

    // Epoch of the snowflake's timestamps (2021-01-01 00:00:00 in this example)
    private static final long EPOCH = 1609430400000L;

    // Worker id (up to 1024 workers)
    private final int workerId;

    // Last timestamp in milliseconds
    private long lastTimestamp = -1L;

    // Sequence number for the current timestamp (per worker)
    private int sequence = 0;

    public SnowflakeIdGenerator(int workerId) {
        if (workerId < 0 || workerId > MAX_WORKER_ID) {
            throw new IllegalArgumentException("Worker id must be between 0 and " + MAX_WORKER_ID);
        }
        this.workerId = workerId;
    }

    public synchronized long nextId() {
        long currentTime = System.currentTimeMillis();
        if (currentTime < lastTimestamp) {
            throw new RuntimeException("Clock moved backwards. Refusing to generate id.");
        }
        if (currentTime == lastTimestamp) {
            sequence = (sequence + 1) & MAX_SEQUENCE;
            if (sequence == 0) {
                currentTime = waitNextMillis(currentTime);
            }
        } else {
            sequence = 0;
        }

        lastTimestamp = currentTime;

        return ((lastTimestamp - EPOCH) << (WORKER_ID_BITS + SEQUENCE_BITS)) |
                (workerId << SEQUENCE_BITS) |
                sequence;
    }

    private long waitNextMillis(long currentTime) {
        while (currentTime == lastTimestamp) {
            currentTime = System.currentTimeMillis();
        }
        return currentTime;
    }
}
