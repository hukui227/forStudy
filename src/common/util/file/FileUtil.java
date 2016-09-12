package common.util.file;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Collection;
import java.util.List;

public abstract class FileUtil {
	
	public final String readFile(String fileUrl,boolean isPutList,Collection list){
		BufferedReader re = null;
		String laststr = "";
		try {
			InputStream ins = new FileInputStream(fileUrl);
			InputStreamReader reader = new InputStreamReader(ins);
		    re = new BufferedReader(reader);
			String tempString = null;
			while((tempString = re.readLine()) != null){
				if(isPutList){
					putList(tempString,list);
				}else{
					laststr += tempString;
				}
				
			}
			re.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch(IOException ie){
			ie.printStackTrace();
		} finally{
			if (re != null) {
				try {
					re.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return laststr;
	}
	
	public String writeFile(String fileUrl){
		
		return "";
	}
	
	public abstract void putList(String temStr,Collection list);
}
