package tw.com.proxy.staticproxy;

//interface應用:靜態代理模式
public class Demo01 {
	public static void main(String[] args) {
		Server server = new Server();
		ProxyServer proxyServer = new ProxyServer(server);
		proxyServer.browse();
	}
}

interface NetWork {
	public void browse();
}

// 被代理類(代理目標)
class Server implements NetWork {

	@Override
	public void browse() {
		System.out.println("真實服務器訪問網路");
	}

}

// 代理類
class ProxyServer implements NetWork {
	private NetWork server;

	public ProxyServer(NetWork server) {
		this.server = server;
	}

	@Override
	public void browse() {
		check();
		server.browse();
	}

	public void check() {
		System.out.println("連網之前的檢查工作");
	}
}