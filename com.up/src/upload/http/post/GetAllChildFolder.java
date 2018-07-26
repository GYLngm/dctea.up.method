package upload.http.post;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;

public class GetAllChildFolder {
	private static //获取文件夹内的所有文�?
	List<String> floderUrl=new ArrayList<String>();
	public static List<String> ReadAllFile(String filePath) {  
        File f = null;  
        f = new File(filePath);  
        File[] files = f.listFiles(); // 得到f文件夹下面的�?有文件�??  
       
        for (File file : files) {  
            if(file.isDirectory()) {  
                //如何当前路劲是文件夹，则循环读取这个文件夹下的所有文�?  
                ReadAllFile(file.getAbsolutePath());  
            } else {
            	String a=file.getParent().replaceAll("\\\\", "/");
            	if(!floderUrl.contains(a)) {
            		
            		floderUrl.add(a);
            		//System.out.println(a);
            	}
            }  
        }
 
		return floderUrl;  
        
    }
	 public static String readLastLine(File file, String charset) throws IOException {  
         if (!file.exists() || file.isDirectory() || !file.canRead()) {  
           return null;  
         }  
         RandomAccessFile raf = null;  
         try {  
           raf = new RandomAccessFile(file, "r");  
           long len = raf.length();  
           if (len == 0L) {  
             return "";  
           } else {  
             long pos = len - 1;  
             while (pos > 0) {  
               pos--;  
               raf.seek(pos);  
               if (raf.readByte() == '\n') {  
                 break;  
               }  
             }  
             if (pos == 0) {  
               raf.seek(0);  
             }  
             byte[] bytes = new byte[(int) (len - pos)];  
             raf.read(bytes);  
             if (charset == null) {  
               return new String(bytes);  
             } else {  
               return new String(bytes, charset);  
             }  
           }  
         } catch (FileNotFoundException e) {  
         } finally {  
           if (raf != null) {  
             try {  
               raf.close();  
             } catch (Exception e2) {  
             }  
           }  
         }  
         return null;  
       }  
}
