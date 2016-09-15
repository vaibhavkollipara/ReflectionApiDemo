package genericCheckpointing.mydata;

import java.util.Iterator;
import java.util.Vector;

import genericCheckpointing.util.SerializableObject;

public class VectorStorage implements Storage{
	private Vector<SerializableObject> objects;
	
	/**
	 * method to create vector
	 */
	public VectorStorage() {
		// TODO Auto-generated constructor stub
		objects = new Vector<SerializableObject>();
	}
	
	/* (non-Javadoc)
	 * @see genericCheckpointing.mydata.Storage#store(genericCheckpointing.util.SerializableObject)
	 * Adds object to vector
	 */
	@Override
	public void store(SerializableObject obj) {
		// TODO Auto-generated method stub
		objects.addElement(obj);
	}

	/* (non-Javadoc)
	 * @see genericCheckpointing.mydata.Storage#retrieve(int)
	 * method to retrieve object from vector
	 */
	@Override
	public void retrieve(int index) {
		// TODO Auto-generated method stub
		objects.get(index);
		
	}

	/* (non-Javadoc)
	 * @see genericCheckpointing.mydata.Storage#getIterator()
	 * returns an iterator for vector
	 */
	@Override
	public Iterator<SerializableObject> getIterator() {
		// TODO Auto-generated method stub
		return objects.iterator();
	}

}
