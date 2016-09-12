package common.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class CollectionsUtil {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public static List<Map.Entry<Integer,Integer>> sortMapIntegerValue(Map<Integer,Integer> m,boolean isAsc){
		List<Map.Entry<Integer,Integer>> mapping = new ArrayList<Map.Entry<Integer,Integer>>(m.entrySet());
		if(isAsc){
			Collections.sort(mapping, new Comparator<Map.Entry<Integer, Integer>>(){
				
				public int compare(Entry<Integer, Integer> o1,Entry<Integer, Integer> o2) {
						
					return o1.getValue().compareTo(o2.getValue());
				}});
		}else{
			Collections.sort(mapping, new Comparator<Map.Entry<Integer, Integer>>(){
				
				public int compare(Entry<Integer, Integer> o1,Entry<Integer, Integer> o2) {
						
					int flag = o1.getValue().compareTo(o2.getValue());
					if(flag>0){
						return -1;
					}else{
						return 1;
					}
				}});
		}
		
		
		return mapping;
	}
	
	public static List<Map.Entry<Integer,Integer>> sortMapIntegerKey(Map<Integer,Integer> m,boolean isAsc){
		List<Map.Entry<Integer,Integer>> mapping = new ArrayList<Map.Entry<Integer,Integer>>(m.entrySet());
		if(isAsc){
			Collections.sort(mapping, new Comparator<Map.Entry<Integer, Integer>>(){
				
				public int compare(Entry<Integer, Integer> o1,Entry<Integer, Integer> o2) {
						
					return o1.getKey().compareTo(o2.getKey());
				}});
		}else{
			Collections.sort(mapping, new Comparator<Map.Entry<Integer, Integer>>(){
				
				public int compare(Entry<Integer, Integer> o1,Entry<Integer, Integer> o2) {
						
					int flag = o1.getKey().compareTo(o2.getKey());
					if(flag>0){
						return -1;
					}else{
						return 1;
					}
				}});
		}
		
		
		return mapping;
	}
	
	public static List<Map.Entry<Integer,List<List<Integer>>>> sortMapList(Map<Integer,List<List<Integer>>> m,boolean isAsc){
		List<Map.Entry<Integer,List<List<Integer>>>> mapping = new ArrayList<Map.Entry<Integer,List<List<Integer>>>>(m.entrySet());
		if(isAsc){
			Collections.sort(mapping, new Comparator<Map.Entry<Integer, List<List<Integer>>>>(){
				
				public int compare(Entry<Integer, List<List<Integer>>> o1,Entry<Integer, List<List<Integer>>> o2) {
						
					return o1.getKey().compareTo(o2.getKey());
				}});
		}else{
			Collections.sort(mapping, new Comparator<Map.Entry<Integer, List<List<Integer>>>>(){
				
				public int compare(Entry<Integer, List<List<Integer>>> o1,Entry<Integer, List<List<Integer>>> o2) {
						
					int flag = o1.getKey().compareTo(o2.getKey());
					if(flag>0){
						return -1;
					}else{
						return 1;
					}
				}});
		}
		
		
		return mapping;
	}
}
