package genericCheckpointing.strategy;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

import genericCheckpointing.util.FileOut;
import genericCheckpointing.util.SerializableObject;

public class XMLSerializationStrategy implements SerStrategy {
	/* (non-Javadoc)
	 * @see genericCheckpointing.strategy.SerStrategy#processInput(genericCheckpointing.util.SerializableObject)
	 * Takes an object and serialize it and wrtes it in wireformat
	 */
	@Override
	public void processInput(SerializableObject obj) {
		// TODO Auto-generated method stub
		String op = "<DPSerialization>\n";
		op += " <complexType xsi:type=\"" + obj.getClass().getName() + "\">\n";
		Field[] fields = obj.getClass().getDeclaredFields();
		int noOfFields = fields.length;
		try {
			for (int i = 0; i < noOfFields; i++) {
				String fieldName = fields[i].getName();
				op += "  <" + fieldName + " xsi:type=\"xsd:" + fields[i].getType().getSimpleName().toLowerCase() + "\">"
						+ obj.getClass().getMethod("get" + fieldName).invoke(obj) + "</" + fieldName + ">\n";
			}
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException
				| SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(e.getMessage());
			System.exit(1);
		}finally{}
		op += " </complexType>\n";
		op += "</DPSerialization>\n";
		FileOut.write(op);
	}

}
