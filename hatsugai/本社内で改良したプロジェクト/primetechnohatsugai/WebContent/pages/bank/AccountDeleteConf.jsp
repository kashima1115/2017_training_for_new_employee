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
<title>�������m�F</title>
</head>
<body>
<%-- 	<jsp:include page="/pages/bank/header.jsp"></jsp:include> --%>
	<h1>���m�F</h1>
	<p>�{���ɉ�񂵂܂����H</p>
	<html:form action="/AccountDeleteConfAction">
	<div><html:submit styleId="middle_button" property="accountDeleteConfAction">
	<bean:message key="conf" />
	</html:submit>
	<html:submit styleId="middle_button" property="accountDeleteConfAction">
	<bean:message key="return" />
	</html:submit>
	</div>
	</html:form>
</body>
</html:html>