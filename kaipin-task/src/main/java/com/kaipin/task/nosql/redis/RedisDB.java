package com.kaipin.task.nosql.redis;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPool;
 
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

public class RedisDB    implements InitializingBean, ApplicationContextAware,DisposableBean{
	private ShardedJedisPool shardedJedispool;
	private JedisPool jedisPool;
	
	private  	GenericObjectPoolConfig poolConfig;
	
	private List<NodeInfo> nodes;
	private int poolMaxTotal=10;
	private long poolMaxWaitMillis=2000;
	private String auth;
	public static final String mode_single = "single";
	public static final String mode_cluster = "cluster";
	public static final String mode_shared = "shared";
	/**
	 * single|cluster|shared
	 */
	private String mode = mode_single;
	
	private JedisCluster jc; 

	 
	public RedisDB() {
	 
	}
	
	public void startSharedPool() {
 
		
		    if(poolConfig==null){
		    	throw new NullPointerException("JedisPoolConfig is null");
		    }
		    
		    List<JedisShardInfo> jedisClusterNode = new ArrayList<JedisShardInfo>();
		    for(int i = 0; i < nodes.size(); i ++)
		    {
		    	NodeInfo node = nodes.get(i);
		    	
		    	JedisShardInfo jedisShardInfo = new JedisShardInfo(node.getHost(), node.getPort(),node.getName());
 
		    	if(this.auth != null)
		    		jedisShardInfo.setPassword(this.auth);
		       	jedisClusterNode.add(jedisShardInfo);
		    }
		    
		    shardedJedispool = new ShardedJedisPool(poolConfig, jedisClusterNode);
		    
	   
	  }
	
	public void startSingleNode()
	{
	    if(poolConfig==null){
	    	throw new NullPointerException("JedisPoolConfig is null");
	    }
	    
		NodeInfo node = nodes.get(0);
		jedisPool = new JedisPool(poolConfig,node.getHost(), node.getPort(), (int)poolMaxWaitMillis  ,this.auth);
		 
 
	}
	 public void startPoolClusterPools() {
		    if(poolConfig==null){
		    	throw new NullPointerException("JedisPoolConfig is null");
		    }
		    
		    Set<HostAndPort> jedisClusterNode = new HashSet<HostAndPort>();
		    for(int i = 0; i < nodes.size(); i ++)
		    {
		    	NodeInfo node = nodes.get(i);
		    	HostAndPort hostAndPort = new HostAndPort(node.getHost(), node.getPort());
		    	
		    	jedisClusterNode.add(hostAndPort);
		    }
		  
		    
		    jc = new JedisCluster(jedisClusterNode, poolConfig);
		    
 
		 
		  }
		  
	
	public Jedis getRedis()
	{
		 Jedis jedis = jedisPool.getResource();
		 if(auth != null)
			 jedis.auth(this.auth);
		 return jedis;
	}
	public ShardedJedis getSharedRedis()
	{
		 ShardedJedis jedis = shardedJedispool.getResource();
		 
			 
		 return jedis;
	}
	public JedisCluster geJedisCluster()
	{
		 
		return jc;
	}
	public void releaseSharedRedis(ShardedJedis redis) throws IOException
	{
		redis.close();
	}
	
	public void releaseRedis(Jedis redis) throws IOException
	{
		redis.close();
	}
	
	public void close()
	{
		if(shardedJedispool != null)
			this.shardedJedispool.destroy();
		if(jc != null)
			try {
				jc.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		if(jedisPool != null)
			jedisPool.destroy();
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		if(getMode().equals(mode_shared))
		{
			this.startSharedPool();
		}
		else if(getMode().equals(mode_cluster))
		{
			this.startPoolClusterPools();
		}
		else if(getMode().equals(mode_single))
		{
			this.startSingleNode();
		}
		
	}

	public int getPoolMaxTotal() {
		return poolMaxTotal;
	}

	public void setPoolMaxTotal(int poolMaxTotal) {
		this.poolMaxTotal = poolMaxTotal;
	}

	public long getPoolMaxWaitMillis() {
		return poolMaxWaitMillis;
	}

	public void setPoolMaxWaitMillis(long poolMaxWaitMillis) {
		this.poolMaxWaitMillis = poolMaxWaitMillis;
	}

	public String getMode() {
		return mode;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}

	public String getAuth() {
		return auth;
	}

	public void setAuth(String auth) {
		this.auth = auth;
	}

	public JedisPool getJedisPool() {
		return jedisPool;
	}

	public void setJedisPool(JedisPool jedisPool) {
		this.jedisPool = jedisPool;
	}

	
	
	
	
	
	
	
	
	public GenericObjectPoolConfig getPoolConfig() {
		return poolConfig;
	}

	public void setPoolConfig(GenericObjectPoolConfig poolConfig) {
		this.poolConfig = poolConfig;
	}

	public List<NodeInfo> getNodes() {
		return nodes;
	}

	public void setNodes(List<NodeInfo> nodes) {
		this.nodes = nodes;
	}

	@Override
	public void destroy() throws Exception {
		 
		close();
	}

	@Override
	public void setApplicationContext(ApplicationContext arg0) throws BeansException {
	 
		
	}
	
	
	
	

}
