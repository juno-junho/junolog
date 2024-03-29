package com.junolog.api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.junolog.api.domain.Post;
import com.junolog.api.repository.PostRepository;
import com.junolog.api.request.PostCreate;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

//@WebMvcTest
@SpringBootTest
@AutoConfigureMockMvc
class PostControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @AfterEach
    void tearDown() {
        postRepository.deleteAllInBatch();
    }

    @Test
    @DisplayName("/posts 요청시 Hello world를 출력한다")
    void test() throws Exception {
        // Given
        PostCreate request = PostCreate.builder()
                .title("제목입니다")
                .content("내용입니다")
                .build();

        String json = objectMapper.writeValueAsString(request);

        System.out.println(json);
        // expected
        mockMvc.perform(post("/posts")
                        .contentType(MediaType.APPLICATION_JSON)
                        /* .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                         .param("title", "글 제목 입니다.")
                         .param("content", "글 내용 입니다.")*/
                        .content(json)
                )   // MockMvc의 경우 application/json 형태로 보낸다. (예전에는 application/x-www-form-urlencoded로 되어있었음.)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("{}"))
                .andDo(MockMvcResultHandlers.print());  // http request summary 남겨줌.
    }

    @Test
    @DisplayName("/posts 요청시 title값은 필수 다.")
    void test2() throws Exception {
        mockMvc.perform(post("/posts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"title\": \"\",\"content\": \"내용입니다.\"}")
                )
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
//                .andExpect(MockMvcResultMatchers.content().string("Hello World"))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.title").value("타이틀을 입력해주세요."))
                .andExpect(MockMvcResultMatchers.jsonPath("$.code").value("400"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("잘못된 요청입니다."))
                .andExpect(MockMvcResultMatchers.jsonPath("$.validation.title").value("타이틀을 입력해주세요."))
                .andDo(MockMvcResultHandlers.print());

    }

    @Test
    @DisplayName("/posts 요청시 db에 값이 저장된다")
    void test3() throws Exception {
        // when
        mockMvc.perform(post("/posts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"title\": \"제목입니다.\",\"content\": \"내용입니다.\"}")
                )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());

        //then
        assertThat(postRepository.count()).isEqualTo(1L);
    }
}
