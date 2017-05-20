package stockindexing.config;

import java.io.IOException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

public class XmlInterceptor implements ClientHttpRequestInterceptor {

	@Override
	public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution)
			throws IOException {
		ClientHttpResponse response = execution.execute(request, body);
		HttpHeaders headers = response.getHeaders();

		// you'd want to check if the value needs to be changed
		if (headers.containsKey("Content-Type")) {
			headers.remove("Content-Type");
		}

		headers.add("Content-Type", "application/xml");

		return response;
	}
}
