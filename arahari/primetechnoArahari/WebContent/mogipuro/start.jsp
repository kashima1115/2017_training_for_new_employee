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


<title>�X�^�[�g���</title>
</head>
<body>
	<h1>�A���P�[�g�������̓T�C�g</h1>

	<a class="square_btn"> �o�^�ς݂̕��̓��O�C���{�^���A���߂Ă̕��̓��[�U�o�^�{�^���������Ă������� </a>


	<html:form action="/StartAction">


		<html:submit property="startControl" styleClass="btn">
			<bean:message key="login" />
		</html:submit>


		<html:submit property="startControl" styleClass="btn">
			<bean:message key="touroku" />
		</html:submit>


	</html:form>

</body>
</html:html>