package com.share1024.cache;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;

import java.util.Collection;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author : yesheng
 * @Description :
 * @Date : 2017/10/22
 */
public class CustomCache<K,V> implements Cache<K,V>{

    private ConcurrentHashMap<K,V> cache = new ConcurrentHashMap<K, V>();

    public V get(K key) throws CacheException {
        return cache.get(key);
    }

    public V put(K key, V value) throws CacheException {
        return cache.put(key,value);
    }

    public V remove(K key) throws CacheException {
        return cache.remove(key);
    }

    public void clear() throws CacheException {
        cache.clear();
    }

    public int size() {
        return cache.size();
    }

    public Set<K> keys() {
        return cache.keySet();
    }

    public Collection<V> values() {
        return cache.values();
    }
}
