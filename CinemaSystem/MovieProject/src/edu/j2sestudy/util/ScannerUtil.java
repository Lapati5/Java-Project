package edu.j2sestudy.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class ScannerUtil {

    private static final Scanner in = new Scanner(System.in);

    public static int readID() {
        String s = readKeyBoard(10, false);
        return Integer.parseInt(s);
    }

    /**
     * 读取一个日期
     * @param format 日期格式
     * @return date
     */
    public static LocalDate readDate(String format) {
        String s = readKeyBoard(format.length(), false);
        return LocalDate.parse(s, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    /**
     * 读取一个字符串
     * @param limit 字符串最大长度
     * @return 字符串
     */
    public static String readString(int limit) {
        return readKeyBoard(limit, false);
    }
    /**
     * 通过控制台读取正确选择
     * @param size 可选择的选项数量
     * @return 选择字符
     */
    public static char readMenuSelect(int size) {
        char c = ' ';
        while (true) {
            String str = readKeyBoard(1, true);
            if (!str.equals("")) {
                c = str.charAt(0);
            } else {
                System.out.println("请输入你的选择：");
                continue;
            }
            boolean r = true;
            for (int i = 1; i <= size; ++i) {
                r &= (c != (i + '0'));
                if (!r) break;
            }
            if (r) {
                System.out.println("选择错误，请重新选择：");
            } else {
                break;
            }
        }
        return c;
    }

    /**
     * 从控制台按照指定要求获得一行数据
     * @param limit 输入的最大长度
     * @param blank 是否不允许空格输入（true：不允许，false：允许）
     * @return 输入正确的字符串
     */
    private static String readKeyBoard(int limit, boolean blank) {
        String s = "";
        while (in.hasNextLine()) {
            s = in.nextLine().trim();
            //输入为空格
            if (s.length() == 0) {
                if (blank) {
                    break;
                } else {
                    continue;
                }
            }
            //输入超出限制
            if (s.length() > limit) {
                System.out.println("输入长度有误（不大于" + limit + "）个字符，请重新输入：");
                continue;
            }
            break;
        }
        return s;
    }
}
