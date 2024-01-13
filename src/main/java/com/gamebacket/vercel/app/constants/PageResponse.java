package com.gamebacket.vercel.app.constants;


import lombok.Data;

import java.util.List;

@Data
public class PageResponse<T> {
    private final List<T> content;
    private final int totalPages;
    private final long totalElements;

    public PageResponse(List<T> content, int totalPages, long totalElements, long elements) {
        this.content = content;
        this.totalPages = totalPages;
        this.totalElements = totalElements;
    }
}
