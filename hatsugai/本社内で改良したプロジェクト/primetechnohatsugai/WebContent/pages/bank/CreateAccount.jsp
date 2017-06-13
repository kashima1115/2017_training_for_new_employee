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
<title>�����J�݉��</title>
</head>
<body>
	<h1>�����J��</h1>
	<html:form action="/CreateAccountAction">
	<div><h2>���͕K�{�����ł��B</h2></div>
	<html:errors/>
	<table>
		<tr><td>��</td><td><bean:message key="name" />�i�S�p�j</td><td><html:text property="name" /></td></tr>
		<tr><td>��</td><td><bean:message key="furigana"/>�i�S�p�J�^�J�i�j</td><td><html:text property="katakana" /></td></tr>
		<tr><td>��</td><td><bean:message key="birthday"/>�i����j</td>
			<td><html:select property="bYear">
				<html:option value=""></html:option>
				<logic:iterate id="byb" name="CreateAccountForm" property="bYearList" type="LabelValueBean">
					<html:option value="<%=byb.getValue() %>"><bean:write name="byb" property="label"/></html:option>
				</logic:iterate>
			</html:select>�N
			<html:select property="fMonth">
			 	<html:option value=""></html:option>
			 	<logic:iterate id="mb" name="CreateAccountForm" property="monthList" type="LabelValueBean">
			 	   <html:option value="<%=mb.getValue() %>"><bean:write name="mb" property="label"/></html:option>
			 	</logic:iterate>
			 </html:select>��
			 <html:select property="fDate">
			 	<html:option value=""></html:option>
			 	<logic:iterate id="db" name="CreateAccountForm" property="dayList" type="LabelValueBean">
			 	   <html:option value="<%=db.getValue() %>"><bean:write name="db" property="label"/></html:option>
			 	</logic:iterate>
			 </html:select>��
		</td></tr>
		<tr><td></td><td><bean:message key="uAddress" /></td></tr>
		<tr><td>��</td><td>�s���{��</td>
			<td><html:text property="uPrefAddress" maxlength="4"/></td><tr>
		<tr><td>��</td><td>�s�撬��</td><td><html:text property="uCityAddress" /></td></tr>
		<tr><td>��</td><td>�Ԓn��</td><td><html:text property="uConstructure" /></td></tr>
		<tr><td></td><td><bean:message key="tel"/>�i���p�����A�n�C�t���s�v�j</td><td><html:text property="tel" maxlength="10" /></td></tr>
		<tr><td></td><td><bean:message key="mobile"/>�i���p�����A�n�C�t���s�v�j</td><td><html:text property="mobile" maxlength="11" /></td></tr>
		<tr><td></td><td><bean:message key="mail"/>�i���p�p�����j</td><td><html:text property="mail" /></td></tr>
		<tr><td>��</td><td><bean:message key="zokusei"/></td>
			<td><html:radio property="zokusei" value="0" />�l
			<html:radio property="zokusei" value="1" />�@�l</td></tr>
		<tr><td colspan="3">�p�X���[�h��5�����ȏ�15�����ȓ��ł��B�p���E������K���g�p���Ă��������B</td></tr>
		<tr><td>��</td><td><bean:message key="password"/>�i���p�p�����j</td><td><html:password property="password" maxlength="15"/></td></tr>
		<tr><td>��</td><td><bean:message key="password"/>�i�m�F�p�j�i���p�p�����j</td><td><html:password property="passwordconf" maxlength="15"/></td></tr>
		<tr><td colspan="3">�Ïؔԍ���4���̔��p��������͂��Ă��������B���N�����ƘA�Ԃ͓��͂��Ȃ��ł��������B</td></tr>
		<tr><td>��</td><td><bean:message key="passno"/>�i���p�����j</td><td><html:password property="passno" maxlength="4" /></td></tr>
		<tr><td>��</td><td><bean:message key="passno"/>�i�m�F�p�j�i���p�����j</td><td><html:password property="passnoconf" maxlength="4" /></td></tr>
		<tr><td></td><td>�Ζ���</td></tr>
		<tr><td></td><td><bean:message key="worknm" /></td><td><html:text property="worknm" /></td></tr>
		<tr><td></td><td><bean:message key="workad" /></td></tr>
		<tr><td></td><td>�s���{��</td><td><html:text property="wPrefAddress" /></td><tr>
		<tr><td></td><td>�s�撬��</td><td><html:text property="wCityAddress" /></td></tr>
		<tr><td></td><td>�Ԓn��</td><td><html:text property="wConstructure" /></td></tr>
		<tr><td></td><td><bean:message key="worktel" />�i���p�����A�n�C�t���s�v�j</td><td><html:text property="worktel" maxlength="10" /></td></tr>
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