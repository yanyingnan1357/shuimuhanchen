package cn.yyn.consumer;

import cn.yyn.enums.EnumMessageType;
import cn.yyn.model.dto.BaseKafkaMessage;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class defaultConsumer {

    private static Logger LOG = LoggerFactory.getLogger(defaultConsumer.class);

    @KafkaListener(topics = "${spring.kafka.producer.topic.default}")
    public void handlerMessage(String msg) {

        try {
            BaseKafkaMessage baseKafkaMessage = JSONObject.parseObject(msg, BaseKafkaMessage.class);
            if(baseKafkaMessage == null
                    || !EnumMessageType.SELF_MSG.getType().equals(baseKafkaMessage.getMsgType())
                    || baseKafkaMessage.getData() == null){
                return;
            }
            String msgData = (String)baseKafkaMessage.getData();
            System.out.println(msgData);
            LOG.info("消费消息:msg={}", msg);
        } catch (Exception e) {
            LOG.error("epx handle filed.{}", e);
        }
    }
}

//使用@KafkaListener这个注解并不局限于这个监听容器是单条数据消费还是批量消费，
//区分单数据还是多数据消费只需要配置一下注解的containerFactory属性即可，
//先看下这个监听方法都能接收写什么参数：

//    public void listen1(String data)
//
//    public void listen2(ConsumerRecord<K,V> data)
//
//    public void listen3(ConsumerRecord<K,V> data, Acknowledgment acknowledgment)
//
//    public void listen4(ConsumerRecord<K,V> data, Acknowledgment acknowledgment, Consumer<K,V> consumer)
//
//    public void listen5(List<String> data)
//
//    public void listen6(List<ConsumerRecord<K,V>> data)
//
//    public void listen7(List<ConsumerRecord<K,V>> data, Acknowledgment acknowledgment)
//
//    public void listen8(List<ConsumerRecord<K,V>> data, Acknowledgment acknowledgment, Consumer<K,V> consumer)
