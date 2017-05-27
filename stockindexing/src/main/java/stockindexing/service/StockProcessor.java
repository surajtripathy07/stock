package stockindexing.service;

import java.util.List;

import org.springframework.stereotype.Service;

import stockindexing.response.StockDetails;

@Service
public interface StockProcessor {

	public List<StockDetails> getStockDetails(String[] stockSymbols, String exchange);

	public boolean insertStockDetails(List<StockDetails> stockResponse);

	public List<StockDetails> getNewStockValues(String[] stockSymbols, String exchange);
}
