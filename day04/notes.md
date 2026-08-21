# Day 04 — Java OOP Part 2

## Inheritance, Polymorphism, Abstraction & Interfaces

---

# 1. Inheritance

## What is inheritance?

Inheritance allows a child class to acquire accessible members (fields/methods) from a parent class.

```java
class Animal {
    void eat() {
        System.out.println("Eating");
    }
}

class Dog extends Animal {
    void bark() {
        System.out.println("Barking");
    }
}
```

```java
Dog d = new Dog();

d.eat();   // inherited from Animal
d.bark();  // Dog's own method
```

### Key terminology

```text
Animal → Parent / Superclass
Dog    → Child / Subclass
```

Keyword:

```java
extends
```

### Remember

> `Dog extends Animal` means Dog has an inheritance relationship with Animal.

---

# 2. Types of Inheritance

## Single inheritance

One parent → one child.

```text
Animal
   ↓
  Dog
```

```java
class Dog extends Animal
```

---

## Multilevel inheritance

Inheritance happens across multiple levels.

```text
Animal
   ↓
  Dog
   ↓
 Puppy
```

Puppy can access inherited members from Dog and, through Dog, from Animal.

---

## Hierarchical inheritance

One parent → multiple children.

```text
       Animal
       /    \
     Dog    Cat
```

```java
class Dog extends Animal
class Cat extends Animal
```

### Don't confuse:

```text
Multilevel:

A
↓
B
↓
C
```

```text
Hierarchical:

   A
  / \
 B   C
```

---

# 3. `super`

`super` refers to the **immediate parent class**.

Example:

```java
class Animal {
    String name = "Animal";
}

class Dog extends Animal {
    String name = "Dog";

    void display() {
        System.out.println(name);
        System.out.println(super.name);
    }
}
```

Output:

```text
Dog
Animal
```

### Why?

```java
name
```

refers to the current class's field.

```java
super.name
```

refers to the parent's field.

### Quick memory rule

```text
this  → current class/object
super → immediate parent class
```

---

# 4. Why Java Doesn't Support Multiple Class Inheritance

Java does **not** allow:

```java
class C extends A, B   // ❌
```

Suppose:

```text
Class A
  ↓
 show()

Class B
  ↓
 show()

Class C
  ↑
 A + B
```

If C inherited both `show()` implementations:

```java
C obj = new C();
obj.show();
```

Which one should run?

```text
A.show() ?
B.show() ?
```

This creates ambiguity.

Therefore:

> Java does not support multiple inheritance through classes.

However, Java allows a class to implement multiple interfaces.

---

# 5. Polymorphism

## Meaning

```text
poly = many
morph = forms
```

Polymorphism means the same method name/reference can work with different forms or implementations.

Day 04 covers two types:

```text
Compile-time polymorphism → Overloading
Runtime polymorphism       → Overriding
```

---

# 6. Method Overloading

## Definition

Multiple methods have:

* same method name
* different parameter lists

Example:

```java
class Calculator {

    int add(int a, int b) {
        return a + b;
    }

    double add(double a, double b) {
        return a + b;
    }

    int add(int a, int b, int c) {
        return a + b + c;
    }
}
```

All three are named:

```text
add()
```

but their parameter lists differ.

### What can differ?

The parameter list can differ by:

1. Number of parameters
2. Parameter types
3. Order of parameter types

### What does NOT create overloading?

Changing only the return type.

This is invalid:

```java
int add(int a, int b) {
    return a + b;
}

double add(int a, int b) {
    return a + b;
}
```

Same name + same parameters = not a valid overload.

---

## Why is overloading compile-time polymorphism?

The compiler can determine which method to call based on the arguments.

```java
Calculator c = new Calculator();

c.add(2, 3);
```

→ `add(int, int)`

```java
c.add(2.5, 3.5);
```

→ `add(double, double)`

Therefore:

> Method overloading is compile-time polymorphism.

---

# 7. Method Overriding

## Definition

When a child class provides its own implementation of a method inherited from the parent, it is called method overriding.

```java
class Animal {

    void sound() {
        System.out.println("Animal sound");
    }
}

class Dog extends Animal {

    @Override
    void sound() {
        System.out.println("Dog barks");
    }
}
```

`Dog` overrides `Animal.sound()`.

---

## `@Override`

```java
@Override
void sound() {
    ...
}
```

`@Override` is an annotation that tells the compiler that the method is intended to override a parent method.

It does **not** create overriding by itself.

---

# 8. Runtime Polymorphism & Dynamic Method Dispatch

This is one of the most important Day 04 concepts.

```java
Animal a = new Dog();

a.sound();
```

There are two types to understand:

```text
Animal a
   ↑
Reference type

new Dog()
   ↑
Actual object type
```

The actual object is a `Dog`.

Therefore:

```java
a.sound();
```

calls:

```java
Dog.sound()
```

not:

```java
Animal.sound()
```

This is called:

> Dynamic method dispatch

and is the mechanism behind runtime polymorphism.

### Mental model

```text
Animal a = new Dog();

Reference type → Animal
Actual object  → Dog

a.sound()
    ↓
Dog.sound()
```

---

# 9. Overloading vs Overriding

| Feature          | Overloading                    | Overriding                    |
| ---------------- | ------------------------------ | ----------------------------- |
| Same method name | Yes                            | Yes                           |
| Parameters       | Must differ                    | Compatible with parent method |
| Where            | Usually same class             | Parent + child                |
| Main purpose     | Multiple ways to call a method | Child-specific implementation |
| Polymorphism     | Compile-time                   | Runtime                       |
| Method selection | Compiler                       | Runtime / dynamic dispatch    |

### Interview shortcut

```text
Overloading  → same name + different parameters → compile time

Overriding   → child replaces inherited implementation → runtime
```

---

# 10. Abstraction

## What is abstraction?

Abstraction means exposing what must be done while hiding implementation details.

Example:

```java
abstract class Animal {

    abstract void sound();

    void eat() {
        System.out.println("Eating");
    }
}
```

Here:

```java
abstract void sound();
```

says:

> Every concrete child must provide a `sound()` implementation.

But `Animal` doesn't specify how every animal sounds.

---

# 11. Abstract Method

An abstract method has no body.

```java
abstract void sound();
```

A class containing an abstract method must itself be abstract.

Invalid:

```java
class Animal {
    abstract void sound();   // ❌
}
```

Correct:

```java
abstract class Animal {
    abstract void sound();
}
```

---

# 12. Abstract Class

An abstract class cannot be directly instantiated.

```java
Animal a = new Animal();   // ❌
```

But it can contain:

* abstract methods
* concrete methods
* fields
* constructors

Example:

```java
abstract class Vehicle {

    abstract void start();

    void stop() {
        System.out.println("Vehicle stops");
    }
}
```

Notice:

> An abstract class does NOT have to contain only abstract methods.

It can contain both abstract and concrete methods.

---

# 13. Abstract Class Reference

This is valid:

```java
Animal a = new Dog();
```

Even if:

```java
Animal
```

is abstract.

Why?

Because you're **not creating an Animal object**.

You're creating a Dog object and using an Animal reference.

```text
Animal a = new Dog();

Animal → reference
Dog    → actual object
```

But this is invalid:

```java
Animal a = new Animal();   // ❌
```

because Animal is abstract.

---

# 14. Interfaces

An interface is primarily a **contract/capability**.

Example:

```java
interface Animal {
    void sound();
}
```

A class implements the interface:

```java
class Dog implements Animal {

    @Override
    public void sound() {
        System.out.println("Dog barks");
    }
}
```

Keyword:

```java
implements
```

### Basic distinction

```text
class inheritance → extends

interface implementation → implements
```

---

# 15. Interface Methods

An interface can contain abstract methods.

```java
interface Shape {
    double area();
}
```

A class implementing it must provide the required method:

```java
class Circle implements Shape {

    @Override
    public double area() {
        return Math.PI * 5 * 5;
    }
}
```

The implementation must be `public` because interface methods are public.

---

# 16. Default Methods

Java 8+ allows interfaces to contain `default` methods with an implementation.

```java
interface Animal {

    void sound();

    default void eat() {
        System.out.println("Animal eats");
    }
}
```

A class implementing the interface automatically gets the default method.

```java
class Dog implements Animal {

    public void sound() {
        System.out.println("Bark");
    }
}
```

Then:

```java
Dog d = new Dog();

d.sound();
d.eat();
```

Both work.

### Key idea

```text
abstract interface method
→ class must implement

default interface method
→ class gets implementation automatically
```

A class can also override the default method.

---

# 17. Static Methods in Interfaces

Interfaces can contain static methods.

```java
interface MathHelper {

    static void show() {
        System.out.println("Math helper");
    }
}
```

Call it using the interface name:

```java
MathHelper.show();
```

Not:

```java
MathHelper m = ...;
m.show();    // ❌
```

### Remember

```text
default method → available through implementing object

static method → belongs to interface itself
```

---

# 18. Multiple Interfaces

A class cannot extend multiple classes:

```java
class Dog extends Animal, Bird   // ❌
```

But a class can implement multiple interfaces:

```java
interface Swimmer {
    void swim();
}

interface Runner {
    void run();
}

class Dog implements Swimmer, Runner {

    public void swim() {
        System.out.println("Swimming");
    }

    public void run() {
        System.out.println("Running");
    }
}
```

This is valid.

### Why?

Interfaces represent contracts/capabilities.

```text
Dog
 ├── Swimmer
 └── Runner
```

Dog agrees to provide both behaviors.

This avoids the multiple-parent-class implementation ambiguity discussed earlier.

---

# 19. Interface vs Abstract Class

This is a **high-priority Java interview question**.

| Feature                | Abstract Class                         | Interface                                        |
| ---------------------- | -------------------------------------- | ------------------------------------------------ |
| Declaration            | `abstract class`                       | `interface`                                      |
| Class relationship     | `extends`                              | `implements`                                     |
| Abstract methods       | Yes                                    | Yes                                              |
| Concrete methods       | Yes                                    | Yes, through `default`/`static` methods          |
| Direct object creation | No                                     | No                                               |
| Multiple inheritance   | A class cannot extend multiple classes | A class can implement multiple interfaces        |
| Main purpose           | Shared base + common implementation    | Contract / capability                            |
| Shared state           | Can have instance fields               | Not used like an ordinary class's instance state |
| Constructor            | Can have constructors                  | No normal constructors                           |
| Typical relationship   | Strong "is-a" relationship             | "Can-do"/capability relationship                 |

---

# 20. When to Choose Abstract Class

Use an abstract class when related classes should share:

* common implementation
* common state
* common parent behavior

Example:

```text
        Vehicle
        /     \
      Car     Bike
```

Both are Vehicles.

They might share:

```java
void stop() {
    System.out.println("Vehicle stops");
}
```

while requiring different implementations of:

```java
abstract void start();
```

---

# 21. When to Choose Interface

Use an interface when you want to define a:

> Contract or capability.

Different types of classes can implement the same capability.

Example:

```text
        Flyable
        /     \
      Bird    Plane
```

Bird and Plane aren't necessarily the same kind of class, but both can be `Flyable`.

A class can also implement multiple interfaces:

```java
class Duck implements Flyable, Swimmable
```

### Don't use this incorrect rule:

> "If multiple interfaces are needed, use an interface; otherwise use an abstract class."

The real decision is based on **design and relationship**, not simply the number of interfaces.

---

# 22. Interface Polymorphism

An interface can be used as a reference:

```java
interface Shape {
    double area();
}

class Circle implements Shape {

    public double area() {
        return Math.PI * 5 * 5;
    }
}
```

Then:

```java
Shape s = new Circle();

s.area();
```

Here:

```text
Shape  → reference type
Circle → actual object
```

So:

```java
s.area();
```

runs:

```text
Circle.area()
```

This is runtime polymorphism through an interface.

---

# 23. Coding Patterns from Day 04

## Animal overriding

```java
class Animal {
    void sound() {
        System.out.println("Animal sound");
    }
}

class Dog extends Animal {

    @Override
    void sound() {
        System.out.println("Dog barks");
    }
}

class Cat extends Animal {

    @Override
    void sound() {
        System.out.println("Cat meows");
    }
}
```

---

## Method overloading

```java
class Calculator {

    int add(int a, int b) {
        return a + b;
    }

    double add(double a, double b) {
        return a + b;
    }

    int add(int a, int b, int c) {
        return a + b + c;
    }
}
```

---

## Interface + implementations

```java
interface Shape {
    double area();
}

class Circle implements Shape {

    double radius;

    Circle(double radius) {
        this.radius = radius;
    }

    @Override
    public double area() {
        return Math.PI * radius * radius;
    }
}

class Rectangle implements Shape {

    double length;
    double width;

    Rectangle(double length, double width) {
        this.length = length;
        this.width = width;
    }

    @Override
    public double area() {
        return length * width;
    }
}
```

Polymorphic references:

```java
Shape c = new Circle(5);
Shape r = new Rectangle(5, 8);

System.out.println(c.area());
System.out.println(r.area());
```

---

# 24. DSA — Missing Number

## Problem

Given numbers from `1` to `N` with exactly one missing, find the missing number.

Example:

```text
[1, 2, 3, 5]
```

Missing:

```text
4
```

## Pattern

Use the sum formula:

```text
1 + 2 + ... + N = N(N + 1) / 2
```

Then:

```text
missing = expectedSum - actualSum
```

## Java

```java
int n = arr.length + 1;

long sum = (long) n * (n + 1) / 2;

long total = 0;

for (int x : arr) {
    total += x;
}

return (int)(sum - total);
```

## Complexity

```text
Time  → O(N)
Space → O(1)
```

### Important Java lesson: integer overflow

This is NOT sufficiently safe:

```java
long sum = (long)(n * (n + 1)) / 2;
```

because:

```text
n * (n + 1)
```

is calculated as `int` first.

Correct:

```java
long sum = (long)n * (n + 1) / 2;
```

The cast must happen **before the multiplication**.

---

# 25. DSA — Frequency Counting

## Pattern

```text
element → frequency
```

Example:

```text
[1, 2, 2, 3, 1, 2, 4]
```

Frequency:

```text
1 → 2
2 → 3
3 → 1
4 → 1
```

For today's bounded-range exercise:

```java
int[] freq = new int[101];

for (int x : arr) {
    freq[x]++;
}
```

Then:

```java
for (int i = 0; i < freq.length; i++) {
    if (freq[i] > 0) {
        System.out.println(i + " " + freq[i]);
    }
}
```

### Important distinction

In:

```java
for (int x : arr)
```

`x` is the **element/value**.

In:

```java
for (int i = 0; i < freq.length; i++)
```

`i` is the **index**.

---

## Frequency array vs HashMap

Use a frequency array when the possible values have a **small, known range**.

Example:

```text
0–100
```

Use HashMap when values can be huge.

Example:

```text
arr[i] ≤ 10^9
```

A frequency array with one billion positions would waste enormous memory.

### Important roadmap note

HashMap-based frequency counting is intentionally taught later in the bootcamp.

---

# 26. Interview Questions — Quick Revision

## Q1. Difference between overloading and overriding?

### Expected answer

> Method overloading occurs when multiple methods have the same name but different parameter lists. It is compile-time polymorphism. Method overriding occurs when a child class provides its own implementation of a method inherited from the parent. It is runtime polymorphism and uses dynamic method dispatch.

### Keywords to mention

```text
Overloading → same name + different parameters → compile time

Overriding → child implementation → runtime
```

---

## Q2. Can Java achieve multiple inheritance? How?

### Expected answer

> Java does not support multiple inheritance through classes, meaning a class cannot extend multiple classes. This avoids ambiguity when multiple parent classes contain the same method. Java does allow a class to implement multiple interfaces, so multiple contracts/capabilities can be combined.

Example:

```java
class Duck implements Flyable, Swimmable
```

---

## Q3. Interface vs abstract class — when would you pick one?

### Expected answer

> I would use an abstract class when related classes need shared state or common implementation while also having some abstract behavior. I would use an interface when I want to define a contract or capability that potentially unrelated classes can implement, especially when a class needs multiple contracts.

### Simple mental model

```text
Abstract class → shared base + common implementation

Interface → contract / capability
```

---

# 27. Common Traps

### Trap 1

> "Changing return type creates overloading."

❌ False.

Parameters must differ.

---

### Trap 2

> "`@Override` creates overriding."

❌ False.

The child-parent method relationship creates overriding. `@Override` simply verifies your intention.

---

### Trap 3

> "Abstract class contains only abstract methods."

❌ False.

It can contain both:

```text
abstract methods
+
concrete methods
```

---

### Trap 4

> "You can't use an abstract class as a reference."

❌ False.

This is valid:

```java
Animal a = new Dog();
```

This is not:

```java
Animal a = new Animal();
```

---

### Trap 5

> "Interface methods can never have implementations."

❌ Outdated.

Java 8+ interfaces can have:

```text
default methods
static methods
```

---

### Trap 6

> "A for-each loop gives the index."

❌ False.

```java
for (int x : arr)
```

gives the value.

Use an indexed loop when you need the index.

---

### Trap 7 — Integer overflow

Wrong:

```java
(long)(n * (n + 1))
```

Correct:

```java
(long)n * (n + 1)
```

Cast **before** arithmetic.

---

# 28. Day 04 One-Page Mental Model

```text
INHERITANCE
    │
    ├── extends
    ├── super
    ├── single
    ├── multilevel
    └── hierarchical

POLYMORPHISM
    │
    ├── Compile time
    │      └── Overloading
    │          same name + different parameters
    │
    └── Runtime
           └── Overriding
               child implementation
               dynamic method dispatch

ABSTRACTION
    │
    └── abstract class
         ├── abstract methods
         ├── concrete methods
         └── cannot instantiate directly

INTERFACE
    │
    ├── implements
    ├── contract/capability
    ├── default methods
    ├── static methods
    └── multiple interfaces

DECISION
    │
    ├── Shared base/state/implementation
    │       → Abstract class
    │
    └── Contract/capability/multiple contracts
            → Interface
```

# 29. Day 04 Revision Checklist

* [ ] Explain `extends`
* [ ] Explain `super`
* [ ] Distinguish single, multilevel, and hierarchical inheritance
* [ ] Explain why Java doesn't support multiple class inheritance
* [ ] Define polymorphism
* [ ] Explain overloading
* [ ] Explain overriding
* [ ] Explain dynamic method dispatch
* [ ] Explain abstract class
* [ ] Explain abstract method
* [ ] Explain why an abstract class cannot be instantiated
* [ ] Explain abstract-class reference → child object
* [ ] Explain interface
* [ ] Explain `implements`
* [ ] Explain default methods
* [ ] Explain static interface methods
* [ ] Explain multiple interfaces
* [ ] Compare interface vs abstract class
* [ ] Solve Missing Number using the sum formula
* [ ] Explain integer overflow in the sum formula
* [ ] Explain frequency counting
* [ ] Distinguish array value from array index
* [ ] Answer all 3 Day 04 interview questions without notes

# Day 04 Interview Flashcards

**Q:** Overloading?
**A:** Same method name, different parameter list → compile-time polymorphism.

**Q:** Overriding?
**A:** Child provides its own implementation of an inherited parent method → runtime polymorphism.

**Q:** `this` vs `super`?
**A:** `this` refers to current object/class; `super` refers to immediate parent.

**Q:** Can an abstract class have concrete methods?
**A:** Yes.

**Q:** Can you instantiate an abstract class?
**A:** No.

**Q:** Can an abstract-class reference point to a child object?
**A:** Yes.

**Q:** `extends` vs `implements`?
**A:** Class inheritance uses `extends`; implementing interfaces uses `implements`.

**Q:** Can a class implement multiple interfaces?
**A:** Yes.

**Q:** Why no multiple class inheritance?
**A:** To avoid ambiguity/conflicting inherited implementations.

**Q:** Interface vs abstract class?
**A:** Abstract class for shared base/state/implementation; interface for contracts/capabilities and multiple contracts.

**Q:** What is dynamic method dispatch?
**A:** At runtime, an overridden method is selected based on the actual object rather than merely the reference type.

**Q:** Frequency array vs HashMap?
**A:** Frequency array for a small bounded value range; HashMap when values can be large or sparse.
