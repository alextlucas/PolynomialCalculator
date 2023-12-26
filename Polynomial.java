import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Polynomial implements Comparable<Polynomial> {

    // Private integer list created for storing coefficients of polynomials
    private List<Integer> list = new ArrayList<Integer>();

    // Constructor for a variable length of integers and storing each integer into an ArrayList with for each loop
    public Polynomial(int... coefficients) {
        // figure out how to add to list so they're not in reverse order
        for (int i : coefficients) {
            list.add(i);
        }
        boolean foundNonZero = false;
        for(int i = 0; i < list.size(); i++){
            if(foundNonZero){
                break;
            }
            if(list.get(i) == 0){
                list.remove(i);
                i--;
            }
            else foundNonZero = true;
        }
    }

    // a method that finds the degree, or the highest exponent value in the polynomial
    public int degree;

    public int degree() {
        for (int i = 0; i < this.list.size() - 1; i++) {
            if (this.list.get(i) != 0) {
                 degree = i + 1;

            }
            else degree = i-1;
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
        for(int i = degree() - 1; i >= 0; i--) {
            int pos = (list.size()-1-i);
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

    @Override
    public int compareTo(Polynomial o) {
        // add greater than part here

        //less than part here
// this is the equals part
        int[] equality = new int[this.list.size()];
        if (this.list.size() == o.list.size()) {
            for (int i = 0; i < list.size(); i++) {
                if (this.list.get(i) == o.list.get(i)) {
                    equality[i] = 1;
                }
            }
        }
        else return 1;
        for (int i = 0; i<equality.length;i++){
            if(equality[i]!=1){
                return 1;
            }
            else return 0;
        }
        return 1;
    }
}





