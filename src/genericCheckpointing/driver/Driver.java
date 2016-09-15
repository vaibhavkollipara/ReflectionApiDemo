package genericCheckpointing.driver;

import java.util.Iterator;

import genericCheckpointing.mydata.VectorStorage;
import genericCheckpointing.mydata.Storage;
import genericCheckpointing.server.RestoreI;
import genericCheckpointing.server.StoreI;
import genericCheckpointing.server.StoreRestoreI;
import genericCheckpointing.util.FileOut;
import genericCheckpointing.util.FileProcessor;
import genericCheckpointing.util.MyAllTypesFirst;
import genericCheckpointing.util.MyAllTypesSecond;
import genericCheckpointing.util.ProxyCreator;
import genericCheckpointing.util.SerializableObject;
import genericCheckpointing.xmlstorerestore.StoreRestoreHandler;

public class Driver {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		if (args.length == 3) {
			int misMatchedObjects=0;
			String mode = args[0];
			String fileName = args[2];
			FileProcessor fileProcessor=null;
			ProxyCreator pc = new ProxyCreator();
			StoreRestoreI cpointRef = (StoreRestoreI) pc.createProxy(new Class[] { StoreI.class, RestoreI.class },
					new StoreRestoreHandler());
			MyAllTypesFirst f = null;
			MyAllTypesSecond s = null;
			SerializableObject obj = null;
			// serdser mode
			if ("serdeser".equals(mode)) {
				int noOfObjects = 0;
				try {
					noOfObjects = Integer.parseInt(args[1]);
				} catch (NumberFormatException e) {
					System.out.println("Invalid command line argument for number of objects");
					System.exit(1);
				}
				Storage list1 = new VectorStorage();
				Storage list2 = new VectorStorage();
				FileOut.setFile(fileName);
				for (int i = 0; i < noOfObjects; i++) {
					f = MyAllTypesFirst.getRandomFirst();
					list1.store(f);
					((StoreI) cpointRef).writeObj(f, "XML");
					s = MyAllTypesSecond.getRandomSecond();
					list1.store(s);
					((StoreI) cpointRef).writeObj(s, "XML");
				}
				FileOut.closeFile();
				fileProcessor = new FileProcessor(fileName);
				obj = ((RestoreI) cpointRef).readObj("XML",fileProcessor);
				while (obj != null) {
					list2.store(obj);
					obj = ((RestoreI) cpointRef).readObj("XML",fileProcessor);
				}
				Iterator<SerializableObject> list1Iterator = list1.getIterator();
				Iterator<SerializableObject> list2Iterator = list2.getIterator();
				SerializableObject oldObj =null;
				SerializableObject newObj =null;
				while (list1Iterator.hasNext() && list2Iterator.hasNext()) {
					oldObj = list1Iterator.next();
					newObj = list2Iterator.next();
					if (!(oldObj.equals(newObj) && oldObj.hashCode()==newObj.hashCode())) {
						misMatchedObjects++;
					}
				}
				fileProcessor.closeFile();
				System.out.println(misMatchedObjects+" mismatched object");

			} else if ("deser".equals(mode)) {
				// deser mode
				fileProcessor = new FileProcessor(fileName);
				obj = ((RestoreI) cpointRef).readObj("XML",fileProcessor);
				while (obj != null) {
					System.out.println(obj);
					obj = ((RestoreI) cpointRef).readObj("XML",fileProcessor);
				}
				fileProcessor.closeFile();
			} else {
				System.out.println("Invalid Mode of Operation");
				System.exit(1);
			}
		} else {
			System.out.println("invalid no. of arguments");
			System.exit(1);
		}
	}
}
