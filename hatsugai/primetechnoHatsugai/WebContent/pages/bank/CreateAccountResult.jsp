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
<title>�����J�݊���</title>
</head>
<body>
	<h1>�����J�݊���</h1>
	<h2>�����J�݂��������܂����B���Ȃ��̌����ԍ���<bean:write name="CreateAccountForm" property="accountNumber"/>�ł��B</h2>
	<html:form action="/CreateAccountResultAction">
		<div><html:submit styleId="middle_button" property="createAccountResultAction">
			<bean:message key="tologin"/>
		</html:submit></div>
	</html:form>
</body>
</html:html>