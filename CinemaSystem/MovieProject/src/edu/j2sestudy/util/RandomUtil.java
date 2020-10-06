package edu.j2sestudy.util;

public class RandomUtil {
    /**
     * 获得一个随机数
     * @return 随机整数
     */
    public static int getRandomNum() {
        long time = System.currentTimeMillis();
        String s = time + "";
        s = s.substring(7);
        return Integer.parseInt(s);
    }
}
