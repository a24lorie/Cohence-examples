package com.examples.pof.annotations;

import java.io.Serializable;

import com.tangosol.io.pof.annotation.Portable;
import com.tangosol.io.pof.annotation.PortableProperty;
import com.tangosol.util.Base;

@Portable
public class ContactId implements Serializable 
{

    private static final long serialVersionUID = 1333795261035086576L;
    
    // ----- constants -------------------------------------------------------

    public static final int FIRSTNAME = 0;
    public static final int LASTNAME = 1;

    // ----- constructors ---------------------------------------------------
    /**
     * Default constructor (necessary for PortableObject implementation).
     */
    public ContactId()
    {
    }

    /**
     * Construct a contact key.
     *
     * @param sFirstName  first name
     * @param sLastName   last name
     */
    public ContactId(String sFirstName, String sLastName)
    {
	m_sFirstName = sFirstName;
	m_sLastName  = sLastName;
    }


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
     * Return the last name.
     *
     * @return the last name
     */
    public String getLastName()
    {
	return m_sLastName;
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

	ContactId that = (ContactId) oThat;
	return Base.equals(getFirstName(), that.getFirstName()) &&
		Base.equals(getLastName(),  that.getLastName());
    }


    /**
     * {@inheritDoc}
     */
    public int hashCode()
    {
	return (getFirstName() == null ? 0 : getFirstName().hashCode()) ^
		(getLastName() == null ? 0 : getLastName().hashCode());

    }

    /**
     * {@inheritDoc}
     */
    public String toString()
    {
	return getFirstName() + " " + getLastName();
    }

}
