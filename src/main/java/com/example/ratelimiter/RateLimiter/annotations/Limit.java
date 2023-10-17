package com.example.ratelimiter.RateLimiter.annotations;

import jdk.jfr.ContentType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Limit{
    String key() default "skip";//order,desk,product,user
    boolean increaseCapacity() default false;
}