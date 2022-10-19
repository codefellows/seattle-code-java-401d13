/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package streamFun;

import java.util.*;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.*;

public class App {

    private static List<Java401Person> setup()
    {
        List<Java401Person> java401People = new ArrayList<>();
        java401People.add(new Java401Person("Ed", true, 0, 0, new String[0], false, "skydiving", true));
        java401People.add(new Java401Person("Matt", true, 0, 0, new String[0], true, "video games", true));
        java401People.add(new Java401Person("Jen", false, 1, 0, new String[]{ "Fang"}, false, "snowboarding", true));
        java401People.add(new Java401Person("Haustin", true, 2, 1, new String[]{"Bob Ross", "Lenny Kravitz"}, false, "video games", true));
        java401People.add(new Java401Person("Patrick", false, 2, 18, new String[]{"Robin", "Batman"}, false, "guitarist", true));

        return java401People;
    }

    public static void main(String[] args)
    {
        List<Java401Person> people = setup();


        // ***** TEST CASE 1 *****


        System.out.println("Test Case 1 Imperative: Print the first 10 numbers");

        for(int i = 1; i <= 10; i++)
        {
            System.out.println(i);
        }
        System.out.println("Test Case 1 Functional:");

        // Streams need a source, and a terminal operation
        // Using .boxed to convert primitive int to Integer
        List<Integer> numbersFrom1To10 = IntStream.range(1, 11).boxed().collect(toList());
        for (Integer number : numbersFrom1To10)
        {
            System.out.println(number);
        }


        // ***** TEST CASE 2 *****


        System.out.println("Test Case 2 I(mperative): Print the last 5 numbers (from 1-10)");

        int[] numbersFrom1To10Array = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

        for(int i = 0; i < 10; i++)
        {
            if (i > 4)
            {
                System.out.println(numbersFrom1To10Array[i]);
            }
        }

        System.out.println("Test Case 2 F(unctional):");

        //                               Source op             Intermediate ops  Terminal op
        List<Integer> numbersFrom6To10 = IntStream.range(1, 11).boxed().skip(5).collect(toList());
        for (Integer number : numbersFrom6To10)
        {
            System.out.println(number);
        }

        int[] numbersFrom1To10ArrayAgain = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};


        // ***** TEST CASE 3 *****


        System.out.println("Test Case 3 I: Print the first 5 numbers (starting with 1)");

        for(int i = 0; i < 10; i++)
        {
            if (i <= 4)
            {
                System.out.println(numbersFrom1To10ArrayAgain[i]);
            }
        }

        System.out.println("Test Case 3 F:");

        List<Integer> numbersFrom1To5 = IntStream.range(1, 11).limit(5).boxed().collect(toList());
        for (Integer number : numbersFrom1To5)
        {
            System.out.println(number);
        }


        // ***** TEST CASE 4 *****


        System.out.println("Test Case 4 I: Print the number of people in our class");

        System.out.println(people.size());

        System.out.println("Test Case 4 F:");

        long countPeople = people.stream().count();
        // can also do
        //long countPeople = people.stream().collect(counting());

        System.out.println(countPeople);


        // ***** TEST CASE 5 *****


        System.out.println("Test Case 5 I: Print out if anyone is tired");

        boolean isAnyoneTired = false;
        for (Java401Person person : people)
        {
            if (person.isTired == true)
            {
                System.out.println("Someone is tired!");
                isAnyoneTired = true;
                break;
            }
        }
        if (isAnyoneTired == false)
        {
            System.out.println("No one is tired!");
        }

        System.out.println("Test Case 5 F:");

        // Method 1
        //List<Java401Person> tiredPeople = people.stream().filter(s -> s.isTired == true).collect(toList());
        // findFirst() and findAny() are basically equivalent for our purposes
        Java401Person tiredPerson = people.stream().filter(s -> s.isTired == true).findAny()
                .orElse(null);

        if(tiredPerson != null)
        {
            System.out.println("Someone is tired!");
        }
        else
        {
            System.out.println("No one is tired!");
        }

        //Method 2
        boolean isAnyoneTiredFunctional = people.stream().anyMatch(s -> s.isTired == true);

        if(isAnyoneTiredFunctional == true)
        {
            System.out.println("Someone is tired!");
        }
        else
        {
            System.out.println("No one is tired!");
        }


        // ***** TEST CASE 6 *****


        System.out.println("Test Case 6 I: Print out if everyone is smart");

        boolean isEveryoneSmartImperative = true;
        for (Java401Person person : people)
        {
            if (person.isSmart == false)
            {
                System.out.println("Someone is not smart!");
                isEveryoneSmartImperative = false;
                break;
            }
        }
        if (isEveryoneSmartImperative == true)
        {
            System.out.println("Everyone is smart!");
        }

        System.out.println("Test Case 6 F:");
        boolean isEveryoneSmartFunctional = people.stream().noneMatch(s -> s.isSmart == false);
        if(isEveryoneSmartFunctional == true)
        {
            System.out.println("Everyone is smart!");
        }
        else
        {
            System.out.println("Someone is not smart!");
        }
        // equivalent thing is:
        isEveryoneSmartFunctional = people.stream().allMatch(s -> s.isSmart == true);

        if(isEveryoneSmartFunctional == true)
        {
            System.out.println("Everyone is smart!");
        }
        else
        {
            System.out.println("Someone is not smart!");
        }


        // ***** TEST CASE 7 *****


        System.out.println("Test Case 7 I: Print out the sum of all our pet numbers, and print the average");

        double averageNumberOfPets = 0.0;
        int totalNumberOfPets = 0;

        for (Java401Person person : people)
        {
            averageNumberOfPets += person.numOfPets;
            totalNumberOfPets += person.numOfPets;
        }

        averageNumberOfPets /= people.size();

        System.out.println("Average number of pets: " + averageNumberOfPets);
        System.out.println("Total number of pets: " + totalNumberOfPets);

        System.out.println("Test Case 7 F:");

        double averageNumberOfPetsFunctional = people.stream().collect(averagingDouble(s -> s.numOfPets));
        int totalNumberOfPetsFunctional = people.stream().collect(summingInt(s -> s.numOfPets));
        System.out.println("Average number of pets: " + averageNumberOfPetsFunctional);
        System.out.println("Total number of pets: " + totalNumberOfPetsFunctional);


        // ***** TEST CASE 8 *****


        System.out.println("Test Case 8 I: Print how many people are tired, and not tired");

        List<Java401Person> tiredPeople = new ArrayList<>();
        List<Java401Person> notTiredPeople = new ArrayList<>();

        for (Java401Person person : people)
        {
            if (person.isTired == true)
            {
                tiredPeople.add(person);
            }
            else
            {
                notTiredPeople.add(person);
            }
        }

        System.out.println("Tired people: " + tiredPeople.size());

        System.out.println("Not tired people: " + notTiredPeople.size());

        System.out.println("Test Case 8 F:");

        Map<Boolean, Long> tiredPeopleMap = people.stream().collect(groupingBy(s -> s.isTired, counting()));

        System.out.println("tiredPeopleMap: " + tiredPeopleMap);


        // ***** TEST CASE 9 *****


        System.out.println("Test Case 9 I: Print out all our names in sorted alphabetical order (ascending)");

        System.out.println("People before sorting: " + people);

        List<Java401Person> sortedPeople = new ArrayList<>(people);
        Collections.sort(sortedPeople);

        System.out.println("People after sorting: " + sortedPeople);

        System.out.println("Test Case 9 F:");

        List<String> sortedPeopleFunctional = people.stream()
                .map(s -> s.name)
                .sorted()
                .collect(toList());

        System.out.println("People after sorting: " + sortedPeopleFunctional);


        // ***** TEST CASE 10 *****


        System.out.println("Test Case 10 I: Print out all our names, separated by semicolons");

        String allPeopleSeparatedBySemicolons = "";
        for (Java401Person person : people)
        {
            allPeopleSeparatedBySemicolons += person.name + ";";
        }

        System.out.println("All people: " + allPeopleSeparatedBySemicolons);

        System.out.println("Test Case 10 F:");

        // This version uses a method reference: Java401Person::getName
        String allPeopleSeparatedBySemicolonsFunctional = people.stream().map(Java401Person::getName).collect(joining(";"));

        System.out.println("All people: " + allPeopleSeparatedBySemicolonsFunctional);


        // ***** TEST CASE 11 *****


        System.out.println("Test Case 11 I: Print out all our distinct hobbies");

        //List<String> hobbies = new ArrayList<>();
        //Set<String> hobbySet = new HashSet<>();
        Map<String, String> hobbyMap = new HashMap<>();
        for (Java401Person person : people)
        {
            //if (!hobbies.contains(person.hobby))
            //{
            //    hobbies.add(person.hobby);
            //}
            //hobbySet.add(person.hobby);
            hobbyMap.putIfAbsent(person.hobby, "wheeee");
        }

        //System.out.println("Unique hobbies: " + hobbies);
        //System.out.println("Unique hobbies: " + hobbySet);
        System.out.println("Unique hobbies: " + hobbyMap);

        System.out.println("Test Case 11 F:");

        List<String> uniqueHobbiesFunctional = people.stream().map(s -> s.hobby).distinct().collect(toList());

        System.out.println("Unique hobbies: " + uniqueHobbiesFunctional);
    }
}
