package com.shun.favoriteindex.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

/**
 * 公共工具类
 */
public class CommonUtil {

    public static final String[] LETTER = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O",
            "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};

    /**
     * 生成验证码
     * @param codeLength 验证码长度
     * @return
     */
    public static String generateVerificationCode(int codeLength) {
        StringBuilder codeBuilder = new StringBuilder();
        for (int i = 0; i < codeLength; i++) {
            codeBuilder.append(LETTER[(int) ((Math.random()) * 26)]);
        }
        return codeBuilder.toString();
    }

    /**
     * 读取文件内容
     *
     * @param filePath
     * @return
     */
    public static String readFileContent(String filePath) {
        File file = new File(filePath);
        if (!file.exists()) {
            throw new RuntimeException("读取文件内容出错，错误原因：文件不存在");
        }
        StringBuilder result = new StringBuilder();
        try {
            //构造一个BufferedReader类来读取文件
            BufferedReader br = new BufferedReader(new FileReader(file));
            String s = null;
            //使用readLine方法，一次读一行
            while ((s = br.readLine()) != null) {
                result.append("\n").append(s);
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return result.toString();
    }
}
