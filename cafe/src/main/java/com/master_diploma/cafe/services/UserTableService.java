package com.master_diploma.cafe.services;

import com.master_diploma.cafe.models.UserTable;
import com.master_diploma.cafe.repositories.UserTableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserTableService implements UserTableRepository {
    @Autowired
    private UserTableRepository userTableRepository;
    @Override
    public UserTable findByFirstName(String firstName) {
        return userTableRepository.findByFirstName(firstName);
    }

    @Override
    public Optional<UserTable> findByEmail(String email) {
        return userTableRepository.findByEmail(email);
    }

    @Override
    public List<UserTable> findAllWorkers(Long roleId) {
        return userTableRepository.findAllWorkers(roleId);
    }

    @Override
    public void updateRoleUser(Long roleId, Long id) {
        userTableRepository.updateRoleUser(roleId, id);
    }

    @Override
    public <S extends UserTable> S save(S entity) {
        return userTableRepository.save(entity);
    }

    @Override
    public <S extends UserTable> Iterable<S> saveAll(Iterable<S> entities) {
        return userTableRepository.saveAll(entities);
    }

    @Override
    public Optional<UserTable> findById(Long aLong) {
        return userTableRepository.findById(aLong);
    }

    @Override
    public boolean existsById(Long aLong) {
        return userTableRepository.existsById(aLong);
    }

    @Override
    public Iterable<UserTable> findAll() {
        return userTableRepository.findAll();
    }

    @Override
    public Iterable<UserTable> findAllById(Iterable<Long> longs) {
        return userTableRepository.findAllById(longs);
    }

    @Override
    public long count() {
        return userTableRepository.count();
    }

    @Override
    public void deleteById(Long aLong) {
        userTableRepository.deleteById(aLong);
    }

    @Override
    public void delete(UserTable entity) {
        userTableRepository.delete(entity);
    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {
        userTableRepository.deleteAllById(longs);
    }

    @Override
    public void deleteAll(Iterable<? extends UserTable> entities) {
        userTableRepository.deleteAll(entities);
    }

    @Override
    public void deleteAll() {
        userTableRepository.deleteAll();
    }
}
