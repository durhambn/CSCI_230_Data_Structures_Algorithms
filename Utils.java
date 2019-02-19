package edu.cofc.csci230;

/**
 * Utilities class that will sort (in increasing order)
 * the elements of a list.
 *
 * The sorting algorithms supported in this class are:
 *  1. selection sort
 *  2. bubble sort
 *  3. insertion sort
 * 
 * @author CSCI 230: Data Structures and Algorithms Fall 2017
 *
 */
public class Utils {
    
    /**
     * 
     */
    private Utils() {
        
    } // end private constructor
    
    /**
     * 
     * @param list
     */
    public static <AnyType extends Comparable> void selectionSort( List<AnyType> list ) throws IndexOutOfBoundsException {
        
         /*
         * TODO:
         * --------------------------------------
         * Implement insertion sort algorithm as
         * described in class and as provided in 
         * this coding assignment.
         * 
         */
        if(list == null){
            throw new IndexOutOfBoundsException();
        }
        for(int i = 0; i<= list.size()-2; i++){//i = 0, i< 1
             int min = i; // min = 0
             //System.out.println(min);
            for(int j = i+1; j<= list.size()-1; j++){ // j = 1; j < 2   j = 2,  
              if( list.get(j).compareTo(list.get(min)) < 0){ //list[1] < list[0]
                  min = j;
               }
            }
            list.swap(i,min);
            //System.out.println(list);
        }   
        
        } // end selectionSort() method
    
    /**
     * 
     * @param list
     */
    public static <AnyType extends Comparable> void bubbleSort( List<AnyType> list ) throws IndexOutOfBoundsException {
        
        /*
         * TODO:
         * --------------------------------------
         * Implement insertion sort algorithm as
         * described in class and as provided in 
         * this coding assignment.
         * 
         */
        if(list == null){
            throw new IndexOutOfBoundsException();
        }
        for(int i =0; i<= list.size()-2; i++){
            for(int j = 0; j<= list.size()-2-i;j++){
                if(list.get(j+1).compareTo(list.get(j)) < 0){
                    list.swap(j,j+1);
                }
            }
        }
        
        
        
    } // end bubbleSort() method
    
    /**
     * 
     * @param list
     */
    public static <AnyType extends Comparable> void insertionSort( List<AnyType> list ) throws IndexOutOfBoundsException {
        
        /*
         * TODO:
         * Implement insertion sort algorithm as
         * described in class and as provided in 
         * this coding assignment.
         * 
         */
        if(list == null){
            throw new IndexOutOfBoundsException();
        }
        for(int i = 0; i<= list.size()-1; i++){
            AnyType v = list.get(i);
            int j = i-1;
            while( j>=0 && list.get(j).compareTo(v)> 0){
                list.set(j+1, list.get(j));
                j =j -1;
                
            }
            list.set(j+1,v);
        }
        
        
        
        
    } // end insertionSort() method
    
} // end Utils class definition
