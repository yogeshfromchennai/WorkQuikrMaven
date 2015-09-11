import static org.junit.Assert.*;

import org.junit.Test;



public class NewTest {
	@Test
	public void test(){
		Multiply mi=new Multiply();
		assertEquals("5 x 5 must be 25", 25, mi.multiply(5, 5));
	}
	
}
