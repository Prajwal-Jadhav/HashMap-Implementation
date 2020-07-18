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

    ArrayList<Pair<K, V>> listAtIndex = this.values[indexOfKey];

    for (int i = 0; i < listAtIndex.size(); i++) {
      Pair<K, V> pair = listAtIndex.get(i);

      if (key.equals(pair.getKey())) {
        return pair.getValue();
      }
    }

    return null;
  }

  public void put(K key, V value) {
    int indexOfKey = createIndex(key);

    if (this.values[indexOfKey] == null) {
      this.values[indexOfKey] = new ArrayList<>();
    }

    ArrayList<Pair<K, V>> listAtIndex = this.values[indexOfKey];

    int index = getIndexOfKey(key, listAtIndex);

    if (index < 0) {
      listAtIndex.add(new Pair<K, V>(key, value));
      this.totalNumberOfPairs++;
    } else {
      listAtIndex.get(index).setValue(value);
    }

    if (1.0 * this.totalNumberOfPairs / this.values.length >= 0.75) {
      grow();
    }
  }

  public V remove(K key) {
    int indexOfList = createIndex(key);

    if (this.values[indexOfList] == null || this.values[indexOfList].size() == 0) {
      return null;
    }

    ArrayList<Pair<K, V>> listAtIndex = this.values[indexOfList];

    int indexOfKey = getIndexOfKey(key, listAtIndex);

    if (indexOfKey < 0) {
      return null;
    }

    Pair<K, V> pair = listAtIndex.get(indexOfKey);
    listAtIndex.remove(indexOfKey);
    return pair.getValue();
  }

  private int createIndex(K key) {
    return Math.abs(key.hashCode() % this.values.length);
  }

  private int getIndexOfKey(K key, ArrayList<Pair<K, V>> list) {
    for (int i = 0; i < list.size(); i++) {
      if (key.equals(list.get(i).getKey())) {
        return i;
      }
    }
    return -1;
  }

  private void grow() {
    ArrayList<Pair<K, V>>[] newArray = (ArrayList<Pair<K, V>>[]) new ArrayList[this.values.length * 2];

    for (int i = 0; i < this.values.length; i++) {
      copyToNewArray(newArray, i);
    }

    this.values = newArray;
  }

  private void copyToNewArray(ArrayList<Pair<K, V>>[] newArray, int atIndex) {
    for (int i = 0; i < this.values[atIndex].size(); i++) {
      Pair<K, V> pair = this.values[atIndex].get(i);

      int hashCode = Math.abs(pair.getKey().hashCode() % newArray.length);

      if (newArray[hashCode] == null) {
        newArray[hashCode] = new ArrayList<>();
      }

      newArray[hashCode].add(pair);
    }
  }
}