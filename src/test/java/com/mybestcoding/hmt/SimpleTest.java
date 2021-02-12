package com.mybestcoding.hmt;

import org.junit.Test;

/**
 * Created By lixinkai on 2020/12/29
 */
public class SimpleTest {

    @Test
    public void testLength() {
        String str = "11111111111-111111111111111-11111111111111111";
        System.out.println(str.split("-").length);
    }
}
