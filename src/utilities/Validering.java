package utilities;

public class Validering {

	public static boolean erGyldigFornavn(String fornavn) {

		if (fornavn == null) {
			return false;
		}
		return fornavn.matches("^[A-ZÆØÅ][A-ZÆØÅa-zæøå -]{2,19}");

	}

	public static boolean erGyldigEtternavn(String etternavn) {
		return etternavn.matches("^[A-ZÆØÅ][A-ZÆØÅa-zæøå-]{2,19}");
	}

	public static boolean erGyldigMobil(String mobil) {

		return mobil.matches("[0-9]{8}");
	}

	public static boolean erLikePassord(String passord, String passordRepetert) {
		return passord.contentEquals(passordRepetert);
	}

	public static boolean erGyldigPassord(String passord) {
//		return passord.matches("\\S{8,}");
		return passord.matches("^(?=.*[0-9])(?=.*[a-zæøå])(?=.*[A-ZØÆÅ])(?=.*[!@#$%^&+=])(?=\\S+$).{8,}$");
	}

	public static boolean erGyldigKjonn(String kjonn) {

		return kjonn != null && !kjonn.isEmpty();
	}
}
