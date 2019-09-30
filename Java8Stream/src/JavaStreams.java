import java.lang.String;
import java.util.Arrays;
import java.util.List;
import java.util.stream.*;
import java.util.*;
import java.nio.file.*;
import java.io.IOException;

public class JavaStreams {
    public static void main(String[] args) throws IOException {
        // 1. Integer Stream
        IntStream
                .range(1, 10)
                .forEach(System.out::print);
        System.out.println();

        // 2. Integer Stream with skip
        IntStream
                .range(1, 10)
                .skip(5)
                .forEach(x -> System.out.println(x));
        System.out.println();

        // 3. Integer Stream with sum
        int sum1 = IntStream.range(1, 5).sum();
        System.out.println(sum1);
        System.out.println(
                IntStream
                        .range(1, 5)
                        .sum());
        System.out.println();

        // 4. Stream.of, sorted and findFirst
        System.out.println("4.");
        Stream.of("Ava", "Aneri", "Alberto")
                .sorted()
                .findFirst()
                .ifPresent(System.out::println);

        // 5. Stream from Array, sort, filter and print
        System.out.println("5.");
        String[] names = {"Al", "Ankit", "Kushal", "Brent", "Sarika", "amanda", "Hans", "Shivika", "Sarah"};
        Arrays.stream(names)	// same as Stream.of(names)
                .filter(x -> x.startsWith("S"))
                .sorted()
                .forEach(System.out::println);

        // 6. average of squares of an int array
        System.out.println("6.");
        Arrays.stream(new int[] {2, 4, 6, 8, 10})
                .map(x -> x * x)
                .average()
                .ifPresent(System.out::println);

        // 7. Stream from List, filter and print
        System.out.println("7.");
        List<String> people = Arrays.asList("Al", "Ankit", "Brent", "Sarika", "amanda", "Hans", "Shivika", "Sarah");
        people
                .stream()
                .map(String::toLowerCase)
                .filter(x -> x.startsWith("a"))
                .forEach(System.out::println);

        // 8. Stream rows from text file, sort, filter, and print
        System.out.println("8.");
        Stream<String> bands = Files.lines(Paths.get("bands.txt"));
        bands
                .sorted()
                .filter(x -> x.length() > 13)
                .forEach(System.out::println);
        bands.close();

        // 9. Stream rows from text file and save to List
        System.out.println("9.");
        List<String> bands2 = Files.lines(Paths.get("bands.txt"))
                .filter(x -> x.contains("jit"))
                .collect(Collectors.toList());
        bands2.forEach(x -> System.out.println(x));

        // 10. Stream rows from CSV file and count
        System.out.println("10.");
        Stream<String> rows1 = Files.lines(Paths.get("data.txt"));
        int rowCount = (int)rows1
                .map(x -> x.split(","))
                .filter(x -> x.length == 3)
                .count();
        System.out.println(rowCount + " rows.");
        rows1.close();

        // 11. Stream rows from CSV file, parse data from rows
        System.out.println("11.");
        Stream<String> rows2 = Files.lines(Paths.get("data.txt"));
        rows2
                .map(x -> x.split(","))
                .filter(x -> x.length == 3)
                .filter(x -> Integer.parseInt(x[1]) > 15)
                .forEach(x -> System.out.println(x[0] + "  " + x[1] + "  " + x[2]));
        rows2.close();

        // 12. Stream rows from CSV file, store fields in HashMap
        System.out.println("12.");
        Stream<String> rows3 = Files.lines(Paths.get("data.txt"));
        Map<String, Integer> map = new HashMap<>();
        map = rows3
                .map(x -> x.split(","))
                .filter(x -> x.length == 3)
                .filter(x -> Integer.parseInt(x[1]) > 15)
                .collect(Collectors.toMap(
                        x -> x[0],
                        x -> Integer.parseInt(x[1])));
        rows3.close();
        for (String key : map.keySet()) {
            System.out.println(key + "  " + map.get(key));
        }

        // 13. Reduction - sum
        System.out.println("13.");
        double total = Stream.of(7.3, 1.5, 4.8)
                .reduce(0.0, (Double a, Double b) -> a + b);
        System.out.println("Total = " + total);

        // 14. Reduction - summary statistics
        System.out.println("14.");
        IntSummaryStatistics summary = IntStream.of(7, 2, 19, 88, 73, 4, 10)
                .summaryStatistics();
        System.out.println(summary);

        //
        System.out.println("16.");
        Stream.of("as", 1, 3, "f")
                .map(x -> String.valueOf(x))
                .forEach(System.out::print);

        System.out.println();
        System.out.println("17.");
        Stream.of(2, 4, 1, 9)
                .sorted(Collections.reverseOrder())
                .forEach(System.out::println);

        System.out.println("18.");
        List<String>strings = Arrays.asList("abc", "", "bc", "efg", "abcd","", "jkl");
        String mergedString = strings.stream().filter(string -> !string.isEmpty()).collect(Collectors.joining(", "));
        System.out.println(mergedString);

        System.out.println("19.");
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);
        numbers.parallelStream()
                .forEach(System.out::println);

        numbers.parallelStream()
                .forEachOrdered(System.out::println);

        System.out.println("20.");
        IntStream range2 = IntStream.rangeClosed(1, 10);
        range2.parallel().forEachOrdered(System.out::println);

        System.out.println("21.");
        Stream<String> stringStream = Stream.of("a", "b", "c");
//        String[] stringArray = stringStream.toArray(size -> new String[size]);
        String[] stringArray = stringStream.toArray(String[]::new);
        Arrays.stream(stringArray).forEach(System.out::println);


        // Creating a list of integers
        System.out.println("22.");
        List<Integer> list = Arrays.asList(-9, -18, 0, 25, 4);

        System.out.print("The maximum value is : ");

        // Using stream.max() to get maximum
        // element according to provided Comparator
        // and storing in variable var
        Integer var = list.stream().max(Comparator.reverseOrder()).get();
        System.out.print(var);

        System.out.println();
        int a = Stream.of(2, 4, 1, 9).max(Comparator.naturalOrder()).get();
        System.out.println(a);

        System.out.println("23.");
        String[] arr = new String[]{"a", "b", "c"};
        Stream<String> streamOfArrayFull = Arrays.stream(arr);
        Stream<String> streamOfArrayPart = Arrays.stream(arr, 1, 3);


    }
}