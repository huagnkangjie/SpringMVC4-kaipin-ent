package test.resources;

import java.io.IOException;

import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

public class ResolverTest {

	public static void main(String[] args) throws IOException {
		ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
		//①加载所有类包com.baobaotao（及子孙包）下的以xml为后缀的资源
		Resource resources[] =resolver.getResources("classpath*:*.xml");
		for(Resource resource:resources){
			System.out.println(resource.getDescription());
		}
	}
}
