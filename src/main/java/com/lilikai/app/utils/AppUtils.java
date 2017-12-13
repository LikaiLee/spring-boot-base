package com.lilikai.app.utils;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;

/**
 * 工具类
 */
public class AppUtils {
    /**
     * 首字母大写
     *
     * @param str
     * @return
     */
    public static String upperCaseFirstLetter(String str) {
        char[] ch = str.toCharArray();
        if (ch[0] >= 'a' && ch[0] <= 'z') {
            ch[0] = (char) (ch[0] - 32);
        }
        return new String(ch);
    }

    /**
     * 格式化参数map，将参数最后一个值作为真实值 ;如/?a=1&b=2&a=3，最终a=3
     *
     * @param paramMap
     * @return
     */
    public static Map<String, String> formatParamMap(Map<String, String[]> paramMap) {
        Map<String, String> map = new HashMap<String, String>();
        Iterator<String> keys = paramMap.keySet().iterator();
        while (keys.hasNext()) {
            String key = keys.next();// key
            String[] value = paramMap.get(key);// 上面key对应的value
            map.put(key, value[value.length - 1]);
        }
        // for (Entry<String, String[]> keys : paramMap.entrySet()) {
        // String key = keys.getKey();
        // String[] value = keys.getValue();
        // map.put(key, value[value.length-1]);
        // }
        return map;
    }

    /**
     * 获取当前时间的时间戳，单位秒
     *
     * @return
     */
    public static int time() {
        Long time = System.currentTimeMillis() / 1000;
        return time.intValue();
    }

    /**
     * 格式化时间戳成日期时间
     *
     * @param timestamp
     * @param format
     * @return
     */
    public static String date(int timestamp, String... format) {
        if (timestamp == 0) {
            return " - "; // 无数据
        }
        String formatString = "yyyy-MM-dd HH:mm:ss";
        if (format.length == 1) {
            formatString = format[0];
        }
        SimpleDateFormat sdf = new SimpleDateFormat(formatString);
        return sdf.format(new Date(timestamp * 1000L));
    }

    /**
     * 去除字符串左侧的N多指定字符
     *
     * @param str
     * @param needle
     * @return
     */
    public static String leftTrim(String str, String needle) {
        int start = 0;
        while (start != str.length() && needle.indexOf(str.charAt(start)) != -1) {
            start++;
        }
        if (start == str.length()) {
            return "";
        }
        return str.substring(start);
    }

    /**
     * 去除字符串右侧的N多指定字符
     *
     * @param str
     * @param needle
     * @return
     */
    public static String rightTrim(String str, String needle) {
        int end = str.length();
        while (end != 0 && needle.indexOf(str.charAt(end - 1)) != -1) {
            end--;
        }
        if (end == 0) {
            return "";
        }
        return str.substring(0, end);
    }

    /**
     * 将对象的大写转换为下划线加小写，例如：userName-->user_name
     *
     * @param object
     * @return
     * @throws JsonProcessingException
     */
    public static String toUnderlineJSONString(Object object) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);
        mapper.setSerializationInclusion(Include.NON_NULL);
        String reqJson = mapper.writeValueAsString(object);
        return reqJson;
    }

    /**
     * 将下划线转换为驼峰的形式，例如：user_name-->userName
     *
     * @param json
     * @param clazz
     * @return
     * @throws IOException
     */
    public static <T> T toSnakeObject(String json, Class<T> clazz) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);
        T reqJson = mapper.readValue(json, clazz);
        return reqJson;
    }

    /**
     * null值变成空字符
     */
    public static String nullToEmpty(String str) {
        return str == null ? "" : str;
    }

    /**
     * 发送内容。使用UTF-8编码。
     *
     * @param response
     * @param contentType
     * @param text
     */
    public static void render(HttpServletResponse response, String text, String... contentType) {

        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        PrintWriter pw = null;
        try {
            pw = response.getWriter();
            pw.write(text);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            pw.close();
        }
    }

    /**
     * 获取一个字符串中的图片src 的list
     *
     * @param urlString
     * @return
     */
    public static List<String> getImgSrc(String urlString) {
        String IMGURL_REG = "\\<img\\s+src=.*?\\s*\\/?\\>";
        Matcher matcher = Pattern.compile(IMGURL_REG).matcher(urlString);
        List<String> listImgUrl = new ArrayList<String>();
        List<String> listImgSrc = new ArrayList<String>();
        while (matcher.find()) {
            listImgUrl.add(matcher.group());
        }
        for (String string : listImgUrl) {// 获取所有图片 标签
            Matcher m = Pattern.compile("src\\s*=\\s*\"?(.*?)(\"|>|\\s+)").matcher(string);
            while (m.find()) {
                listImgSrc.add(m.group(1));
            }
        }
        return listImgSrc;
    }

    /**
     * 替换一个字符串中的src 在前面加上当前服务器的ip 和 端口号
     *
     * @param request
     * @param urlString
     * @return
     */
    public static String replaceImgSrc(HttpServletRequest request, String urlString) {
        if (urlString != null && urlString != "") {
            List<String> listImgSrc = getImgSrc(urlString);
            for (String string : listImgSrc) {// 替换所有图片src
                urlString = urlString.replace(string, AppUtils.serverUrl(request, string));
            }
        }
        return urlString;
    }

    /**
     * 添加服务器的ip
     *
     * @param request
     * @param url
     * @return
     */
    public static String serverUrl(HttpServletRequest request, String url) {
        if (url.indexOf("http://") < 0) {
            String http = "http://" + request.getServerName() // 服务器地址
                    + ":" + request.getServerPort(); // 端口号
            if (url.startsWith("/")) {
                url = http + url;
            } else {
                url = http + "/" + url;
            }
        }
        return url;
    }

}
