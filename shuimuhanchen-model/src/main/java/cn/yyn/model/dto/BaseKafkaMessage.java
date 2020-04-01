package cn.yyn.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BaseKafkaMessage<T> implements Serializable {
    private static final long serialVersionUID = 34481913552019333L;

    private String msgType;

    private T data;
}
