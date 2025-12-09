package com.ltc.helioslessonspring.repository;

import com.ltc.helioslessonspring.model.AppUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppUserRepository extends JpaRepository<AppUserEntity, Long> {




}
