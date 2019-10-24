<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<!-- Fra https://purecss.io/ -->
<link rel="stylesheet"
	href="https://unpkg.com/purecss@1.0.0/build/pure-min.css">
<title>Deltagerliste</title>
</head>
<body>
	<h2>Deltagerliste</h2>
	<table class="pure-table">
		<tr bgcolor="#cccccc">
			<th>Kjønn</th>
			<th align="left">Navn</th>
			<th align="left">Mobil</th>
		</tr>
		<c:forEach var="deltager" items="${deltagerliste}">
			<c:set var="innloggetDeltager" value="${innloggetDeltager}" />
			<c:choose>
				<c:when test="${deltager.mobil eq innloggetDeltager.mobil}">
					<tr bgcolor="#aaffaa">
						<td align="center">${deltager.kjonn == "kvinne" ? "&#9792;" : "&#9794;"}</td>
						<td>${deltager.fornavn} ${deltager.etternavn}</td>
						<td>${deltager.mobil}</td>
					</tr>
				</c:when>
				<c:otherwise>
					<tr bgcolor="#ffffff">
						<td align="center">${deltager.kjonn == "kvinne" ? "&#9792;" : "&#9794;"}</td>
						<td>${deltager.fornavn} ${deltager.etternavn}</td>
						<td>${deltager.mobil}</td>
					</tr>
				</c:otherwise>
			</c:choose>

		</c:forEach>
	</table>
	<p>
		<a href="loggut">Ferdig</a>
	</p>
</body>
</html>