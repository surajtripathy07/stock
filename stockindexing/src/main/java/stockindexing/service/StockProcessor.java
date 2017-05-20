package stockindexing.service;

import java.util.List;

import org.springframework.stereotype.Service;

import stockindexing.response.StockDetails;

@Service
public interface StockProcessor {

	public List<StockDetails> getStockDetails(String[] stockSymbols);
	
	public boolean insertStockDetails(List<StockDetails> stockResponse);
	
	public boolean insertStockDetail(StockDetails stockResponse);
	
	public List<StockDetails> getNewStockValues(String[] stockSymbols);
}
