package com.bme.syx.redistest;

import redis.clients.jedis.Jedis;

public class RedisDemo {


    public static void main(String[] args) {

        String DATA_SOURCE_KEY_PREFIX = "cache:dataSource:";

        Jedis jedis = new Jedis("192.168.1.132",9000);
        jedis.auth("1qaz@WSX");
        String key = DATA_SOURCE_KEY_PREFIX + 64 + ":" + "00000000092034";

        jedis.set(key,"1");

        String  value = jedis.get(key);

        System.out.println(value);
        
    }
}
