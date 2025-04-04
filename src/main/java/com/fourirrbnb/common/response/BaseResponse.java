package com.fourirrbnb.common.response;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@Builder
public class BaseResponse<D> {

    private D data;
    private Pagination pagination;
    private String description;
    private String status;

    public static <D> BaseResponse<D> SUCCESS(D data) {

        return BaseResponse.<D>builder()
                .data(data)
                .description("SUCCESS")
                .status("SUCCESS")
                .build();
    }

    public static <D> BaseResponse<D> SUCCESS(D data, Pagination pagination) {
        return BaseResponse.<D>builder()
                .data(data)
                .pagination(pagination)
                .description("SUCCESS")
                .status(HttpStatus.OK.toString())
                .build();
    }

    public static <D> BaseResponse<D> FAIL(String description, String status) {

        return BaseResponse.<D>builder()
                .description(description)
                .status(status)
                .build();
    }
}