import java.util.Scanner;

/**
 * this program is for encodin a string
 * 
 * @author ashkan
 * @since 12/9/2018
 */

public class Encode {

	private static Map map = new Map();
	private static MaxHeap heap = new MaxHeap(26);
	private static Queue q = new Queue();

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		String ramz = sc.nextLine();

		char[] ramzArray = ramz.toCharArray();// changing the Enter string too char array
		int[] letters = new int[26];

		/**
		 * this loop is for random key for each letter and enter them in map
		 */

		ashkan: for (int i = 0; i < letters.length; i++) {// this loop is for making random numbers for letters
			letters[i] = 1 + (int) (26 * Math.random());

			for (int j = 0; j < i; j++) {
				if (letters[j] == letters[i]) {
					i--;
					continue ashkan;
				}
			}
		}

		int x = 0;

		for (int i = 97; i < 123; i++) {// in this loop we match numbers with letters
			char c = (char) i;
			map.insert(c, letters[x]);
			x++;
		}

		Node t = map.head;

		for (int i = 0; i < map.size; i++) {
			heap.insert((int) t.data);
			t = t.next;
		}

		System.out.println("map :");

		//////////////////////////////////////////////////////////////////////////

		Node t1 = new Node();
		t1 = map.head;

		for (int i = 0; i < map.size; i++) {

			System.out.print(t1.key + " " + t1.data + " ");
			t1 = t1.next;
		}

		System.out.println();

		//////////////////////////////////////////////////////////////////////////

		//System.out.println();

		/**
		 * this loop is for decoding
		 */

		int count = 0;

		for (int i = 0; i < ramzArray.length; i++) {

			count = (int) map.getData(ramzArray[i]);

			if (heap.size >= count) {// if we can remove from heap...

				for (int j = 0; j < count; j++) {
					q.enqueue(heap.remove());// push in the queue

				}

				// if our heap size smaller than our count
			} else {

				int a = (int) q.getSize();

				for (int j = 0; j < a; j++) {// pour all of the queue in heap

					heap.insert((int) q.dequeue().data);
				}

				for (int j2 = 0; j2 < count; j2++)
					q.enqueue(heap.remove());// now delete from heap and push in queue
			}

			Node t2 = q.getLast();// get the last element

			char tmp = ramzArray[i];// hold the last amount
			ramzArray[i] = (char) map.getKey(t2.data);// change the letter

			/**
			 * in this part we change the numbers of the letters
			 */

			map.change(map.getKey(t2.data), count);
			map.change(tmp, t2.data);

		}

		String s = "";

		for (int i = 0; i < ramzArray.length; i++)// changing the char array to string
			s += ramzArray[i];

		System.out.println("ramz= " + s);

		sc.close();

	}
}
