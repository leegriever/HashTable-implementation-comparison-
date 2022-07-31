/*
 name1: Lee Griever
 name2: Bar Onn
*/

public class LPHashTable extends OAHashTable {
	
	public LPHashTable(int m, long p) {
		super(m);
		this.h = ModHash.GetFunc(m, p);
	}
	
	@Override
	public int Hash(long x, int i) {
		long res = ((long)h.Hash(x) + (long)i) % this.m;
		return (int)res;
	}
	
}
