<%@ page language="java" contentType="text/html; charset=Windows-31J"
	pageEncoding="Windows-31J"%>
<%@ page import="java.util.Calendar"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=Windows-31J">
<link rel="stylesheet" href="<html:rewrite page='/style/sheet.css'/>"
	type="text/css">

<title>�A���P�[�g�쐬���</title>
</head>
<body>


<!-- �s���A�N�Z�X���� -->
	<logic:notPresent name="logid" scope="session">
		<logic:redirect forward="logerror"/>
	</logic:notPresent>




	<h1>�A���P�[�g�쐬</h1>
	�� �͓��͕K�{�ł��B<br/>
	<a class="error"> <html:errors />
	</a>
	<html:form action="/AnkmakeAction">


	�A���P�[�g����<html:text property="ankName" />
		<br />
		<br />


	������e�P��<html:text property="question1" />
		<br />

	�I����<br />��<html:text property="choices11" /><br />
		��<html:text property="choices12" />
		<br />
		<html:text property="choices13" /><br />
		<html:text property="choices14" /><br />
		<br />
		<br />


	������e�Q<html:text property="question2" />
		<br />

	�I����<br /><html:text property="choices21" /><br />
		<html:text property="choices22" />
		<br />
		<html:text property="choices23" /><br />
		<html:text property="choices24" /><br />
		<br />
		<br />


	������e�R<html:text property="question3" />
		<br />

	�I����<br /><html:text property="choices31" /><br />
		<html:text property="choices32" />
		<br />
		<html:text property="choices33" /><br />
		<html:text property="choices34" /><br />
		<br />
		<br />


	������e�S<html:text property="question4" />
		<br />

	�I����<br /><html:text property="choices41" /><br />
		<html:text property="choices42" />
		<br />
		<html:text property="choices43" /><br />
		<html:text property="choices44" /><br />
		<br />
		<br />


	������e�T<html:text property="question5" />
		<br />

	�I����<br /><html:text property="choices51" /><br />
		<html:text property="choices52" />
		<br />
		<html:text property="choices53" /><br />
		<html:text property="choices54" /><br />
		<br />
		<br />




�I������
		         <html:select property="year">
			<option></option>
			<html:optionsCollection property="yearList" value="value"
				label="label" />
		</html:select>
		�N
		<html:select property="month">
			<option></option>
			<html:optionsCollection property="monthList" value="value"
				label="label" />
		</html:select>
		��
		<html:select property="day">
			<option></option>
			<html:optionsCollection property="dayList" value="value"
				label="label" />
		</html:select>
		��
<br />


		<html:submit value="�쐬" styleClass="deg_btn" /><br/>
		<html:cancel value="�߂�" styleClass="deg_btn" />



	</html:form>
</body>
</html:html>