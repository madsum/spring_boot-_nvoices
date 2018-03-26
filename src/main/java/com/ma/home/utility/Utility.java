package com.ma.home.utility;

public class Utility {

	/**
	 * We get  Format="CCYYMMDD" from xml. We have to convert it as yyyy-MM-dd. So that we call Java api to convert actual date. 
	 * @param formatString
	 * @return
	 */
	public static String getDateFormatString(String formatString){
		char[] strArr = formatString.toCharArray();
		StringBuilder str = new StringBuilder();

		for(int i=0; i<strArr.length; i++)
		{
			if (strArr[i] == 'C' || strArr[i] == 'Y')
			{
				str.append('y');
				continue;
			}

			if (strArr[i] == 'M')
			{
				if(i%4 == 0){
					str.append("-M");
				}
				else{
					str.append('M');
				}
			}
			else if(strArr[i] == 'D')
			{
				if(i%2 == 0 ){
					str.append("-d");	
				}
				else{
					str.append('d');
				}
			}
			else
			{
				str.append(Character.toLowerCase(strArr[i]));
			}
		}
		return str.toString();
	}

	/**
	 * We get date as 20131223. We have to convert as 2013-12-23. So it can used as actual readable date.
	 * 
	 * @param dateStr
	 * @return
	 */
	public static String getDateValue(String dateStr){
		StringBuilder str = new StringBuilder();

		str.append(dateStr.substring(0, 4));
		str.append("-");
		str.append(dateStr.substring(4, 6));
		str.append("-");
		str.append(dateStr.substring(6, 8));

		return str.toString();
	}   	
}
