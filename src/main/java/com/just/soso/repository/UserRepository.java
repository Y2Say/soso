package com.just.soso.repository;

import com.just.soso.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by user on 2017/3/20.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Integer>,PagingAndSortingRepository<User, Integer>{
     User findByName(String name);

     User findById(Integer id);

     void deleteById(Integer id);
}
