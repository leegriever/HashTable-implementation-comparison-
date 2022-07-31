/*
 name1: Lee Griever
 name2: Bar Onn
*/

public class AQPHashTable extends OAHashTable {

	public AQPHashTable(int m, long p) {
		super(m);
		this.h = ModHash.GetFunc(m, p);
	}
	
	@Override
	public int Hash(long x, int i) {
		long li = (long)i;
		long sign = (li % 2 == 0) ? 1 : -1;
		long res = ((long)h.Hash(x) + (sign * (li*li))) % this.m;
		if (res < 0)
			res += this.m;
		return (int) res;
	}
}
