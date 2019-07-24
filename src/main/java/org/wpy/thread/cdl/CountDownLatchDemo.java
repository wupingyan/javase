package org.wpy.thread.cdl;

import java.util.Random;
import java.util.concurrent.CountDownLatch;

/**
 * CountDownLatch是一个倒计时器，它允许一个或多个线程一直等待，直到其他线程的操作执行完后再执行
 * 比如：集齐七龙珠后方可召唤神龙
 * @author pywu
 *CountDownLatch是在java1.5被引入的，它存在于java.util.concurrent包下。
 *	CountDownLatch这个类能够使一个线程等待其他线程完成各自的工作后再执行。
 *	例如，应用程序的主线程希望在负责启动框架服务的线程已经启动所有的框架服务之后再执行。
 *CountDownLatch是通过一个计数器来实现的，计数器的初始值为线程的数量。
 *	每当一个线程完成了自己的任务后，计数器的值就会减1。
 *	当计数器值到达0时，它表示所有的线程已经完成了任务，然后在闭锁上等待的线程就可以恢复执行任务。

 */
public class CountDownLatchDemo {
	private static final int THREAD_COUNT_NUM=7;
	/**
	 * 构造函数参数表示需要等待执行完毕的线程数量，这个值只能被设置一次，
	 * 而且CountDownLatch没有提供任何机制去重新设置这个计数器
	 */
	private static CountDownLatch countDownLatch=new CountDownLatch(THREAD_COUNT_NUM);
	
	
	public static void main(String[] args) throws InterruptedException{
		for(int i=1;i<=THREAD_COUNT_NUM;i++){
			int index=i;
			new Thread(()->{
				try{
					System.out.println("第"+index+"颗龙珠已收集到");
					//模拟收集第i个龙珠，随机模拟不同的寻找时间
					Thread.sleep(new Random().nextInt(3000));
				}catch(Exception e){
					e.printStackTrace();
				}
				//每收集一个龙珠，需要将等待的颗数减1
				countDownLatch.countDown();
			}).start();
		}
		//等待检查，即上述7个线程执行完毕后，执行await后边的代码
		countDownLatch.await();
		System.out.println("集齐七颗龙珠！召唤神龙！");
	}
}
