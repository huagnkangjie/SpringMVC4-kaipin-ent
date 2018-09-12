package com.kaipin.search.constant;

import java.io.File;

import com.kaipin.search.common.config.impl.SystemConfig;
import com.kaipin.search.common.page.GenericDefaultPage;

/**
 * Lucene 路径
 * 
 * @author Tony
 * 
 *
 */
public class Lucene {

	
	
	public static final int DEFAULT_COUNT = GenericDefaultPage .DEF_COUNT;

	public static String getLuceneDir() {

		return SystemConfig.getInstance().getLucenePath();
	}

	public static String getLuceneIndexPath(String dir, String child) {

		if (!dir.endsWith(File.separator)) {
			dir += File.separator;
		}

		return dir + child;
	}

	public static String getIndexPath() {
		String dir = getLuceneDir();
		if (dir == null || dir.equals("")) {
			return "";
		}

		return getLuceneIndexPath(dir, IndexType.index.getPath());

	}

	public static String getCompanyPath() {
		String dir = getLuceneDir();
		if (dir == null || dir.equals("")) {
			return "";
		}
		return getLuceneIndexPath(dir, IndexType.company.getPath());

	}

	public static String getPositionPath() {
		String dir = getLuceneDir();
		if (dir == null || dir.equals("")) {
			return "";
		}

		return getLuceneIndexPath(dir, IndexType.position.getPath());

	}

	public static String getLivePath() {
		String dir = getLuceneDir();
		if (dir == null || dir.equals("")) {
			return "";
		}
		return getLuceneIndexPath(dir, IndexType.live.getPath());

	}

	public static String getStuPath() {
		String dir = getLuceneDir();
		if (dir == null || dir.equals("")) {
			return "";
		}

		return getLuceneIndexPath(dir, IndexType.stu.getPath());

	}
	
	public static String getSchPath() {
		String dir = getLuceneDir();
		if (dir == null || dir.equals("")) {
			return "";
		}
		
		return getLuceneIndexPath(dir, IndexType.sch.getPath());
		
	}

//	public static String getUniversityPath() {
//		String dir = getLuceneDir();
//		if (dir == null || dir.equals("")) {
//			return "";
//		}
//		return getLuceneIndexPath(dir, IndexType.university.getPath());
//
//	}

	static public enum IndexType {
		// 根索引(所有)
		index("index"),

		// 职位
		position("position"),
		// 视频
		live("live"),
		// 公司
		company("company"),
		// 学生
	 	student("student"),
		// 学校
	 	school("school"),
		//学生
	 
		stu("stu"),
		//学校
		sch("sch");

		private String path;

		IndexType(String path) {
			this.path = path;
		}

		public String getPath() {
			return this.path;
		}

	}

	public static void main(String[] args) {
		//System.out.println(Lucene.IndexType.position.name());

		System.out.println("E:\\lucene");
		
		
	}

}
