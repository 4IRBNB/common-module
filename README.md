# 🧱 4IRBNB 공통 모듈 (common-module)

모든 서비스에서 공통적으로 사용하는 DTO, 예외 처리, 응답 포맷, 엔티티 등의 기능을 제공하는 모듈입니다.  
해당 모듈은 GitHub Packages를 통해 배포되며, 각 서비스에서 외부 의존성으로 불러와 사용합니다.

---

## ✨ 주요 기능

### ✅ 공통 응답 포맷

- `BaseResponse<T>`: 성공/실패 응답을 통일된 형태로 제공  
- `Pagination`: 페이징 응답에 필요한 정보 포함

```json
{
  "data": {...},
  "pagination": {
    "totalPages": 2,
    "totalElements": 40,
    "currentPage": 0,
    "currentElements": 10
  },
  "description": "SUCCESS",
  "status": "200 OK"
}
```

---

### 🚨 예외 처리 공통화

`@RestControllerAdvice` 기반의 전역 예외 처리기(`GlobalExceptionHandler`)를 제공합니다.  
서비스에서 정의한 예외는 아래와 같이 매핑됩니다:

| 예외 클래스                       | 상태 코드 | 설명 |
|-----------------------------------|-----------|------|
| `DeletedDataAccessException`      | 400       | 삭제된 리소스 접근 |
| `DuplicateResourceException`      | 409       | 중복 데이터 생성 |
| `ResourceNotFoundException`       | 404       | 존재하지 않는 리소스 접근 |
| `UnauthorizedAccessException`     | 401       | 인증되지 않은 사용자 |
| `OwnershipMismatchException`      | 403       | 리소스 소유자 불일치 |
| `InvalidParameterException`       | 400       | 잘못된 요청 파라미터 |
| `OperationNotAllowedException`    | 400/403   | 허용되지 않은 작업 |
| `InternalServerException`         | 500       | 서버 내부 오류 |
| `OutOfStockException`             | 400       | 재고 부족으로 인한 주문 실패 |

---

### 🧩 공통 엔티티

- `BasicEntity`: 생성/수정/삭제 시간 및 사용자 ID 추적  
  - `@CreatedDate`, `@CreatedBy`, `@LastModifiedDate`, `@LastModifiedBy`  
  - 소프트 삭제 지원 (`deletedAt`, `deletedBy` 필드 포함)

---

## ⚙️ 사용법

### 1. 의존성 추가 (서비스 프로젝트의 `build.gradle`)

```groovy
repositories {
    mavenCentral()
    maven {
        url = uri("https://maven.pkg.github.com/4IRBNB/common-module")
        credentials {
          username = project.findProperty("gpr.user") // 이전처럼 키값은 gradle.properties 로 관리
          password = project.findProperty("gpr.key") // user - 개인 사용자(닉네임) / key - 개인 토큰
		}
    }
}

dependencies {
    implementation 'com.fourirbnb:common:1.1.0'
}
```

※ `gradle.properties`에 GitHub 인증 정보 추가 필요:

```properties
gpr.user=GITHUB_USERNAME_OR_ORG
gpr.key=YOUR_PERSONAL_ACCESS_TOKEN
```

---

## 🚀 배포 방법 (관리자용)

```bash
./gradlew publish
jar tf build/libs/common-1.1.1.jar  # JAR 파일 내 클래스 확인
```

---

## 📌 버전 관리 전략

시맨틱 버전(SemVer)을 따릅니다.  
버전 형식: `MAJOR.MINOR.PATCH`

| 항목 | 설명 |
|------|------|
| MAJOR | 하위 호환이 깨지는 변경 |
| MINOR | 기능 추가 (호환 유지) |
| PATCH | 버그 수정, 사소한 개선 |

---

## 📄 변경 로그

[CHANGELOG.md](./CHANGELOG.md) 참고

---

## 🙋 문의

이슈 또는 개선 제안은 GitHub Issues를 통해 남겨주세요.
