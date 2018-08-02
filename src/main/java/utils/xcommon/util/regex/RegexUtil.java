package utils.xcommon.util.regex;

import java.util.regex.Pattern;

import utils.xcommon.util.string.StringUtils;

/**
 * 
 * @author xuxiaoming
 * @version $Id: RegexUtil.java, v 0.1 2016年8月1日 下午2:06:12 xuxiaoming Exp $
 */
public class RegexUtil {
    public static boolean isCellPhone(String input)
    {
        return StringUtils.isNotEmpty(input) && CELL_PHONE_PATTERN.matcher(input).matches();
    }

    public static boolean isTelePhone(String input)
    {
        return StringUtils.isNotEmpty(input) && TELE_PHONE_PATTERN.matcher(input).matches();
    }

    public static boolean isPhoneNum(String input)
    {
        if(StringUtils.isNotEmpty(input))
            return isCellPhone(input) || isTelePhone(input);
        else
            return false;
    }

    public static boolean isIDCardNo(String input)
    {
        return StringUtils.isNotEmpty(input) && ID_CARD_NO_PATTERN.matcher(input).matches();
    }

    public static boolean isBankAccount(String input)
    {
        return StringUtils.isNotEmpty(input) && BANK_ACCOUNT_PATTERN.matcher(input).matches();
    }

    public static boolean isQQ(String input)
    {
        return StringUtils.isNotEmpty(input) && QQ_PATTERN.matcher(input).matches();
    }

    public static String fetchIpFromText(String input)
    {
        return IP_PATTERN.matcher(input).group();
    }

    public static final Pattern CELL_PHONE_PATTERN = Pattern.compile("^1\\d{10}$");
    public static final Pattern TELE_PHONE_PATTERN = Pattern.compile("(^((\\d{7,8})|(\\d{4}|\\d{3})-(\\d{7,8})|(\\d{4}|\\d{3})-(\\d{7,8})-(\\d{4}|\\d{3}|\\d{2}|\\d{1})|(\\d{7,8})-(\\d{4}|\\d{3}|\\d{2}|\\d{1}))$)");
    public static final Pattern ID_CARD_NO_PATTERN = Pattern.compile("\\d{18}|\\d{15}");
    public static final Pattern BANK_ACCOUNT_PATTERN = Pattern.compile("\\d{19}");
    public static final Pattern QQ_PATTERN = Pattern.compile("^\\d{5,10}$");
    public static final Pattern IP_PATTERN = Pattern.compile("^(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|[1-9])\\.(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)\\.(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)\\.(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)$");

}
