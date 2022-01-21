package com.karrot.demo.domain.item;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {

    List<Item> findTop20ByOrderByIdDesc();
    List<Item> findAllByPlace(String place);
    List<Item> findAllByUploaderId(Long uploaderId);
}
