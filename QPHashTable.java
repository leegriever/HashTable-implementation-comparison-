/*
 name1: Lee Griever
 name2: Bar Onn
*/

public class QPHashTable extends OAHashTable {

	public QPHashTable(int m, long p) {
		super(m);
		this.h = ModHash.GetFunc(m, p);
	}
	
	@Override
	public int Hash(long x, int i) {
		long res = ((long)h.Hash(x) + (long)Math.pow(i, 2)) % this.m;
		return (int) res;
	}
}
