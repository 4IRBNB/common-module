package com.fourirbnb.common.config;

import jakarta.servlet.http.HttpServletRequest;
import java.util.Optional;
import org.springframework.data.domain.AuditorAware;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public class AuditorAwareImpl implements AuditorAware<Long> {

  @Override
  public Optional<Long> getCurrentAuditor() {

    //현재 스레드에 바인딩된 요청컨텍스트를 ServletRequestAttributes로 다운캐스팅해 가져옴
    ServletRequestAttributes attributes =
        (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();

    // 바인딩이 null 일때
    if (attributes == null) {
      return Optional.empty();
    }
    //ServletRequestAttributes 내 HttpServletRequest를 꺼냄
    HttpServletRequest request = attributes.getRequest();

    //HttpServletRequest의 헤더를 꺼냄
    String userIdHeader = request.getHeader("X-User-Id");

    //헤더 null 혹은 비어있음
    if (userIdHeader == null || userIdHeader.isBlank()) {
      return Optional.empty();
    }

    try {
      //정상시 반환
      return Optional.of(Long.parseLong(userIdHeader));
    } catch (NumberFormatException e) {
      // 잘못된 값 들어올 경우
      return Optional.empty();
    }
  }
}