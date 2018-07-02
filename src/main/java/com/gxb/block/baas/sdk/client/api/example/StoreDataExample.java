package com.gxb.block.baas.sdk.client.api.example;

import com.alibaba.fastjson.JSON;
import com.gxb.block.baas.sdk.client.api.BaasApiException;
import com.gxb.block.baas.sdk.client.api.client.StoreDataClient;
import org.apache.commons.lang3.RandomStringUtils;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static com.gxb.block.baas.sdk.client.api.BaasConstants.*;

/**
 * @Description 存储数据例子
 * @Author Hanawa
 * @Date 2018/3/13
 * @Version 1.0
 */
public class StoreDataExample {


    private static String EXAMPLE_ACCOUNT = "1.2.441";
    private static String EXAMPLE_PUBLIC_KEY = "GXC8435JoLsmNgEzJBqkSzqfz65isQA1XfTvkGycG5KAdgsq4BhwV";
    private static String EXAMPLE_PRIVATE_KEY = "5KAYWd4oGVN6ZMcXej9DB74gqNZhcZP7fWtn2MKoCmsUwzJjQVy";

    public static void main(String[] args) throws BaasApiException {
        StoreDataExample example = new StoreDataExample();
        StoreDataClient client = new StoreDataClient(EXAMPLE_ACCOUNT, EXAMPLE_PRIVATE_KEY, EXAMPLE_PUBLIC_KEY, true);
//		example.loop();
        example.println(client);
    }

    private void println(StoreDataClient client) {
        System.out.println(JSON.toJSONString(client.storeString(RandomStringUtils.random(8))));
    }

    private void loop() {
        StoreDataClient client = new StoreDataClient(EXAMPLE_ACCOUNT, EXAMPLE_PRIVATE_KEY, EXAMPLE_PUBLIC_KEY, true,
                URL_HEADER);
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        for (int i = 1; i < 100000; i++) {
            if (i % 1000 == 0) {
                try {
                    System.out.println("sleeping...");
                    Thread.sleep(900000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            executorService.submit(() -> new StoreDataExample().println(client));
        }
    }
}
