package com.twschool.practice.domain;

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
     * 用户本次玩的次数
     */
    Integer count = 0;

    /**
     * 积分
     */
    Integer integral = 0;

    /**
     * 额外积分
     */
    Integer extraIntegral = 0;

}
