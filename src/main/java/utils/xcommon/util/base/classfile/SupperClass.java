package utils.xcommon.util.base.classfile;

import javax.xml.bind.annotation.XmlInlineBinaryData;

@XmlInlineBinaryData
public class SupperClass<R> extends BaseClass implements Comparable<Integer>{
	private final int i1=1;
	private static int ia1[]=new int[]{1,2};
	private RefClass refClass;
	
	public synchronized int getI1(Integer ... integers) throws Exception {
		int aaa=34;
		int bbb=340;
		int ccc=250;
		int ddd=489;
		int eee = 987;
		return eee-9;
	}
	public RefClass getRef(){
		return refClass;
	}
	public int compareTo(Integer o) {
		return 0;
	}
}
