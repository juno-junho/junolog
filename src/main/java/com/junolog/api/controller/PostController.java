package com.junolog.api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
/** SSR -> jsp
 *      // html rendering
 *  SPA -> vue, react
 *      // javascript로 화면, server와의 통신은 api로만. (JSON)
 * vue + SSR = nuxt.js
 * react + SSR = next.js
 */
@RestController
public class PostController {

    @GetMapping("/posts")
    public String get() {
        return "Hello World";
    }

}
