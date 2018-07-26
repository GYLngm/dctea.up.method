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
		
		/*�����ݿ������Ƿ������ϴ���*/
		System.out.println("Processing "+ animes.size() + " anime Infomation\n");
		if(animes.size() == 0) {
			System.out.println("û����Ҫ���µĶ���");
		}else {
			System.out.println("Start to update movie content.....\n");
			for(AnimeModal a:animes) {
				System.out.println("Updating movie : "+a.getMovieName()+"...\n");
					a.setKeyWord(a.getMovieName()+","+a.getType()[0]+",�߲�ӰԺ�������");
					sendform.setBclassid(this.bclassid);
					System.out.println("��������: "+a.getType()[0]+"("+a.getTypenumber()+")");	
					System.out.println("��������: "+a.getEpisodes().size()+" ��");
					
					sendform.setClassid(a.getTypenumber());
					key_id = sendform.sendFormAnime(a);
					
					con.insertInfoFen(a.getMovieName(), Double.valueOf(a.getPingfen()).intValue());
				if(key_id>0) {
					con_end.check(a.getMovieName());
					a.setBclassid(this.bclassid);
					sp.putAnimeMap(key_id, a);
					e.saveUploadedMovie(a.getMovieName()+": ¼��ɹ�",e.captureOrigineText(e.getPath()));
					System.out.println("Updating movie : "+a.getMovieName()+" success!\n");
				}else {
					//con_end.checkerror(m.getMovieName());
					e.saveUploadedMovie(a.getMovieName()+": ¼��ʧ��",e.captureOrigineText(e.getPath()));
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
