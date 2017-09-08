package com.just.soso.repository;


import com.just.soso.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by user on 2017/3/21.
 */
@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, Integer>,PagingAndSortingRepository<UserRole, Integer> {
     UserRole findUserRoleByUserId(Integer userId);
}
