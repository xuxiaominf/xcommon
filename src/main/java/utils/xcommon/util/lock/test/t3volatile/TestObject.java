package utils.xcommon.util.lock.test.t3volatile;

public class TestObject  {
//	private int state = 0;
	private volatile int state = 0;
//	private AtomicInteger state = new AtomicInteger(0);

	public int getState() {
		return state;
//		return state.get();
	}

	public void setState(int st) {
//		state.compareAndSet(state.get(), st);
		this.state = state;
	}
}
