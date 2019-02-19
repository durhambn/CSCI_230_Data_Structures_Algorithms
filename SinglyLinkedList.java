package edu.cofc.csci230;
//import java.util.Random;

/**
 * Singly LinkedList Data Structure
 * 
 * @author CSCI 230: Data Structures and Algorithms Fall 2017
 *
 * @param <AnyType>
 */
public class SinglyLinkedList<AnyType extends Comparable<AnyType>> implements List<AnyType> {
    
    // instance variables
    private Node<AnyType> headNode = null;
    private int size = 0;
    
    /**
     * Appends the specified element to the end of this list.
     * 
     * @param t
     */
    public void add( AnyType t) {
        
        addNode( new Node<AnyType>(t) );
        
    } // end add() method
    
    /**
     * implementation for public add(AnyType t) method
     * 
     * @param t
     */
    private void addNode(Node<AnyType> t) {
        
        if ( isEmpty() ) headNode = t;
        else getNode( size-1 ).setNextNode( t );
        
        size++;
        
    } // end addNote() method
    
    
    /**
     * Inserts the specified element at the specified position in this list.
     * 
     * @param index
     * @param t
     * @throws IndexOutOfBoundsException
     */
    public void add(int index, AnyType t) throws IndexOutOfBoundsException {
        
        addNode( index, new Node<AnyType>(t) );
        
    } // end add() method
    
    /*
     * 
     * Implementation for public add(int index, AnyType t) method
     * 
     */
    private void addNode(int index, Node<AnyType> t) throws IndexOutOfBoundsException {
        
        /**
         * -------------------------------------------
         * TODO: You fully implement this method
         * 
         */
        
        if ( index == 0 ) {
            
            t.setNextNode( headNode );
            headNode = t;
            size++;
            
        } else if ( index == size ) {
            
            addNode( t );
            
        } else {
            
            t.setNextNode( getNode( index ) );
            getNode( index - 1 ).setNextNode( t );
            size++;
            
        }
        
    } // end addNode() method
    
    /**
     * Replaces the element at the specified position in this list with the specified element.
     * 
     * @param index
     * @param t
     * @throws IndexOutOfBoundsException
     */
    public void set(int index, AnyType t) throws IndexOutOfBoundsException {
        
        setNode( index, new Node<AnyType>(t) );
        
    } // end set() method
    
    /**
     * 
     * @param index
     * @param t
     * @throws IndexOutOfBoundsException
     */
    private void setNode( int index, Node<AnyType> t )  throws IndexOutOfBoundsException {
        
        /**
         * -------------------------------------------
         * TODO: You fully implement this method
         * 
         */
        
        getNode( index ).setData( t.getData() );
        
    } // end setNode() method
    
    
    /**
     * Removes the element at the specified position in this list.
     * 
     * @param index
     * @return
     * @throws IndexOutOfBoundsException
     */
    public AnyType remove( int index ) throws IndexOutOfBoundsException {
        
        return removeNode( index ).getData();
        
    } // end remove() method
    
    /**
     * 
     * @param index
     * @return
     * @throws IndexOutOfBoundsException
     */
    private Node<AnyType> removeNode( int index ) throws IndexOutOfBoundsException {
        
        /**
         * -------------------------------------------
         * TODO: You fully implement this method
         * 
         */
        
        Node<AnyType> node = getNode( index );
        
        if ( index == 0 ) {
            
            headNode = node.getNextNode();
            
        } else {
            
            getNode( index - 1 ).setNextNode( node.getNextNode() );
            
        }
        
        size--;
        
        return node;
        
    } // end removeNode() method
    
    /**
     * Returns the element at the specified position in this list.
     * 
     * @param index
     * @return
     * @throws IndexOutOfBoundsException
     */
    public AnyType get( int index ) throws IndexOutOfBoundsException {
        
        return getNode( index ).getData();
        
    } // end get() method
    
    /**
     * Implementation for get(int index) method
     * 
     * @param index
     * @return
     * @throws IndexOutOfBoundsException
     */
    private Node<AnyType> getNode(int index) throws IndexOutOfBoundsException {
        
        /**
         * -------------------------------------------
         * TODO: You fully implement this method
         * 
         */
        
        if ( index < 0 || index >= size ) {
            
            throw new IndexOutOfBoundsException();
            
        }
        
        Node<AnyType> node = headNode;
        
        for ( int i=1; i<=index; i++ ) {
            
            node = node.getNextNode();
            
        }
        
        return node;
        
    } // end get() method
    
    /**
     * Returns the number of elements in this list. 
     * 
     * @return
     */
    public int size() {
        
        return size;
        
    } // end size() method
    
    /**
     * Returns true if this list contains no elements.
     * 
     * @return
     */
    public Boolean isEmpty() {
        
        return ( size == 0 ) ? true : false;
        
    } // end isEmpty() method
    
    
    /**
     * Removes all of the elements from this list.
     * 
     */
    public void clear() {
        
        /**
         * -------------------------------------------
         * TODO: You fully implement this method
         * 
         */
        
        headNode = null;
        size=0;
        
    } // end clear method
    /**
     * swap two elements in a list at index 
     * position i and j.
     * 
     * For example, 
     *  if A = 1->2->3->4->5 and swap( 1, 3 ) is executed
     *  then A = 1->4->3->2->5
     *
     * @param i
     * @param j
     * @throws IndexOutOfBoundsException
     */
    public void swap( int i, int j )  throws IndexOutOfBoundsException {
        
        /**
         * -------------------------------------------
         * TODO: You fully implement this method
         * 
         */
        if (i > size || j > size || i<0 || j<0){
            throw new IndexOutOfBoundsException();
        }
        if( i!= j){
            AnyType temp = this.get(i);
            this.set(i, get(j));
            this.set(j, temp);
        }
        
        
        
    } // end swap() method
    /**
     * For debugging purposes :)
     * 
     * 
     */
    public String toString() {
        
        StringBuffer buffer = new StringBuffer();
        
        buffer.append( String.format( "Size=%d, A = [ ", size ) );
        
        if ( !isEmpty() ) {
            
            for ( int i=0; i<size-1; i++ ) {
                buffer.append( String.format( "%d, ", get(i) ) );    
            }
            
            buffer.append( String.format( "%d ]", get(size-1 ) ) );
            
        } else {
            
            buffer.append( "] " );
        }
        
        return buffer.toString();
        
    } // end toString()
    
    
    /**
     * 
     * @param args
     */
    public static void main( String[] args ) {
                
        // -------------------------------------
        // Put your test cases here
        // -------------------------------------
        
        SinglyLinkedList<Integer> list = new SinglyLinkedList<Integer>();
        
        // for( int i = 0; i< 20; i++){
            // Random rand = new Random(); 
            // int value = rand.nextInt(50);
            // list.add(value);
        // }
        list.add(5);
        list.add(6);
        list.add(2);
        list.add(9);
        list.add(8);
        
        System.out.println(list);
        System.out.println("Selection Sort");
        Utils.selectionSort(list);
        System.out.println(list);
        System.out.println("\n------\n");
        SinglyLinkedList<Integer> list2 = new SinglyLinkedList<Integer>();
        // for( int i = 0; i< 20; i++){
            // Random rand = new Random(); 
            // int value = rand.nextInt(50);
            // list2.add(value);
        // }
        list2.add(5);
        list2.add(6);
        list2.add(2);
        list2.add(9);
        list2.add(8);
        
        System.out.println(list2);
        System.out.println("Bubble Sort");
        Utils.bubbleSort(list2);
        System.out.println(list2);
        System.out.println("\n------\n");
        SinglyLinkedList<Integer> list3 = new SinglyLinkedList<Integer>();
        // for( int i = 0; i< 20; i++){
            // Random rand = new Random(); 
            // int value = rand.nextInt(50);
            // list3.add(value);
        // }
        list3.add(5);
        list3.add(6);
        list3.add(2);
        list3.add(9);
        list3.add(8);
        
        System.out.println(list3);
        System.out.println("Insertion Sort");
        Utils.insertionSort(list3);
        System.out.println(list3);

                
    } // end main() method
    
} // end SinglyLinkedList class definition