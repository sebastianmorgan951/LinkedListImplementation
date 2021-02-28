/**Author: Sebastian Morgan and CSE Tutors
 * Email: sjmorgan@ucsd.edu
 * Date: 1/18/2021
 *
 * This file is a test file, testing our implementation of our Lists, Choosers,
 * and Transformers. This runs with the parametrized option, allowing us to apply
 * each test to each List we've defined.
 * **/

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collection;

import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

/* This class allows us to test our implementation of MyList, MyChooser, and
* MyTransformer using junit test and parametrized classes for our MyList interface.
* */
@RunWith(Parameterized.class)
public class TestLists {

	public static Collection<Object[]> LISTNUMS =
			Arrays.asList(new Object[][] { {"Linked"}, {"Array"} });
	private String listType;

	public TestLists(String listType) {
		super();
		this.listType = listType;
	}

	@Parameterized.Parameters(name = "{0}List")
	public static Collection<Object[]> bags() {
		return LISTNUMS;
	}

	private <E> MyList<E> makeList(E[] contents) {
		switch (this.listType) {
		case "Linked":
			return new LinkedGL<E>(contents);
		case "Array":
			return new ArrayGL<E>(contents);
		}
		return null;
	}

  // Don't change code above this line, it ensures the autograder works as
  // expected


  // This is a sample test; you can keep it, change it, or remove it as you like.
  // Note that it uses the method `assertArrayEquals`, which you should use to
  // test equality of arrays in this PA.
	@Test
	public void testSimpleToArray() {
		// Using the generic list to create an Integer list
		Integer[] int_input = {1, 2, 3};
		MyList<Integer> int_s = makeList(int_input);
		assertArrayEquals(int_input, int_s.toArray());
		
		// Using the generic list to create a String list
		String[] string_input = {"a", "b", "c"};
		MyList<String> string_s = makeList(string_input);
		assertArrayEquals(string_input, string_s.toArray());
	}

	/* This test will check if our chooser functions act as expected */
	@Test
	public void testChooseAll() {
		// Testing the multiple of 12 chooser
		Integer[] int_input = {1, 2, 3, 12, 0, null, 24, 48};
		Integer[] intExpected = {12,0,24,48};
		MyList<Integer> int_s = makeList(int_input);
		int_s.chooseAll(new MultipleOfTwelveChooser());
		assertArrayEquals(intExpected, int_s.toArray());

		// Testing the capitalized word chooser
		String[] string_input = {"", "b", "C", "car", "Door", "bUN", null, "RUDE"};
		String[] strExpected = {"C","Door","RUDE"};
		MyList<String> string_s = makeList(string_input);
		string_s.chooseAll(new CapitalizedWordChooser());
		assertArrayEquals(strExpected, string_s.toArray());

		//Testing the has a 3 chooser
		Double[] double_input = {0.0, 1.23, 3.0, null, -4.20, 1.1111113, -1.2463};
		Double[] dblExpected = {0.0,-4.20};
		MyList<Double> double_s = makeList(double_input);
		double_s.chooseAll(new HasAThreeChooser());
		assertArrayEquals(dblExpected, double_s.toArray());
	}

	/* This test will test if our transform methods work for each array */
	@Test
	public void testTransformAll() {
		// Testing the toFibonacciCounterpart transformer
		Integer[] int_input = {1, 2, 3, 12, 0, null, 44, 48};
		Integer[] intExpected = {1, 1, 2, 144, 0, null, 701408733, 48};
		MyList<Integer> int_s = makeList(int_input);
		int_s.transformAll(new IntegerToFibonacciCounterpartTransformer());
		assertArrayEquals(intExpected, int_s.toArray());

		// Testing the plural word remover transformer
		String[] string_input = {"Is", "B", "", "cars", "Bill's", "mass", null, "miles"};
		String[] strExpected = {"Is", "B", "", "car", "Bill's", "mass", null, "mile"};
		MyList<String> string_s = makeList(string_input);
		string_s.transformAll(new RemovePluralsTransformer());
		assertArrayEquals(strExpected, string_s.toArray());

		//Testing the toSquareArea transformer
		Double[] double_input = {0.0, 1.23, 3.0, null, -4.20, 1.1111113, -1.2463};
		Double[] dblExpected = {0.0, 1.23*1.23, 9.0, null, -4.20,
				1.1111113*1.1111113, -1.2463};
		MyList<Double> double_s = makeList(double_input);
		double_s.transformAll(new DoubleToSquareAreaTransformer());
		assertArrayEquals(dblExpected, double_s.toArray());
	}

	/* This test will check if anything weird happens with input empty arrays */
	@Test
	public void testEmptyArrayInput(){
		Integer[] int_input = {};
		Integer[] intExpected = {};
		MyList<Integer> int_s = makeList(int_input);
		assertArrayEquals(intExpected, int_s.toArray());

		// Testing the plural word remover transformer
		String[] string_input = {};
		String[] strExpected = {};
		MyList<String> string_s = makeList(string_input);
		assertArrayEquals(strExpected, string_s.toArray());

		//Testing the toSquareArea transformer
		Double[] double_input = {};
		Double[] dblExpected = {};
		MyList<Double> double_s = makeList(double_input);
		assertArrayEquals(dblExpected, double_s.toArray());
	}

	/* This test will check if anything weird with null value arrays */
	@Test
	public void testNullArrayInput(){
		//Testing with a 1 element null array input
		Integer[] int_input = {null};
		Integer[] intExpected = {null};
		MyList<Integer> int_s = makeList(int_input);
		assertArrayEquals(intExpected, int_s.toArray());

		// With a 2 element null array
		String[] string_input = {null, null};
		String[] strExpected = {null, null};
		MyList<String> string_s = makeList(string_input);
		assertArrayEquals(strExpected, string_s.toArray());

		//Testing the null imput for a huge null array
		Double[] double_input = new Double[1000];
		Double[] dblExpected = new Double[1000];
		for(int i = 0 ; i < 1000; i += 1){
			double_input[i] = null;
			dblExpected[i] = null;
		}
		MyList<Double> double_s = makeList(double_input);
		assertArrayEquals(dblExpected, double_s.toArray());
	}

	/* This test checks if anything weird happens with called .transformAll on
	* an empty array */
	@Test
	public void testEmptyArrayTransform(){
		Integer[] int_input = {};
		Integer[] intExpected = {};
		MyList<Integer> int_s = makeList(int_input);
		int_s.transformAll(new IntegerToFibonacciCounterpartTransformer());
		assertArrayEquals(intExpected, int_s.toArray());

		// Testing the plural word remover transformer
		String[] string_input = {};
		String[] strExpected = {};
		MyList<String> string_s = makeList(string_input);
		string_s.transformAll(new RemovePluralsTransformer());
		assertArrayEquals(strExpected, string_s.toArray());

		//Testing the toSquareArea transformer
		Double[] double_input = {};
		Double[] dblExpected = {};
		MyList<Double> double_s = makeList(double_input);
		double_s.transformAll(new DoubleToSquareAreaTransformer());
		assertArrayEquals(dblExpected, double_s.toArray());
	}

	/* This test checks if anything weird happens with called .chooseAll on
	 * an empty array */
	@Test
	public void testEmptyArrayChoose(){
		Integer[] int_input = {};
		Integer[] intExpected = {};
		MyList<Integer> int_s = makeList(int_input);
		int_s.chooseAll(new MultipleOfTwelveChooser());
		assertArrayEquals(intExpected, int_s.toArray());

		// Testing the plural word remover transformer
		String[] string_input = {};
		String[] strExpected = {};
		MyList<String> string_s = makeList(string_input);
		string_s.chooseAll(new CapitalizedWordChooser());
		assertArrayEquals(strExpected, string_s.toArray());

		//Testing the toSquareArea transformer
		Double[] double_input = {};
		Double[] dblExpected = {};
		MyList<Double> double_s = makeList(double_input);
		double_s.chooseAll(new HasAThreeChooser());
		assertArrayEquals(dblExpected, double_s.toArray());
	}

	/* This test checks that the empty method returns false if the list isn't empty
	*  */
	@Test
	public void testEmptyReturnsFalse(){
		//Testing for nonEmpty return on integer list
		Integer[] int_input = {1,3,6,null,0,132412};
		MyList<Integer> int_s = makeList(int_input);
		assertEquals(false, int_s.isEmpty());

		//Testing for nonEmpty return on string list
		String[] string_input = {"",null,"input","stuff"};
		MyList<String> string_s = makeList(string_input);
		assertEquals(false, string_s.isEmpty());

		//Testing for nonEmpty return on double list
		Double[] double_input = {null};
		MyList<Double> double_s = makeList(double_input);
		assertEquals(false, double_s.isEmpty());
	}

	/* This checks that our empty test returns true if the input is empty */
	@Test
	public void testEmptyReturnsTrue(){
		//Same behaviour should be exhibited for any type with this
		Integer[] int_input = {};
		MyList<Integer> int_s = makeList(int_input);
		assertEquals(true, int_s.isEmpty());
	}

}