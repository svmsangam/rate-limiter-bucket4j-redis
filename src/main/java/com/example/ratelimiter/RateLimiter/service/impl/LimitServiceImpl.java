package com.example.ratelimiter.RateLimiter.service.impl;

import com.example.ratelimiter.RateLimiter.service.LimitService;
import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.BucketConfiguration;
import io.github.bucket4j.Refill;
import io.github.bucket4j.distributed.proxy.ProxyManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.function.Supplier;

@Service
@RequiredArgsConstructor
public class LimitServiceImpl implements LimitService {
    private final ProxyManager<String> buckets;

    @Override
    public Bucket resolveBucket(String key) {
        Supplier<BucketConfiguration> configurationSupplier = getConfigSupplierForOrder(key);
        return buckets.builder().build(key,configurationSupplier);
    }

    @Override
    public Supplier<BucketConfiguration> getConfigSupplierForOrder(String key) {

        Refill refill = Refill.intervally(10, Duration.ofMinutes(1));
        Bandwidth limit = Bandwidth.classic(10,refill);
        return ()->(BucketConfiguration.builder()
                .addLimit(limit)
                .build()) ;
    }
}
