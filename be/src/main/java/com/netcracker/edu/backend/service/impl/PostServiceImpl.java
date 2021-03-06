package com.netcracker.edu.backend.service.impl;

import com.netcracker.edu.backend.entity.Post;
import com.netcracker.edu.backend.repository.CommentRepository;
import com.netcracker.edu.backend.repository.LikeRepository;
import com.netcracker.edu.backend.repository.PostRepository;
import com.netcracker.edu.backend.repository.UserRepository;
import com.netcracker.edu.backend.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

@Service
public class PostServiceImpl implements PostService {

    @Value("${upload.path}")
    private String uploadPath;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private LikeRepository likeRepository;

    @Override
    public List <Post> findAll(int page) {
        Pageable pageable = new PageRequest(page, 8, Sort.by("id").descending());
        Page <Post> postPage = postRepository.findAll(pageable);
        return postRepository.findAll();
    }

    public Post findById(long id) {
        return postRepository.findById(id);
    }

    @Override
    public Post save(Post post) {
        post.setTimeCreation(new Timestamp(System.currentTimeMillis()));
        return postRepository.save(post);
    }

    @Override
    public void delete(long id) {
        likeRepository.deleteAllLikes(id);
        commentRepository.deleteAll(id);
        postRepository.deleteById(id);
    }

    @Override
    public List <Post> findByUserId(int page, Long userId) {
        Pageable pageable = new PageRequest(page, 8, Sort.by("id").descending());
        Page <Post> postPage = postRepository.findByUserId(pageable, userId);
        return postPage.getContent();
//        return postRepository.findByUserId(userId);
    }

    @Override
    public String uploadFile(MultipartFile file) {
        File uploadDir = new File(uploadPath);
        if(!uploadDir.exists()){
            uploadDir.mkdir();
        }
        String uuidFile = UUID.randomUUID().toString();
        String resultFilename = uuidFile + "." + file.getOriginalFilename();
        String fileNameWithPath = uploadPath + "//" + resultFilename;
        try {
            file.transferTo(new File(fileNameWithPath));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return resultFilename;
    }


    @Override
    public MultipartFile getFile(String fileName) {

        BufferedImage originalImage = null;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            originalImage = ImageIO.read(new File(uploadPath + "//" + fileName));

            ImageIO.write(originalImage, "jpg", baos);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                baos.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        MultipartFile multipartFile = new MockMultipartFile(fileName, baos.toByteArray());
        return multipartFile;
    }
}
