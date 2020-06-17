package com.twschool.practice.domain;

/**
 * 功能描述 : TODO
 *
 * @Author : wang hui
 * @Email : 793147654@qq.com
 * @Date : 2020-06-17 21:31
 */
public class UserGameResponse {
    private Integer code;

    private Message message;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }

    public static class Message{
        String result;

        /**
         * 用户id
         */
        String userId;

        /**
         * 用户本次玩的次数
         */
        Integer count = 0;

        /**
         * 用户连续正确次数
         */
        Integer continuousRightCount = 0;

        /**
         * 总积分
         */
        Integer integralTotal = 0;


        String instruction;

        public Message(String result, String userId, Integer count, Integer continuousRightCount, Integer integralTotal, String instruction) {
            this.result = result;
            this.userId = userId;
            this.count = count;
            this.continuousRightCount = continuousRightCount;
            this.integralTotal = integralTotal;
            this.instruction = instruction;
        }

        public String getResult() {
            return result;
        }

        public void setResult(String result) {
            this.result = result;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public Integer getCount() {
            return count;
        }

        public void setCount(Integer count) {
            this.count = count;
        }

        public Integer getContinuousRightCount() {
            return continuousRightCount;
        }

        public void setContinuousRightCount(Integer continuousRightCount) {
            this.continuousRightCount = continuousRightCount;
        }

        public Integer getIntegralTotal() {
            return integralTotal;
        }

        public void setIntegralTotal(Integer integralTotal) {
            this.integralTotal = integralTotal;
        }

        public String getInstruction() {
            return instruction;
        }

        public void setInstruction(String instruction) {
            this.instruction = instruction;
        }
    }

}
