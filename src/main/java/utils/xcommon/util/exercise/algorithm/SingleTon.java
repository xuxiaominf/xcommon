package utils.xcommon.util.exercise.algorithm;

public class SingleTon {
	private  SingleTon(){};
	private volatile static SingleTon singleTon = null;
	public SingleTon getInstance(){
		if(singleTon==null){
			synchronized (this) {
				singleTon = new SingleTon();
			}
		}
		return singleTon;
	}
}
