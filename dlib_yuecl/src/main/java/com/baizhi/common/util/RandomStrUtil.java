package com.baizhi.common.util;

import java.util.Random;

public class RandomStrUtil {

    public static String getString(int count) {

        Random random = new Random();

        String str = "1234567890ABCDDAWDHUKIHNMKIYUEOLFHJDANabdaudwgtdncoweirwldmidppmnvbv";

        char[] chars = new char[count];

        for (int i = 0; i < chars.length; i++) {

            chars[i] = str.charAt(random.nextInt(str.length()));
        }

        return new String(chars);

    }
}
