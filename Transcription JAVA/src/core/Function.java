package core;
import org.apache.commons.math3.analysis.*;
import org.apache.commons.math3.analysis.differentiation.*;

public class Function implements UnivariateFunction {
			
	double[] input  ; 
		
	public Function(double[] input){
			this.input = input ; 
		}
		
	public final double value(double arg){
			return input[(int)arg] ; 
		}
	
	}
	
