package utils;

public class ComponentException extends Exception{
	
	private static final long serialVersionUID = 1L;

	public ComponentException(){}
	
	public ComponentException(String msg){ super(msg); }
	
	public ComponentException(String msg, Throwable cause){ super(msg, cause); }

}
