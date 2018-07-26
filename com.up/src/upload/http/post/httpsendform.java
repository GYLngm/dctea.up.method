package upload.http.post;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TimeZone;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import Modal.AnimeModal;
import Modal.MovieModal;
import Modal.pictureModal;
import upload.http.params.Configuration;
import upload.sql.jdbclink;

public class httpsendform {
	private piceditor pictureHandle;
	private String bclassid;
	private String classid;
	
	private SimpleDateFormat df2 = new SimpleDateFormat("yyy-MM-dd");
	private arrinfos formparam;
	private CloseableHttpClient htpcl;
	private List<Cookie> cookies = new ArrayList<Cookie>();
	
	public arrinfos getFormparam() {
		return formparam;
	}

	public void setFormparam(arrinfos formparam) {
		this.formparam = formparam;
	}
	public String getClassid() {
		return classid;
	}

	public void setClassid(String classid) {
		this.classid = classid;
	}
	
	public String getBclassid() {
		return bclassid;
	}

	public void setBclassid(String bclassid) {
		this.bclassid = bclassid;
	}
	public void passBclassid(httpsendpic pic) {
		pic.setBclassid(this.bclassid);
	}
	public void passHeader(httpsendpic header){
		try {
			header.setRequestHeader(requestHeader());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public httpsendform(CloseableHttpClient htpcl,List<Cookie> cookies) {
		this.formparam=new arrinfos();
		this.htpcl = htpcl;
		this.cookies.addAll(cookies);
		this.formparam.initalParameterList();
	    df2.setTimeZone(TimeZone.getTimeZone("GMT+8"));
	}

	public List<Header> requestHeader() throws Exception {
		List<Header> hbody = new ArrayList<Header>();
		hbody.add(new BasicHeader("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:57.0) Gecko/20100101 Firefox/57.0"));
		hbody.add(new BasicHeader("Accept-Language","zh-cn,zh;q=0.8,en-us;q=0.5,en;q=0.3"));
		hbody.add(new BasicHeader("Accept-Encoding","gzip, deflate"));
		hbody.add(new BasicHeader("Content-type","application/x-www-form-urlencoded; charset=utf-8"));
		if(this.bclassid!=null) {
			hbody.add(new BasicHeader("Refer","http://"+Configuration.URL.getValue()+"/e/admin99-cn/ListNews.php?bclassid="+this.bclassid+"&classid="+this.classid));

		}else {
			throw new Exception("unknown bclassid!");
		}
		hbody.add(new BasicHeader("Host","localhost"));
		return hbody;
	}
	
	public void wrappingMovieData(CloseableHttpClient htpcl,MovieModal movie) {
	    SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
	    
	    df1.setTimeZone(TimeZone.getTimeZone("GMT+8"));
	    
	    long unixtimestamp = System.currentTimeMillis()/1000;
	    
	    this.formparam.putTitle(movie.getMovieName().trim());
	    this.formparam.putArea(movie.getNation());
	    this.formparam.putBigclass(this.bclassid);
	    this.formparam.putClass(this.classid);
	    this.formparam.putDirector(movie.getDirector());
	    this.formparam.putEcmsFrom("http://"+Configuration.URL.getValue()+"/e/admin99-cn/ListNews.php?bclassid="+this.bclassid+"&classid="+this.classid);
	    this.formparam.putLanguage(movie.getLanguage());
	    this.formparam.putMovieSay(movie.getDescription());
	    this.formparam.putMovietime(movie.getYear());
	    this.formparam.putPlayer(movie.getProtagonist());
	    this.formparam.putNewstempid("6");
	    this.formparam.putNewstime(df1.format(new Date()));
	    this.formparam.putNewsPath(this.df2.format(new Date()));
	    this.formparam.putMovie(movie.getMovieName()+"$"+movie.getVideoUrl());
	    this.formparam.putFilepass(unixtimestamp);
	    this.formparam.putKeyWord(movie.getKeyWord());
		
		/////////////////////////////////////////////////////////////////
		//上传图片
	    if(movie.getPhotourl() != "null") {
	    	pictureHandle=new piceditor(movie.getPhotourl(),this.classid,unixtimestamp);
	    	pictureHandle.setBigpicturepath(htpcl, movie.getPhotourl());
		}
		//pictureHandle.setSmallpicturepath(htpcl, movie.getPhotourl());
		//pictureHandle.setMaxpicturepath(htpcl, movie.getPhotourl());
		//pictureHandle.setIndexpicturepath(htpcl, movie.getPhotourl());
		//上传结束
		/////////////////////////////////////////////////////////////////
	}
	public void wrappingAnimeData(CloseableHttpClient htpcl,AnimeModal anime) {
	    SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式    
	    df1.setTimeZone(TimeZone.getTimeZone("GMT+8"));
	    long unixtimestamp = System.currentTimeMillis()/1000;
	    
	    formparam.putTitle(anime.getMovieName());
	    formparam.putArea("日本");
	    formparam.putBigclass(this.bclassid);
	    formparam.putClass(String.valueOf(anime.getTypenumber()));
	    formparam.putEcmsFrom("http://"+Configuration.URL.getValue()+"/e/admin99-cn/ListNews.php?bclassid="+this.bclassid+"&classid="+this.classid);
	    formparam.putLanguage(anime.getLanguage());
	    //formparam.putMovieSay(anime.getDescription());
	    if(anime.getStatus() == "連載中"||anime.getStatus() == "连载中") {
	    	formparam.putLz(false);
	    	formparam.putState(anime.getStatus());
	    }else {
	    	formparam.putLz(true);
	    	formparam.putState("已完结");
	    }
	    formparam.putMovietime(anime.getYear());
	    formparam.putNewstempid("6");
		formparam.putNewstime(df1.format(new Date()));
		formparam.putNewsPath(this.df2.format(new Date()));
		formparam.putMovie(anime.getVideoUrl());
		formparam.putFilepass(unixtimestamp);
		formparam.putKeyWord(anime.getKeyWord());
		
		/////////////////////////////////////////////////////////////////
		//上传图片
		if(!anime.getPhotourl().isEmpty()) {
			pictureHandle=new piceditor(anime.getPhotourl(),this.classid,unixtimestamp);
			pictureHandle.setBigpicturepath(htpcl, anime.getPhotourl());
			pictureHandle.setSmallpicturepath(htpcl, anime.getPhotourl());
			pictureHandle.setMaxpicturepath(htpcl, anime.getPhotourl());
			pictureHandle.setIndexpicturepath(htpcl, anime.getPhotourl());
		}
		//上传结束
		/////////////////////////////////////////////////////////////////
		
	}
	
	public int sendForm(MovieModal movie) throws Exception {
		jdbclink check_duplicate = new jdbclink();
		boolean flag = check_duplicate.findMovieInDBbyTitle(movie.getMovieName().trim());
		int factor = 0;
		if(flag == false) {
			String requrl = "";
			if(!Configuration.URL.getValue().isEmpty()) {
				requrl = "http://"+Configuration.URL.getValue()+":"+Configuration.PORT.getValue()+"/e/admin99-cn/ecmsinfo.php";
			}else {
				requrl = "http://"+Configuration.URL.getValue()+"/e/admin99-cn/ecmsadmin.php";
			}
			HttpPost addpost=new HttpPost(requrl);
			//设置HTTP头
			for(Header h:requestHeader()) {
				addpost.setHeader(h);
			}
			//打包数据
			/*打包text数据*/
			wrappingMovieData(htpcl,movie);
			System.out.println(getClassid());
			if(this.formparam == null) {
				System.out.println("数据为空");
			}
	
			String value = "";
			String key = "";
			
			List<NameValuePair> urlParameters2 = new ArrayList<NameValuePair>();
			Set set=this.formparam.getarrainfo().keySet();
			if(!this.formparam.getarrainfo().isEmpty()) {
				for(Iterator iter = set.iterator();iter.hasNext();) {
					key = iter.next().toString();
					value = this.formparam.getarrainfo().get(key).toString();
					urlParameters2.add(new BasicNameValuePair(key, value));
				}
			}
			//打包完毕
			
			addpost.setEntity(new UrlEncodedFormEntity(urlParameters2,"UTF-8"));
			try {
		      CloseableHttpResponse response2 = htpcl.execute(addpost);
		      HttpEntity entity = response2.getEntity(); 
		      
		       if (entity != null) { 
		          System.out.println("--------------------------------------"); 
		          System.out.println("Response content(upload): " + EntityUtils.toString(entity, "UTF-8")); 
		          System.out.println("--------------------------------------"); 
		        }
				}finally {
					addpost.releaseConnection();
				}
			
			factor = check_duplicate.queryIdMovieByTitle(movie.getMovieName());
		} else {
			factor = 0;
			  System.out.println("--------------------------------------"); 
	          System.out.println("Response content(upload): 电影已存在，跳过。。"); 
	          System.out.println("--------------------------------------"); 
		}
		check_duplicate.disconnect();
		return factor;
	}
	public int sendFormAnime(AnimeModal anime) throws Exception {
		jdbclink check_duplicate = new jdbclink();
		boolean flag = check_duplicate.findMovieInDBbyTitle(anime.getMovieName().trim());
		int factor = 0;
		if(flag == false) {
			String requrl = "";
			if(!Configuration.URL.getValue().isEmpty()) {
				requrl = "http://"+Configuration.URL.getValue()+":"+Configuration.PORT.getValue()+"/e/admin99-cn/ecmsinfo.php";
			}else {
				requrl = "http://"+Configuration.URL.getValue()+"/e/admin99-cn/ecmsadmin.php";
			}
		HttpPost addpost=new HttpPost(requrl);
		//设置HTTP头
		for(Header h:requestHeader()) {
			addpost.setHeader(h);
		}
		//打包数据
		/*打包text数据*/
		wrappingAnimeData(htpcl,anime);
		if(this.formparam == null) {
			System.out.println("数据为空");
		}

		String value = "";
		String key = "";
		
		List<NameValuePair> urlParameters2 = new ArrayList<NameValuePair>();
		Set set=this.formparam.getarrainfo().keySet();
		if(!this.formparam.getarrainfo().isEmpty()) {
			for(Iterator iter = set.iterator();iter.hasNext();) {
				key = iter.next().toString();
				value = this.formparam.getarrainfo().get(key).toString();
				urlParameters2.add(new BasicNameValuePair(key, value));
			}
		}
		//打包完毕
		
		addpost.setEntity(new UrlEncodedFormEntity(urlParameters2,"UTF-8"));
		try {
	      CloseableHttpResponse response2 = htpcl.execute(addpost);
	      HttpEntity entity = response2.getEntity(); 
	      
	       if (entity != null) { 
	          System.out.println("--------------------------------------"); 
	          System.out.println("Response content: " + EntityUtils.toString(entity, "UTF-8")); 
	          System.out.println("--------------------------------------"); 
	        }
			}finally {
				addpost.releaseConnection();
			}
		factor = check_duplicate.queryIdMovieByTitle(anime.getMovieName());
		} else {
			  System.out.println("--------------------------------------"); 
	          System.out.println("Response content(upload): 电视剧已存在，跳过。。"); 
	          System.out.println("--------------------------------------"); 
		}
		return factor;
	}

	public void delVideoInCMS(int id) throws ClassNotFoundException, SQLException {
		String reques = "http://"+Configuration.URL.getValue()+":"+Configuration.PORT.getValue()+"/e/admin99-cn/ecmsinfo.php";
		
	}
	
	
	public boolean closeHttpConnection(CloseableHttpClient htpcl) throws IOException {
		boolean check = false;
		try {
			htpcl.close();
			check = true;
		}catch(ClientProtocolException e) {
			e.printStackTrace();
			check = false;
		}
		return check;
	}
	
}
