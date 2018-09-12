package com.kaipin.search.util;

import java.io.File;

 

public class FileUtils {

	public static String getRealPath(String path) {

		return System.getProperty("user.dir") + path;

	}

	public static void deleteAllFilesOfDir(File path) {

		if (!path.exists())
			return;
		if (path.isFile()) {
			
			path.delete();
			System.out.println("delete:"+path.toString());
			return;
		}
		File[] files = path.listFiles();
		for (int i = 0; i < files.length; i++) {
			deleteAllFilesOfDir(files[i]);
		}

		path.delete();
		System.out.println("delete:"+path.toString());
	}

	/**
	 * 删除目录或者文件
	 * 
	 * @param dir
	 *            文件路径或者目录路径
	 */
	public static void deleteFile(String path) {
		File dir = new File(path);
		if (dir != null && dir.isDirectory()) {
			File[] file = dir.listFiles();
			for (File file2 : file) {
				deleteFile(file2.toString());
				file2.delete();
			}

		}

	}
	public static void deleteFile(File dir) {
 
		if (dir != null && dir.isDirectory()) {
			File[] file = dir.listFiles();
			for (File file2 : file) {
				deleteFile(file2.toString());
				file2.delete();
			}

		}

	}
	public static void main(String[] args) {
 
		
	}
}
