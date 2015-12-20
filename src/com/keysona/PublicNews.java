package com.keysona;

import java.io.IOException;
import java.sql.Date;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.zip.None;
import org.omg.CORBA.PUBLIC_MEMBER;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import com.keysona.WordNews;

public class PublicNews extends WordNews {
    public PublicNews(){
    	
    }
    public PublicNews(JSONObject tmp){
    	System.out.println(tmp);
		this.date = get(tmp, "cTime", "str");
		title = get(tmp, "title", "str");
		stitle = get(tmp, "stitle", "str");
		source = get(tmp, "source", "str");
		summary = get(tmp, "summary", "str");
		url = get(tmp, "URL","str");
		comment = get(tmp, "comment", "int");
		image_url = getImageURL(tmp);
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
    
    private String getImageURL(JSONObject tmp){
    	JSONObject allPics = tmp.getJSONObject("allPics");
    	JSONArray array = allPics.getJSONArray("pics");
    	System.out.println(array);
    	if(array.length()>=1){
    		JSONObject img = array.getJSONObject(0);
    		String temp = img.getString("note");
    		if("".equals(temp)){
    			temp = img.getString("imgurl");
    		}
    		return temp;
    	}else{
    		return "";
    	}
    }
    
    OkHttpClient client = new OkHttpClient();
	 String run(String url) throws IOException {
	    Request request = new Request.Builder()
	        .url(url)
	        .build();
	    Response response = client.newCall(request).execute();
	    return response.body().string();
	  }
    
    
	public static void main(String[] args) {
		PublicNews test = new PublicNews();
		try{
			JSONObject resp = new JSONObject(test.run("http://interface.sina.cn/wap_api/layout_col.d.json?col=56264&level=1&show_num=30&page=2"));
			
			JSONObject result = resp.getJSONObject("result");
			JSONObject data = result.getJSONObject("data");
			String total_news = String.valueOf(data.getInt("total"));
			String count = String.valueOf(data.getInt("count"));
			System.out.println("新闻总数:" + total_news);
			System.out.println("当前数量:" + count);
			JSONArray array = data.getJSONArray("list");
			for(int i = 0;i<array.length();i++){
				JSONObject tmp = array.getJSONObject(i);
//				System.out.println(tmp);
				PublicNews news = new PublicNews(tmp);
				System.out.println(news.toString());
			}
		}catch(IOException e){
			e.printStackTrace();
		}
		
	}
}
