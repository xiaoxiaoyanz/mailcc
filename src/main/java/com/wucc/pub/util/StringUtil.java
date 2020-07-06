package com.wucc.pub.util;

import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.*;
import java.util.UUID;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtil implements Serializable {
    private static final long serialVersionUID = 1L;

    public static String nullToEmpty(Object value) {
        if (null == value) {
            return "";
        }
        String tempString = String.valueOf(value);
        if ("null".equalsIgnoreCase(tempString) || "".equals(tempString)) {
            return "";
        }
        return tempString;
    }

    public static String nvl(Object value, String def) {
        if (null == value) {
            return def;
        }
        String tempString = String.valueOf(value);
        if ("null".equalsIgnoreCase(tempString) || "".equals(tempString)) {
            return def;
        }
        return tempString;
    }

    public static <T> T getGenericsValue(Object value, T clazz) {
        if (isEmpty(value)) {
            return null;
        }
        Object newInstance = null;
        Constructor<? extends Object> constructor;
        try {
            constructor = clazz.getClass().getConstructor(String.class);
            if (constructor != null) {
                newInstance = constructor.newInstance(value.toString());
            }
        } catch (NoSuchMethodException e) {
        } catch (InstantiationException e) {
        } catch (IllegalAccessException e) {
        } catch (IllegalArgumentException e) {
        } catch (InvocationTargetException e) {
        }
        return (T) newInstance;
    }

    /**
     * @param val
     * @return
     * @describe 判断一个字符是否是“是”
     */
    public static boolean isTrue(String val) {
        if (isEmpty(val)) {
            return false;
        }
        return "y".equalsIgnoreCase(val) || "true".equalsIgnoreCase(val) || "1".equals(val);
    }

    /**
     * @param value
     * @return
     * @describe 字符串转换成longl类型，默认为0
     */
    public static long toLong(Object value) {
        return toLong(value, 0);
    }

    /**
     * @param value      要转换的值
     * @param defaultVal 默认的值，报错的时候返回默认值
     * @return
     * @describe 字符串转换成long
     */
    public static long toLong(Object value, long defaultVal) {
        try {
            return Long.parseLong(nullToEmpty(value));
        } catch (Exception e) {
            return defaultVal;
        }
    }

    /**
     * @param value
     * @return
     * @describe 字符串转换成short类型，默认为0
     */
    public static short toShort(Object value) {
        return toShort(value, 0);
    }

    /**
     * @param value      要转换的值
     * @param defaultVal 默认的值，报错的时候返回默认值
     * @return
     * @describe 字符串转换成short
     */
    public static short toShort(Object value, int defaultVal) {
        try {
            return Short.parseShort(nullToEmpty(value));
        } catch (Exception e) {
            return (short) defaultVal;
        }
    }

    /**
     * @param value
     * @return
     * @describe 字符串转换成Integer类型，默认为0
     */
    public static Integer toInteger(Object value) {
        return toInteger(value, new Integer("0"));
    }

    /**
     * @param value      要转换的值
     * @param defaultVal 默认的值，报错的时候返回默认值
     * @return
     * @describe 字符串转换成Integer
     */
    public static Integer toInteger(Object value, Integer defaultVal) {
        try {
            return Integer.valueOf(nullToEmpty(value));
        } catch (Exception e) {
            return defaultVal;
        }
    }

    public static boolean isEmpty(Object value) {
        String valueString = nullToEmpty(value);
        if (null == valueString) {
            return true;
        }
        if ("null".equalsIgnoreCase(valueString) || "".equals(valueString)) {
            return true;
        }
        return false;
    }

    public static boolean isNotEmpty(String value) {
        return !isEmpty(value);
    }

    public static boolean isNotEmpty(Object value) {
        return !isEmpty(value);
    }

    public static boolean isNotEmpty(Map<?, ?> map) {
        return !(map == null || map.isEmpty());
    }

    public static boolean isNotEmpty(List<?> list) {
        return !(list == null || list.isEmpty());
    }

    /**
     * @return
     * @describe 获得一个随机的编码
     */
    public static String getUUID() {
        String uuid = UUID.randomUUID().toString();
        uuid = uuid.replaceAll("-", "");
        return uuid;//StringUtil.nullToEmpty(UUID.randomUUID().toString());
    }

    public static String formateNum(String valueString) {
        NumberFormat format = new DecimalFormat("#,##0.00");
        BigDecimal bigDecimal = toBigDecimal(valueString);
        return format.format(bigDecimal.doubleValue());
    }

    /**
     * 格式化金额
     *
     * @param valueString
     * @return 格式化后的金额（不包含千分位逗号显示，例如：1234.00）
     */
    public static BigDecimal formateNumToDecimal(String valueString) {
        BigDecimal bigDecimal = toBigDecimal(valueString);
        return bigDecimal;
    }

    public static BigDecimal toBigDecimal(String value) {
        BigDecimal decimal = new BigDecimal(Double.valueOf("0").doubleValue());
        decimal = decimal.setScale(2, 5);
        if (null == value) {
            return decimal;
        }
        if ("null".equalsIgnoreCase(value) || "".equals(value.trim())) {
            return decimal;
        }
        value = value.replaceAll(",", "");
        NumberFormat format = new DecimalFormat("0.00");
        BigDecimal bigDecimal = new BigDecimal(Double.valueOf(value).doubleValue());
        BigDecimal res = new BigDecimal(format.format(bigDecimal));
        return res;
    }

    /**
     * @param timestamp
     * @return
     * @describe 日期转换成字符串
     */
    public static String dateToString(Timestamp timestamp) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//定义格式，不显示毫秒
        return df.format(timestamp);
    }

    /**
     * @describe string字符串转数组
     * @parms
     */
    public static String[] stringToArray(String string) {
        if (isEmpty(string))
            return new String[0];
        String[] result = new String[string.length()];
        for (int i = 0; i < string.length(); i++) {
            result[i] = string.substring(i, i + 1);
        }
        return result;
    }

    /**
     * @param originStr
     * @return
     * @Title: getUpperHeadStrNo_
     * @Description: 将字段转为驼峰式大写字母开头字符串，例如：    AREM_HOUSE_COLLECT -->  AremHouseCollect
     */
    public static String getUpperHeadStrNo_(String originStr) {
        String[] chars = StringUtil.stringToArray(originStr);
        StringBuffer target = new StringBuffer();
        for (int j = 0; j < chars.length; j++) {
            if (j == 0) {
                target.append(chars[j].toUpperCase());
            } else {
                if (chars[j - 1].equals("_")) {
                    target.append(chars[j].toUpperCase());
                } else if (!chars[j].equals("_"))
                    target.append(chars[j].toLowerCase());
            }
        }
        return target.toString();
    }

    /**
     * @param originStr
     * @return
     * @Title: getLowerHeadStrNo_
     * @Description: 将字段转为驼峰式小写字母开头字符串，例如：AREM_HOUSE_COLLECT -->  aremHouseCollect
     */
    public static String getLowerHeadStrNo_(String originStr) {
        String[] chars = StringUtil.stringToArray(originStr);
        StringBuffer target = new StringBuffer();
        for (int j = 0; j < chars.length; j++) {
            if (j == 0) {
                target.append(chars[j].toLowerCase());
            } else {
                if (chars[j - 1].equals("_")) {
                    target.append(chars[j].toUpperCase());
                } else if (!chars[j].equals("_"))
                    target.append(chars[j].toLowerCase());
            }
        }
        return target.toString();
    }

    /**
     * @param param
     * @return
     * @describe aremHouseCollect  --> AREM_HOUSE_COLLECT
     */
    public static String camelToSnake(String param) {
        char UNDERLINE = '_';
        if (param == null || "".equals(param.trim())) {
            return "";
        }
        int len = param.length();
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++) {
            char c = param.charAt(i);
            if (Character.isUpperCase(c)) {
                sb.append(UNDERLINE);
            }
            sb.append(Character.toUpperCase(c));
        }
        return sb.toString();
    }

    /**
     * @param date java.util.date
     * @return
     * @describe Date类型转字符串
     */
    public static String dateToString(Date date) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//定义格式，不显示毫秒
        return df.format(date);
    }

    /**
     * @param date java.util.date
     * @return
     * @author jiangyx
     * @describe 将长时间格式时间转换为字符串 yyyy-MM-dd HH:mm:ss
     */
    public static String dateToStrLong(Date date) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return df.format(date);
    }

    /**
     * <p>clean illegal char</p>
     *
     * @param str cleaned object
     * @return
     */
    public static String celanIllegalChar(String str) {
        if (str != null) {
            Pattern pattern = Pattern.compile("\\t|\r|\n");
            Matcher m = pattern.matcher(str);
            return m.replaceAll("");
        }
        return str;
    }

    /**
     * @param
     * @describe 转驼峰
     */
    public static String strTransHump(String str) {
        String[] split = str.split("_");
        String resStr = split[0].toLowerCase();
        for (int i = 1; i < split.length; i++) {
            resStr += split[i].substring(0, 1).toUpperCase() + split[i].substring(1, split[i].length()).toLowerCase();
        }
        return resStr;
    }

    /**
     * @param str
     * @return
     * @Title: isNumber
     * @Description: 判断一个字符串是否是数字
     */
    public static boolean isNumber(String str) {
        if (str == null || str.trim().length() == 0) {
            return false;
        }
        Pattern pattern = Pattern.compile("[0-9]*");
        return pattern.matcher(str).matches();
    }

    /**
     * @param str
     * @return
     * @Title: isMoney
     * @Description: 判断是否是可以带小数点和千分位的数字
     */
    public static boolean isPointCommaNumber(String str) {
        if (str == null || str.trim().length() == 0) {
            return false;
        }
        Pattern pattern = Pattern.compile("[0-9\\.,]*");
        return pattern.matcher(str).matches();
    }

    /**
     * @param str
     * @return
     * @Title: isContainChinese
     * @Description: 判断是否含有中文
     */
    public static boolean isContainChinese(String str) {
        boolean flag = true;
        int count = 0;
        String regEx = "[\\u4e00-\\u9fa5]";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(str);
        while (m.find()) {
            for (int i = 0; i <= m.groupCount(); i++) {
                count++;
            }
        }
        if (count == 0) {
            flag = false;
        }
        return flag;
    }

    /**
     * @param str
     * @param beginIndex
     * @param endIndex
     * @return
     * @Title: subString
     * @Description: 截取字符串，如果超出范围则返回空字符串
     */
    public static String subString(String str, int beginIndex, int endIndex) {
        int length = str.length();
        String result = "";
        if (beginIndex < 0) {
            beginIndex = 0;
        }
        if (beginIndex <= length && endIndex <= length && beginIndex < endIndex) {
            result = str.substring(beginIndex, endIndex);
        } else if (beginIndex <= length && endIndex > length) {
            result = str.substring(beginIndex, length);
        }
        return result;
    }

    /**
     * @param
     * @return
     * @throws ParseException
     * @describe 日期转换成字符串
     */
    public static Date strToDateLong(String strDate) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        ParsePosition pos = new ParsePosition(0);
        Date strtodate = formatter.parse(strDate, pos);
        return strtodate;
    }

    /**
     * @param
     * @return
     * @throws ParseException
     * @describe 日期转换成字符串
     */
    public static Date strToDateShort(String strDate) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        ParsePosition pos = new ParsePosition(0);
        Date strtodate = formatter.parse(strDate, pos);
        return strtodate;
    }

    /**
     * 是否存在指定的字符串
     *
     * @param str   大字符串
     * @param regex 判断是否存在的字符串
     * @return
     * @author wangaz
     * @Date {date}
     */
    public static boolean stringFilter(String str, String regex) {
        Pattern p = Pattern.compile(regex);
        Matcher matcher = p.matcher(str);
        return matcher.find();
    }

    /**
     * 替换存在的字符串
     *
     * @param str   大字符串
     * @param regex 被替换的字符串
     * @param reStr 替换的字符串
     * @return
     * @author wangaz
     * @Date {date}
     */
    public static String filter(String str, String regex, String reStr) {
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(str);
        return m.replaceAll(reStr).trim();
    }

    /**
     * 一组字符串，获取最小的字符串
     *
     * @param list 字符串list
     * @return
     * @author wuh
     */
    public static String getMin(List<String> list) {
        if (list == null || list.size() == 0) {
            return null;
        }
        String min = list.get(0);
        for (int i = 1; i < list.size(); i++) {
            if (list.get(i).compareTo(min) < 0) {
                min = list.get(i);
            }
        }
        return min;
    }

    /**
     * 一组字符串，获取最大的字符串
     *
     * @param list 字符串list
     * @return
     * @author
     */
    public static String getMax(List<String> list) {
        if (list == null || list.size() == 0) {
            return null;
        }
        String min = list.get(0);
        for (int i = 1; i < list.size(); i++) {
            if (list.get(i).compareTo(min) > 0) {
                min = list.get(i);
            }
        }
        return min;
    }

    /**
     * @param curNo 当前编号
     * @param size
     * @return
     * @Title: getNextNo
     * @Description: 获取下一个编号（当前编号加1），前面自动补0
     * 例如： curNo:0001
     * size:4
     * 返回：0002
     */
    public static String getNextNo(String curNo, long size) {
        if (StringUtil.isEmpty(curNo)) {
            curNo = "0";
        }
        long curNoLong = Long.parseLong(curNo);
        long nextCurNo = curNoLong + 1;
        long zeroSize = size - String.valueOf(nextCurNo).length();
        if (zeroSize < 0) {
            return "";
        }
        StringBuffer nextNo = new StringBuffer();
        if (zeroSize > 0) {
            for (long i = 0; i < zeroSize; i++) {
                nextNo.append("0");
            }
        }
        nextNo.append(nextCurNo);
        return nextNo.toString();
    }

    public static List<String> stringToList(String str, String sign) {
        if (!StringUtil.isEmpty(str) && !StringUtil.isEmpty(sign)) {
            String[] strArray = str.split(sign);
            return Arrays.asList(strArray);
        }
        return null;
    }

    /**
     * @return
     * @Title: getHtmlNewLine
     * @Description: 获取html的换行符
     */
    public static final String getHtmlNewLine() {
        return "<br>";
    }

    public static void main(String[] args) {
        //    long lo = toLong("0");
        //    System.out.println(StringUtil.dateToStrLong(new Date()));
        //    System.out.println(lo == 0);
        //    System.out.println(isEmpty(lo));
        //    System.out.println(strTransHump("AAA_BBB_CdD"));
        //    String regex = "[`~{}:;\\[\\]/?~@#￥……&（）——|{}【】‘；：”“’。，、？|]";
        //    System.out.println(StringUtil.stringFilter("and (acco_code = '11,1122') and dr_cr <> 1",""));
        String regex = "[`~{}:;/?~@#￥……&（）——{}【】‘；：”“’。，、？\r\t]";
//        System.out.println(StringUtil.stringFilter("@@111##DDD$$$sss$$  \tddd      and dr_cr <> 1", regex));
//        System.out.println(StringUtil.filter("@@111##DDD$$$sss$$  \tddd      and dr_cr <> 1", regex, "呃"));
    }


    //驼峰形转蛇形

    /**
     * @param string
     * @return
     */
    public static String snakeToCamel(String string) {

        StringBuilder stringBuilder = new StringBuilder();

        String[] str = string.split("_");

        for (String string2 : str) {

            if (stringBuilder.length() == 0) {

                stringBuilder.append(string2);
            } else {
                stringBuilder.append(string2.substring(0, 1).toUpperCase());

                stringBuilder.append(string2.substring(1));

            }
        }

        return stringBuilder.toString();

    }

    /**
     * 首字母小写
     * @param str
     * @return
     */
    public static String lowerFirstCase(String str){
        char[] chars = str.toCharArray();
        //首字母小写方法，大写会变成小写，如果小写首字母会消失
        chars[0] +=32;
        return String.valueOf(chars);

    }

}
