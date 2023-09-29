
public class Map {

	Node head;
	int size = 0;

	public Map() {

	}

	public Map(Node head) {
		this.head = head;
		size++;
	}

	public void insert(Object key, Object data) {

		Node tmp = new Node(key, data);

		if (size == 0) {
			head = tmp;
			size++;

		} else {
			tmp.next = head;
			head = tmp;
			size++;
		}
	}

	// in tabe baraye tashkhise inke yek data e khas vojood dard ya na
	public boolean isData(Object key) {

		Node tmp = new Node();
		tmp = head;

		while (tmp != null) {

			if (tmp.key==key)
				return true;

			tmp = tmp.next;
		}

		return false;
	}

	// in tabe baraye gereftane data e yek moteghayer ast
	
	public Object getData(Object key) {

		Node tmp = new Node();
		tmp = head;

		while (tmp != null) {

			if (tmp.key==key) {

				return tmp.data;
			}

			tmp = tmp.next;

		}

		return null;
	}

	// in tabe baraye gereftane key az data e morede nazar ast

	public Object getKey(Object data) {

		Node tmp = new Node();
		tmp = head;

		while (tmp != null) {

			if (tmp.data==data) {

				return tmp.key;
			}

			tmp = tmp.next;

		}
		return null;

	}

	// in tabe baraye define e dobare morede estefade gharar migirad

	public void change(Object key, Object newData) {

		Node tmp = new Node();
		tmp = head;

		while (tmp != null) {

			if (tmp.key.equals(key)) {
			
				tmp.data = newData;
			//	System.out.println("tmp.key= "+tmp.key+" tmp.data= "+tmp.data);
				return;

			}

			tmp = tmp.next;

		}

	}

	public void clear() {
		head = null;
		size = 0;
	}

}
