package upload.http.post;

import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import org.apache.http.client.ClientProtocolException;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.CloseableHttpClient;

import Modal.AnimeModal;
import Modal.MovieModal;
import upload.sql.jdbcNative;
import upload_todo.todoAnime;
import upload_todo.todoMovie;
import upload.http.params.*;

public class main {
	
	
	public static void main(String[] args) throws ClassNotFoundException, SQLException{
		String requrl = "";
		if(!Configuration.URL.getValue().isEmpty()) {
			requrl = "http://"+Configuration.URL.getValue()+":"+Configuration.PORT.getValue()+"/e/admin99-cn/ecmsadmin.php";
		}else {
			requrl = "http://"+Configuration.URL.getValue()+"/e/admin99-cn/ecmsadmin.php";
		}
		httpLogin httpl=new httpLogin(requrl,Configuration.adminAccount.getValue(),Configuration.adminPassword.getValue());
		
		CloseableHttpClient hasLogined = httpl.connector();
		List<Cookie> cki = httpl.getcookies();

		jdbcNative con =new jdbcNative();
		httpsendform sendform = new httpsendform(hasLogined,cki);
		httpsendpic sp = new httpsendpic(hasLogined,cki);
		todoMovie uploadmovie = new todoMovie();
		
		todoAnime uploadanime = new todoAnime();
		
		List<MovieModal> movies = new ArrayList<MovieModal>();
		//List<MovieModal> movies_end = new ArrayList<MovieModal>();
		
		ResultSet rs = con.getMovies();
		
		if(!rs.equals(null)) {
			while(rs.next()) {
				MovieModal movie = new MovieModal();
				movie.setMovieName(rs.getString("movieName"));
				movie.setDescription(rs.getString("introduction"));
				if(rs.getString("director") != null) {
					movie.setDirector(rs.getString("director"));
				}else {
					movie.setDirector("");
				}
				if(rs.getString("actor") != null) {
					movie.setProtagonist(rs.getString("actor"));
				}else {
					movie.setProtagonist("");
				}
				if(rs.getString("year") != null) {
					movie.setYear(rs.getString("year"));
				}else {
					movie.setYear("");
				}
				if(rs.getString("language") != null) {
					movie.setLanguage(rs.getString("language"));
				}else {
					movie.setLanguage("");
				}
				if(rs.getString("subtitle") != null) {
					movie.setSubtitle(rs.getString("subtitle"));
				}else {
					movie.setSubtitle("");
				}
				if(rs.getString("score") != null) {
					movie.setPingfen(rs.getString("score"));
				}else {
					movie.setPingfen("0");
				}
				if(rs.getString("photourl") != null) {
					movie.setPhotoUrl(rs.getString("photourl").trim());
				}else {
					movie.setPhotoUrl("null");
				}
				if(rs.getString("nation") != null) {
					movie.setNation(rs.getString("nation"));
				}else {
					movie.setNation("");
				}
				movie.setType(rs.getString("type"));
				/////////此处添加URL/////////////////////////////
				String url=getUCDNURL(movie.getMovieName());
				movie.setVideoUrl(url);
				movies.add(movie);
			}
		}
		
		List<AnimeModal> animes = new ArrayList<AnimeModal>();
		animes = con.getAnime();
		
		try {
			uploadmovie.uploadMovie(sendform,sp,movies);
			uploadanime.uploadAnime(sendform, sp, animes);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	private static String getUCDNURL(String movieName) {
		// TODO Auto-generated method stub
		String local_video_filelocation="d:/video";//输入root文件夹所在地址///////////////
		List<String> floderUrl = new ArrayList<String>();
		List<String> movieUrlAllList  = new ArrayList<String>();
		List<String> movienameAllList  = new ArrayList<String>();
		//获得root文件夹的名字
		String []s1=local_video_filelocation.split("/");
		String rootfolderName=s1[s1.length-1];
		
		floderUrl = GetAllChildFolder.ReadAllFile(local_video_filelocation);
		
		for (String allFolderUrl : floderUrl) {
			// System.out.println(allFolderUrl);
			List<String> txtnameList = GetAndReadAllFile.getTxtNameList(new File(allFolderUrl));
			List<String> movieNameList = GetAndReadAllFile.getMovieNameList(new File(allFolderUrl));
			List<String> movieUrlList = GetAndReadAllFile.getMovieUrlList(new File(allFolderUrl));

			movienameAllList.addAll(movieNameList);
			movieUrlAllList.addAll(movieUrlList);

		}
		
		for(String s:movieUrlAllList) {
			if(s.contains(movieName)) {
				String []s2=s.split(local_video_filelocation);
				return "http://10653-1.b.cdn13.com/"+rootfolderName+s2[1];
			}
		}
		return null;
	}
}
