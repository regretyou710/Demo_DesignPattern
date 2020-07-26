package tw.com.singleton.lazy;

public class Demo01 {

	public static void main(String[] args) {
		Order order1 = Order.getInstance();
		Order order2 = Order.getInstance();
		System.out.println(order1 == order2);
	}

}

// 懶漢式
// 存在執行緒安全問題
class Order {
	// 1.建立私有建構式
	private Order() {

	}

	// 2.由內部創建類的實例(物件)
	private static Order instance = null;

	// 3.提供公共方法獲取instance
	// 因為無法藉由new建立物件所以方法宣告靜態
	public static Order getInstance() {
		if (instance == null) {
			instance = new Order();
		}
		return instance;
	}
}