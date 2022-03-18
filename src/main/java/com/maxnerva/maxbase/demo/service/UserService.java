package com.maxnerva.maxbase.demo.service;

import com.maxnerva.maxbase.demo.pojo.dto.UserLoginDTO;
import com.maxnerva.maxbase.demo.pojo.dto.UserLogoutDTO;
import com.maxnerva.maxbase.demo.pojo.vo.UserAccessTokenVO;

/**
 * 用户服务
 *
 * @author Shengxiang Xu
 * @date 3/17/2022
 */
public interface UserService {

    UserAccessTokenVO login(UserLoginDTO userLoginDTO);

    void logout(UserLogoutDTO userLogoutDTO);

}
