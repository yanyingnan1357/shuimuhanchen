package cn.yyn.postCache.serializer;

import com.esotericsoftware.kryo.Kryo;

class KryoUtil {
    private static PooledKryoFactory kryoFactory = new PooledKryoFactory();

    static Kryo get() {
        return kryoFactory.getKryo();
    }

    static void release(Kryo kryo) {
        kryoFactory.returnKryo(kryo);
    }
}
