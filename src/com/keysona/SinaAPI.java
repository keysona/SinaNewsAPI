package com.keysona;

import java.awt.geom.FlatteningPathIterator;

import com.keysona.WordNews;

/** 
 *依赖包: json,okio,okhttp <br>
 *新浪api，获取新闻。支持按页获取，每次返回25或35条新闻。保存在一个WordNews数组里面。<br>
 *目前可以获取的新闻类型： <br>
 *<ul>
 *<li>最新</li>
 *<li>军事</li>
 *<li>国内</li>
 *<li>国外</li>
 *<li>社会</li>
 * </ul>
 * 例子：
 * <pre>
 * SinaAPI test = new SinaAPI test = new SinaAPI();
 *		WordNews[] news = test.getNews(SinaAPI.EXTERNAL_NEWS, 1);
 *		for(int i = 0;i&lt;news.length;i++){
 *			System.out.println("*****************");
 *			System.out.println(news[i]);
 *			System.out.println("title:"+news[i].getTitle());
 *			System.out.println("stitle"+news[i].getStitle());
 *			System.out.println("date:"+news[i].getDate());
 *			System.out.println("source:"+news[i].getSource());
 *			System.out.println("url:"+news[i].getUrl());
 *			System.out.println("comment:"+news[i].getComment());
 *		}
 *</pre>
 *参考输出：
 * <pre>
 * *****************
 * title :杭州高三女生被哈佛提前录取
 * stitle :杭州高三女生被哈佛提前录取
 * date :今天 07:19
 * source :钱江晚报
 * summary :她虽然个子小小外表柔美但个性非常坚毅，学习目标明确。
 * url :http://news.sina.cn/sh/2015-12-12/detail-ifxmpnqi6359375.d.html
 * comment :5423
 *</pre>
 *
 * @author key
 * @version 0.1
 *
 */
public class SinaAPI {
	
	//新闻类型
	//最新新闻
	/**
	 * 最新新闻
	 */
	public static final int TOP_NEWS = 0;
	private static final String TOP_URL = "http://interface.sina.cn/ent/feed.d.json?ch=mil&col=mil&page=";
	
	//国际新闻
	/**
	 * 国外新闻
	 */
	public static final int EXTERNAL_NEWS = 1;
	private static final String EXTERNAL_URL = "http://interface.sina.cn/wap_api/layout_col.d.json?col=56262&level=1%2C2&show_num=25&page=";
	
	//国内新闻
	/**
	 * 国内新闻
	 */
	public static final int INTERNAL_NEWS = 2;
	private static final String INTERNAL_URL = "http://interface.sina.cn/wap_api/layout_col.d.json?col=56261&level=1%2C2&show_num=25&page=";
	
	//社会新闻
	/**
	 * 社会新闻
	 */
	public static final int SOCIAL_NEWS = 3;
	private static final String SOCIAL_URL = "http://interface.sina.cn/wap_api/layout_col.d.json?col=56264&level=1&show_num=25&page=";
	
	//军事新闻
	/**
	 * 军事新闻
	 */
	public static final int MILITARY_NEWS = 4;
	private static final String MILITARY_URL = "http://interface.sina.cn/ent/feed.d.json?ch=mil&col=mil&page=";
	
	/**
	 * 返回一个WordNews数组。数组大小可能是25或35,这个无关紧要，使用数组的length()即可知道大小。
	 * 
	 * @param flag 新闻类型
	 * @param page 页数
	 * @return WordNews数组
	 */
	public WordNews[] getNews(int flag,int page){
		WordNews[] news = null;
		switch(flag){
		case TOP_NEWS:
			news = (WordNews[]) WordNews.getFeedTopNews(TOP_URL, page);
			break;
		case MILITARY_NEWS:
			news = (WordNews[]) WordNews.getFeedTopNews(MILITARY_URL, page);
			break;
		case EXTERNAL_NEWS:
			news = (WordNews[]) WordNews.getPublicNews(EXTERNAL_URL, page);
			break;
		case INTERNAL_NEWS:
			news = (WordNews[]) WordNews.getPublicNews(INTERNAL_URL, page);
			break;
		case SOCIAL_NEWS:
			news = (WordNews[]) WordNews.getPublicNews(SOCIAL_URL, page);
			break;
		}
		return news;
	}
	
	private void test(){
		WordNews[] news = this.getNews(INTERNAL_NEWS, 3);
		for(int i = 0;i<news.length;i++){
			System.out.println(news[i]);
		}
		System.out.println("新闻数量:"+news.length);
	}
	
	public static void main(String[] args) {
		SinaAPI test = new SinaAPI();
		test.test();
	}

}
