package com.netcracker.edu.backend.controller;

import com.netcracker.edu.backend.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/api/file")
public class FileController {

    @Autowired
    private PostService postService;

    @PostMapping(value = "/upload",  produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity <String> upload(@RequestParam(value = "file", required = false) MultipartFile file){
        String name = postService.uploadFile(file);
        return ResponseEntity.ok(name);
    }

    @GetMapping(value = "/getfile/{fileName}")
    public MultipartFile getFile(@PathVariable String fileName){
        return postService.getFile(fileName);
    }
}
