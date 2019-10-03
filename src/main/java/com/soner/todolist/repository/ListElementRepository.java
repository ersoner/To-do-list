package com.soner.todolist.repository;

import com.soner.todolist.entity.ListElement;
import com.soner.todolist.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@javax.transaction.Transactional
public interface ListElementRepository extends CrudRepository<ListElement, Long> {
    java.util.List<ListElement> findByListIdIn(Integer list_id);
}