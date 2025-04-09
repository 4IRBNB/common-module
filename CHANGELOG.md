# Changelog

## [1.2.1] - 2025-04-10

### 변경 사항
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
