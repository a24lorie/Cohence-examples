package com.examples;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.tangosol.net.CacheFactory;
import com.tangosol.net.NamedCache;

public class HelloWorld {
    
    public static void main(String[] args) {
       //CacheFactory.ensureCluster();
       NamedCache cache = CacheFactory.getCache("jsf_state_cache");
  
       @SuppressWarnings("unchecked")
       Set<Entry<String,Map<String,String>>> keySet=cache.entrySet();
       
       Iterator<Entry<String, Map<String, String>>>  iterator=keySet.iterator();

       while(iterator.hasNext())
       { 
	   Entry<String, Map<String, String>> entryItem=iterator.next();
	   Map<String,String> viewsMap=entryItem.getValue();
	   Iterator<String> viewsIterator=viewsMap.keySet().iterator();
	   
	   System.out.println("---------------------------------------------------------------");
	   System.out.println("Session: " + (String)entryItem.getKey() + ", No views:" +viewsMap.size());

	   while(viewsIterator.hasNext())
	   { 
	       String viewId=viewsIterator.next();
	       System.out.print("\t viewId:" + viewId);
	       System.out.println("\t value:" + viewsMap.get(viewId));
	   }
       }
  
       //CacheFactory.shutdown();
    }
 }
