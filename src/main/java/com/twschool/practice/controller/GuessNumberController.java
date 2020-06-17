package com.twschool.practice.controller;

import com.alibaba.fastjson.JSONObject;
import com.twschool.practice.domain.UserGameInfo;
import com.twschool.practice.domain.UserGameInfoDTO;
import com.twschool.practice.domain.UserGameResponse;
import com.twschool.practice.repository.UserGameRepository;
import com.twschool.practice.service.GuessNumberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 功能描述 : TODO
 *
 * @Author : wang hui
 * @Email : 793147654@qq.com
 * @Date : 2020-06-17 16:29
 */
@RestController
@RequestMapping("/guess-number")
public class GuessNumberController {

    @Autowired
    GuessNumberService guessNumberService;

    @Autowired
    UserGameRepository userGameRepository;
    @GetMapping("/check")
    public JSONObject check(@RequestParam(value = "guess") String guess){

        JSONObject dataJson = new JSONObject();
        dataJson.put("output","4A0B");
        dataJson.put("instruction","success");

        JSONObject resultJson = new JSONObject();
        resultJson.put("code","200");
        resultJson.put("data", dataJson);

        return resultJson;

    }

    @PostMapping("/check")
    public UserGameResponse check(@RequestBody UserGameInfoDTO userGameInfoDTO){

        UserGameInfo userGameInfo = userGameRepository.getUserGameInfoById(userGameInfoDTO.getUserId());

        if (userGameInfo == null){
            UserGameInfo registerUserGameInfo = new UserGameInfo();
            registerUserGameInfo.setAnswer(null);
            registerUserGameInfo.setCount(0);
            registerUserGameInfo.setIntegral(0);
            registerUserGameInfo.setUserId(userGameInfoDTO.getUserId());
            registerUserGameInfo.setExtraIntegral(0);
            registerUserGameInfo.setContinuousRightCount(0);

            userGameRepository.registerUserGameInfo(registerUserGameInfo);

            userGameInfo = registerUserGameInfo;
        }

        return guessNumberService.playGame(userGameInfoDTO.getGuess(), userGameInfo);

    }
}
