package com.just.soso.repository;

import com.just.soso.entity.Functions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * Created by user on 2017/3/21.
 */
public interface FunctionRepository extends JpaRepository<Functions, Integer>, PagingAndSortingRepository<Functions,Integer> {
     void deleteById(Integer id);
     Functions findById(Integer id);
     List<Functions> findByParentId(Integer parentId);
}
