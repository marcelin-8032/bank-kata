package com.exhalt.bank.repository;

import com.exhalt.bank.entity.OperationHistory;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OperationRepository extends CrudRepository<OperationHistory,Long> {
}
