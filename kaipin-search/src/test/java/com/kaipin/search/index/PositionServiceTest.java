package com.kaipin.search.index;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.lucene.search.Query;
import org.apache.lucene.search.Sort;
import org.apache.lucene.search.SortField;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.kaipin.search.JUnitBase;
import com.kaipin.search.constant.Lucene;
import com.kaipin.search.core.dimension.Position;
import com.kaipin.search.core.index.IndexHolder;
import com.kaipin.search.core.index.SearchHelper;
import com.kaipin.search.core.query.QueryFactory;
import com.kaipin.search.core.query.WebBuildQueryFactory;
import com.kaipin.search.repository.dao.IndexBuildDao;

public class PositionServiceTest extends JUnitBase {
	
 
	
	@Autowired @Qualifier("positionInfoDao")
private IndexBuildDao indexBuildDao;
	
	
	//@Test
	public void selectByPrimaryKey(){
		
		System.out.println(indexBuildDao.selectByPrimaryKey("0000927f-4d92-40d9-8b3f-c323e830e21c"));
		
	}
	
	

	@Test
	public void findPosition() {
		try {
			IndexHolder indexHolder = IndexHolder.init(Lucene.getLuceneDir());

			String path = Lucene.IndexType.position.getPath();

			String keyword = "销售";
		
			QueryFactory queryFactory = new WebBuildQueryFactory();

			Map<String, Object> map = new HashMap<>();

			map.put(Position.TITLE, keyword);
			
			//map.put(Position.LOCATION , Arrays.asList("801", "551"));

		//	map.put(Position.INDUSTRY , "17000200");

			Query query = queryFactory.createPositionQuery(map, 1.f);// SearchHelper.makeQuery(Position.POSITION_NAME,
																		// keyword,
																		// 1.f);

			System.out.println(query.toString());

			int count = indexHolder.count(path, query, null);

			System.out.println("count:" + count);

			Sort sort = new Sort(new SortField(Position.TIME, SortField.Type.LONG, true));

			
			List<String> list = (List<String>) indexHolder.find(path, query, null, null, 1, 10);
			

			StringBuffer s = new StringBuffer();
			for (String str : list) {
				s.append("\'" + str + "\',");

			}
			
			System.out.println(s.toString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// List<Long> ids =( List<Long>) holder.find(Post.class, query, null,
		// null, 1, 10);

	}
}
