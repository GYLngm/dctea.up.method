package upload_todo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


import Modal.MovieModal;
import log.checkBeforeUpload;
import upload.http.post.httpsendform;
import upload.http.post.httpsendpic;
import upload.sql.jdbcNative;
import upload.sql.jdbclink;

public class todoMovie {
	private String bclassid = "2";
	private checkBeforeUpload e = new checkBeforeUpload();
	public void uploadMovie(httpsendform sendform,httpsendpic sp,List<MovieModal> movies) throws Exception {
		jdbclink con = new jdbclink();
		jdbcNative con_end = new jdbcNative();

		//--------------------------------------------------------------
		int key_id = 0;
		
		/*�����ݿ������Ƿ������ϴ���*/
		System.out.println("Processing "+ movies.size() + " movie Infomation\n");
		if(movies.size() == 0) {
			System.out.println("û����Ҫ���µĵ�Ӱ");
		}else {
			System.out.println("Start to update movie content.....\n");
			for(MovieModal m:movies) {
				System.out.println("Updating movie : "+m.getMovieName()+"...\n");
					m.setKeyWord(m.getMovieName()+","+m.getType()[0]+",�߲�ӰԺ�������");
					sendform.setBclassid(this.bclassid);
					System.out.println("��Ӱ����: "+m.getType()[0]+"("+m.getTypenumber()+")");
					sendform.setClassid(m.getTypenumber());
					key_id = sendform.sendForm(m);
					
					con.insertInfoFen(m.getMovieName(), Double.valueOf(m.getPingfen()).intValue());
				if(key_id>0) {
					con_end.check(m.getMovieName());
					m.setBclassid(this.bclassid);
					sp.putMovieMap(key_id, m);
					e.saveUploadedMovie(m.getMovieName()+": ¼��ɹ�",e.captureOrigineText(e.getPath()));
					System.out.println("Updating movie : "+m.getMovieName()+" success!\n");
				}else {
					//con_end.checkerror(m.getMovieName());
					e.saveUploadedMovie(m.getMovieName()+": ¼��ʧ��",e.captureOrigineText(e.getPath()));
					System.out.println("Updating movie : "+m.getMovieName()+" failed\n");
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
