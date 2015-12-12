# SinaNewsAPI
解析新浪新闻后台，获取相关新闻信息。

支持按页获取，每次返回25或35条新闻。

保存在一个WordNews数组里面。
## 依赖

* **json**  
* **okio**  
* **okhttp**

##目前可以获取的新闻类型： <br>
 * 最新
 * 军事
 * 国内
 * 国外
 * 社会
 
##例子：
```java
  SinaAPI test = new SinaAPI test = new SinaAPI();
 		WordNews[] news = test.getNews(SinaAPI.EXTERNAL_NEWS, 1);
 		for(int i = 0;i&lt;news.length;i++){
 			System.out.println("*****************");
 			System.out.println(news[i]);
 			System.out.println("title:"+news[i].getTitle());
			System.out.println("stitle"+news[i].getStitle());
			System.out.println("date:"+news[i].getDate());
 			System.out.println("source:"+news[i].getSource());
 			System.out.println("url:"+news[i].getUrl());
 			System.out.println("comment:"+news[i].getComment());
 		}
 ```
 参考输出：
```java
  *****************
  title :杭州高三女生被哈佛提前录取
  stitle :杭州高三女生被哈佛提前录取
  date :今天 07:19
  source :钱江晚报
  summary :她虽然个子小小外表柔美但个性非常坚毅，学习目标明确。
  url :http://news.sina.cn/sh/2015-12-12/detail-ifxmpnqi6359375.d.html
  comment :5423
  ...
```
