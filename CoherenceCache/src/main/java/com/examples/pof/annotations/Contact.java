package com.examples.pof.annotations;

import java.io.Serializable;
import java.sql.Date;
import java.util.Iterator;
import java.util.Map;

import com.tangosol.io.pof.annotation.Portable;
import com.tangosol.io.pof.annotation.PortableProperty;

@Portable
public class Contact implements Serializable {

    private static final long serialVersionUID = 8461227026885885125L;

    // ----- constants -------------------------------------------------------

    /**
     * Approximate number of millis in a year ignoring things such as leap
     * years. Suitable for example use only.
     */
    public static final long MILLIS_IN_YEAR = 1000L * 60L * 60L * 24L * 365L;

    public static final int FIRSTNAME = 0;
    public static final int LASTNAME = 1;
    public static final int HOME_ADDRESS = 2;
    public static final int WORK_ADDRESS = 3;
    public static final int PHONE_NUMBERS = 4;
    public static final int BIRTH_DATE = 5;

    // ----- data members ---------------------------------------------------

    /**
     * First name.
     */
    @PortableProperty(FIRSTNAME)
    private String m_sFirstName;

    /**
     * Last name.
     */
    @PortableProperty(LASTNAME)
    private String m_sLastName;

    /**
     * Home address.
     */
    @PortableProperty(HOME_ADDRESS)
    private Address m_addrHome;

    /**
     * Work address.
     */
    @PortableProperty(WORK_ADDRESS)
    private Address m_addrWork;

    /**
     *  Maps phone number type (such as "work", "home") to PhoneNumber.
     */
    @PortableProperty(PHONE_NUMBERS)
    private Map m_mapPhoneNumber;

    /**
     * Birth Date.
     */
    @PortableProperty(BIRTH_DATE)
    private Date m_dtBirth;

    // ----- constructors ---------------------------------------------------

    /**
     * Default constructor (necessary for PortableObject implementation).
     */
    public Contact()
    {
    }

    /**
     * Construct Contact
     *
     * @param sFirstName      the first name
     * @param sLastName       the last name
     * @param addrHome        the home address
     * @param addrWork        the work address
     * @param mapPhoneNumber  map string number type (e.g. "work") to
     *                        PhoneNumber
     * @param dtBirth         date of birth
     */
    public Contact(String sFirstName, String sLastName, Address addrHome,
	    Address addrWork, Map mapPhoneNumber, java.sql.Date dtBirth)
    {
	m_sFirstName     = sFirstName;
	m_sLastName      = sLastName;
	m_addrHome       = addrHome;
	m_addrWork       = addrWork;
	m_mapPhoneNumber = mapPhoneNumber;
	m_dtBirth        = dtBirth;
    }

    // ----- accessors ------------------------------------------------------

    /**
     * Return the first name.
     *
     * @return the first name
     */
    public String getFirstName()
    {
	return m_sFirstName;
    }

    /**
     * Set the first name.
     *
     * @param sFirstName  the first name
     */
    public void setFirstName(String sFirstName)
    {
	m_sFirstName = sFirstName;
    }

    /**
     * Return the last name.
     *
     * @return the last name
     */
    public String getLastName()
    {
	return m_sLastName;
    }


    /**
     * Set the last name.
     *
     * @param sLastName  the last name
     */
    public void setLastName(String sLastName)
    {
	m_sLastName = sLastName;
    }

    /**
     * Return the home address.
     *
     * @return the home address
     */
    public Address getHomeAddress()
    {
	return m_addrHome;
    }

    /**
     * Set the home address.
     *
     * @param addrHome  the home address
     */
    public void setHomeAddress(Address addrHome)
    {
	m_addrHome = addrHome;
    }

    /**
     * Return the work address.
     *
     * @return the work address
     */
    public Address getWorkAddress()
    {
	return m_addrWork;
    }

    /**
     * Set the work address.
     *
     * @param addrWork  the work address
     */
    public void setWorkAddress(Address addrWork)
    {
	m_addrWork = addrWork;
    }

    /**
     * Get all phone numbers.
     *
     * @return a map of phone numbers
     */
    public Map getPhoneNumbers()
    {
	return m_mapPhoneNumber;
    }

    /**
     * Set the list of phone numbers.
     *
     * @param mapTelNumber  a map of phone numbers
     */
    public void setPhoneNumbers(Map mapTelNumber)
    {
	m_mapPhoneNumber = mapTelNumber;
    }

    /**
     * Get the date of birth.
     *
     * @return the date of birth
     */
    public Date getBirthDate()
    {
	return m_dtBirth;
    }

    /**
     * Set the date of birth.
     *
     * @param dtBirth  the date of birth
     */
    public void setBirthDate(Date dtBirth)
    {
	m_dtBirth = dtBirth;
    }

    /**
     * Get age.
     *
     * @return age
     */
    public int getAge()
    {
	return (int) ((System.currentTimeMillis() - m_dtBirth.getTime()) /
		MILLIS_IN_YEAR);
    }

    // ----- Object methods -------------------------------------------------

    /**
     * {@inheritDoc}
     */
    public String toString()
    {
	StringBuffer sb = new StringBuffer(getFirstName())
	.append(" ")
	.append(getLastName())
	.append("\nAddresses")
	.append("\nHome: ").append(getHomeAddress())
	.append("\nWork: ").append(getWorkAddress())
	.append("\nPhone Numbers");

	for (Iterator iter = m_mapPhoneNumber.entrySet().iterator();iter.hasNext(); )
	{
	    Map.Entry entry = (Map.Entry) iter.next();
	    sb.append("\n")
	    .append(entry.getKey()).append(": ").append(entry.getValue());
	}
	return sb.append("\nBirth Date: ").append(getBirthDate()).toString();
    }
}
