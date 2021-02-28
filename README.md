CREDIT: to the CSE 12 Faculty and Tutoring Team for providing the interfaces
for this project and building much of this README

JAVA VERSION - (openjdk-15)

---
Lists - Linked List Implementations and JUnit Testing
---
## Code Layout

- `MyList.java` – Created by CSE 12 Tutors
- `MyTransformer.java` – Created by CSE 12 Tutors
- `MyChooser.java` – Created by CSE 12 Tutors
- `ArrayGL.java` – Developed by myself and CSE 12 Tutors
- `LinkedGL.java` – Developed by myself
- `TestLists.java` – Developed by myself
- `Choosers.java` – Developed by myself
- `Transformers.java` – Developed by myself


## Project Overview
The `MyList` interface is implemented in `ArrayGL.java` and in `LinkedGL.java`: 

```
public interface MyList<E>{
    E[] toArray();
    void transformAll(MyTransformer mt);
    void chooseAll(MyChooser mc);
    boolean isEmpty();
}
```

The constructor for the lists will take a generic array (`E[]`) as a parameter. 

- `LinkedGL(E[] contents)`
- `isEmpty` for both types of list
- `toArray` for both types of list
- `transformAll` for both types of list
- `chooseAll` for both types of list
- Implementations of the `MyTransformer` and `MyChooser`
  interfaces

The interfaces `MyTransformer` and `MyChooser` are defined as:

```
public interface MyTransformer<E>{
    E transformElement(E e);
}

public interface MyChooser<E>{
    boolean chooseElement(E e);
}
```

#### `public LinkedGL(E[] contents)`

*Constructor* that creates a new `LinkedGL` with its elements from
`contents` in the same order. For example, the following constructor call:

```
Integer[] input = {1, 2, 3};
LinkedGL<Integer> list = new LinkedGL<Integer>(input);
```

creates a new Linked Integer list with contents `{1, 2, 3}`.

#### `public E[] toArray()`

Returns the contents of the list as a new array, with *shallow copy* of the 
elements in the same order they appear in the list.

#### `public boolean isEmpty()`

Returns `true` if the list has no elements, `false` otherwise.

#### `public void transformAll(MyTransformer mt)`

Changes the contents of the list according to the provided `MyTransformer`.
`mt` is a concrete class that implements `MyTransformer`. It has a method called 
`transformElement(E e)`, which takes an element as an argument and returns the 
transformed element. Here, we apply the 
`transformElement` method of the `mt` class to each element in the list, getting
the transformed element and replacing the orginal one.
For example, consider the provided `UpperCaseTransformer` that 
implements `MyChooser`, which transforms a string into uppercase. If we 
construct a list like:

```
String[] contents = {"a", "b", "c"};
ArrayGL<String> agl = new ArrayGL<String>(contents);
agl.transformAll(new UpperCaseTransformer());
```

then the contents of the list after is `{"A", "B", "C"}`.

When the element is `null`, it is not transformed. For example,

```
String[] contents = {"a", "b", null};
ArrayGL<String> agl = new ArrayGL<String>(contents);
agl.transformAll(new UpperCaseTransformer());
```

then the contents of the list after is `{"A", "B", null}`.



#### `public void chooseAll(MyChooser mc)`

Changes the list to contain only elements selected by the `MyChooser`.
`mc` is a concrete class that implements `MyChooser`. It has a method called 
`chooseElement(E e)`, which takes an element as an argument and checks if this
element should be put into the new list. It returns `true`, if we should choose 
this element. Otherwise, it returns `false`.
The elements remain in the same order after `chooseAll` is called. 
For example, consider the provided `LongWordChooser` that implements `MyChooser`,
which chooses a string whose length is more than 5. If we construct a list like:

```
String[] contents = {"longword", "longerword", "short"};
ArrayGL<String> agl = new ArrayGL(contents);
agl.chooseAll(new LongWordChooser());
```

then the contents of the list after is `{"longword",
"longerword"}`.

When an element is `null`, it should not be chosen. For example, 

```
String[] contents = {"longword", null, "short"};
ArrayGL<String> agl = new ArrayGL(contents);
agl.chooseAll(new LongWordChooser());
```
then the contents of the list after is `{"longword"}`.




### Part 2: Implementations of `MyChooser` and `MyTransformer`

We have 4 implementations of **each** of these interfaces which implement 
`MyChooser` in `Choosers.java` and implement `MyTransformer` in 
`Transformers.java`.

"Choosers" overwrite `public boolean chooseElement(E e)` of `MyChooser`.
We put a "chooser" into `chooseAll(MyChooser mc)`'s paramter by 
`agl.chooseAll(new LongWordChooser())`, and access the `chooseElement` method by
`mc.chooseElement(element)`. Similarly, "transformers" overwrite 
`E transformElement(E e)`.

## Testing

Here, we use JUnit testing to ensure the thoroughness and correctness of 
our implementations of the given interfaces. Through this testing, we can
ensure that buggy implementations are found and correct implementations are
actually correct.
