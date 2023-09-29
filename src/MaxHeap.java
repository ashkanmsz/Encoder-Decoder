/**
 * this class is for making our heap
 *
 */
public class MaxHeap {

	public int[] heapArray;// this is our heap array
	public int size;// the size of heap
	private int length;// the size of heap array

	public MaxHeap(int length) {

		this.length = length;
		this.size = 0;
		heapArray = new int[this.length + 1];// we make a array with one more size for adding elements
		heapArray[0] = Integer.MAX_VALUE;// the first index of heap array is a
		// big number

	}

	// return the parent of each node
	private int parent(int i) {

		return i / 2;

	}

	// return the left Child of each node
	private int leftChild(int i) {

		return (2 * i);

	}

	// return the right Child of each node
	private int rightChild(int i) {

		return (2 * i) + 1;

	}

	public boolean isLeaf(int pos) {// this func is for cheking that our node is leaf or not

		if (pos > (size / 2) && pos <= size)
			return true;

		return false;
	}

	public void swap(int pos2, int pos1) {// with this func we change the parent with its children

		int tmp = heapArray[pos2];
		heapArray[pos2] = heapArray[pos1];
		heapArray[pos1] = tmp;

	}

	public void heapify(int pos) {// this func is for after removing the element and change the place of nodes

		if (isLeaf(pos))
			return;

		if (heapArray[pos] < heapArray[leftChild(pos)] || heapArray[pos] < heapArray[rightChild(pos)]) {

			if (heapArray[leftChild(pos)] > heapArray[rightChild(pos)]) {

				swap(pos, leftChild(pos));
				heapify(leftChild(pos));

			}

			else {

				swap(pos, rightChild(pos));
				heapify(rightChild(pos));

			}
		}

	}

	public void insert(int value) {// this func is for adding elements to our tree

		heapArray[++size] = value;// each time we add element we should increase our size

		int current = size;

		while (heapArray[current] > heapArray[parent(current)]) {// if our element is bigger tham
																	// its parent
			swap(parent(current), current);
			current = parent(current);
		}
	}

	public int remove() {// this func is for removing an elemnt from our heap

		int tmp = heapArray[1];
		heapArray[1] = heapArray[size--];
		heapify(1);
		return tmp;

	}

}
