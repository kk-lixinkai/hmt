package com.mybestcoding.hmt.util;

import cn.hutool.crypto.digest.DigestAlgorithm;
import cn.hutool.crypto.digest.Digester;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.UUID;

/**
 * @author: lixinkai
 * @description: 常用工具
 * @date: 2021/2/15 9:41
 * @GitHub: https://github.com/kk-lixinkai
 * @Gitee: https://gitee.com/bestbug
 * @version: 1.0
 */
public class CommonUtil {
    private static Digester md5 = new Digester(DigestAlgorithm.MD5);

    /**
     * 生成密码摘要
     *
     * @param password 明文密码
     * @param salt     盐
     * @return 加密后的密码
     */
    public static String encryption(String password, String salt) {
        return md5.digestHex(password + salt);
    }


    /**
     * 获得一个 盐
     *
     * @return
     */
    public static String getSalt() {
        String s = UUID.randomUUID().toString();
        return s.substring(0, s.indexOf("-"));
    }

    /**
     * 获得一个标识 userKey
     *
     * @return
     */
    public static String getKey() {
        String s = UUID.randomUUID().toString();
        return s.substring(s.lastIndexOf("-") + 1);
    }


    /**
     * 将日期转换为 时间戳
     *
     * @param date 时间
     * @return 时间戳
     */
    public static long DateToTimestamp(String date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long timestamp = sdf.parse(date, new ParsePosition(0)).getTime() / 1000;
        return timestamp;

    }
}
