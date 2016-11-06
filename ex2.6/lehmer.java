package Lehmer;

/**
 * 
 * @author Tiia, Mikko, Matti
 * Our implementation of the (original) Lehmer generator
 * 
 * 
 * Lehmer loop:
 * X_n= (a*X_(n-1)+c) mod m
 * where now  
 * m=10^8+1         a=23            c=0  (the original one, Eniac)
 * 
 * m=2^31-1         a=7^5            c=0  (MINSTD version, from wikipedia)
 * m=2^16+1         a=75             c=0  (Sinclair version, from wikipedia) (this is used in the shuffled version!!)
 * m=2^48-1         a=44485709377909 c=0  (RANF/CRAY version, from wikipedia)
 */

public class LehmerGen {

	/**
	 * Lehmer random number generator X_new = (a*x+c) mod m:
	 * @param a = generator constant
	 * @param x = seed/previous x
	 * @param c = generator constant
	 * @param m = generator constant
	 * @return new (actually non)random integer
	 */
	public static double giverandomnum(double a, double x, double c, double m){
		return (a*x+c)%m;
	}
	
	
	
	/**
	 * Calculates a vector which components are random numbers created by Lehmer generator
	 * @param a = generator constant
	 * @param x = seed/previous random number
	 * @param c = generator constant
	 * @param m = generator constant
	 * @param size = size of vector
	 * @return vector which components are random numbers created by Lehmer generator
	 */
	public static double[] giverandomvec(double a, double x, double c, double m, int size){
		double[] randomvector =new double[size];
		randomvector[0] = giverandomnum(a,x,c,m); 
		
		for(int i=1; i < size; i++){
			randomvector[i] = giverandomnum(a, randomvector[i-1], c,m);
		}
		
		return randomvector;
	}
	
	
	/**
	 * Here we pick some of the components of the original vector
	 * in such a way that if the component i of the original vector
	 * exist in the interval [min,max], we pick the component i-1 
	 * of the original vector.
	 * 
	 * For example, for the vector
	 * {0.3, 0.5, 0.77, 0.234, 0.532, 0.11, 0.005}
	 * and interval [0.5, 0.8]
	 * "vectorpart" gives output
	 * {0.3, 0.5, 0.234}.
	 * 
	 * @param randomvector = given vector which to look
	 * @param min = minimum point of interval
	 * @param max = maximum point of interval
	 * @return subvector
	 */
	public static double[] vectorpart(double[] randomvector, double min, double max){
		int compcount=0;
		for(int i = 1; i < randomvector.length; i++){
			if(randomvector[i] >= min && randomvector[i] <= max){
				compcount++;
			}
		}

		double[] newrandomv = new double[compcount];
		int index = 0;
		
		for(int i = 1; i < randomvector.length; i++){
			if(randomvector[i] >= min && randomvector[i] <= max){
				newrandomv[index] = randomvector[i-1];
				index++;
			}
		}
			return newrandomv;
	}
		
	/**
	 * This is a shuffled Lehmer generator. Random number is created in the following way:
	 * 1. calculate random vector by using Lehmer generator
	 * 2. calculate random number by using the Sinclair version of Lehmer generator ([0,1] interval)
	 * 3. Pick one value from the Lehmer random vector by using random number generated in the step 2.
	 * @param a = Lehmer constant
	 * @param x = seed
	 * @param c = Lehmer constant
	 * @param m = Lehmer constant
	 * @param size = size of the Lehmer vector to be calculated (in which you choose a random number)
	 * @return (shuffled) random number
	 */
	public static double giveshuffled(double a, double x, double c, double m, int size){
		double[] lehmerv= giverandomvec(a,x,c,m, size);
		double rnd01 = giverandomnum(75, 1, 0, 65537)/65537; //sinclair values, gives a number in the interval [0,1]
		Double subindex = lehmerv.length*rnd01;
		int index = subindex.intValue();
		return lehmerv[index];
	}
	
	
	/**
	 * This calculated a vector consisting of random numbers calculated by shuffled Lehmer
	 * @param a = Lehmer constant
	 * @param x = seed
	 * @param c = Lehmer constant
	 * @param m = Lehmer constant
	 * @param size the size of the "shuffled" vector and also the size of Lehmer vector in which
	 * all the component of the "shuffled" vector are chosen
	 * @return
	 */
	public static double[] giveshuffledvec(double a, double x, double c, double m, int size){
		double[] randomvector =new double[size];
		randomvector[0] = giveshuffled(a,x,c,m, size); 
		
		for(int i=1; i < size; i++){
			randomvector[i] = giveshuffled(a, randomvector[i-1], c,m, size);
		}
		
		return randomvector;
	}
	
	/**
	 * Here we print random vectors calculated by original and shuffled Lehmer generator
	 * @param args = not in use
	 */
	public static void main(String[] args){
		
		double a = 23;
		double m = 100000001; //10^8+1
		double c = 0;
		int size = 50;
		double x = 94;
		double[] lehmervec = giverandomvec(a,x,c,m,size);
		System.out.println("=== Original Lehmer =========================");
		for(int i=0; i<size; i++){
			System.out.println(lehmervec[i]/m);
		}
		
		System.out.println("=========================");
		
		double[] lehmervectorpart = vectorpart(lehmervec, 0.0, 0.1);
		
		for(int i=0; i<lehmervectorpart.length; i++){
			System.out.println(lehmervectorpart[i]);
		}
		
		//doesn't give anything => original Lehmer doesn't actually gives numbers
		// from [0,0.1] which is bad => not uniform
		
		System.out.println("=== Shuffled Lehmer =========================");
		

		
		double[] lehmershuffled = giveshuffledvec(a,x,c,m,size);
		
		for(int i=0; i<size; i++){
			lehmershuffled[i]=lehmershuffled[i]/m;
		}
		
		for(int i=0; i<size; i++){
			System.out.println(lehmershuffled[i]);
		}
		
		System.out.println("=========================");
		
        double[] lehmershuffledpart = vectorpart(lehmershuffled, 0.0, 0.1);
		
		for(int i=0; i<lehmershuffledpart.length; i++){
			System.out.println(lehmershuffledpart[i]);
		}
		// gives better result than the original one 
	}
}

