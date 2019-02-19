package edu.cofc.csci230;

/**
 * 
 * Closed hashing data structure using linear probing.
 * 
 * @author CSCI 230: Data Structures and Algorithms Fall 2017
 *
 */
public class ClosedHashing extends HashTable { 

    /* private instance variables */
    private String[] hash_table;

    /**
     * Constructor
     */
    public ClosedHashing( int hash_function ) {

        this.hash_function = hash_function;

    } // end constructor

    /**
     * Initialize the hash table
     * 
     * The number of elements in the hash table is equal to 2 x the number of words 
     * in the word list.
     * 
     */
    public void initialize() {

        hash_table = new String[ 2*words.size() ];

        for ( int i=0; i<words.size(); i++ ) {
            hash_table[i] = null;            
        }

    } // end initialize() method

    /**
     * Search for key in the hash table.
     * 
     * In this implementation, a lazy character "^" (at the beginning of the 
     * string value) is used to indicate a collision has occurred. The number 
     * of lazy characters indicate the number of collisions,e.g. "^^" would 
     * indicate two collisions have occurred.
     * 
     * Exceptions: If the key does not exist in the hash table, then throw 
     *             a HashTableKeyException
     * 
     * return: The number of linear probes needed to find the key in the 
     *         hash table, e.g. 1 if no probing, n if probed n times to
     *         find an open location.
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
            //if table at num is null --> throw exception
            //if not, calc lazy chars
            //if lazy = 0, then check table at num
            //if equal return 1
            //if not then throw excpetion
            //if lazy>0
            // loop through list until hit null spot
            int num = calcHash(key);
            int lazy = 0;
            if(hash_table[num] == null){
                //if spot is null then that means key doesn't exist in hash table
                throw new HashTableKeyException(key);
            }
            else{
                //if not null then there is a string in the hash table in that spot
                //loop through that spot and calculate how many lazy characters there are
                for(int i = 0; i< hash_table[num].length();i++){
                        if(hash_table[num].charAt(i)== '^'){
                            lazy++;
                        }
                    }
                    //if lazy == 0 then there were no collisions and only have to check if 
                    //string at num is key
                    if(lazy == 0){
                        //if it is then add 1 to probes and return probes
                        if(hash_table[num].compareTo(key) == 0){
                            probes++;
                            return probes;
                        }
                        //if not then throw exception because its not in hash table
                        else{
                            //probes++;
                            throw new HashTableKeyException(key);
                        }
                }
                // if lazy> 0 then there are collisions
                    else{
                    int i = 0;
                    //loop through table until hit a null spot
                    for( i = num; (hash_table[i] != null ); i++){
                        //for each index after num index, count lazy, and check if = to key
                        
                        if(i == hash_table.length){
                            //means the loop reached end of table and has to start over
                            //because haven't searched through beginning of loop bc the 
                            //table is treated like a circular array
                            i = 0;
                        }
                        //reset the value of lazy each loop
                        lazy = 0;
                        //loop through and count the lazy characters
                        for(int k = 0; k< hash_table[i].length();k++){
                            if(hash_table[i].charAt(k)== '^'){
                                lazy++;
                            }
                        }
                        //increase probes
                        probes++;
                        //check if the string - lazy characters = key
                        if(hash_table[i].substring(lazy).compareTo(key) == 0){
                            //if so return probes
                            return probes;
                        }
                        //else keep checking
                    }
                    //if reached a null spot then exit loop, this means key doesn't exist in table
                    if(i == hash_table.length || hash_table[i] == null){
                        //throw exception bc not in list, set probe to 0 to indicate not in list
                        probes = 0;
                        throw new HashTableKeyException(key);
                    }
                } 
                
            }
        }catch(HashTableKeyException e){

        }
        //returns 0 if not in list 
        return probes;
    } // end search() method

    /**
     * Insert key into hash table
     * 
     * In this implementation, a lazy character "^" (at the beginning of the 
     * string value) is used to indicate a collision has occurred. The number 
     * of lazy characters indicate the number of collisions,e.g. "^^" would 
     * indicate two collisions have occurred.
     * 
     * Exceptions: Duplicate key values are not allowed e.g., if "dog" is 
     *             already exists in the hash table, then another 
     *             "dog" key cannot be inserted. In this instance, throw a
     *             HashTableKeyException.
     * 
     * @param key
     */
    public void insert( String key ) throws HashTableKeyException {       
        /* ----------------------------------
         * TODO: Put your solution here
         * ----------------------------------
         */
        
        try{
            //calc the index pos
            int num = calcHash(key);
            int lazy= 0;
            //if table is null at index then set place to key
            if(hash_table[num] == null){
                hash_table[num] = key;        
            }
            // if not null then check if key is already in table
            //if 0 then yes it is, throw exception
            else if(this.search(key)== 0){
                //that means there was an exception
                throw new HashTableKeyException(key);
            }
            //index pos is not null and key is not already in list
            else{
                //loop through list and calculate lazy characters at index pos
                for(int i = 0; i< hash_table[num].length(); i++){
                    //get num of lazy chars
                    if(hash_table[num].charAt(i) == '^'){
                        lazy++;
                    }
                }
                //add lazy char and add key
                String i = "^";
                i+= hash_table[num];
                hash_table[num] = i;
                int k = 0;
                //loop through and find next null position
                for(k = num+1; k!= num;k++){
                    //go back to front of hash table to seach
                    if(k == hash_table.length){
                        k = 0;
                    }
                    //if null then set position to key
                    if(hash_table[k] == null){
                        hash_table[k] = key;
                        break;
                    }
                }
                
            } 
        }catch(HashTableKeyException e){

        }

    } // end insert() method

    /**
     * Delete a key from the hash table. 
     * 
     * In this implementation, a lazy character "^" (at the beginning of the 
     * string value) is used to indicate a collision has occurred. The number 
     * of lazy characters indicate the number of collisions,e.g. "^^" would 
     * indicate two collisions have occurred. Every successful deletion 
     * should remove one "^" symbol. 
     * 
     * Exceptions: if they key does not exist in the hash table then throw
     *             a HashTableKeyException
     * 
     * return: The number of probes needed to find the key in the hash table,
     *         e.g. 1 if the key was the first element in the list, n if it was 
     *         the very last element in the list, where n is the number of elements 
     *         in the list.
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
        //search and if collision delete the ^ and if not found throw exception
        try{
            
            int num = calcHash(key);
            int lazy = 0;
            //if table at pos is null then throw exception, key doesn't exist
            if(hash_table[num] == null){
                throw new HashTableKeyException(key);
            }
            //key exists if search returns higher than 0
            else if(search(key)>0){
                //loop to check how many ^ chars there are and then compare rest of string 
                for(int i = 0; i< hash_table[num].length(); i++){
                    //get num of lazy chars
                    if(hash_table[num].charAt(i) == '^'){
                        lazy++;

                    }
                }
                //no collistions, if not = throw exception bc key isn't in list
                if(lazy == 0 && hash_table[num].compareTo(key)!=0){
                    throw new HashTableKeyException (key);
                }
                // if no collistions and = then increase probes and return probes
                else if(lazy==0 && hash_table[num].compareTo(key)==0){
                    probes++;
                    return probes;
                }
                //if lazy > 0 collision and if not = to index at num - lazy chars
                else if(lazy > 0 && hash_table[num].substring(lazy).compareTo(key)!=0){
                    //while haven't hit an empty spot and num isn't where it started
                    //loop and check 
                    while(hash_table[num] !=null&& num!= calcHash(key)){
                        if(num== hash_table.length){
                            num=0;
                        }
                        for(int i = 0; i< hash_table[num].length(); i++){
                            //get num of lazy chars
                            if(hash_table[num].charAt(i) == '^'){
                                lazy++;
                            }
                        }
                        //check if = to key
                        if(hash_table[num].substring(lazy).compareTo(key)==0){
                            //if so increase probes and delete 1 lazy char
                            probes++;
                            
                            hash_table[calcHash(key)] = hash_table[calcHash(key)].substring(1);
                            return probes;
                        }
                        
                        
                        num++;
                    }
                    //when out of loop if key wasn't found throw exception
                    if(num == calcHash(key) || hash_table[num] == null){
                        throw new HashTableKeyException (key);
                    }
                }
                probes++;

            }
        }catch(HashTableKeyException e){
        }

        return probes;

    } // end delete() method

    /**
     * See page 271 in supplemental course textbook (chapter is provided as PDF 
     * on OAKS in content section).
     * 
     * Also, refer to your lecture notes. Note, for closed hashing, m is 
     * the number of locations in the hash table.
     * 
     * @return
     */
     public double loadFactor() {

        /* ----------------------------------
         * TODO: Put your solution here
         * ----------------------------------
         */
        double n = 0;
        double m = hash_table.length;
        for(int i = 0; i< hash_table.length; i++){
            if(hash_table[i] !=null){
                n++;
            }
        }
        double a = ((double) n)/ m;
        return a;

    } // end loadFactor() method

    /**
     * See equation (7.5) on page 273 in supplemental course textbook (chapter 
     * is provided as PDF on OAKS in content section).
     * 
     * @return
     */
    public double successfulSearches() {

        /* ----------------------------------
         * TODO: Put your solution here
         * ----------------------------------
         */
        double s = .5*(1 + (1/(1- loadFactor())));
        return s;

    } // end successfulSearches() method

    /**
     * See equation (7.5) on page 273 in supplemental course textbook (chapter 
     * is provided as PDF on OAKS in content section).
     * 
     * @return
     */
    public double unsuccessfulSearches() {

        /* ----------------------------------
         * TODO: Put your solution here
         * ----------------------------------
         */
        double l = (1-loadFactor())*(1 - loadFactor());
        double s = .5*(1 + (1/(l)));
        return s;

    } // end unsuccessfulSearches() method

    /**
     * 
     * @param args
     */
    public static void main( String[] args ) {

        ClosedHashing hashDS = new ClosedHashing( HashTable.HASH_FUNC1 );
        System.out.printf("\n%s ----------------------------------\n", "Closed Hashing: FUNC1" );

        if ( hashDS.loadWords() ) {

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
            try{
                //System.out.println("not exists search: " + hashDS.search("not"));
                //#1
                for(int i = 0; i< words.size() ; i++){
                    //System.out.println(words.get(i));
                    hashDS.insert(words.get(i));
                }
                //hashDS.insert("of");
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
                System.out.println("6) Dog doesn't exist; probe value : " + hashDS.search("dog"));
                System.out.println("7) Deleting words ");
                double meanDelete= 0;
                for(int k = 0; k< words.size(); k++){
                    meanDelete += hashDS.delete(words.get(k));
                }
                System.out.println("Mean probe value for delete: "+ (meanDelete/words.size()));
                System.out.println("8) Dog doesn't exist, delete probe value: "+ hashDS.delete("dog"));
            }catch(HashTableKeyException e){

            }
        } else {

            System.out.println("Failed to load words from text file" );
        }

        // ------------------------------------------------
        // Repeat for second hash function

        System.out.printf("\n%s ----------------------------------\n", "Closed Hashing: FUNC2" );
        hashDS = new ClosedHashing( HashTable.HASH_FUNC2 );

        if ( hashDS.loadWords() ) {

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
            try{
                
                // for(int k = 0; k < words.size()*2; k++){
                    // System.out.println(hashDS[k]);
                // }
                //#1
                for(int i = 0; i< words.size() ; i++){
                    //System.out.println(words.get(i));
                    hashDS.insert(words.get(i));
                }
                //System.out.println("not exists search: " + hashDS.search("not"));
                //System.out.println(hashDS.search("dog"));
                System.out.println("2) Load Factor : "+hashDS.loadFactor());
                System.out.println("3) Successful Searches: "+ hashDS.successfulSearches());
                System.out.println("4) Unseccessful Searches: " + hashDS.unsuccessfulSearches());
                double mean = 0;
                for(int j = 0; j< words.size(); j++){
                    //System.out.println("\nword: " + words.get(j) + ", search#: " + hashDS.search(words.get(j)));
                    mean += hashDS.search(words.get(j));
                    //System.out.println(words.get(j)+ " : " +hashDS.search(words.get(j)));
                }
                System.out.println("Mean: " + mean +", size: " + words.size());
                System.out.println("5) Mean probe value: " + (mean/words.size()));
                System.out.println("6) Brandi doesn't exist; probe value : " + hashDS.search("brandi"));
                System.out.println("7) Deleting words ");
                double meanDelete= 0;
                for(int k = 0; k< words.size(); k++){
                    meanDelete += hashDS.delete(words.get(k));
                }
                System.out.println("Mean probe value for delete: "+ (meanDelete/words.size()));
                System.out.println("8) Dog doesn't exist, delete probe value: "+ hashDS.delete("dog"));
             }catch(HashTableKeyException e){

            }

        } else {

            System.out.println("Failed to load words from text file" );
        }

    } // end main() method

} // end ClosedHashing class definition