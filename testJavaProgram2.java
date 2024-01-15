import java.util.Scanner;

public class testJavaProgram2 {
    static String name;
    static String lastName;
    static String age;

    static void printInfo() {
        System.out.println("Thanks for testing "+name+" "+lastName);
        System.out.println("You are " + age +" years old");
    }
    // Create a class constructor for the Main class

    public static void main(String[] args) {
        System.out.println("5");
        Scanner javaInput = new Scanner(System.in);

        System.out.println("Your first name");
        name = javaInput.nextLine();
        System.out.println("Your last name");
        lastName = javaInput.nextLine();
        System.out.println("Your age");
        age = javaInput.nextLine();
        printInfo();
    }
}
  
  /*
  
  to compile and execute code:
  
  javac filname.java: compiles java code 
  checks for erros in the program first
  
  After compiling it will show filename.class instead of filename.java
  to run input java filename;  do not do java filename.class; it doesn't work
  
  ls: list
  
  
  
   */  