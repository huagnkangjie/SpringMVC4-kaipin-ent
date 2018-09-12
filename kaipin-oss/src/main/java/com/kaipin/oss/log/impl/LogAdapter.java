 
package com.kaipin.oss.log.impl;

import java.util.HashMap;
import java.util.Map;

import com.kaipin.oss.log.LogAPI;
import com.kaipin.oss.log.LogLevel;

 
 

public class LogAdapter implements LogAPI {

	/**   
	 * @param message
	 * @param logLevel  
	 * @see com.ketayao.ketacustom.log.LogAPI#log(java.lang.String, com.ketayao.ketacustom.log.LogLevel)  
	 */
	@Override
	public void log(String message, LogLevel logLevel) {
		log(message, null, logLevel);
	}

	/**   
	 * @param message
	 * @param objects
	 * @param logLevel  
	 * @see com.ketayao.ketacustom.log.LogAPI#log(java.lang.String, java.lang.Object[], com.ketayao.ketacustom.log.LogLevel)  
	 */
	@Override
	public void log(String message, Object[] objects, LogLevel logLevel) {
		
	}
	
	/**   
	 * @return  
	 * @see com.ketayao.ketacustom.log.LogAPI#getRootLogLevel()  
	 */
	@Override
	public LogLevel getRootLogLevel() {
		return LogLevel.ERROR;
	}

	/**   
	 * @return  
	 * @see com.ketayao.ketacustom.log.LogAPI#getCustomLogLevel()  
	 */
	@Override
	public Map<String, LogLevel> getCustomLogLevel() {
		return new HashMap<String, LogLevel>();
	}
}
