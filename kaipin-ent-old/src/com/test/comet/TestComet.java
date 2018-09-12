package com.test.comet;

 
import org.comet4j.core.CometContext;
import org.comet4j.core.CometEngine;

import com.common.Constant;

public class TestComet {

	public static void main(String[] args) {
		CometEngine engine = CometContext.getInstance().getEngine();
        // 参数的意思：通过什么频道（CHANNEL1）发送什么数据（number1++），前台可用可用频道的值（result1）来获取某频道发送的数据
        engine.sendToAll("test", 111);
	}

}
