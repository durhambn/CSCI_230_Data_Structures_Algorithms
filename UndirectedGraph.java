package edu.cofc.csci230;

import java.util.EmptyStackException;
import java.util.NoSuchElementException;

/**
 * Undirected and unweighted graph data structure.
 * 
 * 
 * @author CSCI 230: Data Structures and Algorithms Fall 2017
 *
 * @param <AnyType>
 */
public class UndirectedGraph<AnyType extends Comparable<AnyType>> {
    
    /**
     * Adjacency list data structure. Used in conjunction with
     * adjacent_vertex_list in each Vertex class.
     * 
     * Note: Each vertex in the adjacency list must be unique, 
     * i.e. the list cannot contain two vertices that have the 
     * same value.
     */
    private List<Vertex<AnyType>> vertex_adjacency_list = null;
    
    /**
     * 
     */
    public UndirectedGraph() {
        
        vertex_adjacency_list = new ArrayList<Vertex<AnyType>>();
        
    } // end constructor
    
    /**
     * Add an edge between two vertices
     * 
     * @param vertex_value_A
     * @param vertex_value_B
     * @return
     * @throws VertexException
     */
    public Boolean addEdge( AnyType vertex_value_A, AnyType vertex_value_B ) throws VertexException {
        
        // ------------------------------------
        // Basic steps:
        // ------------------------------------
        // 1) For Vertex A and B, check to see if the vertex exits in the 
        //    vertex_adjacency_list. If not, then add new vertex (with given 
        //    value) to the vertex_adjacency_list.
        // 2) In Vertex class, check if Vertex B is in Vertex A's 
        //    adjacent_vertex_list and vice versa (i.e. an edge exists). If 
        //    edge already exists, then return false, otherwise goto step 3.
        // 3) Add Vertex B to Vertex A's adjacent_vertex_list and vice versa.
        //    Lastly, return true indicating the edge was successfully added.
        
        // ----------------------------
        // TODO: Add your code here
        // ----------------------------
        boolean a_in_list = false;
        boolean b_in_list = false;
        boolean edge = false;
        int size = vertex_adjacency_list.size();
        int a = -1;
        int b = -1;
        
        //make new vertices with values passed in
        Vertex<AnyType> A = new Vertex<AnyType>(vertex_value_A);
        Vertex<AnyType> B = new Vertex<AnyType>(vertex_value_B);
        //check if they are in the list
        for(int i = 0; i<= size-1; i++){
            if( vertex_adjacency_list.get(i).compareTo( A) == 0){
                a_in_list = true;
                a = i;
                
            }
            if( vertex_adjacency_list.get(i).compareTo(B) == 0){
                b_in_list = true;
                b = i;
                
            }
        }
        //if not in list, add
        if( a_in_list == false){
            vertex_adjacency_list.add(A);
            a_in_list = true;
            a = vertex_adjacency_list.size()-1;
        }
        if( b_in_list == false){
            vertex_adjacency_list.add(B);
            b_in_list =true;
            b = vertex_adjacency_list.size()-1;
        }
        //check if edge
        edge = vertex_adjacency_list.get(a).hasAdjacentVertex(vertex_adjacency_list.get(b));
        //if edge then add to list 
        if( a_in_list == true && b_in_list == true){
            if(edge){
                return edge;
            }
            else{
                vertex_adjacency_list.get(a).addAdjacentVertex(vertex_adjacency_list.get(b));
                vertex_adjacency_list.get(b).addAdjacentVertex(vertex_adjacency_list.get(a));
                edge = true;
            }
        }
        return edge;
    
    } // end addEdge() method
    
    
    /**
     * 
     * Remove the edge that connects two vertices
     * 
     * 
     * @param vertex_value_A
     * @param vertex_value_B
     * @return
     * @throws VertexException
     */
    public Boolean removeEdge( AnyType vertex_value_A, AnyType vertex_value_B ) throws VertexException {
        
        // ------------------------------------
        // Basic steps:
        // ------------------------------------
        // 1) For each vertex, check to see if the vertex exists in 
        //    the vertex_adjacency_list. If not, return false indicated 
        //    the edge does not exist. Otherwise goto step 2.
        // 2) In Vertex class, check to see if Vertex B is in Vertex A's
        //    adjacent_vertex_list and vice versa (i.e. an edge exists). 
        //    If the edge does not exist return false, otherwise goto 
        //    step 3.
        // 3) In the Vertex class, remove Vertex B from Vertex A's 
        //    adjacent_vertex_list and vice versa, and then goto step 4. 
        //    Does not exist and return false, otherwise proceed to step 4.
        // 4) If number of adjacent vertices for Vertex A is zero, then 
        //    remove from the vertex_adjacency_list. Likewise, if the 
        //    number of adjacent vertices for Vertex B is zero, then 
        //    remove from _adjacency_list. Lastly, return true indicating 
        //    the edge was successfully removed.
        
        // ----------------------------
        // TODO: Add your code here
        // ----------------------------
        boolean a_in_list = false;
        boolean b_in_list = false;
        boolean edge = false;
        int size = vertex_adjacency_list.size();
        int a = -1;
        int b = -1;
        //Make new vertices with values passed in the parameters
        Vertex<AnyType> A = new Vertex<AnyType>(vertex_value_A);
        Vertex<AnyType> B = new Vertex<AnyType>(vertex_value_B);
        //go through to each vertex and find if the vertices exist
        for(int i = 0; i< size-1; i++){
            if( vertex_adjacency_list.get(i).compareTo(A) == 0){
                a_in_list = true;
                a = i;
            }
            if( vertex_adjacency_list.get(i).compareTo(B) == 0){
                b_in_list = true;
                b = i;
            }
        }
        //if doesn't exist, return false
        if( a_in_list == false || b_in_list == false){
            return false;
        }
        //checks if they're is an edge between vertices
        edge = vertex_adjacency_list.get(a).hasAdjacentVertex(vertex_adjacency_list.get(b));
        if(edge == false){
            return false;
        }
        //if edge exists then remove it from both a's and b's adjacency list
        if(edge){
            vertex_adjacency_list.get(a).removeAdjacentVertex(vertex_adjacency_list.get(b));
            vertex_adjacency_list.get(b).removeAdjacentVertex(vertex_adjacency_list.get(a));
        }
        //checking if no adjacecnt vertices then remove the node
        //bc there is no edge pointing to it
        if(vertex_adjacency_list.get(a).numberOfAdjacentVertices() == 0){
            vertex_adjacency_list.remove(a);
        }
        if(vertex_adjacency_list.get(b).numberOfAdjacentVertices() == 0){
            vertex_adjacency_list.remove(b);
        }
        
            
        return true;
        
    } // end removeEdge() method
    
    /**
     * 
     * Depth first search (DFS) using stack data structure.
     * Specifically, the ConstantTimeStack class.
     * 
     * Must be an iterative solution.
     * 
     * Return a String that shows when each vertex was 
     * visited during the DFS. String format: 
     * <Vertex Value>:<Visited Count>\n
     * 
     * Notes: 
     *  (1) Don't forget to mark each vertex as not visited
     *      before the search begins.
     *  (2) If the start_vertex value does not exist in the 
     *      undirected graph throw a new VertexException.
     *  (3) Vertex must be pushed onto the Stack in the reverse
     *      order they were added to the adjacent_vertex_list 
     *  (4) See assignment for DFS String format example.
     * 
     * @param start_vertex
     * @return
     * @throws VertexException
     */
    public String depthFirstSearch( AnyType start_vertex ) throws VertexException {
        
        StringBuffer buffer = new StringBuffer();
        
        // ----------------------------
        // TODO: Add your code here
        // ----------------------------
        ConstantTimeStack<Vertex<AnyType>> stack = new ConstantTimeStack<Vertex<AnyType>>();
        boolean exists = false;
        Vertex<AnyType> S = new Vertex<AnyType>(start_vertex);
        int counter = 0;
        //loop through to set each vertex to not visited
        for( int i = 0; i < vertex_adjacency_list.size(); i++){
            vertex_adjacency_list.get(i).setVisited(-1);
            //if find the start vertex then set index position to counter
            if(vertex_adjacency_list.get(i).compareTo(S) == 0){
                exists = true;
                vertex_adjacency_list.get(i).setVisited(counter);
                counter = i;
            }
        }
        //if doesn't exist then through exception
        if(exists == false){
            throw new VertexException("Start Vertex does not exist");
        }
        //if it does exist then push start node onto stack, set visited 
        else if(exists){
            stack.push(vertex_adjacency_list.get(counter));
            vertex_adjacency_list.get(counter).setVisited(1);
            Vertex <AnyType> e = vertex_adjacency_list.get(counter);
            counter = 2;
            int n = e.numberOfAdjacentVertices();
            try{
            
                while(true){
                        //loop through e's adjacent vertices 
                        for (n = e.numberOfAdjacentVertices()-1; n >=0 ; n--){
                            //if the adacent vertex has not been visited
                            if(e.getAdjacentVertex(n).hasBeenVisited()==false){
                                //change e to adjacent vertex and push onto stack
                                e = e.getAdjacentVertex(n);
                                stack.push(e);
                                //set e as visited at counter
                                e.setVisited(counter);
                                counter++;
                                //reset n to e's num of vertices
                                n = e.numberOfAdjacentVertices();
                            }
                            //if it has been visited and it's the first node in list
                            //then pop it off the stack and then set e to new top
                            //set n to new num of vertices
                            else if(e.getAdjacentVertex(n).hasBeenVisited() && n == 0){
                                 stack.pop();
                                 e = stack.peek();
                                 n = e.numberOfAdjacentVertices();
                            }
                        }
                }
            }catch(EmptyStackException m){
            }
        }
        //loop through the list and print each with when it was visited
        for(int k = 0; k< vertex_adjacency_list.size(); k++){
            buffer.append(vertex_adjacency_list.get(k) + ":" + vertex_adjacency_list.get(k).getVisited()+ "\n");
        }
    
        return buffer.toString();
    
    } // end depthFirstSearch() method
    
    /**
     * 
     * Breadth first search (BFS) using queue data structure.
     * Specifically, the SemiConstantTimeQueue class
     * 
     * Must be an iterative solution.
     * 
     * Return a String that shows when each vertex was 
     * visited during the BFS. String format: 
     * <Vertex Value>:<Visited Count>\n 
     * 
     * Notes: 
     *  (1) Don't forget to mark each vertex as not visited
     *      before the search begins.
     *  (2) If the start_vertex value does not exist throw a
     *      new VertexException.
     *  (4) Vertex must be added to the Queue in the same
     *      order they were added to the adjacent_vertex_list 
     *  (3) See assignment for BFS String format example.
     * 
     * @param start_vertex
     * @return
     * @throws VertexException
     */
    public String breadthFirstSearch( AnyType start_vertex )  throws VertexException {
        
        StringBuffer buffer = new StringBuffer();
        
        // ----------------------------
        // TODO: Add your code here
        // ----------------------------
        boolean exists = false;
        Vertex<AnyType> S = new Vertex<AnyType>(start_vertex);
        int counter = 1;
        //make new vertex to check if its in list
        //make queue to hold vertices
        SemiConstantTimeQueue<Vertex<AnyType>> queue = new SemiConstantTimeQueue<Vertex<AnyType>>();
        //loop through and set not visited
        for( int i = 0; i < vertex_adjacency_list.size(); i++){
            vertex_adjacency_list.get(i).setVisited(-1);
            //if it's in list then set exists to true
            //and set visited 
            if(vertex_adjacency_list.get(i).compareTo(S) == 0){
                exists = true;
                vertex_adjacency_list.get(i).setVisited(counter);
                counter = i;
            }
        } 
        //if doesn't exist then throw the exception
        if(exists == false){
            throw new VertexException("Start Vertex does not exist");
        }
        //make new queue
        queue.add(vertex_adjacency_list.get(counter));
        vertex_adjacency_list.get(counter).setVisited(1);
        counter = 1;
        int k=0;
        Vertex<AnyType>e;
        //while the queue isn't empty
        while(queue.peek() !=null){
            //make e the top of the queue 
            e = queue.peek();    
            //go through the list and if you reach the begining it exits loop
            for( k = 0; k < vertex_adjacency_list.size()-1; k++){
                if(vertex_adjacency_list.get(k).compareTo(e)==0){
                    break;
                }
            } 
            //go through loop and check if visited, if not then add it to queue, set visited
            for( int j = 0; j< vertex_adjacency_list.get(k).numberOfAdjacentVertices(); j++){
                if(vertex_adjacency_list.get(k).getAdjacentVertex(j).hasBeenVisited()==false){
                    counter++;
                    queue.add(vertex_adjacency_list.get(k).getAdjacentVertex(j));
                    vertex_adjacency_list.get(k).getAdjacentVertex(j).setVisited(counter);
                }
            }
            //remove from queue when through loop once
            k++;
            queue.remove();
        }
        //loop through list and print vertex and when it was visited
        for(int o = 0; o< vertex_adjacency_list.size(); o++){
            buffer.append(vertex_adjacency_list.get(o) + ":" + vertex_adjacency_list.get(o).getVisited()+ "\n");
        }
        return buffer.toString();
    } // end breadthFirstSearch() method
    
    /**
     * 
     */
    public void clear() {
        
        vertex_adjacency_list.clear();
        
    } // end clear()
    
    
    /**
     * 
     * For debugging purposes only
     * 
     */
    public void printAdjacencyList() {
        
        for ( int i=0; i<vertex_adjacency_list.size(); i++ ) {
            
            vertex_adjacency_list.get( i ).printVertex();
            
        }
        
    } // end printGraph() method
    
    
    /**
     * 
     * @param args
     */
    public static void main( String[] args ) {
        
        // -----------------------------------------
        // TODO: Put test cases here
        // -----------------------------------------
        try{
        UndirectedGraph<Integer> graph = new UndirectedGraph<Integer>();
        graph.printAdjacencyList();
        graph.addEdge(1, 2);
        //graph.addEdge(1, 2);
        graph.addEdge(1, 3);
        graph.addEdge(1, 4);
        graph.addEdge(2, 3);
        graph.addEdge(2, 5);
        graph.addEdge(2, 6);
        graph.addEdge(2, 4);
        graph.printAdjacencyList();
        System.out.println("\nDFS\n" + graph.depthFirstSearch(1));
        System.out.println("\nBFS\n" + graph.breadthFirstSearch(1));
        System.out.println();
        graph.removeEdge(1, 4);
        graph.printAdjacencyList();
        System.out.println("DFS\n" + graph.depthFirstSearch(1));
        System.out.println("\nBFS\n" + graph.breadthFirstSearch(1));
        System.out.println();
        graph.removeEdge(2, 5);
        graph.printAdjacencyList();
        System.out.println("DFS\n" + graph.depthFirstSearch(1));
        System.out.println("\nBFS\n" + graph.breadthFirstSearch(1));
        graph.addEdge(5,6);
        graph.printAdjacencyList();
        System.out.println("DFS\n" + graph.depthFirstSearch(1));
        System.out.println("\nBFS\n" + graph.breadthFirstSearch(1));
    }catch(VertexException e){
        System.out.println(e.getMessage());
    }
        
    } // end main() method

} // end UndirectedGraph class definition