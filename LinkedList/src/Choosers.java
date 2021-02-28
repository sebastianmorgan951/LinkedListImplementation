/**Author: Sebastian Morgan
 * Email: sjmorgan@ucsd.edu
 * Date: 1/18/2021
 *
 * This file contains several classes, each of which implements the MyChooser
 * interface, which forces these classes to override the chooseElement method,
 * allowing us to use these chooser methods within our other generic list
 * classes which extend from MyList. These "chooseElement" methods will help
 * us to filter arrays which extend from MyList, choosing only elements which
 * pass the tests implemented in these chooser classes.
 * **/

/* This class overrides chooseElement from the implemented interface, to be
* called from our list classes. */
class LongWordChooser implements MyChooser<String> {

	/**This method returns true if the input String has length at least 6,
	 * false return otherwise.
	 *
	 * @param s the string to check
	 *
	 * @return true if s has length greater than 5, false otherwise
	 * **/
	@Override
	public boolean chooseElement(String s) {
		return s.length() > 5;
	}

}

/* This class overrides chooseElement from the implemented interface, this can
 * be used on our Lists of type String to filter the list according to the method
 * defined within this class. */
class CapitalizedWordChooser implements MyChooser<String>{

	/**This method returns true if the input String has a capital letter at
	 * String index 0 (the first char of the string), returns false if the
	 * string is not capitalized or if the string is empty/null
	 *
	 * @param s the string to check
	 *
	 * @return true if s has a capital first char, false if not capitalized
	 * or if s is null or empty
	 * **/
	@Override
	public boolean chooseElement(String s) {
		//Return false for empty strings or null strings, this is because only
		//Strings of length >= 1 can have a first index which is capitalized
		if(s == null || s.length() == 0){
			return false;
		}
		//Return true if string has a capital first character, false if not
		return Character.isUpperCase(s.charAt(0));
	}
}

/* This class overrides chooseElement from the implemented interface, this can
 * be used on our Lists of type Integer to filter the list according to the method
 * defined within this class. */
class MultipleOfTwelveChooser implements MyChooser<Integer>{

	/**This method returns true if the input Integer is divisible by 12
	 *
	 * @param i the integer to check
	 *
	 * @return true if i is divisible by 12, false otherwise or if i is null
	 * **/
	@Override
	public boolean chooseElement(Integer i){
		//Check for null input and remove if so
		if(i == null){
			return false;
		}
		//returns true if int is divisible by 0, false otherwise
		return i % 12 == 0;
	}
}

/* This class overrides chooseElement from the implemented interface, this can
* be used on our Lists of type Double to filter the list according to the method
* defined within this class. */
class HasAThreeChooser implements MyChooser<Double>{

	/**This method returns true if the input double contains a 3 at some point
	 * within the double, returns false otherwise
	 *
	 * @param d the double to check
	 *
	 * @return true if d contains a "3", false if not or if d is null
	 * **/
	@Override
	public boolean chooseElement(Double d){
		//Check for null input
		if(d == null){
			return false;
		}
		//Convert the double to a string, we are able to now look for specific
		//numbers in this string, if we find a 3, return false, if not return true
		return !(d.toString().contains("3"));
	}
}