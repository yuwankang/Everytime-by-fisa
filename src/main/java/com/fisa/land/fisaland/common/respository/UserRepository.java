package com.fisa.land.fisaland.common.respository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fisa.land.fisaland.common.entity.User;
import com.fisa.land.fisaland.common.type.LoginProvider;
	
@Repository
public interface UserRepository extends JpaRepository<User, Long>{

	Optional<User> findByUserId(Long userId);
	
	Optional<User> findByEmail(String email);
	
	void deleteByUserId(Long userId);
	
	Optional<User> findBySocialIdAndLoginProvider(String socialId, LoginProvider loginProvider);
}