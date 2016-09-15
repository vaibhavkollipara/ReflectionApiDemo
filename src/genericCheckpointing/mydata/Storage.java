package genericCheckpointing.mydata;

import java.util.Iterator;

import genericCheckpointing.util.SerializableObject;

public interface Storage {
	public void store(SerializableObject obj);
	public void retrieve(int index);
	public Iterator<SerializableObject> getIterator();
}
