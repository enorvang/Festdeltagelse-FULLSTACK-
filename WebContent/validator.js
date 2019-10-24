let fornavn = document.getElementById("fornavn")
let etternavn = document.getElementById("etternavn")
let mobil = document.getElementById("mobil")
let passord = document.getElementById("passord")
let passordRepetert = document.getElementById("passordRepetert")
let kjonn = document.getElementById("kjonn")
let form = document.getElementById("form")

fornavn.addEventListener("keyup", function() {
    let regex = /^[A-ZÆØÅ][A-ZÆØÅa-zæøå -]{2,19}/;
    validerFelt(fornavn.value, regex);
});

etternavn.addEventListener("keyup", function() {
    let regex = /^[A-ZÆØÅ][A-ZÆØÅa-zæøå-]{2,19}/;
    validerFelt(etternavn.value, regex);
});

mobil.addEventListener("keyup", function() {
    let regex = /[0-9]{8}/;
    validerFelt(mobil.value, regex);
});


passord.addEventListener("keyup", function() {
    validerPassord(passord.value);
});

let info = document.getElementById("info");
passord.addEventListener('mouseover', function(){
	info.style.visibility = "visible";
});

passord.addEventListener('mouseout', function(){
	info.style.visibility = "hidden";
});

passordRepetert.addEventListener("keyup", function() {
    if(passordRepetert.value !== passord.value){
        event.target.style.borderColor="red";
    }else{
        event.target.style.borderColor="green";
    }
});

function validerFelt(input, regex){
    if(!regex.test(input)){
        event.target.style.borderColor="red";
    }else{
        event.target.style.borderColor="green";
    }
}

function validerPassord(input){
    let regex = /^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#\$%\^&\*])(?=.{8,})/;
    if(!regex.test(input)){
        event.target.style.borderColor="red";
    }else if(regex.test(input) && input.length < 10){
        event.target.style.borderColor="yellow";
    }else{
        event.target.style.borderColor="green";
    }
}