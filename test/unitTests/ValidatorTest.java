package unitTests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import utilities.Validering;

class ValidatorTest {

	@Test
	public void validFirstNameShouldBeOk() {
        assertTrue(Validering.erGyldigFornavn("Aaa"));
        assertTrue(Validering.erGyldigFornavn("Æøå"));
        assertTrue(Validering.erGyldigFornavn("Ææææææææææææææææ"));
        assertTrue(Validering.erGyldigFornavn("Ken-gunnar"));
        assertTrue(Validering.erGyldigFornavn("Ken Gunnar"));
        assertTrue(Validering.erGyldigFornavn("KUUUUUUUUULT")); //Bør fikse regex slik at den ikke godtar kun store bokstaver, men samtidig godtar dobbeltnavn
    }
	
	@Test
	public void shortFirstNameShouldNotBeOk() {
		assertFalse(Validering.erGyldigFornavn(null));
		assertFalse(Validering.erGyldigFornavn("A"));
		assertFalse(Validering.erGyldigFornavn("AB"));
	}
	
	@Test
	public void firstNameInvalidCharsShouldNotBeOk() {
		assertFalse(Validering.erGyldigFornavn("@gunnar"));
		assertFalse(Validering.erGyldigFornavn("Esp£n"));
	}
	
	@Test
	public void firstNameShouldStartWithCapitalLetter() {
		assertFalse(Validering.erGyldigFornavn("espen"));
	}
	
	@Test
	public void validLastNameShouldBeOk() {
		assertTrue(Validering.erGyldigEtternavn("Aaa"));
        assertTrue(Validering.erGyldigEtternavn("Æøå"));
        assertTrue(Validering.erGyldigEtternavn("Ææææææææææææææææ"));
        assertTrue(Validering.erGyldigEtternavn("D-generate"));
        assertTrue(Validering.erGyldigEtternavn("KUUUUUUUUULT"));
	}
	
	@Test
	public void shortLastNameShouldNotBeOk() {
		assertFalse(Validering.erGyldigFornavn(null));
		assertFalse(Validering.erGyldigFornavn("A"));
		assertFalse(Validering.erGyldigFornavn("AB"));
	}
	
	@Test
	public void lastNameInvalidCharsShouldNotBeOk() {
		assertFalse(Validering.erGyldigFornavn("@norvang"));
		assertFalse(Validering.erGyldigFornavn("Norv@ng"));
	}
	
	@Test
	public void lastNameShouldStartWithCapitalLetter() {
		assertFalse(Validering.erGyldigFornavn("norvang"));
	}
	
	@Test
	public void phoneNumberLessThanEightDigitsShouldNotBeOk() {
		assertFalse(Validering.erGyldigMobil("1234567"));
	}
	
	@Test 
	public void phoneNumberMoreThanEightDigitsShouldNotBeOk() {
		assertFalse(Validering.erGyldigMobil("123456789"));
	}
	
	@Test
	public void invalidCharsPhoneNumberShouldNotBeOk() {
		assertFalse(Validering.erGyldigMobil("E1234567"));
	}
	
	@Test
	public void phoneNumberEightDigitsShouldBeOk() {
		assertTrue(Validering.erGyldigMobil("12345678"));
	}
	
	@Test
	public void passwordLessThanEightCharactersShouldNotBeOk() {
		assertFalse(Validering.erGyldigPassord("passord"));
	}
	
	@Test
	public void passwordWithoutSpecialCharacterShouldNotBeOk() {
		assertFalse(Validering.erGyldigPassord("Etg0dtpassord"));
	}
	
	@Test
	public void passwordWithoutDigitShouldNotBeOk() {
		assertFalse(Validering.erGyldigPassord("@£$Verygoodpassword"));
	}
	
	@Test 
	public void passwordWithoutUppercaseLetterShouldNotBeOk() {
		assertFalse(Validering.erGyldigPassord("@123passord"));
	}

	@Test
	public void passwordWithoutLowercaseLetterShouldNotBeOk() {
		assertFalse(Validering.erGyldigPassord("@123PASSORD"));
	}
	
	@Test
	public void passwordWithWhitespaceShouldNotBeOk() {
		assertFalse(Validering.erGyldigPassord("@123Pass ord"));
	}
	
	@Test
	public void passwordShouldBeOk() {
		assertTrue(Validering.erGyldigPassord("!123Passord"));
	}
	
	@Test
	public void equalPasswordsShouldBeOk() {
		assertTrue(Validering.erLikePassord("@123Passord", "@123Passord"));
	}
	
	@Test 
	public void differentPasswordsShouldNotBeOk() {
		assertFalse(Validering.erLikePassord("@123Passord", "@123passord"));
	}
	
	@Test
	public void emptyKjonnShouldNotBeOk() {
		assertFalse(Validering.erGyldigKjonn(""));
		assertFalse(Validering.erGyldigKjonn(null));
	}
	
	@Test
	public void correctKjonnShouldBeOk() {
		assertTrue(Validering.erGyldigKjonn("mann"));
		assertTrue(Validering.erGyldigKjonn("dame"));
	}
}
