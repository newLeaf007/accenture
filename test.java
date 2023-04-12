package org.example.question1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ReadTxtFile {

    public static void mostCommon100words (String filePath){
        try(Stream<String> st = Files.lines(Paths.get(filePath))){

             st.flatMap(s -> Stream.of(s.split("\\W+")))
                   //  .peek(s -> System.out.println(s))
                     .collect(
                             Collectors.groupingBy(
                                     Function.identity(),
                                     Collectors.counting()
                             )
                     ).entrySet().stream()
                     .sorted(Map.Entry.<String,Long>comparingByValue().reversed())
                     .limit(100)
                     .forEach(System.out::println);

        }catch (IOException e){
            System.out.println("File is not present in the "+filePath);
        }
    }


    //exclude words "of","the","to","and","for"
    public static void excludeWords(String filePath){
        String[] excluding = {"of","the","to","and","for"};
        Predicate<String> exclude = s -> !Arrays.asList(excluding).contains(s);
        try(Stream<String> st = Files.lines(Paths.get(filePath))) {
            st.flatMap(s -> Stream.of(s.split("\\W+")))
                    .collect(Collectors.toList())
                    .forEach(System.out::println);
        }catch (IOException e){
            System.out.println("File not found in the location "+filePath);
        }
    }

    //print the Key first
    public static void csvFormat(String filePath){
        {
            try(Stream<String> st = Files.lines(Paths.get(filePath))){

                st.flatMap(s -> Stream.of(s.split("\\W+")))
                        //  .peek(s -> System.out.println(s))
                        .collect(
                                Collectors.groupingBy(
                                        Function.identity(),
                                        Collectors.counting()
                                )
                        ).entrySet().stream()
                        .sorted(Map.Entry.<String,Long>comparingByValue().reversed())
                        .forEach(
                                entry->System.out.println(entry.getValue()+" , "+entry.getKey())
                        );

            }catch (IOException e){
                System.out.println("File is not present in the "+filePath);
            }
        }
    }

    public static void main(String[] args){
            String filePath ="C:\\Users\\WW984NJ\\Downloads\\sample-text-file.txt";

            //mostCommon100words(filePath);

            //excluding words
                //excludeWords(filePath);

        //csv format
             csvFormat(filePath);


    }
}
