import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Polynomial implements Comparable<Polynomial> {

    // Private integer list created for storing coefficients of polynomials
    private List<Integer> list = new ArrayList<Integer>();

    // Constructor for a variable length of integers and storing each integer into an ArrayList with for each loop
    public Polynomial(int... coefficients) {
        for (int i : coefficients) {
            list.add(i);
        }

        boolean foundNonZero = false;
        for (int i = 0; i < list.size(); i++) {
            if (foundNonZero) {
                break;
            }
            if (list.get(i) == 0) {
                list.remove(i);
                i--;
            } else foundNonZero = true;
        }
    }

    // a method that finds the degree, or the highest exponent value in the polynomial
    public int degree;

    public int degree() {
        for (int i = 0; i < this.list.size() - 1; i++) {
            if (this.list.get(i) != 0) {
                degree = i + 1;

            } else degree = i - 1;
        }
        return degree;
    }

    // a method that converts a Polynomial ArrayList to a readable string
    public String toString() {
        if (degree() == -1)
            return "0";
        else if (degree() == 0)
            return "" + list.get(0);
        else if (degree() == 1)
            return list.get(0) + "x + " + list.get(1);

        String c = list.get(0) + "x^" + degree();
        for (int i = degree() - 1; i >= 0; i--) {
            int pos = (list.size() - 1 - i);
            if (list.get(pos) == 0)
                continue;
            else if (list.get(pos) > 0)
                c = c + " + " + list.get(pos);
            else if (list.get(pos) < 0)
                c = c + " - " + (-1 * list.get(pos));
            if (i == 1)
                c = c + "x";
            else if (i > 1)
                c = c + "x^" + i;
            else if (i == 0)
                c = c;
        }
        return c;
    }


    public int t;
    // a method that shows whether an inputted polynomial is greater than, less than, or equal to another polynomial.
    @Override
    public int compareTo(Polynomial o) {
        // this is the equals part
        int[] equality = new int[this.list.size()];
        if (this.list.size() == o.list.size()) {
            for (int i = 0; i < list.size(); i++) {
                if (this.list.get(i) == o.list.get(i)) {
                    equality[i] = 1;
                }
            }
        } else t = 1;
        for (int i = 0; i < equality.length; i++) {
            if (equality[i] != 1) {
                t = 1;
            } else t = 0;
        }
        // this is the greater than part
        if (this.degree() > o.degree()) {
            t = 1;
        }
        //this is the less than part
        if (this.degree() < o.degree()) {
            t = -1;
        }
        return t;
    }

    // A method that returns true or false if a polynomial is equal to another polynomial
    public boolean equals(Polynomial o) {
        int[] equality = new int[this.list.size()];
        if (this.list.size() == o.list.size()) {
            for (int i = 0; i < list.size(); i++) {
                if (this.list.get(i) == o.list.get(i)) {
                    equality[i] = 1;
                }
            }
        } else return false;
        return true;

// a method that adds two polynomials together
    }
    public Polynomial plus(Polynomial that) {

        Polynomial sum = new Polynomial();

        // Finding which polynomial is larger and adding 0 to the smaller one so that they both have the same number of terms.

        int m = Math.max(this.list.size(), that.list.size());
        int n = Math.min(this.list.size(), that.list.size());

        int dif = m - n;

        int j = (this.list.size() - that.list.size());
        if (j > 0) {
            for (int i = 0; i < dif; i++) {
                that.list.add(0);
            }
        } else if (j < 0) {
            for (int i = 0; i < dif; i++) {
                this.list.add(0);
            }
        }

        // Adding each corresponding term of the polynomials together
        for (int i = 0; i < m; i++) {
            sum.list.add(this.list.get(i) + that.list.get(i));

        }
        return sum;
    }

    // a method that subtracts two polynomials
    public Polynomial minus(Polynomial that) {

        Polynomial sub = new Polynomial();
        // Finding which polynomial is larger and adding 0 to the smaller list so that they both have the same number of terms.
        int m = Math.max(this.list.size(), that.list.size());
        int n = Math.min(this.list.size(), that.list.size());

        int dif = m - n;

        int j = (this.list.size() - that.list.size());

        if (j < 0) {
            for (int i = 0; i < dif; i++) {
                this.list.add(0);
            }
        }
        if (j > 0) {
            for (int i = 0; i < dif; i++) {
                that.list.add(0);
            }
        }
        // Subtracting each corresponding term

        for (int i = 0; i < m; i++) {
            sub.list.add(this.list.get(i) - that.list.get(i));
        }
        return sub;
    }

    // a method that finds the solution of a given polynomial
    public double evaluate(int x) {

        // iterates through the polynomial with the given integer x and returns the answer
        double result = list.get(list.size() - 1);
        for (int i = 1; i < list.size(); i++) {
            result = result + (Math.pow(x, i) * list.get(list.size() - 1 - i));
        }

        return result;
    }

    // colors for printing
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_CYAN = "\u001B[36m";

// the main method which sets up taking of all user inputs
    public static void main(String[] args) {
        System.out.println(ANSI_CYAN + "Welcome to Polynomial Calculator by Alex Lucas! Click on the README file for more info."+ ANSI_RESET);
        Scanner scanner = new Scanner(System.in);

        // asking the user which method they want to use
        System.out.println("Would you like to: 'add', 'subtract', or 'evaluate' polynomials?");
        String userInput1 = scanner.nextLine();

        // parsing the input from the user into a list of integers which can be passed through the Polynomial constructor and then the specific method
        // This performs the Addition function
        if (userInput1.equalsIgnoreCase("add")) {
            System.out.println("Enter the coefficients of the first polynomial seperated by spaces (integers only): ");
            String userInput2 = scanner.nextLine();
//Split the user input using spaces and convert the parts ot integers
            String[] inputArray1 = userInput2.split("\\s+"); //Split by spaces
            int[] numbers1 = new int[inputArray1.length];

            for (int i = 0; i < inputArray1.length; i++) {
                try {
                    numbers1[i] = Integer.parseInt(inputArray1[i]);
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. Please enter integers seperated by spaces.");
                }
            }
            System.out.println("Enter the coefficients of the second polynomial seperated by spaces (integers only): ");
            String userInput3 = scanner.nextLine();
            String[] inputArray2 = userInput3.split("\\s+");
            int[] numbers2 = new int[inputArray2.length];
            for (int i = 0; i < inputArray2.length; i++) {
                try {
                    numbers2[i] = Integer.parseInt(inputArray2[i]);
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. Please enter integers seperated by spaces.");
                }

            }
            Polynomial p = new Polynomial(numbers1);
            Polynomial q = new Polynomial(numbers2);
            String value = p.plus(q).toString();
            System.out.println("Answer: (" + p.toString() + ") + (" + q.toString() + ") = " + value);

            //This performs the subtraction function
        } else if (userInput1.equalsIgnoreCase("subtract")) {
            System.out.println("Enter the coefficients of the first polynomial seperated by spaces (integers only): ");
            String userInput2 = scanner.nextLine();
//Split the user input using spaces and convert the parts ot integers
            String[] inputArray1 = userInput2.split("\\s+"); //Split by spaces
            int[] numbers1 = new int[inputArray1.length];

            for (int i = 0; i < inputArray1.length; i++) {
                try {
                    numbers1[i] = Integer.parseInt(inputArray1[i]);
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. Please enter integers seperated by spaces.");
                }
            }
            System.out.println("Enter the coefficients of the second polynomial seperated by spaces (integers only): ");
            String userInput3 = scanner.nextLine();
            String[] inputArray2 = userInput3.split("\\s+");
            int[] numbers2 = new int[inputArray2.length];
            for (int i = 0; i < inputArray2.length; i++) {
                try {
                    numbers2[i] = Integer.parseInt(inputArray2[i]);
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. Please enter integers seperated by spaces.");
                }

            }
            Polynomial p = new Polynomial(numbers1);
            Polynomial q = new Polynomial(numbers2);
            String value = p.minus(q).toString();
            System.out.println("Answer: (" + p.toString() + ") - (" + q.toString() + ") = " + value);

            // This performs the evaluation function
        } else if (userInput1.equalsIgnoreCase("evaluate")) {
            System.out.println("Enter the coefficients of the first polynomial seperated by spaces (integers only): ");
            String userInput2 = scanner.nextLine();
//Split the user input using spaces and convert the parts ot integers
            String[] inputArray1 = userInput2.split("\\s+"); //Split by spaces
            int[] numbers1 = new int[inputArray1.length];

            for (int i = 0; i < inputArray1.length; i++) {
                try {
                    numbers1[i] = Integer.parseInt(inputArray1[i]);
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. Please enter integers seperated by spaces.");
                }

            }
            System.out.println("What value of x would you like to evaluate the polynomial at? (integers only)");
            String userInput3 = scanner.nextLine();
            int convertedInput3 = Integer.parseInt(userInput3);
            Polynomial p = new Polynomial(numbers1);
            double value = p.evaluate(convertedInput3);
            System.out.println("Answer: " + value);
        }
        else {
            System.out.println("Error! Invalid Input. Run the calculator again and type only 'add', 'subtract', or 'evaluate'.");
        }
    }
}










