package test.threadpool.pool;


import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class TestProp {
    private String fileName="/test/threadpool/pool/task.properties";//这里是指放在classes下，如果有包的话，前面加包名即可。例：/com/web/db.properties
    

    public  void  getConn(){
        Properties p = new Properties();
		try {
			InputStream in = TestProp.class.getResourceAsStream(fileName);//这里有人用new FileInputStream(fileName),不过这种方式找不到配置文件。有人说是在classes下，我调过了，不行。
		    p.load(in);
		    System.out.println(""+p.get("task.count"));
		    in.close();
		} catch (IOException ex) {
		    ex.printStackTrace();
		}
    }
    
    public static void main(String[] args) {
    	TestProp t = new TestProp();
    	t.getConn();
	}
}