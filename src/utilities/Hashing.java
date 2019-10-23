/**
 * 
 */
package utilities;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.xml.bind.DatatypeConverter;

/**
 * @author tdoy
 * @since 14.08.2019
 */
public class Hashing {
	
	private int iteration = 0;
	private String hashAlgorithm = "SHA-1";			// default is SHA-1
	
	private String passwordHash;
	private byte[] passwordSalt;
	
	// Hashing Algorithms available in Java Security
	public static final String MD5 = "MD5";			// 16 bytes (16*8 = 128bits)
	public static final String SHA1 = "SHA-1";		// 20 bytes (160bits)
	public static final String SHA256 = "SHA-256";	// 32 bytes (256bits)
	public static final String SHA384 = "SHA-384";	// 48 bytes (384bits)
	public static final String SHA512 = "SHA-512";	// 64 bytes (512bits)
	
	public Hashing() {
		// empty constructor to be used with generateHashWithSaltAndIteration method
	}
	
	/**
	 * @param hashAlgorithm (e.g. use PasswordHash.SHA1 to specify the hash algorithm)
	 */
	public Hashing(String hashAlgorithm) {
		this.hashAlgorithm = hashAlgorithm;
	}

	/**
	 * Low password security
	 * @param password
	 * @throws NoSuchAlgorithmException
	 */
	public String generateHashWithoutSalt(final String password) throws NoSuchAlgorithmException {
		
		MessageDigest md = MessageDigest.getInstance(hashAlgorithm);
		byte[] passbytes = password.getBytes();
		md.update(passbytes);											// pass the password to the hash function
		byte[] passhash = md.digest();									// obtain the hash value of the password
		
		String hexOfHash = DatatypeConverter.printHexBinary(passhash); 	// convert into hex value
		
		passwordHash = hexOfHash;
		
		return hexOfHash;
		
	}
	
	/**
	 * Medium password security - strong against brute force, dictionary, and rainbow attacks
	 * @param password
	 * @param salt Adding salt disallows 2 passwords to be the same and increases weak password's security (length and randomness)
	 * @throws NoSuchAlgorithmException
	 */
	public void generateHashWithSalt(final String password, byte[] salt) throws NoSuchAlgorithmException {
		
		MessageDigest md = MessageDigest.getInstance(hashAlgorithm);
		md.update(salt); 						// pass the salt into the update method
		byte[] passbytes = password.getBytes();
		byte[] passhash = md.digest(passbytes);	// pass the password to the digest method to finally obtain the hash value of the password + salt

		String hexOfHash = DatatypeConverter.printHexBinary(passhash);	// passhash is in decimal format - convert into hex
		
		passwordHash = hexOfHash;
	}
	
	/**
	 * Advanced password security - strong against brute force, dictionary, and rainbow attacks
	 * We use the Password-Based Key Derivation Function (PBKDF) specially designed to slow down hash computation
	 * without destroying usability.
	 * This is used to harden the password hashing algorithm - iteration slows down the 
	 * hashing computation steps and salt disallows 2 passwords to be the same and increases weak password's security (length and randomness)
	 * Make sure the salt and the hashed password are stored in a persistent storage for each user
	 * @param password
	 * @param salt 
	 * @param keylength in bits (e.g. 128, 256, 512)
	 * @param iteration number of iteration (int) to slow down CPU computations of the hash
	 * @throws NoSuchAlgorithmException 
	 * @throws InvalidKeySpecException 
	 */
	public void generateHashWithSaltAndIteration(final String password, byte[] salt, int keylength, int iteration) throws NoSuchAlgorithmException, InvalidKeySpecException {
		
		char[] passchar = password.toCharArray();
		
		PBEKeySpec pks = new PBEKeySpec(passchar, salt, iteration, keylength);
		SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
		byte[] keyhash = skf.generateSecret(pks).getEncoded();
		
		passwordHash = DatatypeConverter.printHexBinary(keyhash);
		
	}
	
	/**
	 * 
	 * @param password
	 * @param hashedPassword
	 * @return true or false value indicating that newly hashed password matches the stored hashed value
	 * @throws NoSuchAlgorithmException
	 */
	public boolean validatePasswordWithoutSalt(final String password, final String hashedPassword) throws NoSuchAlgorithmException {
		
		generateHashWithoutSalt(password);								// call the generate method
		
		return passwordHash.equalsIgnoreCase(hashedPassword);	// compare the hashed password and the plaintext password
		
	}
	
	/**
	 * 
	 * @param password
	 * @param salt
	 * @return true or false value indicating that newly hashed password matches the stored hashed value
	 * @throws NoSuchAlgorithmException 
	 */
	public boolean validatePasswordWithSalt(final String password, final String salt, final String hashedPassword) throws NoSuchAlgorithmException {
		
		byte[] saltbytes = DatatypeConverter.parseHexBinary(salt);		// convert to hex format
		
		generateHashWithSalt(password, saltbytes);						// call the generate method
		
		boolean equal = passwordHash.equalsIgnoreCase(hashedPassword);	// compare the hashed password and the plaintext password
		
		return equal;		
		
	}
	
	/**
	 * 
	 * @param password
	 * @param salt
	 * @return true or false value indicating that newly hashed password matches the stored hashed value
	 * @throws InvalidKeySpecException 
	 * @throws NoSuchAlgorithmException 
	 */
	public boolean validatePasswordWithSaltAndIteration(final String password, final String salt, final String hashedPassword, int keylength, int iteration) throws NoSuchAlgorithmException, InvalidKeySpecException {
		
		byte[] saltbytes = DatatypeConverter.parseHexBinary(salt);	
		
		generateHashWithSaltAndIteration(password, saltbytes, keylength, iteration);	// call the generate method
		
		boolean equal = passwordHash.equalsIgnoreCase(hashedPassword);	// compare the hashed password and the plaintext password
		
		return equal;
	}
	
	/**
	 * 
	 * @return salt as a byte array
	 */
	public byte[] getSalt() {
		
		//Always use a SecureRandom generator
	    SecureRandom sr;
	    byte[] salt = new byte[16];			//Create 128bits salt
	    
		try {
			sr = SecureRandom.getInstance("SHA1PRNG");
		    
		    sr.nextBytes(salt);					//Get a random salt
		} catch (NoSuchAlgorithmException e) {

			e.printStackTrace();
		}
		
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

	/**
	 * 
	 * @return iteration
	 */
	public int getIteration() {
		return iteration;
	}

	/**
	 * @return the hashAlgorithm
	 */
	public String getHashAlgorithm() {
		return hashAlgorithm;
	}

}
