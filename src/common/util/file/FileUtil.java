package common.util.file;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Collection;

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
	
	public final void writeFile(String fileUrl,Object content){
		String result = castToContent(content);
		if(result!=null&&result.length()>0){
			BufferedWriter writer = null;
			
			try{
				writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileUrl, true)));
				writer.write(result+"\r\n");
			}catch(Exception e){
				e.printStackTrace();
			}finally {
				try{
					if(writer!=null)
						writer.close();
				}catch(Exception e){
					e.printStackTrace();
				}
			}
		}
	}
	
	public abstract void putList(String temStr,Collection list);
	
	public abstract String castToContent(Object content);
}
