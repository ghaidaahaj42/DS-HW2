import java.util.HashMap;

public class FibTester {
    public static void main(String[] args) { // EACH TEST WORKS ALONE
//        testInsert();
//        testDeleteMin();
//        testDecreaseKey();
//        testDelete();
//        testMeld();
        q2();
    }

    public static void q2() {
        int[] listOfIs = {6,8,10,12,14};
        int m;
        int links = 0;
        for ( int i : listOfIs){
            FibonacciHeap fibi = new FibonacciHeap();
            m = (int) Math.pow(3, i) - 1;
            System.out.print("m: ");
            System.out.println(m);
            long t1 = System.currentTimeMillis();
            System.out.print("t1: ");
            System.out.println(t1);
            for (int k=0; k<=m; k++){
                fibi.insert(k);
            }
            int newRange = (3*m)/4;
            for (int k=1; k<=newRange; k++){
                fibi.deleteMin();
            }
            long t2 = System.currentTimeMillis();
            System.out.print("t2: ");
            System.out.println(t2);
            System.out.print("Run-Time (ms): ");
            System.out.println(t2-t1);
            System.out.print("totalLinks: ");
            System.out.println(FibonacciHeap.totalLinks()-links);
            links = FibonacciHeap.totalLinks();
            System.out.print("totalCuts: ");
            System.out.println(FibonacciHeap.totalCuts());
            System.out.print("Potential: ");
            System.out.println(fibi.potential());
            System.out.println("");
        }
    }

    public static void testMeld() {

        FibonacciHeap empty = new FibonacciHeap();
        FibonacciHeap twoRoots = new FibonacciHeap();
        twoRoots.insert(55);
        twoRoots.insert(80);

        empty.meld(twoRoots);

        ////////////////////// ONLY ROOT + ONLY ROOT //////////////////////

        FibonacciHeap onlyRoot1 = new FibonacciHeap();
        onlyRoot1.insert(70);
        FibonacciHeap onlyRoot2 = new FibonacciHeap();
        onlyRoot2.insert(80);
        onlyRoot1.meld(onlyRoot2);
        if (onlyRoot1.min.getKey() != 70) {System.out.println("problem min!!!");}
        if (onlyRoot1.numOfMarked != 0) {System.out.println("problem marked!!!");}
        if (FibonacciHeap.totalCuts() != 0 ) {System.out.println("problem cuts!!!");}
        if (FibonacciHeap.totalLinks() != 0 ) {System.out.println("problem links!!!");}
        if (onlyRoot1.leftestRoot.getKey() != 70) {System.out.println("problem leftest!!!");}
        if (onlyRoot1.rightestRoot.getKey() != 80) {System.out.println("problem rightest!!!");}
        if (onlyRoot1.numberOfTrees != 2) {System.out.println("problem numberOfTrees!!!");}
        if (onlyRoot1.size() != 2) {System.out.println("problem numberOfTrees!!!");}
        int[] counter = onlyRoot1.countersRep();
        if (counter.length != 1) {System.out.println("problem with len!!!");}
        if (counter[0] != 2){System.out.println("problem with i=0!!!");}

        onlyRoot1 = new FibonacciHeap();
        onlyRoot1.insert(70);
        onlyRoot2 = new FibonacciHeap();
        onlyRoot2.insert(80);
        onlyRoot2.meld(onlyRoot1);
        if (onlyRoot2.min.getKey() != 70) {System.out.println("problem min!!!");}
        if (onlyRoot2.numOfMarked != 0) {System.out.println("problem marked!!!");}
        if (FibonacciHeap.totalCuts() != 0 ) {System.out.println("problem cuts!!!");}
        if (FibonacciHeap.totalLinks() != 0 ) {System.out.println("problem links!!!");}
        if (onlyRoot2.leftestRoot.getKey() != 80) {System.out.println("problem leftest!!!");}
        if (onlyRoot2.rightestRoot.getKey() != 70) {System.out.println("problem rightest!!!");}
        if (onlyRoot2.numberOfTrees != 2) {System.out.println("problem numberOfTrees!!!");}
        if (onlyRoot2.size() != 2) {System.out.println("problem numberOfTrees!!!");}
        counter = onlyRoot2.countersRep();
        if (counter.length != 1) {System.out.println("problem with len!!!");}
        if (counter[0] != 2){System.out.println("problem with i=0!!!");}

        ////////////////////// ONLY ROOT + ONLY 2 ROOTS //////////////////////

        onlyRoot1 = new FibonacciHeap();
        onlyRoot1.insert(10);
        FibonacciHeap only2roots = new FibonacciHeap();
        only2roots.insert(55);
        only2roots.insert(80);
        onlyRoot1.meld(only2roots);
        if (onlyRoot1.min.getKey() != 10) {System.out.println("problem min!!!");}
        if (onlyRoot1.numOfMarked != 0) {System.out.println("problem marked!!!");}
        if (FibonacciHeap.totalCuts() != 0 ) {System.out.println("problem cuts!!!");}
        if (FibonacciHeap.totalLinks() != 0 ) {System.out.println("problem links!!!");}
        if (onlyRoot1.leftestRoot.getKey() != 10) {System.out.println("problem leftest!!!");}
        if (onlyRoot1.rightestRoot.getKey() != 55) {System.out.println("problem rightest!!!");}
        if (onlyRoot1.numberOfTrees != 3) {System.out.println("problem numberOfTrees!!!");}
        if (onlyRoot1.size() != 3) {System.out.println("problem numberOfTrees!!!");}
        counter = onlyRoot1.countersRep();
        if (counter.length != 1) {System.out.println("problem with len!!!");}
        if (counter[0] != 3){System.out.println("problem with i=0!!!");}

        ////////////////////// ONLY 2 ROOTS + ONLY ROOT //////////////////////

        only2roots = new FibonacciHeap();
        only2roots.insert(55);
        only2roots.insert(80);
        onlyRoot1 = new FibonacciHeap();
        onlyRoot1.insert(10);
        only2roots.meld(onlyRoot1);
        if (only2roots.min.getKey() != 10) {System.out.println("problem min!!!");}
        if (only2roots.numOfMarked != 0) {System.out.println("problem marked!!!");}
        if (FibonacciHeap.totalCuts() != 0 ) {System.out.println("problem cuts!!!");}
        if (FibonacciHeap.totalLinks() != 0 ) {System.out.println("problem links!!!");}
        if (only2roots.leftestRoot.getKey() != 80) {System.out.println("problem leftest!!!");}
        if (only2roots.rightestRoot.getKey() != 10) {System.out.println("problem rightest!!!");}
        if (only2roots.numberOfTrees != 3) {System.out.println("problem numberOfTrees!!!");}
        if (only2roots.size() != 3) {System.out.println("problem numberOfTrees!!!");}
        counter = only2roots.countersRep();
        if (counter.length != 1) {System.out.println("problem with len!!!");}
        if (counter[0] != 3){System.out.println("problem with i=0!!!");}

        ////////////////////// ONLY 3 ROOTS + ONLY ROOT //////////////////////

        FibonacciHeap only3roots = new FibonacciHeap();
        only3roots.insert(20);
        only3roots.insert(5);
        only3roots.insert(10);
        onlyRoot1 = new FibonacciHeap();
        onlyRoot1.insert(7);
        only3roots.meld(onlyRoot1);
        if (only3roots.min.getKey() != 5) {System.out.println("problem min!!!");}
        if (only3roots.numOfMarked != 0) {System.out.println("problem marked!!!");}
        if (FibonacciHeap.totalCuts() != 0 ) {System.out.println("problem cuts!!!");}
        if (FibonacciHeap.totalLinks() != 0 ) {System.out.println("problem links!!!");}
        if (only3roots.leftestRoot.getKey() != 10) {System.out.println("problem leftest!!!");}
        if (only3roots.rightestRoot.getKey() != 7) {System.out.println("problem rightest!!!");}
        if (only3roots.numberOfTrees != 4) {System.out.println("problem numberOfTrees!!!");}
        if (only3roots.size() != 4) {System.out.println("problem numberOfTrees!!!");}
        counter = only3roots.countersRep();
        if (counter.length != 1) {System.out.println("problem with len!!!");}
        if (counter[0] != 4){System.out.println("problem with i=0!!!");}

        ////////////////////// ONLY 3 ROOTS + ONLY ROOT //////////////////////

        only3roots = new FibonacciHeap();
        only3roots.insert(20);
        only3roots.insert(7);
        only3roots.insert(10);
        onlyRoot1 = new FibonacciHeap();
        onlyRoot1.insert(5);
        only3roots.meld(onlyRoot1);
        if (only3roots.min.getKey() != 5) {System.out.println("problem min!!!");}
        if (only3roots.numOfMarked != 0) {System.out.println("problem marked!!!");}
        if (FibonacciHeap.totalCuts() != 0 ) {System.out.println("problem cuts!!!");}
        if (FibonacciHeap.totalLinks() != 0 ) {System.out.println("problem links!!!");}
        if (only3roots.leftestRoot.getKey() != 10) {System.out.println("problem leftest!!!");}
        if (only3roots.rightestRoot.getKey() != 5) {System.out.println("problem rightest!!!");}
        if (only3roots.numberOfTrees != 4) {System.out.println("problem numberOfTrees!!!");}
        if (only3roots.size() != 4) {System.out.println("problem numberOfTrees!!!");}
        counter = only3roots.countersRep();
        if (counter.length != 1) {System.out.println("problem with len!!!");}
        if (counter[0] != 4){System.out.println("problem with i=0!!!");}

        ////////////////////// ONLY ROOT + ONLY 3 ROOTS //////////////////////

        onlyRoot1 = new FibonacciHeap();
        onlyRoot1.insert(5);
        only3roots = new FibonacciHeap();
        only3roots.insert(20);
        only3roots.insert(7);
        only3roots.insert(10);
        onlyRoot1.meld(only3roots);
        if (onlyRoot1.min.getKey() != 5) {System.out.println("problem min!!!");}
        if (onlyRoot1.numOfMarked != 0) {System.out.println("problem marked!!!");}
        if (FibonacciHeap.totalCuts() != 0 ) {System.out.println("problem cuts!!!");}
        if (FibonacciHeap.totalLinks() != 0 ) {System.out.println("problem links!!!");}
        if (onlyRoot1.leftestRoot.getKey() != 5) {System.out.println("problem leftest!!!");}
        if (onlyRoot1.rightestRoot.getKey() != 20) {System.out.println("problem rightest!!!");}
        if (onlyRoot1.numberOfTrees != 4) {System.out.println("problem numberOfTrees!!!");}
        if (onlyRoot1.size() != 4) {System.out.println("problem numberOfTrees!!!");}
        counter = onlyRoot1.countersRep();
        if (counter.length != 1) {System.out.println("problem with len!!!");}
        if (counter[0] != 4){System.out.println("problem with i=0!!!");}

        ////////////////////// ONLY ROOT + ONLY 3 ROOTS //////////////////////

        onlyRoot1 = new FibonacciHeap();
        onlyRoot1.insert(7);
        only3roots = new FibonacciHeap();
        only3roots.insert(20);
        only3roots.insert(5);
        only3roots.insert(10);
        onlyRoot1.meld(only3roots);
        if (onlyRoot1.min.getKey() != 5) {System.out.println("problem min!!!");}
        if (onlyRoot1.numOfMarked != 0) {System.out.println("problem marked!!!");}
        if (FibonacciHeap.totalCuts() != 0 ) {System.out.println("problem cuts!!!");}
        if (FibonacciHeap.totalLinks() != 0 ) {System.out.println("problem links!!!");}
        if (onlyRoot1.leftestRoot.getKey() != 7) {System.out.println("problem leftest!!!");}
        if (onlyRoot1.rightestRoot.getKey() != 20) {System.out.println("problem rightest!!!");}
        if (onlyRoot1.numberOfTrees != 4) {System.out.println("problem numberOfTrees!!!");}
        if (onlyRoot1.size() != 4) {System.out.println("problem numberOfTrees!!!");}
        counter = onlyRoot1.countersRep();
        if (counter.length != 1) {System.out.println("problem with len!!!");}
        if (counter[0] != 4){System.out.println("problem with i=0!!!");}

        ////////////////////// ONLY ROOT + LARGE HEAP //////////////////////

        onlyRoot1 = new FibonacciHeap();
        onlyRoot1.insert(67);
        int [] listOfKeys = {80,2,5,90,77,8,1000,1,6,4,10,190,7,3};
        FibonacciHeap heapFromListOfKeys = new FibonacciHeap();
        for (int num : listOfKeys) {
            heapFromListOfKeys.insert(num);
        }
        heapFromListOfKeys.deleteMin();

        if (heapFromListOfKeys.min.getKey() != 2) {System.out.println("problem min=2!!!");}
        if (heapFromListOfKeys.numOfMarked != 0) {System.out.println("problem marked!!!");}
        if (FibonacciHeap.totalCuts() != 0 ) {System.out.println("problem cuts!!!");}
        if (FibonacciHeap.totalLinks() != 10 ) {System.out.println("problem links!!!");}
        if (heapFromListOfKeys.leftestRoot.getKey() != 80) {System.out.println("problem leftest!!!");}
        if (heapFromListOfKeys.rightestRoot.getKey() != 3) {System.out.println("problem rightest!!!");}
        if (heapFromListOfKeys.numberOfTrees != 3) {System.out.println("problem numberOfTrees!!!");}
        if (heapFromListOfKeys.size() != 13) {System.out.println("problem size!!!");}

        onlyRoot1.meld(heapFromListOfKeys);

        if (onlyRoot1.min.getKey() != 2) {System.out.println("problem min!!!");}
        if (onlyRoot1.numOfMarked != 0) {System.out.println("problem marked!!!");}
        if (FibonacciHeap.totalCuts() != 0 ) {System.out.println("problem cuts!!!");}
        if (FibonacciHeap.totalLinks() != 10 ) {System.out.println("problem links!!!");}
        if (onlyRoot1.leftestRoot.getKey() != 67) {System.out.println("problem leftest!!!");}
        if (onlyRoot1.rightestRoot.getKey() != 3) {System.out.println("problem rightest!!!");}
        if (onlyRoot1.numberOfTrees != 4) {System.out.println("problem numberOfTrees!!!");}
        if (onlyRoot1.size() != 14) {System.out.println("problem size!!!");}
        counter = onlyRoot1.countersRep();
        if (counter.length != 4) {System.out.println("problem with len!!!");}
        for(int i=0; i<4; i++){
            if (i == 0 && counter[i] != 2){System.out.println("problem with counter 1!!!");}
            if (i == 1 && counter[i] != 0){System.out.println("problem with counter 1!!!");}
            if (i != 1 && i != 0 && counter[i] != 1){System.out.println("problem with counter 0,2,3 !!!");}
        }

        ////////////////////// ONLY ROOT + LARGE HEAP //////////////////////

        onlyRoot1 = new FibonacciHeap();
        onlyRoot1.insert(1);
        heapFromListOfKeys = new FibonacciHeap();
        for (int num : listOfKeys) {
            heapFromListOfKeys.insert(num);
        }
        heapFromListOfKeys.deleteMin();

        onlyRoot1.meld(heapFromListOfKeys);

        if (onlyRoot1.min.getKey() != 1) {System.out.println("problem min!!!");}
        if (onlyRoot1.numOfMarked != 0) {System.out.println("problem marked!!!");}
        if (FibonacciHeap.totalCuts() != 0 ) {System.out.println("problem cuts!!!");}
        if (FibonacciHeap.totalLinks() != 20 ) {System.out.println("problem links!!!");}
        if (onlyRoot1.leftestRoot.getKey() != 1) {System.out.println("problem leftest!!!");}
        if (onlyRoot1.rightestRoot.getKey() != 3) {System.out.println("problem rightest!!!");}
        if (onlyRoot1.numberOfTrees != 4) {System.out.println("problem numberOfTrees!!!");}
        if (onlyRoot1.size() != 14) {System.out.println("problem numberOfTrees!!!");}
        counter = onlyRoot1.countersRep();
        if (counter.length != 4) {System.out.println("problem with len!!!");}
        for(int i=0; i<4; i++){
            if (i == 0 && counter[i] != 2){System.out.print("problem with counter 1!!!");}
            if (i == 1 && counter[i] != 0){System.out.print("problem with counter 1!!!");}
            if (i != 1 && i != 0 && counter[i] != 1){System.out.println("problem with counter 0,2,3 !!!");}
        }

        ////////////////////// LARGE HEAP + ONLY ROOT //////////////////////

        heapFromListOfKeys = new FibonacciHeap();
        for (int num : listOfKeys) {
            heapFromListOfKeys.insert(num);
        }
        onlyRoot1 = new FibonacciHeap();
        onlyRoot1.insert(67);
        heapFromListOfKeys.deleteMin();

        heapFromListOfKeys.meld(onlyRoot1);

        if (heapFromListOfKeys.min.getKey() != 2) {System.out.println("problem min!!!");}
        if (heapFromListOfKeys.numOfMarked != 0) {System.out.println("problem marked!!!");}
        if (FibonacciHeap.totalCuts() != 0 ) {System.out.println("problem cuts!!!");}
        if (FibonacciHeap.totalLinks() != 30 ) {System.out.println("problem links!!!");}
        if (heapFromListOfKeys.leftestRoot.getKey() != 80) {System.out.println("problem leftest!!!");}
        if (heapFromListOfKeys.rightestRoot.getKey() != 67) {System.out.println("problem rightest!!!");}
        if (heapFromListOfKeys.numberOfTrees != 4) {System.out.println("problem numberOfTrees!!!");}
        if (heapFromListOfKeys.size() != 14) {System.out.println("problem size!!!");}
        counter = heapFromListOfKeys.countersRep();
        if (counter.length != 4) {System.out.println("problem with len!!!");}
        for(int i=0; i<4; i++){
            if (i == 0 && counter[i] != 2){System.out.print("problem with counter 1!!!");}
            if (i == 1 && counter[i] != 0){System.out.print("problem with counter 1!!!");}
            if (i != 1 && i != 0 && counter[i] != 1){System.out.println("problem with counter 0,2,3 !!!");}
        }

        ////////////////////// LARGE HEAP + ONLY ROOT //////////////////////

        heapFromListOfKeys = new FibonacciHeap();
        for (int num : listOfKeys) {
            heapFromListOfKeys.insert(num);
        }
        onlyRoot1 = new FibonacciHeap();
        onlyRoot1.insert(1);
        heapFromListOfKeys.deleteMin();

        heapFromListOfKeys.meld(onlyRoot1);

        if (heapFromListOfKeys.min.getKey() != 1) {System.out.println("problem min!!!");}
        if (heapFromListOfKeys.numOfMarked != 0) {System.out.println("problem marked!!!");}
        if (FibonacciHeap.totalCuts() != 0 ) {System.out.println("problem cuts!!!");}
        if (FibonacciHeap.totalLinks() != 40 ) {System.out.println("problem links!!!");}
        if (heapFromListOfKeys.leftestRoot.getKey() != 80) {System.out.println("problem leftest!!!");}
        if (heapFromListOfKeys.rightestRoot.getKey() != 1) {System.out.println("problem rightest!!!");}
        if (heapFromListOfKeys.numberOfTrees != 4) {System.out.println("problem numberOfTrees!!!");}
        if (heapFromListOfKeys.size() != 14) {System.out.println("problem size!!!");}
        counter = heapFromListOfKeys.countersRep();
        if (counter.length != 4) {System.out.println("problem with len!!!");}
        for(int i=0; i<4; i++){
            if (i == 0 && counter[i] != 2){System.out.print("problem with counter 1!!!");}
            if (i == 1 && counter[i] != 0){System.out.print("problem with counter 1!!!");}
            if (i != 1 && i != 0 && counter[i] != 1){System.out.println("problem with counter 0,2,3 !!!");}
        }

        ////////////////////// ONLY 2 ROOTS + LARGE HEAP //////////////////////

        only2roots = new FibonacciHeap();
        only2roots.insert(67);
        only2roots.insert(20);
        int [] listOfKeys2 = {1,2,5,90,77,8,1000,80,6,4,10,190,7,3};
        heapFromListOfKeys = new FibonacciHeap();
        for (int num : listOfKeys2) {
            heapFromListOfKeys.insert(num);
        }
        heapFromListOfKeys.deleteMin();

        if (heapFromListOfKeys.min.getKey() != 2) {System.out.println("problem min=2!!!");}
        if (heapFromListOfKeys.numOfMarked != 0) {System.out.println("problem marked!!!");}
        if (FibonacciHeap.totalCuts() != 0 ) {System.out.println("problem cuts!!!");}
        if (FibonacciHeap.totalLinks() != 50 ) {System.out.println("problem links!!!");}
        if (heapFromListOfKeys.leftestRoot.getKey() != 2) {System.out.println("problem leftest!!!");}
        if (heapFromListOfKeys.rightestRoot.getKey() != 3) {System.out.println("problem rightest!!!");}
        if (heapFromListOfKeys.numberOfTrees != 3) {System.out.println("problem numberOfTrees!!!");}
        if (heapFromListOfKeys.size() != 13) {System.out.println("problem size!!!");}

        only2roots.meld(heapFromListOfKeys);

        if (only2roots.min.getKey() != 2) {System.out.println("problem min!!!");}
        if (only2roots.numOfMarked != 0) {System.out.println("problem marked!!!");}
        if (FibonacciHeap.totalCuts() != 0 ) {System.out.println("problem cuts!!!");}
        if (FibonacciHeap.totalLinks() != 50 ) {System.out.println("problem links!!!");}
        if (only2roots.leftestRoot.getKey() != 20) {System.out.println("problem leftest!!!");}
        if (only2roots.rightestRoot.getKey() != 3) {System.out.println("problem rightest!!!");}
        if (only2roots.numberOfTrees != 5) {System.out.println("problem numberOfTrees!!!");}
        if (only2roots.size() != 15) {System.out.println("problem size!!!");}
        counter = only2roots.countersRep();
        if (counter.length != 4) {System.out.println("problem with len!!!");}
        for(int i=0; i<4; i++){
            if (i == 0 && counter[i] != 3){System.out.println("problem with counter 1!!!");}
            if (i == 1 && counter[i] != 0){System.out.println("problem with counter 1!!!");}
            if (i != 1 && i != 0 && counter[i] != 1){System.out.println("problem with counter 0,2,3 !!!");}
        }

        ////////////////////// ONLY 2 ROOTS + LARGE HEAP //////////////////////

        only2roots = new FibonacciHeap();
        only2roots.insert(0);
        only2roots.insert(20);
        //int [] listOfKeys2 = {1,2,5,90,77,8,1000,80,6,4,10,190,7,3};
        heapFromListOfKeys = new FibonacciHeap();
        for (int num : listOfKeys2) {
            heapFromListOfKeys.insert(num);
        }
        heapFromListOfKeys.deleteMin();

        only2roots.meld(heapFromListOfKeys);

        if (only2roots.min.getKey() != 0) {System.out.println("problem min!!!");}
        if (only2roots.numOfMarked != 0) {System.out.println("problem marked!!!");}
        if (FibonacciHeap.totalCuts() != 0 ) {System.out.println("problem cuts!!!");}
        if (FibonacciHeap.totalLinks() != 60 ) {System.out.println("problem links!!!");}
        if (only2roots.leftestRoot.getKey() != 20) {System.out.println("problem leftest!!!");}
        if (only2roots.rightestRoot.getKey() != 3) {System.out.println("problem rightest!!!");}
        if (only2roots.numberOfTrees != 5) {System.out.println("problem numberOfTrees!!!");}
        if (only2roots.size() != 15) {System.out.println("problem size!!!");}
        counter = only2roots.countersRep();
        if (counter.length != 4) {System.out.println("problem with len!!!");}
        for(int i=0; i<4; i++){
            if (i == 0 && counter[i] != 3){System.out.println("problem with counter 1!!!");}
            if (i == 1 && counter[i] != 0){System.out.println("problem with counter 1!!!");}
            if (i != 1 && i != 0 && counter[i] != 1){System.out.println("problem with counter 0,2,3 !!!");}
        }

        ////////////////////// LARGE HEAP + ONLY 2 ROOTS //////////////////////

        //int [] listOfKeys2 = {1,2,5,90,77,8,1000,80,6,4,10,190,7,3};
        heapFromListOfKeys = new FibonacciHeap();
        for (int num : listOfKeys2) {
            heapFromListOfKeys.insert(num);
        }
        heapFromListOfKeys.deleteMin();

        only2roots = new FibonacciHeap();
        only2roots.insert(67);
        only2roots.insert(20);

        heapFromListOfKeys.meld(only2roots);

        if (heapFromListOfKeys.min.getKey() != 2) {System.out.println("problem min!!!");}
        if (heapFromListOfKeys.numOfMarked != 0) {System.out.println("problem marked!!!");}
        if (FibonacciHeap.totalCuts() != 0 ) {System.out.println("problem cuts!!!");}
        if (FibonacciHeap.totalLinks() != 70 ) {System.out.println("problem links!!!");}
        if (heapFromListOfKeys.leftestRoot.getKey() != 2) {System.out.println("problem leftest!!!");}
        if (heapFromListOfKeys.rightestRoot.getKey() != 67) {System.out.println("problem rightest!!!");}
        if (heapFromListOfKeys.numberOfTrees != 5) {System.out.println("problem numberOfTrees!!!");}
        if (heapFromListOfKeys.size() != 15) {System.out.println("problem size!!!");}
        counter = heapFromListOfKeys.countersRep();
        if (counter.length != 4) {System.out.println("problem with len!!!");}
        for(int i=0; i<4; i++){
            if (i == 0 && counter[i] != 3){System.out.println("problem with counter 1!!!");}
            if (i == 1 && counter[i] != 0){System.out.println("problem with counter 1!!!");}
            if (i != 1 && i != 0 && counter[i] != 1){System.out.println("problem with counter 0,2,3 !!!");}
        }

        ////////////////////// LARGE HEAP + ONLY 2 ROOTS //////////////////////

        //int [] listOfKeys2 = {1,2,5,90,77,8,1000,80,6,4,10,190,7,3};
        heapFromListOfKeys = new FibonacciHeap();
        for (int num : listOfKeys2) {
            heapFromListOfKeys.insert(num);
        }
        heapFromListOfKeys.deleteMin();

        only2roots = new FibonacciHeap();
        only2roots.insert(67);
        only2roots.insert(0);

        heapFromListOfKeys.meld(only2roots);

        if (heapFromListOfKeys.min.getKey() != 0) {System.out.println("problem min!!!");}
        if (heapFromListOfKeys.numOfMarked != 0) {System.out.println("problem marked!!!");}
        if (FibonacciHeap.totalCuts() != 0 ) {System.out.println("problem cuts!!!");}
        if (FibonacciHeap.totalLinks() != 80 ) {System.out.println("problem links!!!");}
        if (heapFromListOfKeys.leftestRoot.getKey() != 2) {System.out.println("problem leftest!!!");}
        if (heapFromListOfKeys.rightestRoot.getKey() != 67) {System.out.println("problem rightest!!!");}
        if (heapFromListOfKeys.numberOfTrees != 5) {System.out.println("problem numberOfTrees!!!");}
        if (heapFromListOfKeys.size() != 15) {System.out.println("problem size!!!");}
        counter = heapFromListOfKeys.countersRep();
        if (counter.length != 4) {System.out.println("problem with len!!!");}
        for(int i=0; i<4; i++){
            if (i == 0 && counter[i] != 3){System.out.println("problem with counter 1!!!");}
            if (i == 1 && counter[i] != 0){System.out.println("problem with counter 1!!!");}
            if (i != 1 && i != 0 && counter[i] != 1){System.out.println("problem with counter 0,2,3 !!!");}
        }

        ////////////////////// ONLY 3 ROOTS + LARGE HEAP //////////////////////

        only3roots = new FibonacciHeap();
        only3roots.insert(67);
        only3roots.insert(20);
        only3roots.insert(70 );
        // listOfKeys2 = {1,2,5,90,77,8,1000,80,6,4,10,190,7,3};
        heapFromListOfKeys = new FibonacciHeap();
        for (int num : listOfKeys2) {
            heapFromListOfKeys.insert(num);
        }
        heapFromListOfKeys.deleteMin();

        only3roots.meld(heapFromListOfKeys);

        if (only3roots.min.getKey() != 2) {System.out.println("problem min!!!");}
        if (only3roots.numOfMarked != 0) {System.out.println("problem marked!!!");}
        if (FibonacciHeap.totalCuts() != 0 ) {System.out.println("problem cuts!!!");}
        if (FibonacciHeap.totalLinks() != 90 ) {System.out.println("problem links!!!");}
        if (only3roots.leftestRoot.getKey() != 70) {System.out.println("problem leftest!!!");}
        if (only3roots.rightestRoot.getKey() != 3) {System.out.println("problem rightest!!!");}
        if (only3roots.numberOfTrees != 6) {System.out.println("problem numberOfTrees!!!");}
        if (only3roots.size() != 16) {System.out.println("problem size!!!");}
        counter = only3roots.countersRep();
        if (counter.length != 4) {System.out.println("problem with len!!!");}
        for(int i=0; i<4; i++){
            if (i == 0 && counter[i] != 4){System.out.println("problem with counter 1!!!");}
            if (i == 1 && counter[i] != 0){System.out.println("problem with counter 1!!!");}
            if (i != 1 && i != 0 && counter[i] != 1){System.out.println("problem with counter 0,2,3 !!!");}
        }

        ////////////////////// ONLY 3 ROOTS + LARGE HEAP //////////////////////

        only3roots = new FibonacciHeap();
        only3roots.insert(1);
        only3roots.insert(20);
        only3roots.insert(70 );
        // listOfKeys2 = {1,2,5,90,77,8,1000,80,6,4,10,190,7,3};
        heapFromListOfKeys = new FibonacciHeap();
        for (int num : listOfKeys2) {
            heapFromListOfKeys.insert(num);
        }
        heapFromListOfKeys.deleteMin();

        only3roots.meld(heapFromListOfKeys);

        if (only3roots.min.getKey() != 1) {System.out.println("problem min!!!");}
        if (only3roots.numOfMarked != 0) {System.out.println("problem marked!!!");}
        if (FibonacciHeap.totalCuts() != 0 ) {System.out.println("problem cuts!!!");}
        if (FibonacciHeap.totalLinks() != 100 ) {System.out.println("problem links!!!");}
        if (only3roots.leftestRoot.getKey() != 70) {System.out.println("problem leftest!!!");}
        if (only3roots.rightestRoot.getKey() != 3) {System.out.println("problem rightest!!!");}
        if (only3roots.numberOfTrees != 6) {System.out.println("problem numberOfTrees!!!");}
        if (only3roots.size() != 16) {System.out.println("problem size!!!");}
        counter = only3roots.countersRep();
        if (counter.length != 4) {System.out.println("problem with len!!!");}
        for(int i=0; i<4; i++){
            if (i == 0 && counter[i] != 4){System.out.println("problem with counter 1!!!");}
            if (i == 1 && counter[i] != 0){System.out.println("problem with counter 1!!!");}
            if (i != 1 && i != 0 && counter[i] != 1){System.out.println("problem with counter 0,2,3 !!!");}
        }

        ////////////////////// LARGE HEAP + ONLY 3 ROOTS //////////////////////

        // listOfKeys2 = {1,2,5,90,77,8,1000,80,6,4,10,190,7,3};
        heapFromListOfKeys = new FibonacciHeap();
        for (int num : listOfKeys2) {
            heapFromListOfKeys.insert(num);
        }
        heapFromListOfKeys.deleteMin();

        only3roots = new FibonacciHeap();
        only3roots.insert(1);
        only3roots.insert(20);
        only3roots.insert(70 );

        heapFromListOfKeys.meld(only3roots);

        if (heapFromListOfKeys.min.getKey() != 1) {System.out.println("problem min!!!");}
        if (heapFromListOfKeys.numOfMarked != 0) {System.out.println("problem marked!!!");}
        if (FibonacciHeap.totalCuts() != 0 ) {System.out.println("problem cuts!!!");}
        if (FibonacciHeap.totalLinks() != 110 ) {System.out.println("problem links!!!");}
        if (heapFromListOfKeys.leftestRoot.getKey() != 2) {System.out.println("problem leftest!!!");}
        if (heapFromListOfKeys.rightestRoot.getKey() != 1) {System.out.println("problem rightest!!!");}
        if (heapFromListOfKeys.numberOfTrees != 6) {System.out.println("problem numberOfTrees!!!");}
        if (heapFromListOfKeys.size() != 16) {System.out.println("problem size!!!");}
        counter = heapFromListOfKeys.countersRep();
        if (counter.length != 4) {System.out.println("problem with len!!!");}
        for(int i=0; i<4; i++){
            if (i == 0 && counter[i] != 4){System.out.println("problem with counter 1!!!");}
            if (i == 1 && counter[i] != 0){System.out.println("problem with counter 1!!!");}
            if (i != 1 && i != 0 && counter[i] != 1){System.out.println("problem with counter 0,2,3 !!!");}
        }

        ////////////////////// LARGE HEAP + ONLY 3 ROOTS //////////////////////

        // listOfKeys2 = {1,2,5,90,77,8,1000,80,6,4,10,190,7,3};
        heapFromListOfKeys = new FibonacciHeap();
        for (int num : listOfKeys2) {
            heapFromListOfKeys.insert(num);
        }
        heapFromListOfKeys.deleteMin();

        only3roots = new FibonacciHeap();
        only3roots.insert(88);
        only3roots.insert(20);
        only3roots.insert(70 );

        heapFromListOfKeys.meld(only3roots);

        if (heapFromListOfKeys.min.getKey() != 2) {System.out.println("problem min!!!");}
        if (heapFromListOfKeys.numOfMarked != 0) {System.out.println("problem marked!!!");}
        if (FibonacciHeap.totalCuts() != 0 ) {System.out.println("problem cuts!!!");}
        if (FibonacciHeap.totalLinks() != 120 ) {System.out.println("problem links!!!");}
        if (heapFromListOfKeys.leftestRoot.getKey() != 2) {System.out.println("problem leftest!!!");}
        if (heapFromListOfKeys.rightestRoot.getKey() != 88) {System.out.println("problem rightest!!!");}
        if (heapFromListOfKeys.numberOfTrees != 6) {System.out.println("problem numberOfTrees!!!");}
        if (heapFromListOfKeys.size() != 16) {System.out.println("problem size!!!");}
        counter = heapFromListOfKeys.countersRep();
        if (counter.length != 4) {System.out.println("problem with len!!!");}
        for(int i=0; i<4; i++){
            if (i == 0 && counter[i] != 4){System.out.println("problem with counter 1!!!");}
            if (i == 1 && counter[i] != 0){System.out.println("problem with counter 1!!!");}
            if (i != 1 && i != 0 && counter[i] != 1){System.out.println("problem with counter 0,2,3 !!!");}
        }

        ////////////////////// LARGE HEAP + LARGE HEAP //////////////////////

        int[] listOfKeys3 = {20,45,99,72,51,120,37,0,60,44,10,190,700,33,222};
        // listOfKeys2 = {1,2,5,90,77,8,1000,80,6,4,10,190,7,3};
        FibonacciHeap heapFromList2OfKeys = new FibonacciHeap();
        for (int num : listOfKeys2) {
            heapFromList2OfKeys.insert(num);
        }

        heapFromList2OfKeys.deleteMin();

        FibonacciHeap heapFromList3OfKeys = new FibonacciHeap();
        for (int num : listOfKeys3) {
            heapFromList3OfKeys.insert(num);
        }
        heapFromList3OfKeys.deleteMin();

        heapFromList3OfKeys.meld(heapFromList2OfKeys);

        if (heapFromList3OfKeys.min.getKey() != 2) {System.out.println("problem min!!!");}
        if (heapFromList3OfKeys.numOfMarked != 0) {System.out.println("problem marked!!!");}
        if (FibonacciHeap.totalCuts() != 0 ) {System.out.println("problem cuts!!!");}
        if (FibonacciHeap.totalLinks() != 141 ) {System.out.println("problem links!!!");}
        if (heapFromList3OfKeys.leftestRoot.getKey() != 20) {System.out.println("problem leftest!!!");}
        if (heapFromList3OfKeys.rightestRoot.getKey() != 3) {System.out.println("problem rightest!!!");}
        if (heapFromList3OfKeys.numberOfTrees != 6) {System.out.println("problem numberOfTrees!!!");}
        if (heapFromList3OfKeys.size() != 14+13) {System.out.println("problem size!!!");}
        counter = heapFromList3OfKeys.countersRep();
        if (counter.length != 4) {System.out.println("problem with len!!!");}
        for(int i=0; i<4; i++){
            if (i == 0 && counter[i] != 1){System.out.println("problem with counter 1!!!");}
            if (i == 1 && counter[i] != 1){System.out.println("problem with counter 1!!!");}
            if (i != 1 && i != 0 && counter[i] != 2){System.out.println("problem with counter 2,3 !!!");}
        }

        ////////////////////// LARGE HEAP + LARGE HEAP //////////////////////

        //listOfKeys3 = {20,45,99,72,51,120,37,0,60,44,10,190,700,33,222};
        // listOfKeys2 = {1,2,5,90,77,8,1000,80,6,4,10,190,7,3};
        heapFromList2OfKeys = new FibonacciHeap();
        for (int num : listOfKeys2) {
            heapFromList2OfKeys.insert(num);
        }

        heapFromList2OfKeys.deleteMin();

        heapFromList3OfKeys = new FibonacciHeap();
        for (int num : listOfKeys3) {
            heapFromList3OfKeys.insert(num);
        }
        heapFromList3OfKeys.deleteMin();

        heapFromList2OfKeys.meld(heapFromList3OfKeys);

        if (heapFromList2OfKeys.min.getKey() != 2) {System.out.println("problem min!!!");}
        if (heapFromList2OfKeys.numOfMarked != 0) {System.out.println("problem marked!!!");}
        if (FibonacciHeap.totalCuts() != 0 ) {System.out.println("problem cuts!!!");}
        if (FibonacciHeap.totalLinks() != 162 ) {System.out.println("problem links!!!");}
        if (heapFromList2OfKeys.leftestRoot.getKey() != 2) {System.out.println("problem leftest!!!");}
        if (heapFromList2OfKeys.rightestRoot.getKey() != 10) {System.out.println("problem rightest!!!");}
        if (heapFromList2OfKeys.numberOfTrees != 6) {System.out.println("problem numberOfTrees!!!");}
        if (heapFromList2OfKeys.size() != 14+13) {System.out.println("problem size!!!");}
        counter = heapFromList2OfKeys.countersRep();
        if (counter.length != 4) {System.out.println("problem with len!!!");}
        for(int i=0; i<4; i++){
            if (i == 0 && counter[i] != 1){System.out.println("problem with counter 1!!!");}
            if (i == 1 && counter[i] != 1){System.out.println("problem with counter 1!!!");}
            if (i != 1 && i != 0 && counter[i] != 2){System.out.println("problem with counter 2,3 !!!");}
        }

    }

    public static void testDelete(){
        System.out.println("minIsTheOldest:");
        FibonacciHeap minIsTheOldest = new FibonacciHeap();
        for (int i=1; i < 11; i++){
            minIsTheOldest.insert(5*i);
        }
        int[] counter = minIsTheOldest.countersRep();
        if (counter.length != 1) {System.out.println("problem with len!!!");}
        if (counter[0] != 10) {System.out.println("problem with i = 0 !!!");}


        FibonacciHeap.HeapNode x = minIsTheOldest.leftestRoot.getNext().getNext();

        if(x.getKey() != 40){System.out.println("problem with x=40!!!");}

        minIsTheOldest.delete(x);
        if (minIsTheOldest.min.getKey() != 5) {System.out.println("problem min!!!");}
        if (minIsTheOldest.numOfMarked != 0) {System.out.println("problem marked!!!");}
        if (FibonacciHeap.totalCuts() != 0 ) {System.out.println("problem cuts!!!");}
        if (FibonacciHeap.totalLinks() != 7 ) {System.out.println("problem links!!!");}
        if (minIsTheOldest.leftestRoot.getKey() != 5) {System.out.println("problem leftest!!!");}
        if (minIsTheOldest.rightestRoot.getKey() != 10) {System.out.println("problem rightest!!!");}
        if (minIsTheOldest.numberOfTrees != 2) {System.out.println("problem numberOfTrees!!!");}
        if (minIsTheOldest.size() != 9) {System.out.println("problem numberOfTrees!!!");}
        counter = minIsTheOldest.countersRep();
        if (counter.length != 4) {System.out.println("problem with len!!!");}
        for (int i=0; i<4; i++){
            if(i == 0){
                if(counter[i] != 1){
                System.out.println("problem with i = 0 !!!");
                }
                continue;}
            if(i == 3){
                if(counter[i] != 1){
                    System.out.println("problem with i = 3 !!!");
                    }}
            else {if(counter[i] != 0){System.out.print("problem with ");
                System.out.print(i);
                System.out.println("!!!");}}
        }

        x = minIsTheOldest.rightestRoot.getChild();

        if(x.getKey() != 30){System.out.println("problem with x=30!!!");}

        minIsTheOldest.delete(x);

        if (minIsTheOldest.min.getKey() != 5) {System.out.println("problem min!!!");}
        if (minIsTheOldest.numOfMarked != 0) {System.out.println("problem marked!!!");}
        if (FibonacciHeap.totalCuts() != 1 ) {System.out.println("problem cuts!!!");}
        if (FibonacciHeap.totalLinks() != 10 ) {System.out.println("problem links!!!");}
        if (minIsTheOldest.leftestRoot.getKey() != 5) {System.out.println("problem leftest!!!");}
        if (minIsTheOldest.rightestRoot.getKey() != 5) {System.out.println("problem rightest!!!");}
        if (minIsTheOldest.numberOfTrees != 1) {System.out.println("problem numberOfTrees!!!");}
        if (minIsTheOldest.size() != 8) {System.out.println("problem numberOfTrees!!!");}
        counter = minIsTheOldest.countersRep();
        if (counter.length != 4) {System.out.println("problem with len!!!");}
        for (int i=0; i<4; i++){
            if(i == 3){
                if(counter[i] != 1){
                    System.out.println("problem with i = 3 !!!");
                    }}
            else {
                if(counter[i] != 0){
                    System.out.print("problem with ");
                System.out.print(i);
                System.out.println("!!!");}}
        }

        x = minIsTheOldest.rightestRoot.getChild().getChild();

        if(x.getKey() != 20){System.out.println("problem with x=20!!!");}

        minIsTheOldest.delete(x);

        if (minIsTheOldest.min.getKey() != 5) {System.out.println("problem min!!!");}
        if (minIsTheOldest.numOfMarked != 1) {System.out.println("problem marked!!!");}
        if (FibonacciHeap.totalCuts() != 2 ) {System.out.println("problem cuts!!!");}
        if (FibonacciHeap.totalLinks() != 10 ) {System.out.println("problem links!!!");}
        if (minIsTheOldest.leftestRoot.getKey() != 25) {System.out.println("problem leftest!!!");}
        if (minIsTheOldest.rightestRoot.getKey() != 5) {System.out.println("problem rightest!!!");}
        if (minIsTheOldest.numberOfTrees != 2) {System.out.println("problem numberOfTrees!!!");}
        if (minIsTheOldest.size() != 7) {System.out.println("problem numberOfTrees!!!");}
        counter = minIsTheOldest.countersRep();
        if (counter.length != 4) {System.out.println("problem with len!!!");}
        for (int i=0; i<4; i++){
            if(i == 0){
                if(counter[i] != 1){
                    System.out.println("problem with i = 0 !!!");
                    }
                continue;}
            if(i == 3){
                if(counter[i] != 1){
                    System.out.println("problem with i = 3 !!!");
                    }}
            else {
                if(counter[i] != 0){System.out.print("problem with ");
                System.out.print(i);
                System.out.println("!!!");}}
        }

        x = minIsTheOldest.min.getChild().getChild();

        if(x.getKey() != 15){System.out.println("problem with x=15!!!");}

        minIsTheOldest.delete(x);

        if (minIsTheOldest.min.getKey() != 5) {System.out.println("problem min!!!");}
        if (minIsTheOldest.numOfMarked != 0) {System.out.println("problem marked!!!");}
        if (FibonacciHeap.totalCuts() != 4 ) {System.out.println("problem cuts!!!");}
        if (FibonacciHeap.totalLinks() != 11 ) {System.out.println("problem links!!!");}
        if (minIsTheOldest.leftestRoot.getKey() != 10) {System.out.println("problem leftest!!!");}
        if (minIsTheOldest.rightestRoot.getKey() != 5) {System.out.println("problem rightest!!!");}
        if (minIsTheOldest.numberOfTrees != 2) {System.out.println("problem numberOfTrees!!!");}
        if (minIsTheOldest.size() != 6) {System.out.println("problem numberOfTrees!!!");}
        counter = minIsTheOldest.countersRep();
        if (counter.length != 3) {System.out.println("problem with len!!!");}
        for (int i=0; i<3; i++){
            if(i == 1){
                if(counter[i] != 1){
                    System.out.println("problem with i = 0 !!!");
                    }
                continue;}
            if(i == 2){
                if(counter[i] != 1){
                    System.out.println("problem with i = 3 !!!");
                    }}
            else {if(counter[i] != 0){
                System.out.print("problem with ");
                System.out.print(i);
                System.out.println("!!!");}}

        }

        System.out.println("misIsTheNewest:");
        FibonacciHeap misIsTheNewest = new FibonacciHeap();
        for (int i=20; i>0; i--) {
            misIsTheNewest.insert(10*i);
        }
        counter = misIsTheNewest.countersRep();
        if (counter.length != 1) {System.out.println("problem with len!!!");}
        if (counter[0] != 20) {System.out.println("problem with i = 0 !!!");}

        int currentTotalCuts = FibonacciHeap.totalCuts();
        int currentTotalLinks = FibonacciHeap.totalLinks();

        x = misIsTheNewest.leftestRoot.getNext().getNext();

        //misIsTheNewest.display();

        if(x.getKey() != 30){System.out.println("problem with x=30!!!");}

        misIsTheNewest.delete(x);

        if (misIsTheNewest.min.getKey() != 10) {System.out.println("problem min!!!");}
        if (misIsTheNewest.numOfMarked != 0) {System.out.println("problem marked!!!");}
        if (FibonacciHeap.totalCuts() != currentTotalCuts) {System.out.println("problem cuts!!!");}
        if (FibonacciHeap.totalLinks() != currentTotalLinks+16) {System.out.println("problem links!!!");}
        if (misIsTheNewest.leftestRoot.getKey() != 200) {System.out.println("problem leftest!!! (30)");}
        if (misIsTheNewest.rightestRoot.getKey() != 10) {System.out.println("problem rightest!!! (30)");}
        if (misIsTheNewest.numberOfTrees != 3) {System.out.println("problem numberOfTrees!!!");}
        if (misIsTheNewest.size() != 19) {System.out.println("problem numberOfTrees!!!");}
        counter = misIsTheNewest.countersRep();
        if (counter.length != 5) {System.out.println("problem with len after deleting 30!!!");}
        for (int i=0; i<5; i++){
            if(i == 0){
                if(counter[i] != 1){
                    System.out.println("problem with i = 0 after deleting 30!!!");
                }
                continue;}
            if(i == 1){
                if(counter[i] != 1){
                    System.out.println("problem with i = 3 after deleting 30!!!");
                }
                continue;}
            if(i == 4){
                if(counter[i] != 1){
                    System.out.println("problem with i = 4 after deleting 30!!!");
                    }}
            else {if(counter[i] != 0){System.out.print("problem with ");
                System.out.print(i);
                System.out.println(" after deleting 30!!!");}}
        }

        x = misIsTheNewest.min.getChild().getChild().getChild();

        //misIsTheNewest.display();

        if(x.getKey() != 160){System.out.println("problem with x=160!!!");}

        misIsTheNewest.delete(x);

        if (misIsTheNewest.min.getKey() != 10) {System.out.println("problem min!!!");}
        if (misIsTheNewest.numOfMarked != 1) {System.out.println("problem marked!!!");}
        if (FibonacciHeap.totalCuts() != currentTotalCuts+1) {System.out.println("problem cuts!!! 160");}
        if (FibonacciHeap.totalLinks() != currentTotalLinks+18) {System.out.println("problem links!!!");}
        if (misIsTheNewest.leftestRoot.getKey() != 170) {System.out.println("problem leftest!!!");}
        if (misIsTheNewest.rightestRoot.getKey() != 10) {System.out.println("problem rightest!!!");}
        if (misIsTheNewest.numberOfTrees != 2) {System.out.println("problem numberOfTrees!!!");}
        if (misIsTheNewest.size() != 18) {System.out.println("problem numberOfTrees!!!");}
        counter = misIsTheNewest.countersRep();
        if (counter.length != 5) {System.out.println("problem with len after deleting 160!!!");}
        for (int i=0; i<5; i++){
            if(i == 2){
                if(counter[i] != 1){
                    System.out.println("problem with i = 2 after deleting 160!!!");
                }
                continue;}
            if(i == 4){
                if(counter[i] != 1){
                    System.out.println("problem with i = 4 after deleting 160!!!");
                }}
            else {if(counter[i] != 0){System.out.print("problem with ");
                System.out.print(i);
                System.out.println("after deleting 160!!!");}}
        }

        x = misIsTheNewest.leftestRoot.getNext().getChild().getChild().getPrev();

        //misIsTheNewest.display();

        if(x.getKey() != 110){System.out.println("problem with x=110!!!");}

        misIsTheNewest.delete(x);

        if (misIsTheNewest.min.getKey() != 10) {System.out.println("problem min!!!");}
        if (misIsTheNewest.numOfMarked != 2) {System.out.println("problem marked!!!");}
        if (FibonacciHeap.totalCuts() != currentTotalCuts+2) {System.out.println("problem cuts!!! 110");}
        if (FibonacciHeap.totalLinks() != currentTotalLinks+18) {System.out.println("problem links!!!");}
        if (misIsTheNewest.leftestRoot.getKey() != 170) {System.out.println("problem leftest!!!");}
        if (misIsTheNewest.rightestRoot.getKey() != 10) {System.out.println("problem rightest!!!");}
        if (misIsTheNewest.numberOfTrees != 2) {System.out.println("problem numberOfTrees!!!");}
        if (misIsTheNewest.size() != 17) {System.out.println("problem numberOfTrees!!!");}
        counter = misIsTheNewest.countersRep();
        if (counter.length != 5) {System.out.println("problem with len after deleting 110!!!");}
        for (int i=0; i<5; i++){
            if(i == 2){
                if(counter[i] != 1){
                    System.out.println("problem with i = 2 after deleting 110!!!");
                }
                continue;}
            if(i == 4){
                if(counter[i] != 1){
                    System.out.println("problem with i = 4 after deleting 110!!!");
                }}
            else {if(counter[i] != 0){System.out.print("problem with ");
                System.out.print(i);
                System.out.println("after deleting 110!!!");}}
        }

        x = misIsTheNewest.rightestRoot.getChild();

        //misIsTheNewest.display();

        if(x.getKey() != 100){System.out.println("problem with x=100!!!");}

        if (!x.getMark()) {System.out.println("problem with mark for 100!!!");}
        if (!x.getChild().getMark()) {System.out.println("problem with mark for 140!!!");}

        x = x.getChild().getChild();
        if(x.getKey() != 150){System.out.println("problem with x=150!!!");}
        if (x.getPrev().getKey() != 150 || x.getNext().getKey() != 150)
        {System.out.println("problem with x.left, x.right!!!");};

        misIsTheNewest.delete(x);

        if (misIsTheNewest.min.getKey() != 10) {System.out.println("problem min!!!");}
        if (misIsTheNewest.numOfMarked != 0) {System.out.println("problem marked!!!");}
        if (FibonacciHeap.totalCuts() != currentTotalCuts+5) {System.out.println("problem cuts!!! 150");}
        if (FibonacciHeap.totalLinks() != currentTotalLinks+18) {System.out.println("problem links!!!");}
        if (misIsTheNewest.leftestRoot.getKey() != 140) {System.out.println("problem leftest!!!");}
        if (misIsTheNewest.leftestRoot.getNext().getKey() != 100) {System.out.println("problem 100!!!");}
        if (misIsTheNewest.leftestRoot.getNext().getNext().getKey() != 170) {System.out.println("problem 170!!!");}
        if (misIsTheNewest.rightestRoot.getKey() != 10) {System.out.println("problem rightest!!!");}
        if (misIsTheNewest.numberOfTrees != 4) {System.out.println("problem numberOfTrees!!!");}
        if (misIsTheNewest.size() != 16) {System.out.println("problem numberOfTrees!!!");}
        counter = misIsTheNewest.countersRep();
        if (counter.length != 4) {System.out.println("problem with len after deleting 150!!!");}
        for (int i=0; i<4; i++){
            if(counter[i] != 1) {
                System.out.print("problem with ");
                System.out.print(i);
                System.out.println("after deleting 150!!!");
            }
        }
    }

    public static void testDecreaseKey(){
        System.out.println("minIsTheOldest:");
        FibonacciHeap minIsTheOldest = new FibonacciHeap();
        for (int i=1; i < 11; i++){
            minIsTheOldest.insert(5*i);
        }

        FibonacciHeap.HeapNode x = minIsTheOldest.leftestRoot;

        minIsTheOldest.decreaseKey(x, 44);
        if (minIsTheOldest.min.getKey() != 5) {System.out.println("problem min");}
        if (minIsTheOldest.numOfMarked != 0) {System.out.println("problem marked");}
        if (FibonacciHeap.totalCuts() != 0 ) {System.out.println("problem cuts");}
        if (FibonacciHeap.totalLinks() != 0 ) {System.out.println("problem links");}
        if (minIsTheOldest.leftestRoot.getKey() != 6) {System.out.println("problem leftest");}
        if (minIsTheOldest.rightestRoot.getKey() != 5) {System.out.println("problem rightest");}
        if (minIsTheOldest.numberOfTrees != 10) {System.out.println("problem numberOfTrees");}

        minIsTheOldest.decreaseKey(x,3);
        if (minIsTheOldest.min.getKey() != 3) {System.out.println("problem min");}
        if (minIsTheOldest.numOfMarked != 0) {System.out.println("problem marked");}
        if (FibonacciHeap.totalCuts() != 0 ) {System.out.println("problem cuts");}
        if (FibonacciHeap.totalLinks() != 0 ) {System.out.println("problem links");}
        if (minIsTheOldest.leftestRoot.getKey() != 3) {System.out.println("problem leftest");}
        if (minIsTheOldest.rightestRoot.getKey() != 5) {System.out.println("problem rightest");}
        if (minIsTheOldest.numberOfTrees != 10) {System.out.println("problem numberOfTrees");}

        minIsTheOldest.deleteMin();
        System.out.println("after delete min");

        if (minIsTheOldest.min.getKey() != 5) {System.out.println("problem min!!!");}
        if (minIsTheOldest.numOfMarked != 0) {System.out.println("problem marked!!!");}
        if (FibonacciHeap.totalCuts() != 0 ) {System.out.println("problem cuts!!!");}
        if (FibonacciHeap.totalLinks() != 7 ) {System.out.println("problem links!!!");}
        if (minIsTheOldest.leftestRoot.getKey() != 5) {System.out.println("problem leftest!!!");}
        if (minIsTheOldest.rightestRoot.getKey() != 10) {System.out.println("problem rightest!!!");}
        if (minIsTheOldest.numberOfTrees != 2) {System.out.println("problem numberOfTrees!!!");}

        x = minIsTheOldest.rightestRoot.getChild();
        if (x.getKey() != 30) {System.out.println("problem with leftest child!!!");}

        System.out.println("after delete min, decrease 30 to 16");

        minIsTheOldest.decreaseKey(x, 14);
        if (x.getKey() != 16) {System.out.println("problem with key!!!");}
        if (minIsTheOldest.min.getKey() != 5) {System.out.println("problem min!!!");}
        if (minIsTheOldest.numOfMarked != 0) {System.out.println("problem marked!!!");}
        if (FibonacciHeap.totalCuts() != 0 ) {System.out.println("problem cuts!!!");}
        if (FibonacciHeap.totalLinks() != 7 ) {System.out.println("problem links!!!");}
        if (minIsTheOldest.leftestRoot.getKey() != 5) {System.out.println("problem leftest!!!");}
        if (minIsTheOldest.rightestRoot.getKey() != 10) {System.out.println("problem rightest!!!");}
        if (minIsTheOldest.numberOfTrees != 2) {System.out.println("problem numberOfTrees!!!");}

        x = x.getChild();
        if (x.getKey() != 40) {System.out.println("problem with leftest.child.child!!!");}

        System.out.println("decrease 40 to 36");

        minIsTheOldest.decreaseKey(x, 4);
        if (x.getKey() != 36) {System.out.println("problem with key!!!");}
        if (minIsTheOldest.min.getKey() != 5) {System.out.println("problem min!!!");}
        if (minIsTheOldest.numOfMarked != 0) {System.out.println("problem marked!!!");}
        if (FibonacciHeap.totalCuts() != 0 ) {System.out.println("problem cuts!!!");}
        if (FibonacciHeap.totalLinks() != 7 ) {System.out.println("problem links!!!");}
        if (minIsTheOldest.leftestRoot.getKey() != 5) {System.out.println("problem leftest!!!");}
        if (minIsTheOldest.rightestRoot.getKey() != 10) {System.out.println("problem rightest!!!");}
        if (minIsTheOldest.numberOfTrees != 2) {System.out.println("problem numberOfTrees!!!");}

        System.out.println("decrease 36 to 6");
        FibonacciHeap.HeapNode parentX = x.getParent();
        if (parentX.getKey() != 16) {System.out.println("problem with parentX!!!");}

        minIsTheOldest.decreaseKey(x, 30);
        if (x.getKey() != 6) {System.out.println("problem with key!!!");}
        if (minIsTheOldest.min.getKey() != 5) {System.out.println("problem min!!!");}
        if (minIsTheOldest.numOfMarked != 1) {System.out.println("problem marked!!!");}
        if (!parentX.getMark()) {System.out.println("problem parenX.mark!!!");}
        if (FibonacciHeap.totalCuts() != 1 ) {System.out.println("problem cuts!!!");}
        if (FibonacciHeap.totalLinks() != 7 ) {System.out.println("problem links!!!");}
        if (minIsTheOldest.leftestRoot.getKey() != 6) {System.out.println("problem leftest!!!");}
        if (minIsTheOldest.rightestRoot.getKey() != 10) {System.out.println("problem rightest!!!");}
        if (minIsTheOldest.numberOfTrees != 3) {System.out.println("problem numberOfTrees!!!");}

        System.out.println("misIsTheNewest:");
        FibonacciHeap misIsTheNewest = new FibonacciHeap();
        for (int i=20; i>0; i--) {
            misIsTheNewest.insert(10*i);
        }

        FibonacciHeap.HeapNode y = misIsTheNewest.rightestRoot;
        System.out.println("decrease 200 to 15");
        misIsTheNewest.decreaseKey(y, 185);
        if (misIsTheNewest.min.getKey() != 10) {System.out.println("problem min!!!");}
        if (misIsTheNewest.numOfMarked != 0) {System.out.println("problem marked!!!");}
        if (FibonacciHeap.totalCuts() != 1 ) {System.out.println("problem cuts!!!");}
        if (FibonacciHeap.totalLinks() != 7 ) {System.out.println("problem links!!!");}
        if (misIsTheNewest.leftestRoot.getKey() != 10) {System.out.println("problem leftest!!!");}
        if (misIsTheNewest.rightestRoot.getKey() != 15) {System.out.println("problem rightest!!!");}
        if (misIsTheNewest.numberOfTrees != 20) {System.out.println("problem numberOfTrees!!!");}

        System.out.println("decrease 40 to 5");
        y = misIsTheNewest.leftestRoot;
        y = y.getNext().getNext().getNext();
        if (y.getKey() != 40) {System.out.println("problem 40!!!");}
        misIsTheNewest.decreaseKey(y, 35);
        if (y.getKey() != 5) {System.out.println("problem with 40 -> 5");}
        if (misIsTheNewest.min.getKey() != 5) {System.out.println("problem min!!!!");}

        misIsTheNewest.deleteMin();

        System.out.println("after delete min");

        if (misIsTheNewest.min.getKey() != 10) {System.out.println("problem min!!!");}
        if (misIsTheNewest.numOfMarked != 0) {System.out.println("problem marked!!!");}
        if (FibonacciHeap.totalCuts() != 1 ) {System.out.println("problem cuts!!!");}
        if (FibonacciHeap.totalLinks() != 7+16 ) {System.out.println("problem links!!!");}
        if (misIsTheNewest.leftestRoot.getKey() != 15) {System.out.println("problem leftest!!!");}
        if (misIsTheNewest.rightestRoot.getKey() != 10) {System.out.println("problem rightest!!!");}
        if (misIsTheNewest.numberOfTrees != 3) {System.out.println("problem numberOfTrees!!!");}

        y = misIsTheNewest.findMin().getChild().getChild().getChild().getNext();
        if(y.getKey()!= 150){System.out.println("problem with y!!!");}
        misIsTheNewest.decreaseKey(y,5);
        System.out.println("after decrease key");

        if (misIsTheNewest.min.getKey() != 10) {System.out.println("problem min!!!");}
        if (misIsTheNewest.numOfMarked != 0) {System.out.println("problem marked!!!");}
        if (FibonacciHeap.totalCuts() != 1 ) {System.out.println("problem cuts!!!");}
        if (FibonacciHeap.totalLinks() != 7+16 ) {System.out.println("problem links!!!");}
        if (misIsTheNewest.leftestRoot.getKey() != 15) {System.out.println("problem leftest!!!");}
        if (misIsTheNewest.rightestRoot.getKey() != 10) {System.out.println("problem rightest!!!");}
        if (misIsTheNewest.numberOfTrees != 3) {System.out.println("problem numberOfTrees!!!");}

        FibonacciHeap.HeapNode parentY = y.getParent();
        if (parentY.getKey() != 140){System.out.println("problem with parentY!!!");}
        misIsTheNewest.decreaseKey(y,10);
        if (misIsTheNewest.min.getKey() != 10) {System.out.println("problem min!!!");}
        if (misIsTheNewest.numOfMarked != 1) {System.out.println("problem marked!!!");}
        if (!parentY.getMark()){System.out.println("problem with mark for parentY!!!");}
        if (FibonacciHeap.totalCuts() != 1+1 ) {System.out.println("problem cuts!!!");}
        if (FibonacciHeap.totalLinks() != 7+16 ) {System.out.println("problem links!!!");}
        if (misIsTheNewest.leftestRoot.getKey() != 135) {System.out.println("problem leftest!!!");}
        if (misIsTheNewest.rightestRoot.getKey() != 10) {System.out.println("problem rightest!!!");}
        if (misIsTheNewest.numberOfTrees != 4) {System.out.println("problem numberOfTrees!!!");}

        System.out.println("second decrease for son of 140");

        y = misIsTheNewest.findMin().getChild().getChild().getChild();
        if(y.getKey()!= 160){System.out.println("problem with y!!!");}
        misIsTheNewest.decreaseKey(y,5);
        if (misIsTheNewest.min.getKey() != 10) {System.out.println("problem min!!!");}
        if (misIsTheNewest.numOfMarked != 1) {System.out.println("problem marked!!!");}
        if (FibonacciHeap.totalCuts() != 1+1 ) {System.out.println("problem cuts!!!");}
        if (FibonacciHeap.totalLinks() != 7+16 ) {System.out.println("problem links!!!");}
        if (misIsTheNewest.leftestRoot.getKey() != 135) {System.out.println("problem leftest!!!");}
        if (misIsTheNewest.rightestRoot.getKey() != 10) {System.out.println("problem rightest!!!");}
        if (misIsTheNewest.numberOfTrees != 4) {System.out.println("problem numberOfTrees!!!");}

        if (parentY.getKey() != 140){System.out.println("problem with parentY!!!");}
        FibonacciHeap.HeapNode grandpaY = parentY.getParent();
        misIsTheNewest.decreaseKey(y,30);
        if (misIsTheNewest.min.getKey() != 10) {System.out.println("problem min!!!");}
        if (misIsTheNewest.numOfMarked != 1) {System.out.println("problem marked!!!");}
        if (parentY.getMark()){System.out.println("problem with mark for parentY!!!");}
        if (!grandpaY.getMark()){System.out.println("problem with mark for grandpaY!!!");}
        if (FibonacciHeap.totalCuts() != 1+1+2 ) {System.out.println("problem cuts!!!");}
        if (FibonacciHeap.totalLinks() != 7+16 ) {System.out.println("problem links!!!");}
        if (misIsTheNewest.leftestRoot.getKey() != 140) {System.out.println("problem leftest!!!");}
        if (misIsTheNewest.rightestRoot.getKey() != 10) {System.out.println("problem rightest!!!");}
        if (misIsTheNewest.numberOfTrees != 6) {System.out.println("problem numberOfTrees!!!");}



        System.out.println("heapFromListOfKeys:");
        int [] listOfKeys = {6,2,5,1,3,4,10,8,7,9};
        FibonacciHeap heapFromListOfKeys = new FibonacciHeap();
        for (int num : listOfKeys) {
            heapFromListOfKeys.insert(5*num);
        }
    }

    public static void testInsert() {
        FibonacciHeap insertionCheck = new FibonacciHeap();
        if (insertionCheck.findMin() != null || insertionCheck.leftestRoot != null ||
        insertionCheck.rightestRoot != null || insertionCheck.size() != 0 || insertionCheck.numOfMarked != 0 ||
        insertionCheck.numberOfTrees != 0 || FibonacciHeap.totalLinks() != 0 || FibonacciHeap.totalCuts() != 0 ||
                !insertionCheck.isEmpty()){
            System.out.println("problem with empty list");
        }
        insertionCheck.insert(100);
        if (insertionCheck.findMin().getKey() != 100 || insertionCheck.leftestRoot.getKey() != 100 ||
                insertionCheck.rightestRoot.getKey() != 100 || insertionCheck.size() != 1 || insertionCheck.numOfMarked != 0 ||
                insertionCheck.numberOfTrees != 1 || FibonacciHeap.totalLinks() != 0 || FibonacciHeap.totalCuts() != 0 ||
                insertionCheck.isEmpty()){
            System.out.println("problem with 1 list");
        }
        insertionCheck.insert(50);
        if (insertionCheck.findMin().getKey() != 50 || insertionCheck.leftestRoot.getKey() != 50 ||
                insertionCheck.rightestRoot.getKey() != 100 || insertionCheck.size() != 2 || insertionCheck.numOfMarked != 0 ||
                insertionCheck.numberOfTrees != 2 || FibonacciHeap.totalLinks() != 0 || FibonacciHeap.totalCuts() != 0 ||
                insertionCheck.isEmpty()){
            System.out.println("problem with 2 list");
        }
        insertionCheck.insert(75);
        if (insertionCheck.findMin().getKey() != 50 || insertionCheck.leftestRoot.getKey() != 75 ||
                insertionCheck.rightestRoot.getKey() != 100 || insertionCheck.size() != 3 || insertionCheck.numOfMarked != 0 ||
                insertionCheck.numberOfTrees != 3 || FibonacciHeap.totalLinks() != 0 || FibonacciHeap.totalCuts() != 0 ||
                insertionCheck.isEmpty()){
            System.out.println("problem with 3 list");
        }
        FibonacciHeap.HeapNode next = insertionCheck.findMin();
        for (int i=0; i<insertionCheck.size(); i++) {
            System.out.print(" left: ");
            System.out.print(next.getPrev().getKey());
            System.out.print(" key: ");
            System.out.print(next.getKey());
            System.out.print(" right: ");
            System.out.println(next.getNext().getKey());
            if (next.getParent() != null || next.getChild() != null){
                System.out.println("parents or children problem");
            }
            next = next.getNext();
        }
    }

    public static void testDeleteMin(){
        FibonacciHeap empty = new FibonacciHeap();
        empty.deleteMin();
        System.out.println("minIsTheOldest:");
        FibonacciHeap minIsTheOldest = new FibonacciHeap();
        for (int i=1; i < 10; i++){
            minIsTheOldest.insert(i);
        }
        if (minIsTheOldest.findMin().getKey() != 1 || minIsTheOldest.leftestRoot.getKey() != 9 ||
                minIsTheOldest.rightestRoot.getKey() != 1 || minIsTheOldest.size() != 9 || minIsTheOldest.numOfMarked != 0 ||
                minIsTheOldest.numberOfTrees != 9 || FibonacciHeap.totalLinks() != 0 || FibonacciHeap.totalCuts() != 0 ||
                minIsTheOldest.isEmpty()){
            System.out.println("problem 1");
        }
        minIsTheOldest.deleteMin();
        if (minIsTheOldest.findMin().getKey() != 2 || minIsTheOldest.leftestRoot.getKey() != 2 ||
                minIsTheOldest.rightestRoot.getKey() != 2 || minIsTheOldest.size() != 8 || minIsTheOldest.numOfMarked != 0 ||
                minIsTheOldest.numberOfTrees != 1 || FibonacciHeap.totalLinks() != 7 || FibonacciHeap.totalCuts() != 0 ||
                minIsTheOldest.isEmpty()){
            System.out.println("problem 2");
        }
        minIsTheOldest.deleteMin();
        if (minIsTheOldest.findMin().getKey() != 3 || minIsTheOldest.leftestRoot.getKey() != 3 ||
                minIsTheOldest.rightestRoot.getKey() != 6 || minIsTheOldest.size() != 7 || minIsTheOldest.numOfMarked != 0 ||
                minIsTheOldest.numberOfTrees != 3 || FibonacciHeap.totalLinks() != 7 || FibonacciHeap.totalCuts() != 0 ||
                minIsTheOldest.isEmpty()){
            System.out.println("problem 3");
        }
        minIsTheOldest.deleteMin();
        if (minIsTheOldest.findMin().getKey() != 4 || minIsTheOldest.leftestRoot.getKey() != 4 ||
                minIsTheOldest.rightestRoot.getKey() != 6 || minIsTheOldest.size() != 6 || minIsTheOldest.numOfMarked != 0 ||
                minIsTheOldest.numberOfTrees != 2 || FibonacciHeap.totalLinks() != 7 || FibonacciHeap.totalCuts() != 0 ||
                minIsTheOldest.isEmpty()){
            System.out.println("problem 4");
        }
        minIsTheOldest.deleteMin();
        if (minIsTheOldest.findMin().getKey() != 5 || minIsTheOldest.leftestRoot.getKey() != 5 ||
                minIsTheOldest.rightestRoot.getKey() != 6 || minIsTheOldest.size() != 5 || minIsTheOldest.numOfMarked != 0 ||
                minIsTheOldest.numberOfTrees != 2 || FibonacciHeap.totalLinks() != 7 || FibonacciHeap.totalCuts() != 0 ||
                minIsTheOldest.isEmpty()){
            System.out.println("problem 5");
        }
        minIsTheOldest.deleteMin();
        if (minIsTheOldest.findMin().getKey() != 6 || minIsTheOldest.leftestRoot.getKey() != 6 ||
                minIsTheOldest.rightestRoot.getKey() != 6 || minIsTheOldest.size() != 4 || minIsTheOldest.numOfMarked != 0 ||
                minIsTheOldest.numberOfTrees != 1 || FibonacciHeap.totalLinks() != 7 || FibonacciHeap.totalCuts() != 0 ||
                minIsTheOldest.isEmpty()){
            System.out.println("problem 6");
        }
        minIsTheOldest.deleteMin();
        if (minIsTheOldest.findMin().getKey() != 7 || minIsTheOldest.leftestRoot.getKey() != 7 ||
                minIsTheOldest.rightestRoot.getKey() != 8 || minIsTheOldest.size() != 3 || minIsTheOldest.numOfMarked != 0 ||
                minIsTheOldest.numberOfTrees != 2 || FibonacciHeap.totalLinks() != 7 || FibonacciHeap.totalCuts() != 0 ||
                minIsTheOldest.isEmpty()){
            System.out.println("problem 7");
        }
        minIsTheOldest.deleteMin();
        if (minIsTheOldest.findMin().getKey() != 8 || minIsTheOldest.leftestRoot.getKey() != 8 ||
                minIsTheOldest.rightestRoot.getKey() != 8 || minIsTheOldest.size() != 2 || minIsTheOldest.numOfMarked != 0 ||
                minIsTheOldest.numberOfTrees != 1 || FibonacciHeap.totalLinks() != 7 || FibonacciHeap.totalCuts() != 0 ||
                minIsTheOldest.isEmpty()){
            System.out.println("problem 8");
        }
        int linkTillNow = FibonacciHeap.totalLinks();
        System.out.println("misIsTheNewest:");
        FibonacciHeap misIsTheNewest = new FibonacciHeap();
        for (int i=10; i>0; i--) {
            misIsTheNewest.insert(i);
        }
        if (misIsTheNewest.findMin().getKey() != 1 || misIsTheNewest.leftestRoot.getKey() != 1 ||
                misIsTheNewest.rightestRoot.getKey() != 10 || misIsTheNewest.size() != 10 || misIsTheNewest.numOfMarked != 0 ||
                misIsTheNewest.numberOfTrees != 10 || FibonacciHeap.totalLinks() != linkTillNow || FibonacciHeap.totalCuts() != 0 ||
                misIsTheNewest.isEmpty()){
            System.out.println("problem 1");
        }
        misIsTheNewest.deleteMin();
        if (misIsTheNewest.findMin().getKey() != 2 || misIsTheNewest.leftestRoot.getKey() != 10 ||
                misIsTheNewest.rightestRoot.getKey() != 2 || misIsTheNewest.size() != 9 || misIsTheNewest.numOfMarked != 0 ||
                misIsTheNewest.numberOfTrees != 2 || FibonacciHeap.totalLinks() != linkTillNow+7 || FibonacciHeap.totalCuts() != 0 ||
                misIsTheNewest.isEmpty()){
            System.out.println("problem 2");
        }
        misIsTheNewest.deleteMin();
        if (misIsTheNewest.findMin().getKey() != 3 || misIsTheNewest.leftestRoot.getKey() != 3 ||
                misIsTheNewest.rightestRoot.getKey() != 3 || misIsTheNewest.size() != 8 || misIsTheNewest.numOfMarked != 0 ||
                misIsTheNewest.numberOfTrees != 1 || FibonacciHeap.totalLinks() != 10+linkTillNow || FibonacciHeap.totalCuts() != 0 ||
                misIsTheNewest.isEmpty()){
            System.out.println("problem 3");
        }
        misIsTheNewest.deleteMin();
        if (misIsTheNewest.findMin().getKey() != 4 || misIsTheNewest.leftestRoot.getKey() != 10 ||
                misIsTheNewest.rightestRoot.getKey() != 6 || misIsTheNewest.size() != 7 || misIsTheNewest.numOfMarked != 0 ||
                misIsTheNewest.numberOfTrees != 3 || FibonacciHeap.totalLinks() != 10+linkTillNow || FibonacciHeap.totalCuts() != 0 ||
                misIsTheNewest.isEmpty()){
            System.out.println("problem 4");
        }
        misIsTheNewest.deleteMin();
        if (misIsTheNewest.findMin().getKey() != 5 || misIsTheNewest.leftestRoot.getKey() != 5 ||
                misIsTheNewest.rightestRoot.getKey() != 6 || misIsTheNewest.size() != 6 || misIsTheNewest.numOfMarked != 0 ||
                misIsTheNewest.numberOfTrees != 2 || FibonacciHeap.totalLinks() != 11+linkTillNow || FibonacciHeap.totalCuts() != 0 ||
                misIsTheNewest.isEmpty()){
            System.out.println("problem 5");
        }
        misIsTheNewest.deleteMin();
        if (misIsTheNewest.findMin().getKey() != 6 || misIsTheNewest.leftestRoot.getKey() != 10 ||
                misIsTheNewest.rightestRoot.getKey() != 6 || misIsTheNewest.size() != 5 || misIsTheNewest.numOfMarked != 0 ||
                misIsTheNewest.numberOfTrees != 2 || FibonacciHeap.totalLinks() != 11+linkTillNow || FibonacciHeap.totalCuts() != 0 ||
                misIsTheNewest.isEmpty()){
            System.out.println("problem 6");
        }
        misIsTheNewest.deleteMin();
        if (misIsTheNewest.findMin().getKey() != 7 || misIsTheNewest.leftestRoot.getKey() != 7 ||
                misIsTheNewest.rightestRoot.getKey() != 7 || misIsTheNewest.size() != 4 || misIsTheNewest.numOfMarked != 0 ||
                misIsTheNewest.numberOfTrees != 1 || FibonacciHeap.totalLinks() != 13+linkTillNow || FibonacciHeap.totalCuts() != 0 ||
                misIsTheNewest.isEmpty()){
            System.out.println("problem 7");
        }
        misIsTheNewest.deleteMin();
        if (misIsTheNewest.findMin().getKey() != 8 || misIsTheNewest.leftestRoot.getKey() != 10 ||
                misIsTheNewest.rightestRoot.getKey() != 8 || misIsTheNewest.size() != 3 || misIsTheNewest.numOfMarked != 0 ||
                misIsTheNewest.numberOfTrees != 2 || FibonacciHeap.totalLinks() != 13+linkTillNow || FibonacciHeap.totalCuts() != 0 ||
                misIsTheNewest.isEmpty()){
            System.out.println("problem 8");
        }
        misIsTheNewest.deleteMin();
        if (misIsTheNewest.findMin().getKey() != 9 || misIsTheNewest.leftestRoot.getKey() != 9 ||
                misIsTheNewest.rightestRoot.getKey() != 9 || misIsTheNewest.size() != 2 || misIsTheNewest.numOfMarked != 0 ||
                misIsTheNewest.numberOfTrees != 1 || FibonacciHeap.totalLinks() != 14+linkTillNow || FibonacciHeap.totalCuts() != 0 ||
                misIsTheNewest.isEmpty()){
            System.out.println("problem 9");
        }
        misIsTheNewest.deleteMin();
        if (misIsTheNewest.findMin().getKey() != 10 || misIsTheNewest.leftestRoot.getKey() != 10 ||
                misIsTheNewest.rightestRoot.getKey() != 10 || misIsTheNewest.size() != 1 || misIsTheNewest.numOfMarked != 0 ||
                misIsTheNewest.numberOfTrees != 1 || FibonacciHeap.totalLinks() != 14+linkTillNow || FibonacciHeap.totalCuts() != 0 ||
                misIsTheNewest.isEmpty()){
            System.out.println("problem 10");
        }
        misIsTheNewest.deleteMin();
        if (misIsTheNewest.findMin() != null || misIsTheNewest.leftestRoot != null ||
                misIsTheNewest.rightestRoot != null || misIsTheNewest.size() != 0 || misIsTheNewest.numOfMarked != 0 ||
                misIsTheNewest.numberOfTrees != 0 || FibonacciHeap.totalLinks() != 14+linkTillNow || FibonacciHeap.totalCuts() != 0 ||
                !misIsTheNewest.isEmpty()){
            System.out.println("problem 11");
        }
        System.out.println("heapFromListOfKeys:");
        int [] listOfKeys = {6,2,5,1,3,4,10,8,7,9};
        FibonacciHeap heapFromListOfKeys = new FibonacciHeap();
        for (int num : listOfKeys) {
            heapFromListOfKeys.insert(num);
        }
        linkTillNow = FibonacciHeap.totalLinks();
        if (heapFromListOfKeys.findMin().getKey() != 1 || heapFromListOfKeys.leftestRoot.getKey() != 9 ||
                heapFromListOfKeys.rightestRoot.getKey() != 6 || heapFromListOfKeys.size() != 10 || heapFromListOfKeys.numOfMarked != 0 ||
                heapFromListOfKeys.numberOfTrees != 10 || FibonacciHeap.totalLinks() != linkTillNow || FibonacciHeap.totalCuts() != 0 ||
                heapFromListOfKeys.isEmpty()){
            System.out.println("problem 1");
        }
        heapFromListOfKeys.deleteMin();
        if (heapFromListOfKeys.findMin().getKey() != 2 || heapFromListOfKeys.leftestRoot.getKey() != 6 ||
                heapFromListOfKeys.rightestRoot.getKey() != 2 || heapFromListOfKeys.size() != 9 || heapFromListOfKeys.numOfMarked != 0 ||
                heapFromListOfKeys.numberOfTrees != 2 || FibonacciHeap.totalLinks() != linkTillNow+7 || FibonacciHeap.totalCuts() != 0 ||
                heapFromListOfKeys.isEmpty()){
            System.out.println("problem 2");
        }
        heapFromListOfKeys.deleteMin();
        if (heapFromListOfKeys.findMin().getKey() != 3 || heapFromListOfKeys.leftestRoot.getKey() != 3 ||
                heapFromListOfKeys.rightestRoot.getKey() != 3 || heapFromListOfKeys.size() != 8 || heapFromListOfKeys.numOfMarked != 0 ||
                heapFromListOfKeys.numberOfTrees != 1 || FibonacciHeap.totalLinks() != linkTillNow+10 || FibonacciHeap.totalCuts() != 0 ||
                heapFromListOfKeys.isEmpty()){
            System.out.println("problem 3");
        }
        heapFromListOfKeys.deleteMin();
        if (heapFromListOfKeys.findMin().getKey() != 4 || heapFromListOfKeys.leftestRoot.getKey() != 4 ||
                heapFromListOfKeys.rightestRoot.getKey() != 7 || heapFromListOfKeys.size() != 7 || heapFromListOfKeys.numOfMarked != 0 ||
                heapFromListOfKeys.numberOfTrees != 3 || FibonacciHeap.totalLinks() != linkTillNow+10 || FibonacciHeap.totalCuts() != 0 ||
                heapFromListOfKeys.isEmpty()){
            System.out.println("problem 4");
        }
        heapFromListOfKeys.deleteMin();
        if (heapFromListOfKeys.findMin().getKey() != 5 || heapFromListOfKeys.leftestRoot.getKey() != 5 ||
                heapFromListOfKeys.rightestRoot.getKey() != 7 || heapFromListOfKeys.size() != 6 || heapFromListOfKeys.numOfMarked != 0 ||
                heapFromListOfKeys.numberOfTrees != 2 || FibonacciHeap.totalLinks() != linkTillNow+10 || FibonacciHeap.totalCuts() != 0 ||
                heapFromListOfKeys.isEmpty()){
            System.out.println("problem 5");
        }
        heapFromListOfKeys.deleteMin();
        if (heapFromListOfKeys.findMin().getKey() != 6 || heapFromListOfKeys.leftestRoot.getKey() != 6 ||
                heapFromListOfKeys.rightestRoot.getKey() != 7 || heapFromListOfKeys.size() != 5 || heapFromListOfKeys.numOfMarked != 0 ||
                heapFromListOfKeys.numberOfTrees != 2 || FibonacciHeap.totalLinks() != linkTillNow+10 || FibonacciHeap.totalCuts() != 0 ||
                heapFromListOfKeys.isEmpty()){
            System.out.println("problem 6");
        }
        heapFromListOfKeys.deleteMin();
        if (heapFromListOfKeys.findMin().getKey() != 7 || heapFromListOfKeys.leftestRoot.getKey() != 7 ||
                heapFromListOfKeys.rightestRoot.getKey() != 7 || heapFromListOfKeys.size() != 4 || heapFromListOfKeys.numOfMarked != 0 ||
                heapFromListOfKeys.numberOfTrees != 1 || FibonacciHeap.totalLinks() != linkTillNow+10 || FibonacciHeap.totalCuts() != 0 ||
                heapFromListOfKeys.isEmpty()){
            System.out.println("problem 7");
        }
        heapFromListOfKeys.deleteMin();
        if (heapFromListOfKeys.findMin().getKey() != 8 || heapFromListOfKeys.leftestRoot.getKey() != 9 ||
                heapFromListOfKeys.rightestRoot.getKey() != 8 || heapFromListOfKeys.size() != 3 || heapFromListOfKeys.numOfMarked != 0 ||
                heapFromListOfKeys.numberOfTrees != 2 || FibonacciHeap.totalLinks() != linkTillNow+10 || FibonacciHeap.totalCuts() != 0 ||
                heapFromListOfKeys.isEmpty()){
            System.out.println("problem 8");
        }
        heapFromListOfKeys.deleteMin();
        if (heapFromListOfKeys.findMin().getKey() != 9 || heapFromListOfKeys.leftestRoot.getKey() != 9 ||
                heapFromListOfKeys.rightestRoot.getKey() != 9 || heapFromListOfKeys.size() != 2 || heapFromListOfKeys.numOfMarked != 0 ||
                heapFromListOfKeys.numberOfTrees != 1 || FibonacciHeap.totalLinks() != linkTillNow+11 || FibonacciHeap.totalCuts() != 0 ||
                heapFromListOfKeys.isEmpty()){
            System.out.println("problem 9");
        }
        heapFromListOfKeys.deleteMin();
        if (heapFromListOfKeys.findMin().getKey() != 10 || heapFromListOfKeys.leftestRoot.getKey() != 10 ||
                heapFromListOfKeys.rightestRoot.getKey() != 10 || heapFromListOfKeys.size() != 1 || heapFromListOfKeys.numOfMarked != 0 ||
                heapFromListOfKeys.numberOfTrees != 1 || FibonacciHeap.totalLinks() != linkTillNow+11 || FibonacciHeap.totalCuts() != 0 ||
                heapFromListOfKeys.isEmpty()){
            System.out.println("problem 10");
        }
        heapFromListOfKeys.deleteMin();
        if (heapFromListOfKeys.findMin() != null || heapFromListOfKeys.leftestRoot != null ||
                heapFromListOfKeys.rightestRoot != null || heapFromListOfKeys.size() != 0 || heapFromListOfKeys.numOfMarked != 0 ||
                heapFromListOfKeys.numberOfTrees != 0 || FibonacciHeap.totalLinks() != linkTillNow+11 || FibonacciHeap.totalCuts() != 0 ||
                !heapFromListOfKeys.isEmpty()){
            System.out.println("problem 11");
        }
    }
}
