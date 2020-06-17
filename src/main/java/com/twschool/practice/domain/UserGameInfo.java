package com.twschool.practice.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * 功能描述 : 用户游戏存档信息
 *
 * @Author : wang hui
 * @Email : 793147654@qq.com
 * @Date : 2020-06-17 21:10
 */
public class UserGameInfo {

    /**
     * 用户id
     */
    String userId;

    /**
     * 游戏答案
     */
    String answer;

    /**
     * 用户本次玩的次数
     */
    Integer count = 0;

    /**
     * 用户连续正确次数
     */
    Integer continuousRightCount = 0;

    /**
     * 积分
     */
    Integer integral = 0;

    /**
     * 额外积分
     */
    Integer extraIntegral = 0;

    /**
     * 历史积分信息
     */
    List<History> histories = new ArrayList<>(32);

    class History {
        Integer totalIntegral = 0;

        String time;
    }
}
