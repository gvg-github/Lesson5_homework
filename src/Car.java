import java.util.concurrent.BrokenBarrierException;

/**
 * Created by GVG on 29.08.2017.
 */
public class Car implements Runnable {

    private static int CARS_COUNT;

    static {
        CARS_COUNT = 0;
    }

    private Race race;
    private int speed;
    private String name;

    public String getName() {
        return name;
    }

    public int getSpeed() {
        return speed;
    }

    public Car(Race race, int speed) {
        this.race = race;
        this.speed = speed;
        CARS_COUNT++;
        this.name = "Участник #" + CARS_COUNT;
    }

    @Override
    public void run() {
        try {
            System.out.println(this.name + " готовится");
            Thread.sleep(500 + (int) (Math.random() * 800));
            System.out.println(this.name + " готов");
            MainClass.CDLSTART.countDown(); // синхронизация для вывода сообщения о старте
        } catch (Exception e) {
            e.printStackTrace();
        }

        //+ синхронизация старта
        try {
            MainClass.CB.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
        //- синхронизация старта

        for (int i = 0; i < race.getStages().size(); i++) {
            race.getStages().get(i).go(this);
        }

        //+ синхронизация для вывода сообщения о завершении и определение победителя
        MainClass.CDLFINISH.countDown();
        synchronized (this) {
            if (!MainClass.first) {
                MainClass.first = true;
                System.out.println(name + " WIN!");
            }
        }

        //- синхронизация для вывода сообщения о завершении и определение победителя
    }
}
