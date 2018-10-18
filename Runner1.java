
public class Runner {
	
	public static void main(String args[]) {
		System.out.println("BISECTION");
		Bisection bisect = new Bisection();
		bisect.calc(0, 4, 1, 2);
		
		System.out.println("\n\nNEWTON");
		NewtonRhapson newton = new NewtonRhapson();
		newton.calc(0, 4, 1, 2);
		
		System.out.println("\n\nSECANT");
		Secant secant = new Secant();
		secant.calc(0,  4,  1, 2);
		
		System.out.println("\n\n FALSE POSITION");
		FalsePosition fp = new FalsePosition();
		fp.calc(0, 4, 1, 2);
//		
		System.out.println("\n\n MODIFIED SECANT");
		ModifiedSecant ms = new ModifiedSecant();
		ms.calc(0, 4, 1, 2);
		
	}
	
}
