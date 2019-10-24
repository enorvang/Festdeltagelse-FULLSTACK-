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
	<font color="red">${feilmelding}<a href="login">${loginLink }</a></font>
	<form id="form" method="post" class="pure-form pure-form-aligned">
		<fieldset>
			<div class="pure-control-group">
				<label for="fornavn">Fornavn: </label> <input id="fornavn" type="text"
					name="fornavn" value="${registreringsskjema.fornavn }" /> 
					<font color="red">${registreringsskjema.fornavnFeil}</font>
			</div>
			<div class="pure-control-group">
				<label for="etternavn">Etternavn: </label> <input id="etternavn" type="text"
					name="etternavn" value="${registreringsskjema.etternavn }" /> 
					<font color="red">${registreringsskjema.etternavnFeil}</font>
			</div>
			<div class="pure-control-group">
				<label for="mobil">Mobil (8 siffer) :</label> <input id="mobil" type="text"
					name="mobil" value="${registreringsskjema.mobil }" /> 
					<font color="red">${registreringsskjema.mobilFeil}</font>
			</div>
			<div class="pure-control-group">
				<label for="password">Passord:</label> <input id="passord" type="password"
					name="passord" value="${registreringsskjema.passord }" /> <span id="info" class="info">Passordlengde bør være mer lengre enn 10 tegn, og må inneholde minst et spesialtegn(!@#$%^*), en stor bokstav, en liten bokstav og et tall.</span> 
					<font color="red">${registreringsskjema.passordFeil}</font>
			</div>
			<div class="pure-control-group">
				<label for="passordRepetert">Passord repetert:</label> <input id="passordRepetert"
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
				<input type="button" class="pure-button pure-button-primary" onclick="location.href='login';" value="Logg inn" />
			</div>
		</fieldset>
	</form>
<script src="validator.js"></script>
</body>
</html>