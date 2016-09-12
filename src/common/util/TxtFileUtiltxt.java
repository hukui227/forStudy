package common.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class TxtFileUtiltxt {
	
//	public static int WIN_NUM = 1;
//	public static int NOT_FIND = 2;
//	public static int SUM_NUM = 3;
//	public static int MAX_NOT_FIND = 4;
//	
//	/**
//	 * @param args
//	 */
//	public static void main(String[] args) {
//		newFileName("E:/redNumber/2017016.txt");
//
//	}
//	
//	public static Map<Integer,List<List<Integer>>> readTxtFile(String fileName,int type,boolean lastLine){
//		try {
//			FileInputStream fis = new FileInputStream(fileName);
//			InputStreamReader isr = new InputStreamReader(fis);
//			BufferedReader br = new BufferedReader(isr);
//			String lineTx = null;
//			if(type==WIN_NUM){
//				Map<Integer,List<List<Integer>>> winNum = new HashMap<Integer,List<List<Integer>>>();//中奖号码信息
//				while((lineTx = br.readLine())!=null){
//					//System.out.println(lineTx);
//					String key = lineTx.split(":")[0];
//					String nums = lineTx.split(":")[1];
//					String[] reds = nums.split(";")[0].split(",");
//					List<Integer> blue = new ArrayList<Integer>();
//					blue.add(Integer.valueOf(nums.split(";")[1]));
//					List<Integer> r = new ArrayList<Integer>(6);
//					
//					for(int i=0;i<6;i++){
//						r.add(Integer.valueOf(reds[i]));
//					}
//					List<List<Integer>> list = new ArrayList<List<Integer>>();
//					list.add(r);
//					list.add(blue);
//					winNum.put(Integer.valueOf(key), list);
//				}
//				br.close();
//				return winNum;
//			}else if(type==NOT_FIND||type==MAX_NOT_FIND){
//				Map<Integer,List<List<Integer>>> countNotFind = new HashMap<Integer,List<List<Integer>>>();//所以号码的概率值信息
//				while((lineTx = br.readLine())!=null){
//					if(lastLine){
//						countNotFind = new HashMap<Integer,List<List<Integer>>>();
//					}
//					String key = lineTx.split(":")[0];
//					String nums = lineTx.split(":")[1];
//					String[] reds = nums.split(";")[0].split(",");
//					String[] blues = nums.split(";")[1].split(",");
//					List<Integer> r = new ArrayList<Integer>(33);
//					List<Integer> b = new ArrayList<Integer>(16);
//					for(int i=0;i<33;i++){
//						r.add(Integer.valueOf(reds[i]));
//					}
//					for(int j=0;j<16;j++){
//						b.add(Integer.valueOf(blues[j]));
//					}
//					List<List<Integer>> list = new ArrayList<List<Integer>>();
//					list.add(r);
//					list.add(b);
//					countNotFind.put(Integer.valueOf(key), list);
//				}
//				br.close();
//				return countNotFind;
//			}else if(type==SUM_NUM){//号码和值
//				Map<Integer,List<List<Integer>>> sumNum = new HashMap<Integer,List<List<Integer>>>();
//				while((lineTx = br.readLine())!=null){
//					//System.out.println(lineTx);
//					String key = lineTx.split(":")[0];
//					String nums = lineTx.split(":")[1];
//					
//					List<Integer> sum = new ArrayList<Integer>();
//					sum.add(Integer.valueOf(nums));
//					
//					List<List<Integer>> list = new ArrayList<List<Integer>>();
//					list.add(sum);
//					sumNum.put(Integer.valueOf(key), list);
//				}
//				br.close();
//				return sumNum;
//			}else{//平均漏号值。格式：期号：总期数#出现总次数,分割;分割红色蓝色#平均漏号值,分割;分割红色蓝色#最大漏号值,分割;分割红色蓝色
//				
//			}
//		} catch (FileNotFoundException fe) {
//			System.out.println("文件不存在");
//		} catch (Exception e){
//			System.out.println(e.getMessage());
//		}
//		
//		return null;
//	}
//	
//	public static Map<Integer,List<List<Integer>>> addFileContent(String filePath,List<Map.Entry<Integer,int[][]>> caculateResult,List<List<Integer>> maxNotFindHistory){
//		BufferedWriter out = null;
//		Map<Integer,List<List<Integer>>> resultMap = new HashMap<Integer,List<List<Integer>>>();
//		try{
//			out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filePath, true)));
//			
//			for(Map.Entry<Integer,int[][]> result:caculateResult){
//				boolean canAdd = false;
//				List<List<Integer>> tem = new ArrayList<List<Integer>>();
//				int[][] rs = result.getValue();
//				StringBuffer sb = new StringBuffer();
//				sb.append("\r\n"+result.getKey()+":");
//				for(int i=0;i<rs.length;i++){
//					List<Integer> temOne = new ArrayList<Integer>();
//					int[] ns = rs[i];
//					List<Integer> l = maxNotFindHistory.get(i);
//					for(int j=0;j<ns.length;j++){
//						int n = ns[j];
//						int maxNum = l.get(j);
//						if(maxNum<n){
//							canAdd = true;
//						}
//						temOne.add(n);
//						sb.append(n+",");
//					}
//					tem.add(temOne);
//					sb.append(";");
//				}
//				if(canAdd){
//					resultMap.put(result.getKey(), tem);
//				}
//				String rss = sb.substring(0,sb.length()-2);
//				out.write(rss.replaceAll(",;", ";"));
//			}
//		}catch (Exception e) {     
//            e.printStackTrace();     
//        } finally {     
//            try {     
//                if(out != null){  
//                    out.close();     
//                }  
//            } catch (IOException e) {     
//                e.printStackTrace();     
//            }     
//        }
//        return resultMap;
//	}
//	
//	public static void addNewFileContent(String filePath,Map<Integer,int[][]> chances,int type){
//		BufferedWriter out = null;
//		try{
//			out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filePath, true)));
//			out.write("---------区 "+type+" ------------\r\n");
//			//map转list
//			List<Map.Entry<Integer,int[][]>> mappingList = new ArrayList<Map.Entry<Integer,int[][]>>(chances.entrySet()); 
//			Collections.sort(mappingList, new Comparator<Map.Entry<Integer, int[][]>>(){
//
//				public int compare(Map.Entry<Integer, int[][]> m1, Map.Entry<Integer,int[][]> m2) {
//					
//					return m1.getKey().compareTo(m2.getKey());
//				}});
//			//list转map
//			for(Map.Entry<Integer,int[][]> map:mappingList){
//				out.write(map.getKey()+":");
//				int[][] ch = map.getValue();
//				for(int[] c1:ch){
//					if(c1!=null)
//						out.write(c1[0]+","+c1[1]+","+c1[2]+","+c1[3]+","+c1[4]+","+c1[5]+"---");
//				}
//				out.write("\r\n");
//			}
//		}catch (Exception e) {     
//            e.printStackTrace();     
//        } finally {     
//            try {     
//                if(out != null){  
//                    out.close();     
//                }  
//            } catch (IOException e) {     
//                e.printStackTrace();     
//            }     
//        }
//	}
//	
//	public static void addNewFileContentBase(String filePath,Map<Integer,Integer> chancesArea,int totalNum,Map<Integer,Map<Integer,Integer>> chancesAreaChances,List<Map.Entry<Integer,Integer>> mappingList){
//		BufferedWriter out = null;
//		try{
//			out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filePath, true)));
//			
//			out.write("\r\n-------区概率值组合，概率---------\r\n");
//			out.write("总次数(概率值组合个数)："+totalNum+"\r\n");
//			for(Integer areaNum:chancesArea.keySet()){
//				out.write("区"+areaNum+"： 概率值组合出现次数："+chancesArea.get(areaNum)+";概率："+MathUtil.format(MathUtil.divide(chancesArea.get(areaNum),totalNum),1)+"%\r\n");
//			}
//			
//			out.write("\r\n-------区内和值，概率--------\r\n");
//			for(Integer an:chancesAreaChances.keySet()){
//				int aNum = chancesArea.get(an);
//				out.write("区"+an+"： 概率值组合出现次数："+aNum+"\r\n");
//				Map<Integer,Integer> sumChances = chancesAreaChances.get(an);
//				List<Map.Entry<Integer, Integer>> sumMapping = CollectionsUtil.sortMapIntegerKey(sumChances,true);
//				
//				for(Map.Entry<Integer,Integer> cn:sumMapping){
//					out.write("   和值="+cn.getKey()+" ： 概率值组合出现次数："+cn.getValue()+" ;区内概率： "+MathUtil.format(MathUtil.divide(cn.getValue(),aNum),1)+"%\r\n");
//				}
//			}
//			
//			out.write("\r\n-------概率值组合，概率---------\r\n");
//			for(Map.Entry<Integer,Integer> cv:mappingList){
//				int nu = cv.getValue();
//				out.write("概率值和："+cv.getKey()+";出现次数："+nu+";概率："+MathUtil.format(MathUtil.divide(nu,totalNum),1)+"%\r\n");
//			}
//			
//		}catch (Exception e) {     
//            e.printStackTrace();     
//        } finally {     
//            try {     
//                if(out != null){  
//                    out.close();     
//                }  
//            } catch (IOException e) {     
//                e.printStackTrace();     
//            }     
//        }	
//	}
//	
//	public static void writeCaculateResult(String filePath,Map<Integer,String[]> chanceMappingNumResult){
//		BufferedWriter out = null;
//		try{
//			out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filePath, true)));
//			out.write("\r\n-------推断号码组合情况，组合--------\r\n");
//			int areaNum = 5;
//			
//			out.write("共 "+chanceMappingNumResult.size()+" 条记录\r\n");
//			Map<Integer,Integer> numCount = new HashMap<Integer,Integer>();
//			
//			for(Integer finalKey:chanceMappingNumResult.keySet()){
//				List<Integer> tem = new ArrayList<Integer>();
//				//out.write("区号： "+areaNum+" ；概率值和值是 "+finalKey+" 的号码组合: \r\n");
//				for(String finalN:chanceMappingNumResult.get(finalKey)){
//					Integer finalNn = Integer.valueOf(finalN);
//					tem.add(finalNn);
//					//统计号码出现次数，有就在原来值上+1，第一次出现则为1
//					if(numCount.containsKey(finalNn)){
//						numCount.put(finalNn, numCount.get(finalNn)+1);
//					}else{
//						numCount.put(finalNn, 1);
//					}
//				}
//				Collections.sort(tem);
//				
//				//打印每个号码组合
//				for (Integer n : tem) {
//					out.write(n + ",");
//				}
//				out.write("\r\n");
//				
//			}
//			
//			//打印单个号码在这次的估算中出现的概率
//			out.write("\r\n-------以上推断出的号码组合，统计单个号码出现次数和概率--------\r\n");
//			for(Integer finalNK:numCount.keySet()){
//				out.write("号码： "+finalNK+" 出现次数： "+numCount.get(finalNK)+" ; 概率： "+MathUtil.format(MathUtil.divide(numCount.get(finalNK),chanceMappingNumResult.size()*6),1)+"%\r\n");
//			}
//		}catch (Exception e) {     
//            e.printStackTrace();     
//        } finally {     
//            try {     
//                if(out != null){  
//                    out.close();     
//                }  
//            } catch (IOException e) {     
//                e.printStackTrace();     
//            }     
//        }	
//	}
//	
//	public static void writeWin(String filePath,int[][] winNumOne,int redChanceCount,int type,String subtype){
//		BufferedWriter out = null;
//		try{
//			out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filePath, true)));
//			out.write("\r\n-----实际中奖号码情况--------\r\n");
//			int[] winRedNumChance = winNumOne[1];
//			
//			out.write("中奖号码："+winNumOne[0][0]+","+winNumOne[0][1]+","+winNumOne[0][2]+","+winNumOne[0][3]+","+winNumOne[0][4]+","+winNumOne[0][5]);
//			out.write("\r\n号码的概率值和："+redChanceCount+";概率值组合:"+winRedNumChance[0]+";"+winRedNumChance[1]+";"+winRedNumChance[2]+";"+winRedNumChance[3]+";"+winRedNumChance[4]+";"+winRedNumChance[5]);
//			out.write("\r\n所在区间："+type+"-"+subtype);
//			
//			
//		//System.out.println("号码的概率值和："+(winRedNumChance[0]+winRedNumChance[1]+winRedNumChance[2]+winRedNumChance[3]+winRedNumChance[4]+winRedNumChance[5])+";概率值组合:"+winRedNumChance[0]+";"+winRedNumChance[1]+";"+winRedNumChance[2]+";"+winRedNumChance[3]+";"+winRedNumChance[4]+";"+winRedNumChance[5]);
//		}catch (Exception e) {     
//            e.printStackTrace();     
//        } finally {     
//            try {     
//                if(out != null){  
//                    out.close();     
//                }  
//            } catch (IOException e) {     
//                e.printStackTrace();     
//            }     
//        }	
//	}
//	
//	public static void writeNewMaxYiLou(String file,Map<Integer,List<List<Integer>>> newMaxNotFindHistory){
//		BufferedWriter out = null;
//		try{
//			out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file, true)));
//			List<Map.Entry<Integer,List<List<Integer>>>> mapping = CollectionsUtil.sortMapList(newMaxNotFindHistory,true);
//			
//			StringBuffer sb = new StringBuffer();
//			for(Map.Entry<Integer,List<List<Integer>>> enty:mapping){
//				sb.append(enty.getKey()+":");
//				for(List<Integer> l:enty.getValue()){
//					for(Integer num:l){
//						sb.append(num+",");
//					}
//					sb.append(";");
//				}
//				sb.append("\r\n");
//			}
//			out.write(sb.toString().replaceAll(",;", ";"));
//		}catch (Exception e) {     
//            e.printStackTrace();     
//        } finally {
//            try {     
//                if(out != null){  
//                    out.close();     
//                }  
//            } catch (IOException e) {     
//                e.printStackTrace();     
//            }     
//        }		
//	}
//	
//	public static String newFileName(String destFileName){
//		String tem = destFileName.substring(0,destFileName.indexOf("."));
//		File file = new File(destFileName);  
//		int i=1;
//		while(file.exists()){
//			destFileName = tem+"_"+i+".txt";
//			file = new File(destFileName);
//			i++;
//		}
//		return destFileName;
//	}

}
