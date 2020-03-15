package com.nowcoder.community;


import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = CommunityApplication.class)
public class KafkaTest {

    @Autowired
    private KafkaTemplate kafkaTemplate;
    @Test
    public void testKafka(){
        kafkaTemplate.send("test","你好");
        kafkaTemplate.send("test","在吗");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}

@Component
class KafkaProducer{

    @Autowired
    private KafkaTemplate kafkaTemplate;

    public void send(String topic,String content){
        kafkaTemplate.send(topic,content);
    }
}
//消费者消费消息是自动去调的
@Component
class KafkaConsumer{

    @Autowired
    private KafkaTemplate kafkaTemplate;

    @KafkaListener(topics = "test")
    public void handleMessage(ConsumerRecord record){
        System.out.println(record.value());
    }
}