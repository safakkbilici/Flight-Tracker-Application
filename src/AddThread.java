
public class AddThread extends Thread {
	public static void ekle(Flight fly) {
		new Thread(new Runnable() {
		@Override
		public void run() {
			try {
				float a=fly.getRemains();
				while(fly.getRemains()!=0) {
					fly.setRemains(fly.getRemains()-1);
					sleep(1000);}
				fly.setRemains(a);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		}).start();
		
	}
}
