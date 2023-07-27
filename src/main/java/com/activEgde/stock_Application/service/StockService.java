package com.activEgde.stock_Application.service;


import com.activEgde.stock_Application.dto.request.StockRequest;
import com.activEgde.stock_Application.dto.response.StockResponse;
import com.activEgde.stock_Application.model.Stock;
import org.springframework.data.domain.Page;

public interface StockService {
    StockResponse createStock(StockRequest request);
    Page<Stock> getListOfStocks(int pageNo, int pageSize, String sortBy);
    StockResponse getStockById(Long stockId);
    StockResponse updateStock(Long stockId, StockRequest request);
}
