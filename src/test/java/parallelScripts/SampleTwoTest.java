package parallelScripts;

import org.testng.annotations.Test;

public class SampleTwoTest {
	@Test
	  public void testOne() {
			long id = Thread.currentThread().getId();
		  System.out.println("Test21 in SampleTwo...."+id);
	  }
	  @Test
	  public void testTwo() {
		  long id = Thread.currentThread().getId();
		  System.out.println("Test22 in SampleTwo...."+id);
	  }
	  @Test
	  public void testThree() {
		  long id = Thread.currentThread().getId();
		  System.out.println("Test23 in SampleTwo...."+id);
	  }
//	  @Test(invocationCount = 6,threadPoolSize = 2)
	  @Test
	  public void testFour() {
		  long id = Thread.currentThread().getId();
		  System.out.println("Test24 in SampleTwo...."+id);
	  }

}
