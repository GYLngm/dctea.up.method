package upload.http.post;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import upload.http.params.Configuration;

public class piceditor {
	
	
	private HttpPost picPost = new HttpPost("http://"+Configuration.URL.getValue()+"/e/admin99-cn/ecmseditor/ecmseditor.php");
	private String pic_url;
	private String bigpic_path;
	private String classid;
	private String no = "";
	private static long filepass;
	private String pic_path = "";
	private String field;
	
	private static Map<String,Object> pic_param = new HashMap<String,Object>();
	
	public piceditor(String url,String classid,long eTS) {
		this.pic_url = url;
		this.classid = classid;
		this.filepass = eTS;
		
		pic_param.put("infoid", 0);
		pic_param.put("enews", "TranFile");
		pic_param.put("type",1);
		pic_param.put("modtype",0);
		pic_param.put("doing", 1);
		pic_param.put("fstb", 1);
		pic_param.put("sinfo", 0);
		pic_param.put("width", 105);
		pic_param.put("height", 118);
		pic_param.put("Submit3", "ÉÏ´«");
	}
	
	public List<Header> requestHeader(String field) {
		List<Header> hbody = new ArrayList<Header>();
		if(this.classid!=null&&field!=null) {
			hbody.add(new BasicHeader("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:57.0) Gecko/20100101 Firefox/57.0"));
			hbody.add(new BasicHeader("Accept-Language","zh-cn,zh;q=0.8,en-us;q=0.5,en;q=0.3"));
			hbody.add(new BasicHeader("Accept-Encoding","gzip, deflate"));
			hbody.add(new BasicHeader("Content-type","application/x-www-form-urlencoded; charset=utf-8"));
			hbody.add(new BasicHeader("Refer","http://"+Configuration.URL.getValue()+"/e/admin99-cn/ecmseditor/FileMain.php?type=1&classid="+this.classid+"&filepass="+this.filepass+"&doing=1&field="+field));
			hbody.add(new BasicHeader("Host","localhost"));
		}
		
		return hbody;
	}
	
	public void setBigpicturepath(CloseableHttpClient htpcl,String org) {
		
		for(Header h:requestHeader("bigpic")) {
			picPost.setHeader(h);
		}
		this.pic_param.put("tranurl", org);
		this.pic_param.put("classid", this.classid);
		this.pic_param.put("filepass", this.filepass);
		
		List<NameValuePair> urlParameters2 = new ArrayList<NameValuePair>();
		Set set = this.pic_param.keySet();
		String key = "";
		for(Iterator it = set.iterator();it.hasNext();) {
			key = (String) it.next().toString();
			urlParameters2.add(new BasicNameValuePair(key,(String) this.pic_param.get(key).toString()));
		}
		
		try {
			sendpicPost(htpcl,new UrlEncodedFormEntity(urlParameters2,"UTF-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}
	
	public void setIndexpicturepath(CloseableHttpClient htpcl,String org) {
		
		for(Header h:requestHeader("indexpic")) {
			picPost.setHeader(h);
		}
		this.pic_param.put("tranurl", org);
		this.pic_param.put("classid", this.classid);
		this.pic_param.put("filepass", this.filepass);
		
		List<NameValuePair> urlParameters2 = new ArrayList<NameValuePair>();
		Set set = this.pic_param.keySet();
		String key = "";
		for(Iterator it = set.iterator();it.hasNext();) {
			key = (String) it.next().toString();
			urlParameters2.add(new BasicNameValuePair(key,(String) this.pic_param.get(key).toString()));
		}
		
		try {
			sendpicPost(htpcl,new UrlEncodedFormEntity(urlParameters2,"UTF-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}
	
public void setMaxpicturepath(CloseableHttpClient htpcl,String org) {
		
		for(Header h:requestHeader("maxpic")) {
			picPost.setHeader(h);
		}
		this.pic_param.put("tranurl", org);
		this.pic_param.put("classid", this.classid);
		this.pic_param.put("filepass", this.filepass);
		
		List<NameValuePair> urlParameters2 = new ArrayList<NameValuePair>();
		Set set = this.pic_param.keySet();
		String key = "";
		for(Iterator it = set.iterator();it.hasNext();) {
			key = (String) it.next().toString();
			urlParameters2.add(new BasicNameValuePair(key,(String) this.pic_param.get(key).toString()));
		}
		
		
		try {
			sendpicPost(htpcl,new UrlEncodedFormEntity(urlParameters2,"UTF-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}
	
	public void setSmallpicturepath(CloseableHttpClient htpcl,String org) {
		for(Header h:requestHeader("titlepic")) {
			picPost.setHeader(h);
		}
		this.pic_param.put("tranurl", org);
		this.pic_param.put("classid", this.classid);
		this.pic_param.put("filepass", this.filepass);
		this.pic_param.put("getsmall", 1);
		
		List<NameValuePair> urlParameters2 = new ArrayList<NameValuePair>();
		Set set = this.pic_param.keySet();
		String key = "";
		for(Iterator it = set.iterator();it.hasNext();) {
			key = (String) it.next().toString();
			urlParameters2.add(new BasicNameValuePair(key,(String) this.pic_param.get(key).toString()));
		}
		
		try {
			sendpicPost(htpcl,new UrlEncodedFormEntity(urlParameters2,"UTF-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}
	
	private void sendpicPost(CloseableHttpClient htpcl,HttpEntity sendentity) {
		try {
			this.picPost.setEntity(sendentity);
			CloseableHttpResponse pic_response = htpcl.execute(this.picPost);
			HttpEntity responseEntity = pic_response.getEntity();
			
			System.out.println("----------------------------------------------------------\n");
			System.out.println("Response content(picture)"+EntityUtils.toString(responseEntity,"UTF-8"));
			System.out.println("----------------------------------------------------------\n");
			
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			picPost.releaseConnection();
		}
	}
	
}
