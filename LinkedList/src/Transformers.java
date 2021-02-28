/**Author: Sebastian Morgan
 * Email: sjmorgan@ucsd.edu
 * Date: 1/18/2021
 *
 * This file contains several classes, each of which implements the MyTransformer
 * interface, which forces these classes to override the transformElement method,
 * allowing us to use these transforming methods within our other generic list
 * classes which extend from MyList. These "transformElement" methods will help
 * us to alter arrays which extend from MyList, transforming each element in
 * the list if possible according to the methods defined in the Transformer class
 * called.
 * **/

/*UpperCaseTransformer has one method which will allow us to manipulate strings,
* returning their upper case counterparts. */
class UpperCaseTransformer implements MyTransformer<String> {

	/** transformElement will take an input String, then return its upper case
	 * counterpart.
	 *
	 * @param s, the input string
	 * @return the upper case version of s
	 * **/
	public String transformElement(String s) {
		return s.toUpperCase();
	}

}

// Add your transformers here
/* RemovePluralsTransformer has one method which will allow us to manipulate
* strings, returning their non-plural counterparts. */
class RemovePluralsTransformer implements MyTransformer<String>{

	/** transformElement will take an input String, then return its non-plural
	 * counterpart.
	 *
	 * @param s, the input string
	 * @return the non-plural version of s, if s was interpreted as plural
	 * **/
	@Override
	public String transformElement(String s) {
		/*Null case check, also plurals don't really apply to super short
		* words, so we check both of those and leave them unchanged*/
		if(s == null || s.length() < 3){
			return s;
		}
		char secondToLastChar = s.charAt(s.length()-2);
		/*The idea here is to ensure that we don't remove Strings that end in
		* "ss", as these words like "mass" aren't plural but end in "s". Also,
		* we ensure that we don't alter words that are possessive, like "Bill's",
		* as this isn't actually plural. We do miss a few words out with this
		* method, like "gras" or other strange words that end with an "s", but
		* for most case, this simple check should work! */
		if(secondToLastChar == '\'' ||
			Character.toLowerCase(secondToLastChar) == 's'){
			return s;
		}
		//If above cases aren't true and the last letter of the string is an "s",
		// likely the word is plural, so remove the "s" at the end
		if(Character.toLowerCase(s.charAt(s.length()-1)) == 's'){
			return s.substring(0,s.length()-1);
		}
		//If all these tests above don't check to true, then we have a normal,
		//long string without an "s" at the end, it isn't plural, so leave it
		return s;
	}
}

/* IntegerToFibonacciCounterpartTransformer has one method which will allow us
 * to find the fibonacci number corresponding to the integer input. */
class IntegerToFibonacciCounterpartTransformer implements MyTransformer<Integer>{

	/** transformElement will take an integer = 'n', then interpret that 'n' as
	 * referring to the 'n'th element of fibonacci sequence. If possible, it will
	 * make that conversion, and return the element of the fibonacci sequence.
	 *
	 * @param integer, the input integer
	 * @return the element of the fibonacci sequence corresponding to the input
	 * integer.
	 * **/
	@Override
	public Integer transformElement(Integer integer) {
		/*First, we check for a null integer, and leave that unchanged if so
		* Then, check for an integer < 2. If the integer is negative, we can't
		* 	find a corresponding fibonacci number, so we return those, if the
		* 	integer = 0, the fibonacci number corresponding to that is 0, if
		* 	the integer = 1, the corresponding number should be 1 as well, so
		* 	we can include those two values in this check and return integer.
		* Finally, we check for integer > 45. If so, the corresponding fibonacci
		* 	number will be too large to store as an Integer, so we leave integers
		* 	larger than 45 alone as well. */
		if(integer == null || integer < 2 || integer > 45){
			return integer;
		}
		int index = integer;
		/*Finally, at this point we have 2 <= integer <= 45, now we can use a
		* defined recursive helper method to find the fibonacci number
		* corresponding to the index. We start counting at the 2nd index
		* because our aforementioned tests find the 1st and 0th index */
		return fibonacci(index-2,0,1);
	}

	/** fibonacci will take an input index of the fibonacci sequence to calculate,
	 * the smaller and larger integer to start adding together to begin the
	 * sequence, and returns the fibonacci number at the index-th position of the
	 * sequence.
	 *
	 * @param index, how far into the sequence to calculate
	 * @param smaller, the smaller number being added at the current step of the
	 *                 sequence
	 * @param larger, the larger number being added at the current step of the
	 *                sequence
	 * @return the fibonacci number at the index-th position of the sequence
	 * **/
	private int fibonacci(int index, int smaller, int larger){
		//Updating our larger and smaller values on each recursion
		larger = larger + smaller;
		smaller = larger - smaller;
		//Every recursion, decrease index by 1, if we aren't at 0 yet, we simply
		//recurse again, going along the sequence, if we finally get to 0, stop
		//recursing and return the current number in the sequence
		if(index != 0){
			return fibonacci(index-1,smaller,larger);
		}
		return larger;
	}
}

/* DoubleToSquareAreaTransformer has one method which will allow us to manipulate
 * doubles, squaring the input double. */
class DoubleToSquareAreaTransformer implements MyTransformer<Double>{

	/** transformElement will take a double = 'n', then interpret that 'n' as
	 * referring to the side length of a square. If possible, it will
	 * calculate the area of the square corresponding to that side length, and
	 * return that area.
	 *
	 * @param aDouble, the input side length of the square
	 * @return the area of a square with aDouble side length
	 * **/
	@Override
	public Double transformElement(Double aDouble) {
		//Check for null, then return, also we can't have a negative square side
		//length, so in that case, we also return aDouble
		if(aDouble == null || aDouble < 0){
			return aDouble;
		}
		//If we have a positive side length, return its square
		return aDouble*aDouble;
	}
}