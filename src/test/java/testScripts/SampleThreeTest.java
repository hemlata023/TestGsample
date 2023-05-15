package testScripts;

import org.testng.annotations.Test;

public class SampleThreeTest {
	@Test(groups = "feature2")
	  public void testOne() {
		  System.out.println("Test12 in SampleThree");
	  }
	  @Test
	  public void testTwo() {
		  System.out.println("TestTwo in SampleThree");
	  }
	  @Test(groups = "feature1")
	  public void testThree() {
		  System.out.println("Test3 in SampleThree");
	  }
	  @Test(groups = "feature3")
	  public void testFour() {
		  System.out.println("Test22 in SampleThree");
	  }

}
