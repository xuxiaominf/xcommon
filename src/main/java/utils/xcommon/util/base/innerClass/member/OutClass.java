package utils.xcommon.util.base.innerClass.member;

/**
 * https://www.cnblogs.com/dolphin0520/p/3811445.html
 * @author xiaoming3.xu
 *
 */
public class OutClass {
	private Integer outClassInt = 1;
	
	class InnerClass{
		public Integer getOutInt(){
			return outClassInt;
		}
	}
}
