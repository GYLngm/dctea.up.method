package Modal;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class AnimeModal<T> {
	private String bclassid;
	
	private String movieName;
	private String keyWord;
	private String photourl;
	private String[] type;
	private String status;
	private String year;
	private String language;
	private String nation;
	private String description;
	private String subtitle;
	private String typenumber;
	private String pingfen;
	private String video_url;
	private int jishu = 0;
	
	private Map<Integer,String> episodes = new HashMap<Integer,String>();
	
	//private List <String> list=new ArrayList<String>();
	private Hashtable<String,String> map=new Hashtable<String,String>();
	public AnimeModal() throws ClassNotFoundException, SQLException {
		super();
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
	public void setNation(String nation) {
		this.nation = nation;
	}
	public String getNation() {
		return nation;
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
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
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
			case "热血" : this.typenumber="61";break;
			case "校园" : this.typenumber="62";break;
			case "恋爱" : this.typenumber="63";break;
			case "魔幻" : this.typenumber="64";break;
			case "治愈" : this.typenumber="65";break;
			case "推理" : this.typenumber="66";break;
			case "恐怖" : this.typenumber="67";break;
			case "剧场版" : this.typenumber="68";break;
			case "其他" : this.typenumber="69";break;
		}
		
		this.type = types;

	}

	public String getTypenumber() {
		return typenumber;
	}
	
	
	public void setVideoUrl(Map<Integer,String> episodes) {
		String handler = "";
		Map<Integer,String> teMap=new TreeMap<Integer,String>(
				new Comparator<Integer>() {
					@Override
					public int compare(Integer o1, Integer o2) {
						return o2.compareTo(o1);
					}
				});
		teMap.putAll(episodes);
		int MapSize = teMap.size();
		for (Integer au:teMap.keySet()) {  
			String aurl = episodes.get(au);
			int episodeInt = au;
			if(episodeInt<10) {
				if(episodeInt == 1) {
					handler += "\r\n"+"第0"+episodeInt+"集$"+aurl;
				}else if(episodeInt == MapSize){
					handler += "第0"+episodeInt+"集$"+aurl;
				}else {
					handler += "\r\n"+"第0"+episodeInt+"集$"+aurl;
				}
			}else {
				if(episodeInt == MapSize) {
					handler += "第"+episodeInt+"集$"+aurl;
				}else {
					handler += "\r\n"+"第"+episodeInt+"集$"+aurl;
				}
				
			}
		}  
		this.video_url = handler;
		teMap.clear();
	}
	public Map<Integer,String> getEpisodes(){
		return this.episodes;
	}
	public void setEpisodes(Map<Integer,String> episodes) {
		this.episodes.putAll(episodes);
		setVideoUrl(this.episodes);
	}
	public String getVideoUrl() {
		return this.video_url;
	}
	public int getJishu() {
		return jishu;
	}
	public void setJishu(int jishu) {
		this.jishu = jishu;
	}
}

