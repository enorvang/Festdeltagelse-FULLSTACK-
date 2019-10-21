package entities;

import java.security.NoSuchAlgorithmException;

import javax.xml.bind.DatatypeConverter;

import utilities.Hashing;

public class Deltager {
	private String fornavn;
	private String etternavn;
	private String mobil;
	private String passordhash;
	private String passordsalt;
	private String kjonn;
	
	
	
	public Deltager(String fornavn, String etternavn, String mobil, String passord, String kjonn) throws NoSuchAlgorithmException {
		this.fornavn = fornavn;
		this.etternavn = etternavn;
		this.mobil = mobil;
		Hashing hash = new Hashing("SHA-256");
		hash.generateHashWithSalt(passord, hash.getSalt());
		passordhash = hash.getPasswordHashinHex();
		passordsalt = hash.getPasswordSalt();
		this.kjonn = kjonn;
	}


	public String getFornavn() {
		return fornavn;
	}


	public void setFornavn(String fornavn) {
		this.fornavn = fornavn;
	}


	public String getEtternavn() {
		return etternavn;
	}


	public void setEtternavn(String etternavn) {
		this.etternavn = etternavn;
	}


	public String getMobil() {
		return mobil;
	}


	public void setMobil(String mobil) {
		this.mobil = mobil;
	}


	public String getPassordhash() {
		return passordhash;
	}


	public String getPassordsalt() {
		return passordsalt;
	}


	public String getKjonn() {
		return kjonn;
	}


	public void setKjonn(String kjonn) {
		this.kjonn = kjonn;
	}


	@Override
	public String toString() {
		return "Deltager [fornavn=" + fornavn + ", etternavn=" + etternavn + ", mobil=" + mobil + ", kjonn=" + kjonn
				+ "]";
	}
	
	
}
