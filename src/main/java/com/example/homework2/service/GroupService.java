package com.example.homework2.service;

import com.example.homework2.entity.Group;
import com.example.homework2.repository.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupService {

    @Autowired
    private GroupRepository repository;

    public List<Group> getGroups() {
        return repository.findAll();
    }

    public Group geGroupById(long id) {
        return repository.getGroupById(id);
    }

    public Group saveGroup(Group group) {
        return repository.save(group);
    }

}
