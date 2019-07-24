package org.wpy.thread.cdl;

import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierDemo {
	private static final int THREAD_COUNT_NUM=7;
	
	public static void main(String[] args){
		CyclicBarrier cyclicBarrier1=new CyclicBarrier(THREAD_COUNT_NUM,new Runnable(){
			@Override
			public void run() {
				System.out.println("7个法师召集完毕，同时出发，去往不同地方寻找龙珠！");
				summonDragon();
			}
		});
		//召集齐7位法师
		for(int i=1;i<=THREAD_COUNT_NUM;i++){
			int index=i;
			new Thread(()->{
				try {
					System.out.println("召集第"+index+"个法师");
					cyclicBarrier1.await();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}).start();
		}
	}
	
	private static void summonDragon(){
		CyclicBarrier cyclicBarrier2=new CyclicBarrier(THREAD_COUNT_NUM,new Runnable(){
			@Override
			public void run() {
				System.out.println("集齐七颗龙珠！召唤神龙！");
			}
		});
		for(int i=1;i<=THREAD_COUNT_NUM;i++){
			int index=i;
			new Thread(()->{
				try {
					System.out.println("第"+index+"颗龙之已经收集到！");
					cyclicBarrier2.await();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}).start();
		}
	}
}
