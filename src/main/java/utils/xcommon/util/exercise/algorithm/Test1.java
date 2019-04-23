package utils.xcommon.util.exercise.algorithm;

/**
 * 
 * 有一个字符串它的构成是词+空格的组合，如“北京 杭州 杭州 北京”， 要求输入一个匹配模式（简单的以字符来写）， 比如 aabb, 来判断该字符串是否符合该模式， 举个例子：

	pattern = "abba", str="北京 杭州 杭州 北京" 返回 ture
	pattern = "aabb", str="北京 杭州 杭州 北京" 返回 false
	pattern = "baab", str="北京 杭州 杭州 北京" 返回 ture
 * @author xiaoming3.xu
 */
public class Test1 {
	public static boolean solution(String pattern, String str){
		if(pattern == null || str == null)
			return false;
		if(pattern.equals(str)){
			return true;
		}
		String [] strArr = str.split(" ");
		if(pattern.length()!=strArr.length){
			return false;
		}
		
		for(int i=0;i<pattern.length();i++){
			char test = pattern.charAt(i);
			String testStr = strArr[i];
			for(int j=i+1;j<pattern.length();j++){
				char compareChar = pattern.charAt(j);
				String compareStr = strArr[j];
				if(test == compareChar && !compareStr.equals(testStr)){
					return false;
				}
			}
		}
		return true;
	}

	public static void main(String[] args) {
		System.out.println(Test1.solution("baab", "北京 杭州 杭州 北京"));
	}
}
