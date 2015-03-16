Starting cache on console:
	java -cp C:\OracleCoherence\coherence\lib\coherence.jar -Dtangosol.coherence.ttl=4 -Dtangosol.coherence.distributed.localstorage=false -Dtangosol.coherence.ttl=0 -Dtangosol.coherence.cluster=alb_localhost -Dtangosol.coherence.clusterport=10099 -Dtangosol.coherence.clusteraddress=224.0.1.10 com.tangosol.net.DefaultCacheServer

Verifing cache
	java -classpath COHERENCE_HOME\coherence.jar;COHERENCE_HOME\config;PATH_CLASES\classes -Dtangosol.coherence.cacheconfig=PATH_TO\contactsCache-config.xml -Dtangosol.coherence.distributed.localstorage=false -Dtangosol.coherence.ttl=0 -Dtangosol.coherence.cluster=alb_localhost -Dtangosol.coherence.clusterport=10099 -Dtangosol.coherence.clusteraddress=224.0.1.10 com.tangosol.net.CacheFactory
	
Query cache
	java -classpath COHERENCE_HOME\coherence.jar;PATH_TO\jline-0.9.9.jar;COHERENCE_HOME\config;PATH_CLASES\classes -Dtangosol.coherence.cacheconfig=PATH_TO\contactsCache-config.xml -Dtangosol.coherence.distributed.localstorage=false -Dtangosol.coherence.ttl=0 -Dtangosol.coherence.cluster=alb_localhost -Dtangosol.coherence.clusterport=10099 -Dtangosol.coherence.clusteraddress=224.0.1.10 com.tangosol.coherence.dslquery.QueryPlus	