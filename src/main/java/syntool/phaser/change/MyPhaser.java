package syntool.phaser.change;

import java.util.Date;
import java.util.Random;
import java.util.concurrent.Phaser;
import java.util.concurrent.TimeUnit;

/**
 *@author chengnl
 *@E-mail:chengnengliang@vrvmail.com.cn
 *@date 2014年12月1日 上午11:26:09
 *@version 1.0
 *@Description:自定义phaser
 */
public class MyPhaser extends Phaser{
    @Override  
	public boolean onAdvance(int phase, int registeredParties ){
    	  switch (phase) {
				case 0:
					return studentsArrived();
				case 1:
					return finishFirstExercise();
				case 2:
					return finishSecondExercise();
				case 3:
					return finishExam();
				default:
					return true;
		}
     }
    //返回false表明phaser将继续执行
    private boolean studentsArrived(){
    	System.out.println("Phaser: The exam are going to start. the students are ready.");
    	System.out.printf("Phaser: We hava %d students.\n",getRegisteredParties());
    	return false;
    }
    private boolean finishFirstExercise(){
    	System.out.println("Phaser: All the students hava finished the first exercise.");
    	System.out.printf("Phaser: It's time for the second one.\n");
    	return false;
    }
    private boolean finishSecondExercise(){
    	System.out.println("Phaser: All the students hava finished the second exercise.");
    	System.out.printf("Phaser: It's time for the third one.\n");
    	return false;
    }
    //返回true 表明phaser将结束
    private boolean finishExam(){
    	System.out.println("Phaser: All the students hava finished the exam.");
    	System.out.printf("Phaser: thank you for your time.\n");
    	return true;
    }
    
    public class Student implements Runnable{
        private Phaser phaser;
        public  Student(Phaser phaser){
        	this.phaser=phaser;
        }
		@Override
		public void run() {
			System.out.printf("%s: Has arrived to do the exam. %s\n",Thread.currentThread().getName(),new Date());
			phaser.arriveAndAwaitAdvance();
			System.out.printf("%s: Is going to do the first exercise. %s\n",Thread.currentThread().getName(),new Date());
            doExercise1();
            System.out.printf("%s: Has done the first exercise. %s\n",Thread.currentThread().getName(),new Date());
            phaser.arriveAndAwaitAdvance();
            System.out.printf("%s: Is going to do the second exercise. %s\n",Thread.currentThread().getName(),new Date());
            doExercise2();
            System.out.printf("%s: Has done the second exercise. %s\n",Thread.currentThread().getName(),new Date());
            phaser.arriveAndAwaitAdvance();
            System.out.printf("%s: Is going to do thrid exercise. %s\n",Thread.currentThread().getName(),new Date());
            doExercise3();
            System.out.printf("%s: Has done the exam. %s\n",Thread.currentThread().getName(),new Date());
            phaser.arriveAndAwaitAdvance();
		}
		
		private void doExercise1(){
			 Random  random = new Random();
			 try {
				TimeUnit.SECONDS.sleep( (long)random.nextInt(10));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		private void doExercise2(){
			 Random  random = new Random();
			 try {
				TimeUnit.SECONDS.sleep( (long)random.nextInt(10));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		private void doExercise3(){
			 Random  random = new Random();
			 try {
				TimeUnit.SECONDS.sleep( (long)random.nextInt(10));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
    }
}
