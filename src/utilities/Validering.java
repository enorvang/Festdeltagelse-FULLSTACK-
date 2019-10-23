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

	public static boolean erUnikMobil(String mobil1, String mobil2) {

		return mobil1 != mobil2;
	}

	public static boolean erLikePassord(String passord, String passordRepetert) {
		return passord.contentEquals(passordRepetert);
	}

	public static boolean erGyldigPassord(String passord) {
		return passord.matches("^(?=.*[0-9])(?=.*[a-zæøå])(?=.*[A-ZØÆÅ])(?=.*[!@#$%^&+=])(?=\\S+$).{8,}$");
	}

	public static boolean erGyldigKjonn(String kjonn) {
		return kjonn != null && !kjonn.isEmpty();
	}

	public static boolean erAlleGyldige(RegistreringsSkjema rs) {
		boolean gyldigInput = true;
		if (!erGyldigFornavn(rs.getFornavn())) {
			gyldigInput = false;
			rs.setFornavnFeil("Ugyldig fornavn");
		} else {
			rs.setFornavnFeil("");
		}
		if (!erGyldigEtternavn(rs.getEtternavn())) {
			gyldigInput = false;
			rs.setEtternavnFeil("Ugyldig etternavn");
		} else {
			rs.setEtternavnFeil("");
		}
		if (!erGyldigMobil(rs.getMobil())) {
			gyldigInput = false;
			rs.setMobilFeil("Ugyldig mobil");
		} else {
			rs.setMobilFeil("");
		}
		if (!erGyldigPassord(rs.getPassord())) {
			gyldigInput = false;
			rs.setPassordFeil("Ugyldig passord");
		} else {
			rs.setPassordFeil("");
		}
		if (!erLikePassord(rs.getPassord(), rs.getPassordRepetert())) {
			gyldigInput = false;
			rs.setPassordFeil("Passordene samsvarer ikke");
			rs.setPassordRepetertFeil("Passordene samsvarer ikke");
		} else {
			rs.setPassordRepetertFeil("");
		}
		if (rs.getKjonn() == null) {
			gyldigInput = false;
			rs.setKjonnFeil("Du må oppgi kjønn");
		} else {
			rs.setKjonnFeil("");
		}
		return gyldigInput;
	}
}
