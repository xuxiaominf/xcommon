package utils.xcommon.util.proxy;

import net.sf.cglib.proxy.Enhancer;

public class Test {
	public static void main(String[] args) {
		Enhancer enhancer = new Enhancer();
		enhancer.setSuperclass(HelloWordServiceImpl.class);
		enhancer.setCallback(new HelloWordInterceptor());
		
		HelloWordServiceImpl helloWord = (HelloWordServiceImpl) enhancer.create();
		helloWord.print();
	}
}
