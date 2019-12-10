package com.netcracker.edu.fapi.converter;

import com.netcracker.edu.fapi.UIModels.UILike;
import com.netcracker.edu.fapi.UIModels.UIPost;
import com.netcracker.edu.fapi.models.Like;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

public class LikeToLikeUIModel implements Converter <Like, UILike> {

    @Autowired
    private PostToPostUIModel postToPostUIModel;

    @Autowired
    private UserToUserUIModel userToUserUIModel;

    @Override
    public UILike convert(Like like) {
        UILike uiLike = new UILike();
        uiLike.setUiUser(userToUserUIModel.convert(like.getUser()));
        return uiLike;
    }
}
