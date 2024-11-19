package com.master_diploma.cafe.services;

import com.master_diploma.cafe.models.OrderTable;
import com.master_diploma.cafe.repositories.OrderTableRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
@AllArgsConstructor
@NoArgsConstructor
public class OrderTableService implements OrderTableRepository {
    @Autowired
    private OrderTableRepository orderTableRepository;
    @Override
    public <S extends OrderTable> S save(S entity) {
        return orderTableRepository.save(entity);
    }

    @Override
    public <S extends OrderTable> Iterable<S> saveAll(Iterable<S> entities) {
        return orderTableRepository.saveAll(entities);
    }

    @Override
    public Optional<OrderTable> findById(Long aLong) {
        return orderTableRepository.findById(aLong);
    }

    @Override
    public boolean existsById(Long aLong) {
        return orderTableRepository.existsById(aLong);
    }

    @Override
    public Iterable<OrderTable> findAll() {
        return orderTableRepository.findAll();
    }

    @Override
    public Iterable<OrderTable> findAllById(Iterable<Long> longs) {
        return orderTableRepository.findAllById(longs);
    }

    @Override
    public long count() {
        return orderTableRepository.count();
    }

    @Override
    public void deleteById(Long aLong) {
        orderTableRepository.deleteById(aLong);
    }

    @Override
    public void delete(OrderTable entity) {
        orderTableRepository.delete(entity);
    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {
        orderTableRepository.deleteAllById(longs);
    }

    @Override
    public void deleteAll(Iterable<? extends OrderTable> entities) {
        orderTableRepository.deleteAll(entities);
    }

    @Override
    public void deleteAll() {
        orderTableRepository.deleteAll();
    }
    @Transactional
    @Override
    public Integer updateStatus(String status, Long id) {
        return orderTableRepository.updateStatus(status, id);
    }

    @Override
    public List<OrderTable> findByUserId(Long id) {
        return orderTableRepository.findByUserId(id);
    }

    @Override
    public List<OrderTable> findUnfinishedOrders(Long institutionId) {
        return orderTableRepository.findUnfinishedOrders(institutionId);
    }
}
