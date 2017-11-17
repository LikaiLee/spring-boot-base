package com.lilikai.app.utils;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;

/**
 * 工具类
 *
 */
public class AppUtils {

	/**
	 * 给sessionName加上前缀，以避免和其他项目冲突(前缀是项目名称)
	 * 
	 * @param sessionName
	 * @return
	 */
	public static String formatSessionName(String sessionName) {
		// -
		// /usr/local/Cellar/tomcat/7.0.50/libexec/webapps/shdyj-admin/WEB-INF/classes/
		String path = AppUtils.class.getClassLoader().getResource("").getPath();
		String flagStr = "/WEB-INF/classes/";
		if (path.indexOf(flagStr) != -1) {
			path = path.substring(0, path.length() - flagStr.length());
			path = path.substring(path.lastIndexOf("/") + 1);
		}
		return path + "_" + sessionName;
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
	 * 命名由驼峰法变下划线小写
	 * 
	 * @return return "" if name is null or empty
	 */
	public static String underscoreName(String name) {
		if (name == null || name.isEmpty()) {
			return "";
		}
		StringBuilder result = new StringBuilder();
		String[] letters = { "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R",
				"S", "T", "U", "V", "W", "X", "Y", "Z" };
		// 将第一个字符处理成小写
		result.append(name.substring(0, 1).toLowerCase());
		// 循环处理其余字符
		for (int i = 1; i < name.length(); i++) {
			String s = name.substring(i, i + 1);
			// 在大写字母前添加下划线
			if (Arrays.asList(letters).contains(s)) {
				result.append("_");
			}
			// 其他字符直接转成小写
			result.append(s.toLowerCase());
		}
		return result.toString();
	}

	/**
	 * 命名由下划线小写变驼峰法
	 * 
	 * @param name
	 *            待转换的下划线名称
	 * @param isFirstUpperCase
	 *            首字母是否需要大写，默认小写
	 * @return return null if name is null or empty
	 */
	public static String camelName(String name, Boolean... isFirstUpperCase) {
		if (name == null || name.isEmpty()) {
			return "";
		}
		StringBuilder result = new StringBuilder();
		if (isFirstUpperCase.length != 0 && isFirstUpperCase[0]) {
			// 将第一个字符处理成大写
			result.append(name.substring(0, 1).toUpperCase());
		} else {
			// 将第一个字符保持不变
			result.append(name.substring(0, 1));
		}

		// 循环处理其余字符
		for (int i = 1; i < name.length(); i++) {
			String s = name.substring(i, i + 1);
			// 遇到下划线则舍弃，并把后一个字符变大写
			if (s.equals("_")) {
				i++;
				String nextChar = name.substring(i, i + 1);
				result.append(nextChar.toUpperCase());
			} else {
				// 其他字符直接追加上
				result.append(s);
			}

		}
		return result.toString();
	}

	/**
	 * null值变成空字符
	 */
	public static String nullToEmpty(String str) {
		return str == null ? "" : str;
	}

	/**
	 * ajax返回json数据
	 * 
	 * @param response
	 * @param data
	 *            要返回的数据
	 */
	public static String ajaxReturn(HttpServletResponse response, Object data) {
		render(response, JSON.toJSONString(data), "json");
		return null;
	}

	/**
	 * ajax返回json数据，参数重载
	 * 
	 * @param response
	 * @param data
	 *            要返回的数据
	 * @param info
	 *            返回的信息
	 * @param status
	 *            返回的状态
	 * @return
	 */
	public static String ajaxReturn(HttpServletResponse response, Object data, String info, int status) {

		Map<String, Object> jsonData = new HashMap<String, Object>();
		jsonData.put("data", data);
		jsonData.put("info", info);
		jsonData.put("status", status);

		render(response, JSON.toJSONString(jsonData), "json");
		return null;
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
