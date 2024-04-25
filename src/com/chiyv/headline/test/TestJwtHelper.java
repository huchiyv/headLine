package com.chiyv.headline.test;

import com.chiyv.headline.util.JwtHelper;
import org.junit.Test;

public class TestJwtHelper {

    @Test
    public  void testAllMethod() throws InterruptedException {
        String token = JwtHelper.createToken(1L);
        System.out.println(token);
        Long userId = JwtHelper.getUserId(token);

        System.out.println(JwtHelper.isExpiration((token)));

        Thread.sleep(7000);

        System.out.println(JwtHelper.isExpiration((token)));


    }
}
