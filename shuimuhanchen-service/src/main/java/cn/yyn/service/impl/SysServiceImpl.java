package cn.yyn.service.impl;

import cn.yyn.common.CacheConstants;
import cn.yyn.dao.SysLogMapper;
import cn.yyn.dao.SysViewMapper;
import cn.yyn.enums.EnumMessageType;
import cn.yyn.model.dto.BaseKafkaMessage;
import cn.yyn.model.entity.SysLog;
import cn.yyn.model.entity.SysView;
import cn.yyn.postCache.aop.PostCache;
import cn.yyn.postCache.aop.PostCacheConfig;
import cn.yyn.service.SysService;
import com.alibaba.fastjson.JSON;
import io.searchbox.client.JestClient;
import io.searchbox.core.Index;
import io.searchbox.core.Search;
import io.searchbox.core.SearchResult;
import org.redisson.api.RedissonClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * SysService实现类
 *
 * @author:yyn
 */
@Component
@PostCacheConfig(prefix = "post-cache:SysService", expire = 10 * 24 * 3600)
public class SysServiceImpl implements SysService {

    @Resource
    private SysLogMapper sysLogMapper;

    @Resource
    private SysViewMapper sysViewMapper;

    @Resource
    private JestClient jestClient;

    @Resource
    private RedissonClient redissonClient;

    @Resource
    private KafkaTemplate<String, String> kafkaTemplate;

    @Value("${spring.kafka.producer.topic.default}")
    private String defaultTopic;

    private static Logger LOG = LoggerFactory.getLogger(SysServiceImpl.class);

    /**
     * 增加一条日志信息
     *
     * @param sysLog
     */
    @Override
    public void addLog(SysLog sysLog) {
        sysLogMapper.insertLog(sysLog);
    }

    /**
     * 增加一条访问量
     *
     * @param sysView
     */
    @Override
    public void addView(SysView sysView) {
        sysViewMapper.insertView(sysView);
    }

    /**
     * 批量获取日志信息
     *
     * @return
     */
    @Override
    //只缓存批量查询的第一批数据
    @Cacheable(cacheNames = CacheConstants.DEFAULT, key = "'getBatchLog-' + #id + '-' + #limit", condition = "#id == 0", unless = "#result.size() == 0")
    public List<SysLog> getBatchLog(Long id, Integer limit) {
        return sysLogMapper.selectLogBatch(id, limit);
    }

    /**
     * 批量获取访问信息
     *
     * @return
     */
    @Override
    //先查库再将结果放入缓存
    //@CachePut(cacheNames = CacheConstants.DEFAULT, key = "'getBatchView-' + #id + '-' + #limit")
    //先查库再将结果从缓存删除 beforeInvocation = true表示先删除缓存再查库
    //@CacheEvict(cacheNames = CacheConstants.DEFAULT, key = "'getBatchView-' + #id + '-' + #limit")

    //使用自己封装的后置缓存注解
    @PostCache
    public List<SysView> getBatchView(Long id, Integer limit) {

        esTest();
        send();

        return sysViewMapper.selectViewBatch(id, limit);
    }

    private void esTest(){

        SysLog sysLog = new SysLog();
        sysLog.setId(1L);
        sysLog.setIp("0.0.0.1");

        //-----es测试-添加索引---------
        Index index = new Index.Builder(sysLog)
                .index("sys")
                .type("log")
                .id(sysLog.getId().toString())
                .build();
        try {
            jestClient.execute(index);
            LOG.info("构建es索引成功index={}", index);
        } catch (IOException e) {
            LOG.error("jest IO异常", e);
        }

        String json = "{\n" +
                "\t\"query\" : {\n" +
                "\t\t\"match\" : {\n" +
                "\t\t\t\"operateBy\" : \"Chro Mac\"\n" +
                "\t\t}\n" +
                "\t}\n" +
                "}";
        Search search = new Search.Builder(json)
                .addIndex("sys")
                .addType("log")
                .build();

        //-----es测试-搜索---------
        try {
            SearchResult result = jestClient.execute(search);
            System.out.println(result.getJsonString());
        } catch (IOException e) {
            LOG.error("jest IO异常", e);
        }

    }

    //-----kafka测试------发送消息方法
    public void send() {
        BaseKafkaMessage<String> msg = new BaseKafkaMessage<>();
        msg.setMsgType(EnumMessageType.SELF_MSG.getType());
        msg.setData("test");
        kafkaTemplate.send(defaultTopic, JSON.toJSONString(msg));
        LOG.info("发送消息:topic={}, msg={}", defaultTopic, msg);
    }

}
