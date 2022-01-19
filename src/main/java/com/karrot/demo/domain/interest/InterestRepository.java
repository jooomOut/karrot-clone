package com.karrot.demo.domain.interest;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InterestRepository extends JpaRepository<Interest, Long> {
    boolean existsByItemIdAndUserId(Long itemId, Long userId);
}
