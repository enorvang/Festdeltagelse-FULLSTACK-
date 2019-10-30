let fornavn = document.getElementById("fornavn")
let etternavn = document.getElementById("etternavn")
let mobil = document.getElementById("mobil")
let passord = document.getElementById("passord")
let passordRepetert = document.getElementById("passordRepetert")
let kjonn = document.getElementsByName("kjonn")
let knapp = document.getElementById("paameldingsknapp")
let info = document.getElementById("info");

let gyldigFornavn = false;
let gyldigEtternavn = false;
let gyldigMobil = false;
let gyldigPassord = false;
let gyldigPassordRepetert = false;
let gyldigKjonn = false;

let fornavnRegex = /^[A-ZÆØÅ][A-ZÆØÅa-zæøå -]{2,19}$/;
let etternavnRegex = /^[A-ZÆØÅ][A-ZÆØÅa-zæøå-]{2,19}$/;
let mobilRegex = /^[0-9]{8}$/;
let passordRegex = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{8,}$/;



fornavn.addEventListener("keyup", function() {
    gyldigFornavn = validerFelt(fornavn, fornavnRegex);
});

fornavn.addEventListener("blur", function(){
    gyldigFornavn = validerFelt(fornavn, fornavnRegex);
});

etternavn.addEventListener("keyup", function() {
    gyldigEtternavn = validerFelt(etternavn, etternavnRegex);
});

etternavn.addEventListener("blur", function() {
    gyldigEtternavn = validerFelt(etternavn, etternavnRegex);
});

mobil.addEventListener("keyup", function() {
    gyldigMobil = validerFelt(mobil, mobilRegex);
});

mobil.addEventListener("blur", function() {
    gyldigMobil = validerFelt(mobil, mobilRegex);
});

passord.addEventListener("keyup", function() {
    gyldigPassord = validerPassord(passord.value, passordRegex);
});

passord.addEventListener('mouseover', function(){
	info.style.visibility = "visible";
});

passord.addEventListener('mouseout', function(){
	info.style.visibility = "hidden";
});


passordRepetert.addEventListener("keyup", function(){
    validerPassordRepetert(passordRepetert);
});

passordRepetert.addEventListener("blur", function(){
    validerPassordRepetert(passordRepetert);
});

for(let i = 0; i < kjonn.length; i++){
    kjonn[i].addEventListener("click", function(){
        if (kjonn[i].value !== null){
            gyldigKjonn = true;
        }else{
            gyldigKjonn = false;
        }
    });
}
/*
window.addEventListener('keyup', function () {
	if (gyldigFornavn && gyldigEtternavn && gyldigMobil && gyldigPassord && gyldigPassordRepetert && gyldigKjonn){
        knapp.disabled = false;
		
	} else {
		knapp.disabled = true;
	}
});
window.addEventListener('click', function () {
	if (gyldigFornavn && gyldigEtternavn && gyldigMobil && gyldigPassord && gyldigPassordRepetert && gyldigKjonn){
        knapp.disabled = false;
	} else {
		knapp.disabled = true;
	}
});
*/

function validerFelt(input, regex){
    let validBool;
    if(input.value === null || input.value === ""){
        if(document.activeElement === input){
            event.target.style.borderColor="#129FEA";
        }else{
            event.target.style.borderColor="#ddd";
        }
    }else if(!regex.test(input.value)){
        event.target.style.borderColor="red";
        validBool = false;
    }else{
        event.target.style.borderColor="#14df14";
        validBool = true;
    }
    return validBool;
}



function validerPassord(input, regex){
    let validBool;
    if(input === null || input === ""){
        event.target.style.borderColor="#ddd";
    }else if(!regex.test(input)){
        event.target.style.borderColor="red";
        validBool = false;
    }else if(regex.test(input) && input.length < 10){
        event.target.style.borderColor="yellow";
        validBool = true;
    }else{
        event.target.style.borderColor="#14df14";
        validBool = true;
    }
    return validBool;
}

function validerPassordRepetert(input){
    if((input.value === null && passord.value === null) || (input.value === "" && passord.value === "")){
        if (input === document.activeElement){
            event.target.style.borderColor="#129FEA";
        }else{
            event.target.style.borderColor="#ddd";
        }
        gyldigPassordRepetert = false;
    }else if(input.value !== passord.value){
        event.target.style.borderColor="red";
        gyldigPassordRepetert = false;
    }else{
        event.target.style.borderColor="#14df14";
        gyldigPassordRepetert = true;
    }
}