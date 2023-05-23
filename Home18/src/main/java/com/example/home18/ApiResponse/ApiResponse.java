package com.example.home18.ApiResponse;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ApiResponse {
    private boolean success;
    private String message;
    private Object data;
}
