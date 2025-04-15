# Changelog
## 현재버전 1.4.0

## [1.4.0] - 2025-04-15

### Added
- `@NoAuthFeignClient` 커스텀 어노테이션 도입
    - 인증 정보가 불필요한 FeignClient (예: 회원가입, 로그인, 외부 API 등)에 명시적으로 선언
    - 불필요한 헤더 전송을 방지하여 보안성과 명확한 책임 분리 확보

- `FeignInterceptor` 자동 구성 기능 추가
    - 내부 API 요청에만 `X-User-Id`, `X-User-Role` 헤더를 자동으로 포함
    - `@NoAuthFeignClient` 어노테이션이 붙은 경우 헤더 전송을 생략하도록 분기 처리
    - Spring Boot 3 기준 `AutoConfiguration.imports` 등록 방식 사용

## [1.3.1] - 2025-04-15

### Fixed
- `1.3.0` 버전에서 누락된 `System.out.println()` 출력 제거
    - 공통 모듈 내 `UserInfo` 관련 테스트 출력물이 의도치 않게 포함되어 로그에 `"1"`이 출력되는 문제 수정
    - 동일 버전 반복 배포로 인해 캐시된 `.jar` 파일이 계속 참조되어 발생
    - 이후 동일 버전 재배포를 피하고, 반드시 **패치 버전 증가**로 배포하도록 정책 변경

## [1.3.0] - 2025-04-15

### Added
- `@AuthenticatedUser` 어노테이션 및 `AuthenticatedUserArgumentResolver` 구현
    - `HttpServletRequest`의 `X-User-Id` 헤더 값을 자동 주입하는 ArgumentResolver 구현
  - Spring Boot 3 기준 `AutoConfiguration.imports` 등록 방식 사용

- `@RoleCheck` 어노테이션 및 AOP 기반 인가 처리 (`RoleCheckAspect`)
    - 메서드 단위로 역할(Role)에 따른 인가 제어 가능
    - 헤더 기반 권한 정보(`X-User-Role`)를 기준으로 비교

- `RoleCheckAopConfig` / `AuthenticatedUserConfig` 설정 클래스
    - Spring Boot 3 기준 `AutoConfiguration.imports` 등록 방식 사용
  
- `UserInfo` DTO 추가
    - 인증된 사용자 정보를 담기 위한 공용 데이터 클래스 (확장 가능 구조)

## [1.2.4] - 2025-04-14

### Added
- `AuditorAwareImpl` 추가: `RequestContextHolder`를 통해 `HttpServletRequest`의 헤더에서 사용자 ID를 꺼내 자동으로 감사 필드(`@CreatedBy`, `@LastModifiedBy`)에 적용되도록 구성

### Changed
- `JpaConfig`에 `@ConditionalOnProperty`와 `@AutoConfiguration` 설정 추가  
  → 공통 모듈을 의존하는 서비스 모듈에서 `common.jpa.enabled=true` 설정만 추가하면, 자동으로 JPA 감사 관련 빈이 등록되도록 개선

## [1.2.3] - 2025-04-13

### Add
- QueryDSL 의존성 추가 - `QBasicEntity` 생성 목적

### Changed
- 공통 엔티티 클래스명 `BasicEntity` → `BaseEntity`로 변경 (팀원 요청에 따라 네이밍 통일 목적)


## [1.2.2] - 2025-04-12

### Fixed
- 잘못 설정된 `groupId` 오타 수정 (`com.fourirrbnb` → `com.fouribnb`)
- 외부 배포(`groupId = com.fourirbnb`)는 문제 없었으며, 내부 모듈 구조 일관성 확보 목적
- 기존 버전과 호환되며, 기능 변경 없음

## [1.2.1] - 2025-04-10

### Changed
- `BaseResponse` 구조 개선
    - `description` → `message`로 필드 명 변경
    - `status`를 `int` 타입으로 유지하고 `HttpStatus.value()` 기반 처리
    - 응답 오버로딩 메서드 정리 및 통일
- `GlobalExceptionHandler` 구성 및 커스텀 예외 매핑 정리

## [1.1.1] - 2025-04-07
### Added
- CHANGELOG.md, README.md 문서 추가
- Gradle credential 처리 방식 단일화
    - 기존 `System.getenv(...)` 방식 제거
    - `project.findProperty(...)` 방식만 지원하도록 변경
    - `.gradle.properties` 또는 `-P` 파라미터를 통해 일관된 설정 가능
    - 환경 변수 방식의 관리 어려움 및 빌드 환경 불안정성 해소 목적

## [1.1.0] - 2025-04-05
### Added
- `config.JpaConfig`: JPA 감사(Auditing) 관련 설정 클래스 추가
- `domain.BasicEntity`: 공통 엔티티 기본 필드 (`createdAt`, `updatedAt`) 포함한 베이스 엔티티 클래스 추가
- `exception` 패키지:
    - `GlobalExceptionHandler`: 전역 예외 처리 핸들러 추가
    - 커스텀 예외 클래스 추가:
        - `DeletedDataAccessException`
        - `DuplicateResourceException`
        - `InternalServerException`
        - `InvalidParameterException`
        - `OperationNotAllowedException`
        - `OutOfStockException`
        - `OwnershipMismatchException`
        - `ResourceNotFoundException`
        - `UnauthorizedAccessException`
- `response` 패키지:
    - `BaseResponse`: 공통 응답 포맷 클래스 추가
    - `Pagination`: 페이징 응답 정보를 담는 클래스 추가

## [1.0.0] - 2025-04-05
### Added
- 초기 프로젝트 세팅
- Gradle 설정, Github Packages 배포 설정 포함
