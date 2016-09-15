package genericCheckpointing.strategy;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import genericCheckpointing.util.FileProcessor;
import genericCheckpointing.util.MyAllTypesFirst;
import genericCheckpointing.util.MyAllTypesSecond;
import genericCheckpointing.util.SerializableObject;

public class XMLDeserializationStrategy implements DeserStrategy {

	/* (non-Javadoc)
	 * @see genericCheckpointing.strategy.DeserStrategy#processData(genericCheckpointing.util.FileProcessor)
	 * Reads the input file and returns object for each call
	 */
	@Override
	public SerializableObject processData(FileProcessor fileProcessor) {
		// TODO Auto-generated method stub
		String line = "";
		String value;
		String type = "";
		String var = "";
		SerializableObject object = null;
		Class<?> cls = null;
		line = fileProcessor.readLineFromFile();
		if (line == null)
			return null;
		if (line.equals("<DPSerialization>"))
			line = fileProcessor.readLineFromFile();
		while (!line.equals("</DPSerialization>")) {
			if (line.equals(" </complexType>")) {
				line = fileProcessor.readLineFromFile();
			} else if (line.contains("xsi:type")) {
				type = line.substring(line.indexOf("\"") + 1, line.lastIndexOf("\""));
				if (type.contains("genericCheckpointing.util")) {
					try {
						cls = Class.forName(type);
						object = (SerializableObject) cls.newInstance();
					} catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						System.out.println(e.getMessage());
						System.exit(1);
					}
				} else {
					var = line.substring(line.indexOf('<')+1, line.indexOf(' ', line.indexOf('<')+1));
					value = line.substring(line.indexOf("\">") + 2, line.indexOf("</"));
					Method method = null;
					try {
						switch (type) {
						case "xsd:int":
							method = cls.getMethod("set" + var, int.class);
							method.invoke(object, Integer.parseInt(value));
							break;
						case "xsd:float":
							method = cls.getMethod("set" + var, float.class);
							method.invoke(object, Float.parseFloat(value));
							break;
						case "xsd:double":
							method = cls.getMethod("set" + var, double.class);
							method.invoke(object, Double.parseDouble(value));
							break;
						case "xsd:long":
							method = cls.getMethod("set" + var, long.class);
							method.invoke(object, Long.parseLong(value));
							break;
						case "xsd:short":
							method = cls.getMethod("set" + var, short.class);
							method.invoke(object, Short.parseShort(value));
							break;
						case "xsd:char":
							method = cls.getMethod("set" + var, char.class);
							method.invoke(object, value.charAt(0));
							break;
						case "xsd:string":
							method = cls.getMethod("set" + var, String.class);
							method.invoke(object, value);
							break;
						case "xsd:boolean":
							method = cls.getMethod("set" + var, boolean.class);
							method.invoke(object, Boolean.parseBoolean(value));
							break;
						default:
							System.out.println("Unrecognized Data Type ! : "+type);
							System.exit(1);
						}
					} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException
							| NoSuchMethodException | SecurityException e) {
						// TODO Auto-generated catch block
						System.out.println(e.getMessage());
						e.printStackTrace();
						System.exit(1);
					}finally{}

				}

				line = fileProcessor.readLineFromFile();

			} else {
				System.out.println("Invalid File... : "+line);
				System.exit(1);
			}
		}

		return object;
	}

}
