package common.util.file;

import java.util.Collection;
import java.util.List;

import bean.common.DoubleColorBall;

public abstract class TxtFileUtil extends FileUtil{

	
	@Override
	public void putList(String temStr,Collection list) {
		putDoubleColorBallInList(temStr,list);
		System.out.println("Txt Put List");
	}

	/**
	 * txt中放置格式：期号:红色号码,红色号码,红色号码,红色号码,红色号码,红色号码;蓝色号码
	 */
	public abstract void putDoubleColorBallInList(String temStr,Collection list);


}
