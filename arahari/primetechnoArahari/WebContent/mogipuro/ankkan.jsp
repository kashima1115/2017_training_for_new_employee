<%@ page language="java" contentType="text/html; charset=Windows-31J"
	pageEncoding="Windows-31J"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=Windows-31J">
<link rel="stylesheet" href="<html:rewrite page='/style/sheet.css'/>"
	type="text/css">

<title>�A���P�[�g�쐬�������</title>
</head>
<body>


<!-- �s���A�N�Z�X���� -->
	<logic:notPresent name="logid" scope="session">
		<logic:redirect forward="logerror"/>
	</logic:notPresent>


	<h1>�A���P�[�g�쐬����</h1>
	<a class="square_btn"> �A���P�[�g�쐬���肪�Ƃ��������܂��I 2P����܂��� </a>

	<html:form action="/AnkkanAction">
		<html:submit property="kanControl" styleClass="deg_btn">
			<bean:message key="home" />
		</html:submit>
		<br />


		<html:submit property="kanControl" styleClass="deg_btn">
			<bean:message key="logout" />
		</html:submit>


	</html:form>

</body>
</html:html>