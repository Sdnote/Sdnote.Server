# SDnote API 文档

![logo](https://avatars2.githubusercontent.com/u/38611917?s=200&v=4)

## BB几句
本说明是SDnote的api说明，请开发者参照说明食用。

## 测试api：
### URL:http://dstantside.com/Sdnote/api/test
### 请求方式:get

该api用于测试开发者是否能正常连接api服务器，同样也是测试api服务器是否是开启状态。

返回值是：Hello World
<br>
<br>
<br>

## body异常
当发送的参数出现问题，缺失或者不正确返回异常的json。

可能出现的问题情形是：

* 没有api需求的key
* key的值类型不正确

返回的body参数：

第一种返回：

	{
	    "information": "body error",
	    "state": "500"
	}

参数说明：
* information：返回error
* state：500的状态码，说明body错误。


第二种返回：

	{
	    "email": "body error",
	    "phone": "body error",
	    "state": "body error",
	    "userName": "body error"
	}

参数说明：

* 当接收的body出现错误，那么发送的json的所有key的值都会显示body error


## 注册api：
### URL：http://dstantside.com/Sdnote/api/Login/register
### 请求方式：post

body：

	{
		"userName":"12345",
		"password":"494296145",
		"Email":"1234",
		"phone":"12345",
		"age":"12",
		"sex":1
	}

需求参数说明：

* userName：注册的用户名
* password：注册密码
* Email：注册的邮箱
* phone：注册的手机号
* age：年龄
	* 可为空
* sex：性别
	* 可为空
	* 必须为int
	* 1：女 2：男

注册正确的返回参数形式：

	{
	    "email": "true",
	    "phone": "true",
	    "state": "200",
	    "userName": "true"
	}

注册失败的返回参数形式：

	{
	    "email": "false",
	    "phone": "false",
	    "state": "400",
	    "userName": "false"
	}
<br>

	{
	    "email": "false",
	    "phone": "false",
	    "state": "400",
	    "userName": "true"
	}

* true：参数正确
* false：参数重复
* email：邮箱
  * false：邮箱已被注册
  * true：邮箱通过
* phone：手机号
  * false：手机已被注册
  * true：手机通过
* userName：用户名
  * false：用户已被注册
  * true：用户名通过  
* state：状态码
  * 200：注册成功
  * 400：注册失败
  * 401：用户名重复
  * 402：Email重复
  * 403：手机号重复
    ​
## 系统登陆api:
### URL：http://dstantside.com/Sdnote/api/Login/Login
### 请求方式：post

body：

	{
		"userName":"xxx",
		"password":"xxx"
	}

需求参数说明：

* userName:用户名
* password:密码


校验正确的返回参数形式：

	{
	    "password": "",
	    "state": "200",
	    "token": "d9804f1d047e419fb347bcb716838685",
	    "userID": "1",
	    "userName": "qq494296145"
	}
校验错误的返回参数形式:

	{
	    "password": "密码错误",
	    "state": "401",
	    "token": "",
	    "userID": "",
	    "userName": "true"
	}	
<br>

	{
	    "password": "",
	    "state": "402",
	    "token": "",
	    "userID": "",
	    "userName": "用户为空"
	}

	{
	    "password": "",
	    "state": "403",
	    "token": "",
	    "userID": "",
	    "userName": "密码为空"
	}

* password：密码信息
* state状态码
  * 400：无此用户
  * 401：密码错误
  * 200：正常
  * 402：用户为空
  * 403：密码为空
* token：返回该用户的token
* userID：返回该用户的用户id
* userName：返回该用户的用户名

## 密码修改api
### URL：http://dstantside.com/Sdnote/api/Login/backPw
### 请求方式：post

body：

	{
		"choose":1,
		"userName":"qq494296145",
		"OldPassword":"494296145",
		"NewPassword":"123456"
	}

需求参数说明：

* choose:选择码
	* 当选择数值是1时，是密码修改设置
* userName：用户名
* OldPassword:老密码密码
* NewPassword：新密码

校验正确的返回参数形式：

	{
	    "information": "success",
	    "state": "200"
	}

校验错误的返回参数形式:

	{
	    "information": "password error",
	    "state": "401"
	}
<br>

	{
	    "information": "userName error",
	    "state": "400"
	}
<br>

	{
	    "information": "error",
	    "state": "402"
	}

* information：状态信息说明
* state：状态码
	* 200：修改成功
	* 400：userName错误
	* 401：原密码错误
	* 402：数据库错误


## 密码找回api
### URL：http://dstantside.com/Sdnote/api/Login/backPw
### 请求方式：post

body：

	{
		"choose":2,
		"userName":"qq494296145",
		"phone":"1233",
		"NewPassword":"123456"
	}

需求参数说明：

* choose:选择码
	* 当选择数值是2时，是密码找回设置
* userName：用户名
* phone:手机号
* NewPassword：新密码

校验正确的返回参数形式：

	{
	    "information": "success",
	    "state": "200"
	}

校验错误的返回参数形式:

	{
	    "information": "phone error",
	    "state": "401"
	}
<br>
	
	{
	    "information": "userName error",
	    "state": "400"
	}
<br>

	{
	    "information": "error",
	    "state": "402"
	}

* information：状态信息说明
* state：状态码
	* 200：修改成功
	* 400：userName错误
	* 401：手机号错误
	* 402：数据库错误

  ​
## 增加标签api
### URL：http://dstantside.com/Sdnote/api/Note/add
### 请求方式：post

body：

	{
		"UserID":"1",
		"Token":"d9804f1d047e419fb347bcb716838685",
		"Title":"test",
		"time":"1860-01-01 12:00:00",
		"Content":"test",
		"Tag":"test"
	}

需求参数说明：

* UserID：用户ID
* Token：用户token
* Title：标签标题
* time：标签时间
	* 格式应是：xxxx-xx-xx xx:xx:xx
* Content:标签内容
* Tag：标签类型

<br>

添加正确的返回参数形式：

	{
	    "information": "success",
	    "state": "200"
	}

添加失败的返回参数形式：

	{
	    "information": "UserID or Token error",
	    "state": "400"
	}

<br>

	{
	    "information": "error",
	    "state": "401"
	}

* information：错误正确信息说明
* state：状态码
  * 200：成功
  * 400：ID或者Token错误
  * 401：数据库错误
    ​
## 更新标签api
### URL：http://dstantside.com/Sdnote/api/Note/update
### 请求方式：post

body：

	{
		"noteID":"1",
		"Token":"d9804f1d047e419fb347bcb716838685",
		"Title":"test",
		"time":"1860-01-01 12:00:00",
		"Content":"tes1t",
		"Tag":"test"
	}

需求参数说明：

* noteID：需要修改的标签ID
* Token：用户Token
* Title：标签标题
* time：标签时间
* Content：标签内容
* Tag：标签类型


更新正确的返回参数形式：


	{
	    "information": "success",
	    "state": "200"
	}

更新错误的返回参数形式：

	{
	    "information": "Token error",
	    "state": "400"
	}
<br>

	{
	    "information": "error",
	    "state": "401"
	}

* information:错误信息
* state：状态码
  * 200：更新正确
  * 400：Token错误
  * 401：服务器错误
    ​
## 删除标签api
### URL：http://dstantside.com/Sdnote/api/Note/update
### 请求方式：post

body：


	{
		"noteID":"1",
		"Token":"d9804f1d047e419fb347bcb716838685",
		"UserID":"1"
	}

需求参数说明：

* noteID：需要删除的便签ID
* Token：Token
* UserID：用户ID


删除正确的返回参数形式：
	
	{
	    "information": "success",
	    "state": "200"
	}

删除错误的返回参数形式：

	{
	    "information": "UserID or Token error",
	    "state": "400"
	}
<br>

	{
	    "information": "error",
	    "state": "401"
	}

* information:错误或正确信息
* state：状态吗
  * 200：删除成功
  * 401：数据库错误
  * 400：UserID以及Token不匹配
    ​
## 获取全部标签api
### URL：http://dstantside.com/Sdnote/api/Note/update
### 请求方式：post

body：

	{
		"Token":"d9804f1d047e419fb347bcb716838685",
		"UserID":"1"
	}

需求参数说明：


* Token：Token
* UserID：用户ID

获取所有便签的正确返回参数形式：

	{
	    "data": [
	        {
	            "content": "test",
	            "noteID": "4",
	            "noteTime": "1860-01-01 12:00:00.0",
	            "tags": "test",
	            "title": "test"
	        },
	        {
	            "content": "test",
	            "noteID": "5",
	            "noteTime": "1860-01-01 12:00:00.0",
	            "tags": "test",
	            "title": "test"
	        },
	        {
	            "content": "test",
	            "noteID": "8",
	            "noteTime": "1860-01-01 12:00:00.0",
	            "tags": "test",
	            "title": "test"
	        },
	        {
	            "content": "test",
	            "noteID": "9",
	            "noteTime": "1860-01-01 12:00:00.0",
	            "tags": "test",
	            "title": "test"
	        }
	    ],
	    "State": "200",
	    "information": "success"
	}

删除错误的返回参数形式：
	
	{
	    "State": "401",
	    "information": "userid or Token error"
	}
<br>

	{
	    "State": "400",
	    "information": "error"
	}

* information:错误或正确信息
* State:状态码
  * 200：返回成功
  * 401：用户ID或Token错误
* data:返回的标签列表信息
  * title:标签标题
  * tags:标签类型
  * noteTime:标签时间
  * noteID：标签ID
  * content：标签内容
    ​
## 增加提醒API
### URL:http://dstantside.com/Sdnote/api/remid/add
### 请求方式：post

body：

	{
		"Token":"d9804f1d047e419fb347bcb716838685",
		"UserID":"1",
		"Title":"今天真开心",
		"time":"2017-02-02 12:12:32.2",
		"stratTime":"2017-02-02 12:12:32.2",
		"endTime":"2018-02-02 12:12:32.2",
		"remindTime":"2018-03-02 12:12:32.2",
		"Content":"test",
		"Tag":"test"
	}

需求参数说明：

* Token：用户Token。
* UserID：用户ID。
* Title：提醒标题
* time：提醒记录时间
* stratTime：提醒开始时间
* endTime：提醒结束时间
* remindTime：规定提醒时间
	* 应符合： stratTime < remindTime < endTime
* Content：提醒内容
* Tag：提醒类型

增加提醒成功的返回参数形式：
	
	{
	    "information": "success",
	    "state": "200"
	}

增加提醒失败的返回参数形式：

	{
	    "information": "UserID or Token error",
	    "state": "400"
	}

<br>

	{
	    "information": "error",
	    "state": "401"
	}

* information:错误或正确信息
* state：状态吗
  * 200：增加成功
  * 401：数据库错误
  * 400：UserID以及Token不匹配
    ​
## 更新提醒api
### URL：http://dstantside.com/Sdnote/api/remid/update
### 请求方式：post

body：

	{
		"Token":"d9804f1d047e419fb347bcb716838685",
		"remindID":"1",
		"Title":"今天真开心",
		"time":"2017-02-02 12:12:32.2",
		"stratTime":"2017-02-02 12:12:32.2",
		"endTime":"2018-02-02 12:12:32.2",
		"remindTime":"2018-03-02 12:12:32.2",
		"Content":"tes111t",
		"Tag":"tes111t"
	}

需求参数说明：

* Token：用户Token
* remindID：提醒id
* Title：标题
* time：记录时间
* stratTime：开始提醒时间
* endTime：结束时间
* remindTime：提醒时间
	* 应符合：stratTime < remindTime < endTime
* Content：提醒内容
* Tag：提醒类别

更新成功参数返回形式：

	{
	    "information": "success",
	    "state": "200"
	}

更新失败参数返回形式：
		
	{
	    "information": "Token error",
	    "state": "400"
	}
<br>

	{
	    "information": "remindID error",
	    "state": "400"
	}
<br>

	{
	    "information": "error",
	    "state": "401"
	}

* information:错误或正确信息
* state：状态吗
  * 200：更新成功
  * 401：数据库出现错误
  * 400：Token或remindID不匹配
    ​
## 删除提醒api
### URL：http://dstantside.com/Sdnote/api/remid/update
### 请求方式：post

请求参数形式：
	
	{
		"Token":"d9804f1d047e419fb347bcb716838685",
		"remindID":"1",
		"UserID":"1"
	}

需求参数说明：

* Token：用户Token
* remindID：记录ID
* UserID：用户ID


删除成功参数返回形式：
	
	{
	    "information": "success",
	    "state": "200"
	}

失败失败参数返回形式：

	{
	    "information": "UserID or Token error",
	    "state": "400"
	}
<br>
	
	{
	    "information": "remindID error",
	    "state": "400"
	}
<br>
	
	{
	    "information": "error",
	    "state": "401"
	}


* information:错误或正确信息
* state：状态吗
  * 200：更新成功
  * 401：数据库出现错误
  * 400：Token或remindID或者UserID出现错误
    ​
## 获取所有提醒api
### URL：http://dstantside.com/Sdnote/api/remid/getAll
### 请求方式：post

body：

	{
		"Token":"d9804f1d047e419fb347bcb716838685",
		"UserID":"1"
	}

需求参数说明：

* Token：用户Token
* UserID：用户ID




获取成功参数返回形式：

	{
	    "data": [
	        {
	            "content": "test",
	            "endTime": "2018-02-02 12:12:32.0",
	            "remindID": "1",
	            "remindTime": "2018-03-02 12:12:32.0",
	            "stratTime": "2017-02-02 12:12:32.0",
	            "tag": "tes111t",
	            "time": "2017-02-02 12:12:32.0",
	            "title": "?????"
	        }
	    ],
	    "State": "200",
	    "information": "success"
	}

获取失败参数返回形式：

	{
	    "State": "401",
	    "information": "userid or Token error"
	}
<br>
	
    {
        "State": "400",
        "information": "error"
    }

* information:错误或正确信息
* state：状态吗
  * 200：更新成功
  * 401：数据库出现错误
  * 400：Token或UserID出现错误
* data：返回的提醒list
  * content：日程内容
  * endTime：结束时间
  * remindID：提醒事件ID
  * remindTime：提醒时间
  * stratTime：开始时间
  * tag：tag类别
  * time：记录时间
  * title：标题
    ​
## 图片识别api
### URL：http://dstantside.com/Sdnote/api/Sdnote/orcapi
### 请求方式：post


图片样式：<br>
![](https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1524630158300&di=442a5c93bf2ff2898daf4e8c8b28178c&imgtype=0&src=http%3A%2F%2Fimg.my.csdn.net%2Fuploads%2F201303%2F05%2F1362488305_1943.jpg)



body：

	{
		"Token":"d9804f1d047e419fb347bcb716838685",
		"url":"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1524630158300&di=442a5c9
			3bf2ff2898daf4e8c8b28178c&imgtype=0&src=http%3A%2F%2Fimg.my.csdn.net%2Fuploads%2F201303%2F05%2F1362488305_1943.jpg"
	}

需求参数说明：

* Token：用户Token
* url：识别的图片


识别成功参数返回形式：


	{
	    "Results": "不存在Servlet实例\n结束Servlet\ndestroy方法\n容器装载Servlet\n初始化Servlet
			\n调用service方法\ninit方法\n创建Servlet实例\n",
	    "State": "200"
	}

识别失败参数返回形式：

	{
	    "Results": "",
	    "State": "400"
	}

* Results：识别结果
	* 空为识别失败
*  State：状态吗
	*  200：识别成功
	*  400：识别失败
