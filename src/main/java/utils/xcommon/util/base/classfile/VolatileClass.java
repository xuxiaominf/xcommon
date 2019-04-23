package utils.xcommon.util.base.classfile;

public class VolatileClass {
	private volatile int i2 = 1;

	public int getI2() {
		int j = 1;
		int m = i2 + 3;
		int k = m + 5;
		i2 = k + 6;
		int a = 007;
		return i2;
	}

	public static void main(String[] args) throws InterruptedException {
		String s3 = "1" + "1";
		System.out.println(s3.hashCode());
		String s4 = "11";
		System.out.println(s3.hashCode());
		s3.intern();
		System.out.println(s3==s4);
	}
}
