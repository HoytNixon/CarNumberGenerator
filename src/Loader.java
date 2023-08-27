import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Loader {

    public static void main(String[] args) throws Exception {
        long start = System.currentTimeMillis();
        List<Writer> writersPool = new ArrayList<>();
        for (int i =4; i>0; i--){
            String source = "src/res/numbers"+ i + ".txt";
            try(Writer pw =new Writer(source)){
                writersPool.add(pw);
            }catch (Exception ex){
                System.out.println(ex);
            }

        }
        try (ExecutorService pool = Executors.newFixedThreadPool(4)) {

            char[] letters = {'У', 'К', 'Е', 'Н', 'Х', 'В', 'А', 'Р', 'О', 'С', 'М', 'Т'};
            for (int number = 999; number > 0; number--) {
                int regionCode = 199;
                for (char firstLetter : letters) {
                    for (char secondLetter : letters) {
                        for (char thirdLetter : letters) {
                            String stringBuilder = firstLetter +
                                    padNumber(number, 3) +
                                    secondLetter +
                                    thirdLetter +
                                    padNumber(regionCode, 2);
                            pool.execute(new RunnableNumbers(stringBuilder, writersPool));
                        }
                    }
                }
            }
            pool.shutdown();
            for (Writer writer : writersPool){
                writer.close();
            }
        }
        System.out.println((System.currentTimeMillis() - start) + " ms");
    }

    private static String padNumber(int number, int numberLength) {
        StringBuilder numberStr = new StringBuilder(Integer.toString(number));
        int padSize = numberLength - numberStr.length();

        for (int i = 0; i < padSize; i++) {
            numberStr.insert(0, '0');
        }

        return numberStr.toString();
    }
}
