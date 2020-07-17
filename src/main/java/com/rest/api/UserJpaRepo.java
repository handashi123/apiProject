package com.rest.api;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rest.api.entity.User;

public interface UserJpaRepo extends JpaRepository<User, Long> {

}
