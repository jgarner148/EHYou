/**
 * Class File for Person Object
 */

import java.io.Serializable;

public class Person implements Serializable {
	/**
	 * Persons first name
	 */
	protected String fname;
	/**
	 * Persons Last name
	 */
	protected String lname;
	/**
	 * Persons date of birth
	 */
	protected String dob;

	/**
	 * Constructor for Person
	 * @param fname Persons first name
	 * @param lname Persons Last name
	 * @param dob Persons date of birth
	 */
	public Person(String fname, String lname, String dob) {
		this.fname = fname;
		this.lname = lname;
		this.dob = dob;
	}
}
