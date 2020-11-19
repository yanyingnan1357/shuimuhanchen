package thread;

import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.WorkHandler;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.util.DaemonThreadFactory;

/**
 * 每10ms向disruptor中插入一个元素，消费者读取数据，并打印
 *
 * 不错的文章：https://www.cnblogs.com/pku-liuqiang/p/8544700.html
 */
public class DisruptorMultiConsumerTest {
	public static void main(String[] args) throws Exception {
		// 队列中的元素
		class Element {

			private int value;

			public int get(){
				return value;
			}

			public void set(int value){
				this.value= value;
			}

		}

		// 处理Event的handler
		WorkHandler<Element> handler = element -> System.out.println("Element: " + element.get());

		// 指定RingBuffer的大小
		int bufferSize = 1024;

		// 创建disruptor，采用单生产者模式
		Disruptor<Element> disruptor = new Disruptor<>(Element::new, bufferSize, DaemonThreadFactory.INSTANCE);

		// 设置EventHandler
		disruptor.handleEventsWithWorkerPool(handler, handler, handler);

		long time1 = System.currentTimeMillis();

		// 启动disruptor的线程
		disruptor.start();

		RingBuffer<Element> ringBuffer = disruptor.getRingBuffer();

		for (int l = 0; l<1000; l++)
		{
			// 获取下一个可用位置的下标
			long sequence = ringBuffer.next();
			try {
				// 返回可用位置的元素
				Element event = ringBuffer.get(sequence);
				// 设置该位置元素的值
				event.set(l);
			}
			finally {
				ringBuffer.publish(sequence);
			}
			//每10ms向disruptor中插入一个元素
//			Thread.sleep(10);
		}

		long time2 = System.currentTimeMillis();

		System.out.println(time2 - time1);

		Thread.sleep(1000);
		disruptor.shutdown();
	}
}
