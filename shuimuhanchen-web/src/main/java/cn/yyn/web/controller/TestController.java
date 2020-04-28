package cn.yyn.web.controller;

import cn.yyn.jsonConfig.FieldTemplateService;
import cn.yyn.model.dto.FieldTemplateConfig;
import org.redisson.api.RBucket;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * 测试Controller
 *
 * @author:yyn
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @Resource
    private RedissonClient redissonClient;

    @Resource
    private FieldTemplateService fieldTemplateService;

    private int number = 100;

    @GetMapping("/success")
    public String success(Map<String, Object> map){
        map.put("hello", "恭喜你测试成功！");
        return "test";
    }

    @GetMapping("/jsonConfig")
    public List<FieldTemplateConfig> jsonConfig(){
        return fieldTemplateService.getFieldConfigsByDelType();
    }

//
//    /**
//     * 分布式锁例子，基于redis，redis单线程，底层类似nio，分布式情况下，只有一个线程能访问到redis
//     * redis的 SETNX 是『SET if Not eXists』(如果不存在，则 SET)，设置成功，返回 1 。设置失败，返回 0 。利用其返回值加锁
//     * @return
//     */
//    @GetMapping("/redissionLock")
//    public String redissionLock(){
//
//        //加锁
//        RBucket<String> bucket = redissonClient.getBucket("lock");
//        boolean lock = bucket.trySet("yanyingnan");
//        if(!lock){
//            return "分布式争抢中，请稍等！";
//        }
//
//        if(number > 0){
//            number --;
//            System.out.println("number-- 成功" + number);
//        } else {
//            System.out.println("number 已经是0啦" + number);
//        }
//
//        //释放锁
//        bucket.delete();
//
//
//        return "testRedissionLock";
//    }


//    /**
//     * 分布式锁例子（优化1），基于redis，redis单线程，底层类似nio，分布式情况下，只有一个线程能访问到redis
//     * 1、finally中保证一定要释放锁，避免死锁
//     * 2、加上过期时间，避免死锁
//     * @param map
//     * @return
//     */
//    @GetMapping("/redissionLock")
//    public String redissionLock(){
//
//        RBucket<String> bucket = redissonClient.getBucket("lock");
//        try {
//            //加锁
//            boolean lock = bucket.trySet("yanyingnan", 10, TimeUnit.SECONDS);
//            if(!lock){
//                return "分布式争抢中，请稍等！";
//            }
//
//            if(number > 0){
//                number --;
//                System.out.println("number-- 成功" + number);
//            } else {
//                System.out.println("number 已经是0啦" + number);
//            }
//
//        } finally {
//            //释放锁
//            bucket.delete();
//        }
//
//
//        return "testRedissionLock";
//    }


//    /**
//     * 分布式锁例子（优化2），基于redis，redis单线程，底层类似nio，分布式情况下，只有一个线程能访问到redis
//     * 1、每一个线程的锁的值为随机生成的uuid 防止一个线程没执行完，过期时间到了自动解锁了，另一个线程加锁进入后，第一个线程释放了另一个线程的锁
//     * @param map
//     * @return
//     */
//    @GetMapping("/redissionLock")
//    public String redissionLock(){
//
//        RBucket<String> bucket = redissonClient.getBucket("lock");
//        String uuid = UUID.randomUUID().toString();
//        try {
//            //加锁
//            boolean lock = bucket.trySet("uuid", 10, TimeUnit.SECONDS);
//            if(!lock){
//                return "分布式争抢中，请稍等！";
//            }
//
//            if(number > 0){
//                number --;
//                System.out.println("number-- 成功" + number);
//            } else {
//                System.out.println("number 已经是0啦" + number);
//            }
//
//        } finally {
//            //先确定是自己的锁，释放锁
//            if(uuid.equals(bucket.get()))
//            bucket.delete();
//        }
//
//
//        return "testRedissionLock";
//    }

    /**
     * 分布式锁例子（优化3），基于redis，redis单线程，底层类似nio，分布式情况下，只有一个线程能访问到redis
     * 1、为了防止线程没执行完就自动释放锁了，当前线程开启一个新线程，定时判断当前的锁是否释放了，如果没有就将过期时间还设置为原来的值，定时时间是过期时间的1/3
     * 这也就是redission的底层原理
     * @return
     */
    @GetMapping("/redissionLock")
    public String redissionLock(){

        RLock lock = redissonClient.getLock("lock");
        try {
            //加锁
            lock.lock(10, TimeUnit.SECONDS);

            if(number > 0){
                number --;
                System.out.println("number-- 成功" + number);
            } else {
                System.out.println("number 已经是0啦" + number);
            }

        } finally {
            lock.unlock();
        }


        return "testRedissionLock";
    }

}
