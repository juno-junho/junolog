package com.junolog.api.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@WebMvcTest
class PostControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("/posts 요청시 Hello world를 출력한다")
    void test() throws Exception {
        // 글 제목

        //글 내용

        // expected
        mockMvc.perform(MockMvcRequestBuilders.post("/posts")
                        .contentType(MediaType.APPLICATION_JSON)
                        /* .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                         .param("title", "글 제목 입니다.")
                         .param("content", "글 내용 입니다.")*/
                        .content("{\"title\": \"제목입니다.\",\"content\": \"내용입니다.\"}")
                )   // MockMvc의 경우 application/json 형태로 보낸다. (예전에는 application/x-www-form-urlencoded로 되어있었음.)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("{}"))
                .andDo(MockMvcResultHandlers.print());  // http request summary 남겨줌.
    }

    @Test
    @DisplayName("/posts 요청시 title값은 필수다.")
    void test2() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/posts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"title\": \"\",\"content\": \"내용입니다.\"}")
                )
                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andExpect(MockMvcResultMatchers.content().string("Hello World"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.title").value("타이틀을 입력해주세요."))
                .andDo(MockMvcResultHandlers.print());
    }
}