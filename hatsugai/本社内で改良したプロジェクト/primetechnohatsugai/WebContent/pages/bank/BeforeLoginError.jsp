<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<html:html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="Content-Style-Type" content="text/css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/pages/css/normaldesign.css">
<title>深刻なエラー発生</title>
</head>
<body>
	<div><h2>不正な操作が行われました。恐れ入りますが最初からやり直してください。</h2>
	<html:form action="/BeforeLoginErrorAction">
	<html:submit styleId="middle_button" property="beforeLoginErrorAction">
		<bean:message key="tologin" />
	</html:submit>
	</html:form></div>
</body>
</html:html>