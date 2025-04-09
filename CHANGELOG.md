# Changelog

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
