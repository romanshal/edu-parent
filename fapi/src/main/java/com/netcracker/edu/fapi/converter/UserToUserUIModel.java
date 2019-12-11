package com.netcracker.edu.fapi.converter;

import com.netcracker.edu.fapi.UIModels.UIUser;
import com.netcracker.edu.fapi.models.User;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class UserToUserUIModel implements Converter <User, UIUser> {

    @Override
    public UIUser convert(User user) {
        UIUser uiUser = new UIUser();
        uiUser.setId(user.getId());
        uiUser.setLogin(user.getLogin());
        uiUser.setRole(user.getRole().getRoleName());
        return uiUser;
    }
}
