package utils.xcommon.util.base.classfile;

import javax.xml.bind.annotation.XmlInlineBinaryData;

@XmlInlineBinaryData
public class SupperClass<R> extends BaseClass implements Comparable<Integer>{
	private int i1=1;
	private static int ia1[]=new int[]{1,2};
	private RefClass refClass;
	
	public synchronized int getI1(){
		return i1;
	}
	public RefClass getRef(){
		return refClass;
	}
	public int compareTo(Integer o) {
		return 0;
	}
}
