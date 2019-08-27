package site.pushy.ymall.common.util;

import java.util.Random;
import java.util.UUID;

public class IDUtil {

    public static long getRandomId() {
        long millis = System.currentTimeMillis();
        //加上两位随机数
        Random random = new Random();
        int end2 = random.nextInt(99);
        //如果不足两位前面补0
        String str = millis + String.format("%02d", end2);
        return new Long(str);
    }

    public static String getUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}
