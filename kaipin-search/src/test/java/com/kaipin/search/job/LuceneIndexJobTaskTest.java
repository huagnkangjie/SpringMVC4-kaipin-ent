package com.kaipin.search.job;

import java.util.Scanner;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.kaipin.search.JUnitBase;
import com.kaipin.search.service.IndexUpdaterService ;

public class LuceneIndexJobTaskTest  extends JUnitBase{

	@Autowired
	private IndexUpdaterService  indexUpdaterService;
	
	
	//@Test
	public void jobTask(){
		
		System.out.println("test input：");
	        Scanner input=new Scanner(System.in);
	        int x=input.nextInt();
	        System.out.println(x);
	        System.out.print("Test end..");
		
		
	}
	@Test
	public void start(){
		indexUpdaterService.start();
	}
	
	public static void main(String[] args) {
		 
		

	}

}
