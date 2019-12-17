package com.netcracker.edu.fapi.converter;

import com.netcracker.edu.fapi.UIModels.UIComment;
import com.netcracker.edu.fapi.models.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class CommentToCommentUIModel implements Converter <Comment, UIComment> {

    @Autowired
    private UserToUserUIModel userToUserUIModel;

    @Override
    public UIComment convert(Comment comment) {
        UIComment uiComment = new UIComment();
        uiComment.setId(comment.getId());
        uiComment.setContent(comment.getContent());
        uiComment.setUiUser(userToUserUIModel.convert(comment.getUser()));
        uiComment.setTimeCreation(comment.getTimeCreation());
        return uiComment;
    }

    public List <UIComment> convertList(List<Comment> comments){
        List<UIComment> uiComments = comments.stream().map(this::convert).collect(Collectors.toList());
        return uiComments;
    }
}
