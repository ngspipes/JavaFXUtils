package utils;

public class Pair<K,V> {

	private K key;
	public K getKey(){ return key; }
	public void setKet(K key){ this.key = key; }
	
	private V value;
	public V getValue(){ return value; }
	public void setValue(V value){ this.value = value; }
	
	public Pair(K key, V value){
		this.key = key;
		this.value = value;
	}
	
	public Pair(){}
	
}
