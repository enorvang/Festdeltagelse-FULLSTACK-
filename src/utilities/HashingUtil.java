package utilities;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import javax.xml.bind.DatatypeConverter;

/**
 * Lånt fra Tosin
 */

public class HashingUtil {

	private String hashAlgorithm;

	private String passwordHash;
	private byte[] passwordSalt;

	// Hashing Algorithms available in Java Security
	public static final String MD5 = "MD5"; // 16 bytes (16*8 = 128bits)
	public static final String SHA1 = "SHA-1"; // 20 bytes (160bits)
	public static final String SHA256 = "SHA-256"; // 32 bytes (256bits)
	public static final String SHA384 = "SHA-384"; // 48 bytes (384bits)
	public static final String SHA512 = "SHA-512"; // 64 bytes (512bits)

	public HashingUtil(String hashAlgorithm) {
		this.hashAlgorithm = hashAlgorithm;
	}

	public void generateHashWithSalt(final String password, byte[] salt) {

		try {
			MessageDigest md = MessageDigest.getInstance(hashAlgorithm);
			md.update(salt); // pass the salt into the update method
			byte[] passbytes = password.getBytes();
			byte[] passhash = md.digest(passbytes); // pass the password to the digest method to finally obtain the hash
													// value of the password + salt
			String hexOfHash = DatatypeConverter.printHexBinary(passhash); // passhash is in decimal format - convert
																			// into hex
			passwordHash = hexOfHash;
		} catch (NoSuchAlgorithmException e) {

		}
	}

	public boolean validatePasswordWithSalt(final String password, final String salt, final String hashedPassword)
			throws NoSuchAlgorithmException {

		byte[] saltbytes = DatatypeConverter.parseHexBinary(salt); // convert to hex format

		generateHashWithSalt(password, saltbytes); // call the generate method

		boolean equal = passwordHash.equalsIgnoreCase(hashedPassword); // compare the hashed password and the plaintext
																		// password

		return equal;

	}

	/**
	 * Generates new salt
	 * 
	 * @return salt as a byte array
	 */
	public byte[] generateSalt() {

		// Always use a SecureRandom generator
		SecureRandom sr;
		byte[] salt = new byte[16]; // Create 128bits salt

		try {
			sr = SecureRandom.getInstance("SHA1PRNG");

			sr.nextBytes(salt); // Get a random salt
		} catch (NoSuchAlgorithmException e) {

			e.printStackTrace();
		}

		passwordSalt = salt;
		return salt;
	}

	/**
	 * 
	 * @return passwordHash in hexadecimal format
	 */
	public String getPasswordHashinHex() {
		return passwordHash;
	}

	/**
	 * 
	 * @return salt in hexadecimal format
	 */
	public String getPasswordSalt() {
		return DatatypeConverter.printHexBinary(passwordSalt);
	}
}
