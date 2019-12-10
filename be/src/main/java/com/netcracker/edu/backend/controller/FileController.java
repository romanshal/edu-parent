package com.netcracker.edu.backend.controller;

import com.netcracker.edu.backend.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
@RequestMapping("/api/file")
public class FileController {

    @Autowired
    private PostService postService;

    @Value("${upload.path}")
    private String uploadPath;

    @PostMapping(value = "/upload",  produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity <String> upload(@RequestParam(value = "file", required = false) MultipartFile file){
        String name = postService.uploadFile(file);
        return ResponseEntity.ok(name);
    }

    @GetMapping(value = "/getFile/{fileName}")
    public void getFile(HttpServletRequest request,
                        HttpServletResponse response,
                        @PathVariable String fileName) {

        Path file = Paths.get(uploadPath, fileName);
        if (Files.exists(file)) {
            response.setContentType("image/jpeg");
            response.addHeader("Content-Disposition", "attachment; filename=" + fileName);
            try {
                Files.copy(file, response.getOutputStream());
                response.getOutputStream().flush();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}
