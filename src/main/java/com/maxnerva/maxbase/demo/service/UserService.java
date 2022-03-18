package com.maxnerva.maxbase.demo.service;

import com.maxnerva.maxbase.demo.pojo.dto.UserLoginDTO;
import com.maxnerva.maxbase.demo.pojo.dto.UserLogoutDTO;
import com.maxnerva.maxbase.demo.pojo.vo.UserAccessTokenVO;

/**
 * @author Shengxiang Xu
 * @date 3/18/2022
 */
public interface UserService {

    UserAccessTokenVO login(UserLoginDTO userLoginDTO);

    void logout(UserLogoutDTO userLogoutDTO);

}
