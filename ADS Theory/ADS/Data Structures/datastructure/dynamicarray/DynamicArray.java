package datastructure.dynamicarray;

public class DynamicArray {

    int size;
    int capacity = 10;
    Object[] array;

    /**
     * Instantiates a new dynamic array of the set capacity
     * <br>
     * <br>
     * <b>Time complexity: O(1) - capacity is constant
     * <br>
     * Space complexity: O(1) - capacity is constant
     * <br></b>
     * <br>
     */
    public DynamicArray () {
        this.array = new Object[capacity];
    }

    /**
     * Instantiates a dynamic array of the given capacity from the user
     * <br>
     * <br>
     * <b>Time complexity: O(N)
     * <br>
     * Space complexity: O(N)
     * <br></b>
     * <br>
     *
     * @param capacity Starting size of array
     */
    public DynamicArray (int capacity) {
        this.capacity = capacity;
        this.array = new Object[capacity];
    }

    /**
     * Random access in the array.
     * <br>
     * <br>
     * <b>Time complexity: O(1)
     * <br>
     * Space complexity: O(1)
     * <br></b>
     * <br>
     *
     * @param index of the Object to retrieve
     * @return The object at the index
     */
    public Object get (int index) {
        return array[index];
    }


    /**
     * Adds an Object at the end of the array.
     * If the size of the array is greater than
     * the capacity of the array, call function 'grow'
     * <br>
     * Else, add the data at the first free spot
     * which is remembered by 'size' and increase the
     * current index of the last element in the array
     * <br>
     * <br>
     * <b>Time complexity: O(1)  - amortized, when grow is called -> O(N)
     * <br>
     * Space complexity: O(1)
     * <br></b>
     * <br>
     *
     * @param data Object to be added
     */
    public void add (Object data) {

        if (size >= capacity) {
            grow();
        }
        array[size] = data;
        size++;
    }

    /**
     * Inserts a given Object at a given index inside
     * the array. If the array is full, call 'grow'.
     * <br><br>
     * If not, shift everything from the index to the right
     * for one space and insert the element at the newly
     * freed index.
     * <br><br> <b>
     * Time complexity: O(N) - N = capacity
     * <br>
     * Space complexity: O(1)
     * <br></b>
     * <br>
     *
     * @param index position in array
     * @param data  data to be insert in array
     */
    public void insert (int index, Object data) {

        if (size >= capacity) {
            grow();
        }
        for (int i = size; i > index; i--){
            array[i] = array[i - 1];
        }
        array[index] = data;
        size++;
    }

    /**
     * Finds a specified data inside the array and
     * removes it, after which it shifts the remainder
     * to the left by one spot.
     * <br><br>
     * If the current size is one third of the capacity,
     * the array is shrunk to save memory
     * <br><br><b>
     * Time complexity: O(N) - N = capacity
     * <br>
     * Space complexity: O(1)
     * <br></b>
     * <br>
     *
     * @param data Object to be deleted
     */
    public void delete (Object data) {

        for (int i = 0; i < size; i++){
            if (array[i] == data) {
                for (int j = 0; j < (size - i - 1); j++){
                    array[i + j] = array[i + j + 1];
                }
                array[size - 1] = null;
                size--;
                if (size <= (int) (capacity / 3)) {
                    shrink();
                }
                break;
            }
        }
    }

    /**
     * Searches through the array and finds the specified
     * data. If found, returns the index, if not, returns -1
     * <br>
     * <br>
     * <b>Time complexity: O(N) - N = capacity
     * <br>
     * Space complexity: O(1)
     * <br></b>
     * <br>
     *
     * @param data Object to look for
     * @return index of the data
     */
    public int search (Object data) {

        for (int i = 0; i < size; i++){
            if (array[i] == data) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Whenever the array is filled -> size == capacity
     * this method is called. It creates a new array of the
     * double capacity of the previous one and copies the old
     * array in the new array.
     * <br>
     * <br>
     * <b>Time complexity: O(N)  - N is capacity ( copy the array )
     * <br>
     * Space complexity: O(N) - formal -> O(2N) <=> 2 is constant
     * <br></b>
     * <br>
     */
    private void grow () {

        int newCapacity = (int) (capacity * 2);
        Object[] newArray = new Object[newCapacity];

        for (int i = 0; i < size; i++){
            newArray[i] = array[i];
        }
        capacity = newCapacity;
        array = newArray;
    }

    /**
     * Shrinks the array to half it's size to preserve memory
     * <br>Usually called when the array has more than 1/3 of it's size
     * empty.
     * <br>
     * <br>
     * <b>Time complexity: O(N)
     * <br>
     * Space complexity: O(N)
     * <br></b>
     * <br>
     */
    private void shrink () {

        int newCapacity = (int) (capacity / 2);
        Object[] newArray = new Object[newCapacity];

        for (int i = 0; i < size; i++){
            newArray[i] = array[i];
        }
        capacity = newCapacity;
        array = newArray;
    }

    /**
     * Checks if the index to the last element in the
     * array is 0. If so, it means the array is empty
     * <br>
     * <br>
     * <b>Time complexity: O(1)
     * <br>
     * Space complexity: O(1)
     * <br></b>
     * <br>
     *
     * @return if the array is empty
     */
    public boolean isEmpty () {
        return size == 0;
    }

    /**
     * Returns a human friendly representation of the
     * array. Overrides the 'toString' method in <b>Object</b>
     * so you are able to print the array without calling the method.
     * <br>
     * <br>
     * <b>Time complexity: O(N)
     * <br>
     * Space complexity: O(N)
     * <br></b>
     * <br>
     *
     * @return String of the array
     */
    public String toString () {

        String string = "";

        for (int i = 0; i < capacity; i++){
            string += array[i] + ", ";
        }
        if (string != "") {
            string = "[" + string.substring(0, string.length() - 2) + "]";
        } else {
            string = "[]";
        }
        return string;
    }
}