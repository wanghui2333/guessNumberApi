package com.twschool.practice.console;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 功能描述 : TODO
 *
 * @Author : wang hui
 * @Email : 793147654@qq.com
 * @Date : 2020-06-16 17:11
 */
public class GuessInputCommand {

    private BufferedReader bufferedReader;

    public GuessInputCommand() {
        bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    }

    public String input() throws IOException {
        System.out.println("------请输入答案，格式 x x x x , x < 10 ------");
        return bufferedReader.readLine();
    }

    public static boolean judgeInputFormat(String input) {
        // 去除空字符
        input = input.replace(" ", "");
        if(input.length() != 4) {
            return false;
        }

        //相同字符串去重
        StringBuffer sb = new StringBuffer(input);

        String rs = sb.reverse().toString().replaceAll("(.)(?=.*\\1)", "");

        StringBuffer out = new StringBuffer(rs);
        String result = out.reverse().toString();
        if(result.length() != 4) {
            return false;
        }

        return true;
    }
}
