/**Author: Sebastian Morgan
 * Email: sjmorgan@ucsd.edu
 * Date: 1/18/2021
 *
 * This file implements a generic linked list with a very small amount of
 * capabilities, and the Node class to act as each element of the list. This
 * class mainly exists to allow us to store arrays in a different way.
 * **/

/* This class implements MyList, so it has to override the 4 methods introduced
* there. This is a generic class, and can be applied to any object. This class
* will allow us to convert normal arrays to linked lists, and back. We will also
* be able to call chooser and transformer methods from here, altering the linked
* list from within the class in specific ways. */
public class LinkedGL<E> implements MyList<E> {

    /* This class defines the structure of our linked list. Each element of our
    * linked list will be made up of a Node, which will store the value we want
    * to store in the node, and the reference to the next Node in the sequence.
    *
    * This class only has a defined constructor and instance variables to store
    * the above values. */
    class Node {
        E value;
        Node next;

        public Node(E value, Node next) {
            this.value = value;
            this.next = next;
        }
    }

    Node front;
    int size;

    /** This constructor throws an exception if the input array is null, otherwise
     * it creates a dummy front node, then appends more nodes to that node and
     * copies the value from the array into the value of the node. Each node points
     * to the next Node in the sequence, and the final Node has a null value in its
     * next instance variable. For every node added to the dummy node, we increase
     * this.size by 1.
     *
     * @param contents, the array to build our linked list from
     * **/
    public LinkedGL(E[] contents) {
        //Check null case
        if(contents == null){
            throw new NullPointerException();
        }
        //Create a dummy node
        //We do this because now, even if our linked list is empty, we will
        //always be able to call this.front.next, allowing us to eliminate
        //extra tests in other steps when we need to iterate through our list
        this.front = new Node(null,null);
        this.size = 0;
        if(contents.length != 0){
            Node currNode = this.front;
            //For each element in the array, we want to copy its value into the
            //next node, so we use currNode to keep track of the Node we're
            //looking at, then update the node.next field as we continue to
            //have more elements to append. Finally, when we finish, the last
            //node has a null next value, ending the linked list
            //We also increase size for each appended node
            for(int i = 0; i < contents.length; i += 1){
                currNode.next = new Node(contents[i],null);
                currNode = currNode.next;
                this.size += 1;
            }
        }
    }

    // Fill in all methods
    /** This method turns our Linked List back into an array, returning the array
     *
     * @return the array representation of this list.*/
    @Override
    @SuppressWarnings("unchecked")
    public E[] toArray() {
        //We have to typecast to an Element array because we cannot directly
        //create a new Element array, forcing us to suppress the unchecked
        //warning.
        E[] toRet = (E[])(new Object[this.size]);
        //If we have an empty list, this.front.next will exist, but be null,
        //thanks to our dummy front node! currNode keeps track of what node
        //we're on
        Node currNode = this.front.next;
        //Take back values in the order we inserted them into the linked list,
        //and append those values to the next value of toRet
        for(int i = 0; i < this.size; i += 1){
            toRet[i] = currNode.value;
            currNode = currNode.next;
        }
        return toRet;
    }

    /** transformAll attempts to change every value of each Node in our linked
     * list by using one of the classes that extend MyTransformer
     *
     * @param mt, the class which extends from MyTransformer to draw our
     *            transform method from
     * */
    @Override
    @SuppressWarnings("unchecked")
    public void transformAll(MyTransformer mt) {
        Node currNode = this.front;
        //For each node in our list, we transform its value
        while(currNode.next != null){
            currNode = currNode.next;
            //We need to typecast here to ensure that currNode.value is assigned
            //the right kind of value. We know that if we choose the right mt
            //class, we won't have a problem typecasting, but the compiler doesn't,
            //so we have to suppress warnings so the typecasting can be done
            currNode.value = (E) mt.transformElement(currNode.value);
        }
    }

    /** chooseAll will filter out nodes from our linked list which contain
     * values which aren't accepted by the Chooser class called
     *
     * @param mc, the class which extends from MyChooser to draw our
     *            chooser method from
     * */
    @Override
    public void chooseAll(MyChooser mc) {
        //Keeps track of the current node we're checking
        Node currNode = this.front;
        //Keeps track of the last node that was allowed by our chooser
        Node lastValidNode = this.front;
        int newSize = 0;
        while(currNode.next != null){
            //Update the node we're checking each iteration
            currNode = currNode.next;
            //If our chooser method we input returns true on the value of the
            //Node we input, then that value shouldn't be filtered out, so we
            //connect that node to our last valid node and update our last
            //valid node to the current node, and update our size.
            if(mc.chooseElement(currNode.value)){
                lastValidNode.next = currNode;
                lastValidNode = currNode;
                newSize += 1;
            }
        }
        //Make sure the last node in our sequence points to null instead of
        //artifacts from the unchanged list
        lastValidNode.next = null;
        this.size = newSize;
    }

    /** isEmpty returns true if our linked list only has the dummy node
     *
     * @return true if our linked list is empty, false otherwise
     * */
    @Override
    public boolean isEmpty() {
        if(this.front.next == null){
            return true;
        }
        return false;
    }
}