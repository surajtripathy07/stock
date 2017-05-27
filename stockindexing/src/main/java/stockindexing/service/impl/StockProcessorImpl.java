package stockindexing.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import stockindexing.dao.StockDao;
import stockindexing.response.StockDetails;
import stockindexing.service.CallExternal;
import stockindexing.service.StockProcessor;
import stockindexing.util.StockURL;

@Service
public class StockProcessorImpl implements StockProcessor {

	@Autowired
	private CallExternal googleCall;

	@Autowired
	private StockDao stDao;

	@Override
	public List<StockDetails> getStockDetails(String[] stockSymbols, String exchange) {
		StringBuffer url = new StringBuffer(StockURL.GOOGLE_STOCK_URL);
		StringBuilder paramB = new StringBuilder();
		for (String symbols : stockSymbols)
			paramB.append(symbols).append(",");

		// removing the last ","
		paramB.setLength(paramB.length() - 1);

		List<StockDetails> response = googleCall.get(url.toString(), exchange, paramB.toString());
		return null != response ? response : null;
	}

	@Override
	public boolean insertStockDetails(List<StockDetails> stockResponse) {
		if (stockResponse == null)
			return true;

		for (StockDetails detail : stockResponse) {
			// save in db for regression
			stDao.archiveStockDetail(detail);

			// update master data
			StockDetails det = stDao.getStockName(detail.getT());
			detail.setName(det.getName());
			detail.setMarketCap(det.getMarketCap());
			detail.setSector(det.getSector());
			detail.setIndustry(det.getIndustry());

		}
		return true;
	}

	@Override
	public List<StockDetails> getNewStockValues(String[] stockSymbols, String exchange) {
		List<StockDetails> details = getStockDetails(stockSymbols, exchange);
		insertStockDetails(details);
		return details;
	}

}
