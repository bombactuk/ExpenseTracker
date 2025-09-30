package com.expense.tracker.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class JwtRequestDto {

    private String username;
    private String password;

}
