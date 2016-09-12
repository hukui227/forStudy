package struts.servlet;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.util.CollectionsUtil;
import common.util.TxtFileUtiltxt;
public class Query {

//	int totalNum = 0;
//	Map<Integer,Integer> chancesArea = new HashMap<Integer,Integer>();
//	Map<Integer,Map<Integer,Integer>> chancesAreaChances = new HashMap<Integer,Map<Integer,Integer>>();
//	Map<Integer,String> chanceMapNum = new HashMap<Integer,String>();
//	boolean needPrint = false;
//	boolean needWrite = true;
//	static String writeFilePath = "";
//	int length = 5;//最高概率值的和的概率与最低概率值的和的概率，相差几个（不包含相同的概率情况）。参见：区内和值，概率
//	int[] sums = new int[]{0,1000};
//	
//	public static void main(String[] args){
//		Query q = new Query();
//		
//		String fileName = "E:/redNumber/notFindNum.txt";
//		String winNumFile = "E:/redNumber/winNum.txt";
//		String sumNumFile = "E:/redNumber/sumNum.txt";
//		
//		int doubleNumberKey = 2016017;
//		
//		int[][] notFindOne = q.getOneNotFind(fileName,doubleNumberKey);
//		
//		int[][] wins = q.getWins(winNumFile, doubleNumberKey+1, notFindOne);
//		
//		writeFilePath = "E:/redNumber/"+doubleNumberKey+".txt";
//		
//		q.caculateSums(sumNumFile);//计算过去号码和值的最小值和最大值
//		
//		//q.printNotFindChanceHistory(countNotFind);
//		//q.caculateNotFindChance(winNumHistory,countNotFind);
//		//System.out.println("--计算后---");
//		//q.printNotFindChanceHistory(countNotFind);
//		//根据未中奖记录，预测中奖比例高的号码,//根据预测和下期中奖号码对比,计算中奖区间//分析每个概率值和的中奖率，每个区间的中奖率
//		q.countHighChanceNum(notFindOne,wins,writeFilePath);
//		
//	}
//	
//	private int[][] getOneNotFind(String fileName,int doubleNumberKey){
//		Map<Integer,int[][]> countNotFind = new HashMap<Integer,int[][]>();
//		
//		Map<Integer,List<List<Integer>>> acountNotFind = TxtFileUtil.readTxtFile(fileName, TxtFileUtil.NOT_FIND, false);
//		
//		for(Integer key:acountNotFind.keySet()){
//			int[][] i = new int[2][];
//			int j=0;
//			i[0] = new int[acountNotFind.get(key).get(0).size()];
//			for(int r:acountNotFind.get(key).get(0)){
//				i[0][j] = r;
//				j++;
//			}
//			j=0;
//			i[1] = new int[acountNotFind.get(key).get(1).size()];
//			for(int b:acountNotFind.get(key).get(1)){
//				i[1][j] = b;
//				j++;
//			}
//			countNotFind.put(key, i);
//		}
//		
//		return countNotFind.get(doubleNumberKey);
//	}
//	
//	private int[][] getWins(String winNumFile,int doubleNumberKey,int[][] notFindOne){
//		int[][] wins = null;
//		Map<Integer,List<List<Integer>>> winNumHistory = TxtFileUtil.readTxtFile(winNumFile,1,false);
//		
//		if(winNumHistory!=null&&winNumHistory.containsKey(doubleNumberKey)){
//			List<List<Integer>> winNums = winNumHistory.get(doubleNumberKey);
//			List<Integer> reds = winNums.get(0);
//			int blue = winNums.get(1).get(0);
//			
//			wins = new int[4][];
//			
//			int k=0;
//			wins[0] = new int[reds.size()];
//			for(Integer red:reds){
//				wins[0][k] = red;
//				k++;
//			}
//			k=0;
//			wins[1] = new int[6];
//			for(int w:wins[0]){
//				for(int m=0;m<notFindOne[0].length;m++){
//					if(w==(m+1)){
//						wins[1][k] = notFindOne[0][m];
//						k++;
//					}
//				}
//			}
//			wins[2] = new int[]{blue};
//			k=0;
//			wins[3] = new int[1];
//			for(int n=0;n<notFindOne[1].length;n++){
//				if(blue==(n+1)){
//					wins[3][k] = notFindOne[1][n];
//					k++;
//				}
//			}
//		}
//		return wins;
//	}
//	
//	/**
//	 * 概率和值法：
//	 * 	通过号码没有出现产生的值-概率值，计算任意6个号码的概率值，分析下次出现号码的概率值规律。反推下次可能出现的6个号码的组合情况
//	 * 难点：
//	 * 	概率值有重复，本来的计算是33个概率值取任意6个来计算和值，统计组合情况。但是，这种情况下的组合太大，还没想到好办法一次性呈现。
//	 *  使用去重后，又遇到，不一定是6个概率值，由于重复，那么本来的组合可以是去重后的5个，4个，。。。。等等，现在还没办法怎么处理这种情况。
//	 *  --- 解决
//	 *  分6个情况的概率值组合情况。
//	 *  问题：哪个概率值容易中，就是说哪个概率值出现后，下次这个号码就会选中。概率值的概率.
//	 *  
//	 * @param countNotFind
//	 */
//	private void countHighChanceNum(int[][] notFindOne,int[][] winNumOne,String writeFilePath){
//		
//		
//		//计算每种6个的和值，先去重，在排序，再按规律取6个数字，得出和值。
//		//原来概率中有重叠概率值，在这里去重后，那么6个数字对应的概率值就不是6个数字了，极端的是1个，
//		//6个0，4个1，2个2，3个3，4个4，2个5，3个6，2和7，1个9，2个11，1个12，1个13，1个14，1个15
//		//计算原来概率值重复次数，带入到这个计算中，
//		List<Integer> l = new ArrayList<Integer>();
//		Map<Integer,Integer> sameChances = new HashMap<Integer,Integer>();
//		for(int v:notFindOne[0]){
//			if(!l.contains(v)){
//				l.add(v);
//			}else{
//				if(sameChances.keySet().contains(v)){
//					int sameNum = sameChances.get(v);
//					sameChances.put(v, sameNum+1);
//				}else{
//					sameChances.put(v, 2);
//				}
//			}
//		}
//		Collections.sort(l);
//		
//		//概率值和号码对应关系
//		mappingChanceToNum(notFindOne[0]);
//		
//		methodOne(l,sameChances,winNumOne,writeFilePath);
//	}
//	
//	/**
//	 * 去重后，出现概率值个数情况：
//	 * 6个：1+1+1+1+1+1.（6个号的概率值都不相同）
//	 * 5个:1+1+1+1+2	   (6个号，有2个号的概率值相同，其他4个不同)
//	 * 4个:1+1+1+3	   (6个号，有3个号的概率值相同，其他3个不同)
//	 *     1+1+2+2     (6个号，有2个号的概率值相同，另外2个号的概率值相同，剩下2个号的概率值各自不同)
//	 * 3个:1+2+3       (6个号，有3个号的概率值相同，另外2个号的概率值相同，剩下1个号的概率值不同)
//	 *     2+2+2       (6个号，有2个号的概率值相同，另外2个号的概率值相同，剩下2个号的概率值相同，3对之间各自不同)
//	 * 2个:3+3         (6个号，有3个号的概率值相同，另外3个号的概率值相同)
//	 *     2+4         (6个号，有2个号的概率值相同，另外4个号的概率值相同)
//	 *     1+5         (6个号，有5个号的概率值相同，剩下1个号的概率值不同)
//	 * 1个:6           (6个号，都是相同的概率值) 
//	 * 
//	 * 将各种组合分类存放，将下期中奖号码和概率值组合传入，查看规律
//	 * @param l
//	 * @param chances
//	 */
//	private void methodOne(List<Integer> l,Map<Integer,Integer> sameChances,int[][] winNumOne,String writeFilePath){
//		
//		//6层循环:6个不同的
//		Map<Integer,int[][]> sixChances = methodOneForSix(l);
//		
//		//5个
//		Map<Integer,int[][]> fiveChances = methodOneForFive(l,sameChances);
//		
//		//4ge
//		Map<Integer,int[][]> fourChancesOne = methodOneForFourOne(l,sameChances);
//		Map<Integer,int[][]> fourChancesTwo = methodOneForFourTwo(l,sameChances);
//		
//		//3ge
//		Map<Integer,int[][]> threeChancesOne = methodOneForThreeOne(l,sameChances);
//		Map<Integer,int[][]> threeChancesTwo = methodOneForThreeTwo(l,sameChances);
//		
//		//2ge
//		Map<Integer,int[][]> twoChancesOne = methodOneForTwoOne(l,sameChances);
//		Map<Integer,int[][]> twoChancesTwo = methodOneForTwoTwo(l,sameChances);
//		Map<Integer,int[][]> twoChancesThree = methodOneForTwoThree(l,sameChances);
//		
//		//1ge
//		Map<Integer,int[][]> oneChances = methodOneForOne(l,sameChances);
//		
//		if(needPrint){
//			printResult(oneChances,1);
//			printResult(twoChancesOne,21);
//			printResult(twoChancesTwo,22);
//			printResult(twoChancesThree,23);
//			printResult(threeChancesOne,31);
//			printResult(threeChancesTwo,32);
//			printResult(fourChancesOne,41);
//			printResult(fourChancesTwo,42);
//			printResult(fiveChances,5);
//			printResult(sixChances,6);//打印概率值组合及其和值情况
//		}
//		
//		if(needWrite){
//			writeFilePath = TxtFileUtil.newFileName(writeFilePath);
//			TxtFileUtil.addNewFileContent(writeFilePath, oneChances,1);
//			TxtFileUtil.addNewFileContent(writeFilePath,twoChancesOne,21);
//			TxtFileUtil.addNewFileContent(writeFilePath,twoChancesTwo,22);
//			TxtFileUtil.addNewFileContent(writeFilePath,twoChancesThree,23);
//			TxtFileUtil.addNewFileContent(writeFilePath,threeChancesOne,31);
//			TxtFileUtil.addNewFileContent(writeFilePath,threeChancesTwo,32);
//			TxtFileUtil.addNewFileContent(writeFilePath,fourChancesOne,41);
//			TxtFileUtil.addNewFileContent(writeFilePath,fourChancesTwo,42);
//			TxtFileUtil.addNewFileContent(writeFilePath,fiveChances,5);
//			TxtFileUtil.addNewFileContent(writeFilePath,sixChances,6);
//		}
//		//计算概率值和值概率
//		
//		Map<Integer,Integer> caculateValueResult =  new HashMap<Integer,Integer>();
//		caculateValueResult = caculateChancesValue(sixChances,6,caculateValueResult);
//		caculateValueResult = caculateChancesValue(fiveChances,5,caculateValueResult);
//		caculateValueResult = caculateChancesValue(fourChancesOne,41,caculateValueResult);
//		caculateValueResult = caculateChancesValue(fourChancesTwo,42,caculateValueResult);
//		caculateValueResult = caculateChancesValue(threeChancesOne,31,caculateValueResult);
//		caculateValueResult = caculateChancesValue(threeChancesTwo,32,caculateValueResult);
//		caculateValueResult = caculateChancesValue(twoChancesOne,21,caculateValueResult);
//		caculateValueResult = caculateChancesValue(twoChancesTwo,22,caculateValueResult);
//		caculateValueResult = caculateChancesValue(twoChancesThree,23,caculateValueResult);
//		caculateValueResult = caculateChancesValue(oneChances,1,caculateValueResult);
//		
//		List<Map.Entry<Integer,Integer>> mappingList = CollectionsUtil.sortMapIntegerKey(caculateValueResult,true);
//		
//		if(needWrite){
//			TxtFileUtil.addNewFileContentBase(writeFilePath, chancesArea, totalNum, chancesAreaChances, mappingList);
//			//取号码算法
//			int areaNum = caculateFinalChances(chancesArea);
//			Map<Integer,int[][]> finalChances = null;
//			switch(areaNum){
//				case 6: finalChances=sixChances; break;
//				case 5: finalChances=fiveChances;break;
//				case 41: finalChances=fourChancesOne; break;
//				case 42: finalChances=fourChancesTwo;break;
//				case 31: finalChances=threeChancesOne; break;
//				case 32: finalChances=threeChancesTwo;break;
//				case 21: finalChances=twoChancesOne; break;
//				case 22: finalChances=twoChancesTwo;break;
//				case 23: finalChances=twoChancesThree; break;
//				case 1: finalChances=oneChances;break;
//				default : finalChances=null; break;
//			}
//			List<Integer> finalSum = caculateFinalSum(chancesAreaChances,areaNum,length);
//			
//			//比如经过上面的算法，得出号码最可能出现在5区的和值是34，34之间的概率值组合中
//			if(finalChances!=null&&finalSum!=null){
//				Map<Integer,String[]> chanceMappingNumResult = caculateChanceMappingNum(finalChances,finalSum);
//				//取号码结果
//				TxtFileUtil.writeCaculateResult(writeFilePath, chanceMappingNumResult);
//			}
//			
//			//打印中奖结果
//			if(winNumOne!=null){
//				whichChances(sixChances,6,"",winNumOne,writeFilePath);//查看同期的中奖号码出现在概率值的哪个和值下的哪个概率值组合，是哪个区间的
//				whichChances(fiveChances,5,"",winNumOne,writeFilePath);
//				whichChances(fourChancesOne,4,"1",winNumOne,writeFilePath);
//				whichChances(fourChancesTwo,4,"2",winNumOne,writeFilePath);
//				whichChances(threeChancesOne,3,"1",winNumOne,writeFilePath);
//				whichChances(threeChancesTwo,3,"2",winNumOne,writeFilePath);
//				whichChances(twoChancesOne,2,"1",winNumOne,writeFilePath);
//				whichChances(twoChancesTwo,2,"2",winNumOne,writeFilePath);
//				whichChances(twoChancesThree,2,"3",winNumOne,writeFilePath);
//				whichChances(oneChances,1,"",winNumOne,writeFilePath);
//			}
//		}
//		
//	}
//	
//	private void whichChances(Map<Integer,int[][]> chances,int type,String subtype,int[][] winNumOne,String writeFilePath){
//		int[] winRedNumChance = winNumOne[1];
//		
//		int redChanceCount = winRedNumChance[0]+winRedNumChance[1]+winRedNumChance[2]+winRedNumChance[3]+winRedNumChance[4]+winRedNumChance[5];
//		List<Integer> winNumList = new ArrayList<Integer>();
//		for(Integer n:winRedNumChance){
//			winNumList.add(n);
//		}
//		Collections.sort(winNumList);
//		if(chances.get(redChanceCount)!=null){
//			int[][] chancesNum = chances.get(redChanceCount);
//			for(int[] nums:chancesNum){
//				List<Integer> numList = new ArrayList<Integer>();
//				for(Integer m:nums){
//					numList.add(m);
//				}
//				Collections.sort(numList);
//				if(numList.containsAll(winNumList)){
//					TxtFileUtil.writeWin(writeFilePath, winNumOne, redChanceCount, type, subtype);
//				}
//			}
//		}
//	}
//	
//	private Map<Integer,int[][]> methodOneForSix(List<Integer> l){
//		Map<Integer,int[][]> chances = new HashMap<Integer,int[][]>();
//		for (int i = 0; i < l.size() - 5; i++) {
//			int one = l.get(i);
//			for (int j = (i + 1); j < l.size() - 4; j++) {
//				int two = l.get(j);
//				for (int k = (j + 1); k < (l.size() - 3); k++) {
//					int three = l.get(k);
//					for (int m = (k + 1); m < (l.size() - 2); m++) {
//						int four = l.get(m);
//						for (int n = (m + 1); n < (l.size() - 1); n++) {
//							int five = l.get(n);
//							for (int g = (n + 1); g < l.size(); g++) {
//								int six = l.get(g);
//								int count = one + two + three + four + five + six;
//								//System.out.println(count + ":" + one + "," + two + "," + three + "," + four + "," + five + "," + six);
//										
//								if(chances.containsKey(count)){
//									int[][] b = chances.get(count);
//									int[][] c = new int[b.length+1][];
//									for(int tem=0;tem<b.length;tem++){
//										c[tem] = b[tem];
//									}
//									
//									c[b.length] = new int[]{one,two,three,four,five,six};
//									chances.put(count, c);
//								}else{
//									int[][] a = {{one,two,three,four,five,six}};
//									chances.put(count, a);
//								}
//							}
//						}
//					}
//				}
//			}
//		}
//		
//		return chances;
//	}
//	
//	private Map<Integer,int[][]> methodOneForFive(List<Integer> l,Map<Integer,Integer> sameChances){
//		Map<Integer,int[][]> chances = new HashMap<Integer,int[][]>();
//		//找出两个相同概率值的，其他4个是随机取值
//		List<Integer> twoSameList = getSameList(sameChances,2);
//		
//		for(Integer one:twoSameList){
//			
//			for(int i=0;i<l.size()-3;i++){
//				int two = l.get(i);
//				if(two!=one){
//					for(int j=(i+1);j<l.size()-2;j++){
//						int three = l.get(j);
//						if(three!=one){
//							for(int k=(j+1);k<l.size()-1;k++){
//								int four = l.get(k);
//								if(four!=one){
//									for(int g=(k+1);g<l.size();g++){
//										int five = l.get(g);
//										
//										if(five!=one){
//											int count = (one *2) + two + three + four + five;
//											//System.out.println(count + ":" + one + "," + two + "," + three + "," + four + "," + five + "," + six);
//													
//											if(chances.containsKey(count)){
//												int[][] b = chances.get(count);
//												int[][] c = new int[b.length+1][];
//												for(int tem=0;tem<b.length;tem++){
//													c[tem] = b[tem];
//												}
//												
//												c[b.length] = new int[]{one,one,two,three,four,five};
//												chances.put(count, c);
//											}else{
//												int[][] a = {{one,one,two,three,four,five}};
//												chances.put(count, a);
//											}
//										}
//									}
//								}
//							}
//						}
//					}
//				}
//			}
//		}
//		
//		return chances;
//	}
//	
////	private void methodOneForFour(List<Integer> l,Map<Integer,Integer> sameChances){
////		methodOneForFourOne(l,sameChances);
////		System.out.println();
////		methodOneForFourTwo(l,sameChances);
////	}
//	
//	private Map<Integer,int[][]> methodOneForFourOne(List<Integer> l,Map<Integer,Integer> sameChances){
//		Map<Integer,int[][]> chances = new HashMap<Integer,int[][]>();
//		//找出3个相同概率值的，其他4个是随机取值
//		List<Integer> threeSameList = getSameList(sameChances,3);
//		
//		for(Integer one:threeSameList){
//			
//				for(int j=0;j<l.size()-2;j++){
//					int tow = l.get(j);
//					if(tow!=one){
//						for(int k=(j+1);k<l.size()-1;k++){
//							int three = l.get(k);
//							if(three!=one){
//								for(int g=(k+1);g<l.size();g++){
//									int four = l.get(g);
//									if(four!=one){
//										int count = (one *3) + tow + +three + four;
//										//System.out.println(count + ":" + one + "," + two + "," + three + "," + four + "," + five + "," + six);
//												
//										if(chances.containsKey(count)){
//											int[][] b = chances.get(count);
//											int[][] c = new int[b.length+1][];
//											for(int tem=0;tem<b.length;tem++){
//												c[tem] = b[tem];
//											}
//											
//											c[b.length] = new int[]{one,one,one,tow,three,four};
//											chances.put(count, c);
//										}else{
//											int[][] a = {{one,one,one,tow,three,four}};
//											chances.put(count, a);
//										}
//									}
//								}
//							}
//						}
//					}
//				
//			}
//		}
//		
//		return chances;
//		
//	}
//	
//	private Map<Integer,int[][]> methodOneForFourTwo(List<Integer> l,Map<Integer,Integer> sameChances){
//		Map<Integer,int[][]> chancesTwo = new HashMap<Integer,int[][]>();
//		List<Integer> twoSameList = getSameList(sameChances,2);
//		
//		for(int ti=0;ti<twoSameList.size()-1;ti++){
//			int tone = twoSameList.get(ti);
//			for(int tj=(ti+1);tj<twoSameList.size();tj++){
//				int ttow = twoSameList.get(tj);
//				for(int tk=0;tk<l.size()-1;tk++){
//					int tthree = l.get(tk);
//					if(tthree!=tone&&tthree!=ttow){
//						for(int tg=(tk+1);tk<l.size();tk++){
//							int tfour = l.get(tg);
//							
//							if(tfour!=tone&&tfour!=tthree){
//								int tcount = (tone *2) + (ttow *2) + tthree + tfour;
//								//System.out.println(count + ":" + one + "," + two + "," + three + "," + four + "," + five + "," + six);
//										
//								if(chancesTwo.containsKey(tcount)){
//									int[][] b = chancesTwo.get(tcount);
//									int[][] c = new int[b.length+1][];
//									for(int tem=0;tem<b.length;tem++){
//										c[tem] = b[tem];
//									}
//									
//									c[b.length] = new int[]{tone,tone,ttow,ttow,tthree,tfour};
//									chancesTwo.put(tcount, c);
//								}else{
//									int[][] a = {{tone,tone,ttow,ttow,tthree,tfour}};
//									chancesTwo.put(tcount, a);
//								}
//							}
//						}
//					}
//				}
//			}
//		}
//		
//		return chancesTwo;
//	}
//	
////	private void methodOneForThree(List<Integer> l,Map<Integer,Integer> sameChances){
////		methodOneForThreeOne(l,sameChances);
////		System.out.println();
////		methodOneForThreeTwo(l,sameChances);
////	}
//	private Map<Integer,int[][]> methodOneForThreeOne(List<Integer> l,Map<Integer,Integer> sameChances){
//		Map<Integer,int[][]> chances = new HashMap<Integer,int[][]>();
//		//找出3个相同概率值的，其他4个是随机取值
//		List<Integer> twoSameList = getSameList(sameChances,2);
//		
//		for(int i=0;i<twoSameList.size()-2;i++){
//			int one = twoSameList.get(i);
//			for(int j=(i+1);j<twoSameList.size()-1;j++){
//				int two = twoSameList.get(j);
//				for(int k=(j+1);k<twoSameList.size();k++){
//					int three = twoSameList.get(k);
//					
//					int count = (one *2) + (two *2) +(three *2);
//					//System.out.println(count + ":" + one + "," + two + "," + three + "," + four + "," + five + "," + six);
//							
//					if(chances.containsKey(count)){
//						int[][] b = chances.get(count);
//						int[][] c = new int[b.length+1][];
//						for(int tem=0;tem<b.length;tem++){
//							c[tem] = b[tem];
//						}
//						
//						c[b.length] = new int[]{one,one,two,two,three,three};
//						chances.put(count, c);
//					}else{
//						int[][] a = {{one,one,two,two,three,three}};
//						chances.put(count, a);
//					}
//				}
//			}
//		}
//		
//		return chances;
//		
//	}
//	private Map<Integer,int[][]> methodOneForThreeTwo(List<Integer> l,Map<Integer,Integer> sameChances){
//		Map<Integer,int[][]> chancesTwo = new HashMap<Integer,int[][]>();
//		List<Integer> threeSameList = getSameList(sameChances,3);
//		List<Integer> twoNotThree = getSameList(sameChances,2);
//		twoNotThree.removeAll(threeSameList);
//		for(int ti=0;ti<threeSameList.size();ti++){
//			int tone = threeSameList.get(ti);
//			for(int tj=0;tj<twoNotThree.size();tj++){
//				int ttwo = twoNotThree.get(tj);
//				for(int tk=0;tk<l.size();tk++){
//					int tthree = l.get(tk);
//					
//					if(tthree!=tone&&tthree!=ttwo){
//						int tcount = (tone *3) + (ttwo *2) + tthree;
//						//System.out.println(count + ":" + one + "," + two + "," + three + "," + four + "," + five + "," + six);
//								
//						if(chancesTwo.containsKey(tcount)){
//							int[][] b = chancesTwo.get(tcount);
//							int[][] c = new int[b.length+1][];
//							for(int tem=0;tem<b.length;tem++){
//								c[tem] = b[tem];
//							}
//							
//							c[b.length] = new int[]{tone,tone,tone,ttwo,ttwo,tthree};
//							chancesTwo.put(tcount, c);
//						}else{
//							int[][] a = {{tone,tone,tone,ttwo,ttwo,tthree}};
//							chancesTwo.put(tcount, a);
//						}
//					}
//				}
//			}
//		}
//		
//		return chancesTwo;
//	}
//	
////	private void methodOneForTwo(List<Integer> l,Map<Integer,Integer> sameChances){
////		methodOneForTwoOne(l,sameChances);
////		System.out.println();
////		methodOneForTwoTwo(l,sameChances);
////		System.out.println();
////		methodOneForTwoThree(l,sameChances);
////	}
//	
//	private Map<Integer,int[][]> methodOneForTwoOne(List<Integer> l,Map<Integer,Integer> sameChances){
//		Map<Integer,int[][]> chances = new HashMap<Integer,int[][]>();
//		
//		List<Integer> fiveSameList = getSameList(sameChances,5);
//		
//		for(Integer one:fiveSameList){
//			for(Integer two:l){
//				
//				if(two!=one){
//					int count = (one *5) + two;
//					//System.out.println(count + ":" + one + "," + two + "," + three + "," + four + "," + five + "," + six);
//							
//					if(chances.containsKey(count)){
//						int[][] b = chances.get(count);
//						int[][] c = new int[b.length+1][];
//						for(int tem=0;tem<b.length;tem++){
//							c[tem] = b[tem];
//						}
//						
//						c[b.length] = new int[]{one,one,one,one,one,two};
//						chances.put(count, c);
//					}else{
//						int[][] a = {{one,one,one,one,one,two}};
//						chances.put(count, a);
//					}
//				}
//			}
//		}
//		
//		return chances;
//	}	
//	
//	private Map<Integer,int[][]> methodOneForTwoTwo(List<Integer> l,Map<Integer,Integer> sameChances){
//		Map<Integer,int[][]> chancesTwo = new HashMap<Integer,int[][]>();
//		List<Integer> fourSameList = getSameList(sameChances,4);
//		List<Integer> twoSameList = getSameList(sameChances,2);
//		for(Integer tone:fourSameList){
//			for(Integer ttwo:twoSameList){
//				if(tone!=ttwo){
//					int tcount = (tone *4) + (ttwo * 2);
//					//System.out.println(count + ":" + one + "," + two + "," + three + "," + four + "," + five + "," + six);
//							
//					if(chancesTwo.containsKey(tcount)){
//						int[][] b = chancesTwo.get(tcount);
//						int[][] c = new int[b.length+1][];
//						for(int tem=0;tem<b.length;tem++){
//							c[tem] = b[tem];
//						}
//						
//						c[b.length] = new int[]{tone,tone,tone,tone,ttwo,ttwo};
//						chancesTwo.put(tcount, c);
//					}else{
//						int[][] a = {{tone,tone,tone,tone,ttwo,ttwo}};
//						chancesTwo.put(tcount, a);
//					}
//				}
//			}
//		}
//		
//		return chancesTwo;
//		
//	}
//	
//	private Map<Integer,int[][]> methodOneForTwoThree(List<Integer> l,Map<Integer,Integer> sameChances){
//		Map<Integer,int[][]> chancesThree = new HashMap<Integer,int[][]>();
//		List<Integer> threeSameList = getSameList(sameChances,3);
//		for(int i=0;i<threeSameList.size()-1;i++){
//			int thone = threeSameList.get(i);
//			for(int j=(i+1);j<threeSameList.size();j++){
//				int thtwo = threeSameList.get(j);
//				
//				int thcount = (thone *3) + (thtwo * 3);
//				
//				if(chancesThree.containsKey(thcount)){
//					int[][] b = chancesThree.get(thcount);
//					int[][] c = new int[b.length+1][];
//					for(int tem=0;tem<b.length;tem++){
//						c[tem] = b[tem];
//					}
//					
//					c[b.length] = new int[]{thone,thone,thone,thtwo,thtwo,thtwo};
//					chancesThree.put(thcount, c);
//				}else{
//					int[][] a = {{thone,thone,thone,thtwo,thtwo,thtwo}};
//					chancesThree.put(thcount, a);
//				}
//			}
//		}
//		
//		return chancesThree;
//	}
//	
//	private Map<Integer,int[][]> methodOneForOne(List<Integer> l,Map<Integer,Integer> sameChances){
//		Map<Integer,int[][]> chances = new HashMap<Integer,int[][]>();
//		List<Integer> sixSameList = getSameList(sameChances,6);
//		
//		for(int one:sixSameList){
//			int count = one*6;
//			
//			if(chances.containsKey(count)){
//				int[][] b = chances.get(count);
//				int[][] c = new int[b.length+1][];
//				for(int tem=0;tem<b.length;tem++){
//					c[tem] = b[tem];
//				}
//				
//				c[b.length] = new int[]{one,one,one,one,one,one};
//				chances.put(count, c);
//			}else{
//				int[][] a = {{one,one,one,one,one,one}};
//				chances.put(count, a);
//			}
//		}
//		
//		return chances;
//	}
//	
//	//打印概率值和值和对应的各种概率值组合
//	private void printResult(Map<Integer,int[][]> chances,int type){
//		System.out.println("----------区 "+type+" ------------");
//		//map转list
//		List<Map.Entry<Integer,int[][]>> mappingList = new ArrayList<Map.Entry<Integer,int[][]>>(chances.entrySet()); 
//		Collections.sort(mappingList, new Comparator<Map.Entry<Integer, int[][]>>(){
//
//			public int compare(Map.Entry<Integer, int[][]> m1, Map.Entry<Integer,int[][]> m2) {
//				
//				return m1.getKey().compareTo(m2.getKey());
//			}});
//		//list转map
//		for(Map.Entry<Integer,int[][]> map:mappingList){
//			System.out.print(map.getKey()+":");
//			int[][] ch = map.getValue();
//			for(int[] c1:ch){
//				if(c1!=null)
//					System.out.print(c1[0]+","+c1[1]+","+c1[2]+","+c1[3]+","+c1[4]+","+c1[5]+"---");
//			}
//			System.out.println();
//		}
//	}
//	
//	//打印所有的号码的未中奖次数-概率值，是没有去除重复的，即33个号码
////	private void printNotFindChanceHistory(Map<Integer,int[][]> chances){
////		for(Integer key :chances.keySet()){
////			System.out.print("第"+key+"期：红色球：");
////			int[][] nums = chances.get(key);
////			for(int n:nums[0]){
////				System.out.print(n+" ");
////			}
////			System.out.print("蓝色球：");
////			for(int m:nums[1]){
////				System.out.print(m+" ");
////			}
////			System.out.println();
////		}
////	}
//	
//	//计算某期，概率值(未去重)中，概率值相同次数的集合。如3，3，3.那么可以是3次相同的概率值中有3，也可以是2次相同中有3
//	private List<Integer> getSameList(Map<Integer,Integer> sameChances,int sameNum){
//		List<Integer> result = new ArrayList<Integer>();
//		Set<Integer> sames = sameChances.keySet();
//		for(Integer s:sames){
//			if(sameChances.get(s)>=sameNum)
//				result.add(s);
//		}
//		Collections.sort(result);
//		return result;
//	}
//	
//	//计算概率值和值，次数, 区内概率值组合次数，区内和值的概率值组合次数
//	private Map<Integer,Integer> caculateChancesValue(Map<Integer,int[][]> chances,int type,Map<Integer,Integer> countValue){
//		
//		Map<Integer,Integer> chanceInOneArea= new HashMap<Integer,Integer>();
//		int areaNum  = 0;
//		for(Integer key:chances.keySet()){
//			int[][] chancesValue = chances.get(key);
//			int length = chancesValue.length;
//			if(countValue.containsKey(key)){
//				int exitLength = countValue.get(key);
//				countValue.put(key, length+exitLength);
//			}else{
//				countValue.put(key, length);
//			}
//			totalNum += length;
//			areaNum += length;
//			chanceInOneArea.put(key, length);
//		}
//		
//		this.chancesArea.put(type, areaNum);
//		this.chancesAreaChances.put(type, chanceInOneArea);
//		if(type==41||type==42){
//			if(chancesArea.get(4)==null){
//				chancesArea.put(4, areaNum);
//			}else{
//				int typeNum = chancesArea.get(4);
//				chancesArea.put(4, typeNum+areaNum);
//			}
//			
//			if(chancesAreaChances.get(4)==null){
//				this.chancesAreaChances.put(4, chanceInOneArea);
//			}else{
//				Map<Integer,Integer> firstOne = this.chancesAreaChances.get(4);
//				Map<Integer,Integer> twoAll = new HashMap<Integer,Integer>();
//				Set<Integer> all = new HashSet<Integer>();
//				all.addAll(firstOne.keySet());
//				all.addAll(chanceInOneArea.keySet());
//				for(Integer num:all){
//					Integer a = firstOne.get(num);
//					Integer b = chanceInOneArea.get(num);
//					if(a==null&&b!=null){
//						twoAll.put(num, b);
//					}else if(a!=null&&b==null){
//						twoAll.put(num, a);
//					}else if(a!=null&&b!=null){
//						twoAll.put(num, a+b);
//					}
//				}
//				this.chancesAreaChances.put(4, twoAll);
//			}
//		}else if(type==31||type==32){
//			if(chancesArea.get(3)==null){
//				chancesArea.put(3, areaNum);
//			}else{
//				int typeNum = chancesArea.get(3);
//				chancesArea.put(3, typeNum+areaNum);
//			}
//			
//			if(chancesAreaChances.get(3)==null){
//				this.chancesAreaChances.put(3, chanceInOneArea);
//			}else{
//				Map<Integer,Integer> firstOne = this.chancesAreaChances.get(3);
//				Map<Integer,Integer> twoAll = new HashMap<Integer,Integer>();
//				Set<Integer> all = new HashSet<Integer>();
//				all.addAll(firstOne.keySet());
//				all.addAll(chanceInOneArea.keySet());
//				for(Integer num:all){
//					Integer a = firstOne.get(num);
//					Integer b = chanceInOneArea.get(num);
//					if(a==null&&b!=null){
//						twoAll.put(num, b);
//					}else if(a!=null&&b==null){
//						twoAll.put(num, a);
//					}else if(a!=null&&b!=null){
//						twoAll.put(num, a+b);
//					}
//				}
//				this.chancesAreaChances.put(3, twoAll);
//			}
//		}else if(type==21||type==22||type==23){
//			if(chancesArea.get(2)==null){
//				chancesArea.put(2, areaNum);
//			}else{
//				int typeNum = chancesArea.get(2);
//				chancesArea.put(2, typeNum+areaNum);
//			}
//			
//			if(chancesAreaChances.get(2)==null){
//				this.chancesAreaChances.put(2, chanceInOneArea);
//			}else{
//				Map<Integer,Integer> firstOne = this.chancesAreaChances.get(2);
//				Map<Integer,Integer> twoAll = new HashMap<Integer,Integer>();
//				Set<Integer> all = new HashSet<Integer>();
//				all.addAll(firstOne.keySet());
//				all.addAll(chanceInOneArea.keySet());
//				for(Integer num:all){
//					Integer a = firstOne.get(num);
//					Integer b = chanceInOneArea.get(num);
//					if(a==null&&b!=null){
//						twoAll.put(num, b);
//					}else if(a!=null&&b==null){
//						twoAll.put(num, a);
//					}else if(a!=null&&b!=null){
//						twoAll.put(num, a+b);
//					}
//				}
//				this.chancesAreaChances.put(2, twoAll);
//			}
//		}
//		
//		return countValue;
//	}
//	
//	private void mappingChanceToNum(int[] notFindRed){
//		for(int i=0;i<notFindRed.length;i++){
//			if(chanceMapNum.containsKey(notFindRed[i])){
//				String a = chanceMapNum.get(notFindRed[i]);
//				chanceMapNum.put(notFindRed[i], a+"-"+(i+1));
//			}else{
//				chanceMapNum.put(notFindRed[i], (i+1)+"");
//			}
//		}
//	}
//	
//	private Map<Integer,String[]> caculateChanceMappingNum(Map<Integer,int[][]> chances,List<Integer> finalChancesSumKeys){
//		//这里的mapping格式是：  概率值和值=key。号码组合=value；
//		Map<Integer,String[][]> mapping = new HashMap<Integer,String[][]>();
//		
//			if(finalChancesSumKeys.size()==0){
//				for(Integer key:chances.keySet()){
//					int i=0;
//					String[][] s = new String[chances.get(key).length][];
//					for(int[] chance:chances.get(key)){
//						s[i] = new String[]{chanceMapNum.get(chance[0]),chanceMapNum.get(chance[1]),chanceMapNum.get(chance[2]),chanceMapNum.get(chance[3]),chanceMapNum.get(chance[4]),chanceMapNum.get(chance[5])};
//						i++;
//					}
//					mapping.put(key, s);
//				}
//			}else{
//				for(Integer key:finalChancesSumKeys){
//					
//					int i=0;
//					String[][] s = new String[chances.get(key).length][];
//						for(int[] chance:chances.get(key)){
//							s[i] = new String[]{chanceMapNum.get(chance[0]),chanceMapNum.get(chance[1]),chanceMapNum.get(chance[2]),chanceMapNum.get(chance[3]),chanceMapNum.get(chance[4]),chanceMapNum.get(chance[5])};
//							i++;
//						}
//					mapping.put(key, s);
//				}
//			}
//			
//			//这里取的是号码组合可能的例子：[[13-28, 13-28, 33, 11, 23, 9]]。发现可能出现重复，所以要去除重复。
//			Map<Integer,String[]> finalResult = new HashMap<Integer,String[]>();
//			for(Integer chanceSum:mapping.keySet()){
//				for(String[] cmn:mapping.get(chanceSum)){
//					for(String a:cmn[0].split("-")){
//						for(String b:cmn[1].split("-")){
//							if(!b.equals(a)){
//							for(String c:cmn[2].split("-")){
//								if(!(c.equals(a)||c.equals(b))){
//								for(String d:cmn[3].split("-")){
//									if(!(d.equals(a)||d.equals(b)||d.equals(c))){
//									for(String e:cmn[4].split("-")){
//										if(!(e.equals(a)||e.equals(b)||e.equals(c)||e.equals(d))){
//										for(String f:cmn[5].split("-")){
//											if(!(f.equals(a)||f.equals(b)||f.equals(c)||f.equals(d)||f.equals(e))){
//											List<Integer> tem = new ArrayList<Integer>();
//											tem.add(Integer.valueOf(a));
//											tem.add(Integer.valueOf(b));
//											tem.add(Integer.valueOf(c));
//											tem.add(Integer.valueOf(d));
//											tem.add(Integer.valueOf(e));
//											tem.add(Integer.valueOf(f));
//											Collections.sort(tem);
//											Integer key = tem.get(0)*100000+tem.get(1)*10000+tem.get(2)*1000+tem.get(3)*100+tem.get(4)*10+tem.get(5);
//											int sum = Integer.valueOf(a)+Integer.valueOf(b)+Integer.valueOf(c)+Integer.valueOf(d)+Integer.valueOf(e)+Integer.valueOf(f);
//											//如果这个号码的排序不存在，且号码处于最小和值和最大和值之间则。统计出来。
//											if(!finalResult.containsKey(key)&&(sum>=sums[0]&&sum<=sums[1])){
//												finalResult.put(key, new String[]{a,b,c,d,e,f});
//											}
//										}
//										}
//									}
//									}
//								}
//								}
//							}
//							}
//						}
//						}
//					}
//				}
//			}	
//		return finalResult;	
//	}
//	
//	/**
//	 * 概率值重复法：
//	 * 	通过计算概率值重复出现的规律，反推下次可能出现的6个号码的组合情况.
//	 * 
//	 *  比如3是3次,4是次.那么3这个概率值是出现在2:methodTwo里面的情况多,还是出现在3:methodThree里面的情况多.
//	 */
//	private void countChanceRepeat(){
//		
//	}
//	
//	//读取号码和值信息，计算出和值的区间。历史中最小的和历史中最大的
//	//后面可以再进一步的加算法缩小和值区间，
//	private void caculateSums(String fileUrl){
//		Map<Integer,List<List<Integer>>> sumNumHistory = TxtFileUtil.readTxtFile(fileUrl,TxtFileUtil.SUM_NUM,false);
//		
//		int min = 0;
//		int max = 0;
//		
//		for(Integer key:sumNumHistory.keySet()){
//			
//			int tem = sumNumHistory.get(key).get(0).get(0);
//			if(tem<min){
//				min = tem;
//			}
//			if(tem>max){
//				max = tem;
//			}
//			
//		}
//		sums[0] = min;
//		sums[1] = max;
//	}
//	
//	private int caculateFinalChances(Map<Integer,Integer> chancesArea){
//		int num = 0 ;
//		for(Integer key:chancesArea.keySet()){
//			if(chancesArea.get(key)>num){
//				num = key;
//			}else if(chancesArea.get(key)==num){
//				num += key;
//			}
//		}
//		return num;
//	}
//	
//	private List<Integer> caculateFinalSum(Map<Integer,Map<Integer,Integer>> chancesAreaChances,int areaNum,int length){
//		Map<Integer,Integer> chanceSums = chancesAreaChances.get(areaNum);
//		List<Integer> sums = new ArrayList<Integer>();
//		if(chanceSums!=null){
//			
//			List<Map.Entry<Integer, Integer>> list = new ArrayList<Map.Entry<Integer, Integer>>(chanceSums.entrySet());
//			Collections.sort(list, new Comparator<Map.Entry<Integer, Integer>>(){
//
//				public int compare(Entry<Integer, Integer> o1, Entry<Integer, Integer> o2) {
//					
//					return o2.getValue().compareTo(o1.getValue());
//				}
//				
//			});
//			
//			int i=0,j=0;int temp=0;
//			for(Map.Entry<Integer, Integer> maps:list){
//				if(i==0){
//					sums.add(maps.getKey());
//					temp = maps.getValue();
//				}else{
//					if(j<length){
//						if(temp!=maps.getValue()){
//							temp = maps.getValue();
//							j++;
//						}
//						sums.add(maps.getKey());
//					}else{
//						break;
//					}
//				}
//				
//				i++;
//			}
//			
//		}
//		return sums;
//	}
//	
//	public String queryHistory(HttpServletRequest req,HttpServletResponse res){
//		System.out.println("--Query class,queryHistory method----");
//		
//		return "success";
//	}
	
	/**
	 * //		Map<Integer,List> records = new HashMap<Integer,List>();
//		List l1 = new ArrayList();
//		l1.add(12);
//		l1.add(13);
//		l1.add(14);
//		l1.add(17);
//		l1.add(21);
//		l1.add(25);
//		l1.add(4);
//		records.put(2016018, l1);
//		l1 = new ArrayList();
//		l1.add(5);
//		l1.add(6);
//		l1.add(8);
//		l1.add(20);
//		l1.add(22);
//		l1.add(30);
//		l1.add(5);
//		records.put(2016017, l1);
		//未中号码的概率值，1红2蓝
//		Map<Integer,int[][]> countNotFind = new HashMap<Integer,int[][]>();
//		int[][] i = new int[2][];
//		i[0] = new int[]{1,2,6,7,0,0,4,0,15,3,12,4,11,2,6,5,13,3,7,0,4,0,14,1,1,1,3,11,6,0,4,5,9};
//		//6个0，4个1，2个2，3个3，4个4，2个5，3个6，2和7，1个9，2个11，1个12，1个13，1个14，1个15
//		i[1] = new int[]{4,9,18,8,0,7,2,52,22,41,20,6,16,12,5,1};
//		countNotFind.put(2016017, i);
//		
//		//中奖号码和概率值,1红2红概3蓝4蓝概
//		Map<Integer,int[][]> winNum = new HashMap<Integer,int[][]>();
//		int[][] j = new int[4][];
//		j[0]=new int[]{12,13,14,17,21,25};
//		j[1]=new int[]{4,11,2,13,4,1};
//		j[2]=new int[]{4};
//		j[3]=new int[]{8};
//		winNum.put(2016018, j);
//		
//		//根据输入的中奖数，自动计算未中奖号码的概率值
//		Map<Integer,List<List<Integer>>> winNumHistory = new HashMap<Integer,List<List<Integer>>>();
//		List<List<Integer>> numHistory = new ArrayList<List<Integer>>();
//		List<Integer> rednumHistory = new ArrayList<Integer>();
//		rednumHistory.add(7);
//		rednumHistory.add(8);
//		rednumHistory.add(15);
//		rednumHistory.add(19);
//		rednumHistory.add(20);
//		rednumHistory.add(24);
//		List<Integer> bluenumHistory = new ArrayList<Integer>();
//		bluenumHistory.add(13);
//		numHistory.add(rednumHistory);
//		numHistory.add(bluenumHistory);
//		winNumHistory.put(2015145, numHistory);
//		
//		numHistory = new ArrayList<List<Integer>>();
//		rednumHistory = new ArrayList<Integer>();
//		rednumHistory.add(16);
//		rednumHistory.add(17);
//		rednumHistory.add(21);
//		rednumHistory.add(28);
//		rednumHistory.add(30);
//		rednumHistory.add(32);
//		bluenumHistory = new ArrayList<Integer>();
//		bluenumHistory.add(15);
//		numHistory.add(rednumHistory);
//		numHistory.add(bluenumHistory);
//		winNumHistory.put(2015146, numHistory);
//		i = new int[2][];
//		i[0] = new int[]{1,7,4,1,9,5,0,0,22,6,13,9,2,8,0,7,3,6,0,0,2,3,8,0,3,10,4,1,5,5,5,1,13};
//		//6个0，4个1，2个2，3个3，4个4，2个5，3个6，2和7，1个9，2个11，1个12，1个13，1个14，1个15
//		i[1] = new int[]{9,4,14,2,44,17,10,26,6,15,3,5,0,42,12,1};
//		countNotFind.put(2015145, i);
	 */

//	private void caculateNotFindChance(Map<Integer,List<List<Integer>>> winNumHistory,Map<Integer,int[][]> countNotFind){
//	List<Integer> needCaculate = new ArrayList<Integer>();
//	for(Integer i:winNumHistory.keySet()){
//		needCaculate.add(i);
//	}
//	
//	List<Integer> notKey = new ArrayList<Integer>();
//	for(Integer j:countNotFind.keySet()){
//		notKey.add(j);
//	}
//	
//	needCaculate.removeAll(notKey);
//	Collections.sort(needCaculate);
//	
//	for(Integer key:needCaculate){
//		List<List<Integer>> winNum = winNumHistory.get(key);
//		List<Integer> winRed = winNum.get(0);
//		List<Integer> winBlue = winNum.get(1);
//		int[][] notNumHis = countNotFind.get(key-1);
//		int[] redNumChance = new int[33];
//		int[] blueNumChance = new int[16];
//		if(notNumHis!=null&&winNum!=null){
//			for(int i=0;i<notNumHis[0].length;i++){
//				if(winRed.contains(i+1)){
//					redNumChance[i]=0;
//				}else{
//					redNumChance[i]=notNumHis[0][i]+1;
//				}
//			}
//			
//			for(int j=0;j<notNumHis[1].length;j++){
//				if(winBlue.contains(j+1)){
//					blueNumChance[j]=0;
//				}else{
//					blueNumChance[j]=notNumHis[1][j]+1;
//				}
//			}
//			countNotFind.put(key, new int[][]{redNumChance,blueNumChance});
//		}
//		
//		
//	}
//}
}
