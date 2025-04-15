package com.fourirbnb.common.security;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

public class AuthenticatedUserArgumentResolver implements HandlerMethodArgumentResolver {

  @Override
  public boolean supportsParameter(MethodParameter parameter) {
    return parameter.hasParameterAnnotation(AuthenticatedUser.class)
        && parameter.getParameterType().equals(UserInfo.class);
  }

  @Override
  public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
      NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
    // ArgumentResolver 위치 webRequest
    HttpServletRequest request = (HttpServletRequest) webRequest.getNativeRequest();

    String userId = request.getHeader("X-User-Id");
//    Role role = Role.valueOf(request.getParameter("role"));
    if (userId == null || userId.isEmpty()) {
      throw new MissingRequestHeaderException("X-User-Id", parameter);
    }
    //id long
    return new UserInfo(Long.parseLong(userId));
  }
}
