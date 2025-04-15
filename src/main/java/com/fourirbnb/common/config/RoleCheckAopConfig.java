package com.fourirbnb.common.config;

import com.fourirbnb.common.exception.UnauthorizedAccessException;
import com.fourirbnb.common.security.RoleCheck;
import jakarta.servlet.http.HttpServletRequest;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

//프록시 사용을 위한 설정
@Aspect
@Configuration
public class RoleCheckAopConfig {

  @Around("@annotation(roleCheck)")
  public Object checkRole(ProceedingJoinPoint joinPoint, RoleCheck roleCheck) throws Throwable {
    //AOP RequestContextHolder
    ServletRequestAttributes attrs =
        (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();

    if (attrs == null) {
      throw new UnauthorizedAccessException("인증정보 없음");
    }
    //헤더에서 role 꺼내기
    HttpServletRequest request = attrs.getRequest();

    String role = request.getHeader("X-User-Role");

    String requiredRole = roleCheck.value();

    //role 검사
    if (!requiredRole.equals(role)) {
      throw new UnauthorizedAccessException("권한이 없습니다");
    }
    //통과시
    return joinPoint.proceed();
  }
}
