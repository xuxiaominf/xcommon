package utils.xcommon.util.proxy;

import java.lang.reflect.Method;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

public class HelloWordInterceptor implements MethodInterceptor{
	public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
		System.out.println("Before mehtod:"+ method.getName());
		Object object = proxy.invokeSuper(obj, args);
		System.out.println("After mehtod:"+ method.getName()+", Object:"+object);
		return object;
	}
}
