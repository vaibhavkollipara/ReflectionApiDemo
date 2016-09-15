package genericCheckpointing.util;

import java.util.Random;

public class MyAllTypesSecond extends SerializableObject {
	private double myDoubleT;
	private float myFloatT;
	private short myShortT;
	private char myCharT;
	
	public static MyAllTypesSecond getRandomSecond(){
		MyAllTypesSecond obj = new MyAllTypesSecond();
		Random random=new Random(System.currentTimeMillis());
		for(int i=0;i<2;i++){
			int r = (int) (Math.random()*4);
			if(r==0){ 
				obj.setmyDoubleT(random.nextDouble());
			}else if(r==1){
				obj.setmyFloatT(random.nextFloat());
			}else if(r==2){
				obj.setmyShortT((short) random.nextInt(100));
			}else if(r==3){
				obj.setmyCharT((char) (random.nextInt((126 - 32) + 1) + 32));
			}else{System.out.println("Error");}
			}
		return obj;
	}

	public MyAllTypesSecond(){
		setmyDoubleT(0);
		setmyFloatT(0.0f);
		setmyShortT((short)0);
		setmyCharT('X');
	}
	public MyAllTypesSecond(double myDoubleIn, float myFloatIn, short myShortIn, char myCharIn) {
		setmyDoubleT(myDoubleIn);
		setmyFloatT(myFloatIn);
		setmyShortT(myShortIn);
		setmyCharT(myCharIn);
	}


	public double getmyDoubleT() {
		return myDoubleT;
	}
	public void setmyDoubleT(double myDoubleT) {
		this.myDoubleT = myDoubleT;
	}
	public float getmyFloatT() {
		return myFloatT;
	}
	public void setmyFloatT(float myFloatT) {
		this.myFloatT = myFloatT;
	}
	public short getmyShortT() {
		return myShortT;
	}
	public void setmyShortT(short myShortT) {
		this.myShortT = myShortT;
	}
	public char getmyCharT() {
		return myCharT;
	}
	public void setmyCharT(char myCharT) {
		this.myCharT = myCharT;
	}
	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		if(!this.getClass().equals(obj.getClass()))
			return false;
		return (myDoubleT == ((MyAllTypesSecond) obj).getmyDoubleT() && myFloatT == ((MyAllTypesSecond) obj).getmyFloatT()
				&& myShortT == ((MyAllTypesSecond) obj).getmyShortT() && myCharT == ((MyAllTypesSecond) obj).getmyCharT());
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Class :"+this.getClass().getName()+"\nmyDoubleT :"+getmyDoubleT()+"\nmyFloatT :"+getmyFloatT()+"\nmyShortT :"+getmyShortT()+"\nmyCharT :"+getmyCharT()+"\n";
	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		int hash = 3;
		hash += 7 * (int)this.myCharT;
		hash += 7 * (int)this.myDoubleT;
		hash += 7 * (int)this.myFloatT;
		hash += 7 * (int)this.myShortT;
		
		return hash;		
	}
	
	
	

}
