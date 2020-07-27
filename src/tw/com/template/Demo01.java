package tw.com.template;
import java.text.SimpleDateFormat;
import java.util.Date;

/*
 * 抽象類應用:模板方法設計模式
 * 簡述:當功能內部一部分代碼實現是確定的，一部分實現是不確定的。這時可以把不確定的部分抽象化，讓子類去實現
 */
public class Demo01 {

	public static void main(String[] args) {
		SubTemplate t = new SubTemplate();
		t.spendTime();
	}

}

abstract class Template {
	// 計算出某段代碼所花費的時間
	public void spendTime() {
		long start = System.currentTimeMillis();
		code();
		long end = System.currentTimeMillis();
		System.out.println("所花費時間:" + (end - start) + "毫秒");
	}

	public abstract void code();
}

class SubTemplate extends Template {

	@Override
	public void code() {
		// 求質數
		for (int i = 2; i <= 1000; i++) {
			boolean flag = true;
			for (int j = 2; j <= Math.sqrt(i); j++) {
				if (i % j == 0) {
					flag = false;
					break;
				}
			}
			if (flag) {
				System.out.println(i);
			}
		}
	}

}