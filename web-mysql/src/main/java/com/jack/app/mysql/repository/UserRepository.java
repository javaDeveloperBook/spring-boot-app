package com.jack.app.mysql.repository;

import com.jack.app.mysql.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @description:
 * @author: JackWu
 * @create: 2018-08-06 13:50
 **/
public interface UserRepository extends JpaRepository<User,Long> {

    User findByEmail(String email);

    User findByUserName(String userName);

}
