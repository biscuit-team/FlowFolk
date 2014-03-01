package cn.sotou.grabber.pipe.util.builtin;

import java.io.IOException;
import java.io.InputStream;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;

import cn.sotou.grabber.pipe.exception.PipeUtilException;
import cn.sotou.grabber.pipe.util.BasePipeUtil;

/**
 * 
 * @author shigong
 * 
 */
public class Get extends BasePipeUtil {

	private HttpClient httpClient;

	public Get() {
		setHttpClient(HttpClientBuilder.create().build());
	}

	public InputStream[] process(InputStream input, String... args) {
		String url = readerFromStream(input);
		closeStream(input);

		HttpGet httpGet = new HttpGet(url);
		try {
			HttpResponse response = getHttpClient().execute(httpGet);
			HttpEntity entity = response.getEntity();
			return new InputStream[] { entity.getContent() };
		} catch (ClientProtocolException e) {
			e.printStackTrace();
			throw new PipeUtilException("catch an exception when try get "
					+ url, e);
		} catch (IOException e) {
			e.printStackTrace();
			throw new PipeUtilException("catch an exception when try get "
					+ url, e);
		}
	}

	public HttpClient getHttpClient() {
		return httpClient;
	}

	public void setHttpClient(HttpClient httpClient) {
		this.httpClient = httpClient;
	}
}
