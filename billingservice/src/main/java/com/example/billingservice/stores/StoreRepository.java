package com.example.billingservice.stores;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StoreRepository extends JpaRepository<Store, Long> {
    // 这里不需要实现任何方法，Spring Data JPA 会自动提供基本的 CRUD 操作。
}
