package stockindexing.service;

import java.util.List;

import org.springframework.stereotype.Component;

import stockindexing.response.StockDetails;

@Component
public interface CallExternal {

	public List<StockDetails> get(String url);
}
