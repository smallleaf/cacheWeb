package com.share1024.cache;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.util.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

/**
 * @author : yesheng
 * @Description :
 * @Date : 2017/10/22
 */
public class RedisCache<K,V> implements Cache<K,V> {

    private Logger logger = LoggerFactory.getLogger(RedisCache.class);

    private final String SHIRO_SESSION="shiro_session:";

    public String getKey(K key){
        return SHIRO_SESSION + key;
    }

    @Override
    public V get(K key) throws CacheException {
        logger.info("get--从redis中获取:{}",key);
        Object o =  SerializeUtils.deserialize(RedisUtil.getInstance().get(getKey(key).getBytes()));

        if(o == null){
            return null;
        }

        return (V)o;
    }

    @Override
    public V put(K key, V value) throws CacheException {
        logger.info("put--保存到redis，key:{},value:{}",key,value);
        get(key);

        byte[] b = SerializeUtils.serialize(value);
        Object o = SerializeUtils.deserialize(b);
        RedisUtil.getInstance().set(getKey(key).getBytes(),SerializeUtils.serialize(value));
        return get(key);
    }

    @Override
    public V remove(K key) throws CacheException {
        logger.info("remove--删除key:{}",key);
        V value = get(key);
        RedisUtil.getInstance().del(getKey(key).getBytes());
        return value;
    }

    @Override
    public void clear() throws CacheException {
        logger.info("clear--清空缓存");
        RedisUtil.getInstance().del((SHIRO_SESSION + "*").getBytes());
    }

    @Override
    public int size() {
        logger.info("size--获取缓存大小");
        return keys().size();
    }

    @Override
    public Set<K> keys() {
        logger.info("keys--获取缓存大小keys");
        return (Set<K>) RedisUtil.getInstance().keys(SHIRO_SESSION + "*");
    }

    @Override
    public Collection<V> values() {
        logger.info("values--获取缓存值values");
        Set<K> keys = keys();
        if (!CollectionUtils.isEmpty(keys)) {
            List<V> values = new ArrayList<V>(keys.size());
            for (K key : keys) {
                @SuppressWarnings("unchecked")
                V value = get(key);
                if (value != null) {
                    values.add(value);
                }
            }
            return Collections.unmodifiableList(values);
        } else {
            return Collections.emptyList();
        }
    }
}
