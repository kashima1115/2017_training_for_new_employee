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


<title>スタート画面</title>
</head>
<body>
	<h1>アンケート調査協力サイト</h1>

	<a class="square_btn"> 登録済みの方はログインボタン、初めての方はユーザ登録ボタンを押してください </a>


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