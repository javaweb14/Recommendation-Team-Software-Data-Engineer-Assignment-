package com.hepsiburada.database.repository;

import com.hepsiburada.database.model.BrowsingHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BrowsingHistoryRepository extends JpaRepository<BrowsingHistory, Long> {
    List<BrowsingHistory> findByUserId(String userId);

    Optional<BrowsingHistory> findByUserIdAndProductId(String userId, String productId);
}
