package com.tedwon.pilots.cache;

import com.tangosol.net.CacheFactory;
import com.tangosol.net.NamedCache;

/**
 * Description.
 *
 * @author Ted Won
 * @since 1.0
 */
public class ProductSelectCacheManagerImpl<K, V> extends CacheManager<K, V> {

    public Cache<K, V> getCache(String cacheName) {

        return getCache(cacheName, Cache.Products.EHCACHE);

    }

    public Cache<K, V> getCache(String cacheName, Cache.Products cacheProductName) {

        Cache cache = null;

        if (Cache.Products.COHERENCE.equals(cacheProductName)) {
            NamedCache cohNamedCache = CacheFactory.getCache(cacheName);
            cache = new CoherenceCacheImpl<K, V>(cohNamedCache);
        } else if (Cache.Products.EHCACHE.equals(cacheProductName)) {

        } else if (Cache.Products.OSCACHE.equals(cacheProductName)) {

        }

        return cache;
    }

    /**
     * Adds a {@link Cache} to the CacheManager.
     * <p/>
     *
     * @param cache
     */
    @Override
    void addCache(Cache<K, V> kvCache) throws CacheException {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    /**
     * Checks whether a cache of type ehcache exists.
     * <p/>
     *
     * @param cacheName the cache name to check for
     * @return true if it exists
     */
    @Override
    boolean cacheExists(String cacheName) {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    /**
     * Remove a cache from the CacheManager. The cache is disposed of.
     *
     * @param cacheName the cache name
     */
    @Override
    public void removeCache(String cacheName) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

}
