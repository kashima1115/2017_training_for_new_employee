<%@ page language="java" contentType="text/html; charset=Windows-31J"
    pageEncoding="Windows-31J"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=Windows-31J">
    <link rel="stylesheet" href="<html:rewrite page='/style/sheet.css'/>" type="text/css">

    <title>�o�^���</title>
</head>
<body>
	<h1>���[�U�o�^</h1>
	���@�͓��͕K�{�ł��B<br/>
	<a class="error"> <html:errors />
	</a>
	<html:form action="/TourokuAction">


	���[�U����<html:text property="id"/><br/>
	�p�X���[�h��<html:password property="pass"/><br/>
	�p�X���[�h�i�m�F�p�j<html:password property="passkaku"/><br/>

		<html:submit value="�m�F" styleClass="deg_btn"/><br/>
		<html:cancel value="�߂�" styleClass="deg_btn"/>



</html:form>

</body>
</html:html>