package com.aitu.votingsystem.services.interfaces;

import com.aitu.votingsystem.model.User;

public interface IUserService {
    User findByUsername(String username);

    User register(User user);

    void changePassword(User user, String newPassword);
}
