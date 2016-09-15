package genericCheckpointing.util;

import java.util.Random;

public class MyAllTypesFirst extends SerializableObject {
	private int myInt;
	private long myLong;
	private String myString;
	private boolean myBool;

	public static MyAllTypesFirst getRandomFirst(){
		MyAllTypesFirst	obj = new MyAllTypesFirst();
		Random random=new Random(System.currentTimeMillis());
		for(int i=0;i<2;i++){
		int r = (int) (Math.random()*4);
		if(r==0){ 
			obj.setmyInt(random.nextInt(Integer.MAX_VALUE));
		}else if(r==1){
			obj.setmyLong(random.nextLong());
		}else if(r==2){
			obj.setmyString("RandomString "+random.nextInt(Integer.MAX_VALUE));
		}else if(r==3){
				obj.setmyBool(random.nextBoolean());
		}else{System.out.println("Error");}
		}
		return obj;
	}
	
	public MyAllTypesFirst(){
		setmyInt(0);
		setmyLong(000000);
		setmyString("****");
		setmyBool(false);
	}
	public MyAllTypesFirst(int myIntIn, long myLongIn, String myStringIn, boolean myBoolIn) {
		setmyInt(myIntIn);
		setmyLong(myLongIn);
		setmyString(myStringIn);
		setmyBool(myBoolIn);
	}

	public int getmyInt() {
		return myInt;
	}

	public void setmyInt(int myInt) {
		this.myInt = myInt;
	}

	public long getmyLong() {
		return myLong;
	}

	public void setmyLong(long myLong) {
		this.myLong = myLong;
	}

	public String getmyString() {
		return myString;
	}

	public void setmyString(String myString) {
		this.myString = myString;
	}

	public boolean getmyBool() {
		return myBool;
	}

	public void setmyBool(boolean myBool) {
		this.myBool = myBool;
	}

	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		if(!this.getClass().equals(obj.getClass()))
			return false;
		return (this.myInt == ((MyAllTypesFirst) obj).getmyInt() && this.myLong == ((MyAllTypesFirst) obj).getmyLong()
				&& this.myBool == ((MyAllTypesFirst) obj).getmyBool() && this.myString.equals(((MyAllTypesFirst) obj).getmyString()));
	}
	
	
	
	
	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		int hash = 3;
		hash += 7 * hash + this.myInt;
		hash += (int)7 * hash + this.myLong;
		hash += (int)7 * hash + this.myString.length();
		hash += (int)7 * hash + String.valueOf(this.myBool).length();
		return hash;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Class :"+this.getClass().getName()+"\nmyInt :"+getmyInt()+"\nmyLong :"+getmyLong()+"\nmyBool :"+getmyBool()+"\nmyString : "+getmyString()+"\n";
	}

	

}
