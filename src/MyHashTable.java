public class MyHashTable<K, V> {
    private class HashNode<K, V> {
        private K key;
        private V value;
        private HashNode<K, V> next;
        private HashNode(K key, V value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public String toString() {
            return "{" + key + " " + value + "}";
        }
    }

private HashNode<K, V>[] chainArray;
private int M = 11;
private int size;
public MyHashTable() {
    chainArray = new HashNode[M];
}
public MyHashTable(int M) {
    this.M = M;
    this.chainArray = new HashNode[M];
    this.size = 0;
}
private int hash(K key) {
    int getIndex = key.hashCode() % chainArray.length;
    if (getIndex > 0 || getIndex == 0)
        return getIndex;
    else
        return getIndex * (-1);
}
public void put(K key, V value) {int index = hash(key);
    HashNode<K, V> newNode = new HashNode<>(key, value);
    if (chainArray[index] == null) {
        chainArray[index] = newNode;
    } else {
        HashNode<K, V> current = chainArray[index];
        while (current.next != null) {
            if (current.key.equals(key)) {
                current.value = value; // Update value if key already exists
                return;
            }
            current = current.next;
        }
        current.next = newNode;
    }
    size++;}
public V get(K key) {
    int index = hash(key);
    HashNode<K, V> current = chainArray[index];
    while (current != null) {
        if (current.key.equals(key)) {
            return current.value;
        }
        current = current.next;
    }
    return null;
}
public V remove(K key) {
    int index = hash(key);
    HashNode<K, V> prev = null;
    HashNode<K, V> current = chainArray[index];
    while (current != null) {
        if (current.key.equals(key)) {
            if (prev == null) {
                chainArray[index] = current.next;
            } else {
                prev.next = current.next;
            }
            size--;
            return current.value;
        }
        prev = current;
        current = current.next;
    }
    return null;
}
public boolean contains(V value) {
    for (int i = 0; i < M; i++) {
    HashNode<K, V> current = chainArray[i];
    while (current != null) {
        if (current.value.equals(value)) {
            return true;
        }
        current = current.next;
    }
}
    return false;
}
public K getKey(V value) {
    for (int i = 0; i < M; i++) {
        HashNode<K, V> current = chainArray[i];
        while (current != null) {
            if (current.value.equals(value)) {
                return current.key;
            }
            current = current.next;
        }
    }
    return null;
}

}