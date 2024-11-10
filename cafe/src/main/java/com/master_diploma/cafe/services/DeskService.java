package com.master_diploma.cafe.services;

import com.master_diploma.cafe.dto.DeskReserveDto;
import com.master_diploma.cafe.dto.DeskReserveProjection;
import com.master_diploma.cafe.models.Desk;
import com.master_diploma.cafe.repositories.DeskRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class DeskService implements DeskRepository {
    @Autowired
    private DeskRepository deskRepository;
    @Override
    public <S extends Desk> S save(S entity) {
        return deskRepository.save(entity);
    }
    @Override
    public <S extends Desk> Iterable<S> saveAll(Iterable<S> entities) {
        return deskRepository.saveAll(entities);
    }

    @Override
    public Optional<Desk> findById(Long aLong) {
        return deskRepository.findById(aLong);
    }

    @Override
    public boolean existsById(Long aLong) {
        return deskRepository.existsById(aLong);
    }

    @Override
    public Iterable<Desk> findAll() {
        return deskRepository.findAll();
    }

    @Override
    public Iterable<Desk> findAllById(Iterable<Long> longs) {
        return deskRepository.findAllById(longs);
    }

    @Override
    public long count() {
        return deskRepository.count();
    }

    @Override
    public void deleteById(Long aLong) {
        deskRepository.deleteById(aLong);
    }

    @Override
    public void delete(Desk entity) {
        deskRepository.delete(entity);
    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {
        deleteAllById(longs);
    }

    @Override
    public void deleteAll(Iterable<? extends Desk> entities) {
        deskRepository.deleteAll(entities);
    }

    @Override
    public void deleteAll() {
        deskRepository.deleteAll();
    }

    @Override
    public List<Desk> findByInstitutionId(Long id) {
        return deskRepository.findByInstitutionId(id);
    }

    @Override
    public List<DeskReserveProjection> findDeskReserveByInstitutionId(Long institutionId) {
        return deskRepository.findDeskReserveByInstitutionId(institutionId);
    }
}
