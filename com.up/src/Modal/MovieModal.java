package Modal;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;

import upload.sql.jdbclink;

public class MovieModal<T> {
	private String movieName;
	private String keyWord;
	private String bclassid;
	
	private String photourl;
	private String[] type = null;
	private String year;
	private String language;
	private String director;
	private String nation;
	private String protagonist;
	private String description;
	private String subtitle;
	private String typenumber;
	private String pingfen;
	private String viedourl;
	private int id = 0;

	//private List <String> list=new ArrayList<String>();
	private Hashtable<String,String> map=new Hashtable<String,String>();
	public MovieModal() {
		
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getBclassid() {
		return bclassid;
	}
	public void setBclassid(String bclassid) {
		this.bclassid = bclassid;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public Hashtable<String, String> getMap() {
		return map;
	}
	public String getMovieName() {
		return movieName;
	}
	public void setMovieName(String name) {
		this.movieName = name;
	}
	public void setKeyWord(String key) {
		this.keyWord = key;
	}
	public String getKeyWord() {
		return keyWord;
	}
	public void setPhotoUrl(String url) {
		this.photourl = url;
	}
	public String getPhotourl() {
		return photourl;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getYear() {
		return year;
	}
	public void setDirector(String director) {
		this.director = director;
	}
	public String getDirector() {
		return director;
	}
	public void setNation(String nation) {
		this.nation = nation;
	}
	public String getNation() {
		return nation;
	}
	public void setProtagonist(String actors) {
		this.protagonist = actors;
	}
	public String getProtagonist() {
		return protagonist;
	}
	public void setDescription(String desc) {
		this.description = desc;
	}
	public String getDescription() {
		return description;
	}
	public void setSubtitle(String sub) {
		this.subtitle = sub;
	}
	public String getSubtitle() {
		return subtitle;
	}
	public void setPingfen(String fen) {
		this.pingfen = fen;
	}
	public String getPingfen() {
		return this.pingfen;
	}
	public String[] getType() {
		return type;
	}
	public void setType(String type) {
		String[] types = new String[5];
		if(type.contains(",")) {
			types = type.split(",");
		}else {
			types = new String[]{type};
		}
		switch(types[0].trim()) {
			case "动作" : this.typenumber="14";break;
			case "恐怖" : this.typenumber="15";break;
			case "喜剧" : this.typenumber="16";break;
			case "爱情" : this.typenumber="17";break;
			case "剧情" : this.typenumber="18";break;
			case "科幻" : this.typenumber="19";break;
			case "战争" : this.typenumber="20";break;
			case "记录" : this.typenumber="21";break;
			case "动画" : this.typenumber="22";break;
			case "粤语" : this.typenumber="45";break;
		}
		
		this.type = types;

	}
	public String getTypenumber() {
		return typenumber;
	}
	public void setVideoUrl(String video_url) {
		this.viedourl = video_url;
	}
	public String getVideoUrl() {
		return this.viedourl;
	}
}
