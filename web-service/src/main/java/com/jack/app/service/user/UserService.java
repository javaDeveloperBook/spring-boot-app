package com.jack.app.service.user;


import com.jack.app.mysql.domain.User;

import java.util.List;

public interface UserService {

    List<User> getAll();

    void save(User user);

    void update(User user);

    void delete(Long id);

}
