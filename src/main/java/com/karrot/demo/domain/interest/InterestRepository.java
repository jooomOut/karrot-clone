package com.karrot.demo.domain.interest;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface InterestRepository extends JpaRepository<Interest, Long> {
    boolean existsByItemIdAndUserId(Long itemId, Long userId);
    Optional<Interest> findByItemIdAndUserId(Long itemId, Long userId);
}
