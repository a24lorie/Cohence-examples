package com.examples.loading;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Date;
import java.util.HashMap;
import java.util.Map;

import com.examples.pof.annotations.Address;
import com.examples.pof.annotations.Contact;
import com.examples.pof.annotations.ContactId;
import com.examples.pof.annotations.PhoneNumber;
import com.tangosol.net.CacheFactory;
import com.tangosol.net.NamedCache;

public class LoaderExample {
    // ----- static methods -------------------------------------------------

    /**
     * Load contacts from a CSV file, then populate the cache with the data.
     * <p/>
     * The first argument is the name of the datafile to load. The second
     * argument will be treated as the name of the cache to populate.
     * <p/>
     * usage: [file name] [cache name]
     *
     * @param asArg  command line arguments
     *
     * @throws IOException if file cannot be read
     */
    public static void main(String[] asArg)throws IOException
    {
	String sFile  = asArg.length > 0 ? asArg[0] : DataGenerator.FILENAME;
	String sCache = asArg.length > 1 ? asArg[1] : CACHENAME;

	System.out.println("input file: " + sFile);
	System.out.println("cache name: " + sCache);

	new LoaderExample().load(new FileInputStream(sFile),CacheFactory.getCache(sCache));

	CacheFactory.shutdown();
    }

    /**
     * Load contacts from the inputstream and insert them into the cache.
     *
     * @param in     stream containing contacts
     * @param cache  target cache
     *
     * @throws IOException on read error
     */
    public void load(InputStream in, NamedCache cache)throws IOException
    {
	BufferedReader          reader    = new BufferedReader(new InputStreamReader(in));
	Map<ContactId, Contact> mapBatch  = new HashMap<ContactId, Contact>(BATCH_SIZE);
	String sRecord;
	int cRecord = 0;

	System.out.println("------LoaderExample begins------");
	while ((sRecord = reader.readLine()) != null)
	{
	    // parse record
	    String[] asPart = sRecord.split(",");
	    int ofPart = 0;
	    String sFirstName = asPart[ofPart++];
	    String sLastName = asPart[ofPart++];
	    ContactId id = new ContactId(sFirstName, sLastName);
	    Address addrHome = new Address(
		    /*streetline1*/ asPart[ofPart++],
		    /*streetline2*/ asPart[ofPart++],
		    /*city*/ 	    asPart[ofPart++],
		    /*state*/ 	    asPart[ofPart++],
		    /*zip*/ 	    asPart[ofPart++],
		    /*country*/     asPart[ofPart++]);
	    Address addrWork = new Address(
		    /*streetline1*/ asPart[ofPart++],
		    /*streetline2*/ asPart[ofPart++],
		    /*city*/ 	    asPart[ofPart++],
		    /*state*/ 	    asPart[ofPart++],
		    /*zip*/ 	    asPart[ofPart++],
		    /*country*/     asPart[ofPart++]);
	    Map mapTelNum = new HashMap();
	    for (int c = asPart.length - 1; ofPart < c; )
	    {
		mapTelNum.put(/*type*/ asPart[ofPart++], 
				new PhoneNumber(/*access code*/ Short.parseShort(asPart[ofPart++]),
						/*country code*/ Short.parseShort(asPart[ofPart++]),
						/*area code*/ Short.parseShort(asPart[ofPart++]),
						/*local num*/ Integer.parseInt(asPart[ofPart++])));
	    }
	    Date dtBirth = new Date(Long.parseLong(asPart[ofPart]));

	    // Construct Contact and add to batch
	    Contact con1 = new Contact(sFirstName, sLastName, addrHome,addrWork, mapTelNum, dtBirth);

	    System.out.println(con1);
	    mapBatch.put(id, con1);
	    ++cRecord;
	    if (cRecord % BATCH_SIZE == 0)
	    {
		// load batch
		cache.putAll(mapBatch);
		mapBatch.clear();
		System.out.print('.');
		System.out.flush();
	    }
	}
	if (!mapBatch.isEmpty())
	{
	    // load final batch
	    cache.putAll(mapBatch);
	}
	System.out.println("Added " + cRecord + " entries to cache");
	System.out.println("------LoaderExample completed------");
    }

    /**
     * Read a single contact from the supplied stream.
     *
     * @param reader  the stream from which to read a contact
     *
     * @return the contact or null upon reaching end of stream
     *
     * @throws IOException  on read error
     */
    public Contact readContact(BufferedReader reader)throws IOException
    {
	String sRecord = reader.readLine();
	if (sRecord == null)
	{
	    return null;
	}

	String[] asPart     = sRecord.split(",");
	int      ofPart     = 0;
	String   sFirstName = asPart[ofPart++];
	String   sLastName  = asPart[ofPart++];

	Date dtBirth = Date.valueOf(asPart[ofPart++]);

	Address                  addrHome   = new Address(
		/*streetline1*/ asPart[ofPart++],
		/*streetline2*/ asPart[ofPart++],
		/*city*/        asPart[ofPart++],
		/*state*/       asPart[ofPart++],
		/*zip*/         asPart[ofPart++],
		/*country*/     asPart[ofPart++]);
	Address                  addrWork   = new Address(
		/*streetline1*/ asPart[ofPart++],
		/*streetline2*/ asPart[ofPart++],
		/*city*/        asPart[ofPart++],
		/*state*/       asPart[ofPart++],
		/*zip*/         asPart[ofPart++],
		/*country*/     asPart[ofPart++]);
	Map<String, PhoneNumber> mapTelNum  = new HashMap<String, PhoneNumber>();

	for (int c = asPart.length; ofPart < c; )
	{
	    mapTelNum.put(/*type*/ asPart[ofPart++],
		    new PhoneNumber(
			    /*access code*/  Short.parseShort(asPart[ofPart++]),
			    /*country code*/ Short.parseShort(asPart[ofPart++]),
			    /*area code*/    Short.parseShort(asPart[ofPart++]),
			    /*local num*/    Long.parseLong(asPart[ofPart++])));
	}

	return  new Contact(sFirstName, sLastName, addrHome,
		addrWork, mapTelNum, dtBirth);
    }


    // ----- constants ------------------------------------------------------

    /**
     * Default cache name.
     */
    public static final String CACHENAME = "contacts";

    /**
     * The maximum number of contacts to load at a time.
     */
    private static final int BATCH_SIZE = 1024;

}
