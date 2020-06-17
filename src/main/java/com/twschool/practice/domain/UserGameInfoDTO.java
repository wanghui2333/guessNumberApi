package com.twschool.practice.domain;

/**
 * 功能描述 : TODO
 *
 * @Author : wang hui
 * @Email : 793147654@qq.com
 * @Date : 2020-06-17 21:30
 */

public class UserGameInfoDTO {
    /**
     * 用户id
     */
    String userId;

    /**
     * 用户输入答案
     */
    String guess;


    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getGuess() {
        return guess;
    }

    public void setGuess(String guess) {
        this.guess = guess;
    }
}
