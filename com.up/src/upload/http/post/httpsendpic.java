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
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import Modal.AnimeModal;
import Modal.MovieModal;
import Modal.pictureModal;
import upload.http.params.Configuration;
import upload.sql.jdbclink;

public class httpsendpic {
	private List<Integer> ids = new ArrayList<Integer>();
	private List<pictureModal> pm = new ArrayList<pictureModal>();
	private String bclassid;
	private List<Header> requestHeader = new ArrayList<Header>();
	private SimpleDateFormat df2 = new SimpleDateFormat("yyy-MM-dd");
	private arrinfos formparam;
	private CloseableHttpClient htpcl;
	private List<Cookie> cookies = new ArrayList<Cookie>();
	private jdbclink con = null;
	Map<Integer,MovieModal> moviemap = new HashMap<Integer,MovieModal>();
	private List<String> movieTitle = new ArrayList<String>();

	Map<Integer,AnimeModal> animemap = new HashMap<Integer,AnimeModal>();
	
	public httpsendpic(CloseableHttpClient htpcl,List<Cookie> cookies) {
		this.formparam=new arrinfos();
		this.htpcl = htpcl;
		this.cookies.addAll(cookies);
		this.formparam.initalParameterList();
	    df2.setTimeZone(TimeZone.getTimeZone("GMT+8"));
	}
	
	public void wrappingMovieData(int id) throws SQLException, ClassNotFoundException {
	    SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
	    con = new jdbclink();
	    ResultSet rs = con.findfilename(id);
	    df1.setTimeZone(TimeZone.getTimeZone("GMT+8"));
	    long unixtimestamp = System.currentTimeMillis()/1000;
	    while(rs.next()) {
		    	this.formparam.putTitle(this.moviemap.get(id).getMovieName());
			    this.formparam.putArea(this.moviemap.get(id).getNation());
			    this.formparam.putBigclass(this.moviemap.get(id).getBclassid());
			    this.formparam.putClass(rs.getString("classid"));
			    this.formparam.putDirector(this.moviemap.get(id).getDirector());
			    this.formparam.putEcmsFrom("http://"+Configuration.URL.getValue()+"/e/admin99-cn/ListNews.php?bclassid="+this.bclassid+"&classid="+rs.getString("classid"));
			    this.formparam.putLanguage(this.moviemap.get(id).getLanguage());
			    this.formparam.putMovieSay(this.moviemap.get(id).getDescription());
			    this.formparam.putMovietime(this.moviemap.get(id).getYear());
			    this.formparam.putPlayer(this.moviemap.get(id).getProtagonist());
			    this.formparam.putNewstempid("6");
			    this.formparam.putNewstime(df1.format(new Date()));
			    this.formparam.putNewsPath(this.df2.format(new Date()));
			    this.formparam.putMovie(this.moviemap.get(id).getMovieName()+"$"+this.moviemap.get(id).getVideoUrl());
			    this.formparam.putFilepass(id);
			    this.formparam.putKeyWord(this.moviemap.get(id).getKeyWord());
			    this.formparam.putOldFileName(rs.getString("filename"));
			    this.formparam.putFilename(rs.getString("filename"));
		}
	}
	
	public void refreshMovie(List<Header> httpheader, int id,String filename,pictureModal p) throws ClassNotFoundException, SQLException {
		String requrl = "";
		if(!Configuration.URL.getValue().isEmpty()) {
			requrl = "http://"+Configuration.URL.getValue()+":"+Configuration.PORT.getValue()+"/e/admin99-cn/ecmsinfo.php";
		}else {
			requrl = "http://"+Configuration.URL.getValue()+"/e/admin99-cn/ecmsadmin.php";
		}
		HttpPost post = new HttpPost(requrl);
		for(Header h:httpheader) {
			post.setHeader(h);
		}
		wrappingMovieData(id);
		String key = "";
		String value = "";
		this.formparam.putEnews("EditNews");
		this.formparam.putId(id);
		this.formparam.putFilepass(id);
		this.formparam.putPicture(p);
		List<NameValuePair> urlParameters2 = new ArrayList<NameValuePair>();
		Set set=this.formparam.getarrainfo().keySet();
		if(!this.formparam.getarrainfo().isEmpty()) {
			for(Iterator iter = set.iterator();iter.hasNext();) {
				key = iter.next().toString();
				value = this.formparam.getarrainfo().get(key).toString();
				urlParameters2.add(new BasicNameValuePair(key, value));
			}
		}
		try {
			post.setEntity(new UrlEncodedFormEntity(urlParameters2,"UTF-8"));
			CloseableHttpResponse response = htpcl.execute(post);
		    HttpEntity entity = response.getEntity(); 
		    System.out.println("刷新数据：----------------------------------------"); 
		    if(EntityUtils.toString(entity,"utf-8").contains("修改信息成功")) {
		    	System.out.println("图片上传完毕");
		    }else{	
		    	System.out.println("图片上传失败或者已上传!");
		    }
		    //System.out.println("Edite response: "+EntityUtils.toString(entity,"utf-8")); 
		    System.out.println("------------------------------------------------"); 
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			post.releaseConnection();
		}
		con.disconnect();
	} 
	
	public void refreshAll() throws SQLException, ClassNotFoundException {
		jdbclink jdbc_con=new jdbclink();
		for(pictureModal p:this.pm) {
			try {
				refreshMovie(this.requestHeader,p.getId(),p.getBigpic(),p);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		jdbc_con.disconnect();
	}
	
	public String getMovieType(int classid) {
		String type = "";
		switch(classid) {
			case 5: type="dalu";break;
			case 6: type="korea";break;
			case 7: type="hk";break;
			case 8: type="taiwan";break;
			case 9: type="usa";break;
			case 10: type="japan";break;
			case 11: type="thailand";break;
			case 13: type="haiwai";break;
			case 14: type="dongzuo";break;
			case 15: type="kongbu";break;
			case 16: type="xiju";break;
			case 17: type="aiqing";break;
			case 18: type="juqing";break;
			case 19: type="kehuan";break;
			case 20: type="zhanzheng";break;
			case 21: type="jilu";break;
			case 22: type="donghua";break;
			case 45: type="yueyu";break;
			default:
				System.out.println("default");
				break;
		}
		return type;
	}
	
	public void updateAllpic(jdbclink jdbc_con) throws ClassNotFoundException, SQLException {
		ResultSet rs = jdbc_con.findAllmovie();
		String prefix = "/d/file/";
		String type = "";
		String smallpath = "";
		String bigpath = "";
		List<String> fields = new ArrayList<String>();
		fields.add("bigpic");
		fields.add("indexpic");
		fields.add("maxpic");
		fields.add("titlepic");
		int i = 0;
		System.out.println("-----------------------------------------------------");
		if(!rs.equals(null)) {
			while(rs.next()) {	
				type = getMovieType(rs.getInt("classid"));
				//this.ids.add(rs.getInt("id"));
				bigpath = prefix+type+"/"+df2.format(new Date())+"/"+rs.getString("filename");
				/*在数据库里检查是否曾经上传过*/
				boolean f = jdbc_con.getIdAndPicFilename(rs.getInt("id"));
				if(f == true) {
					this.pm.add(new pictureModal(bigpath,rs.getInt("id")));
				}else {
					System.out.println("movie picture has already uploaded.");
					ResultSet data = jdbc_con.findfilename(rs.getInt("id"));
					while(data.next()) {
						if(data.getString("titlepic").isEmpty()) {
							System.out.println("picture hasn't been attached in movie content, processing attachement...");
							refreshMovie(this.requestHeader,rs.getInt("id"),rs.getString("filename"),new pictureModal(bigpath,rs.getInt("id")));
						}
					}
					System.out.println("-----------------------------------------------------");
				}
			}
		}
		jdbc_con.disconnect();
		refreshAll();
	}
	
	public List<Integer> removeDuplicate(List<Integer> ids){
		List<Integer> temp = new ArrayList<Integer>();
		int i = 0;
		for(int s:ids) {
			if(i == 0) {
				i = s;
			}
			if(i != s) {
				temp.add(i);
				i = s;
			}
		}
		return temp;
	}
	///////////////////////////////////////////////////////////////////
	/*Getter and Setters*/
	public void addMovieTitle(String movieTitle) {
		this.movieTitle.add(movieTitle);
	}
	public void setBclassid(String bclassid) {
		this.bclassid = bclassid;
		
	}
	public void setRequestHeader(List<Header> requestHeader) {
		this.requestHeader = requestHeader;
		
	}
	public void putMovieMap(int i,MovieModal m) {
		this.moviemap.put(i, m);
	}
	public void putAnimeMap(int i,AnimeModal a) {
		this.animemap.put(i, a);
	}
	/////////////////////////////////////////////////////////////////
	
}
