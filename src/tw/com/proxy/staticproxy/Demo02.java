package tw.com.proxy.staticproxy;

/*
 * 代理模式:靜態代理
 */
public class Demo02 {

	public static void main(String[] args) {
		Proxy proxy = new Proxy(new RealStar());
		proxy.confer();
		proxy.signContract();
		proxy.bookTicket();
		proxy.sing();
		proxy.collectMoney();
	}

}

// 明星該做的事
interface Star {
	// 面談
	void confer();

	// 簽合約
	void signContract();

	// 訂票
	void bookTicket();

	// 唱歌
	void sing();

	// 收錢
	void collectMoney();
}

// 明星(人)
class RealStar implements Star {

	@Override
	public void confer() {

	}

	@Override
	public void signContract() {

	}

	@Override
	public void bookTicket() {

	}

	@Override
	public void sing() {
		System.out.println("明星:唱歌~~");
	}

	@Override
	public void collectMoney() {

	}

}

// 經紀人
class Proxy implements Star {
	private Star realStar;

	public Proxy(Star realStar) {
		this.realStar = realStar;
	}

	@Override
	public void confer() {
		System.out.println("經紀人:面談");
	}

	@Override
	public void signContract() {
		System.out.println("經紀人:簽合約");
	}

	@Override
	public void bookTicket() {
		System.out.println("經紀人:訂票");
	}

	@Override
	public void sing() {
		realStar.sing();		
	}

	@Override
	public void collectMoney() {
		System.out.println("經紀人:收錢");
	}

}