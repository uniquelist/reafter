package com.feiyi.service;


import com.feiyi.domain.User;

public interface TokenService {
    public String getToken(User user);
}
