package stockindexing.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import stockindexing.response.StockDetails;
import stockindexing.response.StockResponse;
import stockindexing.service.StockProcessor;
import stockindexing.util.StockURL;

/**
 * 
 * @author suraj
 *
 */
@RestController
public class StockController {

	@Autowired
	private StockProcessor sproc;

	@RequestMapping(value = StockURL.PING, method = RequestMethod.GET)
	public String ping() {
		return "Ok";
	}

	@RequestMapping(value = StockURL.GET_STOCK_DETAILS_URL, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<StockResponse> getStockDetails(@RequestParam("stockName") String stockName,
			@RequestParam("exchange") String exchange) {
		List<StockDetails> data = null;
		try {
			data = sproc.getNewStockValues(stockName.split(","), exchange);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return new ResponseEntity<StockResponse>(new StockResponse(0, data), HttpStatus.OK);
	}
}
