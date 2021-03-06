package cn.sotou.tuningfork.util.builtin;

import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;

import cn.sotou.tuningfork.exception.PipeUtilException;
import cn.sotou.tuningfork.util.BasePipeUtil;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;

/**
 * @author shigong
 */
public class Get extends BasePipeUtil {

	private HttpClient httpClient;

	private HttpContext context;

	public Get() {
		setHttpClient(HttpClientBuilder.create().build());
		context = new BasicHttpContext();
	}

	public InputStream[] process(InputStream input, String... args) {
		String url = readerFromStream(input);
		closeStream(input);

		HttpGet httpGet = new HttpGet(url);
		try {
			HttpResponse response = getHttpClient().execute(httpGet, getContext());
			HttpEntity entity = response.getEntity();

			String responseString = IOUtils.toString(entity.getContent());
			EntityUtils.consume(entity);

			return new InputStream[]{IOUtils.toInputStream(responseString)};
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

	public HttpContext getContext() {
		return context;
	}

	public void setContext(HttpContext context) {
		this.context = context;
	}
}
