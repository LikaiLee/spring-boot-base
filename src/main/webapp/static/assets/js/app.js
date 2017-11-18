var APP = function(name) {
	this.name = name;
}

APP.prototype.getAppInfo = function() {
	return {
		name : this.name,
		website : 'http://www.lilikai.tk',
		github : 'likailee',
		version : '1.0.0'
	};
}
APP.prototype.ajax = function(opt) {
	opt = opt || {};
	opt.method = opt.method ? opt.method.toUpperCase() : 'GET';
	opt.url = opt.url || '';
	opt.async = opt.async || true;
	opt.data = opt.data || null;
	opt.success = opt.success || function() {};
	opt.error = opt.error || function() {}
	var xmlHttp = null;
	if (XMLHttpRequest) {
		xmlHttp = new XMLHttpRequest();
	} else {
		xmlHttp = new ActiveXObject('Microsoft.XMLHTTP');
	}
	var params = [];
	for (var key in opt.data) {
		params.push(encodeURIComponent(key) + '=' + encodeURIComponent(opt.data[key]));
	}
	var postData = params.join('&');
	var method = opt.method.toUpperCase();
	switch (method) {
	case 'POST':
		xmlHttp.open(method, opt.url, opt.async);
		xmlHttp.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
		xmlHttp.send(postData);
		break;
	case 'GET':
		xmlHttp.open(method, opt.url + '?' + postData, opt.async);
		xmlHttp.send(null);
		break;
	default:
		break;
	}
	xmlHttp.onreadystatechange = function() {
		if (xmlHttp.readyState === 4) {
			let status = xmlHttp.status;
			if ((status >= 200 && status < 300) || status === 304) {
				opt.success(xmlHttp.responseText);
			} else {
				console.log('Request was unsuccessful, error: ' + status)
				opt.error(status);
			}
		}
	};
}