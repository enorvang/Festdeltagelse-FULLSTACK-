let fornavn = document.getElementById("fornavn")
let etternavn = document.getElementById("etternavn")
let mobil = document.getElementById("mobil")
let passord = document.getElementById("passord")
let passordRepetert = document.getElementById("passordRepetert")
let kjonn = document.getElementById("kjonn")
let form = document.getElementById("form")


fornavn.addEventListener("keyup", function() {
    let fnavn = fornavn.value;
    if(fnavn.length < 3){
        event.target.style.borderColor="red";
    }else if(fnavn.length >=3){
        event.target.style.borderColor="green";
    }
});