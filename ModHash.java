/*
 name1: Lee Griever
 name2: Bar Onn
*/

import java.util.concurrent.ThreadLocalRandom;

public class ModHash {
	private long a;
	private long b;
	private long p;
	private long m;

	public ModHash(long a, long b, long p, int m){
		this.a = a;
		this.b = b;
		this.p = p;
		this.m = (long)m;
	}
	public static ModHash GetFunc(int m, long p){
		long a = ThreadLocalRandom.current().nextLong(1, p);
		long b = ThreadLocalRandom.current().nextLong(0, p);
		return new ModHash(a, b, p, m);
	}
	
	public int Hash(long key) {
		long h = ((a * key + b) % p) % m;
		return (int)h;
	}
	public int Hash2(long key) {
		long h = (((a * key + b) % p) % (m-1)) + 1;
		return (int)h;
	}
}
