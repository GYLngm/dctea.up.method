package upload.http.post;

import java.util.Hashtable;

import java.util.Map;

import Modal.pictureModal;
import java.util.Set;
import upload.http.params.parameters;

public class arrinfos implements parameters{

	private Hashtable<String,Object> arrinfo;
	
	public arrinfos() {
		arrinfo=new Hashtable<String,Object>();
	}
	
	public void initalParameterList() {
		arrinfo.put("enews","AddNews");
		arrinfo.put("id","0");
		arrinfo.put("filepass","");
		arrinfo.put("username","admin");
		arrinfo.put("oldfilename","");
		arrinfo.put("oldgroupid","");
		arrinfo.put("oldchecked","1");
		arrinfo.put("newstext_url","");
		arrinfo.put("ecmsfrom","");
		arrinfo.put("ecmsnfrom","1");
		arrinfo.put("fstb","");
		arrinfo.put("oldttid","");
		arrinfo.put("ecmscheck","0");
		arrinfo.put("ztids","");
		arrinfo.put("zcids","");
		arrinfo.put("oldztids","");
		arrinfo.put("oldzcids","");
		arrinfo.put("title","");
		arrinfo.put("titlecolor","");
		arrinfo.put("checked","1");
		arrinfo.put("isgood","0");
		arrinfo.put("firsttitle","0");
		arrinfo.put("keyboard","");
		arrinfo.put("titleurl","");
		arrinfo.put("newstime","");
		//arrinfo.put("titlepic","");
		arrinfo.put("company","");
		arrinfo.put("movietime","");
		arrinfo.put("language","");
		arrinfo.put("select2","");
		arrinfo.put("pinyin","");
		arrinfo.put("player","");
		arrinfo.put("playadmin","");
		arrinfo.put("initial","");
		arrinfo.put("area","");
		arrinfo.put("monthclick","");
		//arrinfo.put("bigpic","");
		//arrinfo.put("indexpic","");
		//arrinfo.put("maxpic","");
		arrinfo.put("ztid","");
		arrinfo.put("www_92game_net_playerorder","0");
		arrinfo.put("lz","0");
		arrinfo.put("state","");
		arrinfo.put("lastvolume","");
		arrinfo.put("lasturl","");
		arrinfo.put("isformat","");
		arrinfo.put("moviesay","");
		arrinfo.put("otherversion","");
		arrinfo.put("cjid" , "");
		arrinfo.put("istop", "0");
		arrinfo.put("newstempid","0");
		arrinfo.put("groupid","0");
		arrinfo.put("userfen" , "0");
		arrinfo.put("onclick" ,"0");
		arrinfo.put("totaldown","0");
		arrinfo.put("newspath","");
		arrinfo.put("filename","");
		arrinfo.put("infotags" ,"");
		arrinfo.put("oldinfotags","");
		arrinfo.put("info_diyotherlink","0");
		arrinfo.put("info_keyid","");
		arrinfo.put("info_infouptime","");
		arrinfo.put("info_infodowntime","");
		arrinfo.put("vote_title","");
		arrinfo.put("v_vote_num","1");
		arrinfo.put("v_editnum","8");
		arrinfo.put("vote_class","0");
		arrinfo.put("dovote_ip","0");
		arrinfo.put("vote_olddotime","0000-00-00");
		arrinfo.put("vote_dotime","0000-00-00");
		arrinfo.put("vote_width","500");
		arrinfo.put("vote_height","300");
		arrinfo.put("vote_tempid","1");
		arrinfo.put("addnews"," Ã·Ωª ");
	}

	public Hashtable<String,Object> getarrainfo(){
		return this.arrinfo;
	};
	public void putTitle(String title) {
		this.arrinfo.put("title", title);
	}
	public void putOldFileName(String filename) {
		this.arrinfo.put("oldfilename", filename);
	}
	public void putBigclass(String bclassid) {
		this.arrinfo.put("bclassid", bclassid);
	}
	public void putClass(String classid) {
		this.arrinfo.put("classid", classid);
	}

	@Override
	public void putCampany(String companyname) {
		this.arrinfo.put("company", companyname);
		
	}

	@Override
	public void putTitlepic(String url) {
		this.arrinfo.put("titlepic", url);
		
	}

	@Override
	public void putNewstime(String currentTime) {
		this.arrinfo.put("newstime", currentTime);
	}

	@Override
	public void putMovietime(String movietime) {
		this.arrinfo.put("movietime", movietime);
	}

	@Override
	public void putLanguage(String language) {
		this.arrinfo.put("language", language);
	}

	@Override
	public void putPlayer(String name) {
		this.arrinfo.put("player", name);
	}

	@Override
	public void putDirector(String name) {
		this.arrinfo.put("playadmin", name);
	}

	@Override
	public void putArea(String name) {
		this.arrinfo.put("area", name);
	}

	@Override
	public void putLz(Boolean b) {
		if(b)
			this.arrinfo.put("lz", "1");
		else
			this.arrinfo.put("lz", "0");
	}

	@Override
	public void putState(String state) {
		this.arrinfo.put("state", state);
	}

	@Override
	public void putVideo(Map<String,String> data) {
		String url;
		String player;
		/*Set arrset = data.entrySet();
		Iterator iter = arrset.iterator();
		int size = data.size();
		for(int i=0;i<size;i++) {
			url = "";
			player = "";
			Map.Entry entry = (Map.Entry) iter.next();
			url += (String)entry.getValue();
			player += (String)entry.getKey();
			this.arrinfo.put("m_playurl["+i+"]", url);
			this.arrinfo.put("m_playfrom["+i+"]", player);
		}*/
		Set arrset = data.keySet();
		this.arrinfo.put("m_playurl["+0+"]", data.get("ckplayer"));
		this.arrinfo.put("m_playurl["+1+"]", data.get("qvod"));
		this.arrinfo.put("m_playurl["+2+"]", data.get("youku"));
		this.arrinfo.put("m_playfrom["+0+"]", "ckplayer");
		this.arrinfo.put("m_playfrom["+1+"]", "qvod");
		this.arrinfo.put("m_playfrom["+2+"]", "youku");
	}
	public void putMovie(String video_url) {
		this.arrinfo.put("m_playurl["+0+"]", video_url);
		this.arrinfo.put("m_playfrom["+0+"]", "ckplayer");
	}
	@Override
	public void putMovieSay(String describ) {
		this.arrinfo.put("moviesay", describ);
	}

	@Override
	public void putNewstempid(String id) {
		this.arrinfo.put("newstempid", id);
	}

	@Override
	public void putGroupid(String id) {
		this.arrinfo.put("groupid", id);
	}

	@Override
	public void putFilepass(long filepass) {
		this.arrinfo.put("filepass", filepass);
	}

	@Override
	public void putNewsPath(String date) {
		this.arrinfo.put("newspath",date);
	}

	@Override
	public void putEcmsFrom(String url) {
		this.arrinfo.put("ecmsfrom", url);
	}

	@Override
	public void putKeyWord(String keyword) {
		this.arrinfo.put("keyboard", keyword);
	}

	@Override
	public void putEnews(String action) {
		this.arrinfo.put("enews", action);
	}

	@Override
	public void putId(int id) {
		this.arrinfo.put("id", id);
	}

	@Override
	public void putFilename(String filename) {
		// TODO Auto-generated method stub
		this.arrinfo.put("filename", filename);
	}
	@Override
	public void putPicture(pictureModal p) {
		this.arrinfo.put("titlepic", p.getSmallpic());
		this.arrinfo.put("bigpic", p.getBigpic());
		this.arrinfo.put("indexpic", p.getIndexpic());
		this.arrinfo.put("max", p.getMaxpic());

	}

	public void putWhatever(String key,String value){
		this.arrinfo.put(key, value);
	}

}
