package com.example.homework2.controller;

import com.example.homework2.entity.Group;
import com.example.homework2.entity.Student;
import com.example.homework2.repository.GroupRepository;
import com.example.homework2.service.GroupService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/groups")
public class GroupController {
    private static final Logger logger = LoggerFactory.getLogger(GroupController.class.getName());
    @Autowired
    private GroupService groupService;

    @GetMapping
    public List<Group> getGroups() {
        return groupService.getGroups();
    }

    @GetMapping("/{id}")
    public Group getGroupById(@PathVariable long id) {
        return groupService.geGroupById(id);
    }

    @PostMapping
    public Group saveGroup(@RequestBody Group group) {
        return groupService.saveGroup(group);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateGroup(@RequestBody Group group, @PathVariable long id) {
        try {
            Group g = groupService.geGroupById(id);
            if (g == null) return ResponseEntity.badRequest().body("The group id is invalid");
            if (group.getName() != null) g.setName(group.getName());
            return ResponseEntity.ok(g);
        } catch (Exception exception) {
            logger.error(exception.getMessage());
            return ResponseEntity.status(500).body("An internal server error caused by jpa");
        }

    }

}
