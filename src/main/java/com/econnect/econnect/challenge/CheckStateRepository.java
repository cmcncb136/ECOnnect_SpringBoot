package com.econnect.econnect.challenge;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CheckStateRepository extends JpaRepository<CheckState, Integer> {
}
