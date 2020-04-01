package cn.yyn.postCache.serializer;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.pool.KryoFactory;
import com.esotericsoftware.kryo.pool.KryoPool;

public class PooledKryoFactory implements KryoFactory {
    private KryoPool pool;

    PooledKryoFactory() {
        // Build pool with SoftReferences enabled (optional)
        pool = new KryoPool.Builder(this).softReferences().build();
    }

    Kryo getKryo() {
        return pool.borrow();
    }

    void returnKryo(Kryo kryo) {
        pool.release(kryo);
    }

    @Override
    public Kryo create() {
        return new Kryo();
    }
}
