package com.maxnerva.maxbase.demo.service.impl;

import com.maxnerva.authentication.share.bean.AccessTokenVO;
import com.maxnerva.maxbase.demo.common.exception.business.BossLoginException;
import com.maxnerva.maxbase.demo.pojo.converter.UserConverter;
import com.maxnerva.maxbase.demo.pojo.dto.UserLoginDTO;
import com.maxnerva.maxbase.demo.pojo.dto.UserLogoutDTO;
import com.maxnerva.maxbase.demo.pojo.vo.UserAccessTokenVO;
import com.maxnerva.maxbase.demo.service.UserService;
import com.maxnerva.resource.client.authentication.infrastructure.remote.parameter.LoginParameter;
import com.maxnerva.resource.client.service.AuthorizationRemoteService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 用户服务实现类
 *
 * @author Shengxiang Xu
 * @date 3/17/2022
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserConverter userConverter;
    private final AuthorizationRemoteService authorizationRemoteService;

    @Override
    public UserAccessTokenVO login(UserLoginDTO userLoginDTO) {
        try {
            LoginParameter loginParameter = userConverter.toLoginParameter(userLoginDTO);
            AccessTokenVO accessTokenVO = authorizationRemoteService.login(loginParameter);

            UserAccessTokenVO userAccessTokenVO = userConverter.toUserAccessTokenVO(accessTokenVO);
            Map<String, Object> additional = accessTokenVO.getAdditional();
            if (additional != null) {
                Object expirationWarning = additional.get("EXPIRATION_WARNING");
                if (expirationWarning != null) {
                    Long expirationWarningLongValue = Long.valueOf(String.valueOf(expirationWarning));
                    userAccessTokenVO.setExpirationWarning(expirationWarningLongValue);
                }
            }
            return userAccessTokenVO;
        } catch (Exception exception) {
            // 当为客观异常时，需要日志输出，以方便定位异常具体原因
            log.error(exception.getMessage(), exception);
            throw new BossLoginException();
        }
    }

    @Override
    public void logout(UserLogoutDTO userLogoutDTO) {
        authorizationRemoteService.logout(userLogoutDTO.getAccessToken());
    }

}
