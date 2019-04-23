package utils.xcommon.util.base.optional;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.function.Supplier;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;

import com.alibaba.fastjson.JSON;

public class Main {
	public static void main(String[] args) {
		User user0 = null;
		User user1 = null;
		User user2 = null;
		User user3 = null;
		String name = "xumingyang";
		Integer age = 1;
		//Optional.of(user) 仍让会报空
		//orElse 为空的时候执行
//		if(user0==null){
//			user0 = new User("xuxiaoming", 32);
//		}
		
		user0 = Optional.ofNullable(user0).orElse(new User("xuxiaoming", 32));
		
		Optional.ofNullable(user0).filter((x)->x.getName().equals("xuxiaoming2")).ifPresent((x)->{
			System.out.println("========================");
		});
		
		String clientType = null;
		String client = "Android";
		String android = "Android";
		clientType = Optional.ofNullable(client).filter((c)->c.indexOf("Android")>=0).map((c)-> {System.out.println("ddddddddddfffffffff");return "Android";}).orElse("ios");
		System.out.println("=-==="+clientType);
		
		
		user2 = Optional.ofNullable(user0).filter((x)->x.getName().equals("xuxiaoming")).filter((x)->age.intValue()==2).orElse(new User("xumingyang",1));
		System.out.println(Optional.ofNullable(user2).map((x)->x.getAddAge()).get());
		System.out.println(Optional.ofNullable(user2).flatMap((x)->x.getOptionalAddAge()).get());
		System.out.println(JSON.toJSONString(user2));
		
		System.out.println(getTonCount("10吨"));
		
		System.out.println(camelName("status"));
	}
	
    public static BigDecimal getTonCount(String src){
        Matcher matcher = Pattern.compile("^(\\d+\\.*\\d*)\\S*$").matcher(src);
        if(matcher.find()){
            BigDecimal ton = new BigDecimal(matcher.group(1));
            if(StringUtils.isNotBlank(src) && src.toLowerCase().contains("kg")){
                ton = ton.divide(new BigDecimal(1000), 2 , BigDecimal.ROUND_HALF_UP);
            }
            return ton;
        }
        return BigDecimal.ZERO;
    }
    
    public static String camelName(String name) {
        StringBuilder result = new StringBuilder();
        // 快速检查
        if (name == null || name.isEmpty()) {
            // 没必要转换
            return "";
        } else if (!name.contains("_")) {
            // 不含下划线，仅将首字母小写
            return name.substring(0, 1).toLowerCase() + name.substring(1);
        }
        // 用下划线将原始字符串分割
        String camels[] = name.split("_");
        for (String camel :  camels) {
            // 跳过原始字符串中开头、结尾的下换线或双重下划线
            if (camel.isEmpty()) {
                continue;
            }
            // 处理真正的驼峰片段
            if (result.length() == 0) {
                // 第一个驼峰片段，全部字母都小写
                result.append(camel.toLowerCase());
            } else {
                // 其他的驼峰片段，首字母大写
                result.append(camel.substring(0, 1).toUpperCase());
                result.append(camel.substring(1).toLowerCase());
            }
        }
        return result.toString();
    }

}
