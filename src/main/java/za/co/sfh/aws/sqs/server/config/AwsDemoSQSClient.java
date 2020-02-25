package za.co.sfh.aws.sqs.server.config;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSAsync;
import com.amazonaws.services.sqs.AmazonSQSAsyncClientBuilder;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public final class AwsDemoSQSClient {

    @Value("${cloud.aws.region.static}")
    @Getter
    private String awsRegion;

    @Value("${cloud.aws.sqs.url}")
    @Getter
    private String awsSqsUrl;

    @Getter
    private AmazonSQS basicAmazonSQSClient;

    @Bean
    public QueueMessagingTemplate queueMessagingTemplate() {
        return new QueueMessagingTemplate(amazonSQSAsync());
    }

    public AmazonSQSAsync amazonSQSAsync() {
        return AmazonSQSAsyncClientBuilder
                .standard()
                .withRegion(awsRegion)
                .withCredentials(new ProfileCredentialsProvider())
                .build();
    }

    @PostConstruct
    private void init() {
        basicAmazonSQSClient = AmazonSQSClientBuilder
                .standard()
                .withRegion(awsRegion)
                .withCredentials(new ProfileCredentialsProvider())
                .build();
    }
}
