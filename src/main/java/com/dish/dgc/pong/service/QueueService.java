package com.dish.dgc.pong.service;

import com.dish.dgc.pong.model.QueueEntry;

import java.util.Queue;

public interface QueueService {
    void addToQueue(QueueEntry entry);
    Queue<QueueEntry> getQueue();
    int getPosition(QueueEntry entry);
    void emptyQueue();
}
