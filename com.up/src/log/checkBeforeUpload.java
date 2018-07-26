package log;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Date;

import Modal.MovieModal;
import upload.http.params.Configuration;

public class checkBeforeUpload {
	private SimpleDateFormat df2 = new SimpleDateFormat("yyy-MM-dd");
	private String dir = Configuration.LOG_DIR.getValue();
	private Date now = null;
	public checkBeforeUpload(){
		this.now = new Date();
	}
	public String getPath() {
		String currentTimeConv = this.df2.format(this.now);
		return this.dir+"/"+currentTimeConv+".txt";
	}
	
	public StringBuffer captureOrigineText(String path) throws FileNotFoundException {
		
		File file = new File(path);
		if(!file.getParentFile().exists()) {
			System.out.println("创建目录dcteaLogs");
			if(!file.getParentFile().mkdirs()) {
				System.out.println("创建目录失败！");
			}
		}
		if(!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
			System.out.println("创建3.txt文件\n");
		}
		FileInputStream filestream = new FileInputStream(file);
		InputStreamReader isr = new InputStreamReader(filestream);
		BufferedReader bufr = new BufferedReader(isr);
		StringBuffer buf = new StringBuffer("");
		String temp = "";
		if(file.exists()) {
			try {
				for(int i=0;(temp = bufr.readLine())!=null;i++) {
					buf = buf.append(temp);
					buf = buf.append(System.getProperty("line.separator"));
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return buf;
	}
	
	/*
	 * 追加文件
	 * */
	public boolean saveUploadedMovie(String title,StringBuffer buf) {
	//public boolean saveUploadedMovie(String title) {
		boolean flag = false;
		
		File file = new File(getPath());
		if(!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
			System.out.println("创建txt文件\n");
		}
		
		FileOutputStream fos = null;
		OutputStreamWriter opsw = null;
		PrintWriter pw = null;
		
		String row = getCurrentTime()+title;
		try {
			fos = new FileOutputStream(file);
			opsw = new OutputStreamWriter(fos,"gbk");
			pw = new PrintWriter(opsw,true);
			buf.append(row);
			buf.append(System.getProperty("line.separator"));
			
			pw.println(buf.toString());
			pw.flush();

		} catch (FileNotFoundException | UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}finally {
			if(pw != null) {
				pw.flush();
			}
			try {
				if(fos != null) {
					fos.close();
				}
				if(opsw != null) {
					opsw.close();
				}
			}catch (IOException e) {
				e.printStackTrace();
			}
		}
		return flag;
	}
	
	public String getCurrentTime() {
		Date now = new Date(); 
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String time = df.format(now);
		time = "["+ time +"]";
		return time;
	}
	
}
