/*-
 * Copyright (c) 2016, NGSPipes Team <ngspipes@gmail.com>
 * All rights reserved.
 *
 * This file is part of NGSPipes <http://ngspipes.github.io/>.
 *
 * This program is free software: you can redistribute it and/or modify it
 * under the terms of the GNU General Public License as published by the
 * Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 *
 * This program is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY
 * or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License
 * for more details.
 *
 * You should have received a copy of the GNU General Public License along
 * with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package jfxutils;

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

	@Override
	public boolean equals(Object o){
		if(o == null)
			return false;

		if(!(o instanceof Pair))
			return false;

		Pair<K,V> other = (Pair<K,V>)o;

		return	equals(this.key, other.key) &&
				equals(this.value, other.value);
	}

	public static boolean equals(Object a, Object b){
		if(a==b)
			return true;

		if((a == null && b != null) || (a != null && b == null))
			return false;

		return a.equals(b);
	}
	
}
