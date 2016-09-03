package com.dish.dgc.pong.service;

import com.dish.dgc.pong.model.QueueEntry;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

@Service
public class LinkedListQueueService implements QueueService {
    private List<QueueEntry> queue;

    public LinkedListQueueService() {
        this.queue = new ArrayList<>();
    }

    @Override
    public void addToQueue(QueueEntry entry) {
        queue.add(entry);
    }

    @Override
    public Queue<QueueEntry> getQueue() {
        return new LinkedList<>(queue);
    }

    @Override
    public int getPosition(QueueEntry entry) {
        return queue.indexOf(entry) + 1;
    }

    @Override
    public void emptyQueue() {
        queue.clear();
    }
}
