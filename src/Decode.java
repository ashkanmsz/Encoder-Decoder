import java.util.Scanner;

/**
 * 
 * @author ashkan
 * @since 12/9/2018
 *
 */
public class Decode {

	Map map = new Map();
	MaxHeap heap = new MaxHeap(26);
	Queue q = new Queue();

	public static void main(String[] args) {
		new Decode();

	}

	private Decode() {

		Scanner sc = new Scanner(System.in);

		String keysAndDatas = sc.nextLine();// here we get the keys and datas
		String code = sc.nextLine().substring(6);// here we get the code

		char codeArray[] = code.toCharArray();// change code to char array
		String tmp[] = keysAndDatas.split(" ");

		/**
		 * in this loop we make our map
		 */
		for (int i = 0; i < tmp.length; i += 2) {

			map.insert(tmp[i].charAt(0), Integer.parseInt(tmp[i + 1]));

		}

		Node t1 = new Node();// this is a node for traversing the map
		t1 = map.head;

		for (int i = 0; i < map.size; i++) {// enter the numbers in heap
			heap.insert((int) t1.data);
			t1 = t1.next;
		}

		unlock(heap, q, map, codeArray, 0);// this is the main func
		sc.close();
	}

	private void unlock(MaxHeap heap, Queue q, Map map, char arr[], int i) {

		int counter = 0;
		boolean f = false;
		boolean h = false;
		int b = 0;

		Queue q2 = new Queue();// this is copy of the queue
		Node t1 = q.getFirst();
		for (int j = 0; j < q.getSize(); j++) {
			q2.enqueue(t1.data);
			t1 = t1.prev;
		}

		Map map2 = new Map();// this is copy of the map
		Node t2 = map.head;

		for (int j = 0; j < map.size; j++) {
			map2.insert(t2.key, t2.data);
			t2 = t2.next;
		}

		MaxHeap heap2 = new MaxHeap(26);// this is copy of the heap

		for (int j = 1; j <= heap.size; j++) {
			heap2.insert(heap.heapArray[j]);
		}

		if (i >= arr.length) {// this is our finish condition for printing the results

			for (int j = 0; j < arr.length; j++)
				System.out.print(arr[j] + " ");

			System.out.println();
			System.out.println("*****************");
			return;
		}

		while (true) {// this loop is for delleting until reaching to our mind word

			if (heap2.size == 0) {// this if is for when our heap is empty
				h = true;
				break;
			}

			q2.enqueue(heap2.remove());
			counter++;

			if ((char) map2.getKey(q2.getLast().data) == arr[i]) {// if our word is in heap
				f = true;
				break;
			}

			if (heap2.size == 0) {// this if is for when our heap is empty
				h = true;
				break;
			}
		}

		if (counter == q2.getSize() && f == true) {// this if is for first condition of decoding if the word is in heap

			char tmp = arr[i];
			arr[i] = (char) map2.getKey(counter);

			map2.change(map2.getKey(counter), map2.getData(tmp));
			map2.change(tmp, counter);

			unlock(heap2, q2, map2, arr, b = i + 1);

		}

		if (h == true) {// this if is for when we didn't find our mind word in heap

			int size = (int) q2.getSize();

			for (int j = 0; j < size; j++)// pour all queue in heap
				heap2.insert((int) q2.dequeue().data);

			unlock(heap2, q2, map2, arr, i);

		}

		if (counter != q2.getSize() && f == true) {// this if is for when we found our secound word in heap

			char tmp = arr[i];// creat e copy from our word

			char arr2[] = new char[arr.length];// make a copy from our code array

			for (int j = 0; j < arr2.length; j++)
				arr2[j] = arr[j];

			Map map3 = new Map();// make a copy from our map
			Node t3 = map2.head;

			for (int j = 0; j < map2.size; j++) {
				map3.insert(t3.key, t3.data);
				t3 = t3.next;
			}

			arr[i] = (char) map2.getKey(counter);// change the char of our code

			map2.change(map2.getKey(counter), map2.getData(tmp));
			map2.change(tmp, counter);

			unlock(heap2, q2, map2, arr, b = i + 1);// send to our func

			int size = (int) q2.getSize();// the size of queue

			for (int j = 0; j < size; j++)
				heap2.insert((int) q2.dequeue().data);// pour the queue in the heap

			unlock(heap2, q2, map3, arr2, i);
		}

	}

}
