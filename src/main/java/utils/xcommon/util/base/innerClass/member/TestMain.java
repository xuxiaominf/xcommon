package utils.xcommon.util.base.innerClass.member;

import utils.xcommon.util.base.classfile.EnumClass;
import utils.xcommon.util.base.innerClass.member.OutClass.InnerClass;

public class TestMain {
	public static void main(String [] args){
		OutClass out = new OutClass();
		InnerClass in = out.new InnerClass();
		System.out.println(in.getOutInt());
	}
}
