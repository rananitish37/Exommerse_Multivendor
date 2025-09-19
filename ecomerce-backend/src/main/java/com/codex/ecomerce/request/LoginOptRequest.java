package com.codex.ecomerce.request;

import com.codex.ecomerce.domain.USER_ROLE;
import lombok.Data;

@Data
public class LoginOptRequest {
    private String email;
    private String otp;
    private USER_ROLE role;
}
