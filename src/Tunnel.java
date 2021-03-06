import java.util.concurrent.Semaphore;

/**
 * Created by GVG on 29.08.2017.
 */
public class Tunnel extends Stage {
    Semaphore sm = new Semaphore(MainClass.CARS_COUNT / 2); //Ограничение въезда в тоннель

    public Tunnel() {
        this.length = 80;
        this.description = "Тоннель " + length + " метров";
    }

    @Override
    public void go(Car c) {
        try {
            try {
                System.out.println(c.getName() + " готовится к этапу(ждет): " + description);
                sm.acquire(); //Ограничение въезда в тоннель
                System.out.println(c.getName() + " начал этап: " + description);
                Thread.sleep(length / c.getSpeed() * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                System.out.println(c.getName() + " закончил этап: " + description);
                sm.release(); //Ограничение въезда в тоннель
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
