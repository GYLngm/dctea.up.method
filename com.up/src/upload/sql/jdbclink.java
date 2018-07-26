package upload.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class jdbclink {
	protected Connection con = null;
	private final String driver = "com.mysql.jdbc.Driver";
	
	public jdbclink() throws ClassNotFoundException, SQLException {
		connect();
	}
	
	public void connect() throws ClassNotFoundException, SQLException {
		Class.forName(this.driver);
		con = DriverManager.getConnection("jdbc:mysql://"+sqlDcteaInfo.URL.getValue()+"/"+sqlDcteaInfo.DATABASE.getValue(),sqlDcteaInfo.USERNAME.getValue(),sqlDcteaInfo.PASSWORD.getValue());
		
	}
	public void disconnect() throws SQLException {
		this.con.close();
	}
	
	public ResultSet findfilename(int id) throws SQLException {
		PreparedStatement prstm = con.prepareStatement("SELECT * FROM "+sqlDcteaInfo.PREFIX.getValue()+"ecms_movie WHERE id=? limit 1",ResultSet.CONCUR_UPDATABLE);
		prstm.setInt(1, id);
		return prstm.executeQuery();
	}
	
	public ResultSet findAllmovie() throws SQLException, ClassNotFoundException{
		PreparedStatement stm = con.prepareStatement("SELECT * FROM "+sqlDcteaInfo.PREFIX.getValue()+"enewsfile_1 where 1",ResultSet.TYPE_SCROLL_SENSITIVE,
                ResultSet.CONCUR_UPDATABLE);
		ResultSet r = stm.executeQuery();	
		return r;
	}
	
	public void updatemovie(int id, List<String> field,String url) throws SQLException {
		if(!url.isEmpty()) {
			System.out.println("直接后台插入收图片地址: "+id);
			ResultSet rs = findfilename(id);
			while(rs.next()) {
				System.out.println(" pic id = "+id+"\n");
				PreparedStatement prstm = con.prepareStatement("UPDATE "+sqlDcteaInfo.PREFIX.getValue()+"ecms_movie SET "+field.get(0)+"=?,"+field.get(1)+"=?,"+field.get(2)+"=?,"+field.get(3)+"=?"+" WHERE id=? limit 1");
				
				prstm.setString(1, url);
				prstm.setString(2, url);
				prstm.setString(3, url);
				prstm.setString(4, url);
				prstm.setInt(5, id);
				prstm.executeUpdate();
			}
		}else {
			System.out.println("图片录入失败\n");
		}
	}
	public boolean findMovieInDBbyTitle(String title) throws SQLException {
		boolean flag = false;
		int i = 0;
		if(!title.isEmpty()) {
			PreparedStatement prstm = con.prepareStatement("SELECT count(*) AS count FROM "+sqlDcteaInfo.PREFIX.getValue()+"ecms_movie WHERE title=?");
			prstm.setString(1, title);
			ResultSet rs = prstm.executeQuery();
			rs.next();
			i = rs.getInt("count");
			if(i>0) {
				flag = true;
			}else {
				flag = false;
			}
		}
		return flag;
	}
	public int queryIdMovieByTitle(String title) throws SQLException {
		PreparedStatement prstm = con.prepareStatement("SELECT * FROM "+sqlDcteaInfo.PREFIX.getValue()+"ecms_movie WHERE title=?");
		prstm.setString(1, title);
		ResultSet rs = prstm.executeQuery();
		rs.next();
		return rs.getInt("id");
	}
	public void insertInfoFen(String title,int score) throws SQLException {
		PreparedStatement prstm = con.prepareStatement("UPDATE "+sqlDcteaInfo.PREFIX+"ecms_movie SET infopfen=? WHERE title=?");
		prstm.setInt(1, score);
		prstm.setString(2, title);
		prstm.executeUpdate();
	}
	
	public boolean getIdAndPicFilename(int id) throws SQLException {
		boolean flag = false;
		String rst = "";
		PreparedStatement prstm = con.prepareStatement("SELECT * from "+sqlDcteaInfo.PREFIX+"enewsfile_1 WHERE id=?");
		prstm.setInt(1, id);
		ResultSet rs = prstm.executeQuery();
		if(rs.next()) {
			rst = rs.getString("filename");
		}
		if(rst.isEmpty()) {
			flag = true;
		}
		else {
			flag = false;
		}
		return flag;
	}
	public void findDramaEpisode(String title) {
		
	}
}
