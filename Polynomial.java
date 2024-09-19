public class Polynomial{
	public double[] coefficients;
	
	public Polynomial() {
		this.coefficients = new double[] {0};
	}
	
	public Polynomial(double[] coefficients){
		this.coefficients = coefficients;
	}
	
	public Polynomial add(Polynomial poly) {
		double x = 0;
		double y = 0;
		int maxlength = Math.max(this.coefficients.length, poly.coefficients.length);
		double [] result = new double [maxlength];
		for(int i=0; i<maxlength; i++) {
			if(i < this.coefficients.length) {
				x = this.coefficients[i];
			}
			if(i < poly.coefficients.length) {
				y = poly.coefficients[i];
			}
			result[i] = x + y;
			x = 0;
			y = 0;
		}
		return new Polynomial(result);
	}
	
	public double evaluate(double x) {
		double result = 0;
		for(int i=0; i< this.coefficients.length; i++) {
			double val = Math.pow(x, i);
			result = result + this.coefficients[i] * val;
		}
		return result;
	}
	
	public boolean hasRoot(double x) {
		if(evaluate(x) == 0) return true;
		else return false;
	}
}