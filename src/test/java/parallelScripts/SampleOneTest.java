package parallelScripts;

import org.testng.annotations.Test;

public class SampleOneTest {
	@Test
	  public void testOne() {
		 long id = Thread.currentThread().getId();
		  System.out.println("Test12 in SampleOne..." + id);
	  }
	  @Test
	  public void testTwo() {
		  long id = Thread.currentThread().getId();
		  System.out.println("TestTwo in SampleOne..." + id);
	  }
	  @Test
	  public void testThree() {
		  long id = Thread.currentThread().getId();
		  System.out.println("Test3 in SampleOne..."+id);
	  }
	  @Test(invocationCount = 6, threadPoolSize = 3, timeOut=2000)
	  public void testFour() {
		  long id = Thread.currentThread().getId();
		  System.out.println("Test22 in SampleOne..."+id);
	  }


}
