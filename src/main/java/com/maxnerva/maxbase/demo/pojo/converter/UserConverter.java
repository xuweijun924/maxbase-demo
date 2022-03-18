package com.maxnerva.maxbase.demo.pojo.converter;

import com.maxnerva.authentication.share.bean.AccessTokenVO;
import com.maxnerva.maxbase.demo.pojo.dto.UserLoginDTO;
import com.maxnerva.maxbase.demo.pojo.vo.UserAccessTokenVO;
import com.maxnerva.resource.client.authentication.infrastructure.remote.parameter.LoginParameter;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * @author Shengxiang Xu
 * @date 3/18/2022
 */
@Mapper(componentModel = "spring")
public interface UserConverter {

    @Mapping(target = "grant_type", source = "grantType")
    LoginParameter toLoginParameter(UserLoginDTO userLoginDTO);

    UserAccessTokenVO toUserAccessTokenVO(AccessTokenVO accessTokenVO);

}