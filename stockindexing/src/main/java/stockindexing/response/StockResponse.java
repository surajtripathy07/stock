package stockindexing.response;

public class StockResponse {
	Integer code; 
	Object data;
	
	public StockResponse(int code, Object data){
		this.code = code;
		this.data = data;
	}
}
