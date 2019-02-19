package edu.cofc.csci230;

/**
 * 
 * Open hashing data structure
 * 
 * @author CSCI 230: Data Structures and Algorithms Fall 2017
 *
 */
public class OpenHashing extends HashTable { 
    
    /* private instance variables */
    private ArrayList<String>[] hash_table;
    
    /**
     * Constructor
     */
    public OpenHashing( int hash_function ) {
        
        this.hash_function = hash_function;
        
    } // end constructor
    
    /**
     * Initialize the hash table
     * 
     * The number of elements in the hash table (m) is equal 97.
     * 
     */
    public void initialize() {
        
        hash_table = (ArrayList<String>[]) new ArrayList[ HashTable.M ];
        
        for ( int i=0; i<hash_table.length; i++ ) {
            
            hash_table[i] = new ArrayList<String>();
            
        }
        
    } // end initialize() method
    
    
    /**
     * Search for key in the hash table
     * 
     * Exceptions: If the key does not exist in the hash table, the throw
     *             a HashTableKeyException
     * 
     * return: The number of list probes needed to find the key in the hash 
     *         table, e.g. 1 if the key was the first element in the list, n 
     *         if the key was the very last element in the list (where n is 
     *         the number of elements in the list).
     * 
     * @param key
     * @return
     */
    public int search( String key ) throws HashTableKeyException {
        
        int probes = 0;
        
        /* ----------------------------------
         * TODO: Put your solution here
         * ----------------------------------
         */
            try{
            ArrayList check = null;
            int i = 0;
            if(hash_table[calcHash(key)] == null){
                return probes;
              //throw new HashTableKeyException(key);
              }
            else{
                check = hash_table[calcHash(key)];
                while(i < check.size()){
                    probes++;
                    if(check.get(i).compareTo(key)==0){
                        return probes;
                    }
                    i++;
                }
                if(i == check.size()){
                    throw new HashTableKeyException(key);
                }
            }
        }catch(HashTableKeyException e){
        }
        
        return probes;
        
        
    } // end search() method
    
    /**
     * Insert key into hash table
     * 
     * Exceptions: Duplicate keys are not allowed, e.g., if "dog" is already exists
     *             in the hash table, then another "dog" key cannot be inserted. In 
     *             this instance, throw a HashTableKeyException.
     * 
     * @param key
     */
    public void insert( String key ) throws HashTableKeyException {
        
        /* ----------------------------------
         * TODO: Put your solution here
         * ----------------------------------
         */
        try{
            int num = calcHash(key);
            //System.out.println(num);
            //System.out.println(hash_table);
            if(hash_table[num].size() == 0){
                hash_table[num].add( key);
            }
            else{
                int i = 0;
                //System.out.println(hash_table[num].get(i));
                while( i< hash_table[num].size()){
                    if(hash_table[num].get(i).compareTo(key)==0){
                        throw new HashTableKeyException(key);
                    }
                    i++;
                }
                hash_table[num].add(i, key);
            }
        }catch(HashTableKeyException e){
            //System.out.println(e);
        }
        //System.out.println("Done");
        
    } // end insert() method
    
    /**
     * Delete a key from the hash table
     * 
     * Exceptions: if they key does not exist in the hash table, then throw
     *             a HashTableKeyException
     * 
     * return: The number of probes needed to find the key in the hash table,
     *         e.g. 1 if the key was the first element in the list, n if it 
     *         was the very last element in the list, where n is the size 
     *         of the list.
     * 
     * @param key
     * @return
     */
    public int delete ( String key ) throws HashTableKeyException {
        
        int probes = 0;
        
        /* ----------------------------------
         * TODO: Put your solution here
         * ----------------------------------
         */
        try{
            int num = calcHash(key);
            if(hash_table[num].size() == 0){
                throw new HashTableKeyException(key);
            }
            else{
                for(int i = 0; i<hash_table[num].size();i++){
                    probes++;
                    if(hash_table[num].get(i).compareTo(key) == 0){
                        hash_table[num].remove(i);
                        
                    }
                }
            }
        }catch(HashTableKeyException e){
        }
        return probes;
        
    } // end delete() method
    
    /**
     * See page 271 in supplemental course textbook (chapter is provided as PDF 
     * on OAKS in content section).
     * 
     * Also, refer to your lecture notes.
     * 
     * @return
     */
    public double loadFactor() {
        
        /* ----------------------------------
         * TODO: Put your solution here
         * ----------------------------------
         */
        int n = 0;
        int m = hash_table.length;
        //System.out.println(hash_table.length);
        for(int i = 0; i< hash_table.length; i++){
            if(hash_table[i] !=null){
                for(int j = 0; j< hash_table[i].size();j++){
                    //System.out.println(n + ":" + hash_table[i].get(j));
                    n++;
                }
                //m++;
            }
        }
        //System.out.println(n);
        double a = ((double) n)/ m;
        return a;
                
    } // end loadFactor() method
    
    
    /**
     * See equation (7.4) on page 271 in supplemental course textbook (chapter 
     * is provided as PDF on OAKS in content section).
     * 
     * @return
     */
    public double successfulSearches() {
        
        /* ----------------------------------
         * TODO: Put your solution here
         * ----------------------------------
         */
        double s = 1+ (loadFactor() /2.0);
        return s;
                
    } // end successfulSearches() method
    
    /**
     * See equation (7.4) on page 271 in supplemental course textbook (chapter 
     * is provided as PDF on OAKS in content section).
     * 
     * @return
     */
    public double unsuccessfulSearches() {
        
        /* ----------------------------------
         * TODO: Put your solution here
         * ----------------------------------
         */
        double u = (loadFactor() / 2.0);
        
        return u;
                
    } // end unsuccessfulSearches() method
    
    
    /**
     * 
     * @param args
     */
    public static void main( String[] args ) {
        
            OpenHashing hashDS = new OpenHashing( HashTable.HASH_FUNC1 );
            System.out.printf("\n%s ----------------------------------\n", "Open Hashing: FUNC1" );
            
            if ( hashDS.loadWords() ) {
                try{
                hashDS.initialize();
                
                System.out.printf( "Number of words in list = %d\n", words.size() );
                
                /* ------------------------------------------------
                 * TODO:
                 * ------------------------------------------------
                 * 1) Insert each word into hash data structure
                 * 2) Calculate load factor value and print to 
                 *    console (using System.printf or System.println)
                 * 3) Calculate successful searches value and print to 
                 *    console (using System.printf or System.println)
                 * 4) Calculate unsuccessful searches value and print to 
                 *    console (using System.printf or System.println)
                 * 5) For each word in words list, search in the hashDS, 
                 *    and print mean probe value to console (using System.printf or System.println)
                 * 6) For a word that does not exist in hashDS, search in 
                 *    the hashDS, print the probe value to console (using System.printf or System.println)
                 * 7) For each word in words list, delete word in the hashDS, 
                 *    and print mean probe value to console (using System.printf or System.println)
                 * 8) For a word that does not exist in hashDS, delete in 
                 *    the hashDS, print the probe value to console (using System.printf or System.println)
                 * 
                 */
                //System.out.println(hashDS.length);
                //#1
                for(int i = 0; i< words.size() ; i++){
                    hashDS.insert(words.get(i));
                }
                //System.out.println(hashDS.search("dog"));
                System.out.println("2) Load Factor : "+hashDS.loadFactor());
                System.out.println("3) Successful Searches: "+ hashDS.successfulSearches());
                System.out.println("4) Unseccessful Searches: " + hashDS.unsuccessfulSearches());
                double mean = 0;
                for(int j = 0; j< words.size(); j++){
                    mean += hashDS.search(words.get(j));
                    //System.out.println(words.get(j)+ " : " +hashDS.search(words.get(j)));
                }
                System.out.println("5) Mean probe value: " + (mean/words.size()));
                //System.out.println("6) Happy doesn't exist; probe value : " + hashDS.search("dog"));
                System.out.println("7) Deleting words ");
                double meanDelete= 0;
                for(int k = 0; k< words.size(); k++){
                    meanDelete += hashDS.delete(words.get(k));
                }
                System.out.println("Mean probe value for delete: "+ (meanDelete/words.size()));
                System.out.println("8) Happy doesn't exist, delete probe value: "+ hashDS.delete("happy"));
            }catch(HashTableKeyException e){
            
            }
            } else {
                
                System.out.println("Failed to load words from text file" );
            }
            
            // ------------------------------------------------
            // Repeat for second hash function
            
            hashDS = new OpenHashing( HashTable.HASH_FUNC2 );
            System.out.printf("\n%s ----------------------------------\n", "Open Hashing: FUNC2" );
            
            if ( hashDS.loadWords() ) {
                try{
                hashDS.initialize();
                
                System.out.printf( "Number of words in list = %d\n", words.size() );
                
                /* ------------------------------------------------
                 * TODO:
                 * ------------------------------------------------
                 * 1) Insert each word into hash data structure
                 * 2) Calculate load factor value and print to 
                 *    console (using System.printf or System.println)
                 * 3) Calculate successful searches value and print to 
                 *    console (using System.printf or System.println)
                 * 4) Calculate unsuccessful searches value and print to 
                 *    console (using System.printf or System.println)
                 * 5) For each word in words list, search in the hashDS, 
                 *    and print mean probe value to console (using System.printf or System.println)
                 * 6) For a word that does not exist in hashDS, search in 
                 *    the hashDS, print the probe value to console (using System.printf or System.println)
                 * 7) For each word in words list, delete word in the hashDS, 
                 *    and print mean probe value to console (using System.printf or System.println)
                 * 8) For a word that does not exist in hashDS, delete in 
                 *    the hashDS, print the probe value to console (using System.printf or System.println)
                 * 
                 */
                for(int i = 0; i< words.size() ; i++){
                    hashDS.insert(words.get(i));
                }
                //System.out.println(hashDS.search("little"));
                System.out.println("2) Load Factor : "+hashDS.loadFactor());
                System.out.println("3) Successful Searches: "+ hashDS.successfulSearches());
                System.out.println("4) Unseccessful Searches: " + hashDS.unsuccessfulSearches());
                double mean = 0;
                for(int j = 0; j< words.size(); j++){
                    mean += hashDS.search(words.get(j));
                    //System.out.println(words.get(j)+ " : " +hashDS.search(words.get(j)));
                }
                System.out.println("5) Mean probe value: " + (mean/words.size()));
                //System.out.println("6) happy doesn't exist; probe value : " + hashDS.search("happy"));
                System.out.println("7) Deleting words ");
                double meanDelete= 0;
                for(int k = 0; k< words.size(); k++){
                    meanDelete += hashDS.delete(words.get(k));
                }
                System.out.println("Mean probe value for delete: "+ (meanDelete/words.size()));
                System.out.println("8) happy doesn't exist, delete probe value: "+ hashDS.delete("happy"));
            
                }catch(HashTableKeyException e){
                }
            } else {
                
                System.out.println("Failed to load words from text file" );
            }
        
    } // end main() method
    
} // end OpenHashing class definition
