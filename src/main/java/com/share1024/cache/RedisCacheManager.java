package com.share1024.cache;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.cache.CacheManager;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author : yesheng
 * @Description :
 * @Date : 2017/10/22
 */
public class RedisCacheManager implements CacheManager{

    private final ConcurrentHashMap<String,Cache> caches = new ConcurrentHashMap<String, Cache>();

    public <K, V> Cache<K, V> getCache(String name) throws CacheException {

        Cache cache = caches.get(name);

        if(cache == null){
            cache = new RedisCache<K,V>();
            caches.put(name,cache);
        }
        return cache;
    }
}
