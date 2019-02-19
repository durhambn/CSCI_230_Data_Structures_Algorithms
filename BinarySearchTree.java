package edu.cofc.csci230;

/**
 * 
 * Binary search that does not allow two (or more) binary nodes 
 * in the search tree to have the same element value.
 * 
 * @author CSCI 230: Data Structures and Algorithms Fall 2017
 *
 * @param <AnyType>
 */
public class BinarySearchTree <AnyType extends Comparable<AnyType>> {
    
    // --------------------------------------
    // instance variable
    private BinaryNode<AnyType> root;
    
    /**
     * Constructor with one parameter that
     * sets the root node of the BST.
     * 
     * @param root
     */
    public BinarySearchTree( AnyType rootElement ) throws NullBinaryNodeException {
        
        if ( rootElement == null ) {
            throw new NullBinaryNodeException();
        }
        
        this.root = new BinaryNode<AnyType>( rootElement ) ;
        
    } // end constructor
    
    /**
     * If the BST does not have a root node, then the BST is empty. 
     * 
     * @return
     */
    public boolean isEmpty() {
        
        return ( root == null );
        
    } // end isEmpty() method
    
    /**
     * Make the BST empty, i.e. a BST that has 
     * no nodes (including a root node).
     * 
     */
    public void makeEmpty() {
        
        root = null;
        
    } // end makeEmpty() method
    
    /**
     * Method that adds a new node with the specified element 
     * value in the BST.
     * 
     * This method has one parameter:
     *  1) The element value to be added
     * 
     * If the BST has an existing node with the same element 
     * value, throw an duplicate element exception.
     * 
     * @param element
     */
    public void add( AnyType element ) throws DuplicateElementException {
        
         /**
         * ------------------------------------
         * TODO: You complete the code.
         * 
         * 
         */
        // if the tree hasn't been started then it's empty, root= null
        // root needs to be set to the element.
        if( root == null){
            root = new BinaryNode<AnyType>( element ) ;
        }
        // if this isn't the first in the tree then add to tree using private method
        else{
            add(root, element);
        }
        
        
    } // end add() method
    
    /**
     * Private recursive method that adds a new node (with the 
     * specified element value) in the BST.
     * 
     * This method has two parameters:
     *  1) The node visited while recursively walking the BST
     *  2) The element value to be added
     * 
     * If the BST has an existing node with the same element 
     * value, throw an duplicate element exception.
     * 
     * @param node
     * @param element
     */
    private void add( BinaryNode<AnyType> node, AnyType element )  throws DuplicateElementException {
        
        /**
         * ------------------------------------
         * TODO: You complete the code.
         * 
            * Note: Your solution must use recursion
         * 
         */
        //System.out.println(node);
        //System.out.println(element);
        //make a new node with element to set the right or left leaf to
        BinaryNode<AnyType> val = new BinaryNode<AnyType>( element );
        //if element=node then it will be 0, therefore need to throw exception because 
        //it would be a duplicate
        if(element.compareTo(node.getElement()) == 0){
          throw new DuplicateElementException();
          }
          // if < 0 then element is less than node therefore go left
          else if(element.compareTo(node.getElement()) < 0){
              //go left
              // if not null then call the function again to the left leaf
              if(node.getLeft() != null){
                  add(node.getLeft(), element);
                }
                // if it is null then set element to left leaf
                else{
              //BinaryNode<AnyType> val = new BinaryNode<AnyType>( element );
              node.setLeft(val); 
            }
            }
            // if > 0 then element is > node therefore go right
          else if(element.compareTo(node.getElement()) >0){
              //go right
              // if not null then call function again on right leaf until null
              if(node.getRight() != null){
                  add(node.getRight(), element);
                }
                // if it is null then set right leaf to element
                else{
              //BinaryNode<AnyType> val = new BinaryNode<AnyType>( element );
              node.setRight(val);
            }
            }
            
        
        
        
    } // end add() overloaded method
    
    /**
     * Method that determines if a node with the specified element value 
     * exists in the BST. 
     * 
     * This method has one parameter:
     *  1) The element value that is being searched.
     * 
     * If the BST is empty, throw an empty binary search tree
     * exception.
     * 
     * @param element
     * @return
     */
    public boolean hasElement( AnyType element ) throws EmptyBSTException {
        
        if ( isEmpty() )
            throw new EmptyBSTException();
        
        return hasElement( root, element );
        
    } // end hasElement() method
    
    /**
     * Private recursive method that determines if the element is 
     * currently stored in the BST.
     * 
     * This method has two parameters:
     *  1) The node visited while recursively walking the BST
     *  2) The element value that is being searched.
     *  
     *  Hint: use the compareTo() method
     * 
     * @param element
     * @param node
     * @return
     */
    private boolean hasElement( BinaryNode<AnyType> node, AnyType element ) {
        
        /**
         * ------------------------------------
         * TODO: You complete the code.
         * 
         * Note: Your solution must use recursion
         * 
         */
        //make boolean val 
        boolean val;
        // when calling the function recursively and searching for an node that is
        //the same as an element then is we are traversing the tree and hit a null node
        // then we know that the element is not in the tree and we return false
        if(node == null){
            val = false;
        }
        // when comparing if the compareTo returns 0, then the two are equal and return true
        else if(element.compareTo(node.getElement()) == 0){
            val = true;
            return val;
        }
        // if it is less than, then we call the function again on the left leaf
        else if(element.compareTo(node.getElement()) < 0){
            //if(node.getLeft() != null){
                  val = hasElement(node.getLeft(), element);
                //}
        }
        // if neither then must be greater than, call function on right leaf
        else{ //(element.compareTo(node.getElement()) > 0){
            //if(node.getRight() != null){
                  val = hasElement(node.getRight(), element);
             //   }
        }
        return val;
       
    } // end hasElement() overloaded method
    
    /**
     * Find the node with the minimum element value in the BST.
     * 
     * This method has no parameters.
     * 
     * If the BST is empty, throw an empty binary search tree
     * exception.
     * 
     * @return
     * @throws EmptyBSTException
     */
    public AnyType findMin() throws EmptyBSTException {
        
        if ( isEmpty() )
            throw new EmptyBSTException();
        
        return findMin( root ).getElement();
        
    } // end findMin() method
    
    /**
     * Private recursive method that walks the BST searching
     * for the node with the minimum element value.
     * 
     * This method has one parameter:
     *  1) The node visited while recursively walking the BST
     * 
     * @param node
     * @return
     */
    private BinaryNode<AnyType> findMin( BinaryNode<AnyType> node ) {
        
        /**
         * ------------------------------------
         * TODO: You complete the code.
         * 
         * Note: Your solution must use recursion
         * 
         */
        // the min value should be the left most leaf; so when the left leaf of a node
        // is null then we have reached the left most leaf, and can return the node
        if(node.getLeft() == null){
            return node;
        }
        // else call the function again until it reaches the end 
        else{
            return findMin(node.getLeft());
        }
       
    } // end findMin() method
    
    /**
     * Find the node with the maximum element value in the BST.
     * 
     * This method has no parameters.
     * 
     * If the BST is empty, throw an empty binary search tree
     * exception.
     * 
     * @return
     * @throws EmptyBSTException
     */
    public AnyType findMax() throws EmptyBSTException {
        
        if ( isEmpty() )
            throw new EmptyBSTException();
        
        return findMax( root ).getElement();
        
    } // end findMax() method
    
    /**
     * Private recursive method that walks the BST searching
     * for the node with the maximum element value.
     * 
     * This method has one parameter:
     *  1) The node visited while recursively walking the BST
     * 
     * @param node
     * @return
     */
    private BinaryNode<AnyType> findMax( BinaryNode<AnyType> node ) {
        
        /**
         * ------------------------------------
         * TODO: You complete the code.
         * 
         * Note: Your solution must use recursion
         * 
         */
        // the right most node is the largest, keep traversing the tree going right every
        // time until you hit the null node then the node before null is the max value
        if(node.getRight() == null){
            return node;
        }
        //else keep calling function until you hit the null node
        else{
            return findMax(node.getRight());
        }
        //return node;
        
    } // end findMax() method
    
    /**
     * 
     * @param args
     */
    public static void main( String[] args ) {
        
        /**
         * ------------------------------------
         * TODO: You put your test cases here
         * 
         * 
         */
        
            try {
            BinarySearchTree <Integer> tree = new BinarySearchTree <Integer>(10 );
            //System.out.println("Add 5");
            tree.add(5);
            //System.out.println("Add 15");
            tree.add(15);
            //System.out.println("Add 20");
            tree.add(20);
            //System.out.println("Add 2");
            tree.add(2);
            //System.out.println("Add 11");
            tree.add(11);
            //tree.add(15);
                System.out.println(tree.root.getElement()); // root = 10
                System.out.println(tree.root.getLeft()); // left= 5
                System.out.println(tree.root.getRight()); //right = 15
                System.out.println(tree.root.getLeft().getLeft());
                System.out.println(tree.root.getLeft().getRight());
                System.out.println(tree.root.getRight().getLeft());
                System.out.println(tree.root.getRight().getRight());
                System.out.println("Testing findMax() --> should be 20");
            System.out.println(tree.findMax());
            System.out.println("Testing findMin() --> should be 2");
            System.out.println(tree.findMin());  
            System.out.println("Testing hasElement(2) --> should be true");
            System.out.println(tree.hasElement(2));
            System.out.println("Testing hasElement(7) --> should be false");
            System.out.println(tree.hasElement(7));
            System.out.println("Testing hasElement(10) --> should be true");
            System.out.println(tree.hasElement(10));
            System.out.println("Testing hasElement(15) --> should be true");
            System.out.println(tree.hasElement(15));
            tree.makeEmpty();
            tree.add(20);
            System.out.println(tree.root.getElement());
            System.out.println(tree.root.getLeft()); 
                System.out.println(tree.root.getRight()); 
               
        } catch(NullBinaryNodeException n) {
            System.out.println(n.getMessage());
        }catch(DuplicateElementException d){
            System.out.println(d.getMessage());
        }catch(EmptyBSTException e){
             System.out.println(e.getMessage());
         }
        
    
    } // end main method
    

} // end BinarySearchTree class
