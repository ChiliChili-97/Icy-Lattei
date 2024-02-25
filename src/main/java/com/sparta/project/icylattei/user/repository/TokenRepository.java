package com.sparta.project.icylattei.user.repository;

import com.sparta.project.icylattei.user.entity.Token;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TokenRepository extends JpaRepository<Token, String> {

}
