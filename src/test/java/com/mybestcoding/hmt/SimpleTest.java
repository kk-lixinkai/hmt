package com.mybestcoding.hmt;


import com.mybestcoding.hmt.util.CommonUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.UUID;

/**
 * Created By lixinkai on 2020/12/29
 */
@Slf4j
public class SimpleTest {

    @Test
    public void testLength() {
        String str = "11111111111-111111111111111-11111111111111111";
        System.out.println(str.split("-").length);
    }

    @Test
    public void uuidtest() {
        String s = UUID.randomUUID().toString();
        System.out.println(s);
        String substring = s.substring(0, s.indexOf("-"));
        System.out.println(substring);
        String substring1 = s.substring(s.lastIndexOf("-") + 1);
        System.out.println(substring1);
    }


    @Test
    public void generatePassword() {
        String salt = "222d5a2e";
        String password = CommonUtil.encryption("lixinkai", salt);
        log.info(password);
    }
}
