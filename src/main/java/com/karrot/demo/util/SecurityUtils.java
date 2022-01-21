package com.karrot.demo.util;

import com.karrot.demo.domain.user.Account;
import com.karrot.demo.security.service.AccountAdapter;
import com.karrot.demo.web.dto.user.UserSessionDto;
import org.springframework.security.access.AuthorizationServiceException;
import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityUtils {

    public static UserSessionDto getLoginUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if ("anonymousUser".equals(principal)) {
            return null;
        }
        return toDto(principal);
    }

    public static Long getLoginUserId() {
        UserSessionDto loginUser = getLoginUser();
        return loginUser != null ? loginUser.getId() : null;
    }

    /**
     * 현재 로그인한 사용자가 userId가 아니면 AuthorizationServiceException 을 던진다.
     */
    public static void checkUser(Long userId) throws AuthorizationServiceException {
        if (!userId.equals(getLoginUserId())) {
            throw new AuthorizationServiceException(userId +" 는 해당 영역에 접근할 수 없습니다.");
        }
    }
    private static UserSessionDto toDto(Object principal){
        AccountAdapter adapter = (AccountAdapter) principal;
        Account account = adapter.getAccount();
        return UserSessionDto.builder()
                .id(account.getId())
                .email(account.getEmail())
                .username(account.getUsername())
                .phone(account.getPhone())
                .nickname(account.getNickname())
                .image(account.getImage())
                .build();
    }
}
