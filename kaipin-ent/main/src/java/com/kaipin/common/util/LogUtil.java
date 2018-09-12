package com.kaipin.common.util;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * 获取详细的日志文件信息
 * @author Mr-H
 *
 */
public class LogUtil {
	
	public static String getTrace(Throwable t) {
        StringWriter stringWriter= new StringWriter();
        PrintWriter writer= new PrintWriter(stringWriter);
        t.printStackTrace(writer);
        StringBuffer buffer= stringWriter.getBuffer();
        return buffer.toString();
    }
}
