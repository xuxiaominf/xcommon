package utils.xcommon.util.fastjson;

import com.alibaba.fastjson.JSONObject;

public class TestMain {
	public static void main(String[] args) {
		String str = "{\"name\":\"xxm\",\"age\":\"11\",\"brithDate\":\"1987-06-13\",\"beans\":[{\"country\":\"china\",\"address\":\"E3\"}]}";
		TestBean bean = JSONObject.parseObject(str, TestBean.class);
		
		
		
		
		System.out.println(bean);
	}
}
