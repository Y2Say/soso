package com.just.soso.repository;

import com.just.soso.entity.RoleFunction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by user on 2017/3/21.
 */
@Repository
public interface RoleFunctionRepository extends JpaRepository<RoleFunction,Integer>, PagingAndSortingRepository<RoleFunction, Integer> {
     void deleteByRoleId(Integer roleId);
     List<RoleFunction> findRoleFunctionsByRoleId(Integer roleId);

}
