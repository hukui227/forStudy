/**
 * 
 */
package struts.servlet;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import common.util.CollectionsUtil;
/**
 * 
 * 根据遗漏：
 * 当前遗漏：降序
 * 平均遗漏：固定值。红色33/6=5.5 蓝色16/1=16
 * 最大遗漏：升序。
 * 
 * 最大遗漏-当前遗漏：升序。
 * 
 * @author bestv
 *
 */
public class YiLou {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//26,26,32,45,29,28,21,34,28,40,30,26,28,33,32,28,26,23,27,42,33,42,24,25,40,27,26,25,29,32,31,23,35;67,81,77,112,104,89,90,71,94,60,64,84,84,70,83,68
		//1,2,6,7,0,0,4,0,15,3,12,4,11,2,6,5,13,3,7,0,4,0,14,1,1,1,3,11,6,0,4,5,9;4,9,18,8,0,7,2,52,22,41,20,6,16,12,5,1
		int key = 2016028;
		int[][] current = new int[][]{{8,4,5,2,4,0,4,0,2,5,1,0,1,4,1,9,1,4,1,11,0,6,6,3,0,7,14,2,0,2,1,16,2},{8,13,22,3,0,11,6,56,26,1,24,10,20,16,9,5}};
		int[][] max = new int[][]{{26,26,32,45,29,28,21,34,28,40,30,26,28,33,32,28,26,23,27,42,33,42,24,25,40,27,26,25,29,32,31,23,35},{67,81,77,112,104,89,90,71,94,60,64,84,84,70,83,68}};
		YiLou y = new YiLou();
		List<Map.Entry<Integer,Integer>> l1 = y.sortArray(current[0],false);
		List<Map.Entry<Integer,Integer>> l2 = y.sortArray(y.subtracArray(max[0],current[0]),true);
		System.out.print(key+":current:");
		y.printList(l1);
		System.out.print(key+":max-current:");
		y.printList(l2);
	}

	private List<Map.Entry<Integer,Integer>> sortArray(int[] arr,boolean isAsc){
		Map<Integer,Integer> m = new HashMap<Integer,Integer>();
		for(int i=0;i<arr.length;i++){
			m.put(i+1, arr[i]);
		}
		
		return CollectionsUtil.sortMapIntegerValue(m,isAsc);
	}
	
	private void printList(List<Map.Entry<Integer,Integer>> l){
		int temp = 0;
		boolean first = true;
		for(Map.Entry<Integer,Integer> m:l){
			if(!first){
				if(temp==m.getValue()){
					System.out.print("-");
				}else{
					System.out.print(";");
				}
			}
			System.out.print(m.getKey());
			first =false;
			temp = m.getValue();
		}
		System.out.println();
	}
	
	private int[] subtracArray(int[] a,int[] b){
		int[] result = new int[a.length];
		for(int i=0;i<a.length;i++){
			if(i<b.length){
				result[i] = a[i]-b[i];
			}else{
				result[i] = a[i];
			}
		}
		return result;
	}
	
}
