package com.keysona;

import java.io.IOException;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.zip.None;
import org.omg.CORBA.PUBLIC_MEMBER;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;


public class FeedTopNews extends WordNews {

    public FeedTopNews(){
    	
    }
    
    public FeedTopNews(JSONObject tmp){
    	date = get(tmp,"date", "str");
    	url = get(tmp, "link", "str");
    	source = get(tmp, "source", "str");
    	title = get(tmp, "title", "str");
    	stitle = get(tmp, "wap_title", "str");
    	summary = get(tmp, "intro", "str");
    	comment = get(tmp, "comment", "int");
    }
    
    private String get(JSONObject tmp,String key,String type){
    	if(type.equals("str")){
    		if(tmp.has(key) && !tmp.isNull(key)){
    			return tmp.getString(key);
    		}
    		return "";
    	}else if(type.equals("int")){
    		return tmp.has(key) ? String.valueOf(tmp.getInt(key)): "";
    	}else if(type.equals("jsonObject")){
    		JSONObject a1 = tmp.getJSONObject(key);
    		return get(a1, "total", "int");
    	}
    	return "";
    }
    
    OkHttpClient client = new OkHttpClient();
	 String run(String url) throws IOException {
	    Request request = new Request.Builder()
	        .url(url)
	        .build();
	    Response response = client.newCall(request).execute();
	    return response.body().string();
	  }
    
    public static void main(String[] args){
    	FeedTopNews test = new FeedTopNews();
    	try{
    		JSONObject resp = new JSONObject(test.run("http://interface.sina.cn/ent/feed.d.json?ch=mil&col=mil&show_num=10&page=3"));
    		JSONArray array = resp.getJSONArray("data");
    		for(int i = 0;i<array.length();i++){
    			JSONObject tmp = array.getJSONObject(i);
    			FeedTopNews news = new FeedTopNews(tmp);
    			System.out.println(news.toString());
    		}
    	}catch(IOException e){
    		e.printStackTrace();
    	}

	}
}
