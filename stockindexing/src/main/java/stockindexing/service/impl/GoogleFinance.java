package stockindexing.service.impl;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;

import stockindexing.response.StockDetails;
import stockindexing.service.CallExternal;

@Service
public class GoogleFinance implements CallExternal {

	@Autowired
	private RestTemplate restTemplate;

	public List<StockDetails> get(String url) {
		try {
			HttpHeaders headers = new HttpHeaders();
			headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
			HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);

			/*
			 * ResponseEntity<List<T>> rateResponse = restTemplate.exchange(url,
			 * HttpMethod.GET, entity, new ParameterizedTypeReference<List<T>>()
			 * { });
			 */
			ResponseEntity<String> response = restTemplate.getForEntity(url.toString(), String.class);
			//response.getHeaders().set("Content-Type", MediaType.APPLICATION_JSON.toString());

			ObjectMapper mapper = new ObjectMapper();
			String body = response.getBody();
			body = body.replaceAll("\\/\\/", "");
			List<StockDetails> stock = Arrays.asList(mapper.readValue(body, StockDetails[].class));

			return stock;
		} catch (Exception e) {
			return null;
		}
	}

}
