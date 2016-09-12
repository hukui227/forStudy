package struts.servlet;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import common.util.TxtFileUtiltxt;

/**
 * 通过读取历史中奖号码，计算其所有号码的未出现次数
 * @author bestv
 * （计算公式：平均遗漏＝统计期数/(出现次数+1)）【2016年3月11号-总共1926期】
 */
public class CaculateNotFindTimes {

	
//	public static void main(String[] args) {
//		CaculateNotFindTimes q = new CaculateNotFindTimes();
//		String winNumFile = "E:/redNumber/winNum.txt";
//		String notFindFile = "E:/redNumber/notFindNum.txt";//当前遗漏统计
//		String sumNumFile = "E:/redNumber/sumNum.txt";
//		String maxNotFindFile = "E:/redNumber/maxNotFindNum.txt";//最大遗漏
//		
//		Map<Integer,List<List<Integer>>> winNumHistory = TxtFileUtil.readTxtFile(winNumFile,TxtFileUtil.WIN_NUM,false);
//		Map<Integer,List<List<Integer>>> notFindHistory = TxtFileUtil.readTxtFile(notFindFile,TxtFileUtil.NOT_FIND,true);
//		Map<Integer,List<List<Integer>>> maxNotFindHistory = TxtFileUtil.readTxtFile(maxNotFindFile, TxtFileUtil.MAX_NOT_FIND, true);
//		Iterator<List<List<Integer>>> l = maxNotFindHistory.values().iterator();
//		
//		//根据最近的开奖号码，计算所有号码的未中奖值，并输出到txt文件中保存。
//		List<Map.Entry<Integer,int[][]>> mappingList = q.writeNotFindFile(winNumHistory, notFindHistory);
//		//在保存当前遗漏，顺便和最大遗漏比较，出现任何比最大遗漏的值大的，则记录下来作为新的最大遗漏保存起来
//		Map<Integer,List<List<Integer>>> newMaxNotFindHistory = new HashMap<Integer,List<List<Integer>>>();
//		if(l.hasNext()){
//			newMaxNotFindHistory = TxtFileUtil.addFileContent(notFindFile,mappingList,l.next());
//		}else{
//			newMaxNotFindHistory = TxtFileUtil.addFileContent(notFindFile,mappingList,null);
//		}
//		if(newMaxNotFindHistory.size()>0){
//			TxtFileUtil.writeNewMaxYiLou(maxNotFindFile, newMaxNotFindHistory);
//		}
//		
//		//生成号码总和历史情况
//		q.writeSumNumFile(q.sortWinNumHistory(winNumHistory), sumNumFile);
//		
//		//生成其他遗漏情况统计
//		//Map<Integer,List<List<Integer>>> otherNotFind = TxtFileUtil.readTxtFile(otherNotFindFile, TxtFileUtil.OTHER_NOT_FIND, true);
//	}
//	
//	private List<Map.Entry<Integer,int[][]>> writeNotFindFile(Map<Integer,List<List<Integer>>> winNumHistory,Map<Integer,List<List<Integer>>> notFindHistory){
//		
//
//		Map<Integer,int[][]> caculateResult = new HashMap<Integer,int[][]>();
//		for(Integer key : notFindHistory.keySet()){
//			int i=1;
//			List<Integer> temReds = new ArrayList<Integer>();
//			List<Integer> temBlue = new ArrayList<Integer>();
//			while(winNumHistory.keySet().contains(key+i)){
//				List<List<Integer>> winNum = winNumHistory.get(key+i);
//				List<Integer> reds = winNum.get(0);
//				int blue = winNum.get(1).get(0);
//				List<Integer> notFindReds = new ArrayList<Integer>();
//				List<Integer> notFindBlues = new ArrayList<Integer>();
//				
//				
//				
//				if(notFindHistory.containsKey(key+i-1)){
//					notFindReds = notFindHistory.get(key+i-1).get(0);
//					notFindBlues = notFindHistory.get(key+i-1).get(1);
//				}else if(temReds.size()>0&&temBlue.size()>0){
//					notFindReds.addAll(temReds);
//					notFindBlues.addAll(temBlue);
//					temReds.clear();
//					temBlue.clear();
//				}
//				
//				if(notFindReds.size()>0&&notFindBlues.size()>0){
//					int[] newNotFindReds = new int[33];
//					for(int j=0;j<notFindReds.size();j++){
//						if(reds.contains(j+1)){
//							newNotFindReds[j] = 0;
//						}else{
//							newNotFindReds[j] = notFindReds.get(j)+1;
//						}
//						temReds.add(j, newNotFindReds[j]);
//					}
//					int[] newNotFindBlues = new int[16];
//					for(int k=0;k<16;k++){
//						if(blue==(k+1)){
//							newNotFindBlues[k] = 0;
//						}else{
//							newNotFindBlues[k] = notFindBlues.get(k)+1;
//						}
//						temBlue.add(k, newNotFindBlues[k]);
//					}
//					
//					caculateResult.put(key+i, new int[][]{newNotFindReds,newNotFindBlues});
//				}
//				i++;
//			}
//		}
//		
//		List<Map.Entry<Integer,int[][]>> mappingList = new ArrayList<Map.Entry<Integer,int[][]>>(caculateResult.entrySet());
//		Collections.sort(mappingList, new Comparator<Map.Entry<Integer,int[][]>>(){
//
//			public int compare(Entry<Integer, int[][]> o1,
//					Entry<Integer, int[][]> o2) {
//				
//				return o1.getKey().compareTo(o2.getKey());
//			}
//			
//			
//		});
//		
//		return mappingList;
//	}
//
//	public void writeSumNumFile(List<Map.Entry<Integer,List<List<Integer>>>> winNumHistory,String fileUrl){
//		Map<Integer,List<List<Integer>>> sumNumHistory = TxtFileUtil.readTxtFile(fileUrl,TxtFileUtil.SUM_NUM,false);
//		
//		StringBuffer sb = new StringBuffer();
//		for(Map.Entry<Integer,List<List<Integer>>> history:winNumHistory){
//			Integer key = history.getKey();
//			if(!sumNumHistory.containsKey(key)){
//				List<Integer> list = history.getValue().get(0);
//				int sum = 0;
//				for(Integer red:list){
//					sum += red;
//				}
//				sb.append(key+":"+sum+"\r\n");
//			}
//			
//		}
//		
//		if(sb.length()>0){
//			BufferedWriter out = null;
//			try{
//				out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileUrl, true)));
//				out.write(sb.toString());
//			}catch (Exception e) {     
//	            e.printStackTrace();     
//	        } finally {     
//	            try {     
//	                if(out != null){  
//	                    out.close();     
//	                }  
//	            } catch (IOException e) {     
//	                e.printStackTrace();     
//	            }     
//	        }
//		}
//		
//	}
//	
//	public List<Map.Entry<Integer,List<List<Integer>>>> sortWinNumHistory(Map<Integer,List<List<Integer>>> winNumHistory){
//		List<Map.Entry<Integer,List<List<Integer>>>> mappingList = new ArrayList<Map.Entry<Integer,List<List<Integer>>>>(winNumHistory.entrySet());
//		Collections.sort(mappingList, new Comparator<Map.Entry<Integer,List<List<Integer>>>>(){
//
//			public int compare(Entry<Integer,List<List<Integer>>> o1,
//					Entry<Integer,List<List<Integer>>> o2) {
//				
//				return o1.getKey().compareTo(o2.getKey());
//			}
//			
//			
//		});
//		
//		return mappingList;
//	}
	
}
