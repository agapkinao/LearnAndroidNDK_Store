public class Store {
	static
	{
		System.loadLibrary("store");
	}
	
	public native int getInteger(String pKey);
	public native void setIngeter(String pKey, int pInt);
	public native String getString(String pKey);
	public native void setString(String pKey, String pString);
}
