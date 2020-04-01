package cn.yyn.postCache.redisproxy;

import cn.yyn.postCache.serializer.KryoSerializer;
import cn.yyn.postCache.serializer.NonExistRedisObject;
import lombok.AllArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import javax.annotation.Resource;

@AllArgsConstructor
@Setter
@Component
public class JedisProxy {

    @Resource
    private final JedisPool jedisPool;

    public Object get(String key) {
        if (StringUtils.isBlank(key)) {
            return NonExistRedisObject.notExistRedisObject;
        }
        Jedis jedis = jedisPool.getResource();
        byte[] bytes = jedis.get(key.getBytes());
        if (null == bytes) {
            return NonExistRedisObject.notExistRedisObject;
        }
        return KryoSerializer.deserialize(bytes); //真实value,可能就是null
    }

    public void put(String key, Object value, int expire) {
        if (StringUtils.isBlank(key)) {
            return;
        }
        Jedis jedis = jedisPool.getResource();
        jedis.setex(key.getBytes(), expire, KryoSerializer.serialize(value));
    }

    public void delete(String key) {
        Jedis jedis = jedisPool.getResource();
        jedis.del(key);
    }

    public void close() {
        Jedis jedis = jedisPool.getResource();
        jedis.close();
    }

}
