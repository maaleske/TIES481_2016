package ex2;

import java.io.IOException;

import org.javasim.streams.RandomStream;


/**
 * Returns a number drawn from a triangular distribution with 
 * given lower and upper bounds
 * @author Tiia, Mikko, Matti autumn 2016
 *
 */
public class TriangularStream extends RandomStream {
	private double lo;
	private double up;
	private double pe;
	private double h=1.0; //i'm not sure if this has to be changed (removed)
	
	private double range;

	
	/**
	 * Creates a triangular stream with
	 * @param l = lower bound
	 * @param p = most probable value (peak)
	 * @param u = upper bound
	 */
	public TriangularStream(double l, double p, double u){
		super();
		
		lo =l;
		up= u;
		pe=p;
		
		range = up-lo;
		
	}

	@Override
	/**
	 * TODO xrange lauseke menee varmaan siistimpään muotoon
	 * TODO tarkista kaikki, testaa
	 */
	public double getNumber() throws IOException, ArithmeticException {
		
		double y= uniform()*h;
		double xrange = ((-1)*y*(up-pe)/h+up)-y*(pe-lo)/h*lo;
		double x = y*(pe-lo)/h+lo+uniform()*xrange;
		
		
		return x;
	}

}

