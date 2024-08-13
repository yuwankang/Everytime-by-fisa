package com.fisa.land.fisaland.common.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fisa.land.fisaland.common.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

}
