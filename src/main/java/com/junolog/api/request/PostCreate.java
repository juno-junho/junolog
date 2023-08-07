package com.junolog.api.request;

import lombok.*;

import javax.validation.constraints.NotBlank;

@ToString
@Setter
@Getter
public class PostCreate {

    @NotBlank(message = "타이틀을 입력해주세요.")
    private String title;

    @NotBlank(message = "컨텐츠를 입력해주세요.")
    private String content;

    @Builder // 클래스 위에 달면 작동이 안될 수도 있다. -> 가능한 생성자에 다는 것을 추천
    private PostCreate(final String title, final String content) {
        this.title = title;
        this.content = content;
    }
}


