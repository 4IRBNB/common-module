package com.fourirbnb.common.config;

import com.fourirbnb.common.FeignInterceptor.NoAuthFeignClient;
import com.fourirbnb.common.exception.UnauthorizedAccessException;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Configuration
public class FeignInterceptorConfig implements RequestInterceptor {


  @Override
  public void apply(RequestTemplate requestTemplate) {
    //클래스 추출
    Class<?> clazz = requestTemplate.feignTarget().type();

    //클래스 내 해당 Annotation 존재시 true - 외부 API
    if(clazz.isAnnotationPresent(NoAuthFeignClient.class)){
      return;
    }

    //내부 api 시 헤더추가 - Annotation X
    ServletRequestAttributes attrs =
        (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();

    if (attrs == null) {
      throw new UnauthorizedAccessException("인증정보 없음");
    }
    HttpServletRequest request = attrs.getRequest();
    String userId=request.getHeader("X-User-Id");
    String role=request.getHeader("X-User-Role");
    requestTemplate.header("X-User-Id", userId);
    requestTemplate.header("X-User-Role", role);
  }
}
