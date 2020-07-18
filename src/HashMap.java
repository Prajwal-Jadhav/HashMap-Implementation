import java.util.ArrayList;

public class HashMap<K, V> {
  private ArrayList<Pair<K, V>>[] values;
  private int totalNumberOfPairs;

  public HashMap() {
    this.values = new ArrayList[32];
    this.totalNumberOfPairs = 0;
  }

  public V get(K key) {
    int indexOfKey = createIndex(key);
    if (this.values[indexOfKey] == null)
      return null;

    ArrayList<Pair<K, V>> arrayAtIndex = this.values[indexOfKey];

    for (int i = 0; i < arrayAtIndex.size(); i++) {
      Pair<K, V> pair = arrayAtIndex.get(i);

      if (key.equals(pair.getKey())) {
        return pair.getValue();
      }
    }

    return null;
  }

  private int createIndex(K key) {
    return Math.abs(key.hashCode() % this.values.length);
  }
}