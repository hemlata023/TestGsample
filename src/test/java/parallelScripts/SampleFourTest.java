package parallelScripts;

import org.testng.annotations.Test;

public class SampleFourTest {
	@Test
	  public void testOne() {
		 long id = Thread.currentThread().getId();
		  System.out.println("Test41 in SampleFour...."+id);
	  }
	  @Test
	  public void testTwo() {
		  long id = Thread.currentThread().getId();
		  System.out.println("Test42 in SampleFour...."+id);
	  }
	  @Test
	  public void testThree() {
		  long id = Thread.currentThread().getId();
		  System.out.println("Test43 in SampleFour...."+id);
	  }
	  @Test
	  public void testFour() {
		  long id = Thread.currentThread().getId();
		  System.out.println("Test44 in SampleFour...."+id);
	  }
}
