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
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/pages/css/accountdesign.css">
<title>�������葱��</title>
</head>
<body>
	<html:errors/>
	<h1>�������葱��</h1>
	<p>������������葱�������܂��B�����ԍ��ƃp�X���[�h�E�Ïؔԍ�����͂��������c���̑�����̋��Z�@�ւ��w�肵�Ă��������B</p>
	<html:form action="/AccountDeleteProcessAction"><div>
		<table>
			<tr><td><bean:message key="accountNumber"/></td><td><html:text property="accountNumber" maxlength="10" /></td></tr>
			<tr><td><bean:message key="password"/></td><td><html:password property="password" maxlength="15"/></td></tr>
			<tr><td><bean:message key="passno"/></td><td><html:password property="passno" maxlength="4"/></td></tr>
		</table>
		<html:submit styleId="middle_button" property="accountDeleteProcessAction">
			<bean:message key="conf" />
		</html:submit>
		<html:submit styleId="middle_button" property="accountDeleteProcessAction">
			<bean:message key="return" />
		</html:submit>
		</div>
	</html:form>
</body>
</html:html>