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
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/pages/css/normaldesign.css">
<title>未決済振込一覧</title>
</head>
<body>
	<jsp:include page="/pages/bank/header.jsp"></jsp:include>
	<h1>未決済振込一覧</h1>
	<logic:empty name="outstandingList"><h2 class="print">未決済振込はありません</h2></logic:empty>
	<logic:notEmpty name="outstandingList">
	<table border=1>
		<tr><th>決済予定日</th><th>取引先名</th><th>口座番号</th><th>振込金額</th><th>取消</th><tr>
		<logic:iterate id="ob" name="outstandingList" type="bean.OutstandingBean">
			<tr>
			<td><bean:write name="ob" property="reservedDate"/></td>
			<td><bean:write name="ob" property="furigana"/></td>
			<td><bean:write name="ob" property="otherAccountNumber" /></td>
			<td class="money"><bean:write name="ob" property="transCash" formatKey="numberformat.currency"/>円</td>
			<td style="background-color:#ffff00;"><html:form action="/OutstandingAction">
				<html:hidden name="ob" property="reservedDate"/>
				<html:hidden name="ob" property="furigana"/>
				<html:hidden name="ob" property="otherAccountNumber" />
				<html:hidden name="ob" property="transCash"/>
				<html:hidden name="ob" property="transID"/>
				<html:submit styleId="small_alert_button" property="outstandingAction">
					<bean:message key="deleteOutstandingConf" />
				</html:submit>
			</html:form></td></tr>
		</logic:iterate>
	</table>
	</logic:notEmpty>

	<html:form action="/OutstandingAction">
	<div><html:submit styleId="middle_button" property="outstandingAction">
		<bean:message key="transHistory" />
	</html:submit>
	<html:submit styleId="middle_button" property="outstandingAction">
		<bean:message key="menu" />
	</html:submit>
	</div>
	</html:form>
</body>
</html:html>