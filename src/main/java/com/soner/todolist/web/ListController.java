package com.soner.todolist.web;

import com.soner.todolist.entity.List;
import com.soner.todolist.entity.ListElement;
import com.soner.todolist.entity.User;
import com.soner.todolist.repository.ListElementRepository;
import com.soner.todolist.repository.ListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/lists")
public class ListController {

    @Autowired
    private ListRepository listRepository;

    @Autowired
    private ListElementRepository listElementRepository;

    @GetMapping({""})
    public ResponseEntity<Iterable<List>> list(User user) {
        java.util.List<List> listByUser = listRepository.findAllByUser(user);
        return new ResponseEntity<>(listByUser, HttpStatus.OK);
    }

    @PostMapping({"create"})
    public ResponseEntity<List> store(@Valid @RequestBody List list, User user) {
        list.setUser(user);
        listRepository.save(list);

        return new ResponseEntity<List>(list, HttpStatus.OK);
    }

    @GetMapping("/elements/{id}")
    public ResponseEntity <Iterable<ListElement>> showElements(@PathVariable Integer id) {
        java.util.List<ListElement> listElements = listElementRepository.findByListIdIn(id);
        return new ResponseEntity<>(listElements, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public @ResponseBody
    Optional<List> show(@PathVariable long id) {
        return listRepository.findById(id);
    }

    @PatchMapping("update/{id}")
    public ResponseEntity<List> update(@Valid @RequestBody List list,@PathVariable("id") long id) {
        List currentList = listRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Not found"));
        currentList.setTitle(list.getTitle());
        listRepository.save(currentList);

        return new ResponseEntity<List>(currentList, HttpStatus.OK);
    }
}
