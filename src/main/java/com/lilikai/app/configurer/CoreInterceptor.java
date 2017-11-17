package com.lilikai.app.configurer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.lilikai.app.core.ProjectConstant;

public class CoreInterceptor implements HandlerInterceptor {
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		String scheme = request.getScheme();
		String serverName = request.getServerName();
		int serverPort = request.getServerPort();
		
		String server = scheme + "://" + serverName + ":" + serverPort;
		String url = request.getRequestURL().toString();
		String fullUrl = url;
		if(!StringUtils.isEmpty(request.getQueryString())) {
			fullUrl = url + "?" + request.getQueryString();
		}

		request.setAttribute("__SERVER__", server);
		request.setAttribute("__URL__", url);
		request.setAttribute("__FULL_URL__", fullUrl);
		request.setAttribute("__STATIC__", server + ProjectConstant.STATIC_DIR);
		
		
		return true;
	}

	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {
		// TODO Auto-generated method stub

	}

}
