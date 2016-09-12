package common.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;

public class MathUtil {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	/**
	 * 除法运算，转百分比. 取四舍五入
	 */
	public static double divide(int a,int b){
		int scale = 3;
		BigDecimal ba = new BigDecimal(a);
		BigDecimal bb = new BigDecimal(b);

		return ba.divide(bb,scale, BigDecimal.ROUND_HALF_UP).doubleValue()*100;
	}
	
	/**
	 * 将传入的double值格式化输出，keepNum表示保留小数点后几位
	 */
	public static String format(double value,int keepNum){
		DecimalFormat df1 = new DecimalFormat("0.0");
		if(keepNum==2){
			df1 = new DecimalFormat("0.00");
		}
		return df1.format(value);
	}
	
}
