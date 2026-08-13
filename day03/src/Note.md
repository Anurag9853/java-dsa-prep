

> **Goal:** Build a solid Java OOP foundation for DSA, backend development, and interviews.
>
> Since you're moving from C++, focus especially on **references, `this`, static vs instance, constructors, and encapsulation**. These are areas where Java's behavior and terminology can trip you up.

The original Day 3 plan covered classes/objects, constructors, `this`, static vs instance members, encapsulation, access modifiers, getters/setters, and basic array DSA.

---

# 1. Class vs Object ⭐⭐⭐

The fundamental mental model:

```text
Class
  ↓
Blueprint / definition
  ↓
Object
  ↓
Actual instance
```

A class contains:

```text
Fields        → data/state
Methods       → behavior
Constructors  → initialize objects
```

### Example

```java
class Student {
    String name;
    int rollNo;
    double marks;
}
```

`Student` is a **class**.

It defines what information a Student object should contain.

Now create objects:

```java
Student s1 = new Student();
Student s2 = new Student();
```

Now there are two separate Student objects.

```java
s1.name = "Anurag";
s1.rollNo = 101;

s2.name = "Rahul";
s2.rollNo = 102;
```

Conceptually:

```text
Student class
      │
      ├──────────────┐
      ↓              ↓
     s1              s2
  Student          Student
   object            object
```

### Remember

**Class** → describes what an object should look like.

**Object** → actual instance of that class.

This distinction is one of the core Day 3 concepts.

---

# 2. C++ → Java: Objects and References ⭐⭐⭐

In C++, you may be used to thinking about objects, pointers, and references separately.

In Java:

```java
Student s = new Student();
```

Conceptually:

```text
s
↓
reference
↓
Student object
```

`new Student()` creates the object.

`s` is a **reference variable** referring to that object.

### Important

Do NOT mentally think:

```text
s = object
```

Think:

```text
s = reference → object
```

This becomes extremely important when you study:

* Method arguments
* Object assignment
* `null`
* Garbage collection
* Collections
* Java interview questions

---

# 3. What Does `new` Do? ⭐⭐⭐

Example:

```java
Student s1 = new Student();
```

Conceptually:

```text
new Student()
      ↓
creates Student object
      ↓
heap
      ↑
      │
s1 ───┘
```

So `new` is used to create objects.

It can also create arrays:

```java
int[] arr = new int[5];
```

The original Day 3 material specifically treats `new` as a key concept connecting object creation with Java's memory model.

---

# 4. Constructors ⭐⭐⭐

A **constructor** is used to initialize an object when it is created.

Example:

```java
class Student {

    Student() {
        System.out.println("Student created");
    }
}
```

When:

```java
Student s = new Student();
```

the constructor runs.

Output:

```text
Student created
```

---

# 5. Constructor Rules ⭐⭐⭐

A constructor:

1. Has the **same name as the class**.
2. Has **no return type**.
3. Runs when an object is created.
4. Is used to initialize object state.

Example:

```java
class Student {

    Student() {
        System.out.println("Constructor called");
    }
}
```

### Very important trap

This:

```java
void Student() {
}
```

is **not** a constructor.

It is a method named `Student`.

A constructor has **no return type**, not even `void`.

---

# 6. Constructor vs Method ⭐⭐⭐

| Constructor                           | Method                    |
| ------------------------------------- | ------------------------- |
| Same name as class                    | Has its own name          |
| No return type                        | Has return type or `void` |
| Runs during object creation           | Called explicitly         |
| Initializes object                    | Performs behavior         |
| Cannot be called like a normal method | Can be called normally    |

### Interview answer

> A constructor initializes an object during its creation, while a method represents behavior and is called explicitly.

This distinction was explicitly included in the Day 3 interview section.

---

# 7. Default Constructor ⭐⭐⭐

If you don't write **any constructor**, Java provides a default no-argument constructor.

Example:

```java
class Student {
    String name;
    int rollNo;
}
```

You can write:

```java
Student s = new Student();
```

because Java provides a default constructor.

Fields receive their default values:

```text
String  → null
int     → 0
double  → 0.0
boolean → false
```

---

# 8. Important Default Constructor Trap ⭐⭐⭐

The compiler gives you the default no-argument constructor **only when you don't define a constructor yourself**.

Suppose:

```java
class Student {

    Student(String name) {
        this.name = name;
    }

    String name;
}
```

Now:

```java
Student s = new Student();
```

❌ Compilation error.

Why?

Because you defined a constructor, so Java does not automatically provide the no-argument constructor.

You need:

```java
Student s = new Student("Anurag");
```

or explicitly define:

```java
Student() {
}
```

This is a common interview and coding mistake.

---

# 9. Parameterized Constructor ⭐⭐⭐

Instead of:

```java
Student s = new Student();

s.name = "Anurag";
s.rollNo = 101;
s.marks = 85;
```

use a parameterized constructor:

```java
class Student {

    String name;
    int rollNo;
    double marks;

    Student(String name, int rollNo, double marks) {
        this.name = name;
        this.rollNo = rollNo;
        this.marks = marks;
    }
}
```

Now:

```java
Student s1 = new Student("Anurag", 101, 85);
Student s2 = new Student("Rahul", 102, 91);
```

This creates properly initialized objects immediately.

---

# 10. Constructor Overloading ⭐⭐

A class can have multiple constructors as long as their **parameter lists differ**.

```java
class Student {

    String name;
    int rollNo;

    Student() {
        name = "Unknown";
        rollNo = 0;
    }

    Student(String name) {
        this.name = name;
        rollNo = 0;
    }

    Student(String name, int rollNo) {
        this.name = name;
        this.rollNo = rollNo;
    }
}
```

All are valid:

```java
new Student();

new Student("Anurag");

new Student("Anurag", 101);
```

This is **constructor overloading**.

### Interview point

Overloading means:

> Same constructor name, different parameter list.

Changing only the return type doesn't apply because constructors don't have return types.

---

# 11. `this` Keyword ⭐⭐⭐

This is one of the most important Day 3 topics.

Consider:

```java
class Student {

    String name;

    Student(String name) {
        name = name;
    }
}
```

There are two `name`s:

```text
field     → name
parameter → name
```

Inside the constructor, the parameter is the nearest variable.

So:

```java
name = name;
```

effectively assigns the parameter to itself.

The object's field isn't initialized with the parameter.

---

# 12. `this.name`

Use:

```java
this.name = name;
```

Now:

```text
this.name
   ↓
current object's field

name
   ↓
constructor parameter
```

Therefore:

```java
this.name = name;
```

means:

> Put the constructor parameter `name` into the current object's `name` field.

---

# 13. What Exactly Is `this`? ⭐⭐⭐

`this` refers to the **current object**.

Example:

```java
Student s1 = new Student("Anurag");
```

Inside the constructor:

```text
this → s1
```

For:

```java
Student s2 = new Student("Rahul");
```

inside that constructor:

```text
this → s2
```

So:

```text
s1 constructor → this = s1
s2 constructor → this = s2
```

This is the mental model you should remember.

---

# 14. `this(...)` — Constructor Chaining ⭐⭐⭐

`this` can also call another constructor in the same class.

Example:

```java
class Student {

    String name;
    int rollNo;

    Student() {
        this("Unknown", 0);
    }

    Student(String name, int rollNo) {
        this.name = name;
        this.rollNo = rollNo;
    }
}
```

When:

```java
Student s = new Student();
```

Java calls:

```text
Student()
   ↓
this("Unknown", 0)
   ↓
Student(String, int)
```

This is called **constructor chaining**.

### Critical rule

`this(...)` must be the **first statement** inside a constructor.

Correct:

```java
Student() {
    this("Unknown", 0);
}
```

Incorrect:

```java
Student() {
    System.out.println("Hello");
    this("Unknown", 0); // ❌
}
```

---

# 15. Instance Variables ⭐⭐⭐

Consider:

```java
class Student {

    String name;
    int rollNo;
}
```

`name` and `rollNo` are **instance variables**.

Every object gets its own instance data.

```java
Student s1 = new Student();
Student s2 = new Student();

s1.name = "Anurag";
s2.name = "Rahul";
```

Conceptually:

```text
s1 → name = Anurag

s2 → name = Rahul
```

The values are independent.

---

# 16. Static Variables ⭐⭐⭐

Now:

```java
class Student {

    String name;
    int rollNo;

    static String university = "LPU";
}
```

`university` is static.

Static members belong to the **class**, rather than each individual object.

Conceptually:

```text
Student class
     │
     └── university = "LPU"
              ↑
             s1
              ↑
             s2
```

Access it through the class:

```java
Student.university
```

rather than:

```java
s1.university
```

Although Java technically allows the latter, class access is clearer and should be preferred.

---

# 17. Instance Method vs Static Method ⭐⭐⭐

### Instance method

```java
class Student {

    void display() {
        System.out.println("Student");
    }
}
```

Call using an object:

```java
Student s = new Student();
s.display();
```

### Static method

```java
class Student {

    static void hello() {
        System.out.println("Hello");
    }
}
```

Call using the class:

```java
Student.hello();
```

### Remember

```text
Instance → object
Static   → class
```

---

# 18. Why Can't Static Methods Directly Access Instance Fields? ⭐⭐⭐

Example:

```java
class Student {

    String name;

    static void display() {
        System.out.println(name); // ❌
    }
}
```

Why?

Because:

```text
name → belongs to an object
display() → belongs to the class
```

A static method can be called without any object:

```java
Student.display();
```

But which object's `name` should it use?

There could be:

```text
Student 1 → Anurag
Student 2 → Rahul
Student 3 → Aman
```

There is no current object.

Therefore this doesn't work directly.

But this does:

```java
static void display(Student s) {
    System.out.println(s.name);
}
```

because you've explicitly supplied an object.

---

# 19. Why Is `main()` Static? ⭐⭐⭐

Recall:

```java
public static void main(String[] args)
```

The JVM needs an entry point to start the program.

At that moment, it doesn't need to create an object of your class first.

Therefore:

```text
JVM
 ↓
main()
```

can happen without:

```java
new Main();
```

That's why `main()` is static.

---

# 20. Encapsulation ⭐⭐⭐

This is an important interview topic.

A weak definition:

> Encapsulation means wrapping data and methods together.

That's incomplete.

Better:

> **Encapsulation means controlling access to an object's internal state so that it cannot be changed arbitrarily.**

The original Day 3 material emphasizes that the purpose is controlling access and protecting valid object state, not simply adding getters and setters.

---

# 21. Why Not Make Everything Public?

Bad design:

```java
class BankAccount {

    public double balance;
}
```

Someone could do:

```java
account.balance = -1000000;
```

There is no control.

Better:

```java
class BankAccount {

    private double balance;
}
```

Now outside code can't directly modify `balance`.

Instead, expose controlled operations:

```java
class BankAccount {

    private double balance;

    void deposit(double amount) {

        if (amount > 0) {
            balance += amount;
        }
    }
}
```

Now:

```java
account.deposit(5000);
```

can be allowed.

But:

```java
account.balance = -5000;
```

is impossible from outside the class.

That is meaningful encapsulation.

---

# 22. Access Modifiers ⭐⭐⭐

Java has four important access levels:

| Modifier    | Same Class | Same Package | Subclass Outside Package | Everywhere |
| ----------- | ---------: | -----------: | -----------------------: | ---------: |
| `private`   |          ✅ |            ❌ |                        ❌ |          ❌ |
| default     |          ✅ |            ✅ |                        ❌ |          ❌ |
| `protected` |          ✅ |            ✅ |                       ✅* |          ❌ |
| `public`    |          ✅ |            ✅ |                        ✅ |          ✅ |

### Easy memory trick

```text
private
   ↓
class only

default
   ↓
package

protected
   ↓
package + subclasses

public
   ↓
everywhere
```

---

# 23. Default Access

If you don't specify a modifier:

```java
class Student {

    int marks;
}
```

`marks` has **package-private/default access**.

It can be accessed by classes in the same package.

---

# 24. `private`

```java
private int marks;
```

Only code inside the same class can directly access it.

This is commonly used to protect object state.

---

# 25. `public`

```java
public int marks;
```

Accessible from anywhere where the containing class/member is accessible.

Use public access intentionally; don't make fields public just because it's convenient.

---

# 26. `protected`

`protected` is slightly more complicated than the simplified "subclass access" explanation often given.

Remember:

```text
same package
+
subclasses in other packages
```

For interviews, know that Java's protected access has specific rules when accessing through references across packages.

For now, the important mental model is:

```text
protected → package + subclass access
```

---

# 27. Getters and Setters ⭐⭐

Suppose:

```java
class Student {

    private int marks;
}
```

Outside code cannot directly do:

```java
student.marks = 90;
```

A getter reads the value:

```java
public int getMarks() {
    return marks;
}
```

A setter changes it:

```java
public void setMarks(int marks) {
    this.marks = marks;
}
```

Usage:

```java
student.setMarks(90);

System.out.println(student.getMarks());
```

---

# 28. You Don't Always Need Setters ⭐⭐⭐

This is an important design point.

Don't blindly create:

```text
getter
setter
getter
setter
```

for every field.

Suppose marks should only accept values from `0` to `100`.

Instead:

```java
public void updateMarks(int marks) {

    if (marks >= 0 && marks <= 100) {
        this.marks = marks;
    }
}
```

Now the class controls the valid state.

This is stronger encapsulation than simply exposing a setter.

The original Day 3 material explicitly makes this distinction.

---

# 29. Why Constructors Cannot Be `static` ⭐⭐⭐

A constructor is associated with **object creation**.

Example:

```java
new Student();
```

A static member belongs to the class.

So making a constructor static doesn't make conceptual sense.

### Interview answer

> Constructors are part of object creation and initialize an instance, while static members belong to the class. Therefore constructors cannot be static.

---

# 30. Why Constructors Cannot Be `final` ⭐⭐⭐

A `final` method cannot be overridden.

But constructors:

* Are not inherited.
* Cannot be overridden.

Therefore `final` has no meaningful purpose for constructors.

---

# 31. Why Constructors Cannot Be `abstract` ⭐⭐⭐

An abstract method has no implementation and is intended to be overridden.

Constructors:

* Must actually initialize an object.
* Are not overridden.
* Are not inherited.

Therefore constructors cannot be abstract.

---

# 32. Day 3 Coding Problem — Student Class ⭐⭐⭐

You should be able to write this from scratch.

Requirements:

* Create `Student`
* Fields: `name`, `rollNo`, `marks`
* Parameterized constructor
* `display()` method
* Create 3 objects
* Display their information

Expected structure:

```java
class Student {

    String name;
    int rollNo;
    double marks;

    Student(String name, int rollNo, double marks) {
        this.name = name;
        this.rollNo = rollNo;
        this.marks = marks;
    }

    void display() {
        System.out.println(name);
        System.out.println(rollNo);
        System.out.println(marks);
    }
}

public class Main {

    public static void main(String[] args) {

        Student s1 = new Student("Anurag", 101, 85);
        Student s2 = new Student("Rahul", 102, 90);
        Student s3 = new Student("Aman", 103, 78);

        s1.display();
        s2.display();
        s3.display();
    }
}
```

**Don't memorize this.** Close the notes and rebuild it yourself.

---

# 33. Day 3 Coding Problem — `this`

Write a constructor where parameter names are identical to field names:

```java
Student(String name, int rollNo) {

    this.name = name;
    this.rollNo = rollNo;
}
```

You should be able to explain:

```text
this.name
```

and:

```text
name
```

as two different things.

---

# 34. DSA — Check Whether Array Is Sorted ⭐⭐

Example:

```text
[1, 2, 3, 4, 5]
```

Sorted → `true`

But:

```text
[1, 2, 5, 3, 4]
```

Sorted → `false`

### Pattern

Compare adjacent elements:

```java
static boolean isSorted(int[] arr) {

    for (int i = 0; i < arr.length - 1; i++) {

        if (arr[i] > arr[i + 1]) {
            return false;
        }
    }

    return true;
}
```

The key pattern is:

```text
arr[i] vs arr[i + 1]
```

### Complexity

```text
Time  → O(n)
Space → O(1)
```

---

# 35. DSA — Left Rotate Array by One ⭐⭐

Example:

```text
Before:
[1, 2, 3, 4, 5]

After:
[2, 3, 4, 5, 1]
```

Basic idea:

```text
1. Save first element.
2. Shift everything left.
3. Put saved element at the end.
```

```java
static void leftRotateByOne(int[] arr) {

    int first = arr[0];

    for (int i = 0; i < arr.length - 1; i++) {
        arr[i] = arr[i + 1];
    }

    arr[arr.length - 1] = first;
}
```

### Complexity

```text
Time  → O(n)
Space → O(1)
```

The original Day 3 material intentionally kept these DSA problems simple because the day was primarily theory-heavy.

---

# 36. Day 3 Interview Questions ⭐⭐⭐

You should eventually answer these without looking at the notes.

### OOP

1. What is a class?
2. What is an object?
3. Difference between class and object?
4. What does `new` do?
5. What is a reference variable?
6. What happens when two references point to the same object?

### Constructors

7. What is a constructor?
8. Constructor vs method?
9. What is a default constructor?
10. When does Java provide a default constructor?
11. What happens if you define a parameterized constructor?
12. What is a parameterized constructor?
13. What is constructor overloading?
14. Can constructors have a return type?
15. Why can't constructors be `static`?
16. Why can't constructors be `final`?
17. Why can't constructors be `abstract`?
18. What is constructor chaining?
19. What does `this(...)` do?
20. Why must `this(...)` be the first statement?

### `this`

21. What does `this` refer to?
22. Why do we use `this.name = name`?
23. What is the difference between `this.name` and `name`?

### Static

24. What does `static` mean?
25. Difference between static and instance variables?
26. Difference between static and instance methods?
27. Why can't a static method directly access an instance variable?
28. Why is `main()` static?

### Encapsulation

29. What is encapsulation?
30. Why should fields often be private?
31. What are getters and setters?
32. Do all private fields need setters?
33. How does encapsulation protect invariants?

### Access modifiers

34. What are Java's four access levels?
35. Difference between `private`, default, `protected`, and `public`?
36. What does default/package-private mean?
37. How does `protected` behave across packages?

### DSA

38. How do you check if an array is sorted?
39. What is the time complexity?
40. How do you left-rotate an array by one position?
41. Can you do it in-place?
42. What are the time and space complexities?

---

# 37. The Day 3 Concepts Most Likely to Be Forgotten ⭐⭐⭐

These are the things you should deliberately revise later:

```text
1. Class vs object
2. Reference variable vs actual object
3. What new does
4. Default constructor rule
5. Parameterized constructors
6. Constructor overloading
7. this vs this(...)
8. this.name vs name
9. Instance vs static
10. Why static cannot directly access instance fields
11. Why main() is static
12. Encapsulation ≠ merely getters/setters
13. private/default/protected/public
14. Getter vs controlled update method
15. Why constructors cannot be static/final/abstract
16. Constructor chaining
17. Adjacent comparison for sorted arrays
18. In-place left rotation
```

These are also the concepts the original Day 3 checklist required you to explain without notes.

---

# 38. One-Minute Day 3 Revision

When revising quickly, remember:

```text
CLASS
→ blueprint

OBJECT
→ actual instance

new
→ creates object

REFERENCE
→ variable that refers to object

CONSTRUCTOR
→ initializes object
→ same name as class
→ no return type
→ runs during object creation

this
→ current object

this(...)
→ calls another constructor
→ must be first statement

INSTANCE
→ belongs to object
→ each object can have separate value

STATIC
→ belongs to class
→ shared/class-level member

ENCAPSULATION
→ control access to internal state

private
→ class

default
→ package

protected
→ package + subclasses

public
→ everywhere

GETTER
→ read

SETTER
→ modify

CONTROLLED METHOD
→ modify only if valid

DSA
→ sorted array: adjacent comparison
→ left rotate: save first + shift + place at end
```

---

# 39. C++ → Java Mental Translation

When you see:

```java
Student s = new Student();
```

think:

```text
Create object
      ↓
object lives on heap
      ↓
s holds reference to it
```

When you see:

```java
this.name = name;
```

think:

```text
current object's name
        =
constructor parameter name
```

When you see:

```java
static void display()
```

think:

```text
belongs to class
→ no object required
```

When you see:

```java
private double balance;
```

think:

```text
outside code cannot arbitrarily modify internal state
```

That mental translation is more useful than memorizing textbook definitions.

---

# Day 3 Completion Test

Do **not** move to Day 4 just because you read these notes.

You should be able to open a blank Java file and independently write:

```text
✓ Student class
✓ Instance fields
✓ Parameterized constructor
✓ this keyword
✓ display() method
✓ 3 Student objects
✓ Constructor overloading
✓ Constructor chaining with this(...)
✓ Static variable
✓ Static method
✓ Encapsulated private field
✓ Getter / controlled update method
✓ Sorted-array check
✓ Left rotation
```

Most importantly, you should be able to **explain why the code works**, not just reproduce it.

The original Day 3 completion criterion was exactly this: build the `Student` class from scratch in a blank Java file and explain the listed OOP concepts without notes.
