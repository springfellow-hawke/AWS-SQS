package za.co.sfh.aws.sqs.server.service;

import com.amazonaws.services.sqs.model.SendMessageResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import za.co.sfh.aws.sqs.server.config.AwsDemoSQSClient;

@Slf4j
@Service
public class AwsSqsMessagePublisherService {

    private AwsDemoSQSClient awsDemoSQSClient;
    private int i = 0;

    public AwsSqsMessagePublisherService(AwsDemoSQSClient awsDemoSQSClient) {
        this.awsDemoSQSClient = awsDemoSQSClient;
    }

    @Scheduled(fixedRate = 10000l)
    private void sendMessage() {
        SendMessageResult sendMessageResult
                = awsDemoSQSClient.getBasicAmazonSQSClient().sendMessage(awsDemoSQSClient.getAwsSqsUrl(), "Hello - " + i);
        i++;

        log.info("Message : {}", sendMessageResult.getMessageId());
    }
}
