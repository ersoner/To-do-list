package com.soner.todolist.web;

import com.soner.todolist.entity.List;
import com.soner.todolist.entity.ListElement;
import com.soner.todolist.repository.ListElementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/elements")
public class ListElementController {

    @Autowired
    private ListElementRepository listElementRepository;

    @PostMapping({"create"})
    public ResponseEntity<ListElement> store(@Valid @RequestBody ListElement listElement, List list) {
        listElement.setList(list);
        listElementRepository.save(listElement);

        return new ResponseEntity<ListElement>(listElement, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public @ResponseBody
    Optional<ListElement> show(@PathVariable long id) {
        return listElementRepository.findById(id);
    }

    @PatchMapping("update/{id}")
    public ResponseEntity<ListElement> update(@Valid @RequestBody ListElement listElement, @PathVariable("id") long id) {
        ListElement currentElement = listElementRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Not found"));
        currentElement.setTitle(listElement.getTitle());
        currentElement.setStatus(listElement.getStatus());
        currentElement.setDescription(listElement.getDescription());
        currentElement.setDeadline(listElement.getDeadline());
        listElementRepository.save(currentElement);

        return new ResponseEntity<ListElement>(currentElement, HttpStatus.OK);
    }
}
