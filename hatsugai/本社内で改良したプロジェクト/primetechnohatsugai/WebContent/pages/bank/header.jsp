<%@ page language="java" contentType="text/html; charset=Windows-31J"
    pageEncoding="Windows-31J"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=Windows-31J">
<meta http-equiv="Content-Style-Type" content="text/css">
 <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/pages/css/normaldesign.css">
</head>
<body class="back">
	<!-- �����O�C�������� -->
	<logic:notPresent name="BankForm" property="name">
		<logic:forward name="directerror"/>
	</logic:notPresent>
	<!-- �����O�C���������I�� -->
	<logic:present name="BankForm" property="name">
	<html:form action="/HeaderAction">
	<p class="right"><bean:write name="BankForm" property="name" />�l<br>
	<html:submit styleId="middle_button" property="headerAction">
		<bean:message key="logout" />
		</html:submit>
	</p>
	</html:form>
	</logic:present>
</body>
</html>