package com.examples.pof.annotations;

import java.io.Serializable;

import com.tangosol.io.pof.annotation.Portable;
import com.tangosol.io.pof.annotation.PortableProperty;

@Portable
public class PhoneNumber implements Serializable {

    private static final long serialVersionUID = -3349926092213194942L;

    // ----- constants -------------------------------------------------------

    public static final int ACCESS_CODE = 0;
    public static final int COUNTRY_CODE = 1;
    public static final int AREA_CODE = 2;
    public static final int LOCAL_NUMBER = 3;

    // ----- data members ---------------------------------------------------

    /**
     * The numbers used to access international or non-local calls.
     */
    @PortableProperty(ACCESS_CODE)
    private short m_nAccessCode;

    /**
     * The numbers used to designate a country in international calls.
     */
    @PortableProperty(COUNTRY_CODE)
    private short m_nCountryCode;

    /**
     * The numbers used indicate a geographic area within a country.
     */
    @PortableProperty(AREA_CODE)
    private short m_nAreaCode;

    /**
     * The local number.
     */
    @PortableProperty(LOCAL_NUMBER)
    private long m_lLocalNumber;

    // ----- constructors ---------------------------------------------------

    /**
     * Default constructor (necessary for PortableObject implementation).
     */
    public PhoneNumber()
    {
    }

    /**
     * Construct a Phone.
     *
     * @param nAccessCode   the numbers used to access international or
     *                      non-local calls
     * @param nCountryCode  the numbers used to designate a country
     * @param nAreaCode     the numbers used to indicate a geographical region
     * @param lLocalNumber  the local numbers
     */
    public PhoneNumber(short nAccessCode, short nCountryCode,
	    short nAreaCode, long lLocalNumber)
    {
	m_nAccessCode  = nAccessCode;
	m_nCountryCode = nCountryCode;
	m_nAreaCode    = nAreaCode;
	m_lLocalNumber = lLocalNumber;
    }

    // ----- accessors ------------------------------------------------------

    /**
     * Return the access code.
     *
     * @return the access code
     */
    public short getAccessCode()
    {
	return m_nAccessCode;
    }

    /**
     * Set the numbers used to access international or non-local calls.
     *
     * @param nAccessCode  the access code numbers
     */
    public void setAccessCode(short nAccessCode)
    {
	m_nAccessCode = nAccessCode;
    }

    /**
     * Return the country code.
     *
     * @return the country code
     */
    public short getCountryCode()
    {
	return m_nCountryCode;
    }

    /**
     * Set the country code.
     *
     * @param nCountryCode  the country code
     */
    public void setCountryCode(short nCountryCode)
    {
	m_nCountryCode = nCountryCode;
    }

    /**
     * Return the area code.
     *
     * @return the area code
     */
    public short getAreaCode()
    {
	return m_nAreaCode;
    }

    /**
     * Set the numbers used indicate a geographic area within a country.
     *
     * @param nAreaCode  the area code
     */
    public void setAreaCode(short nAreaCode)
    {
	m_nAreaCode = nAreaCode;
    }

    /**
     * Return the local or subscriber number.
     *
     * @return the local or subscriber number
     */
    public long getLocalNumber()
    {
	return m_lLocalNumber;
    }

    /**
     * Set the local or subscriber number.
     *
     * @param lLocalNumbeer  the local or subscriber number
     */
    public void setLocalNumber(long lLocalNumbeer)
    {
	m_lLocalNumber = lLocalNumbeer;
    }

    // ----- Object methods -------------------------------------------------

    /**
     * {@inheritDoc}
     */
    public boolean equals(Object oThat)
    {
	if (this == oThat)
	{
	    return true;
	}
	if (oThat == null)
	{
	    return false;
	}


	PhoneNumber that = (PhoneNumber) oThat;
	return getAccessCode()  == that.getAccessCode()  &&
		getCountryCode() == that.getCountryCode() &&
		getAreaCode()    == that.getAreaCode()    &&
		getLocalNumber() == that.getLocalNumber();
    }

    /**
     * {@inheritDoc}
     */
    public int hashCode()
    {
	return (int) ((long) getAreaCode() * 31L + getLocalNumber());
    }

    /**
     * {@inheritDoc}
     */
    public String toString()
    {
	return "+" + getAccessCode() + " " + getCountryCode() + " "
		+ getAreaCode()   + " " + getLocalNumber();
    }
}
