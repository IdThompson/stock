package com.activEgde.stock_Application.repository;


import com.activEgde.stock_Application.model.Stock;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockRepository extends JpaRepository<Stock, Long> {
}
