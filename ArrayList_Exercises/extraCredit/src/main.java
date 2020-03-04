import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class main {
    static final String FILENAME = "words.txt";

    public static void main(String args[]){
        Path path = Paths.get("./"+FILENAME);
        try(Stream<String> lines = Files.lines(path);){
            List<String> words = lines.collect(Collectors.toList());
            System.out.println("Number of words total: "+words.stream().count());
            catDog(words);
            fourLetter(words);
            endsWithIngIon(words);
            qNotU(words);
            noVowels(words);
            twoConsecutiveVowels(words);
            twoVowels(words);
        }
        catch(IOException e){
            e.printStackTrace();
        }

    }

    private static void catDog(List<String> e){
        int a = (int) e.stream().filter(x -> x.contains("dog")).count();
        int b = (int) e.stream().filter(x -> x.contains("cat")).count();
        System.out.println("Number of words containing dog: "+a);
        System.out.println("Number of words containing cat: "+b);
    }

    private static void fourLetter(List<String> e){
        int a = (int) e.stream().filter(x -> x.length()==4).count();
        System.out.println("Number of four letter words: "+a);
    }

    private static void endsWithIngIon(List<String> e){
        int a = (int) e.stream().filter(x -> x.endsWith("ing")).count();
        int b = (int) e.stream().filter(x -> x.endsWith("ion")).count();
        System.out.println("Number of words ending with ing: "+a);
        System.out.println("Number of words ending with ion: "+b);
        if(a>b) System.out.println("More words end with ing");
        else System.out.println("More words end with ion");
    }

    private static void qNotU(List<String> e){
        System.out.println("Words with q not followed by u: " +
                ""+e.stream().filter(x->x.contains("q")).filter(x->!x.contains("u")).count());
    }

    private static void noVowels(List<String> e){
        System.out.println("Number of words " +
                "without a vowel: " +
                ""+e.stream().filter(x->x.matches("(?i)[^aeiou]+$")).count());
    }

    private static void twoConsecutiveVowels(List<String> e){
        System.out.println("Number of words with two " +
                "consecutive vowels: "+ e.stream().filter(x->x.matches("^([^aieou]*[aieou]){2,}[^aieou]*$")).count());

    }

    private static void twoVowels(List<String> e){
        System.out.println("Number of words " +
                "with two vowel: "+e.stream().filter(x->x.matches("(?i)[aeiou].{2,}+")).count());
    }
}
