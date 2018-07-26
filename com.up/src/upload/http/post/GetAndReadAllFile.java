package upload.http.post;



import java.io.BufferedReader;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author jishu_vip
 * 
 */
public class GetAndReadAllFile {
	
	
	static List<String> floderUrl=new ArrayList<String>();
	public static List<String> ReadAllFile(String filePath) {  
        File f = null;  
        f = new File(filePath);  
        File[] files = f.listFiles(); // 得到f文件夹下面的�?有文件�??  
       
        for (File file : files) {  
        	System.out.println(file);
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
	
	
	/**
	 * 获取文件的扩展名
	 * 
	 * @param filename
	 * @param defExt
	 * @return
	 */
	public static String getExtension(String filename, String defExt) {
		if ((filename != null) && (filename.length() > 0)) {
			int i = filename.lastIndexOf('.');

			if ((i > -1) && (i < (filename.length() - 1))) {
				return filename.substring(i + 1);
			}
		}
		return defExt;
	}

	public static String getExtension(String filename) {
		return getExtension(filename, "");
	}

	/**
	 * 获取�?个文件夹下的�?有文�? 要求：后�?名为txt (可自己修�?)
	 * 
	 * @param file
	 * @return
	 */
	//用来储存�?有的txt名字
	public static List<String> getTxtNameList(File file) {
		List<String> result = new ArrayList<String>();

		if (!file.isDirectory()) {
			System.out.println(file.getAbsolutePath());

			result.add(file.getAbsolutePath());
		} else {
			// 内部匿名类，用来过滤文件类型

			File[] directoryList = file.listFiles(new FileFilter() {
				public boolean accept(File file) {
					if (file.isFile() && file.getName().indexOf("txt") > -1) {
						return true;
					} else {
						return false;
					}
				}
			});
			// 去除文件的url 只需要获得txt的文件名字即�?
			int sizefile = file.toString().length();
			// System.out.println("文件的开头是"+sizefile);
			for (int i = 0; i < directoryList.length; i++) {
				result.add(directoryList[i].getAbsolutePath().substring(sizefile + 1));
			}
		}
		// 去掉拓展�?
		String fileContent = null;
		String[] content = null;
		List<String> newfilelist=new ArrayList();
		//System.out.println("**" + fileList.size());
		for (String s : result) {
			// 打印文件�?
			int cnt = 0;
			boolean flag = false;
			for (int i = 0; i < s.length(); i++) {
				if (flag == true && s.charAt(i) != '.') {
					//System.out.print(s.charAt(i));
				}
				if (s.charAt(i) == '.')
					break;
				if (s.charAt(i) == '\\') {
					cnt++;
					if (cnt == 2)
						flag = true;
				}
			}
			newfilelist.add(trimExtension(s));
			//System.out.println();
			
		}	
		return newfilelist;
	}
	//要来储存�?有的电影名字
	public static List<String> getMovieNameList(File file) {
		List<String> result = new ArrayList<String>();

		if (!file.isDirectory()) {
			System.out.println(file.getAbsolutePath());

			result.add(file.getAbsolutePath());
		} else {
			// 内部匿名类，用来过滤文件类型

			File[] directoryList = file.listFiles(new FileFilter() {
				public boolean accept(File file) {
					if (file.isFile() && file.getName().indexOf("txt") <= -1) {
						return true;
					} else {
						return false;
					}
				}
			});
			// 去除文件的url 只需要获得txt的文件名字即�?
			int sizefile = file.toString().length();
			// System.out.println("文件的开头是"+sizefile);
			for (int i = 0; i < directoryList.length; i++) {
				result.add(directoryList[i].getAbsolutePath().substring(sizefile + 1));
			}
		}
		// 去掉拓展�?
				String fileContent = null;
				String[] content = null;
				List<String> newfilelist=new ArrayList();
				//System.out.println("**" + fileList.size());
				for (String s : result) {
					// 打印文件�?
					int cnt = 0;
					boolean flag = false;
					for (int i = 0; i < s.length(); i++) {
						if (flag == true && s.charAt(i) != '.') {
							//System.out.print(s.charAt(i));
						}
						if (s.charAt(i) == '.')
							break;
						if (s.charAt(i) == '\\') {
							cnt++;
							if (cnt == 2)
								flag = true;
						}
					}
					newfilelist.add(trimExtension(s));
					//System.out.println();
					
				}
				
				
				return newfilelist;
	}
	//要来储存�?有的电影url
	
		public static List<String> getMovieUrlList(File file) {
			 List<String> result = new ArrayList<String>();  
		        if (!file.isDirectory()) {  
		            System.out.println(file.getAbsolutePath());  
		            result.add(file.getAbsolutePath());  
		        } else {  
		            // 内部匿名类，用来过滤文件类型  
		            File[] directoryList = file.listFiles(new FileFilter() {  
		                public boolean accept(File file) {  
		                    if (file.isFile() && file.getName().indexOf("txt") <= -1) {  
		                        return true;  
		                    } else {  
		                        return false;  
		                    }  
		                }  
		            });  
		            for (int i = 0; i < directoryList.length; i++) {  
		            	String a=directoryList[i].getAbsolutePath().replaceAll("\\\\", "/");
		                result.add(a);  
		            }  
		        }  
		        return result;  
		}
	
	
	/**
	 * 以UTF-8编码方式读取文件内容
	 * 
	 * @param path
	 * @return
	 * @throws IOException
	 */
	public static String getContentByLocalFile(File path) throws IOException {
		InputStream input = new FileInputStream(path);
		InputStreamReader reader = new InputStreamReader(input, "utf-8");
		BufferedReader br = new BufferedReader(reader);
		StringBuilder builder = new StringBuilder();
		// System.out.print(trimExtension(path.getName())+" ");

		String temp1 = br.readLine();
		String temp2 = br.readLine();
		while (temp2 != null) {
			// builder.append(temp);
			temp1 = temp2;
			temp2 = br.readLine();
		}
		return temp1;
	}

	/**
	 * 去掉文件的扩展名
	 * 
	 * @param filename
	 * @return
	 */
	public static String trimExtension(String filename) {
		if ((filename != null) && (filename.length() > 0)) {
			int i = filename.lastIndexOf('.');
			if ((i > -1) && (i < (filename.length()))) {
				return filename.substring(0, i);
			}
		}
		return filename;
	}



}