package upload.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Modal.AnimeModal;

public class jdbcNative {
	protected Connection con = null;
	private final String driver = "com.mysql.jdbc.Driver";
	
	public jdbcNative() throws ClassNotFoundException, SQLException {
		connect();
	}
	
	public void connect() throws ClassNotFoundException, SQLException {
		Class.forName(driver);
		con = DriverManager.getConnection("jdbc:mysql://"+sqlNativeInfo.URL.getValue()+"/"+sqlNativeInfo.DATABASE.getValue(),sqlNativeInfo.USERNAME.getValue(),sqlNativeInfo.PASSWORD.getValue());
	}
	public void disconnect() throws SQLException {
		this.con.close();
	}
	public ResultSet getMovies() throws SQLException{
		PreparedStatement prstm = con.prepareStatement("SELECT * FROM movie WHERE uploadstatus=0");
		return prstm.executeQuery();
	} 
	public ResultSet getMovies1() throws SQLException{
		PreparedStatement prstm = con.prepareStatement("SELECT * FROM movie WHERE uploadstatus=1");
		return prstm.executeQuery();
	} 
	public void check(String name) throws SQLException {
		PreparedStatement prstm = con.prepareStatement("update movie set uploadstatus=1 where movieName=?");
		prstm.setString(1, name);
		prstm.executeUpdate();
	}
	public void uncheck(String name) throws SQLException {
		PreparedStatement prstm = con.prepareStatement("update movie set uploadstatus=0 where movieName=?");
		prstm.setString(1, name);
		prstm.executeUpdate();
	}
	public void checkerror(String name) throws SQLException {
		PreparedStatement prstm = con.prepareStatement("update movie set uploadstatus=2 where movieName=?");
		prstm.setString(1, name);
		prstm.executeUpdate();
	}
	//DaoDramas
	public ResultSet getAnimeName() throws SQLException {
		PreparedStatement prstm = con.prepareStatement("select * from mangaseries");
		return prstm.executeQuery();
	}
	public List<AnimeModal> getAnime() throws SQLException, ClassNotFoundException{
		ResultSet animeNames = getAnimeName();
		List<AnimeModal> animes = new ArrayList<AnimeModal>();
			
		while(animeNames.next()) {
			if(animeNames.getInt("uploadstatus") == 0) {
				Map<Integer,String> episode_urls = new HashMap<Integer,String>();
				String name = animeNames.getString("name");
				String year = (animeNames.getString("year") !=null)?animeNames.getString("year"):"";
				String status = animeNames.getString("status");
				String subtitle = (animeNames.getString("groupsubtitle") != null)?animeNames.getString("groupsubtitle"):"";
				//String description = (animeNames.getString("description") != null)?animeNames.getString("description"):"";
				subtitle += "(»’”Ô)";
				String type = (!animeNames.getString("type").isEmpty())?animeNames.getString("type"):"∆‰À˚";
				int i = 0;
				AnimeModal a = new AnimeModal();
				//i = getAnimeJishu(animeNames.getInt("id"),name,year,type,status,subtitle,episode_urls,a,animes);
				PreparedStatement prstm = con.prepareStatement("select * from mangaepisodes where id_series="+animeNames.getInt("id"));
				ResultSet anime = prstm.executeQuery();
				while(anime.next()) {
					if(anime.getInt("uploadstatus") == 0) {
						episode_urls.put(Integer.parseInt(anime.getString("number")), anime.getString("downloadurl"));
						i++;
					}
				}
				//a.setDescription(description);
				a.setMovieName(name);
				a.setYear(year);
				a.setType(type);
				a.setLanguage(subtitle);
				a.setPhotoUrl("");
				a.setPingfen("0");
				a.setEpisodes(episode_urls);
				animes.add(a);
				a.setJishu(i);
				a=null;
			}
		}
		
		return animes;
	}
	
}
