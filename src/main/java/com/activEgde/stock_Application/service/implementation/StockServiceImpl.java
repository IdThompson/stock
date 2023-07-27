package com.activEgde.stock_Application.service.implementation;


import com.activEgde.stock_Application.dto.request.StockRequest;
import com.activEgde.stock_Application.dto.response.StockResponse;
import com.activEgde.stock_Application.exception.NotFoundException;
import com.activEgde.stock_Application.model.Stock;
import com.activEgde.stock_Application.repository.StockRepository;
import com.activEgde.stock_Application.service.StockService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Log4j2
@Service
@AllArgsConstructor
public class StockServiceImpl implements StockService {
    private final StockRepository stockRepository;
    private ModelMapper modelMapper = new ModelMapper();


    @Override
    public StockResponse createStock(StockRequest request) {
        log.info("service:: about setting");
        Stock newStock = Stock.builder()
                .name(request.getName())
                .currentPrice(request.getCurrentPrice())
                .build();
        Stock savedStock= stockRepository.save(newStock);
        //Map saved stock to stock response DTO
        StockResponse stockResponse = mapToResponse(savedStock);
        return stockResponse;
    }

    @Override
    public Page<Stock> getListOfStocks(int pageNo, int pageSize, String sortBy) {
        PageRequest paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
        return stockRepository.findAll(paging);
    }
    @Override
    public StockResponse getStockById(Long stockId) {
        Stock getStock = stockRepository.findById(stockId)
                .orElseThrow(() -> new NotFoundException("Customer with Id " + stockId + " not found"));
        return mapToResponse(getStock);

    }
    @Override
    public StockResponse updateStock(Long stockId, StockRequest request) {
        Stock stock = stockRepository.findById(stockId)
                .orElseThrow(() -> new NotFoundException("Stock with Id " + stockId + " not found"));
        if (request.getCurrentPrice() != null) {
            stock.setCurrentPrice(request.getCurrentPrice());
        }
        if (request.getName() != null) {
            stock.setName(request.getName());
        }
        Stock updatedStock = stockRepository.save(stock);
        return mapToResponse(updatedStock);
    }
    //    ------------------HELPER METHODS ------------------
    private StockResponse mapToResponse(Stock stock){
        return modelMapper.map(stock, StockResponse.class);
    }
}
