import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Driver {
	public static void main(String [] args) throws FileNotFoundException, IOException {
		Polynomial p = new Polynomial();
		System.out.println(p.evaluate(3));
		double [] c1 = {2,1};
		int [] e1 = {1,2};
		Polynomial p1 = new Polynomial(c1, e1);
		double [] c2 = {5,-9};
		int [] e2 = {2,0};
		Polynomial p2 = new Polynomial(c2, e2);
		Polynomial s = p1.add(p2);
		System.out.println("------------------------");
		System.out.print("The coefficients are: ");
		for(int i = 0; i < s.coefficients.length; i++) {
			System.out.print(s.coefficients[i] + ",");
		}
		System.out.println("");
		
		for(int i = 0; i < s.exponents.length; i++) {
			System.out.print(s.exponents[i] + ",");
		}
		System.out.println("");
		System.out.println("------------------------");
		Polynomial j = p1.multiply(p2);
		System.out.println("------------------------");
		System.out.print("The coefficients are: ");
		for(int i = 0; i < j.coefficients.length; i++) {
			System.out.print(j.coefficients[i] + ",");
		}
		System.out.println("");
		
		for(int i = 0; i < j.exponents.length; i++) {
			System.out.print(j.exponents[i] + ",");
		}
		System.out.println("");
		System.out.println("------------------------");
		
		
		
		System.out.println("s(0.1) = " + s.evaluate(0.1));
		if(s.hasRoot(1))
			System.out.println("1 is a root of s");
		else
			System.out.println("1 is not a root of s");
		
		
		// READ
		
		System.out.println("----------------");	
		File f = new File("polyfile.txt");
		Polynomial poly_file = new Polynomial(f);
		System.out.println("------------------------");
		System.out.print("The coefficients are: ");
		for(int i = 0; i < poly_file.coefficients.length; i++) {
			System.out.print(poly_file.coefficients[i] + ",");
		}
		System.out.println("");
		
		for(int i = 0; i < poly_file.exponents.length; i++) {
			System.out.print(poly_file.exponents[i] + ",");
		}
		System.out.println("");
		System.out.println("------------------------");
		
		// SAVE
		s.saveToFile("test");
		s = new Polynomial();
		s.saveToFile("Test_blank");
	}
}