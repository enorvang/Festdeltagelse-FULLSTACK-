package utilities;


public class RegistreringsSkjema {
	private String fornavn = "";
	private String fornavnFeil = "";
	private String etternavn = "";
	private String etternavnFeil = "";
	private String mobil = "";
	private String mobilFeil = "";
	private String passord = "";
	private String passordFeil = "";
	private String passordRepetert = "";
	private String passordRepetertFeil = "";
	private String kjonn = "";
	private String kjonnFeil = "";
	
	public String getKjonnFeil() {
		return kjonnFeil;
	}
	public void setKjonnFeil(String kjonnFeil) {
		this.kjonnFeil = kjonnFeil;
	}
	public String getFornavnFeil() {
		return fornavnFeil;
	}
	public void setFornavnFeil(String fornavnFeil) {
		this.fornavnFeil = fornavnFeil;
	}
	public String getEtternavnFeil() {
		return etternavnFeil;
	}
	public void setEtternavnFeil(String etternavnFeil) {
		this.etternavnFeil = etternavnFeil;
	}
	public String getMobilFeil() {
		return mobilFeil;
	}
	public void setMobilFeil(String mobilFeil) {
		this.mobilFeil = mobilFeil;
	}
	public String getPassordFeil() {
		return passordFeil;
	}
	public void setPassordFeil(String passordFeil) {
		this.passordFeil = passordFeil;
	}
	public String getPassordRepetertFeil() {
		return passordRepetertFeil;
	}
	public void setPassordRepetertFeil(String passordRepetertFeil) {
		this.passordRepetertFeil = passordRepetertFeil;
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
	public String getPassord() {
		return passord;
	}
	public void setPassord(String passord) {
		this.passord = passord;
	}
	public String getPassordRepetert() {
		return passordRepetert;
	}
	public void setPassordRepetert(String passordRepetert) {
		this.passordRepetert = passordRepetert;
	}
	public String getKjonn() {
		return kjonn;
	}
	public void setKjonn(String kjonn) {
		this.kjonn = kjonn;
	}
	public String toString() {
		return fornavn + etternavn;
	}
}
