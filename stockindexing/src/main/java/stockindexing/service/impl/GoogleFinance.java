package stockindexing.service.impl;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.databind.ObjectMapper;

import stockindexing.response.StockDetails;
import stockindexing.service.CallExternal;

@Service
public class GoogleFinance implements CallExternal {

	@Autowired
	private RestTemplate restTemplate;

	final String regex = "\\/\\/";
	final Pattern pattern = Pattern.compile(regex);

	@Override
	public List<StockDetails> get(String url, String exchange, String param) {
		try {
			HttpHeaders headers = new HttpHeaders();
			headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

			MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();

			HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<MultiValueMap<String, String>>(map,
					headers);

			String qString = exchange + ":" + param;
			UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url).queryParam("client", "ig")
					.queryParam("q", qString);

			ResponseEntity<String> response = restTemplate.exchange(builder.build().encode().toUri(), HttpMethod.GET,
					entity, String.class);

			ObjectMapper mapper = new ObjectMapper();
			String body = response.getBody();
			final Matcher matcher = pattern.matcher(body);

			final String result = matcher.replaceAll("");

			List<StockDetails> stock = Arrays.asList(mapper.readValue(result, StockDetails[].class));

			return stock;
		} catch (Exception e) {
			e.printStackTrace();
			e.getMessage();
			return null;
		}
	}

}
