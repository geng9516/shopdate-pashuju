package shopdate;

public class Aaaa {

	public static void main(String[] args) {
		Jdbc jdbc = new Jdbc();
		try {
			jdbc.getDb();
		} catch (ClassNotFoundException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
	}

}
