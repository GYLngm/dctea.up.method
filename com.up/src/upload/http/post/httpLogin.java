package upload.http.post;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;


import org.apache.http.HttpEntity;

import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.client.LaxRedirectStrategy;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

public class httpLogin {

	private String url;
	private String usr;
	private String password;
	private String filepass;
	private String loginrnd = null;
	List<Cookie> passCookie=new ArrayList<Cookie>();
	

	public httpLogin(String url,String usr,String password) {
		this.url=url;
		this.usr=usr;
		this.password=password;
	}
	public void setFilepass(String res) {
		
        this.filepass=res;
	}
	public String getFilepass() {
		
		return this.filepass;
	}
	public List<Cookie> getcookies() {
		return this.passCookie;
	}
	public void setLoginrnd(String rnd) {
		this.loginrnd = rnd;
	}
	public String getLoginrnd() {
		return this.loginrnd;
	}
	public void addcookies(Cookie cookie) {
		this.passCookie.add(cookie);
	}
	private void setcookies(List<Cookie> cookies) {
		this.passCookie.addAll(cookies);
	}
	
	public CloseableHttpClient connector() {
		HttpPost post=new HttpPost(this.url);
		HttpGet redirpos = null;
		HttpClientContext context=HttpClientContext.create();
		
		BasicCookieStore cookieStore = new BasicCookieStore(); 
		
		CloseableHttpClient htpcl=HttpClients.custom().setDefaultCookieStore(cookieStore).setRedirectStrategy(new LaxRedirectStrategy()).build();
		
		post.setHeader("(Request-Line)","POST /login HTTP/1.1");  
	    post.setHeader("User-Agent","Mozilla/5.0 (Windows NT 6.1; WOW64; rv:15.0) Gecko/20100101 Firefox/15.0.1");     
	    post.setHeader("Accept-Language","zh-cn,zh;q=0.8,en-us;q=0.5,en;q=0.3");  
	    post.setHeader("Accept-Encoding","gzip, deflate");  
	    post.setHeader("Accept","text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");  
	    post.setHeader("Connection","keep-alive");  
		
		List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();

		
		urlParameters.add(new BasicNameValuePair("enews","login"));
		urlParameters.add(new BasicNameValuePair("username", this.usr));
		urlParameters.add(new BasicNameValuePair("password", this.password));
		urlParameters.add(new BasicNameValuePair("equestion", "0"));
		urlParameters.add(new BasicNameValuePair("eanswer", ""));
		urlParameters.add(new BasicNameValuePair("adminwindow", "0"));
		for(int i=0;i<5;i++) {
			urlParameters.add(new BasicNameValuePair("empiercmskey"+(i+1), ""));
		}

		try {
			post.setEntity(new UrlEncodedFormEntity(urlParameters));
			CloseableHttpResponse response=htpcl.execute(post,context);
			htpcl.execute(post);
			
			HttpEntity ex=response.getEntity();
			String res=EntityUtils.toString(ex,"UTF-8");
			
			//passCookie = context.getCookieStore().getCookies();
			setcookies(context.getCookieStore().getCookies());
			
			System.out.println("µÇÂ¼¶¯×÷\n");
			System.out.println("----------------------------------------------");
			System.out.println("Response content : \n"+res);
			
			System.out.println("----------------------------------------------");
			for(Cookie e:passCookie) {
				System.out.println("Login Cookies: "+e.getName()+" : "+e.getValue());
				if(e.getName().equals("aardlloginrnd")) {
					setLoginrnd(e.getValue());
				}
			}

			post.releaseConnection();

		}catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}catch(IOException e) {
			e.printStackTrace();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return htpcl;
	}
}
