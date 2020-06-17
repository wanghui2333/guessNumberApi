package com.twschool.practice.service;

import com.twschool.practice.console.GuessInputCommand;
import com.twschool.practice.domain.UserGameInfo;
import com.twschool.practice.domain.UserGameResponse;
import com.twschool.practice.repository.UserGameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * 功能描述 : TODO
 *
 * @Author : wang hui
 * @Email : 793147654@qq.com
 * @Date : 2020-06-17 21:09
 */
@Service
public class GuessNumberService {

    @Autowired
    UserGameRepository userGameRepository;

    private final String rightResult = "4A0B";

    public String generateNumber() {
        List<Integer> numberSource = IntStream.range(1, 9).boxed().collect(Collectors.toList());
        Collections.shuffle(numberSource);
        String numbers = numberSource.stream().limit(4).map(String::valueOf).collect(Collectors.joining(" "));

        return numbers;
    }


    public UserGameResponse playGame(String guess, UserGameInfo userGameInfo) {

        if (userGameInfo.getCount() >= 6){
            userGameInfo.setCount(0);
        }

        if (userGameInfo.getAnswer() == null){
            // 生成answer
            userGameInfo.setAnswer(generateNumber());
        }

        UserGameResponse userGameResponse = new UserGameResponse();
        userGameResponse.setCode(200);

        String result = this.judge(guess, userGameInfo.getAnswer());

        // 用户猜正确
        if (rightResult.equals(result)){
            int continuousRightCount = userGameInfo.getContinuousRightCount() + 1;

            userGameInfo.setCount(0);
            userGameInfo.setAnswer(null);
            userGameInfo.setContinuousRightCount(continuousRightCount);
            userGameInfo.setExtraIntegral(integralRule(continuousRightCount));
            userGameInfo.setIntegral(userGameInfo.getIntegral() + 3);

            userGameRepository.updateUserGameInfoById(userGameInfo.getUserId(), userGameInfo);

            UserGameResponse.Message message = new UserGameResponse.Message(
                    result,userGameInfo.getUserId(),userGameInfo.getCount(), userGameInfo.getContinuousRightCount(),
                    userGameInfo.getIntegral() + userGameInfo.getExtraIntegral(), "Right!");

            userGameResponse.setMessage(message);

            return userGameResponse;
        }

        userGameInfo.setContinuousRightCount(0);

        // 判断用户执行是否超过6次
        userGameInfo.setCount(userGameInfo.getCount() + 1);

        if (userGameInfo.getCount() >= 6){
            // 扣分
            userGameInfo.setIntegral(userGameInfo.getIntegral() - 3);
            userGameInfo.setAnswer(null);
        }

        userGameRepository.updateUserGameInfoById(userGameInfo.getUserId(), userGameInfo);

        UserGameResponse.Message message = new UserGameResponse.Message(
                result,userGameInfo.getUserId(),userGameInfo.getCount(),userGameInfo.getContinuousRightCount(),
                userGameInfo.getIntegral() + userGameInfo.getExtraIntegral(), "Wrong!");

        userGameResponse.setMessage(message);

        return userGameResponse;
    }

    public String judge(String input, String gameAnswer) {
        if (!GuessInputCommand.judgeInputFormat(input)) {
            return "Wrong Input，Input again";
        }

        String[] answer = gameAnswer.split(" ");
        // 值正确，位置不正确
        Set<String> correctNumber = new HashSet<>(4);
        // 值正确，位置正确
        Set<String> correctNumberAndPosition = new HashSet<>(4);

        String[] result = input.split(" ");

        for (int j = 0; j < result.length; j++) {
            for (int k = 0; k < answer.length; k++) {
                if (result[j].equals(answer[k])) {
                    // 位置相等
                    if (j == k) {
                        correctNumberAndPosition.add(result[j]);
                    } else {
                        // 只有值相等
                        correctNumber.add(result[j]);
                    }
                }
            }
        }

        String output = correctNumberAndPosition.size() + "A" + correctNumber.size() + "B";

        return output;
    }

    private Integer integralRule(int continuousRightCount){
        int integral = 0;

        if (continuousRightCount >= 3){
            integral = 2;
        }

        if (continuousRightCount >= 5){
            integral = 3;
        }

        return integral;
    }
}
