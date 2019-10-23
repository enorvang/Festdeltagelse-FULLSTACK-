package entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;


@Entity
@Table(schema="dat108oblig3", name="deltagere")
public class Deltager {
	
	@Id
	private String mobil;
	private String fornavn;
	private String etternavn;
	private String passordHash;
	private String kjonn;
	private String passordSalt;
	
	public Deltager() {
		
	}
	
	public Deltager(String fornavn, String etternavn, String mobil, String passordHash, String kjonn, String passordSalt) {
		this.fornavn = fornavn;
		this.etternavn = etternavn;
		this.mobil = mobil;
		this.passordHash = passordHash;
		this.kjonn = kjonn;
		this.passordSalt = passordSalt;
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


	public String getPassordHash() {
		return passordHash;
	}

	public void setPassordHash(String passordHash) {
		this.passordHash = passordHash;
	}

	public String getKjonn() {
		return kjonn;
	}


	public void setKjonn(String kjonn) {
		this.kjonn = kjonn;
	}
	
	public void setPassordSalt(String passordSalt) {
		this.passordSalt = passordSalt;
	}
	
	public String getPassordSalt() {
		return passordSalt;
	}


	@Override
	public String toString() {
		return "Deltager [fornavn=" + fornavn + ", etternavn=" + etternavn + ", mobil=" + mobil + ", kjonn=" + kjonn
				+ "]";
	}
	
	
}
