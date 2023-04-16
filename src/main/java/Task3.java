import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class  Task3{
    public static void main(String[] args) {
        String fileName = "src/words.txt";
        Map<String, Integer> wordFrequencyMap = getWordFrequencyMap(fileName);
        wordFrequencyMap.entrySet().stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .forEach(entry -> System.out.println(entry.getKey() + " " + entry.getValue()));
    }

    public static Map<String, Integer> getWordFrequencyMap(String fileName) {
        Map<String, Integer> wordFrequencyMap = new HashMap<>();
        File file = new File(fileName);
        Scanner scanner = null;
        try {
            scanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();}
        while (scanner.hasNext()) {
            String word = scanner.next();
            wordFrequencyMap.merge(word, 1, Integer::sum);
        }
        scanner.close();
        return wordFrequencyMap;
    }
}
