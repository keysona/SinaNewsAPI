package com.keysona;

import java.io.IOException;

import org.json.JSONArray;
import org.json.JSONObject;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;


/**
 * 一条新闻实体，包括标题，日期，来源，总结，原文链接地址，评论数目
 * 
 * @author key
 * @version 0.1
 *
 */
public class WordNews {
	
	protected String date;
	protected String title;
	protected String stitle;
	protected String source;
	protected String summary;
	protected String url;
	protected String comment;
	
	private static OkHttpClient client = new OkHttpClient();
	
	/**
	 * 日期 格式:今天 04:49
	 * 
	 * @return String
	 */
	public String getDate() {
		return date;
	}
	
	/**
	 * 新闻标题(电脑)
	 * 
	 * @return String 
	 */
	public String getTitle() {
		return title;
	}
	
	/**
	 * 新闻标题(手机)
	 * 
	 * @return String
	 */
	public String getStitle() {
		return stitle;
	}
	
	/**
	 * 新闻来源
	 * 
	 * @return String 
	 */
	public String getSource() {
		return source;
	}

	/**
	 * 新闻总结
	 * 
	 * @return String 
	 */
	public String getSummary() {
		return summary;
	}
	
	/**
	 * 新闻链接
	 * 
	 * @return String
	 */
	public String getUrl() {
		return url;
	}
	
	/**
	 * 新闻评论数
	 * 
	 * @return String 
	 */
	public String getComment() {
		return comment;
	}

	public WordNews(){
		
	}
	
	public String toString(){
		return  "title :" + title + "\n" +
				"stitle :" + stitle + "\n" +
				"date :" + date + "\n" +
				"source :" + source + "\n" +
				"summary :" + summary + "\n" +
				"url :" + url + "\n" +
				"comment :" + comment + "\n";
	}
	 private static String run(String url) throws IOException {
	    Request request = new Request.Builder()
	        .url(url)
	        .build();
	    Response response = client.newCall(request).execute();
	    return response.body().string();
	  }
	
	 static PublicNews[] getPublicNews(String url,int page){
		System.out.println(url+String.valueOf(page));
		PublicNews[] news = new PublicNews[25];
		try{
			JSONObject resp = new JSONObject(WordNews.run(url+String.valueOf(page)));
			JSONObject result = resp.getJSONObject("result");
			JSONObject data = result.getJSONObject("data");
			String total_news = String.valueOf(data.getInt("total"));
			String counts = String.valueOf(data.getInt("count"));
			System.out.println("新闻总数:" + total_news);
			
			JSONArray array = data.getJSONArray("list");
			System.out.println("当前数量:" + array.length());
			for(int i = 0;i<array.length();i++){
				JSONObject tmp = array.getJSONObject(i);
				news[i] = new PublicNews(tmp);
				System.out.println(news[i].toString());
			}
		}catch(IOException e){
			e.printStackTrace();
		}
		return news;
	}
	
	static FeedTopNews[] getFeedTopNews(String url,int page){
		System.out.println(url+String.valueOf(page));
		//第一页35条，之后的都是25条
		FeedTopNews[] news = page==1?news = new FeedTopNews[35]:new FeedTopNews[25];
		
		try{
    		//最新新闻
//    		JSONObject resp = new JSONObject(test.run("http://interface.sina.cn/news/feed_top_news.d.json?&page=1"));
    		//军事新闻
			JSONObject resp = new JSONObject(WordNews.run(url+String.valueOf(page)));
			JSONArray array = resp.getJSONArray("data");
    		System.out.println("新闻数量"+array.length());
    		for(int i = 0;i<array.length();i++){
    			JSONObject tmp = array.getJSONObject(i);
    			news[i] =new FeedTopNews(tmp);
    			System.out.println(news[i].toString());
    		}
    	}catch(IOException e){
    		e.printStackTrace();
    	}
		return news;
	}
	
	private static void testPublicNews(){
		PublicNews[] news;
		news = WordNews.getPublicNews("http://interface.sina.cn/wap_api/layout_col.d.json?col=56264&level=1&show_num=25&page=", 1);
		for(int i = 0;i<news.length;i++){
			System.out.println(news[i].toString());
		}
		System.out.println("数量:"+news.length);
	}
	
	private static void testFeedTopNews(){
		String militray = "http://interface.sina.cn/ent/feed.d.json?ch=mil&col=mil&show_num=20&page=";
		String top = "http://interface.sina.cn/ent/feed.d.json?ch=mil&col=mil&page=";
		FeedTopNews[] news = WordNews.getFeedTopNews(militray,1);
		for(int i = 0;i<news.length;i++){
			System.out.println(news[i]);
		}
		System.out.println("数量:" + news.length);
	}
	
	public static void main(String[] args) {
		WordNews test = new WordNews();
		test.testFeedTopNews();
		test.testPublicNews();
		WordNews.testPublicNews();
		WordNews.testFeedTopNews();
	}
}
