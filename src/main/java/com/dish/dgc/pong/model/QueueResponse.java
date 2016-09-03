package com.dish.dgc.pong.model;

import java.util.Queue;

public class QueueResponse {
    private Integer position;
    private Queue<QueueEntry> queue;

    public QueueResponse(Integer position, Queue<QueueEntry> queue) {
        this.position = position;
        this.queue = queue;
    }

    public Integer getPosition() {
        return position;
    }

    public Queue<QueueEntry> getQueue() {
        return queue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        QueueResponse that = (QueueResponse) o;

        if (position != that.position) return false;
        return queue != null ? queue.equals(that.queue) : that.queue == null;

    }

    @Override
    public int hashCode() {
        Integer result = position;
        result = 31 * result + (queue != null ? queue.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "QueueResponse{" +
                "position=" + position +
                ", queue=" + queue +
                '}';
    }


}
