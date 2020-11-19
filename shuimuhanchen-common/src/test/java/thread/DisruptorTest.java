package thread;

import com.lmax.disruptor.EventHandler;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.util.DaemonThreadFactory;

/**
 * 每10ms向disruptor中插入一个元素，消费者读取数据，并打印
 */
public class DisruptorTest {
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
        EventHandler<Element> handler = (element, sequence, endOfBatch) -> System.out.println("Element: " + element.get());

        // 指定RingBuffer的大小
        int bufferSize = 1024;

        // 创建disruptor，采用单生产者模式
        Disruptor<Element> disruptor = new Disruptor<>(Element::new, bufferSize, DaemonThreadFactory.INSTANCE);

        // 设置EventHandler
        disruptor.handleEventsWith(handler);

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
//            Thread.sleep(10);
        }

        long time2 = System.currentTimeMillis();

        System.out.println(time2 - time1);

        Thread.sleep(1000);
        disruptor.shutdown();
    }
}