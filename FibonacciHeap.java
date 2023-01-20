import java.util.ArrayList;
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
    HeapNode leftestRoot;
    HeapNode rightestRoot;

    public FibonacciHeap(){
        this.min = null;
        this.first = null;
        this.numberOfNodes = 0;
        this.numOfMarked = 0;
        this.numberOfTrees=0;
        this.leftestRoot = null;
    }

    public FibonacciHeap(HeapNode min, HeapNode start, int numberOfNodes,int numOfMarked) {
        this.min = min;
        this.first = start;
        this.numberOfNodes = numberOfNodes;
        this.numOfMarked = numOfMarked;

    }

    public int getNumOfMarked() {
        return numOfMarked;
    }

    public void setNumOfMarked(int numOfMarked) {
        this.numOfMarked = numOfMarked;
    }

    public HeapNode getMin() {
        return min;
    }

    public void setMin(HeapNode min) {
        this.min = min;
    }

    public HeapNode getFirst() {
        return first;
    }

    public void setFirst(HeapNode first) {
        this.first = first;
    }

    public int getNumberOfNodes() {
        return numberOfNodes;
    }

    public void setNumberOfNodes(int n) {
        this.numberOfNodes = n;
    }


    /**
     * public boolean isEmpty()
     *
     * Returns true if and only if the heap is empty.
     *
     */
    public boolean isEmpty()
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
    public HeapNode insert(int key)
    {
        HeapNode node = new HeapNode(key);
        insert_node(node,false);
        return node; // should be replaced by student code
    }

    /**
     * public void deleteMin()
     *
     * Deletes the node containing the minimum key.
     *
     */
    public void deleteMin() {
        if (this.getMin() == null) return;
        if(this.getMin()== this.leftestRoot){                 ///////me add
            leftestRoot = getMin().getNext();                 //////// me add
        }
        if(this.getMin()==this.rightestRoot){            //// me add
            rightestRoot = getMin().getPrev();               /// me add
        }
        // if the min have children
        if (this.getMin().getChild() != null) {
            HeapNode curr = this.getMin();
            // if the min have no brothers
            if (curr.getNext() == curr) {
                this.setFirst(curr.getChild());
//                this.setMin(curr.getChild());
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
//                this.setFirst(curr.getChild());

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
//                this.setMin(ch);
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
//                this.setMin(null);
                this.numberOfNodes = numberOfNodes - 1;
                return;
            } else {
                // if the min have brothers
                this.setFirst(curr.getNext());
                curr.getPrev().setNext(curr.getNext());
                curr.getNext().setPrev(curr.getPrev());
//                this.setMin(curr.getPrev());
                this.numberOfNodes = numberOfNodes - 1;
            }


        }

        if (!this.isEmpty() ) {
            linking();
        }

        //setting the Min
        this.setMin(this.getFirst());

        HeapNode second = this.getFirst().getNext();
        while(this.getFirst() != second){
            if(second.getKey()<this.getFirst().getKey()){
                this.setMin(second);
            }
            second = second.getNext();
        }

        this.leftestRoot = this.getFirst();                          //// me add
        this.rightestRoot = this.getFirst().getPrev();                 ///// me add
//        this.setFirst(this.getMin());

    }

    public void linking() {
//        int maxRank= (int) (Math.ceil(Math.log(this.numberOfNodes)/Math.log((Math.sqrt(5)+1)/2)));
        int maxRank =this.numberOfNodes+1;
        if (maxRank<=0) return;
        HeapNode [] heaps=new HeapNode[maxRank];
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
//                num_links +=1;
                heaps[index]=null;
                while(index+1 < heaps.length-1 && heaps[index+1]!=null){
                    afterLink=linkOnce(afterLink,heaps[index+1]);
//                    num_links +=1;
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
                this.numberOfTrees++;
                toreturn.numberOfTrees++;
                toreturn.insert_node(item,true);

            }
        }

        this.setFirst(toreturn.getFirst());
        this.rightestRoot=this.getFirst().getPrev();                 ///////me add
        this.leftestRoot=this.getFirst();                           ///////// me add

    }
    public HeapNode linkOnce(HeapNode h1,HeapNode h2) {
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
    public HeapNode findMin()
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
    public void meld (FibonacciHeap heap2)
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
    public int size()
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
    public int[] countersRep()
    {
        int[] arr = new int[(int) Math.ceil(Math.log(this.numberOfNodes+1))];
        if(this.isEmpty()) return arr;
        HeapNode current=this.getFirst();
        HeapNode first=this.getFirst();
        arr[current.getRank()]=1;
        int lasti=0;
        while (current.getNext()!=first){
            if(current.getRank()>lasti) lasti=current.getRank();
            arr[current.getRank()]=arr[current.getRank()]+1;
            current=current.getNext();
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

    public void delete(HeapNode x)
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
    public void decreaseKey(HeapNode x, int delta)
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
    public int nonMarked()
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
    public int potential()
    {
        return this.numberOfTrees+2*this.numOfMarked; // should be replaced by student code
    }

    /**
     * public static int totalLinks()
     *
     * This static function returns the total number of link operations made during the
     * run-time of the program. A link operation is the operation which gets as input two
     * trees of the same rank, and generates a tree of rank bigger by one, by hanging the
     * tree which has larger value in its root under the other tree.
     */
    public static int totalLinks()
    {

        return num_links; // should be replaced by student code


// should be replaced by student code

    }

    /**
     * public static int totalCuts()
     *
     * This static function returns the total number of cut operations made during the
     * run-time of the program. A cut operation is the operation which disconnects a subtree
     * from its parent (during decreaseKey/delete methods).
     */
    public static int totalCuts()
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
    public static int[] kMin(FibonacciHeap H, int k)
    {

        int[] result= new int[k];
        if(k==0) return result;
        if (H.isEmpty()) return result;
        FibonacciHeap current = new FibonacciHeap();
        HeapNode node = H.getMin();
        node.setIndex(node);
        current.insert_node(node,false);
//        arr[0]=H.getMin().getKey();
        int i=0;
        while (i<k){
//            current.insert_node(node);
            if(current.getMin()!=null) {
                node = current.getMin().getIndex();
            }
            result[i] = node.getKey();
            current.deleteMin();
            HeapNode child = node.getChild();


            if(child!=null) {
                child.setIndex(child);
                current.insert_node(child,false);
                HeapNode child_next = child.getNext();
                while (child != child_next) {
                    child_next.setIndex(child_next);
                    current.insert_node(child_next,false);
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

    public void cut(HeapNode node, HeapNode parnet){
        if(node.getMarked()){
            numOfMarked--;
        }
        node.setMark(false);
        node.setParent(null);
        if(node.getNext()==node){
            parnet.setChild(null);
        }else{
            parnet.setChild(node.getNext());
            node.getPrev().setNext(node.getNext());
            node.getNext().setPrev(node.getPrev());
        }
        parnet.setRank(parnet.getRank()-1);
        insert_node(node,false);
    }

    public void casading_cut(HeapNode node,HeapNode parnet){
        cuts++;
        cut(node,parnet);
        if(parnet.getParent()!=null){
            if(!parnet.getMarked()){
                parnet.setMark(true);
                numOfMarked++;
            }
            else{
                casading_cut(parnet,parnet.getParent());
            }
        }
    }

    public void insert_node(HeapNode node,boolean linking){
        this.numberOfTrees++;
        this.leftestRoot = node;          ///////////////////me add
        if(this.isEmpty()){
            this.setMin(node);
            this.setFirst(node);
            this.getFirst().setNext(this.getFirst());
            this.getFirst().setPrev(this.getFirst());
            this.setNumberOfNodes(1);
            this.rightestRoot = node;        ///////////////////me add
        }

        else{

            HeapNode tmp = this.getFirst().getPrev();
            node.setNext(this.getFirst());
            node.setPrev(this.getFirst().getPrev());

            this.getFirst().getPrev().setNext(node);
            this.getFirst().setPrev(node);
            if(linking==false){
                this.setFirst(node);
            }
            this.setNumberOfNodes(this.getNumberOfNodes()+1);

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

        public void setIndex(HeapNode index) {
            this.index = index;
        }

        public HeapNode getIndex() {
            return index;
        }

        public boolean getMark() {
            return mark;
        }

        public int getKey() {

            return this.key;
        }

        public HeapNode getNext() {
            return this.next;
        }

        public HeapNode getPrev() {
            return this.prev;
        }

        public HeapNode getParent() {
            return this.parent;
        }

        public HeapNode getChild() {
            return this.child;
        }

        public int getRank() {
            return this.rank;
        }

        public boolean getMarked() {
            return mark;
        }

        public void setKey(int key) {
            this.key = key;
        }

        public void setNext(HeapNode next) {
            this.next = next;
        }

        public void setPrev(HeapNode prev) {
            this.prev = prev;
        }

        public void setParent(HeapNode parent) {
            this.parent = parent;
        }

        public void setChild(HeapNode child) {
            this.child = child;
        }

        public void setRank(int rank) {
            this.rank = rank;
        }

        public void setMark(boolean mark) {
            this.mark = mark;
        }

    }

    public static void main(String[] args) {
//        FibonacciHeap h = new FibonacciHeap();
        FibonacciHeap h = new FibonacciHeap();
        int i =10;
        int m =(int) Math.pow(2,i);
        ArrayList<HeapNode> nodeArr= new ArrayList<>(m+1);

        for (int j = 0; j <m+1 ; j++) {
            nodeArr.add(null);
        }
        for (int j = m-1; j >=0 ; j--) {
            nodeArr.set(j,h.insert(j));
        }
        h.insert(-1);
        h.deleteMin();
        for (int j = i; j >0 ; j--) {
            h.decreaseKey(nodeArr.get((int)(m+1-Math.pow(2,j))),m+1);
        }
        h.decreaseKey(nodeArr.get((int)(m-2)),m+1);

        System.out.println("LINKS");
        System.out.println(FibonacciHeap.totalLinks());
        System.out.println("cuts");
        System.out.println(FibonacciHeap.totalCuts());
        System.out.println("potintial");
        System.out.println(h.potential());
        System.out.println("Trees");
        System.out.println(h.numberOfTrees);
        System.out.println(h.numOfMarked);
    }


}
