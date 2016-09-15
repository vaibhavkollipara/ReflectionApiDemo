package genericCheckpointing.util;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

import genericCheckpointing.server.StoreRestoreI;

/**
 * @author vaibhav
 *class to create new Proxy Instance
 */
public class ProxyCreator {
	/**
	 * @param interfaceArray
	 * @param handler
	 * @return StoreRestoreI
	 * Creates proxy instace and returns it
	 */
	public StoreRestoreI createProxy(Class<?>[] interfaceArray, InvocationHandler handler) {
		StoreRestoreI storeRestoreRef = (StoreRestoreI) Proxy.newProxyInstance(getClass().getClassLoader(),
				interfaceArray, handler);

		return storeRestoreRef;
	}

}
