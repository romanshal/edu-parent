package com.netcracker.edu.fapi.service.impl;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.netcracker.edu.fapi.models.Post;
import com.netcracker.edu.fapi.models.User;
import com.netcracker.edu.fapi.service.PostService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
@JsonIgnoreProperties(ignoreUnknown = true)
public class PostServiceImpl implements PostService {

    private static final Logger LOGGER = LogManager.getLogger(PostServiceImpl.class);

    @Value("${backend.server.url}")
    private String backendServerUrl;

    @Override
    public List <Post> getAll() {
        RestTemplate restTemplate = new RestTemplate();
        Post[] posts = restTemplate.getForObject(backendServerUrl + "/api/post", Post[].class);
        return posts == null ? Collections.emptyList() : Arrays.asList(posts);
    }

    @Override
    public Post savePost(String description, String login) {
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
//
//        MultiValueMap <String, Object> body = new LinkedMultiValueMap <>();
//        body.add("file", new FileSystemResource(convert(file)));
//
//        HttpEntity <MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);
//
        RestTemplate restTemplate = new RestTemplate();
//        String filePath = restTemplate.postForEntity(backendServerUrl + "/api/file/upload", requestEntity, String.class).getBody();
////        String filePath = restTemplate.exchange(backendServerUrl + "/api/file/upload", HttpMethod.POST, requestEntity, String.class).getBody();

        User user = restTemplate.getForObject(backendServerUrl + "/api/user/login/" + login, User.class);

        Post post = new Post(description, user);
        return restTemplate.postForEntity(backendServerUrl + "/api/post", post, Post.class).getBody();
    }

    @Override
    public List<Post> getPostsByUserId(Long id) {
        RestTemplate restTemplate = new RestTemplate();
        Post[] postsResponse = restTemplate.getForObject(backendServerUrl + "/api/post/user/" + id, Post[].class);
        return postsResponse == null ? Collections.emptyList() : Arrays.asList(postsResponse);
    }

//    @Override
//    public Post savePost(Post post) {
//        RestTemplate restTemplate = new RestTemplate();
//        return restTemplate.postForObject(backendServerUrl + "/api/post", post, Post.class);
//    }

    @Override
    public void deletePost(long id) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete(backendServerUrl + "/api/post/" + id);
    }

    @Override
    public File getPhoto(String fileName) {
        RestTemplate restTemplate = new RestTemplate();
        return convert(restTemplate.getForEntity(backendServerUrl+"/api/file/getfile/" + fileName, MultipartFile.class).getBody());
    }

    @Override
    public Post getPostById(Long id) {
        RestTemplate restTemplate = new RestTemplate();
        Post post = restTemplate.getForObject(backendServerUrl + "/api/post/" + id, Post.class);
        return post;
    }

    private static File convert(MultipartFile file)
    {
        File convertFile = new File(file.getOriginalFilename());
        try {
            convertFile.createNewFile();
            FileOutputStream fos = new FileOutputStream(convertFile);
            fos.write(file.getBytes());
            fos.close();
        } catch (IOException e) {
            LOGGER.error(e);
        }
        return convertFile;
    }

}
