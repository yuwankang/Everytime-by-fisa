package com.fisa.land.fisaland.common.respository;

import java.util.Optional;

import com.fisa.land.fisaland.common.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fisa.land.fisaland.common.type.LoginProvider;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long>{

	Optional<UserEntity> findByUserId(Long userId);

	Optional<UserEntity> findByEmail(String email);

	void deleteByUserId(Long userId);

	Optional<UserEntity> findBySocialIdAndLoginProvider(String socialId, LoginProvider loginProvider);
}