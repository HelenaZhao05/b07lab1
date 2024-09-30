import java.util.Scanner;
import java.io.File;
import java.lang.*; 
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;

public class Polynomial{
	public double[] coefficients;
	public int[] exponents;
	
	public Polynomial() {
		this.coefficients = new double[0];
		this.exponents = new int[0];
	}
	
	public Polynomial(double[] coefficients, int[] exponents){
		this.coefficients = coefficients;
		this.exponents = exponents;
	}
	
	public Polynomial(File file) throws FileNotFoundException{
		Scanner scanner = new Scanner(file);
		String text = scanner.nextLine();
		String [] terms = text.split("[+-]");
		int[] sign;

		if(terms[0].isEmpty()) {
			int a = 0;
			sign = new int[terms.length - 1];
			for(int i=0; i < text.length(); i++) {
				if(text.charAt(i) == '+') {
					sign[a] = 1;
					a += 1;
				}
				else if(text.charAt(i) == '-') {
					sign[a] = -1;
					a += 1;
				}
			}
		}
		else {
			int a = 1;
			sign = new int[terms.length];
			sign[0] = 1;
			for(int i=1; i < text.length(); i++) {
				if(text.charAt(i) == '+') {
					sign[a] = 1;
					a += 1;
				}
				else if(text.charAt(i) == '-') {
					sign[a] = -1;
					a += 1;
				}
				
			}
		}
		for(int i = 0; i < sign.length; i++) {
			System.out.println(sign[i]);
		}
	
		int [] exponents = new int[sign.length];
		int a = 0;
		double [] coefficients = new double[sign.length];
		
		for(int i=0; i < terms.length; i++) {
			if(terms[0].isEmpty()) {
				continue;
			}
			String [] splitted_terms = new String[2];
			splitted_terms = terms[i].split("x");
			coefficients[a] = sign[a] * Double.parseDouble(splitted_terms[0]);
			if(splitted_terms.length == 1) {
				exponents[a] = 0;
				a++;
			}
			else {
				exponents[a] = Integer.parseInt(splitted_terms[1]);
				a++;
			}
		}
		this.coefficients = coefficients;
		this.exponents = exponents;
		scanner.close();
	}
	
	public Polynomial add(Polynomial poly) {
		int maxlength = poly.exponents.length + this.exponents.length;
		double [] resultco = new double [maxlength];
		int [] resultex = new int [maxlength];
		for(int i=0; i<this.exponents.length; i++) {
			resultex[i] = this.exponents[i];
			resultco[i] = this.coefficients[i];
		}
		
		int position = this.coefficients.length;
		for(int i=0; i<poly.exponents.length; i++) {
			
			int a = 1;
			for(int j=0; j<position; j++) {
				if(poly.exponents[i] == resultex[j]) {	
					resultco[j] += poly.coefficients[i];
					a = 0;
					break;
				}
			}
			
			if(a == 1){
				resultex[position] = poly.exponents[i];
				resultco[position] = poly.coefficients[i];
				position += 1;
			}
		}
		int counter = 0;
		for(int i=0; i < position; i++) {
			if(resultco[i] != 0) {
				counter += 1;
			}
		}
		int index = 0;
		double [] finalco = new double [counter];
		int [] finalex = new int [counter];
		for(int i=0; i < position; i++) {
			if(resultco[i] != 0) {
				finalco[index] = resultco[i];
				finalex[index] = resultex[i];
				index += 1;
			}
		}
		
		return new Polynomial(finalco,finalex);
	}
	
	public Polynomial multiply(Polynomial poly) {
		Polynomial result = new Polynomial(); 
		for(int i=0; i < poly.coefficients.length; i++) {
			double [] medco = new double [this.coefficients.length];
			int [] medex = new int [this.coefficients.length];
			for(int j=0; j < this.coefficients.length; j++) {
				medco[j] = poly.coefficients[i] * this.coefficients[j];
				medex[j] = poly.exponents[i] + this.exponents[j];
			}
			
			Polynomial med = new Polynomial(medco, medex);
			
			result = result.add(med);
		}
		return result;
	}
	
	public double evaluate(double x) {
		double result = 0;
		for(int i=0; i< this.coefficients.length; i++) {
			double val = Math.pow(x, this.exponents[i]);
			result = result + this.coefficients[i] * val;
		}
		return result;
	}
	
	public boolean hasRoot(double x) {
		if(evaluate(x) == 0) return true;
		else return false;
	}
	public void saveToFile(String fileName) throws IOException {
		FileWriter fw = new FileWriter(fileName);
		String [] terms = new String [this.coefficients.length];
		if (this.coefficients.length == 0) {
			String string_print = "0";
			fw.write(string_print);
			fw.close();
			return;
		}
		if(this.exponents[0] != 0) {
			terms[0] = Double.toString(this.coefficients[0]) + 'x' + Integer.toString(this.exponents[0]);
		}
		else {
			terms[0] = Double.toString(this.coefficients[0]);
		}
		for(int i=1; i < this.coefficients.length; i++) {
			if(this.coefficients[i] > 0) {
				if(this.exponents[i] != 0) {
					terms[i] = '+' + Double.toString(this.coefficients[i]) + 'x' + Integer.toString(this.exponents[i]);
				}
				else {
					terms[i] = '+' + Double.toString(this.coefficients[i]);
				}
			}
			else {
				if(this.exponents[i] != 0) {
					terms[i] = Double.toString(this.coefficients[i]) + 'x' + Integer.toString(this.exponents[i]);
				}
				else {
					terms[i] = Double.toString(this.coefficients[i]);
				}
			}
		}
		for(int i = 0; i < this.coefficients.length; i++) {
			fw.write(terms[i]);
		}
		fw.close();
	}
}