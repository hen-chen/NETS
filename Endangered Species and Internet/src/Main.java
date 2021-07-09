import java.util.*;

public class Main {
    public static void main(String[] args) {
        Wiki parser = new Wiki("https://en.wikipedia.org/wiki/Endangered_species");
        
        Scanner scan = new Scanner(System.in);
        
        System.out.println("Enter question number:");
        int questionNumber = Integer.parseInt(scan.nextLine());
        
        if (questionNumber == 1) {
            List<String> resultq1 = parser.q1();
            for (String status : resultq1) {
                System.out.println(status);
            }
        } else if (questionNumber == 2) {
            System.out.println("Enter criteria number (int):");
            int criteria = scan.nextInt();

            String resultq2 = parser.q2("Critically Endangered", criteria);
            System.out.println(resultq2);
        } else if (questionNumber == 3) {
            System.out.println("Enter species (e.g. mammals):");
                
            String species = scan.next();
            String resultq3 = parser.q3(species, "extinct or extinct in the wild");
            
            System.out.println(resultq3);
        } else if (questionNumber == 4) {
            System.out.println("Enter animal name (e.g. white rhinoceros):");
            String animal = scan.nextLine();
            
            System.out.println("Enter classification (e.g. Class):");
            String classification = scan.nextLine();
            
            List<String> resultq4 = parser.q4(animal, classification);
            System.out.println("Status: " + resultq4.get(0));
            System.out.println("Classification: " + resultq4.get(1));
        } else if (questionNumber == 5) {
            System.out.println("Enter species (e.g. Endangered birds):");
            String species = scan.nextLine();
            
            System.out.println("Enter animal type (e.g. parrots):");
            String animal = scan.nextLine();
            
            List<String> resultq5 = parser.q5(species, animal);
            for (String name : resultq5) {
                System.out.println(name);
            }
        } else if (questionNumber == 6) {
            System.out.println("Enter continent (e.g. Australia):");
            
            String continent = scan.nextLine();
            List<String> resultq6 = parser.q6(continent,  "mammals", "recently extinct");
            for (String name : resultq6) {
                System.out.println(name);
            }
        } else if (questionNumber == 7) {
            System.out.println("Enter species (e.g. Endangered Amphibians):");
            String species = scan.nextLine();
            
            System.out.println("Enter animal type (e.g. Lungless Salamanders):");
            String animal = scan.nextLine();
            
            System.out.println("Enter country (e.g. United States):");
            String place = scan.nextLine();
            
            System.out.println("This could take a while...");
            Set<String> resultq7 = parser.q7(species, animal, place);
            for (String name : resultq7) {
                System.out.println(name);
            }
        } else if (questionNumber == 8) {
            System.out.println("Enter country (e.g. United States):");
            String country = scan.nextLine();
            
            Set<String> resultq8 = parser.q8(country);
            for (String name : resultq8) {
                System.out.println(name);
            }
        } else {
            System.out.println("Enter question number between 1-8");
        }
        scan.close();
    }
}
