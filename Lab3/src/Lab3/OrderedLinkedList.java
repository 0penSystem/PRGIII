package Lab3;

import java.util.Iterator;
import java.util.NoSuchElementException;


/***
 * 
 * @author evan.diver
 *
 * @param <T>
 */
public class OrderedLinkedList<T extends Comparable<T>> implements Iterable<T> {

	LinearNode head = null;
	int count = 0;

	public int getCount() {
		return count;
	}

	public OrderedLinkedList() {
		head = null;
		count = 0;
	}
	
	public void add(T value) {
		LinearNode current = head;
		LinearNode newNode = new LinearNode(value);
		
		if (head == null) {
			head = new LinearNode(value);
		}
		else if (head.getData().compareTo(value) > 0 ){
			newNode.setNext(head);
			head = newNode;
		}
		else {
		
			while (current.getNext() != null && current.getNext().getData().compareTo(value) < 0) {
				
			
				
				current = current.getNext();
				
				
			}		
			
			newNode.setNext(current.getNext());
			current.setNext(newNode);
		}
		count++;
	}

	public T findOrAdd(T value) {
		LinearNode current = head;
		LinearNode newNode = new LinearNode(value);

		if (head == null) {
			head = newNode;
		} else if (head.getData().compareTo(value) >= 0) {
			if(head.getData().compareTo(value) == 0){
				return head.getData();
			}			
			newNode.setNext(current);
			head = newNode;
		} else {

			while (current.getNext() != null && current.getNext().getData().compareTo(value) <= 0) {
				
				current = current.getNext();
				if (current.getData().compareTo(value) == 0)
					return current.getData();
			}

			newNode.setNext(current.getNext());
			current.setNext(newNode);
		}
		count++;
		return value;
	}
	

	public T at(int I) {
		LinearNode current = head;
		int i = 0;

		while (current != null && i < I) {
			current = current.getNext();
			i++;
		}
		return current.getData();

	}

	public String toString() {
		String temp = "";
		for (int i = 0; i < count; i++) {
			temp += this.at(i).toString() + "\t";
		}
		return temp;

	}

	@Override
	public Iterator<T> iterator() {
		return new OrdListIterator();
	}

	private class LinearNode {
		T data;
		LinearNode next;

		public LinearNode(T value) {
			data = value;
		}

		public T getData() {
			return data;
		}

		@SuppressWarnings("unused")
		public void setData(T data) {
			this.data = data;
		}

		public LinearNode getNext() {
			return next;
		}

		public void setNext(LinearNode next) {
			this.next = next;
		}

		public String toString() {
			return data.toString();
		}

	}

	private class OrdListIterator implements Iterator<T> {
		LinearNode current;

		public OrdListIterator() {
			current = head;
		}

		@Override
		public boolean hasNext() {
			return (current.getNext() != null);
		}

		@Override
		public T next() {
			if (!hasNext()) {
				throw new NoSuchElementException();
			}
			T result = current.getData();
			current = current.getNext();
			return result;
		}

		public void remove() {
			throw new UnsupportedOperationException();
		}
	}
}
