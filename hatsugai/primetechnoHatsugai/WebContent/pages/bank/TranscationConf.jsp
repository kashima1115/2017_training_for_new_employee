<%@ page language="java" contentType="text/html; charset=Windows-31J"
    pageEncoding="Windows-31J"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html:html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=Windows-31J">
<meta http-equiv="Content-Style-Type" content="text/css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/pages/css/normaldesign.css">
<title>振込確認</title>
</head>
<body>
	<jsp:include page="/pages/bank/header.jsp"></jsp:include>
	<h1>振込確認</h1>
	<table>
		<tr><td>取引先名</td><td><bean:write name="BankForm" property="katakana" /></td></tr>
		<tr><td>口座番号</td><td><bean:write name="BankForm" property="otherAccountNumber" /></td></tr>
		<tr><td>金額</td><td><bean:write name="BankForm" property="furikomigaku"  formatKey="numberformat.currency"/>円</td></tr>
		<tr><td>振込日</td><td><bean:write name="BankForm" property="fYear" />年
		<bean:write name="BankForm" property="fMonth" />月
		<bean:write name="BankForm" property="fDate" />日</td></tr>
	</table>
	<html:form action="/TranscationConfAction">
		<div><html:submit styleId="middle_button" property="transcationConfAction">
		<bean:message key="transcation" />
		</html:submit>
		<html:submit styleId="middle_button" property="transcationConfAction">
		<bean:message key="return" />
		</html:submit></div>
	</html:form>
</body>
</html:html>