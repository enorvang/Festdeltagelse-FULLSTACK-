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

fornavn.addEventListener("keyup", function() {
    let regex = /^[A-ZÆØÅ][A-ZÆØÅa-zæøå -]{2,19}$/;
    gyldigFornavn = validerFelt(fornavn.value, regex);
});

etternavn.addEventListener("keyup", function() {
    let regex = /^[A-ZÆØÅ][A-ZÆØÅa-zæøå-]{2,19}$/;
    gyldigEtternavn = validerFelt(etternavn.value, regex);
});

mobil.addEventListener("keyup", function() {
    let regex = /^[0-9]{8}$/;
    gyldigMobil = validerFelt(mobil.value, regex);
});


passord.addEventListener("keyup", function() {
	let regex = /^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#\$%\^&\*])(?=.{8,})$/;
    gyldigPassord = validerPassord(passord.value, regex);
});

passord.addEventListener('mouseover', function(){
	info.style.visibility = "visible";
});

passord.addEventListener('mouseout', function(){
	info.style.visibility = "hidden";
});

passordRepetert.addEventListener("keyup", function() {
    if((passordRepetert.value === null && passord.value === null) || (passordRepetert.value === "" && passord.value === "")){
        event.target.style.borderColor="initial";
    }
    else if(passordRepetert.value !== passord.value){
        event.target.style.borderColor="red";
        gyldigPassordRepetert = false;
    }else{
        event.target.style.borderColor="#14df14";
        gyldigPassordRepetert = true;
    }
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

function validerFelt(input, regex){
	let validBool;
    if(!regex.test(input)){
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
    if(!regex.test(input)){
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