package za.co.sfh.aws.sqs.server.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate;
import org.springframework.cloud.aws.messaging.listener.SqsMessageDeletionPolicy;
import org.springframework.cloud.aws.messaging.listener.annotation.SqsListener;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/sqs")
public class SQSController {

    @Autowired
    private QueueMessagingTemplate queueMessagingTemplate;

    @Value("${cloud.aws.sqs.url}")
    private String sqsEndPoint;

    @GetMapping
    public void sendMessage() {
        queueMessagingTemplate.send(sqsEndPoint, MessageBuilder.withPayload("hello from Spring Boot").build());
    }

    @SqsListener(value = "aws-test-sqs", deletionPolicy = SqsMessageDeletionPolicy.ON_SUCCESS)
    public void getMessage(String message) throws Exception {
        log.info("Message from SQS Queue - "+message);
        //throw new Exception("Testing error handling");
    }
}