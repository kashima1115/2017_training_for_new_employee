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

<title>�폜�A���P�[�g�I�����</title>
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


<!-- �s���A�N�Z�X���� -->
	<logic:notPresent name="logid" scope="session">
		<logic:redirect forward="logerror"/>
	</logic:notPresent>



	<h1>�폜�A���P�[�g�I��</h1>

	<a class="error"> <html:errors />
	</a>

	<html:form action="/DelitiAction">


	<a class="square_btn">
	�폜����A���P�[�g��I�����Ă�������
</a>
<br/>

		<logic:notEmpty name="makeList">

			<table border="1">
				<tr>

					<th>�A���P�[�g��</th>
					<th>�I���{�^��</th>
				</tr>
				<logic:iterate id="bn3" name="makeList" type="mogipuro.MakeForm">

					<tr>

						<td><bean:write name="bn3" property="endName" /></td>


						<td><html:radio property="radio"
								value="<%=bn3.getEndName()%>" /></td>


					</tr>

				</logic:iterate>
			</table>
		</logic:notEmpty>
		<br/>



		<html:submit property="ditiControl" styleClass="deg_btn">
			<bean:message key="ditinext" />
		</html:submit>
		<br />


		<html:submit property="ditiControl" styleClass="deg_btn">
			<bean:message key="back" />
		</html:submit>


	</html:form>
</body>
</html:html>