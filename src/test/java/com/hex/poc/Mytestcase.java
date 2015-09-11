import static org.junit.Assert.*;

import org.junit.Test;



public class Mytestcase {
	@Test
	public void newtest(){
		Multiply m=new Multiply();
		assertEquals("10 x 5 must be 50", 50, m.multiply(10, 5));
	}
	
}
