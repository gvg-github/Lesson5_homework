
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

/**
 * Created by GVG on 26.08.2017.
 */

public class MainClass {

    public static final int CARS_COUNT = 8;

    //+ добавлены константы и переменная
    public static final CyclicBarrier CB = new CyclicBarrier(CARS_COUNT); // для одновременного старта
    public static final CountDownLatch CDLSTART = new CountDownLatch(CARS_COUNT); // для сообщения о старте
    public static final CountDownLatch CDLFINISH = new CountDownLatch(CARS_COUNT); // для сообщения о финише
    static boolean first = false; // для определения победителя
    //- добавлены константы и переменная

    public static void main(String[] args) throws BrokenBarrierException, InterruptedException {

        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Подготовка!!!");
        Race race = new Race(new Road(60), new Tunnel(), new Road(40));
        Car[] cars = new Car[CARS_COUNT];
        for (int i = 0; i < cars.length; i++) {
            cars[i] = new Car(race, 20 + (int) (Math.random() * 10));
        }
        for (int i = 0; i < cars.length; i++) {
            new Thread(cars[i]).start();
        }

        CDLSTART.await(); // синхронизация для вывода сообщения о старте
        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка началась!!!");

        CDLFINISH.await(); // синхронизация для вывода сообщения о завершении гонки
        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка закончилась!!!");
    }
}

