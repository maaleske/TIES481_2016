package ex2;

import java.io.IOException;

import org.javasim.streams.RandomStream;
import org.javasim.streams.UniformStream;


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
	private double h; //i'm not sure if this has to be changed (removed)
	
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
		h=2/range; // this comes from the fact that the area of triangle must be 1
		
	}

	@Override
	/**
	 * Get number from triangular stream. Idea:
	 * 1. get one random number from the interval [0,h] (y value)
	 * 2. get x-value in such a way that boundaries of x-values are
	 *    defined from the linear equations (the edges of the triangle)
	 */
	public double getNumber() throws IOException, ArithmeticException {
		
		double y= uniform()*h;
		double xrange = ((-1)*y*(up-pe)/h+up)-(y*(pe-lo)/h+lo);
		double x = y*(pe-lo)/h+lo+uniform()*xrange;
		
		
		return x;
	}
	
	/**
	 * We test properties of triangular stream by printing (and then plotting by using another program)
	 * @param args not used
	 * @throws IOException 
	 * @throws ArithmeticException 
	 */
	public static void main(String[] args) throws ArithmeticException, IOException{
		double[] uniformnumbers = new double[30];
		double[] triangularnumbers = new double[30];
		
		TriangularStream tstream = new TriangularStream(1,2,10);
		UniformStream ustream= new UniformStream(1,10);
		
		for(int i=0; i<30; i++){
			triangularnumbers[i] = tstream.getNumber();
			uniformnumbers[i] =ustream.getNumber();
		}
		
		System.out.println("Triangular:");
		for(int i=0; i<30; i++){
			System.out.println(triangularnumbers[i]);
		}
		
		System.out.println("Uniform:");
		for(int i=0; i<30; i++){
			System.out.println(uniformnumbers[i]);
		}
	}

}

