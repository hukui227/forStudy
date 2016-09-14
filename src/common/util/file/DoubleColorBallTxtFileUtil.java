package common.util.file;

import java.util.Collection;

import bean.common.DoubleColorBall;

public class DoubleColorBallTxtFileUtil extends TxtFileUtil {

	
	/**
	 * txt中放置格式：期号:红色号码,红色号码,红色号码,红色号码,红色号码,红色号码;蓝色号码
	 */
	@Override
	public void putDoubleColorBallInList(String temStr, Collection list) {
		String key = temStr.split(":")[0];
		String nums = temStr.split(":")[1];
		String[] reds = nums.split(";")[0].split(",");
		String blue = nums.split(";")[1];
		
		DoubleColorBall dcb = new DoubleColorBall();
		dcb.setIssue(key);
		dcb.setRedOne(reds[0]);
		dcb.setRedTwo(reds[1]);
		dcb.setRedThree(reds[2]);
		dcb.setRedFour(reds[3]);
		dcb.setRedFive(reds[4]);
		dcb.setRedSix(reds[5]);
		dcb.setBlue(blue);
		list.add(dcb);
		
	}

	@Override
	public String castToContent(Object content) {
		DoubleColorBall db = (DoubleColorBall)content;
		String tem = "";
		tem += db.getIssue()+":";
		tem += db.getRedOne()+",";
		tem += db.getRedTwo()+",";
		tem += db.getRedThree()+",";
		tem += db.getRedFour()+",";
		tem += db.getRedFive()+",";
		tem += db.getRedSix()+";";
		tem += db.getBlue();
		return tem;
	}
	
}
