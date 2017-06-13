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

<title>回答確認画面</title>
</head>
<body>
	<h1>回答確認</h1>
	<html:form action="/KaikakuAction">




	アンケート名　<bean:write name="MakeForm" property="radio" />
		<br />

		<logic:notEmpty name="kaiList">

			<logic:iterate id="kai" name="kaiList" type="mogipuro.MakeForm">
				<br />
				<logic:notEmpty name="kai" property="question">
	---------<bean:write name="kai" property="question" />---------------
	<br />
				</logic:notEmpty>

				<logic:notEmpty name="kai" property="choice">

					<logic:iterate id="cc" name="MakeForm" property="choices">
						<logic:equal name="cc" value="<%=kai.getSelect()%>">
	ゝ

	<bean:write name="kai" property="choice" />

							<br />
						</logic:equal>

					</logic:iterate>

				</logic:notEmpty>


			</logic:iterate>
		</logic:notEmpty>

		<br />
		<html:submit property="kkControl" styleClass="deg_btn">
			<bean:message key="kknext" />
		</html:submit>
		<br />


		<html:submit property="kkControl" styleClass="deg_btn">
			<bean:message key="back" />
		</html:submit>


	</html:form>
</body>
</html:html>