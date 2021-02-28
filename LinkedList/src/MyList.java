
public interface MyList<E> {
	  E[] toArray();
	  void transformAll(MyTransformer mt);
	  void chooseAll(MyChooser mc);
	  boolean isEmpty();
}