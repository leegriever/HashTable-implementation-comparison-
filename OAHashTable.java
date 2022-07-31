/*
 name1: Lee Griever
 name2: Bar Onn
*/

public abstract class OAHashTable implements IHashTable {
	
	private HashTableElement [] table;
	protected long m;
	protected ModHash h;
	
	public OAHashTable(int m) {
		this.table = new HashTableElement[m];
		this.m = (long) m;
	}
	
	public int getIndex(long key){
		int j;
		for (int i = 0 ; i < m ; i++){
			j = Hash(key, i);
			// empty for real
			if (table[j] == null)
				return -2;
			else if(table[j].GetKey() == key){
				return j;
			}
		}
		return -2;
	}
	@Override
	public HashTableElement Find(long key) {
		int prob = getIndex(key);
		if (prob == -2)
			return null;
		else
			return table[prob];
	}

	// checks whether key exists in the rest of probing sequence
	public boolean keyExistsInProbingSeq(int i, HashTableElement hte){
		int j;
		for (int k = i+1; k < m; k++) {
			j = this.Hash(hte.GetKey(), k);
			// if null is found in the probing sequence, it means that we never got there in initialization
			if (this.table[j] == null)
				return false;
			else if (this.table[j].GetKey() == hte.GetKey()) {
				return true;
			}
		}
		return false;
	}

	@Override
	public void Insert(HashTableElement hte) throws TableIsFullException, KeyAlreadyExistsException {
		int j;
		for (int i = 0; i < m; i ++) {
			j = this.Hash(hte.GetKey(), i);
			if (this.table[j] == null) {
				this.table[j] = hte;
				return;
			} else if (this.table[j].GetKey() == -1) {
				if (this.keyExistsInProbingSeq(i, hte)) {
					throw new KeyAlreadyExistsException(hte);
				} else {
					this.table[j] = hte;
					return;
				}
			} else if (this.table[j].GetKey() == hte.GetKey()) {
				throw new KeyAlreadyExistsException(hte);
			}
		}
		throw new TableIsFullException(hte);
	}

	@Override
	public void Delete(long key) throws KeyDoesntExistException {
		int prob = getIndex(key);
		if (prob == -2)
			throw new KeyDoesntExistException(key);
		else {
			// negative key is not possible, so it represents deleted element
			HashTableElement virtualElement = new HashTableElement(-1, -1);
			table[prob] = virtualElement;
			return;
		}
	}
	
	/**
	 * 
	 * @param x - the key to hash
	 * @param i - the index in the probing sequence
	 * @return the index into the hash table to place the key x
	 */
	public abstract int Hash(long x, int i);
}
