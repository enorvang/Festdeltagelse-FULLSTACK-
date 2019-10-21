package utilities;

public class Validering {

	public static boolean erGyldigFornavn(String fornavn) {

		if (fornavn == null) {
			return false;
		}
		return fornavn.matches("^[A-Z���][A-Z���a-z��� -]{2,19}");

	}

	public static boolean erGyldigEtternavn(String etternavn) {
		return etternavn.matches("^[A-Z���][A-Z���a-z���-]{2,19}");
	}

	public static boolean erGyldigMobil(String mobil) {

		return mobil.matches("[0-9]{8}");
	}

	public static boolean erLikePassord(String passord, String passordRepetert) {
		return passord.contentEquals(passordRepetert);
	}

	public static boolean erGyldigPassord(String passord) {
//		return passord.matches("\\S{8,}");
		return passord.matches("^(?=.*[0-9])(?=.*[a-z���])(?=.*[A-Z���])(?=.*[!@#$%^&+=])(?=\\S+$).{8,}$");
	}

	public static boolean erGyldigKjonn(String kjonn) {

		return kjonn != null && !kjonn.isEmpty();
	}
}
