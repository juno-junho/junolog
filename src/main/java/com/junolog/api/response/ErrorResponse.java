package com.junolog.api.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.HashMap;
import java.util.Map;

/**
 * {
 *     "code": "400",   // 필요한지는 client와 회의해서 결정해야 한다.
 *     "message": "잘못된 요청입니다.",
 *     "validation": {
 *         "title": "타이틀을 입력해주세요"
 *         "contend":
 *     }
 * }
 */

@RequiredArgsConstructor
@Getter
public class  ErrorResponse { // 정의하는 방식은 회사마다, 팀마다 다르
    private final String code;
    private final String message;
    private Map<String, String> validation = new HashMap<>();

    public void addValidation(String fieldName, String errorMessage) {
        this.validation.put(fieldName, errorMessage);
    }
}
