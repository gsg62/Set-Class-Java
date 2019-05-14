
package p01.set.pkgclass;

/**
 * @author ggear
 * <p>
 * Class for managing sets of integers, has capacity to generate various sets
 */
public class SetClass extends java.lang.Object{
    
    int arrayCapacity;
    int arraySize;
    int[] setArray;
    SetClass[] powerSetArray;
    
    public static final int DEFAULT_ARRAY_CAPACITY = 10;
    private static final int BASE_TWO = 2;
    public static final int EVEN = 103;
    public static final int ODD = 102;
    public static final int PRIME = 104;
    public static final int INCREMENTED = 101;
    
    /**
     * Default constructor
     * <p>
     * Initializes set array but sets power set array to null
     */
    public SetClass()
    {  
        arrayCapacity = DEFAULT_ARRAY_CAPACITY;
        powerSetArray = null;
        setArray = new int[arrayCapacity];
        arraySize = 0;
    }
    /**
     * Initialization constructor
     * <p>
     * Allows specification of set array capacity
     * <p>
     * Initializes set array but sets power set array to null
     * <p>
     * @param initialCapacity - integer that specifies array capacity
     */
    public SetClass( int initialCapacity )
    {
        powerSetArray = null;
        arrayCapacity = initialCapacity;
        setArray = new int[arrayCapacity];
        arraySize = 0;

    }
    /**
     * Copy constructor
     * <p>
     * Duplicates copied set class
     * <p>
     * Also responsible for correct initialization/update of set class array
     * <p>
     * @param copied - SetClass object to be copied 
     */
    public SetClass( SetClass copied )
    {
        // creates new empty setArray of same capacity
        this.arrayCapacity = copied.arrayCapacity;
        this.setArray = new int[copied.arrayCapacity]; 
        // sets the array size of duplicate equal to copied setArray
        this.arraySize = copied.arraySize;
        int index = 0;
        // adds all integers into duplicate setArray 
        while(index < this.arraySize) 
        {
            this.setArray[index] = copied.setArray[index];
            index++;
        }
    }
    /**
     * Local function tests for resize of the set array
     * <p>
     * If array needs to be resized, array capacity is doubled;
     * otherwise, no change
     * <p>
     * @return boolean report that resize was conducted
     */
    private boolean checkForResize()
    {
        // arraySize check
        if( arrayCapacity == arraySize ){
            arrayCapacity = 2*arrayCapacity;
            // saves values from last setArray and creates new larger setArray
            int[] copyValues = setArray;
            setArray = new int[arrayCapacity];    
            int index = 0;
            // adds all values into new array
            while( index < arraySize ) 
            {
                setArray[index] = copyValues[index];
                index++;
            }
            return true;
        }
        return false;
    }
    /**
     * Adds integer to set
     * <p>
     * increases capacity using checkForResize if array is full
     * <p>
     * does not allow duplicate values in set
     * <p>
     * @param item - integer value to be added to set 
     */
    public void addItem( int item )
    {
        checkForResize(); 
        // adds item if unique and increments array size
        if( !hasElement(item) )
        {
            this.setArray[arraySize] = item;
            this.arraySize += 1;
        }
    }
    /**
     * Removes value at given index; moves all succeeding data down in array
     * <p>
     * @param indexToRemove - integer index of element value to remove 
     */
    private void removeAtIndex( int indexToRemove )
    {
        // clears element from setArray
        setArray[indexToRemove] = 0;         
        // moves the rest of the items one space back in the array
        while( indexToRemove < arraySize - 1 )
        {
            setArray[indexToRemove] = setArray[indexToRemove + 1];
            indexToRemove++;
        }
        arraySize -= 1;
    }
    /**
     * Removes value if it is found in set
     * <p>
     * @param valToRemove - integer value to be removed
     * <p>
     * @return boolean result of operation success
     */
    public boolean removeValue( int valToRemove )
    {
        int index = 0;
        int saveIndex = 0;
        // checks whether or not element is in setArray
        if( hasElement(valToRemove) ) 
        {
            // if it is, this loop will save the index it is at
            while( index < arraySize ) 
            {
                if( setArray[index] == valToRemove)
                {
                    saveIndex = index;    
                }
                index++;
            }
            // then removes at index and returns true
            removeAtIndex( saveIndex );
            return true; 
        }
        return false;
    }
    
    /**
     * Tests to indicate if integer value is one of the set elements
     * <p>
     * @param testElement - integer element to be found in set
     * <p>
     * @return boolean result of test
     */
    public boolean hasElement( int testElement )
    {
        int index = 0;
        // loops through setArray to check for element
        while(index < this.arraySize) 
        {
            if( this.setArray[index] == testElement )
            {
                return true;    
            }
            index++;
        }
        return false;
    }
    /**
    * Provides list of set array elements as comma-delimited string
    * <p>
    * @overrides toString in class java.lang.Object 
    */
    public java.lang.String toString() 
    {
        String setArrayString = ""; 
        int index = 0;
        // adds each element from the setArray to a string with comma and spaces
        while( index < arraySize )
        {
            setArrayString += Integer.toString(this.setArray[index]);
            // checks to not add comma after last element
            if( index != (this.arraySize - 1) )
            {
                setArrayString += ", ";
            }
            index++;
        }
        return setArrayString;
    }
    /**
     * Returns the intersection of THIS set and the given other set
     * <p>
     * @param other - SetClass data with which intersection is found
     * <p>
     * @return SetClass object with intersection of two sets
     */
    public SetClass findIntersection( SetClass other )
    {
        SetClass intersectSet = new SetClass();       
        int index = 0;
        int intersectSetSize = 0;
        // loops through both arrays to find same values and add to new set
        while( index < this.arraySize )
        {
            if( other.hasElement(this.setArray[index]) )
            {
                intersectSet.addItem(this.setArray[index]);
            }
                        index++;
        }
        return intersectSet;
    }  
    /**
     * Returns the union of THIS set and the give other set
     * <p>
     * @param other - SetClass data with which union is found
     * <p>
     * @return SetClass object with union of two sets
     */
    public SetClass findUnion( SetClass other )
    {
        SetClass unionSet = new SetClass(other);
        int index = 0;
        // adds all values from one set to another
        while( index < this.arraySize )
        {
            unionSet.addItem(this.setArray[index]);
            index++;
        }
        return unionSet;
    }
    /**
     * Finds relative complement of THIS set in given other set
     * <p>
     * Returns other set having removed any items intersecting with THIS set
     * <p>
     * @param other - SetClass object from which THIS SetClass items 
     * will be removed
     * <p>
     * @return SetClass object with data as specified
     */
    public SetClass findRelativeComplementOfThisSetIn( SetClass other )
    {
        // finds and copies the intersecting set
        SetClass intersectingSet = new SetClass(this.findIntersection(other));
        SetClass relativeComplementSet = new SetClass(other);
        int index = 0;
        // removes all values that intersect
        while( index < intersectingSet.arraySize )
        {
            relativeComplementSet.removeValue(intersectingSet.setArray[index]);
            index++;
        }
        return relativeComplementSet;
    }
        
    /**
     * Tests to indicate if set is subclass another given set
     * <p>
     * @param other - SetClass object to be tested if THIS set is a subset of it
     * <p>
     * @return boolean result of test
     */
    public boolean isSubsetOf( SetClass other )
    {
        int index = 0;
        while( index < this.arraySize )
        {
            if( !other.hasElement(this.setArray[index]) )
            {
                return false;
            }
            index++;
        }
        return true;
    }
        
    /**
     * Tests for even, reports
     * <p>
     * @param value - integer value to be tested
     * <p>
     * @return boolean result as specified 
     */
    public boolean isEven( int value )
    {
        if( (value % 2) == 0 )
        {
            return true;
        }
        return false;
    }
    /**
     * Tests to indicate if given integer value is prime
     * <p>
     * @param testVal - integer value given
     * <p>
     * @return boolean result of test
     */
    private boolean isPrime( int testVal )
    {
        int primeCheck = 2;
        while( primeCheck < testVal ) 
        {
            if( testVal % primeCheck == 0 )
            {
                return false;
            }
            primeCheck++;
        }
        return true;
    }
    /**
     * Seeks and finds prime starting at given value
     * <p>
     * @param value - integer value to start search for prime
     * <p>
     * @return next prime number
     */
    public int getNextPrime( int value )
    {
        int nextPrime = value;
        // adds to next prime until next prime is found
        while(!isPrime(nextPrime) )
        {
            nextPrime++;
        }
        return nextPrime;
    }

        


    /**
     * Loads a number of specified integers to set
     * <p>
     * Characteristics may be odd, even, incremented, or prime
     * <p>
     * Parameter four is only used with INCREMENTED
     * <p>
     * @param StartVals
     * @param numItems
     * @param valueCharacteristics
     * @param incrementBy 
     */     
    void loadItems( int StartVals, int numItems, int valueCharacteristics, 
            int incrementBy )
    {
        // assumes startval will be even or odd based off valueCharacteristics
        if( (valueCharacteristics == EVEN) || (valueCharacteristics == ODD) )
        {
            int nextValue = StartVals;
            // adds first value to set
            this.addItem(StartVals);
            // adds remaining values to set
            while( (numItems - 1) > 0 )
            {
                // adds the next even integer according to increment
                nextValue += (2*incrementBy);
                this.addItem(nextValue);
                numItems -= 1;
            }
        }
        // assumes starting value will be prime
        else if( valueCharacteristics == PRIME )
        {
            // saves increment used in while loop
            int saveIncrement = incrementBy;
            int nextValue = StartVals;
            this.addItem(StartVals);
            // finds and adds remaining values to set
            while((numItems - 1) > 0)
            {
                incrementBy = saveIncrement;
                // finds the next prime integer according to increment
                while( incrementBy > 0 )
                {
                    nextValue = getNextPrime(nextValue);                      
                    incrementBy--;
                }
                // adds integer
                this.addItem(nextValue);
                numItems--;
            }
        }
        // adds values of INCREMENTED value characteristics
        else 
        {
            // adds and saves first value
            int nextValue = StartVals;
            this.addItem(StartVals);
            // adds remaining values
            while( (numItems - 1) > 0 )
            {
                nextValue += incrementBy;
                this.addItem(nextValue);
                numItems--;
            }
        }
    }
}
