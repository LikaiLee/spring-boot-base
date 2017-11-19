package com.lilikai.app.configurer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.ui.Model;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.lilikai.app.core.ProjectConstant;

public class CoreInterceptor implements HandlerInterceptor {
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		/*String scheme = request.getScheme();
		String serverName = request.getServerName();
		int serverPort = request.getServerPort();
		
		String server = scheme + "://" + serverName + ":" + serverPort;
		String url = request.getRequestURL().toString();
		String fullUrl = url;
		if(!StringUtils.isEmpty(request.getQueryString())) {
			fullUrl = url + "?" + request.getQueryString();
		}

		request.setAttribute("_SERVER_", server);
		request.setAttribute("_URL_", url);
		request.setAttribute("_FULL_URL_", fullUrl);
		request.setAttribute("_STATIC_", server + ProjectConstant.STATIC_DIR);*/
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
