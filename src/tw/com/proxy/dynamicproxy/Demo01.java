package tw.com.proxy.dynamicproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class Demo01 {

	public static void main(String[] args) {
		Human proxyInstance = (Human) ProxyFactory.getProxyInstance(new SuperMan());

		// proxyInstance調用方法的時候，自動呼叫(調用)invoke()
		// 此時，invoke()中的method=所調用的方法，args=參數列表
		// ex:method=eat("pizza", 5, 10)，args=new Object[]{"pizza", 5, 10}
		String belief = proxyInstance.getBelief();
		System.out.println(belief);
		proxyInstance.eat("pizza", 5, 10);
	}
}

interface Human {
	// 信仰
	String getBelief();

	// 吃東西
	void eat(String food);

	// 吃東西
	void eat(String food, int count, int size);

}

class SuperMan implements Human {

	@Override
	public String getBelief() {
		return "I believe I can fly.";
	}

	@Override
	public void eat(String food) {
		System.out.println("我喜歡吃:" + food);
	}

	@Override
	public void eat(String food, int count, int size) {
		System.out.println("我喜歡吃:" + food + ",吃" + count + "片," + size + "吋");

	}
}

/*
 * 實現動態代理，需解決的問題:
 * 問題一:如何根據加載到內存中的被代理類(目標對象)，動態創建一個代理類(Class)及其對象(Instance)
 * 問題二:當透過代理類的對象調用方法時，如何動態的去調用被代理類中的同名方法
 */
class ProxyFactory {

	// 調用此方法，返回一個代理類的物件。解決問題一
	// obj:被代理類(代理目標)
	public static Object getProxyInstance(Object obj) {
		/*
		 * 調用反射機制中的Proxy類(java.lang.reflect.Proxy)
		 * newProxyInstance(loader,interfaces, h):創建代理的實例
		 * 參數一:傳遞進來的物件是哪一個類的加載器加載的?代理類就使用跟傳遞進來的物件相同的類加載器。使用目標的加載器
		 * 參數二:要代理的介面，來自目標的介面 
		 * 參數三:代理要執行的方法
		 */
		MyInvocationHandler handler = new MyInvocationHandler();
		handler.bind(obj);
		return Proxy.newProxyInstance(obj.getClass().getClassLoader(), obj.getClass().getInterfaces(), handler);
	}

}

class MyInvocationHandler implements InvocationHandler {
	// 把獲取代理物件時所傳遞進來的參數作為method.invoke()的代理目標
	private Object obj;

	public void bind(Object obj) {
		this.obj = obj;
	}

	// 當使用代理類所建立的物件(Instance)調用被代理類中的同名方法時，就會自動來呼叫(調用)如下的invoke()
	// 參數一:代理物件
	// 參數二:代理目標要執行方法
	// 參數三:代理目標要執行方法的參數列表
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		/*
		 * 和透過反射去操作方法的過程有點不同 
		 * Method method = obj.getClass().getDeclaredMethod("方法名稱","參數類型");
		 * method.invoke("運行時類對應的物件", "參數");
		 */
		
		// 參數一:代理目標
		// 參數二:執行調用方法的參數列表
		Object returnValue = method.invoke(obj, args);
		// 無論method.invoke()有無返回值，都可以用變數來接收
		// 把returnValue作為代理物件調用方法的返回值
		return returnValue;
	}
}