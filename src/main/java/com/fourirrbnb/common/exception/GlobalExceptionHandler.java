package com.fourirrbnb.common.exception;


import com.fourirrbnb.common.response.BaseResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // ❌ 삭제된 데이터 접근
    @ExceptionHandler(DeletedDataAccessException.class)
    public BaseResponse<String> handleDeletedDataAccess(DeletedDataAccessException ex) {
        return BaseResponse.FAIL(ex.getMessage(),HttpStatus.BAD_REQUEST.toString());
    }

    // ❌ 존재하지 않는 데이터 접근
    @ExceptionHandler(ResourceNotFoundException.class)
    public BaseResponse<String> handleResourceNotFound(ResourceNotFoundException ex) {
        return BaseResponse.FAIL(ex.getMessage(),HttpStatus.NOT_FOUND.toString());
    }

    // ❌ 이미 존재하는 데이터 생성 (409)
    @ExceptionHandler(DuplicateResourceException.class)
    public BaseResponse<String> handleDuplicateResource(DuplicateResourceException ex) {
        return BaseResponse.FAIL(ex.getMessage(),HttpStatus.CONFLICT.toString());
    }

    // ❌ 권한 없음 (401)
    @ExceptionHandler(UnauthorizedAccessException.class)
    public BaseResponse<String> handleUnauthorizedAccess(UnauthorizedAccessException ex) {
        return BaseResponse.FAIL(ex.getMessage(),HttpStatus.UNAUTHORIZED.toString());
    }

    // ❌ 소유자가 아닌 사용자의 접근 (403)
    @ExceptionHandler(OwnershipMismatchException.class)
    public BaseResponse<String> handleOwnershipMismatch(OwnershipMismatchException ex) {
        return BaseResponse.FAIL(ex.getMessage(),HttpStatus.FORBIDDEN.toString());
    }

    // ❌ 잘못된 요청 파라미터 (400)
    @ExceptionHandler(InvalidParameterException.class)
    public BaseResponse<String> handleInvalidParameter(InvalidParameterException ex) {
        return BaseResponse.FAIL(ex.getMessage(),HttpStatus.BAD_REQUEST.toString());
    }

    // ❌ 수행할 수 없는 작업 요청 (400 또는 403)
    @ExceptionHandler(OperationNotAllowedException.class)
    public BaseResponse<String> handleOperationNotAllowed(OperationNotAllowedException ex) {
        return BaseResponse.FAIL(ex.getMessage(),HttpStatus.BAD_REQUEST.toString());
    }

    // ❌ 서버 내부 오류 (500)
    @ExceptionHandler(InternalServerException.class)
    public BaseResponse<String> handleInternalServerError(InternalServerException ex) {
        return BaseResponse.FAIL(ex.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR.toString());
    }

    // ❌ 인증은 되었지만, 권한이 부족한 경우 (403)- 의존성 무거워져서 일단 제외
//    @ExceptionHandler(AccessDeniedException.class)
//    public BaseResponse<String> handleAccessDenied(AccessDeniedException ex) {
//        return BaseResponse.fail(HttpStatus.FORBIDDEN, "접근 권한이 없습니다.");
//    }
    
}

