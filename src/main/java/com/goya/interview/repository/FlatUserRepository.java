package com.goya.interview.repository;

import com.goya.interview.entity.FlatUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FlatUserRepository extends JpaRepository<FlatUserEntity, Integer> {}
