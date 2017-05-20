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
	public List<StockDetails> getStockDetails(String[] stockSymbols) {
		StringBuffer url = new StringBuffer(StockURL.GOOGLE_STOCK_URL);

		for (String symbols : stockSymbols)
			url.append(symbols).append(",");

		// removing the last ","
		url.setLength(url.length() - 1);

		List<StockDetails> response = googleCall.get(url.toString());
		return null != response ? response : null;
	}

	@Override
	public boolean insertStockDetails(List<StockDetails> stockResponse) {
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
	public boolean insertStockDetail(StockDetails stockResponse) {
		return false;
	}

	@Override
	public List<StockDetails> getNewStockValues(String[] stockSymbols) {
		List<StockDetails> details = getStockDetails(stockSymbols);
		insertStockDetails(details);
		return details;
	}

}
