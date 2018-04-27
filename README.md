# Sdnote.Server
Sdnote的服务端，基于Jersey框架编写，作为一个助手类应用支持全平台。


## 项目主页
[https://github.com/Sdnote](https://github.com/Sdnote)


## 相关功能
功能列表

* 图片识别
* 语音识别
* 添加笔记
	* 手账
	* 日记
	* 学习笔记
	* 等等....
* 添加提醒
	* 支持设置
		* 提醒时间
		* 开始时间
		* 结束时间
* 语言自动识别
* 注册用户
	* 修改密码
	* 找回密码
* 分享笔记
* 分享日程
 
## 服务器

### 服务器依赖

* jdk:1.8+
* 数据库：mysql
* 服务器容器：tomcat
* 服务器框架：Jersey

### 服务器平台

* windows
* linux

### 服务器配置需求

* 任何机器都可

## 思考

### 编写思路
服务器端基于Restful的的Jersey，只提供数据接口，把文字处理一部分交给服务端，一部分交给用户。使用腾讯云的ORCapi能够很轻松的将图片中的文字识别，基于此来交给用户。

### 编写水平
作为第一次用框架来写api，框架和api也都是第一次，上手还是挺有难度。但是作为有一点点的开发经验的来讲，这都不是问题，甚至还很简单。

作为练手的项目在好不过，也同时让我领略到了框架的魅力所在。

## 注意

#### json支持
在接收请求的过程中我决定只接收json，所以需要json包的依赖，同时在作为参数的时候例如：

	@POST
	@Path("/Login")
	@Produces("application/json")
	@Consumes(MediaType.APPLICATION_JSON)
	public LoginUser test(Object name) throws SQLException {
		JSONObject RequestUser = JSONObject.fromObject(name);
		String userName = "";
		String password = "";
		try {

			userName = RequestUser.getString("userName");
			password = RequestUser.getString("password");
		} catch (Exception e) {
			return new LoginUser("body error", 
				"body error", "body error", "body error",
					 "body error");
		}
		LoginUser user = date.validateUser(userName, password);
		return user;
	}

接收直接接收json对象是会报错，但是postman的确发送了json也证明可以接收，那么我想可能是本事参数问题，所以用Object来接收，然后在转化成json对象，这样就不会报错。

### api识别
接入腾讯云的ORC的api接口，途中需要将body以及参写Header，多次的失败返回error，在多次无果之后准备用python来写这块逻辑，在用pyhton的过程中发现可能是我body本身的原因。

直接用map来写入是错误的，后来我将body数据封装到json对象中，然后在
httpPost.setEntity() 这样数据传送成功，返回OK。

	// 使用URL实体转换工具
	// 需要传递一个 json
	JSONObject param = new JSONObject();
	param.put("appid", "1252209381");
	param.put("bucket", "");
	param.put("url", url);
	StringEntity se = new StringEntity(param.toString()	
	httpPost.setEntity(se);



## 想法
### 关于Server
Server端虽然是我做的，但是依然有很多不足和很多处理不成熟的地方，我会在后续对这些进行更新。

### 关于团队
我们团队也还不成熟，包括上一个网易云的项目，就因为我们的水平和知识水平还不够，问题远远还不能解决导致项目停歇，只能等到闲来无事学习充能的时候才能完成。

### 关于Sdnote
Sdnote是[楠槡](https://github.com/raphaelli)提出的，我们最开始的是基于一个课程所想出来的。主要目的还是想整合我们的日常操作，减轻我们的负担，同时也想让人们回归书写，记住每一刻。


## 感谢

### 特别感谢

此前一直没有框架编写经验，特别是接触到Restful后的Jersey，在英语如此渣的情况下没有想看官方文档的欲望。遍历了谷歌和百度的所有Dome以及说明都很晦涩，找了很久才找到[waylau](https://github.com/waylau)大牛编写的的Jersey的说明目录。

#### 说明文档
[用Jersey构建RESTful服务](https://www.kancloud.cn/wizardforcel/rest-service-with-jersey/151186)

#### waylau Github

[waylau github](https://github.com/waylau)


#### 个人博客
[柳伟卫/老卫/Way Lau's Personal Site](https://waylau.com)


### 杂碎
感谢之情溢于言表，让我突然对开源主义有了更多的思考。

我们都是<b>站在巨人的肩膀上的。</b>


## update

此更新与2018/4/27项目的第一个版本也完成了。


但是这仅仅是一个开始。

* 2018/4/26 V0.1上线
* 2018/4/26 说明文档上线


## api文档说明

请移步至DOC

[API文档说明](/DOC)