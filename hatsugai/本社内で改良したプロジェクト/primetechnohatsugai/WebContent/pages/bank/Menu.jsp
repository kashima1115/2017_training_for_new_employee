<%@ page language="java" contentType="text/html; charset=Windows-31J"
    pageEncoding="Windows-31J"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html:html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=Windows-31J">
<meta http-equiv="Content-Style-Type" content="text/css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/pages/css/normaldesign.css">
<title>メニュー画面</title>
</head>
<body>
	<jsp:include page="/pages/bank/header.jsp"></jsp:include>
	<h1>メニュー</h1>
	<html:form action="/MenuAction">
		<div><html:submit styleId="middle_button" property="menuAction">
		<bean:message key="transHistory" />
		</html:submit>
		<html:submit styleId="middle_button" property="menuAction">
		<bean:message key="transcation" />
		</html:submit>
		<html:submit styleId="middle_button" property="menuAction">
		<bean:message key="zandaka" />
		</html:submit>
		<br/>
		<!-- ここから口座解約に行くボタン -->
		<html:submit styleId="middle_button" property="menuAction">
		<bean:message key="deleteAccount" />
		</html:submit>
		<!-- 口座解約ボタン終了 -->
		</div>
	</html:form>
</body>
</html:html>