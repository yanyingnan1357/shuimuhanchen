package cn.yyn.common;

import com.lmax.disruptor.BlockingWaitStrategy;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;
import com.lmax.disruptor.util.DaemonThreadFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class DisruptorUtil {

	private static int bufferSize = 4096;

	public Disruptor<Object> getSingleDisruptor() {
		return new Disruptor<>(Object::new, bufferSize, DaemonThreadFactory.INSTANCE);
	}

	public Disruptor<Object> getMultiDisruptor() {
		return new Disruptor<>(Object::new, bufferSize, DaemonThreadFactory.INSTANCE, ProducerType.MULTI, new BlockingWaitStrategy());
	}

}
