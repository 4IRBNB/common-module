package com.fourirrbnb.common.response;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@Builder
public class BaseResponse<D> {

    private D data;
    private Pagination pagination;
    private String message;
    private int status;

    public static <D> BaseResponse<D> SUCCESS(D data,String message) {

        return BaseResponse.<D>builder()
                .data(data)
                .message(message)
                .status(HttpStatus.OK.value())
                .build();
    }
    // 커스텀 상태
    public static <D> BaseResponse<D> SUCCESS(D data,String message, int status) {

        return BaseResponse.<D>builder()
            .data(data)
            .message(message)
            .status(status)
            .build();
    }

    public static <D> BaseResponse<D> SUCCESS(D data,String message ,Pagination pagination) {
        return BaseResponse.<D>builder()
                .data(data)
                .pagination(pagination)
                .message(message)
                .status(HttpStatus.OK.value())
                .build();
    }

    public static <D> BaseResponse<D> FAIL(String message, int status) {

        return BaseResponse.<D>builder()
                .message(message)
                .status(status)
                .build();
    }
}