package com.activEgde.stock_Application.dataloader;

import com.activEgde.stock_Application.model.Stock;
import com.activEgde.stock_Application.repository.StockRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class DefaultStock implements CommandLineRunner {
    private final StockRepository stockRepository;
    @Override
    public void run(String... args){
        List<Stock> stockList = new ArrayList<>();
        stockList.add(new Stock(1L, "Stock1", new BigDecimal("100.00"), new Timestamp(System.currentTimeMillis()),
                new Timestamp(System.currentTimeMillis())));
        stockList.add(new Stock(2L, "Stock2", new BigDecimal("200.00"), new Timestamp(System.currentTimeMillis()),
                new Timestamp(System.currentTimeMillis())));
        stockList.add(new Stock(3L, "Stock3", new BigDecimal("300.00"), new Timestamp(System.currentTimeMillis()),
                new Timestamp(System.currentTimeMillis())));
        stockList.add(new Stock(4L, "Stock4", new BigDecimal("400.00"), new Timestamp(System.currentTimeMillis()),
                new Timestamp(System.currentTimeMillis())));
        stockList.add(new Stock(5L, "Stock5", new BigDecimal("500.00"), new Timestamp(System.currentTimeMillis()),
                new Timestamp(System.currentTimeMillis())));
        stockList.add(new Stock(6L, "Stock6", new BigDecimal("600.00"), new Timestamp(System.currentTimeMillis()),
                new Timestamp(System.currentTimeMillis())));
        stockList.add(new Stock(7L, "Stock7", new BigDecimal("700.00"), new Timestamp(System.currentTimeMillis()),
                new Timestamp(System.currentTimeMillis())));
        stockList.add(new Stock(8L, "Stock8", new BigDecimal("800.00"), new Timestamp(System.currentTimeMillis()),
                new Timestamp(System.currentTimeMillis())));
        stockRepository.saveAll(stockList);
    }

}
