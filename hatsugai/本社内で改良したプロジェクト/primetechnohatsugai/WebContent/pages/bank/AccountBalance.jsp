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
<title>残高照会</title>
</head>
<body>
	<jsp:include  page="/pages/bank/header.jsp"></jsp:include>
	<h1>残高照会</h1>
	<h1 class="printer"><bean:write name="BankForm" property="balance" formatKey="numberformat.currency"/>円</h1>
	<div><p class="left">‐（マイナス）がついている場合はキャッシング残高です。</p></div>
	<html:form action="/AccountBalanceAction">
		<div><html:submit styleId="middle_button" property="accountBalanceAction">
			<bean:message key="transHistory" />
		</html:submit>
		<html:submit styleId="middle_button" property="accountBalanceAction">
			<bean:message key="transcation" />
		</html:submit>
		<html:submit styleId="middle_button" property="accountBalanceAction">
			<bean:message key="menu" />
		</html:submit></div>
	</html:form>
</body>
</html:html>