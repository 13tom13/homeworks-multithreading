import java.util.*;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        String[] texts = new String[25];
        for (int i = 0; i < texts.length; i++) {
            texts[i] = generateText("aab", 30_000);
        }
        long startTs = System.currentTimeMillis(); // start time
        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < texts.length; i++) {
            int b = i;
//            threads.add(new Thread(() -> Main.sreach(texts[b])));
            threads.add(new Thread(() -> sreach(texts[b])));
        }
            for (Thread t : threads) {
                t.start();
                t.join();
            }
            long endTs = System.currentTimeMillis(); // end time
            System.out.println("Time: " + (endTs - startTs) + "mds");
        }

        public static String generateText (String letters,int length){
            Random random = new Random();
            StringBuilder text = new StringBuilder();
            for (int i = 0; i < length; i++) {
                text.append(letters.charAt(random.nextInt(letters.length())));
            }
            return text.toString();
        }

        public static void sreach (String text){
            int maxSize = 0;
            for (int i = 0; i < text.length(); i++) {
                for (int j = 0; j < text.length(); j++) {
                    if (i >= j) {
                        continue;
                    }
                    boolean bFound = false;
                    for (int k = i; k < j; k++) {
                        if (text.charAt(k) == 'b') {
                            bFound = true;
                            break;
                        }
                    }
                    if (!bFound && maxSize < j - i) {
                        maxSize = j - i;
                    }
                }
            }
            System.out.println(text.substring(0, 100) + " -> " + maxSize);
        }
    }