package edu.cofc.csci230;

import java.util.EmptyStackException;

/**
 * A LIFO stack that has constant time complexity O(1) for
 * all three stack interface methods (i.e., push, pop, and 
 * peek).
 * 
 * This data structure was discussed in class along with the 
 * operations, please review your notes.
 * 
 * @author CSCI 230: Data Structures and Algorithms Fall 2017
 *
 * @param <AnyType>
 */
public class ConstantTimeStack<AnyType extends Comparable<AnyType>> implements Stack<AnyType> {
    
    /**
     * private instance variables
     */
    private SinglyLinkedList<AnyType> list = new SinglyLinkedList<AnyType>();

    /**
     * Pushes an item onto the top of this stack in constant 
     * time O(1)
     * 
     * @param t the item to be pushed onto this stack.
     */
    public void push(AnyType t) {
        
                /**
                 * -------------------------------------------
                 * TODO: You fully implement this method
                 * 
                 * Note: Your push solution must be a constant 
                 * time O(1) operation
                 *
                 */
                //adds to the begging of the list of nodes
                list.add(0, t);
            
        
		
		
		
	} // end push() method

	/**
	 * Removes the object at the top of this stack and return the 
	 * item in constant time O(1)
	 * .
	 * @return The item at the top of this stack
	 * @throws EmptyStackException - if this stack is empty.
	 */
	public AnyType pop() throws EmptyStackException {
		
		/**
                 * -------------------------------------------
                 * TODO: You fully implement this method
                 * 
                 * Note: Your pop solution must be a constant 
                 * time O(1) operation
                 * 
                 */
                //if front of list is empty throw new exception
                if(list.get(0) == null){
                 throw new EmptyStackException();
                }
                //else remove the first node
                else{
                return list.remove(0);
            }
		
		
		
	} // end pop() method

	/**
	 * Looks at the item at the top of this stack without removing it 
	 * from the stack in constant time O(1)
	 * 
	 * @return the item at the top of this stack
	 * @throws EmptyStackException  - if this stack is empty.
	 */
	public AnyType peek() throws EmptyStackException {
		
	         /**
                 * -------------------------------------------
                 * TODO: You fully implement this method
                 * 
                 * Note: Your peek solution must be a constant 
                 * time O(1) operation
                 * 
                 */
                //if front of list of nodes is null then throw exception
                if( list.get(0) == null){
                    throw new EmptyStackException();
                }
                // return the front of stack
                 return list.get(0);
                
		
		
		
	} // end peek() method
	
	/**
	 * 
	 * @param args
	 */
	public static void main( String[] args ) {
		
		/**
                 * -------------------------------------------
                 * TODO: You put your test cases here
                 * 
                 */
                ConstantTimeStack<Integer> stack = new ConstantTimeStack<Integer> ();
                stack.push(5);
                stack.push(7);
                stack.pop();
                System.out.println(stack.peek());
                //System.out.println(stack);
                stack.pop();
                //stack.pop();
                System.out.println(stack.peek());
        
		
		
		
		
	} // end main method

} // end ConstantTimeStack class definition
