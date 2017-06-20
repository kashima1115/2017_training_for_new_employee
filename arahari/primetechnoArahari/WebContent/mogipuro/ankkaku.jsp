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

<title>アンケート確認画面</title>
</head>
<body>


<!-- 不正アクセス処理 -->
	<logic:notPresent name="logid" scope="session">
		<logic:redirect forward="logerror"/>
	</logic:notPresent>



	<h1>アンケート確認</h1>

		<a class="error"> <html:errors />
	</a>
	<html:form action="/AnkkakuAction">

アンケート名　<bean:write name="MakeForm" property="ankName" />
		<br />
		<br />

質問内容１<bean:write name="MakeForm" property="question1" />
		<br />
選択肢<br />
		<bean:write name="MakeForm" property="choices11" />
		<br />
		<bean:write name="MakeForm" property="choices12" />
		<br />
		<bean:write name="MakeForm" property="choices13" />
		<br />
		<bean:write name="MakeForm" property="choices14" />
		<br />
		<br />

質問内容２<bean:write name="MakeForm" property="question2" />
		<br />
選択肢<br />
		<bean:write name="MakeForm" property="choices21" />
		<br />
		<bean:write name="MakeForm" property="choices22" />
		<br />
		<bean:write name="MakeForm" property="choices23" />
		<br />
		<bean:write name="MakeForm" property="choices24" />
		<br />
		<br />

質問内容３<bean:write name="MakeForm" property="question3" />
		<br />
選択肢<br />
		<bean:write name="MakeForm" property="choices31" />
		<br />
		<bean:write name="MakeForm" property="choices32" />
		<br />
		<bean:write name="MakeForm" property="choices33" />
		<br />
		<bean:write name="MakeForm" property="choices34" />
		<br />
		<br />

質問内容４<bean:write name="MakeForm" property="question4" />
		<br />
選択肢<br />
		<bean:write name="MakeForm" property="choices41" />
		<br />
		<bean:write name="MakeForm" property="choices42" />
		<br />
		<bean:write name="MakeForm" property="choices43" />
		<br />
		<bean:write name="MakeForm" property="choices44" />
		<br />
		<br />

質問内容５<bean:write name="MakeForm" property="question5" />
		<br />
選択肢<br />
		<bean:write name="MakeForm" property="choices51" />
		<br />
		<bean:write name="MakeForm" property="choices52" />
		<br />
		<bean:write name="MakeForm" property="choices53" />
		<br />
		<bean:write name="MakeForm" property="choices54" />
		<br />
		<br />

実施期間
		<bean:write name="MakeForm" property="year" />年
		<bean:write name="MakeForm" property="month" />月
		<bean:write name="MakeForm" property="day" />日<br />


		<br />


		<html:submit property="akControl" styleClass="deg_btn">
			<bean:message key="aknext" />
		</html:submit>
		<br/>
				<html:submit property="akControl" styleClass="deg_btn">
			<bean:message key="akback" />
		</html:submit>

	</html:form>
</body>
</html:html>