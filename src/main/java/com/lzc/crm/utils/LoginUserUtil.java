package com.lzc.crm.utils;

import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;


public class LoginUserUtil {

    /**
     * 从cookie中获取userId
     *
     *
     * @param request
     * @return int
     */
    public static int releaseUserIdFromCookie(HttpServletRequest request) {
        String userIdString = CookieUtil.getCookieValue(request, "userIdStr");
        //判断是否为空
        if (StringUtils.isBlank(userIdString)) {
            return 0;
        }
        //解密
        Integer userId = UserIDBase64.decoderUserID(userIdString);
        return userId;
    }
}
