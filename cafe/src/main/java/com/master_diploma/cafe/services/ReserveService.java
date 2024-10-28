//package com.master_diploma.cafe.services;
//
//import com.master_diploma.cafe.models.Reserve;
//import com.master_diploma.cafe.repositories.ReserveRepository;
//import org.springframework.stereotype.Service;
//
//import java.util.Optional;
//
//@Service
//public class ReserveService implements ReserveRepository {
//    private ReserveRepository reserveRepository;
//    @Override
//    public <S extends Reserve> S save(S entity) {
//        return reserveRepository.save(entity);
//    }
//
//    @Override
//    public <S extends Reserve> Iterable<S> saveAll(Iterable<S> entities) {
//        return saveAll(entities);
//    }
//
//    @Override
//    public Optional<Reserve> findById(Long aLong) {
//        return reserveRepository.findById(aLong);
//    }
//
//    @Override
//    public boolean existsById(Long aLong) {
//        return false;
//    }
//
//    @Override
//    public Iterable<Reserve> findAll() {
//        return null;
//    }
//
//    @Override
//    public Iterable<Reserve> findAllById(Iterable<Long> longs) {
//        return null;
//    }
//
//    @Override
//    public long count() {
//        return 0;
//    }
//
//    @Override
//    public void deleteById(Long aLong) {
//
//    }
//
//    @Override
//    public void delete(Reserve entity) {
//
//    }
//
//    @Override
//    public void deleteAllById(Iterable<? extends Long> longs) {
//
//    }
//
//    @Override
//    public void deleteAll(Iterable<? extends Reserve> entities) {
//
//    }
//
//    @Override
//    public void deleteAll() {
//
//    }
//}
