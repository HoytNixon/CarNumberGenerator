import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;

public class RunnableNumbers implements Runnable {
    String carNumber;
    List <Writer> pool;
    public RunnableNumbers(String carNumber, List<Writer> pool) {
        this.carNumber = carNumber;
        this.pool= pool;
    }

    @Override
    public void run() {
        String currentThread = Thread.currentThread().getName();
        Writer writer= pool.stream()
                .filter(w-> w.getFileName().charAt(15)==currentThread.charAt(14))
                .findFirst()
                .get();
        System.out.println("eding" + carNumber+ writer.getFileName());
        writer.println(carNumber);
    }
}
