package com.tedwon.pilots.cache;

/**
 * Description.
 *
 * @author Ted Won
 * @since 1.0
 */
public class CacheManagerImpl<K, V> extends CacheManager<K, V> {

    public Cache<K, V> getCache(String cacheName) {

        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    void addCache(Cache<K,V> cache) throws CacheException
    {
	// TODO Auto-generated method stub
	
    }

    @Override
    boolean cacheExists(String cacheName)
    {
	// TODO Auto-generated method stub
	return false;
    }

    @Override
    public void removeCache(String cacheName)
    {
	// TODO Auto-generated method stub
	
    }
}
