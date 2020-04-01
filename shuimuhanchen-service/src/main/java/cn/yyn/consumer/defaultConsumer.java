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
