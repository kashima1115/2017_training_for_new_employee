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

<title>�폜�m�F���</title>
</head>
<body>



<!-- �s���A�N�Z�X���� -->
	<logic:notPresent name="logid" scope="session">
		<logic:redirect forward="logerror"/>
	</logic:notPresent>



	<h1>�폜�m�F</h1>


	<a class="error">
	<html:errors />
	</a>
	<html:form action="/DelkakuAction">

<a class="square_btn">
�폜����Ƒ��̃��[�U�����̃A���P�[�g�ɉ񓚂��邱�Ƃ⌋�ʂ��{�����邱�Ƃ��ł��Ȃ��Ȃ�܂��B<br/>
�{���ɍ폜���܂����H<br/>
</a>

<br/>
	�A���P�[�g���@<bean:write name="MakeForm" property="radio" />
		<br />



		<br />
		<html:submit property="dkakuControl" styleClass="deg_btn">
			<bean:message key="dkakunext" />
		</html:submit>
		<br />


		<html:submit property="dkakuControl" styleClass="deg_btn">
			<bean:message key="back" />
		</html:submit>


	</html:form>
</body>
</html:html>