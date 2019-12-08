package com.netcracker.edu.backend.service.impl;

import com.netcracker.edu.backend.entity.Post;
import com.netcracker.edu.backend.repository.PostRepository;
import com.netcracker.edu.backend.repository.UserRepository;
import com.netcracker.edu.backend.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
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

    @Override
    public List <Post> findAll() {
        return postRepository.findAll();
    }

    public Post findById(long id) {
        return postRepository.findById(id);
    }

    @Override
    public Post save(Post post) {
        return postRepository.save(post);
    }

    @Override
    public void delete(long id) {
        postRepository.deleteById(id);
    }

    @Override
    public List <Post> findByUserId(Long userId) {
        return postRepository.findByUserId(userId);
    }

    @Override
    public Post createPost(Long userId, Post post){
        return userRepository.findById(userId).map(user -> {
            post.setUser(user);
            return postRepository.save(post);
        }).orElseThrow(() -> new ResourceNotFoundException("UserId " + userId + " not found"));
    }

    @Override
    public String uploadFile(MultipartFile file) {
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) {
            uploadDir.mkdir();
        }
        String uuidFile = UUID.randomUUID().toString();
        String resultFilename = uuidFile + "." + file.getOriginalFilename();
        String fileNameWithPath = uploadPath + "/" + resultFilename;
        try {
            file.transferTo(new File(fileNameWithPath));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return resultFilename;
    }


    public MultipartFile getFile (String fileName) {

        BufferedImage originalImage = null;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            originalImage = ImageIO.read(new File(uploadPath + "/" + fileName));
//            originalImage = ImageIO.read(new File(uploadPath + "/" + fileName));

            ImageIO.write( originalImage, "jpg", baos );
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

//        FileInputStream input = new FileInputStream(originalImage);
//        MultipartFile multipartFile = new MockMultipartFile("fileItem",
//                originalImage.getName(), "image/png", IOUtils.toByteArray(input));

        return multipartFile;

    }
}
