package com.junolog.api.controller;

import com.junolog.api.request.PostCreate;
import com.junolog.api.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * SSR -> jsp
 * // html rendering
 * SPA -> vue, react
 * // javascript로 화면, server와의 통신은 api로만. (JSON)
 * vue + SSR = nuxt.js
 * react + SSR = next.js
 */
@RestController
@Slf4j

@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    /**
     * Http Method
     * GET, POST, PUT, PATCH, DELETE, OPTIONS, HEAD, TRACE, CONNECT
     * 글 등록
     * POST Method
     */
    @PostMapping("/posts")
  /*
  방법 1
  public String post(@RequestParam String title, @RequestParam String content) {
        log.info("title={}, content={}", title, content);
  방법 2
  public String post(@RequestParam Map<String, String> params)

  방법 3) class 따로 생성
  */
    public Map<String, String> post(@RequestBody @Valid PostCreate params) {
        log.info("params={}", params);
        postService.write(params);
        /**
         * BindingResult를 받는 방식
         * 1. 매번 메서드 마다 값을 검증해야한다.
         *      > 개발자가 까먹을 수 있다. -> 검증 부분에서 버그 발생 여지가 높다.
         *      > 지겹다.
         * 2. 응답값에 HashMap -> 응답 클래스를 만들어주는게 좋다.
         * 3. 여러개의 에러 처리 힘들다
         * 4. 세 번 이상의 반복적 작업은 피해야한다. -> 코드 && 개발 프로세스에 관한 모든 것. -> 자동화 고려해보기.
         */
      /*  if (result.hasErrors()) {
            List<FieldError> fieldErrors = result.getFieldErrors();
            FieldError firstFieldError = fieldErrors.get(0);
            String fieldName = firstFieldError.getField();  // title
            String errorMessage = firstFieldError.getDefaultMessage();  // 에러메시지..

            Map<String, String> error = new HashMap<>();
            error.put(fieldName, errorMessage);
            return error;
        }*/

        return Map.of();
    }
     /* if문으로 데이터 검증하면 안되는 이유
      if (title == null || title.equals("")) {
            // 1. 빡세다. (노가다)
            // 2. 개발팁 -> 무언가 3번 이상 반복작업을 할 때, 내가 뭔가 잘못하고 있는건 아닐지 의심한다.
            // 3. 누락 가능성
            // 4. 생각보다 검증해야 할게 많다. (꼼꼼하지 않을 수 있다.)
            // 5. 간지가 안난다. 뭔가 개발자스럽지 않다.

            // {"title":"         "}
            // {"title":"         .. 수십억 글자"} // 생각보다 검증해야 할 부분이 꽤 많다. 꼼꼼하게 해야한다.

            throw new Exception("타이틀 값이 없어요!");
        }
        if (content == null || content.equals("")) {
            throw new Exception("컨텐츠 값이 없어요!");
        }*/

}
