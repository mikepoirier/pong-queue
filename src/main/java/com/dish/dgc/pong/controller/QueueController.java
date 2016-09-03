package com.dish.dgc.pong.controller;

import com.dish.dgc.pong.model.QueueEntry;
import com.dish.dgc.pong.model.QueueResponse;
import com.dish.dgc.pong.service.QueueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedList;

@RestController
public class QueueController {

    private QueueService queueService;

    @Autowired
    public QueueController(QueueService queueService) {
        this.queueService = queueService;
    }

    @RequestMapping("/pong/queue")
    public ResponseEntity<QueueResponse> getPongQueue() {
        return ResponseEntity.ok(new QueueResponse(-1, queueService.getQueue()));
    }

    @RequestMapping(value = "/pong/queue", method = RequestMethod.POST)
    public ResponseEntity<QueueResponse> addToQueue(@RequestBody QueueEntry body) {
        queueService.addToQueue(body);
        return ResponseEntity.ok(new QueueResponse(queueService.getPosition(body), queueService.getQueue()));
    }

    @RequestMapping(value = "/pong/queue", method = RequestMethod.DELETE)
    public ResponseEntity deleteQueue() {
        queueService.emptyQueue();
        return ResponseEntity.ok(null);
    }
}
