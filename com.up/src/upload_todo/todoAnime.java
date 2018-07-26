package upload_todo;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import Modal.AnimeModal;
import log.checkBeforeUpload;
import upload.http.post.httpsendform;
import upload.http.post.httpsendpic;
import upload.sql.jdbcNative;
import upload.sql.jdbclink;

public class todoAnime {
	private String bclassid = "3";
	private checkBeforeUpload e = new checkBeforeUpload();
	public void uploadAnime(httpsendform sendform,httpsendpic sp,List<AnimeModal> animes) throws Exception {
		jdbclink con = new jdbclink();
		jdbcNative con_end = new jdbcNative();

		//--------------------------------------------------------------
		int key_id = 0;
		
		/*在数据库里检查是否曾经上传过*/
		System.out.println("Processing "+ animes.size() + " anime Infomation\n");
		if(animes.size() == 0) {
			System.out.println("没有需要更新的动漫");
		}else {
			System.out.println("Start to update movie content.....\n");
			for(AnimeModal a:animes) {
				System.out.println("Updating movie : "+a.getMovieName()+"...\n");
					a.setKeyWord(a.getMovieName()+","+a.getType()[0]+",哆茶影院最快最新");
					sendform.setBclassid(this.bclassid);
					System.out.println("动漫类型: "+a.getType()[0]+"("+a.getTypenumber()+")");	
					System.out.println("动漫集数: "+a.getEpisodes().size()+" 集");
					
					sendform.setClassid(a.getTypenumber());
					key_id = sendform.sendFormAnime(a);
					
					con.insertInfoFen(a.getMovieName(), Double.valueOf(a.getPingfen()).intValue());
				if(key_id>0) {
					con_end.check(a.getMovieName());
					a.setBclassid(this.bclassid);
					sp.putAnimeMap(key_id, a);
					e.saveUploadedMovie(a.getMovieName()+": 录入成功",e.captureOrigineText(e.getPath()));
					System.out.println("Updating movie : "+a.getMovieName()+" success!\n");
				}else {
					//con_end.checkerror(m.getMovieName());
					e.saveUploadedMovie(a.getMovieName()+": 录入失败",e.captureOrigineText(e.getPath()));
					System.out.println("Updating movie : "+a.getMovieName()+" failed\n");
				}
				System.out.println("-------------------------------------------------------");
			}
		}
		
			try {
				sp.updateAllpic(con);
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}

			
		
		con.disconnect();
		con_end.disconnect();
	}
}
