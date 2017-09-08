package com.just.soso.repository;

import com.just.soso.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;



/**
 * Created by user on 2017/3/21.
 */
public interface RoleRepository extends JpaRepository<Role,Integer>,PagingAndSortingRepository<Role,Integer> {
     void deleteRoleById(Integer id);
     Role findById(Integer id);
}
