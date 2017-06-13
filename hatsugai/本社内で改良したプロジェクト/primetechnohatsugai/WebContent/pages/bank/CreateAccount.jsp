<%@ page language="java" contentType="text/html; charset=Windows-31J"
    pageEncoding="Windows-31J"%>
<%@ page import="org.apache.struts.util.LabelValueBean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html:html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=Windows-31J">
<meta http-equiv="Content-Style-Type" content="text/css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/pages/css/normaldesign.css">
<title>口座開設画面</title>
</head>
<body>
	<h1>口座開設</h1>
	<html:form action="/CreateAccountAction">
	<div><h2>※は必須事項です。</h2></div>
	<html:errors/>
	<table>
		<tr><td>※</td><td><bean:message key="name" />（全角）</td><td><html:text property="name" /></td></tr>
		<tr><td>※</td><td><bean:message key="furigana"/>（全角カタカナ）</td><td><html:text property="katakana" /></td></tr>
		<tr><td>※</td><td><bean:message key="birthday"/>（西暦）</td>
			<td><html:select property="bYear">
				<html:option value=""></html:option>
				<logic:iterate id="byb" name="CreateAccountForm" property="bYearList" type="LabelValueBean">
					<html:option value="<%=byb.getValue() %>"><bean:write name="byb" property="label"/></html:option>
				</logic:iterate>
			</html:select>年
			<html:select property="fMonth">
			 	<html:option value=""></html:option>
			 	<logic:iterate id="mb" name="CreateAccountForm" property="monthList" type="LabelValueBean">
			 	   <html:option value="<%=mb.getValue() %>"><bean:write name="mb" property="label"/></html:option>
			 	</logic:iterate>
			 </html:select>月
			 <html:select property="fDate">
			 	<html:option value=""></html:option>
			 	<logic:iterate id="db" name="CreateAccountForm" property="dayList" type="LabelValueBean">
			 	   <html:option value="<%=db.getValue() %>"><bean:write name="db" property="label"/></html:option>
			 	</logic:iterate>
			 </html:select>日
		</td></tr>
		<tr><td></td><td><bean:message key="uAddress" /></td></tr>
		<tr><td>※</td><td>都道府県</td>
			<td><html:text property="uPrefAddress" maxlength="4"/></td><tr>
		<tr><td>※</td><td>市区町村</td><td><html:text property="uCityAddress" /></td></tr>
		<tr><td>※</td><td>番地等</td><td><html:text property="uConstructure" /></td></tr>
		<tr><td></td><td><bean:message key="tel"/>（半角数字、ハイフン不要）</td><td><html:text property="tel" maxlength="10" /></td></tr>
		<tr><td></td><td><bean:message key="mobile"/>（半角数字、ハイフン不要）</td><td><html:text property="mobile" maxlength="11" /></td></tr>
		<tr><td></td><td><bean:message key="mail"/>（半角英数字）</td><td><html:text property="mail" /></td></tr>
		<tr><td>※</td><td><bean:message key="zokusei"/></td>
			<td><html:radio property="zokusei" value="0" />個人
			<html:radio property="zokusei" value="1" />法人</td></tr>
		<tr><td colspan="3">パスワードは5文字以上15文字以内です。英字・数字を必ず使用してください。</td></tr>
		<tr><td>※</td><td><bean:message key="password"/>（半角英数字）</td><td><html:password property="password" maxlength="15"/></td></tr>
		<tr><td>※</td><td><bean:message key="password"/>（確認用）（半角英数字）</td><td><html:password property="passwordconf" maxlength="15"/></td></tr>
		<tr><td colspan="3">暗証番号は4桁の半角数字を入力してください。生年月日と連番は入力しないでください。</td></tr>
		<tr><td>※</td><td><bean:message key="passno"/>（半角数字）</td><td><html:password property="passno" maxlength="4" /></td></tr>
		<tr><td>※</td><td><bean:message key="passno"/>（確認用）（半角数字）</td><td><html:password property="passnoconf" maxlength="4" /></td></tr>
		<tr><td></td><td>勤務先</td></tr>
		<tr><td></td><td><bean:message key="worknm" /></td><td><html:text property="worknm" /></td></tr>
		<tr><td></td><td><bean:message key="workad" /></td></tr>
		<tr><td></td><td>都道府県</td><td><html:text property="wPrefAddress" /></td><tr>
		<tr><td></td><td>市区町村</td><td><html:text property="wCityAddress" /></td></tr>
		<tr><td></td><td>番地等</td><td><html:text property="wConstructure" /></td></tr>
		<tr><td></td><td><bean:message key="worktel" />（半角数字、ハイフン不要）</td><td><html:text property="worktel" maxlength="10" /></td></tr>
	</table>
	<div><html:submit styleId="middle_button" property="createAccountAction">
		<bean:message key="conf"/>
	</html:submit></div>
	</html:form>
	<html:form action="/CreateAccountActionB">
	<div><html:submit styleId="middle_button" property="createAccountActionB">
		<bean:message key="return" />
	</html:submit></div>
	</html:form>
</body>
</html:html>