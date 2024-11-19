package com.master_diploma.cafe.services;

import com.master_diploma.cafe.models.UserTable;
import com.master_diploma.cafe.repositories.UserTableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserTableService{
    @Autowired
    private UserTableRepository userTableRepository;
    public List<UserTable> findAllWorkers(Long roleId) {
        return userTableRepository.findAllWorkers(roleId);
    }
    public UserTable findAnyCook() {
        return userTableRepository.findAnyCook();
    }
    public void updateRoleUser(Long roleId, Long id) {
        userTableRepository.updateRoleUser(roleId, id);
    }
    public <S extends UserTable> S save(S entity) {
        return userTableRepository.save(entity);
    }
    public UserTable findById(Long aLong) {
        return userTableRepository.findById(aLong).orElseThrow(() -> new RuntimeException("User not found"));
    }
    public Iterable<UserTable> findAll() {
        return userTableRepository.findAll();
    }
}
