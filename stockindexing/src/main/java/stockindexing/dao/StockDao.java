package stockindexing.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import stockindexing.response.StockDetails;

@Repository
public interface StockDao {

	public StockDetails getStockName(@Param("symbol") String symbol);
	
	public void archiveStockDetail(@Param("stockDetail") StockDetails stockDetail);
}
