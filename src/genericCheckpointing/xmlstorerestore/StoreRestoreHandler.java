package genericCheckpointing.xmlstorerestore;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

import genericCheckpointing.server.RestoreI;
import genericCheckpointing.server.StoreI;
import genericCheckpointing.strategy.DeserStrategy;
import genericCheckpointing.strategy.SerStrategy;
import genericCheckpointing.strategy.XMLDeserializationStrategy;
import genericCheckpointing.strategy.XMLSerializationStrategy;
import genericCheckpointing.util.FileProcessor;
import genericCheckpointing.util.SerializableObject;

/**
 * @author vaibhav
 *Handler for Dynamic proxy
 */
public class StoreRestoreHandler implements InvocationHandler {


	/**
	 * @param sObject
	 * @param sStrategy
	 * serializes data
	 */
	private void serializeData(SerializableObject sObject, SerStrategy sStrategy) {
		sStrategy.processInput(sObject);
	}

	/**
	 * @param dStrategy
	 * @param fileProcessor
	 * @return SerializableObject
	 * deserializes data
	 */
	private SerializableObject deserializeData(DeserStrategy dStrategy, FileProcessor fileProcessor) {
		return dStrategy.processData(fileProcessor);
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		// TODO Auto-generated method stub
		SerializableObject obj = null;
		Class<?> className = method.getDeclaringClass();
		if (className.equals(StoreI.class)) {
			if (method.getName().equals("writeObj")) {
				if ("XML".equals(args[1])) {
					serializeData((SerializableObject) args[0], new XMLSerializationStrategy());
				}
			}
		} else if (className.equals(RestoreI.class)) {
			if (method.getName().equals("readObj")) {
				if ("XML".equals(args[0])) {
					obj = deserializeData(new XMLDeserializationStrategy(), (FileProcessor)args[1]);
				}
			}
		}else{
			System.out.println("Invalid Method Call...");
			System.out.println(1);
		}
		return obj;
	}

}
