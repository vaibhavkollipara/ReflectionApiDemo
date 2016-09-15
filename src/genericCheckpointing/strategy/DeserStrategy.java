package genericCheckpointing.strategy;

import genericCheckpointing.util.FileProcessor;
import genericCheckpointing.util.SerializableObject;

public interface DeserStrategy {
	public SerializableObject processData(FileProcessor fileProcessor);
}
