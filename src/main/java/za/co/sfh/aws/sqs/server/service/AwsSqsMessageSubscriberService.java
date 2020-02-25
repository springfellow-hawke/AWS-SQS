package za.co.sfh.aws.sqs.server.service;

import org.springframework.stereotype.Service;
import za.co.sfh.aws.sqs.server.config.AwsDemoSQSClient;

@Service
public class AwsSqsMessageSubscriberService {

    private AwsDemoSQSClient awsDemoSQSClient;

    public AwsSqsMessageSubscriberService(AwsDemoSQSClient awsDemoSQSClient) {
        this.awsDemoSQSClient = awsDemoSQSClient;
    }


}
