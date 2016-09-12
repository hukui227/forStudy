package common.util.file;

import java.util.Collection;
import java.util.List;

import bean.common.DoubleColorBall;

public class TxtFileUtil extends FileUtil{

	
	@Override
	public void putList(String temStr,Collection list) {
		putDoubleColorBallInList(temStr,list);
		System.out.println("Txt Put List");
	}

	@Override
	public String writeFile(String fileUrl) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * txt中放置格式：期号:红色号码,红色号码,红色号码,红色号码,红色号码,红色号码;蓝色号码
	 */
	private void putDoubleColorBallInList(String temStr,Collection list){
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

}
