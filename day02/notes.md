# Day 2 — Java Methods, Arrays, Memory Model & DSA

> **Purpose:** These notes are meant for future revision and interview preparation.
> Since you're moving from **C++ → Java**, the important differences from C++ are highlighted.

---

# 1. Methods in Java ⭐⭐⭐

A **method** is a block of code designed to perform a particular task.

### Basic syntax

```java
returnType methodName(parameters) {
    // body
}
```

Example:

```java
static int add(int a, int b) {
    return a + b;
}
```

Calling it:

```java
int result = add(10, 20);
```

Output:

```text
30
```

### Important terminology

```text
int add(int a, int b)
    ↑      ↑
parameter parameter

add(10, 20)
    ↑   ↑
arguments
```

**Parameter** → variable declared in the method.

**Argument** → actual value passed when calling the method.

---

# 2. `void` Methods

If a method does not return a value, use `void`.

```java
static void greet() {
    System.out.println("Hello");
}
```

Call:

```java
greet();
```

A `void` method cannot return a value:

```java
static void test() {
    return 10;   // ❌
}
```

But:

```java
static void test() {
    return;      // ✅ allowed
}
```

`return;` simply exits the method.

---

# 3. Methods With Return Values

Example:

```java
static int square(int n) {
    return n * n;
}
```

Usage:

```java
int ans = square(5);
System.out.println(ans);
```

Output:

```text
25
```

### Interview point

The return type must match the returned value.

```java
static int test() {
    return 10;       // ✅
}
```

```java
static double test() {
    return 10.5;     // ✅
}
```

---

# 4. `static` Methods ⭐⭐⭐

A static method belongs to the **class**, not to a particular object.

```java
class Calculator {

    static int add(int a, int b) {
        return a + b;
    }
}
```

Call:

```java
Calculator.add(10, 20);
```

No object is required.

This is why:

```java
public static void main(String[] args)
```

can be called by the JVM without creating an object first.

### C++ comparison

C++:

```cpp
static int add(int a, int b)
```

Java:

```java
static int add(int a, int b)
```

The important Java rule to remember:

> A static method cannot directly access instance members.

Example:

```java
class Student {

    String name;

    static void display() {
        System.out.println(name);  // ❌
    }
}
```

Why?

Because `name` belongs to an object, while the static method belongs to the class.

---

# 5. Java Passes Arguments by Value ⭐⭐⭐

This is a common interview question.

Java is **always pass-by-value**.

For primitive types:

```java
static void change(int x) {
    x = 100;
}

int a = 10;
change(a);

System.out.println(a);
```

Output:

```text
10
```

The method receives a copy of `a`.

### What about objects?

Java still passes **by value**.

The value being copied is the **reference**.

Conceptually:

```text
Original reference
       ↓
    Object

Method receives a copy of that reference
       ↓
    Same Object
```

Therefore, a method can modify the object's fields, but reassigning the copied reference does not change the caller's reference.

### Interview answer

> Java is strictly pass-by-value. For objects, the value passed is a copy of the object's reference.

---

# 6. Arrays in Java ⭐⭐⭐

An array stores multiple values of the **same type**.

### Declaration

```java
int[] arr;
```

This only declares the reference.

### Creation

```java
arr = new int[5];
```

This creates an array capable of storing 5 integers.

### Both together

```java
int[] arr = new int[5];
```

---

# 7. Array Indexing

Java arrays use **0-based indexing**.

For:

```java
int[] arr = {10, 20, 30, 40, 50};
```

Memory concept:

```text
Index:   0   1   2   3   4
Value:  10  20  30  40  50
```

Access:

```java
System.out.println(arr[0]);  // 10
System.out.println(arr[3]);  // 40
```

Last element:

```java
arr[arr.length - 1]
```

---

# 8. Array Initialization

### Using `new`

```java
int[] arr = new int[5];
```

For an integer array, elements initially contain:

```text
0 0 0 0 0
```

Other default values:

```text
int      → 0
double   → 0.0
boolean  → false
reference → null
```

### Direct initialization

```java
int[] arr = {10, 20, 30, 40};
```

---

# 9. Array Length ⭐⭐

Java arrays use:

```java
arr.length
```

Example:

```java
int[] arr = {10, 20, 30, 40};

System.out.println(arr.length);
```

Output:

```text
4
```

### Important interview trap

For arrays:

```java
arr.length
```

For Strings:

```java
str.length()
```

For collections such as `ArrayList`:

```java
list.size()
```

Remember:

```text
Array      → length
String     → length()
ArrayList  → size()
```

---

# 10. Traversing an Array

### Normal `for` loop

```java
for (int i = 0; i < arr.length; i++) {
    System.out.println(arr[i]);
}
```

This is extremely important for DSA because you usually need the index.

### Enhanced `for` loop

```java
for (int num : arr) {
    System.out.println(num);
}
```

This directly gives each element.

Use enhanced `for` when you don't need the index.

---

# 11. Important Array Difference From C++

Java arrays know their own length.

```java
arr.length
```

You don't need:

```cpp
sizeof(arr) / sizeof(arr[0])
```

Also, Java arrays are objects created using `new`.

```java
int[] arr = new int[5];
```

The array itself exists on the heap, while the local reference variable can exist in the current stack frame.

---

# 12. Array Bounds ⭐⭐⭐

Valid indexes for an array of size `n` are:

```text
0 → n - 1
```

Example:

```java
int[] arr = new int[5];
```

Valid:

```text
0, 1, 2, 3, 4
```

Invalid:

```java
arr[5]
```

This causes:

```text
ArrayIndexOutOfBoundsException
```

### DSA habit

Always remember:

```java
i < arr.length
```

not:

```java
i <= arr.length
```

---

# 13. Memory Model — Stack vs Heap ⭐⭐⭐

This is one of the most important concepts for Java interviews.

Consider:

```java
int x = 10;

Student s = new Student();
```

Conceptually:

```text
STACK
┌───────────────┐
│ x = 10        │
│ s ────────────┼──────┐
└───────────────┘      │
                       ↓
                    HEAP
                ┌─────────────┐
                │ Student     │
                │ object      │
                └─────────────┘
```

### Stack

Used for things such as:

* Method call frames
* Local variables
* Primitive local values
* References held by local variables

### Heap

Used for dynamically created objects and arrays.

Example:

```java
Student s = new Student();
```

The `Student` object is created on the heap.

---

# 14. Reference Variables ⭐⭐⭐

This is extremely important when coming from C++.

```java
Student s = new Student();
```

Do **not** think:

```text
s = object
```

Think:

```text
s = reference to object
```

Conceptually:

```text
s ───────────────→ Student object
```

`new Student()` creates the object.

`s` stores a reference to that object.

---

# 15. Multiple References to One Object

Consider:

```java
Student s1 = new Student();
Student s2 = s1;
```

Now:

```text
s1 ──────┐
         ↓
      Student
         ↑
s2 ──────┘
```

There is **one object**, but two references pointing to it.

Therefore:

```java
s1.name = "Anurag";
System.out.println(s2.name);
```

will also show:

```text
Anurag
```

because both references refer to the same object.

---

# 16. `new` Keyword ⭐⭐⭐

`new` is used to create objects and arrays.

Object:

```java
Student s = new Student();
```

Array:

```java
int[] arr = new int[5];
```

Conceptually:

```text
new Student()
      ↓
creates object on heap

new int[5]
      ↓
creates array on heap
```

---

# 17. Garbage Collection ⭐⭐

Java automatically manages memory.

When an object is no longer reachable, it becomes eligible for garbage collection.

Example:

```java
Student s = new Student();

s = null;
```

The previously referenced object may now become eligible for garbage collection if no other reference points to it.

Unlike C++:

```cpp
delete obj;
```

Java normally does not require manual object deletion.

### Important

Do **not** say:

> Garbage collection immediately deletes the object.

Correct:

> The object becomes eligible for garbage collection when it is no longer reachable. The JVM determines when garbage collection actually occurs.

---

# 18. `null`

A reference can contain `null`.

```java
Student s = null;
```

This means:

> `s` currently does not refer to any object.

If you do:

```java
s.name = "Anurag";
```

you get:

```text
NullPointerException
```

### Interview question

**Is `null` an object?**

No.

It represents the absence of an object reference.

---

# 19. DSA Pattern 1 — Find Largest Element ⭐⭐⭐

Given:

```text
[10, 4, 25, 7, 15]
```

Approach:

1. Assume first element is largest.
2. Traverse the array.
3. Compare each element with `largest`.
4. Update when a larger element is found.

```java
static int largest(int[] arr) {

    int largest = arr[0];

    for (int i = 1; i < arr.length; i++) {

        if (arr[i] > largest) {
            largest = arr[i];
        }
    }

    return largest;
}
```

### Complexity

```text
Time  → O(n)
Space → O(1)
```

### Interview explanation

> I initialize the maximum with the first element and scan the remaining elements once. Whenever I find a larger value, I update the maximum.

---

# 20. DSA Pattern 2 — Second Largest ⭐⭐⭐

Basic approach:

Maintain:

```text
largest
secondLargest
```

Example:

```text
[10, 5, 20, 8, 15]
```

Final:

```text
largest       = 20
secondLargest = 15
```

A common efficient approach is one pass:

```java
static int secondLargest(int[] arr) {

    int largest = Integer.MIN_VALUE;
    int secondLargest = Integer.MIN_VALUE;

    for (int num : arr) {

        if (num > largest) {
            secondLargest = largest;
            largest = num;
        }
        else if (num > secondLargest && num != largest) {
            secondLargest = num;
        }
    }

    return secondLargest;
}
```

### Complexity

```text
Time  → O(n)
Space → O(1)
```

### Interview trap

Clarify whether **duplicate values count**.

For:

```text
[10, 20, 20, 5]
```

If the question asks for the **second distinct largest**, answer is:

```text
10
```

If duplicates are allowed, the interpretation can differ.

Never blindly assume. Clarify the requirement.

---

# 21. DSA Pattern 3 — Reverse an Array ⭐⭐⭐

Example:

```text
Before:
[1, 2, 3, 4, 5]

After:
[5, 4, 3, 2, 1]
```

Use the **two-pointer technique**.

```java
static void reverse(int[] arr) {

    int left = 0;
    int right = arr.length - 1;

    while (left < right) {

        int temp = arr[left];
        arr[left] = arr[right];
        arr[right] = temp;

        left++;
        right--;
    }
}
```

Concept:

```text
left →             ← right

[1, 2, 3, 4, 5]
 ↑             ↑

swap

[5, 2, 3, 4, 1]

   ↑         ↑

...

[5, 4, 3, 2, 1]
```

### Complexity

```text
Time  → O(n)
Space → O(1)
```

This is an important interview pattern because the same **two-pointer idea** appears in many array and string problems.

---

# 22. DSA Pattern 4 — Move Zeroes ⭐⭐⭐

Example:

```text
Input:
[0, 1, 0, 3, 12]

Output:
[1, 3, 12, 0, 0]
```

The usual requirement is:

* Move all zeroes to the end.
* Preserve the relative order of non-zero elements.
* Do it in-place if requested.

A two-pointer approach:

```java
static void moveZeroes(int[] arr) {

    int index = 0;

    for (int i = 0; i < arr.length; i++) {

        if (arr[i] != 0) {

            int temp = arr[index];
            arr[index] = arr[i];
            arr[i] = temp;

            index++;
        }
    }
}
```

### Complexity

```text
Time  → O(n)
Space → O(1)
```

### Pattern to remember

```text
index = position where next valid element should go
```

This idea appears frequently in:

* Move zeroes
* Remove duplicates
* Partitioning
* Filtering arrays in-place

---

# 23. Important Basic Problems From Day 2

You also practiced these basic Java problems:

### Armstrong Number

For a number, calculate the required digit powers and compare the result with the original number.

Important concepts:

```java
%
/
while
```

### Check Prime

Basic approach:

```java
for (int i = 2; i < n; i++) {
    if (n % i == 0) {
        // not prime
    }
}
```

For interviews, later improve this to checking only up to:

```text
√n
```

because if `n` has a factor larger than √n, it must have a corresponding factor smaller than √n.

### Factorial

```text
n! = n × (n-1) × ... × 1
```

Example:

```text
5! = 120
```

Iterative approach:

```java
int fact = 1;

for (int i = 1; i <= n; i++) {
    fact *= i;
}
```

### Celsius → Fahrenheit

Formula:

```text
F = (C × 9/5) + 32
```

Be careful with integer division.

Use:

```java
double fahrenheit = (celsius * 9.0 / 5) + 32;
```

rather than accidentally doing:

```java
celsius * 9 / 5
```

when working with integer values and expecting a decimal result.

---

# 24. Java Array Interview Traps ⭐⭐⭐

### Trap 1 — `length`

Correct:

```java
arr.length
```

Wrong:

```java
arr.length()
```

---

### Trap 2 — Last index

For size `n`:

```text
last index = n - 1
```

---

### Trap 3 — Array size cannot be changed

After:

```java
int[] arr = new int[5];
```

the array has a fixed length of 5.

You cannot resize it directly.

For dynamic sizing, Java provides collections such as:

```java
ArrayList<Integer>
```

You will study collections later.

---

### Trap 4 — Arrays are objects

Even primitive arrays are objects in Java.

```java
int[] arr = new int[5];
```

The array is an object managed by the JVM.

---

### Trap 5 — Array assignment

```java
int[] a = {1, 2, 3};
int[] b = a;
```

This does **not** create a copy.

Both references point to the same array.

```text
a ─────┐
       ↓
    [1,2,3]
       ↑
b ─────┘
```

Therefore:

```java
b[0] = 100;
```

also changes:

```java
a[0]
```

to `100`.

---

# 25. C++ → Java Quick Comparison

| Concept             | C++                          | Java                     |
| ------------------- | ---------------------------- | ------------------------ |
| Array declaration   | `int arr[5]`                 | `int[] arr = new int[5]` |
| Array size          | `sizeof` tricks / known size | `arr.length`             |
| String              | `string`                     | `String`                 |
| Object creation     | `new` / automatic objects    | `new` for normal objects |
| Manual delete       | `delete`                     | No manual deletion       |
| Memory management   | Manual + RAII                | Garbage collection       |
| Reference concept   | References/pointers          | Object references        |
| Range loop          | `for(int x : arr)`           | `for(int x : arr)`       |
| Null reference      | `nullptr`                    | `null`                   |
| Boolean             | `bool`                       | `boolean`                |
| Method              | function/member function     | method                   |
| Class static member | `static`                     | `static`                 |

---

# 26. Complexity You Should Already Know ⭐⭐⭐

For the Day 2 array problems:

| Operation           | Time |
| ------------------- | ---: |
| Traverse array      | O(n) |
| Find largest        | O(n) |
| Find second largest | O(n) |
| Reverse array       | O(n) |
| Move zeroes         | O(n) |
| Access `arr[i]`     | O(1) |

### Why is array access O(1)?

Because the array provides direct indexed access.

Conceptually:

```text
address = base + index × element_size
```

So accessing:

```java
arr[500]
```

doesn't require checking elements `0` through `499`.

---

# 27. Interview Questions — Day 2 ⭐⭐⭐

You should eventually answer these without notes.

### Methods

1. What is a method in Java?
2. What is the difference between a parameter and an argument?
3. What is the difference between a `void` method and a method with a return value?
4. What does `static` mean for a method?
5. Why can a static method not directly access instance variables?
6. Is Java pass-by-value or pass-by-reference?
7. How are objects passed to methods in Java?

### Arrays

8. How do you declare an array in Java?
9. What is the difference between declaration and creation?
10. What is the first index of a Java array?
11. How do you find the length of an array?
12. What is the difference between `arr.length`, `String.length()` and `ArrayList.size()`?
13. What happens if you access an invalid array index?
14. Can you resize a Java array?
15. Are Java arrays objects?
16. What happens when you write `int[] b = a`?
17. How would you reverse an array in-place?
18. How would you move all zeroes to the end of an array?

### Memory

19. What is the difference between stack and heap?
20. Where is an object created using `new`?
21. What is a reference variable?
22. What happens when two references point to the same object?
23. What is `null`?
24. What is garbage collection?
25. When does an object become eligible for garbage collection?

### Complexity

26. What is the time complexity of accessing an array element?
27. What is the time complexity of finding the largest element?
28. Can you find the largest and second largest elements in one pass?
29. What is the space complexity of reversing an array in-place?

---

# 28. One-Minute Revision Sheet

Before an interview, remember this:

```text
METHOD
→ reusable block of code
→ parameters = variables in declaration
→ arguments = values passed
→ static = belongs to class
→ Java is always pass-by-value

ARRAY
→ same type of elements
→ 0-based indexing
→ fixed size
→ arr.length
→ access = O(1)
→ arrays are objects

MEMORY
→ stack = method frames + local variables/references
→ heap = objects + arrays
→ reference points to an object
→ new creates object/array
→ null = no object referenced
→ unreachable objects become eligible for GC

DSA
→ Largest              O(n), O(1)
→ Second Largest      O(n), O(1)
→ Reverse             O(n), O(1)
→ Move Zeroes         O(n), O(1)

PATTERNS
→ single traversal
→ two pointers
→ in-place modification
→ maintain variables representing current answer
```

---

# 29. What You Must NOT Forget

These are the Day 2 concepts most likely to become fuzzy after a few weeks:

1. **Java is always pass-by-value.**
2. **Object variables are references, not the objects themselves.**
3. `new` creates objects/arrays.
4. Arrays are objects and have a fixed length.
5. `arr.length` vs `String.length()` vs `ArrayList.size()`.
6. `int[] b = a` creates another reference, **not another array**.
7. Stack vs heap at a conceptual level.
8. `null` means no object is currently referenced.
9. Garbage collection does not mean immediate deletion.
10. Array indexing starts at `0`.
11. `arr.length - 1` is the last valid index.
12. Array access is **O(1)**.
13. Largest/second-largest can be solved in **one pass**.
14. Reverse array → **two pointers**.
15. Move zeroes → **in-place + pointer/index technique**.
16. Always be able to explain the **time and space complexity** of your solution.

---

# Final Day 2 Interview Standard

You have actually understood Day 2 if you can open a blank Java file and, without notes:

* Write a method with parameters and a return value.
* Explain `static`.
* Explain Java's pass-by-value behavior.
* Create and traverse an array.
* Explain stack vs heap.
* Explain what a reference variable is.
* Reverse an array in-place.
* Find the largest and second-largest element in one pass.
* Move zeroes to the end in-place.
* Give the time and space complexity of each solution.

**Do not memorize the code. Memorize the pattern and be able to reconstruct the code.**

That is much closer to what an actual DSA interview tests.
