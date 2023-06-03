package com.projectjavasneaker.backendis216.repository;

import com.projectjavasneaker.backendis216.models.CartDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartDetailsRepository extends JpaRepository<CartDetails, Long> {
    // Các phương thức tương ứng với việc lưu trữ và truy vấn dữ liệu cho CartDetails
}