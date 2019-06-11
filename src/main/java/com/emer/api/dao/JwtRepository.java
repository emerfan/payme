package com.emer.api.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.emer.api.model.JwtUser;
@Repository
@Transactional
public interface JwtRepository extends JpaRepository<JwtUser, Long> {

}
