package tw.com.template;

/*
 * 抽象類應用:模板方法設計模式
 * 簡述:當功能內部一部分代碼實現是確定的，一部分實現是不確定的。這時可以把不確定的部分抽象化，讓子類去實現
 */
public class Demo02 {

	public static void main(String[] args) {
		BankTemplateMethod btm1 = new DrawMoney();
		btm1.process();
		
		System.out.println();
		
		BankTemplateMethod btm2 = new ManageMoney();
		btm2.process();
	}

}

abstract class BankTemplateMethod {
	// 具體方法
	public void takeNumber() {
		System.out.println("排隊取號");
	}

	// 辦理具體的業務
	// 鉤子方法
	public abstract void transact();

	public void evaluate() {
		System.out.println("意見評分");
	}

	// 模板方法，把基本操作組合一起，子類一般不能重寫
	public final void process() {
		this.takeNumber();

		// 像個鉤子，具體執行時，掛哪個子類就執行哪個子類的實現代碼
		this.transact();

		this.evaluate();
	}
}

class DrawMoney extends BankTemplateMethod {
	@Override
	public void transact() {
		System.out.println("我要提款!!");
	}

}

class ManageMoney extends BankTemplateMethod {
	@Override
	public void transact() {
		System.out.println("我要理財!!");
	}
}