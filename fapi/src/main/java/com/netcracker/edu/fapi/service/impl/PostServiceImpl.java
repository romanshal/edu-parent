package com.netcracker.edu.fapi.service.impl;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.netcracker.edu.fapi.models.Post;
import com.netcracker.edu.fapi.models.User;
import com.netcracker.edu.fapi.service.PostService;
import org.apache.catalina.connector.Request;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.*;
import org.springframework.http.converter.ByteArrayHttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
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
    public List <Post> getAll(int page) {
        RestTemplate restTemplate = new RestTemplate();
        Post[] posts = restTemplate.getForObject(backendServerUrl + "/api/post?page="+page, Post[].class);
        return posts == null ? Collections.emptyList() : Arrays.asList(posts);
    }

    @Override
    public Post savePost(MultipartFile file,String description, String login) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        MultiValueMap <String, Object> body = new LinkedMultiValueMap <>();
        body.add("file", new FileSystemResource(convert(file)));

        HttpEntity <MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);

        RestTemplate restTemplate = new RestTemplate();
        String filePath = restTemplate.postForEntity(backendServerUrl + "/api/file/upload", requestEntity, String.class).getBody();
////        String filePath = restTemplate.exchange(backendServerUrl + "/api/file/upload", HttpMethod.POST, requestEntity, String.class).getBody();

        User user = restTemplate.getForObject(backendServerUrl + "/api/user/login/" + login, User.class);

        Post post = new Post(description,filePath, user);
        return restTemplate.postForEntity(backendServerUrl + "/api/post", post, Post.class).getBody();
    }

    @Override
    public List<Post> getPostsByUserId(int page, Long id) {
        RestTemplate restTemplate = new RestTemplate();
        Post[] postsResponse = restTemplate.getForObject(backendServerUrl + "/api/post/user/" + id+"?page="+page, Post[].class);
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
    public void getFile(String fileName, HttpServletResponse response) {
        HttpHeaders headers = new HttpHeaders();
        List<MediaType> acceptableMediaTypes = new ArrayList();

        acceptableMediaTypes.add(MediaType.IMAGE_JPEG);
        acceptableMediaTypes.add(MediaType.APPLICATION_OCTET_STREAM);

        headers.setAccept(acceptableMediaTypes);

        HttpEntity<Request> httpEntity = new HttpEntity(headers);
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new ByteArrayHttpMessageConverter());
        ResponseEntity <byte[]> entity = restTemplate.exchange(backendServerUrl + "/api/file/getFile/" + fileName, HttpMethod.GET,
                httpEntity, byte[].class);
        try (OutputStream os = response.getOutputStream()) {
            os.write(entity.getBody());
        } catch (Exception e) {
        }
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
