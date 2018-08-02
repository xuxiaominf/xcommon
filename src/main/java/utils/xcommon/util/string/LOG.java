package utils.xcommon.util.string;

/**
 * 为对象提供格式化的输出
 * 基础类型：{变量名:变量类型:变量值}
 * 复杂类型
 * @author xuxiaoming
 * @version $Id: LOG.java, v 0.1 2016年8月1日 上午10:04:23 xuxiaoming Exp $
 */
public final class LOG {
    /**
     * 
     * @param key
     * @param intVal
     * @return
     */
    public static String str(String key,Object value){
        return String.format("{%s:%s:%s}", key,getType(value),value==null?"":value.toString());
    }
    
    private static String getType(Object obj){
        return obj==null?"":obj.getClass().getSimpleName();
    }
}
