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

<title>削除確認画面</title>
</head>
<body>



<!-- 不正アクセス処理 -->
	<logic:notPresent name="logid" scope="session">
		<logic:redirect forward="logerror"/>
	</logic:notPresent>



	<h1>削除確認</h1>


	<a class="error">
	<html:errors />
	</a>
	<html:form action="/DelkakuAction">

<a class="square_btn">
削除すると他のユーザがこのアンケートに回答することや結果を閲覧することができなくなります。<br/>
本当に削除しますか？<br/>
</a>

<br/>
	アンケート名　<bean:write name="MakeForm" property="radio" />
		<br />



		<br />
		<html:submit property="dkakuControl" styleClass="deg_btn">
			<bean:message key="dkakunext" />
		</html:submit>
		<br />


		<html:submit property="dkakuControl" styleClass="deg_btn">
			<bean:message key="back" />
		</html:submit>


	</html:form>
</body>
</html:html>