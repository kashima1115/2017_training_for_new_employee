<%@ page language="java" contentType="text/html; charset=Windows-31J"
    pageEncoding="Windows-31J"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="org.apache.struts.util.LabelValueBean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ page import="java.util.Calendar" %>
<html:html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=Windows-31J">
<meta http-equiv="Content-Style-Type" content="text/css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/pages/css/normaldesign.css">
<title>振込申込画面</title>
</head>
<body>
	<jsp:include page="/pages/bank/header.jsp"></jsp:include>
	<h1>お振込お申込み</h1>
	<h2>項目はすべて必須事項です。</h2>
	<html:errors/>
	<html:form action="/TranscationAction">
		<table>
			<tr><td>お取引先名（カタカナ）（全角）</td><td><html:text property="katakana" />様</td></tr>
			<tr><td>お取引先口座番号（半角数字）</td><td><html:text property="otherAccountNumber" maxlength="10" /></td></tr>
			<tr><td>お振込額（半角数字のみ）</td><td><html:text property="furikomi" />円</td></tr>
			<tr><td>お振込日</td>
			<% Calendar cal = Calendar.getInstance();
			String year = String.valueOf(cal.get(Calendar.YEAR));
			String month = String.valueOf(cal.get(Calendar.MONTH)+1);
			String date = String.valueOf(cal.get(Calendar.DATE));%>
			<td><html:select property="fYear" value="<%=year %>">
				<logic:iterate id="yb" name="BankForm" property="yearList" type="LabelValueBean">
					<html:option value="<%=yb.getValue() %>"><bean:write name="yb" property="label"/></html:option>
				</logic:iterate>
			</html:select>年
			<html:select property="fMonth" value="<%=month %>">
			 	<logic:iterate id="mb" name="BankForm" property="monthList" type="LabelValueBean">
			 	   <html:option value="<%=mb.getValue() %>"><bean:write name="mb" property="label"/></html:option>
			 	</logic:iterate>
			 </html:select>月
			 <html:select property="fDate" value="<%=date %>">
			 	<logic:iterate id="db" name="BankForm" property="dayList" type="LabelValueBean">
			 	   <html:option value="<%=db.getValue() %>"><bean:write name="db" property="label"/></html:option>
			 	</logic:iterate>
			 </html:select>日
			 </td></tr>
		</table>
		<div><p class="left">残高が足りない場合は不足額を自動的にキャッシングします。<br/>
		申込み中にブラウザの戻るボタンを押さないでください。
		</p></div>
		<div><html:submit styleId="middle_button" property="transcationAction">
		<bean:message key="transcationconf" />
		</html:submit></div>
	</html:form>
	<br/>
	<html:form action="/TranscationActionB">
		<div><html:submit styleId="middle_button" property="transcationActionB">
		<bean:message key="transHistory" />
		</html:submit>
		<html:submit styleId="middle_button" property="transcationActionB">
		<bean:message key="menu" />
		</html:submit>
		<html:submit styleId="middle_button" property="transcationActionB">
		<bean:message key="zandaka" />
		</html:submit>
		</div>
	</html:form>
</body>
</html:html>