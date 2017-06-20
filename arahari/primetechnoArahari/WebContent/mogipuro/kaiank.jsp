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

<title>アンケート回答画面</title>
</head>
<body>


<!-- 不正アクセス処理 -->
	<logic:notPresent name="logid" scope="session">
		<logic:redirect forward="logerror"/>
	</logic:notPresent>



	<h1>アンケート回答</h1>

	<a class="error"> <html:errors />
	</a>
	<html:form action="/KaiankAction">

		アンケート名　<bean:write name="MakeForm" property="radio" />
				<br />


		<logic:iterate id="kai" name="kaiList" type="mogipuro.MakeForm">
			<br />
			<logic:notEmpty name="kai" property="question">



	---------<bean:write name="kai" property="question" />---------------
	<br />
			</logic:notEmpty>
			<logic:notEmpty name="kai" property="choice">

				<html:multibox property="choices" value="<%=kai.getSelect()%>" />
				<bean:write name="kai" property="choice" />
				<br />
			</logic:notEmpty>
		</logic:iterate>


		<br />


		<html:submit value="回答" styleClass="deg_btn" /><br/>
		<html:cancel value="戻る" styleClass="deg_btn" />
	</html:form>
</body>
</html:html>