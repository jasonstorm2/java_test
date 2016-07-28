package JavaCoreLearn;

class Thread33 implements Runnable {

	int t;
	Thread a, b;

	Thread33() {
		t = 300;
		a = new Thread(this);//Thread(Runnable)
		a.setName("Accountant");
		b = new Thread(this);
		b.setName("Cashier");
	}

	public void run() {
		while (true) {
			t = t - 50;
			if (Thread.currentThread() == a) {
				System.out.println(a.getName() + " need " + t + " Yuan.");
				if (t <= 150) {
					System.out.println(a.getName() + " is dead...");
					return;
				}
			} else if (Thread.currentThread() == b) {
				System.out.println(b.getName() + " need " + t + " Yuan.");
				if (t <= 0) {
					System.out.println(a.getName() + " is dead...");
					return;
				}
			}
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {

			}
		}
	}
}
