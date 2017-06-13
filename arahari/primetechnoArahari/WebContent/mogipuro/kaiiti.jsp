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

<title>回答時の実施期間内のアンケート一覧画面</title>

<style>
table {
	border-collapse: collapse;
	text-align: left;
	line-height: 1.5;
	border-left: 1px solid #ccc;
}

table thead th {
	padding: 10px;
	font-weight: bold;
	border-top: 1px solid #ccc;
	border-right: 1px solid #ccc;
	border-bottom: 2px solid #c00;
	background: #dcdcd1;
}

table tbody th {
	width: 150px;
	padding: 10px;
	font-weight: bold;
	vertical-align: top;
	border-right: 1px solid #ccc;
	border-bottom: 1px solid #ccc;
	background: #ececec;
}

table td {
	width: 350px;
	padding: 10px;
	vertical-align: top;
	border-right: 1px solid #ccc;
	border-bottom: 1px solid #ccc;
}
</style>


</head>
<body>
	<h1>回答アンケート選択</h1>
	<a class="square_btn">同じアンケートに複数回回答しないようお願い致します </a>
	<br />

	<a class="error"> <html:errors />
	</a>


	<html:form action="/KaiitiAction">

		<br />
		<logic:notEmpty name="ankList">

			<table border="1">
				<tr>
					<th>実施期間</th>
					<th>アンケート名</th>
					<th>選択ボタン</th>
				</tr>
				<logic:iterate id="bn" name="ankList" type="mogipuro.MakeForm">

					<tr>
						<td><bean:write name="bn" property="ankEnd" /></td>
						<td><bean:write name="bn" property="ankName" /></td>


						<td><html:radio property="radio" value="<%=bn.getAnkName()%>" /></td>


					</tr>

				</logic:iterate>
			</table>
		</logic:notEmpty>
		<br />









		<html:submit property="kiControl" styleClass="deg_btn">
			<bean:message key="select" />
		</html:submit>
		<br />


		<html:submit property="kiControl" styleClass="deg_btn">
			<bean:message key="back" />
		</html:submit>


	</html:form>

</body>
</html:html>