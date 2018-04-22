package com.wut.orc;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import javax.enterprise.inject.New;
import javax.persistence.PostLoad;
import javax.ws.rs.POST;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

public class TencentApi {
	// APPID,SecretId,SecretKey
	private static long appid = 1256123559;
	private static String SecretId = "AKIDxHXpvqSBaoriUDfFlANOJ3gIm7IjrFEE";
	private static String SecretKey = "41pRs69JOM8jIl271jE853pbHrhcR8pQ";

	public void dome() throws Exception {

		CloseableHttpClient httpClient = HttpClients.createDefault();

		String entityStr = null;
		CloseableHttpResponse response = null;

		try {

			// 创建POST请求对象
			HttpPost httpPost = new HttpPost("http://recognition.image.myqcloud.com/ocr/handwriting");

			/*
			 * 添加请求参数
			 */
			// 创建请求参数
			List<NameValuePair> list = new LinkedList<>();
			BasicNameValuePair param1 = new BasicNameValuePair("appid", "1252209381");
			BasicNameValuePair param2 = new BasicNameValuePair("bucket", "");
			BasicNameValuePair param3 = new BasicNameValuePair("url",
					"http://oi4ty6vxc.bkt.clouddn.com/wangyiyunLOGE.png");
			list.add(param1);
			list.add(param2);
			list.add(param3);
			// 使用URL实体转换工具
			UrlEncodedFormEntity entityParam = new UrlEncodedFormEntity(list, "UTF-8");
			httpPost.setEntity(entityParam);

			/*
			 * 添加请求头信息
			 */
			httpPost.addHeader("host", "recognition.image.myqcloud.com");
			httpPost.addHeader("Content-Type", "application/json");
			httpPost.addHeader("authorization",
					"gekuLE8u+p+J7jHadlYhyJhgCyNhPTEyNTIyMDkzO"
							+ "DEmYj1maWxlJms9QUtJRGM4VGNhTnZVbGJMdUlZeVVDaTNDU2drZEtxUDJmM"
							+ "E5kJmU9MTUyNjk2NTA1OSZ0PTE1MjQzNzMwNTkmcj0xODE1MDkzOTI2JnU9MCZmPQ== ");
			// 执行请求
			response = httpClient.execute(httpPost);
			// 获得响应的实体对象
			HttpEntity entity = response.getEntity();
			// 使用Apache提供的工具类进行转换成字符串
			entityStr = EntityUtils.toString(entity, "UTF-8");

			// System.out.println(Arrays.toString(response.getAllHeaders()));

		} catch (ClientProtocolException e) {
			System.err.println("Http协议出现问题");
			e.printStackTrace();
		} catch (IOException e) {
			System.err.println("IO异常");
			e.printStackTrace();
		} finally {
			// 释放连接
			if (null != response) {
				try {
					response.close();
					httpClient.close();
				} catch (IOException e) {
					System.err.println("释放连接出错");
					e.printStackTrace();
				}
			}
		}

		// 打印响应内容
		System.out.println(entityStr);
	}

}
