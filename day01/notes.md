# Day 1 — Java Quick Revision Notes

## 1. Java Execution Flow ⭐

```text
.java
  ↓
javac
  ↓
.class (Bytecode)
  ↓
JVM
  ↓
Machine Code / Execution
```

* `javac` → source code → bytecode
* JVM → executes bytecode
* Bytecode is platform-independent.
* JVM is platform-specific.
* This is the reason Java is **platform independent / Write Once, Run Anywhere**.

### Interview

> Java is platform independent because the compiler generates platform-independent bytecode, and each operating system has its own JVM implementation capable of executing that bytecode.

---

## 2. JDK vs JRE vs JVM ⭐⭐⭐

```text
JDK
 └── JRE
      └── JVM
```

* **JDK** → used for development; includes compiler and development tools.
* **JRE** → runtime environment.
* **JVM** → executes bytecode.

### Remember

> JDK develops, JRE runs, JVM executes.

**Modern Java note:** Separate JRE downloads are no longer generally provided, but the above remains a useful conceptual model.

---

## 3. Why `main()` Looks Like This ⭐⭐⭐

```java
public static void main(String[] args)
```

* `public` → JVM must be able to access it.
* `static` → JVM can call it without creating an object.
* `void` → returns nothing.
* `main` → standard entry point.
* `String[] args` → command-line arguments.

### Most important

> `main()` is `static` because the JVM needs to invoke it without creating an object first.

---

## 4. Primitive Type Facts Worth Remembering

| Type     |    Size |
| -------- | ------: |
| `byte`   |  1 byte |
| `short`  | 2 bytes |
| `int`    | 4 bytes |
| `long`   | 8 bytes |
| `float`  | 4 bytes |
| `double` | 8 bytes |
| `char`   | 2 bytes |

### Important

* `char` in Java = **16-bit UTF-16 code unit**
* C++ `char` = **1 byte**
* Don't say: `C++ char = ASCII`.

### Default values

Only **fields** get default values automatically.

```text
int      → 0
long     → 0L
float    → 0.0f
double   → 0.0
char     → '\u0000'
boolean  → false
```

Local variables **must be initialized before use**.

---

## 5. Integer & Decimal Literal Defaults ⭐⭐

```java
10      // int
10L     // long

10.5    // double
10.5f   // float
```

### Remember

> Integer literals are `int` by default. Floating-point literals are `double` by default.

---

## 6. Type Casting ⭐⭐⭐

### Widening

```java
int x = 10;
double y = x;
```

```text
smaller → larger
automatic
```

### Narrowing

```java
double x = 10.8;
int y = (int) x;
```

```text
larger → smaller
explicit cast
```

Result:

```text
10
```

### Syntax

```java
(targetType) value
```

---

## 7. Integer Division — Common DSA Trap ⭐⭐⭐

```java
int a = 10;
int b = 3;

System.out.println(a / b);
```

Output:

```text
3
```

Not `3.333...`

To get decimal division:

```java
double result = (double) a / b;
```

### Remember

> If both operands are integers, Java performs integer division.

---

## 8. `nextInt()` + `nextLine()` Trap ⭐⭐⭐

Problem:

```java
int age = sc.nextInt();
String name = sc.nextLine();
```

`nextInt()` reads the number but **leaves the newline**.

Therefore `nextLine()` may immediately read that leftover newline and return an empty string.

Correct:

```java
int age = sc.nextInt();
sc.nextLine();

String name = sc.nextLine();
```

### Remember

> `nextInt()` leaves the newline; `nextLine()` consumes it.

---

## 9. Modern `switch` ⭐⭐

Traditional:

```java
switch (choice) {
    case 1:
        // code
        break;
}
```

Modern:

```java
String result = switch (choice) {
    case 1 -> "Add";
    case 2 -> "Subtract";
    default -> "Invalid";
};
```

### Important differences

* `case 1, 2, 3` can group cases.
* `->` avoids traditional fall-through.
* A switch **expression can produce a value**.

---

## 10. Digit Extraction — First Important DSA Pattern ⭐⭐⭐

```java
while (n > 0) {

    int digit = n % 10;

    n /= 10;
}
```

Remember:

```text
n % 10 → last digit
n / 10 → removes last digit
```

Example:

```text
1234

1234 % 10 → 4
1234 / 10 → 123
```

Used in:

* Reverse Number
* Sum of Digits
* Palindrome Number
* Armstrong Number
* Count Digits

This pattern is worth memorizing.

---

## 11. C++ → Java Differences to Keep in Muscle Memory

```text
cout              → System.out.println()
cin >> x          → sc.nextInt()
string            → String
bool              → boolean
nullptr           → null
#include          → import
```

Java has:

* Garbage collection
* No manual `delete`
* No direct pointer arithmetic
* No multiple inheritance of classes

---

# Interview Questions to Know

### Why is Java platform independent?

> Because Java source code is compiled into platform-independent bytecode, and each operating system has a compatible JVM that executes that bytecode.

### Why is `main()` static?

> So the JVM can call it without creating an object.

### Difference between JDK, JRE and JVM?

> JDK provides development tools, JRE provides the runtime environment, and JVM executes Java bytecode.

### Why is Java `char` 2 bytes?

> Java `char` is a 16-bit UTF-16 code unit.

### Widening vs narrowing?

> Widening is automatic conversion from a smaller compatible type to a larger type. Narrowing converts to a smaller type and requires explicit casting.

### Why does `nextLine()` sometimes return an empty string after `nextInt()`?

> Because `nextInt()` leaves the newline character in the input, which `nextLine()` then consumes.

---

# Day 1 — Don't Forget These

```text
javac → bytecode
JVM   → executes bytecode

JDK > JRE > JVM   (conceptual relationship)

main() → static so JVM doesn't need an object

int literal → int
decimal literal → double

int / int → int

Widening → automatic
Narrowing → explicit cast

nextInt() → leaves newline
nextLine() → consumes newline

n % 10 → last digit
n / 10 → remove last digit

Java char → 2-byte UTF-16 code unit
C++ char → 1 byte
```
