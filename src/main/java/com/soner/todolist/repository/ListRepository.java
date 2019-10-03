package com.soner.todolist.repository;

import com.soner.todolist.entity.List;
import com.soner.todolist.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface ListRepository extends CrudRepository<List, Long> {
    java.util.List<List> findAllByUser(User user);

    List findOneByIdAndUser(Integer id, User user);

    @org.springframework.transaction.annotation.Transactional
    void deleteByIdAndUser(Integer id, User user);
}