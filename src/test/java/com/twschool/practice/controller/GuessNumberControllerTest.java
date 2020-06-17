package com.twschool.practice.controller;

import com.alibaba.fastjson.JSONObject;
import com.twschool.practice.console.GuessInputCommand;
import com.twschool.practice.domain.GuessNumber;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@ContextConfiguration
public class GuessNumberControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private final String basePath = "/guess-number";


    public GuessNumberControllerTest() {
    }

    @Test
    public void should_return_guess_result() throws Exception {
        JSONObject dataJson = new JSONObject();
        dataJson.put("output","4A0B");
        dataJson.put("instruction","success");

        mockMvc.perform(MockMvcRequestBuilders.get(basePath + "/check")
        .contentType(MediaType.APPLICATION_JSON)
        .param("guess", "1 2 3 4"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.code").value("200"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data").value(dataJson));
    }

    @Test
    public void should_return_true_given_1234(){

        String input = "1 2 3 4";
        boolean actual = GuessInputCommand.judgeInputFormat(input);

        assert actual == true;
    }

    @Test
    public void should_return_false_given_134(){

        String input = "1 3 4";
        boolean actual = GuessInputCommand.judgeInputFormat(input);

        assert actual == false;
    }

    @Test
    public void should_return_true_given_void(){
        GuessNumber guessNumber = new GuessNumber();
        String number = guessNumber.generateNumber();
        boolean actual = GuessInputCommand.judgeInputFormat(number);

        assert actual == true;

    }


    @Test
    public void should_return_4A0B_given_1234_answer_1234(){
        GuessNumber guessNumber = new GuessNumber();

        String actual = guessNumber.playGame("1 2 3 4","1 2 3 4");
        String expect = "4A0B";
        assert expect.equals(actual);

    }

    @Test
    public void should_return_2A2B_given_1243_answer_1234(){
        GuessNumber guessNumber = new GuessNumber();

        String actual = guessNumber.playGame("1 2 4 3","1 2 3 4");
        String expect = "2A2B";
        assert expect.equals(actual);

    }

    @Test
    public void should_return_1A2B_given_1043_answer_1234(){
        GuessNumber guessNumber = new GuessNumber();

        String actual = guessNumber.playGame("1 0 4 3","1 2 3 4");
        String expect = "1A2B";
        assert expect.equals(actual);

    }

    @Test
    public void should_return_0A4B_given_4321_answer_1234(){
        GuessNumber guessNumber = new GuessNumber();

        String actual = guessNumber.playGame("4 3 2 1","1 2 3 4");
        String expect = "0A4B";
        assert expect.equals(actual);

    }

    @Test
    public void should_return_0A3B_given_4351_answer_1234(){
        GuessNumber guessNumber = new GuessNumber();

        String actual = guessNumber.playGame("4 3 5 1","1 2 3 4");
        String expect = "0A3B";
        assert expect.equals(actual);

    }

    @Test
    public void should_return_0A0B_given_5678_answer_1234(){
        GuessNumber guessNumber = new GuessNumber();

        String actual = guessNumber.playGame("5 6 7 8","1 2 3 4");
        String expect = "0A0B";
        assert expect.equals(actual);

    }

    @Test
    public void should_return_6_given_count_6(){
        GuessNumber guessNumber = new GuessNumber();
        int count = 6;

        for (int i = 0; i < count; i++){
            guessNumber.playGame(guessNumber.generateNumber(),"1 2 3 4");
        }

        assert guessNumber.getHistory().size() == count;

    }

}