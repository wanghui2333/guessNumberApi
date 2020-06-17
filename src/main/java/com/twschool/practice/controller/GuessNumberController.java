package com.twschool.practice.controller;

import com.alibaba.fastjson.JSONObject;
import com.twschool.practice.domain.UserGameInfoDTO;
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
    public JSONObject check(@RequestBody UserGameInfoDTO userGameInfoDTO){

        JSONObject dataJson = new JSONObject();
        dataJson.put("output","4A0B");
        dataJson.put("instruction","success");

        JSONObject resultJson = new JSONObject();
        resultJson.put("code","200");
        resultJson.put("data", dataJson);

        return resultJson;

    }
}
