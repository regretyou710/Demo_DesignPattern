package tw.com.singleton.early;

/*
 * 單例模式
 */
public class Demo01 {

	public static void main(String[] args) {
		Bank bank1 = Bank.getInstance();
		Bank bank2 = Bank.getInstance();
		System.out.println(bank1==bank2);
	}

}

//餓漢式
class Bank {
	// 1.建立私有建構式
	private Bank() {

	}

	// 2.由內部創建類的實例(物件)
	private static Bank instance = new Bank();

	// 3.提供公共方法獲取instance
	// 因為無法藉由new建立物件所以方法宣告靜態
	public static Bank getInstance() {
		return instance;
	}
}