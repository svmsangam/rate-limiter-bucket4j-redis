package com.example.ratelimiter.RateLimiter.configs;

import io.github.bucket4j.distributed.proxy.ProxyManager;
import io.github.bucket4j.grid.jcache.JCacheProxyManager;
import org.redisson.config.Config;
import org.redisson.jcache.configuration.RedissonConfiguration;
import org.springframework.cache.annotation.EnableCaching;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import javax.cache.CacheManager;
import javax.cache.Caching;

@Configuration
public class RedisConfiguration {

    @Bean
    public Config config(){
        Config config = new Config();
        config.useSingleServer().setAddress("redis://127.0.0.1:6379");
        return config;
    }

    @Bean
    @DependsOn("config")
    public CacheManager cacheManager(Config config){
        CacheManager cacheManager = Caching.getCachingProvider().getCacheManager();
        cacheManager.createCache("cache", RedissonConfiguration.fromConfig(config));
        return cacheManager;
    }

    @Bean
    @DependsOn("cacheManager")
    ProxyManager<String> proxyManager(CacheManager cacheManager){
        return new JCacheProxyManager<>(cacheManager.getCache("cache"));
    }
}
