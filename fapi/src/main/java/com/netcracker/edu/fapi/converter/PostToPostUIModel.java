package com.netcracker.edu.fapi.converter;

import com.netcracker.edu.fapi.UIModels.UIPost;
import com.netcracker.edu.fapi.models.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class PostToPostUIModel implements Converter<Post, UIPost> {

    @Autowired
    private CommentToCommentUIModel commentToCommentUIModel;

    @Override
    public UIPost convert(Post post) {
        UIPost uiPost = new UIPost();
        uiPost.setId(post.getId());
        uiPost.setDescription(post.getDescription());
        uiPost.setTags(post.getTags().toString());
        uiPost.setCountLike(post.getLikes().size());
        uiPost.setFilename(post.getFilename());
        uiPost.setUserLogin(post.getUser().getLogin());
        uiPost.setUserId(post.getUser().getId());
        uiPost.setUiComments(commentToCommentUIModel.convertList(post.getComments()));
        return uiPost;
    }
}
