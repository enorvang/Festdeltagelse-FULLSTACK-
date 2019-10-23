<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<!-- Fra https://purecss.io/ -->
<link rel="stylesheet"
	href="https://unpkg.com/purecss@1.0.0/build/pure-min.css">
<title>Påmelding</title>
</head>
<body>
	<h2>Påmelding</h2>
	<font color="red">${feilmelding}</font>
	<form method="post" class="pure-form pure-form-aligned">
		<fieldset>
			<div class="pure-control-group">
				<label for="fornavn">Fornavn: </label> <input type="text"
					name="fornavn" value="${registreringsskjema.fornavn }" /> 
					<font color="red">${registreringsskjema.fornavnFeil}</font>
			</div>
			<div class="pure-control-group">
				<label for="etternavn">Etternavn: </label> <input type="text"
					name="etternavn" value="${registreringsskjema.etternavn }" /> 
					<font color="red">${registreringsskjema.etternavnFeil}</font>
			</div>
			<div class="pure-control-group">
				<label for="mobil">Mobil (8 siffer) :</label> <input type="text"
					name="mobil" value="${registreringsskjema.mobil }" /> 
					<font color="red">${registreringsskjema.mobilFeil}</font>
			</div>
			<div class="pure-control-group">
				<label for="password">Passord:</label> <input type="password"
					name="passord" value="${registreringsskjema.passord }" /> 
					<font color="red">${registreringsskjema.passordFeil}</font>
			</div>
			<div class="pure-control-group">
				<label for="passordRepetert">Passord repetert:</label> <input
					type="password" name="passordRepetert"
					value="${registreringsskjema.passordRepetert }" /> 
					<font color="red">${registreringsskjema.passordRepetertFeil}</font>
			</div>
			<div class="pure-control-group">
				<label for="kjonn">Kjønn:</label> <input type="radio" name="kjonn"
					value="mann"
					 />mann
				<input type="radio" name="kjonn" value="kvinne"
					 />kvinne
				<font color="red">${registreringsskjema.kjonnFeil}</font>
			</div>
			<div class="pure-controls">
				<button type="submit" class="pure-button pure-button-primary">Meld
					meg på</button>
			</div>
		</fieldset>
	</form>
</body>
</html>