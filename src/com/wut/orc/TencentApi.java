package com.wut.orc;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import javax.enterprise.inject.New;
import javax.json.JsonArray;
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
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import com.wut.user.returnNote;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class TencentApi {
	

	public String api(String url) throws Exception {

		CloseableHttpClient httpClient = HttpClients.createDefault();

		String entityStr = null;
		CloseableHttpResponse response = null;

		try {

			// 创建POST请求对象
			HttpPost httpPost = new HttpPost("http://recognition.image.myqcloud.com/ocr/handwriting");

			
			// 使用URL实体转换工具
			// 需要传递一个 json
			JSONObject param = new JSONObject();
			param.put("appid", "1252209381");
			param.put("bucket", "");
			param.put("url", url);
			StringEntity se = new StringEntity(param.toString());
			
			 
			httpPost.setEntity(se);

			/*
			 * 添加请求头信息
			 */
			httpPost.addHeader("host", "recognition.image.myqcloud.com");
			httpPost.addHeader("Content-Type", "application/json");
			httpPost.addHeader("authorization",
					"gekuLE8u+p+J7jHadlYhyJhgCyNhPTEyNTIyMDkzODEmYj1maWxlJms9QUtJRGM4VGNhT"
					+ "nZVbGJMdUlZeVVDaTNDU2drZEtxUDJmME5kJmU9MTUyNjk2NTA1OSZ0PTE1MjQzNzMwNTkmcj0xODE1MDkzOTI2JnU9MCZmPQ==");
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
		JSONObject result = JSONObject.fromObject(entityStr);
		JSONObject data = result.getJSONObject("data");
		JSONArray jsonArray = data.getJSONArray("items");
		String str = null;
		for(int i = 0; i<jsonArray.length();i++){
			JSONObject a =  jsonArray.getJSONObject(i);
			str+=a.get("itemstring").toString()+"\n";
			System.out.println(a.get("itemstring"));
		}
		if (str==null) {
			return "";
		}
		return str.replace("null", "");
		
		
		
	}

}
