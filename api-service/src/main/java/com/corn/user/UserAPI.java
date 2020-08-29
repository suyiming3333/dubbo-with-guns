package com.corn.user;


import com.corn.user.vo.UserInfoModel;
import com.corn.user.vo.UserModel;

public interface UserAPI {

    int login(String username, String password);

    boolean register(UserModel userModel);

    boolean checkUsername(String username);

    UserInfoModel getUserInfo(int uuid);

    UserInfoModel updateUserInfo(UserInfoModel userInfoModel);

}
