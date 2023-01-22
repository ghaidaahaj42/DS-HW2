import java.util.Arrays;

/**
 * FibonacciHeap
 *
 * An implementation of a Fibonacci Heap over integers.
 */
public class FibonacciHeap
{

    public HeapNode min;
    public HeapNode first;
    public int numberOfNodes;
    public int numOfMarked;

    public static int cuts;

    public int numberOfTrees;

    private static int num_links;

    public FibonacciHeap(){
        this.min = null;
        this.first = null;
        this.numberOfNodes = 0;
        this.numOfMarked = 0;
        this.numberOfTrees=0;
    }

    public FibonacciHeap(HeapNode min, HeapNode start, int numberOfNodes,int numOfMarked) {
        this.min = min;
        this.first = start;
        this.numberOfNodes = numberOfNodes;
        this.numOfMarked = numOfMarked;

    }



	/**returns the the number of marked nodes
	@rtype: int
	@returns: the number of marked nodes
	*/
    public int getNumOfMarked() {     ///O(1)
        return numOfMarked;
    }

    /**sets num of marked nodes
	@type num0fMarked: int
	@param num0fMarked: int
	*/
    public void setNumOfMarked(int numOfMarked) {     //O(1)
        this.numOfMarked = numOfMarked;
    }

    /**returns the min Node
     @rtype: HeapNode
     @returns: Node that has the minimum key
     */
    public HeapNode getMin() {     ///O(1)
        return min;
    }


    /**sets the min node
     @type min: HeapNode
     @param min: HeapNode
     */
    public void setMin(HeapNode min) {      ///O(1)
        this.min = min;
    }

    /**returns the first Node
     @rtype: HeapNode
     @returns: the first Node
     */
    public HeapNode getFirst() {         ///O(1)
        return first;
    }


    /**sets the first node
     @type first: HeapNode
     @param first: HeapNode
     */
    public void setFirst(HeapNode first) {        /////O(1)
        this.first = first;
    }


    /**returns the total number of the nodes
     @rtype: int
     @returns: the total number of nodes
     */
    public int getNumberOfNodes() {        ///O(1)
        return numberOfNodes;
    }


    /**sets the number of nodes
     @type n: int
     @param n: int
     */
    public void setNumberOfNodes(int n) {         ///O(1)
        this.numberOfNodes = n;
    }


    /**
     * public boolean isEmpty()
     *
     * Returns true if and only if the heap is empty.
     *
     */
    public boolean isEmpty()   //O(1)
    {
        if(first !=null) return false;
        return true; // should be replaced by student code
    }

    /**
     * public HeapNode insert(int key)
     *
     * Creates a node (of type HeapNode) which contains the given key, and inserts it into the heap.
     * The added key is assumed not to already belong to the heap.
     *
     * Returns the newly created node.
     */
    public HeapNode insert(int key)   //O(1)
    {
        HeapNode node = new HeapNode(key);
        insert_node(node,false,false);
        return node; // should be replaced by student code
    }

    /**
     * public void deleteMin()
     *
     * Deletes the node containing the minimum key.
     *
     */
    public void deleteMin() {      //O(logn) amortized
        if (this.getMin() == null) return;
        // if the min have children
        if (this.getMin().getChild() != null) {
            HeapNode curr = this.getMin();
            // if the min have no brothers
            if (curr.getNext() == curr) {
                this.setFirst(curr.getChild());
                HeapNode ch = curr.getChild().getNext();
                curr.getChild().setParent(null);
                while (ch != curr.getChild()) {
                    ch.setParent(null);
                    ch = ch.getNext();
                }
                curr.setChild(null);
                this.numberOfNodes = numberOfNodes - 1;
            } else {
                // if the min have brothers
                if(this.getMin()==this.getFirst()){
                    this.setFirst(curr.getChild());
                }

                curr.getPrev().setNext(curr.getChild());
                HeapNode ch = curr.getChild().getNext();
                curr.getChild().setParent(null);
                while (ch != curr.getChild()) {
                    ch.setParent(null);
                    ch = ch.getNext();
                }

                curr.getNext().setPrev(ch.getPrev());
                ch.getPrev().setNext(curr.getNext());
                ch.setPrev(curr.getPrev());
                this.numberOfNodes = numberOfNodes - 1;
            }

            // if the min have no children
        } else {
            HeapNode curr = this.getMin();
            // if the min have no brothers
            if (curr.getNext() == curr) {
                this.setFirst(null);
                curr.setParent(null);
                curr.setChild(null);
                this.setMin(null);
                this.setNumberOfNodes(0);
                this.numberOfTrees=0;
                this.setNumOfMarked(0);
                return;
            } else {
                // if the min have brothers
                if(curr==this.first){
                    this.setFirst(curr.getNext());
                }
                curr.getPrev().setNext(curr.getNext());
                curr.getNext().setPrev(curr.getPrev());
                this.numberOfNodes = numberOfNodes - 1;
            }


        }

        if (!this.isEmpty()) {
            linking();
        }


    }




    /**
     * public HeapNode linking()
     *
     * linking the heaps with the same Rank, untill there is no heaps with the same rank
     *
     */
    public void linking() {    //O(logn) amortized
        int maxRank =this.numberOfNodes+1;
        int maxRank_AfterLinking= (int) (Math.log(maxRank)/Math.log((1+Math.sqrt(5))/2)) +1;

        if (maxRank<=0) return;
        HeapNode [] heaps=new HeapNode[maxRank_AfterLinking];
        for (int i = 0; i < heaps.length; i++) {
            heaps[i]=null;
        }

        HeapNode second =this.getFirst().getNext();
        heaps[this.getFirst().getRank()]=this.getFirst();
        while (this.getFirst()!=second){
            HeapNode current =second;
            if(heaps[current.getRank()]==null){
                heaps[current.getRank()]=current;
                second=second.getNext();
                continue;
            }else{
                int index=current.getRank();
                second=second.getNext();
                HeapNode afterLink=linkOnce(current,heaps[index]);
                heaps[index]=null;
                while(index+1 < heaps.length-1 && heaps[index+1]!=null){
                    afterLink=linkOnce(afterLink,heaps[index+1]);
                    heaps[afterLink.getRank()-1]=null;
                    index++;
                }
                heaps[afterLink.getRank()]=afterLink;

            }
        }

        FibonacciHeap toreturn = new FibonacciHeap();
        this.numberOfTrees=0;
        for (HeapNode item:heaps) {
            if (item!=null){
                toreturn.insert_node(item,true,false);

            }
        }

        this.setFirst(toreturn.getFirst());
        this.setMin(toreturn.getMin());
        this.numberOfTrees = toreturn.numberOfTrees;
//        this.numOfMarked = toreturn.numOfMarked;

    }

    /**
     * public HeapNode linkOnce(HeapNode h1,HeapNode h2)
     *
     * make a once link for two heaps
     *
     * @param h1 : HeapNode
     * @param h2 : HeapNode
     * @returns The root of the heap after the linking
     */
    public HeapNode linkOnce(HeapNode h1,HeapNode h2) {   ///O(1)
        num_links +=1;
        if (h1.getKey() < h2.getKey()) {
            //h1 and h2 have rank 0
            if (h1.getRank() == 0) {
                h1.setChild(h2);
                h2.setParent(h1);
                h2.setPrev(h2);
                h2.setNext(h2);
                h1.setNext(h1);
                h1.setPrev(h1);
                h1.setRank(h1.getRank() + 1);
                return h1;
            } else {
                //h1 and h2 have rank bigger than 0
                HeapNode tmp = h1.getChild().getPrev();
                h2.setNext(h1.getChild());
                h2.setPrev(h1.getChild().getPrev());
                h1.getChild().setPrev(h2);
                tmp.setNext(h2);
                h2.setParent(h1);
                h1.setChild(h2);
                h1.setRank(h1.getRank() + 1);
                return h1;
            }
        }
        // h2>h1
        else {
            //h1 and h2 have rank 0
            if (h2.getRank() == 0) {
                h2.setChild(h1);
                h1.setParent(h2);
                h1.setPrev(h1);
                h1.setNext(h1);
                h2.setNext(h2);
                h2.setPrev(h2);
                h2.setRank(h2.getRank() + 1);
                return h2;
            } else {
                //h1 and h2 have rank bigger than 0
                HeapNode tmp = h2.getChild().getPrev();
                h1.setNext(h2.getChild());
                h1.setPrev(h2.getChild().getPrev());
                h2.getChild().setPrev(h1);
                tmp.setNext(h1);
                h1.setParent(h2);
                h2.setChild(h1);
                h2.setRank(h2.getRank() + 1);
                return h2;
            }
        }
    }


    /**
     * public HeapNode findMin()
     *
     * Returns the node of the heap whose key is minimal, or null if the heap is empty.
     *
     */
    public HeapNode findMin()    //O(1)
    {
        if(this.isEmpty()) return null;
        return this.min;// should be replaced by student code
    }

    /**
     * public void meld (FibonacciHeap heap2)
     *
     * Melds heap2 with the current heap.
     *
     */
    public void meld (FibonacciHeap heap2)      //O(1) amortized
    {
        if(this.isEmpty()){
            this.setMin(heap2.getMin());
            this.setFirst(heap2.getFirst());
            this.setNumberOfNodes(heap2.getNumberOfNodes());
            this.setNumOfMarked(heap2.getNumOfMarked());
            this.numberOfTrees=heap2.numberOfTrees;
        }
        if(heap2.isEmpty()){
            return;
        }
        HeapNode tmp = heap2.getFirst().getPrev();
        heap2.getFirst().getPrev().setNext(this.getFirst());
        heap2.getFirst().setPrev(this.getFirst().getPrev());
        this.getFirst().getPrev().setNext(heap2.getFirst());
        this.getFirst().setPrev(tmp);

        this.numberOfTrees+=heap2.numberOfTrees;
        this.numOfMarked+=heap2.numOfMarked;
        this.numberOfNodes+=heap2.numberOfNodes;
        if(heap2.getMin().getKey()<this.getMin().getKey()){
            this.setMin(heap2.getMin());
        }
    }

    /**
     * public int size()
     *
     * Returns the number of elements in the heap.
     *
     */
    public int size()    ///O(1)
    {

        return this.getNumberOfNodes(); // should be replaced by student code
    }

    /**
     * public int[] countersRep()
     *
     * Return an array of counters. The i-th entry contains the number of trees of order i in the heap.
     * (Note: The size of of the array depends on the maximum order of a tree.)
     *
     */
    public int[] countersRep()     ///O(logn)
    {
        if(this.isEmpty()){
            int[] arr = new int[0];
            return arr;
        }
        int[] arr = new int[(int) Math.ceil((Math.log(this.numberOfNodes))/Math.log(2))+1];
        if(this.isEmpty()) return arr;
        HeapNode current=this.getFirst();
        HeapNode first=this.getFirst();
        arr[current.getRank()]=1;
        int lasti=current.getRank();
        while (current.getNext()!=first){
            current=current.getNext();
            if(current.getRank()>lasti) lasti=current.getRank();
            arr[current.getRank()]=arr[current.getRank()]+1;
        }
        return Arrays.copyOfRange(arr,0,lasti+1); //	 to be replaced by student code
    }

    /**
     * public void delete(HeapNode x)
     *
     * Deletes the node x from the heap.
     * It is assumed that x indeed belongs to the heap.
     *
     */

    public void delete(HeapNode x)    ///O(logn) amortized
    {
        this.decreaseKey(x,Integer.MIN_VALUE-x.getKey());
        this.deleteMin();
        // should be replaced by student code
    }


    /**
     * public void decreaseKey(HeapNode x, int delta)
     *
     * Decreases the key of the node x by a non-negative value delta. The structure of the heap should be updated
     * to reflect this change (for example, the cascading cuts procedure should be applied if needed).
     */
    public void decreaseKey(HeapNode x, int delta)    //O(1) amortized
    {
        HeapNode parent = x.getParent();
        if(parent!=null){
            if(parent.getKey()<=x.getKey()-delta){
                x.setKey(x.getKey()-delta);
                return;
            }
            else{
                x.setKey(x.getKey()-delta);
                casading_cut(x,parent);
                if(x.getKey()<this.getMin().getKey()){
                    this.setMin(x);
                }
            }
        }
        else {
            x.setKey(x.getKey()-delta);
            if(x.getKey()<this.getMin().getKey()){
                this.setMin(x);}
        }
        return; // should be replaced by student code
    }



    /**
     * public int nonMarked()
     *
     * This function returns the current number of non-marked items in the heap
     */
    public int nonMarked()   //O(1)
    {

        return numberOfNodes-numOfMarked; // should be replaced by student code
    }

    /**
     * public int potential()
     *
     * This function returns the current potential of the heap, which is:
     * Potential = #trees + 2*#marked
     *
     * In words: The potential equals to the number of trees in the heap
     * plus twice the number of marked nodes in the heap.
     */
    public int potential()    //O(1)
    {
        return this.numberOfTrees+2*this.numOfMarked; // should be replaced by student code
//        return this.numberOfTrees+2*numberOfMarked;
    }

    /**
     * public static int totalLinks()
     *
     * This static function returns the total number of link operations made during the
     * run-time of the program. A link operation is the operation which gets as input two
     * trees of the same rank, and generates a tree of rank bigger by one, by hanging the
     * tree which has larger value in its root under the other tree.
     */
    public static int totalLinks()     //O(1)
    {

        return num_links; // should be replaced by student code


    }

    /**
     * public static int totalCuts()
     *
     * This static function returns the total number of cut operations made during the
     * run-time of the program. A cut operation is the operation which disconnects a subtree
     * from its parent (during decreaseKey/delete methods).
     */
    public static int totalCuts()    //O(1)
    {
        return cuts; // should be replaced by student code
    }

    /**
     * public static int[] kMin(FibonacciHeap H, int k)
     *
     * This static function returns the k smallest elements in a Fibonacci heap that contains a single tree.
     * The function should run in O(k*deg(H)). (deg(H) is the degree of the only tree in H.)
     *
     * ###CRITICAL### : you are NOT allowed to change H.
     */
    public static int[] kMin(FibonacciHeap H, int k)     ///O(k*log(H))
    {

        int[] result= new int[k];
        if(k==0) return result;
        if (H.isEmpty()) return result;
        FibonacciHeap current = new FibonacciHeap();
        HeapNode node = H.getMin();
        node.setIndex(node);
        current.insert_node(node,false,false);
        int i=0;
        while (i<k){
            if(current.getMin()!=null) {
                node = current.getMin().getIndex();
            }
            result[i] = node.getKey();
            current.deleteMin();
            HeapNode child = node.getChild();


            if(child!=null) {
                child.setIndex(child);
                current.insert_node(child,false,false);
                HeapNode child_next = child.getNext();
                while (child != child_next) {
                    child_next.setIndex(child_next);
                    current.insert_node(child_next,false,false);
                    child_next = child_next.getNext();
                }

            }
            node=current.min;
            node.setIndex(node);

            i++;
        }
        return result; // should be replaced by student code
    }

    /**
     *public void cut (HeapNode node,HeapNode parent)
     *
     * This function well cutt the link between the node and its father(parnet)
     * and the node will be a new root
     *
     * @post: we will have a new subtree , its root is the node
     */

    public void cut(HeapNode node, HeapNode parnet){    ///O(1) amortized
        if(node.getMark()){
            numOfMarked--;
        }
        node.setMark(false);
        node.setParent(null);
        if(node.getNext()==node){
            parnet.setChild(null);
        }else{
            if(node==parnet.getChild()) {
                parnet.setChild(node.getNext());
            }
            node.getPrev().setNext(node.getNext());
            node.getNext().setPrev(node.getPrev());
        }
        parnet.setRank(parnet.getRank()-1);
        insert_node(node,false,true);
    }

    public void casading_cut(HeapNode node,HeapNode parnet){    //O(1) amortized
        cuts++;
        cut(node,parnet);
        if(parnet.getParent()!=null){
            if(!parnet.getMark()){
                parnet.setMark(true);
                numOfMarked++;
            }
            else{
                casading_cut(parnet,parnet.getParent());
            }
        }
    }


    /**
     * public HeapNode insert_node(HeapNode node,boolean linking,boolean cutting)
     *
     * Creates a node (of type HeapNode) which contains the given key, and inserts it into the heap.
     * The added key is assumed not to already belong to the heap.
     *
     * @param node: HeapNode
     * @param linking:boolean
     * @param cutting:boolean
     * @post: insert the node into the heap (the most left)
     *
     */
    public void insert_node(HeapNode node,boolean linking,boolean cutting){        ///O(1)
        this.numberOfTrees++;
        if(this.isEmpty()){
            this.setMin(node);
            this.setFirst(node);
            this.getFirst().setNext(this.getFirst());
            this.getFirst().setPrev(this.getFirst());
            this.setNumberOfNodes(1);
        }

        else{

            if(linking){
                node.setPrev(this.getFirst().getPrev());
                node.setNext(this.getFirst());
                this.getFirst().getPrev().setNext(node);
                this.getFirst().setPrev(node);
            }else {
                HeapNode tmp = this.getFirst().getPrev();
                node.setNext(this.getFirst());
                node.setPrev(this.getFirst().getPrev());

                this.getFirst().getPrev().setNext(node);
                this.getFirst().setPrev(node);
            }
            if(linking==false){
                this.setFirst(node);
                if(!cutting){
                    this.setNumberOfNodes(this.getNumberOfNodes()+1);
                }
            }

        }
        if(node.getKey()<this.getMin().getKey()){
            this.setMin(node);
        }
    }



    /**
     * public class HeapNode
     *
     * If you wish to implement classes other than FibonacciHeap
     * (for example HeapNode), do it in this file, not in another file.
     *
     */
    public static class HeapNode{

        public int key;
        public HeapNode  next;
        public HeapNode prev;
        public HeapNode parent;
        public HeapNode child;
        public int rank;
        boolean mark;
        HeapNode index;

        public HeapNode(int key) {

            this.key = key;
            this.rank = 0;
            this.mark = false;
            this.parent = null;
            this.prev = this;
            this.next = this;
            this.child = null;
            this.index = null;
        }

        /**sets the node it self
         @type index: HeapNode
         @param index: HeapNode
         */
        public void setIndex(HeapNode index) {    ///O(1)
            this.index = index;
        }


        /**returns the node it self
         @rtype: HeapNode
         @returns: the node it self
         */
        public HeapNode getIndex() {    //O(1)
            return index;
        }


        /**returns the key of the node
         @rtype: int
         @returns: the key of the node
         */
        public int getKey() {      //O(1)

            return this.key;
        }

        /**returns the next node
         @rtype: HeapNode
         @returns: the next node
         */
        public HeapNode getNext() {      //O(1)
            return this.next;
        }

        /**returns the prev node
         @rtype: HeapNode
         @returns: the prev node
         */
        public HeapNode getPrev() {     ///O(1)
            return this.prev;
        }

        /**returns the parent node
         @rtype: HeapNode
         @returns: the parent of the node
         */
        public HeapNode getParent() {     ///O(1)
            return this.parent;
        }

        /**returns the child of the node
         @rtype: HeapNode
         @returns: the child of the node
         */
        public HeapNode getChild() {       ///O(1)
            return this.child;
        }

        /**returns the rank of the node
         @rtype: int
         @returns: the rank of the node
         */
        public int getRank() {             ///O(1)
            return this.rank;
        }

        /**returns the mark of the node
         @rtype: boolean
         @returns: the mark of the node
         */
        public boolean getMark() {           ////O(1)
            return mark;
        }

        /**sets the key of the node
         @type key: int
         @param key: int
         */
        public void setKey(int key) {       ///O(1)
            this.key = key;
        }

        /**sets the next of the node
         @type next: HeapNode
         @param next: HeapNode
         */
        public void setNext(HeapNode next) {        ///O(1)
            this.next = next;
        }

        /**sets the prev of the node
         @type prev: HeapNode
         @param prev: HeapNode
         */
        public void setPrev(HeapNode prev) {         ///O(1)
            this.prev = prev;
        }

        /**sets the parent of the node
         @type parent: HeapNode
         @param parent: HeapNode
         */
        public void setParent(HeapNode parent) {       ///O(1)
            this.parent = parent;
        }

        /**sets the child of the node
         @type child: HeapNode
         @param child: HeapNode
         */
        public void setChild(HeapNode child) {         ////O(1)
            this.child = child;
        }

        /**sets the rank of the node
         @type rank: HeapNode
         @param rank: HeapNode
         */
        public void setRank(int rank) {       ////O(1)
            this.rank = rank;
        }

        /**sets the mark of the node
         @type mark: HeapNode
         @param mark: HeapNode
         */
        public void setMark(boolean mark) {        ////O(1)
            this.mark = mark;
        }

    }
}
