package com.activEgde.stock_Application.controller;

import com.activEgde.stock_Application.dto.request.StockRequest;
import com.activEgde.stock_Application.dto.response.ApiResponse;
import com.activEgde.stock_Application.dto.response.StockResponse;
import com.activEgde.stock_Application.model.Stock;
import com.activEgde.stock_Application.service.StockService;
import com.activEgde.stock_Application.utils.Constants;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@Slf4j
@RestController
@RequestMapping("/api/stocks")
@RequiredArgsConstructor
public class StockController {
    private final StockService stockService;
    @PostMapping
    public ResponseEntity<Object> createStock(@RequestBody @Valid final StockRequest stockRequest){
        log.info("controller register: register user :: [{}] ::", stockRequest.getName());
        StockResponse response = stockService.createStock(stockRequest);
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/stock").toUriString());
        return ResponseEntity.created(uri).body(ApiResponse.buildSuccess(response));
    }
    @GetMapping("/getStock/{Id}")
    public ResponseEntity<?> getStockById(@PathVariable("Id") Long stockId){
        return ResponseEntity.ok().body(ApiResponse.buildSuccessTxn(stockService.getStockById(stockId)));
    }
    @PutMapping("/updateStockByPrice/{Id}")
    public ResponseEntity<?> updateStock(@PathVariable("Id") Long stockId, @RequestBody StockRequest stockRequest){
        return ResponseEntity.ok().body(ApiResponse.buildSuccessTxn(stockService.updateStock(stockId, stockRequest)));
    }
    @GetMapping("/getAllStocks")
    public ResponseEntity<ApiResponse<Page<Stock>>> getAllStocks(
            @RequestParam(defaultValue = Constants.PAGENO) Integer pageNo,
            @RequestParam(defaultValue = Constants.PAGESIZE) Integer pageSize,
            @RequestParam(defaultValue = "id") String sortBy) {

        Page<Stock> pageResult = stockService.getListOfStocks(pageNo, pageSize, sortBy);

        ApiResponse<Page<Stock>> response = new ApiResponse<>();
        if (pageResult.hasContent()) {
            response.setStatus(true);
            response.setData(pageResult);
            response.setStatusCode(HttpStatus.OK);
            response.setMessage("Stocks found successfully.");
        } else {
            response.setStatus(false);
            response.setMessage("No stocks found.");
            response.setStatusCode(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(response, response.getStatusCode());
    }
}
