/**
 * ArrayList Exercises
 * Created by Benjamin Blouin
 */

import java.util.*;
import java.util.stream.Collectors;

public class main {

    public static void main(String args[])
    {
        // unique() function test list creation, function call, SOP to check behavior
        List<Object> t = new ArrayList<>();
        t.add("hello"); t.add("toupee"); t.add("hi"); t.add(5); t.add(6); t.add(7);
        System.out.println(unique(t));

        // allMultiples test int list, function call, SOP to check behavior
        List<Integer> u = new ArrayList<>();
        u.add(5); u.add(10); u.add(11);
        System.out.println(allMultiples(u,5));

        // allStringsOfSize test list, function call, SOP to check behavior
        List<String> v = new ArrayList<>();
        v.add("hello"); v.add("hiya"); v.add("hey"); v.add("hi");
        System.out.println(allStringsOfSize(v,3));

        // isPermutation
        List<Object> r = new ArrayList<>();
        t.clear(); t.add(5); t.add(5); t.add(7); t.add(6);
        r.add(7); r.add(7); r.add(5); r.add(6);
        System.out.println(isPermutation(t,r));

        // stringToList
        String s = "Hello, World! Death,, you!!";
        System.out.println(stringToListOfWords(s));

        // removeAllInstances
        t.clear();
        t.add("Hi"); t.add("hello"); t.add(4); t.add(5); t.add(4);
        removeAllInstances(t,4);
    }

    /**
     * Takes in List of Objects, tests for uniqueness; if all are unique
     * return true, else return false
     * @param e
     *
     * @return boolean
     */

    public static boolean unique(List<Object> e){
        int original = (int) e.stream().count(); // count of entries before removal
        int afterDistinct = (int) e.stream().distinct().count(); // count only distinct values
        return original == afterDistinct; //returns boolean
    }

    /**
     * Takes in list of integers and single int, returns list with only
     * multiples of single int.
     * @param e
     * @param t int
     *
     * @return list of integers that are multiples of t
     */

    public static List<Integer> allMultiples(List<Integer> e, int t){
        // Turns List into stream, filters by modulo lambda expression
        // Then it collects the stream and turns it back into a list
        return e.stream().filter(x -> x % t == 0).collect(Collectors.toList());
    }

    /**
     * Takes in list of strings and single int, returns list with only
     * strings of length equal to int
     * @param a
     * @param t
     *
     * @return list of strings that are of length t
     */
    public static List<String> allStringsOfSize(List<String> a, int t){
        // Streams list and filters entries by lambda expresion, collects it to a list
        return a.stream().filter(x->x.length() == t).collect(Collectors.toList());
    }

    /**
     * Given two lists of things and tests if they're a permutation
     * @param a List
     * @param b List
     *
     * @return boolean true if is a permutation
     */

    public static boolean isPermutation(List<Object> a, List<Object> b){
        if(a.size()!=b.size()) return false; // not same elements
        // it's easier if we sort the lists first
        a = a.stream().sorted().collect(Collectors.toList());
        b = b.stream().sorted().collect(Collectors.toList());
        for(int i = 0; i <a.size(); i++){
            if (a.get(i) != b.get(i)) return false;
        }
        return true;
    }

    /**
     * Given a string, separate words and remove punctuation
     * @param a String
     *
     * @return List
     */

    public static List<String> stringToListOfWords(String a){
        List<String> list = new ArrayList<>(); // New list to return to main
        // takes string, replaces all punctuation with spaces, then splits is at whitespaces
        // this would normally return a String[] array, but we need a list, so we use
        // Arrays to return split, modified string as list
        return Arrays.asList(a.replaceAll("\\p{P}", "").split("\\s+"));
    }

    public static void removeAllInstances(List<Object> e, Object f){
        // removes all f, but remove all wants a collection as argument
        // so we turn the Object f into a collection of 1 element (singleton)
        e.removeAll(Collections.singleton(f));
        System.out.println(e);
    }
}
