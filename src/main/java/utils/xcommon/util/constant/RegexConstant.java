package utils.xcommon.util.constant;

/**
 * 
 * @author xuxiaoming
 * @version $Id: RegexConstant.java, v 0.1 2016年8月1日 下午2:08:14 xuxiaoming Exp $
 */
public class RegexConstant {
    public static final String URL = "((([a-zA-z0-9]|-){1,}\\.){1,}[a-zA-z0-9]{1,}-*)|(http://(([a-zA-z0-9]|-){1,}\\.){1,}[a-zA-z0-9]{1,}-*)";
    public static final String SCRIPT = "<[\\s]*?script[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?script[\\s]*?>";
    public static final String STYLE = "<[\\s]*?style[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?style[\\s]*?>";
    public static final String HTML = "<[^>]+>";
    public static final String TELE_PHONE = "(^((\\d{7,8})|(\\d{4}|\\d{3})-(\\d{7,8})|(\\d{4}|\\d{3})-(\\d{7,8})-(\\d{4}|\\d{3}|\\d{2}|\\d{1})|(\\d{7,8})-(\\d{4}|\\d{3}|\\d{2}|\\d{1}))$)";
    public static final String CELL_PHONE = "^1\\d{10}$";
    public static final String ID_CARD_NO = "\\d{18}|\\d{15}";
    public static final String BANK_ACCOUNT = "\\d{19}";
    public static final String QQ = "^\\d{5,10}$";
    public static final String EMAIL = "^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$";
    public static final String IP = "^(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|[1-9])\\.(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)\\.(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)\\.(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)$";
    public static final String STATUS_CODE = "";
    public static final String REQUEST_METHOD = "GET|POST";
}
