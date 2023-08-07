package com.junolog.api.service;

import com.junolog.api.domain.Post;
import com.junolog.api.repository.PostRepository;
import com.junolog.api.request.PostCreate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    public void write(PostCreate postCreate) {

        Post post = new Post(postCreate.getTitle(), postCreate.getContent());
        postRepository.save(post);
    }

}
