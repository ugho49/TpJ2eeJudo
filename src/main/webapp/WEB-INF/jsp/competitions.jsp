<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<t:template title="Compétitions">
	<jsp:attribute name="content">
		<h4>Liste des competitions</h4>
        <br>
        <c:forEach items="${competitions}" var="competition">
            <h5><fmt:formatDate pattern="dd/mm/yyyy" value="${competition.date}" /> - ${competition.nom}</h5>
            <h6>${competition.adresse} - ${competition.ville} (${competition.departement})</h6>
            <br>
        </c:forEach>
	</jsp:attribute>
</t:template>