package cn.yyn.postCache.serializer;

import cn.yyn.exception.KryoCodecException;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

/**
 * 基于kyro的序列化/反序列化工具
 */
public class KryoSerializer {

    public static byte[] serialize(Object obj) {
        Kryo kryo = KryoUtil.get();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try (Output output = new Output(outputStream)) {
            kryo.writeClassAndObject(output, obj);
            return output.toBytes();
        } catch (Exception e) {
            throw new KryoCodecException(e);
        } finally {
            if (kryo != null) {
                KryoUtil.release(kryo);
            }
        }
    }

    public static <T> T deserialize(byte[] bytes) {
        Kryo kryo = KryoUtil.get();
        try (Input input = new Input(new ByteArrayInputStream(bytes))) {
            Object obj = kryo.readClassAndObject(input);
            return (T) obj;
        } catch (Exception e) {
            throw new KryoCodecException(e);
        } finally {
            if (kryo != null) {
                KryoUtil.release(kryo);
            }
        }
    }
}
