package com.twschool.practice.controller;

import com.alibaba.fastjson.JSONObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
}
