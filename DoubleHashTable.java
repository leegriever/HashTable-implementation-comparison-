/*
 name1: Lee Griever
 name2: Bar Onn
 */

public class DoubleHashTable extends OAHashTable {
	private ModHash h2;
	
	public DoubleHashTable(int m, long p) {
		super(m);
		this.h = ModHash.GetFunc(m, p);
		this.h2 = ModHash.GetFunc(m, p);
	}
	
	@Override
	public int Hash(long x, int i) {
		return (int) (((long)h.Hash(x) + ((long)i * (long)h2.Hash2(x))) % this.m);
	}
	
}
