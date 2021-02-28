/**Author: Sebastian Morgan and CSE Tutors
 * Email: sjmorgan@ucsd.edu
 * Date: 1/18/2021
 *
 * This file implements a generic list with a very small amount of capabilities.
 * This class mainly exists to allow us to store an array in a different way
 * and alter the elements in ways defined by our choosers and transformers
 * **/

/* This class implements MyList, so it has to override the 4 methods introduced
 * there. This is a generic class, and can be applied to any object. This class
 * will allow us to convert normal arrays to this specific kind of array, and back.
 * We will also be able to call chooser and transformer methods from here, altering
 * the list from within the class in specific ways. */
public class ArrayGL<E> implements MyList<E> {

    E[] elements;
    int size;

    /** This constructor simply assigns the input array to our elements field,
     * then gives our size field the size of the input array
     *
     * @param initialElements, the array to build our ArrayGL from
     * **/
    public ArrayGL(E[] initialElements) {
        this.elements = initialElements;
        this.size = initialElements.length;
    }

    /** This method turns our ArrayGL back into an array, returning the array
     *
     * @return the array representation of this ArrayGL.*/
    @Override
    @SuppressWarnings("unchecked")
    public E[] toArray() {
        //Make the array to return, we can only typecast instead of creating a
        //new E[] element directly cuz of Java quirkiness, and the compiler
        //wants to throw an error that we don't know if this typecast works,
        //but we know it does because E is an Object, so we can simply suppress
        //this warning
        E[] toRet = (E[])(new Object[this.size]);
        //Fill toRet array
        for(int i = 0; i < this.size; i += 1){
            toRet[i] = this.elements[i];
        }
        return toRet;
    }

    /** transformAll attempts to change every value within our ArrayGL to the
     * transformed version
     *
     * @param mt, the class which extends from MyTransformer to draw our
     *            transform method from
     * */
    @Override
    @SuppressWarnings("unchecked")
    public void transformAll(MyTransformer mt) {
        //We have to typecast in this for loop to tell the compiler that we are
        //getting a value of type E to store, compiler would usually throw an
        //error because of this typecast, so we suppresswarnings to stop that
        for(int i = 0; i < this.size; i += 1){
            this.elements[i] = (E) mt.transformElement(this.elements[i]);
        }
    }

    /** chooseAll will filter out values from our ArrayGL which aren't accepted
     * by the Chooser class called
     *
     * @param mc, the class which extends from MyChooser to draw our
     *            chooser method from
     * */
    @Override
    @SuppressWarnings("unchecked")
    public void chooseAll(MyChooser mc) {
        //Initialize newSize and OldElementsIndex to be defined outside of for
        //loops in this method
        int newSize = 0;
        int oldElementsIndex = 0;
        //First, this for loop tells us the size of the new array we need to
        //create to store our filtered elements
        for(int i = 0; i < this.size; i += 1){
            if(mc.chooseElement(this.elements[i])){
                newSize += 1;
            }
        }
        //Create a new array to store our filtered elements in
        E[] toReplace = (E[]) (new Object[newSize]);
        //This for loop puts the elements we want to keep in our array to replace
        for(int i = 0; i < newSize; i += 1){
            //This for loop keeps track of the elements that have already been
            //added to our filtered array so we get no repeats
            for(int j = oldElementsIndex; j < this.size; j += 1){
                if(mc.chooseElement(this.elements[j])){
                    toReplace[i] = this.elements[j];
                    //The next time we get to this loop, we start searching
                    //from the element after the one we just added to our updated
                    //array
                    oldElementsIndex = j + 1;
                    break;
                }
            }
        }
        this.size = newSize;
        this.elements = toReplace;
        //Update our instance variables
    }

    /** isEmpty returns true if our ArrayGL has 0 elements stored
     *
     * @return true if our linked list is empty, false otherwise
     * */
    @Override
    public boolean isEmpty() {
        if(this.size == 0){
            return true;
        }
        return false;
    }
}