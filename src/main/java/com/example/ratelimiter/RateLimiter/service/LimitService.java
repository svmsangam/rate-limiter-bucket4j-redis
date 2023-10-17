package com.example.ratelimiter.RateLimiter.service;

import io.github.bucket4j.Bucket;
import io.github.bucket4j.BucketConfiguration;

import java.util.function.Supplier;

public interface LimitService {
 Bucket resolveBucket(String key);

 Supplier<BucketConfiguration> getConfigSupplierForOrder(String key);
}
