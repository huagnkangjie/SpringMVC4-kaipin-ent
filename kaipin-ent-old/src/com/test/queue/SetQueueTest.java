package com.test.queue;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Random;
import java.util.TreeSet;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

/**                                                                                                 
  * 测试集合与队列的插入与读写性能                                                                   
  *                                                                                                  
  * @author jiqunpeng@gmail.com                                                                      
  *                                                                                                  
  */                                                                                                 
 public class SetQueueTest {                                                                         
     // 随即数构造器                                                                                 
     private static Random r = new Random(10);                                                       
     // 控制测试线程停止的原子变量                                                                   
     private static AtomicBoolean stop = new AtomicBoolean(false);                                   
                                                                                                     
     /***                                                                                            
      * 基类，供测试用                                                                               
      *                                                                                              
      * @author jiqunpeng@gmail.com                                                                  
      *                                                                                              
      */                                                                                             
     static abstract class Service {                                                                 
         // 操作的计数器                                                                             
         protected long count = 0;                                                                   
                                                                                                     
         // 添加一堆元素，并去一个元素                                                               
         public abstract String addAndPick(List<String> elements);                                   
                                                                                                     
         // 取一个元素                                                                               
         public abstract String pickOne();                                                           
                                                                                                     
         /**                                                                                         
          * 打印操作次数                                                                             
          */                                                                                         
         public void tell() {                                                                        
             System.out.println(this + " :\t" + count);                                              
         }                                                                                           
     }                                                                                               
                                                                                                     
     /***                                                                                            
      * 采用TreeSet的集合工具                                                                        
      *                                                                                              
      * @author jiqunpeng@gmail.com                                                                  
      *                                                                                              
      */                                                                                             
     static class SetService extends Service {                                                       
         private TreeSet<String> set = new TreeSet<String>();                                        
                                                                                                     
         @Override                                                                                   
         public synchronized String addAndPick(List<String> elements) {                              
             count++;                                                                                
             set.addAll(elements);                                                                   
             return set.pollFirst();                                                                 
         }                                                                                           
                                                                                                     
         @Override                                                                                   
         public synchronized String pickOne() {                                                      
             count++;                                                                                
             return set.pollFirst();                                                                 
         }                                                                                           
                                                                                                     
     }                                                                                               
                                                                                                     
     /***                                                                                            
      * 采用LinkedList的队列工具                                                                     
      *                                                                                              
      * @author jiqunpeng@gmail.com                                                                  
      *                                                                                              
      */                                                                                             
     static class QueueService extends Service {                                                     
         private Queue<String> queue = new LinkedList<String>();                                     
                                                                                                     
         @Override                                                                                   
         public synchronized String addAndPick(List<String> elements) {                              
             count++;                                                                                
             queue.addAll(elements);                                                                 
             return queue.poll();                                                                    
         }                                                                                           
                                                                                                     
         @Override                                                                                   
         public synchronized String pickOne() {                                                      
             count++;                                                                                
             return queue.poll();                                                                    
         }                                                                                           
     }                                                                                               
                                                                                                     
     /***                                                                                            
      * 测试类                                                                                       
      *                                                                                              
      * @author jiqunpeng@gmail.com                                                                  
      *                                                                                              
      */                                                                                             
     static class Tester implements Runnable {                                                       
         // 绑定要测试的工具对象                                                                     
         private Service service;                                                                    
                                                                                                     
         Tester(Service s) {                                                                         
             this.service = s;                                                                       
         }                                                                                           
                                                                                                     
         @Override                                                                                   
         public void run() {                                                                         
             while (stop.get() == false) {                                                           
                 List<String> elements = new ArrayList<String>();                                    
                 int len = r.nextInt(200) + 8;                                                       
                 for (int i = 0; i < len; i++) {                                                     
                     elements.add(String.valueOf(r.nextInt()));                                      
                 }                                                                                   
                 service.addAndPick(elements);                                                       
                 for (int i = 0; i < 104; i++)                                                       
                     service.pickOne();                                                              
             }                                                                                       
         }                                                                                           
     }                                                                                               
                                                                                                     
     /***                                                                                            
      * 多线程方式，测试一个插入、删除工具                                                           
      *                                                                                              
      * @param service                                                                               
      * @param time                                                                                  
      * @param unit                                                                                  
      * @throws InterruptedException                                                                 
      */                                                                                             
     private static void test(Service service, int time, TimeUnit unit)                              
             throws InterruptedException {                                                           
         ExecutorService execs = Executors.newCachedThreadPool();                                    
         for (int i = 0; i < 20; i++) {                                                              
             execs.execute(new Tester(service));                                                     
         }                                                                                           
         execs.shutdown();                                                                           
         unit.sleep(time);                                                                           
         stop.compareAndSet(false, true);                                                            
         service.tell();                                                                             
     }                                                                                               
                                                                                                     
     public static void main(String[] args) throws InterruptedException {                            
         Service setService = new SetService();                                                      
         test(setService, 5, TimeUnit.SECONDS);                                                      
         stop.compareAndSet(true, false);// 重置终止条件                                             
         Service queueService = new QueueService();                                                  
         test(queueService, 5, TimeUnit.SECONDS);                                                    
     }                                                                                               
 }                                                                                                   
