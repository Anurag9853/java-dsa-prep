# Day 05 Notes — Arrays Deep-Dive + Big-O Thinking
**Confidence:** 4/5

## TL;DR

Today I learned that solving a DSA problem is not enough — I also need to understand how the solution scales with input size. The major array patterns I practiced were traversal, shifting, sorting, searching, 2D arrays/transpose, brute-force nested loops, running sums/Kadane, cyclic rotation, and equilibrium-index logic.

The most important habit from today:

> Don't just ask "Does my code work?" Ask "How much work does my code do when `n` becomes large?"

## What I Actually Learned (in my own words)

### 1. 1D Arrays

An array stores elements of the same type and each element can be accessed using its index.

```java
int[] arr = {10, 20, 30, 40};

Indexes:

index:  0   1   2   3
value: 10  20  30  40

Access:

arr[0]   // 10
arr[2]   // 30

Array index access is O(1) because I already know the exact position/index of the element and don't need to traverse the array.

2. Array Indexing and Invalid Index

For:

int[] arr = {5, 3, 7};

Valid indexes are:

0 → 5
1 → 3
2 → 7

Trying:

arr[3]

causes an index-out-of-bounds error because index 3 does not exist.

General rule:

valid indexes = 0 to arr.length - 1
3. 2D Arrays

A 2D array can be thought of as rows and columns.

int[][] matrix = {
    {1, 2, 3},
    {4, 5, 6}
};

It has:

2 rows
3 columns

Access:

matrix[0][0] → 1
matrix[0][1] → 2
matrix[1][2] → 6

Number of rows:

matrix.length

Number of columns:

matrix[0].length
4. Arrays of Objects

An array can hold references to objects.

Student[] students = new Student[2];

This creates space for two Student references.

It does NOT automatically create two Student objects.

Initially:

students[0] → null
students[1] → null

Objects must be created separately:

students[0] = new Student();
students[1] = new Student();

Important distinction:

array of references

is not the same as:

actual objects
5. Common Array Operations
Search

Linear search checks elements one by one until the required element is found.

Example:

[10, 20, 30, 40]

Searching for 30:

10 → no
20 → no
30 → found

Worst case:

O(n)

Best case:

O(1)

If the element is the first element, only one comparison is needed.

Insert

When inserting into an array, existing elements may need to be shifted.

If inserting at an index, elements to the right need to move right.

Important:

shift from right to left

so values aren't overwritten.

Delete

When deleting from an array, elements after the deleted element may need to shift left.

The physical array length does not change.

A separate logical size can be maintained:

int size = 0;

This concept was also used when building result arrays for Union and Intersection.

Reverse

An array can be reversed using two indexes:

int start = 0;
int end = arr.length - 1;

Swap:

arr[start] ↔ arr[end]

then:

start++
end--

until:

start >= end
Big-O Thinking
6. What Is Time Complexity?

My initial explanation was:

"Time complexity is the time taken by the algorithm to run one process."

That was incomplete.

Better explanation:

Time complexity describes how the amount of work performed by an algorithm grows as the input size n grows.

It is not simply the exact number of seconds the program takes on my computer.

Why it matters:

A program may work correctly for small test cases but become too slow for large inputs and result in:

TLE = Time Limit Exceeded

Example:

O(n)

usually scales much better than:

O(n²)

when n becomes large.

7. Common Big-O Complexities
O(1)

Constant time.

Example:

arr[500]

Accessing an array element by index is O(1).

Whether the array contains:

1,000 elements

or:

10,000,000 elements

accessing a known index is still constant-time.

O(log n)

The amount of work grows logarithmically.

This was introduced today as one of the common Big-O categories.

O(n)

Linear time.

Example:

for (int i = 0; i < arr.length; i++) {
    // constant work
}

If the array size doubles, the amount of work approximately doubles.

Linear search is O(n) in the worst case.

O(n²)

Quadratic time.

Example:

for (int i = 0; i < n; i++) {
    for (int j = 0; j < n; j++) {
        // work
    }
}

Approximately:

n × n = n²

operations.

Coding Questions
8. Bubble Sort

Bubble Sort repeatedly compares adjacent elements.

Example:

[3, 1, 2]

Compare:

3 and 1

Since:

3 > 1

swap:

[1, 3, 2]

Then compare:

3 and 2

Swap:

[1, 2, 3]

The largest remaining element moves toward the end during each pass.

Bubble Sort Code
package basic;

public class BubbleSort {
    public static void main(String[] args) {

        int[] arr = {20, 10, 30, 70, 60, 50};

        int n = arr.length;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n - 1 - i; j++) {

                if (arr[j] > arr[j + 1]) {

                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }

        for (int i = 0; i < n; i++) {
            System.out.print(arr[i] + " ");
        }
    }
}

Output:

10 20 30 50 60 70
Understanding n - 1 - i

I initially struggled with:

j < n - 1 - i

The important reasoning:

n - 1

is needed because we access:

arr[j + 1]

Then:

-i

because after each outer pass, one more largest element is already in its final position at the end.

Example for n = 6:

Pass 0 → 5 comparisons
Pass 1 → 4 comparisons
Pass 2 → 3 comparisons
Pass 3 → 2 comparisons
Pass 4 → 1 comparison
9. Linear Search

Linear search checks elements one by one.

Example:

[10, 20, 30, 40]

Search:

30

Checks:

10 → no
20 → no
30 → found

Worst-case:

O(n)

Best-case:

O(1)

Interview explanation:

Linear search is O(n) in the worst case because we may have to inspect every element.

10. Matrix and Transpose

Original matrix:

1 2 3
4 5 6

Transpose:

1 4
2 5
3 6

Rule:

matrix[i][j] → transpose[j][i]

Example:

matrix[0][1] = 2

becomes:

transpose[1][0] = 2

The original matrix is:

2 × 3

so the transpose is:

3 × 2

Therefore:

int[][] transpose = new int[m][n];

where:

int n = matrix.length;
int m = matrix[0].length;

Complexity:

Time: O(n × m)
Space: O(n × m)
DSA Questions
11. Union of Two Arrays

Union means all unique elements present in either array.

Example:

A = [2, 4, 6, 8]
B = [4, 6, 10, 12]

Union:

[2, 4, 6, 8, 10, 12]

Mental model:

A OR B
My Approach

Maintain:

int[] result = new int[100];
int size = 0;

For every element in A:

Is it already in result?

YES → skip
NO  → add

Then repeat for B.

Important:

size++;

only when an element is actually added.

Union Pattern
for (int i = 0; i < A.length; i++) {

    boolean isFoundInResult = false;

    for (int j = 0; j < size; j++) {

        if (A[i] == result[j]) {
            isFoundInResult = true;
        }
    }

    if (!isFoundInResult) {
        result[size] = A[i];
        size++;
    }
}

Repeat the same process for B.

Complexity

Brute-force union:

Time: O(n²)
Space: O(n)

assuming both arrays have approximately n elements.

12. Intersection of Two Arrays

Intersection means elements common to both arrays.

Example:

A = [2, 4, 6, 8]
B = [4, 6, 10, 12]

Intersection:

[4, 6]

Mental model:

A AND B
Unique Intersection Logic

For every element of A:

Check whether it exists in B.
Check whether it already exists in result.
Add only if both conditions are satisfied.

Mental structure:

For each A element:

    Search all of B

    Search all of result

    Decide whether to add
Important Mistakes I Made

I initially placed:

boolean foundInB = false;
boolean alreadyExists = false;

inside the B loop.

That resets the flags for every B element.

Correct:

for (int i = 0; i < a.length; i++) {

    boolean foundInB = false;
    boolean alreadyExists = false;

    for (int j = 0; j < b.length; j++) {
        ...
    }

    for (int x = 0; x < size; x++) {
        ...
    }

    if (foundInB && !alreadyExists) {
        ...
    }
}

I also initially increased size just because an element was found in B.

Correct:

finding an element ≠ adding an element

size changes only when the element is actually added to result.

Complexity

Brute-force approach:

Time: O(n²)
Space: O(n)
13. Rearrange Array in Alternating Positive/Negative Order

Requirements:

Start with positive.
0 is considered positive.
Maintain relative order.
Alternate positive and negative.
If one type is exhausted, append the remaining elements in their original order.
Expected time: O(n).
Auxiliary space: O(n).

Example:

[-5, -2, 5, 2, 4, 7, 1, 8, 0, -8]

Positive elements:

[5, 2, 4, 7, 1, 8, 0]

Negative elements:

[-5, -2, -8]

Alternate:

5, -5, 2, -2, 4, -8

Negatives are exhausted.

Remaining positives:

7, 1, 8, 0

Final:

[5, -5, 2, -2, 4, -8, 7, 1, 8, 0]
Important: Zero

The problem says 0 is positive.

Therefore:

if (arr.get(i) >= 0)

not:

if (arr.get(i) > 0)
Relative Order

Example:

[-1, -2, 3, 4, 5]

Positive list:

[3, 4, 5]

Negative list:

[-1, -2]

Result:

[3, -1, 4, -2, 5]

The relative order inside each group is preserved.

ArrayList Issue

The GFG version used:

void rearrange(ArrayList<Integer> arr)

I had not learned ArrayList yet.

ArrayList is NOT a Day 5 topic.

Only the minimum GFG-specific operations were encountered:

arr.size()
arr.get(i)
arr.set(i, value)

For ArrayList:

.length → wrong
arr[i]   → wrong

Instead:

arr.size()
arr.get(i)
arr.set(i, value)

I should learn ArrayList separately when it appears in the roadmap.

Algorithm

Separate elements:

positive values
negative values

while preserving their original order.

Then merge:

positive
negative
positive
negative
...

When one list finishes, append the remaining list.

Complexity:

Time: O(n)
Auxiliary Space: O(n)
14. Maximum Subarray Sum — Kadane's Algorithm

Kadane's Algorithm finds the maximum sum of a contiguous subarray.

Example:

[-2, 1, -3, 4, -1, 2, 1, -5, 4]

Best subarray:

[4, -1, 2, 1]

Sum:

4 + (-1) + 2 + 1 = 6

Answer:

6
Core Idea

At every element:

Should I:

1. start a new subarray here
OR
2. continue the previous subarray?

Formula:

currentSum =
    Math.max(arr[i], currentSum + arr[i]);

Then:

maxSum =
    Math.max(maxSum, currentSum);
Meaning of Variables
currentSum

Best subarray sum ending at the current position.

maxSum

Best subarray sum found anywhere so far.

Example:

[4, -1, 2]

After -1:

currentSum = 3
maxSum = 4

After 2:

currentSum = 5
maxSum = 5
Example
[-2, 5, -1]

Start:

currentSum = -2
maxSum = -2

At 5:

max(5, -2 + 5)
= max(5, 3)
= 5

So:

currentSum = 5
maxSum = 5

At -1:

max(-1, 5 - 1)
= 4

So:

currentSum = 4
maxSum = 5
Important Mistake — All Negative Array

My first version used:

int currentSum = 0;
int maxSum = 0;

This fails for:

[-5, -2, -8]

because it would return 0.

Correct answer:

-2

Correct initialization:

int currentSum = arr[0];
int maxSum = arr[0];

for (int i = 1; i < arr.length; i++) {

    currentSum =
        Math.max(arr[i], currentSum + arr[i]);

    maxSum =
        Math.max(maxSum, currentSum);
}
Kadane Code
package basic;

public class MaximumSubarraySum {
    public static void main(String[] args) {

        int[] arr = {-2, 1, -3, 4, -1, 2, 1, -5, 4};

        int currentSum = arr[0];
        int maxSum = arr[0];

        for (int i = 1; i < arr.length; i++) {

            currentSum =
                Math.max(arr[i], currentSum + arr[i]);

            maxSum =
                Math.max(maxSum, currentSum);
        }

        System.out.println(
            "Maximum Subarray Sum: " + maxSum
        );
    }
}

Complexity:

Time: O(n)
Space: O(1)
15. Find All Pairs With Given Sum

Example:

arr = [2, 4, 3, 5, 7]
target = 7

Pairs:

2 + 5 = 7
4 + 3 = 7

Therefore:

(2, 5)
(4, 3)
Brute Force

Use two loops:

for (int i = 0; i < arr.length; i++) {

    for (int j = i + 1; j < arr.length; j++) {

        if (arr[i] + arr[j] == target) {
            // pair found
        }
    }
}

Important:

j = i + 1

This avoids checking the same pair twice.

For:

index:  0  1  2  3  4
value:  2  4  3  5  7

When:

i = 1

the j indexes are:

2, 3, 4

and the corresponding values are:

3, 5, 7

I initially confused indexes with values.

My Code
package basic;

public class TargetSum {
    public static void main(String[] args) {

        int[] arr = {2, 4, 3, 5, 7};
        int target = 7;

        for (int i = 0; i < arr.length; i++) {

            for (int j = i + 1; j < arr.length; j++) {

                if (arr[i] + arr[j] == target) {
                    System.out.println(
                        arr[i] + " " + arr[j]
                    );
                }
            }
        }
    }
}

Complexity:

Time: O(n²)
Space: O(1)

This brute-force solution is deliberate.

The reason for learning it this way is to later understand why another approach can beat O(n²).

16. Cyclically Rotate an Array by One

Right rotation by one:

[10, 20, 30, 40]

becomes:

[40, 10, 20, 30]

The last element moves to the front.

Save → Shift → Insert

First:

int last = arr[arr.length - 1];

Then:

for (int i = arr.length - 1; i > 0; i--) {
    arr[i] = arr[i - 1];
}

Then:

arr[0] = last;
Why Right-to-Left?

For:

[10, 20, 30, 40]

Correct shift:

i = 3
arr[3] = arr[2]

i = 2
arr[2] = arr[1]

i = 1
arr[1] = arr[0]

Then:

arr[0] = 40;

Final:

[40, 10, 20, 30]

If we shifted from left to right, values would be overwritten before we copied them.

My Code
package basic;

public class Day05_CyclicRotate {
    public static void main(String[] args) {

        int[] arr = {10, 20, 30, 40};

        int last = arr[arr.length - 1];

        for (int i = arr.length - 1; i > 0; i--) {
            arr[i] = arr[i - 1];
        }

        arr[0] = last;

        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
    }
}

Complexity:

Time: O(n)
Space: O(1)
Alternative I Already Knew

I also implemented right rotation using the reversal method:

reverse(arr, 0, arr.length - 1);
reverse(arr, 0, k - 1);
reverse(arr, k, arr.length - 1);

That also gives:

Time: O(n)
Space: O(1)

But for today's learning objective, the save-and-shift approach is more useful because it reinforces array shifting.

17. Equilibrium Index

An equilibrium index is an index where:

sum of elements on the left
=
sum of elements on the right

The current element is excluded from both sides.

Example:

[-7, 1, 5, 2, -4, 3, 0]

At index 3:

-7  1  5 | 2 | -4  3  0

Left:

-7 + 1 + 5 = -1

Right:

-4 + 3 + 0 = -1

Therefore:

index 3

is an equilibrium index.

Prefix / Running-Sum Idea

Instead of repeatedly calculating left and right sums, calculate the total once.

Formula:

rightSum =
totalSum - leftSum - arr[i]

Reason:

total = left + current + right

Therefore:

right = total - left - current
Algorithm

First calculate total:

int sum = 0;

for (int i = 0; i < arr.length; i++) {
    sum += arr[i];
}

Then:

int leftsum = 0;

for (int i = 0; i < arr.length; i++) {

    int rightsum =
        sum - leftsum - arr[i];

    if (leftsum == rightsum) {
        return i;
    }

    leftsum += arr[i];
}

Important:

Check the current index first.
Update leftSum afterward.

because the current element is not part of its own left side.

My Code
class Solution {
    public static int findEquilibrium(int arr[]) {

        int sum = 0;
        int leftsum = 0;
        int rightsum = 0;

        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
        }

        for (int i = 0; i < arr.length; i++) {

            rightsum =
                sum - leftsum - arr[i];

            if (leftsum == rightsum) {
                return i;
            }

            leftsum += arr[i];
        }

        return -1;
    }
}

Complexity:

Time: O(n)
Space: O(1)

Two linear passes:

O(n) + O(n) = O(n)

Two passes do NOT mean O(n²).

Interview Questions
18. What Is Time Complexity?

My original answer:

"T.C is time taken by the algorithm to run one process."

Better:

Time complexity describes how the amount of work performed by an algorithm grows as the input size grows.

Why it matters:

A solution can work correctly for small test cases but become too slow when the input becomes large, causing TLE.

Interview-quality answer:

Time complexity describes how the number of operations performed by an algorithm grows with input size. It matters because an algorithm that works for small inputs may become too slow for large inputs.

19. Why Is Array Access O(1)?

My answer:

"O(1) because we only deal with one element, we don't traverse the array, and we know the exact position."

The important correction:

It is not simply because we "deal with one element."

The real reason:

Arrays provide direct access to an element using its index, so the operation does not depend on the size of the array.

Example:

arr[500]

Whether the array has:

1,000 elements

or:

10,000,000 elements

the access remains:

O(1)

Interview-quality answer:

Array access by index is O(1) because arrays provide direct access using the index. We don't have to traverse the preceding elements.

20. Second-Largest Element in One Pass

Approach:

Maintain:

largest
secondLargest

If the current element is greater than largest:

secondLargest = largest
largest = current

Otherwise, if it is greater than secondLargest:

secondLargest = current

For the second distinct largest, also make sure the value isn't equal to largest.

Skeleton:

int largest = arr[0];
int secondLargest = Integer.MIN_VALUE;

for (int i = 1; i < arr.length; i++) {

    if (arr[i] > largest) {

        secondLargest = largest;
        largest = arr[i];

    } else if (arr[i] > secondLargest
               && arr[i] != largest) {

        secondLargest = arr[i];
    }
}
Mistake

I initially suggested:

secondLargest = 0;

This fails for:

[-10, -5, -20]

because the second-largest value is:

-10

not 0.

Correct initialization can use:

Integer.MIN_VALUE

when appropriate.

Complexity:

Time: O(n)
Space: O(1)
DSA Complexity Summary
Problem Time Complexity    Auxiliary Space
Union — brute force O(n²)  O(n)
Intersection — brute force  O(n²)  O(n)
Alternate Positive/Negative O(n)   O(n)
Kadane  O(n)   O(1)
Pair Sum — brute force  O(n²)  O(1)
Cyclic Rotation O(n)   O(1)
Equilibrium Index   O(n)   O(1)
Code Patterns to Remember
Direct Array Access
int value = arr[index];
O(1)
Bubble Sort
for (int i = 0; i < n; i++) {

    for (int j = 0; j < n - 1 - i; j++) {

        if (arr[j] > arr[j + 1]) {
            // swap
        }
    }
}
Linear Search
for (int i = 0; i < arr.length; i++) {

    if (arr[i] == target) {
        // found
    }
}
Matrix Transpose
int[][] transpose = new int[columns][rows];

for (int i = 0; i < rows; i++) {
    for (int j = 0; j < columns; j++) {
        transpose[j][i] = matrix[i][j];
    }
}
Unique Intersection
for each element in A:

    search B completely

    search result completely

    if foundInB && !alreadyExists:
        add to result
Union
for each element in A:
    if not already in result:
        add

for each element in B:
    if not already in result:
        add
Alternate Positive / Negative
separate positives and negatives

while both remain:
    add positive
    add negative

append remaining values
Kadane
int currentSum = arr[0];
int maxSum = arr[0];

for (int i = 1; i < arr.length; i++) {

    currentSum =
        Math.max(arr[i], currentSum + arr[i]);

    maxSum =
        Math.max(maxSum, currentSum);
}
Pair Sum
for (int i = 0; i < arr.length; i++) {

    for (int j = i + 1; j < arr.length; j++) {

        if (arr[i] + arr[j] == target) {
            // pair found
        }
    }
}
Right Cyclic Rotation
int last = arr[arr.length - 1];

for (int i = arr.length - 1; i > 0; i--) {
    arr[i] = arr[i - 1];
}

arr[0] = last;
Equilibrium Index
int total = 0;
int leftSum = 0;

for (int value : arr) {
    total += value;
}

for (int i = 0; i < arr.length; i++) {

    int rightSum =
        total - leftSum - arr[i];

    if (leftSum == rightSum) {
        return i;
    }

    leftSum += arr[i];
}
Gotchas & Mistakes I Made
1. Time Complexity Definition

I initially described time complexity as:

"Time taken by the algorithm to run one process."

Correct:

It describes how the amount of work grows as input size grows.

2. Bubble Sort n - 1 - i

I initially couldn't understand why the inner loop uses:

j < n - 1 - i

Correct:

n - 1 → because j + 1 must remain valid
-i    → because i elements are already sorted at the end
3. Intersection Logic

I repeatedly placed the foundInB, alreadyExists, and add logic inside the wrong loop.

Correct order:

Search B completely
↓
Search result completely
↓
Decide whether to add
4. Intersection Flags

I initially reset the flags inside the B loop.

Correct:

for (int i = 0; i < a.length; i++) {

    boolean foundInB = false;
    boolean alreadyExists = false;

    for (int j = 0; j < b.length; j++) {
        ...
    }
}

The flags belong to one a[i], not one b[j].

5. Intersection size

I initially increased size when an element was found in B.

Correct:

found ≠ added

Increase size only after actually placing an element into result.

6. ArrayList

I hadn't studied ArrayList yet.

This was not a Day 5 topic.

Only these GFG-specific operations were encountered:

arr.size()
arr.get(i)
arr.set(i, value)
7. Alternate Positive/Negative

Important rule:

0 is considered positive.

Therefore:

value >= 0

belongs to the positive group.

Also, when one group is exhausted, the remaining elements must be appended while preserving relative order.

8. Kadane Wrong Answer

For:

[-2, 1, -3, 4, -1, 2, 1, -5, 4]

I initially answered:

4

Correct answer:

6

because:

4 + (-1) + 2 + 1 = 6
9. Kadane Initialization

My first implementation:

int currentSum = 0;
int maxSum = 0;

fails for all-negative arrays.

Correct:

int currentSum = arr[0];
int maxSum = arr[0];
10. Pair Sum

For:

[2, 4, 3, 5, 7]
target = 7

I initially gave only:

2

Correct pairs:

(2, 5)
(4, 3)
11. Index vs Value

For:

index:  0  1  2  3  4
value:  2  4  3  5  7

when:

i = 1

the j indexes are:

2, 3, 4

The values at those indexes are:

3, 5, 7

I initially answered the values when asked for indexes.

12. Cyclic Rotation

I initially used the reversal method instead of the simple save-and-shift method.

The reversal solution was correct:

O(n) time
O(1) space

But for this day's learning objective, the save-and-shift approach was better because it reinforces array shifting.

13. Second-Largest Initialization

I initially suggested:

secondLargest = 0;

This fails when all values are negative.

Example:

[-10, -5, -20]

Second largest:

-10

Use an appropriate negative sentinel or initialize from actual array values.

Self-Quiz for Later Review
Q1

Why is array index access O(1)?

A: Because the index provides direct access to the element. We don't need to traverse the preceding elements.

Q2

Why is Bubble Sort's inner condition:

j < n - 1 - i

?

A: n - 1 prevents j + 1 from going outside the array, while -i removes elements that are already placed correctly at the end.

Q3

Why did this Kadane initialization fail?

int currentSum = 0;
int maxSum = 0;

A: It incorrectly allows 0 to be the answer for an all-negative array. The correct maximum must contain an actual array element.

Q4

Why must the intersection duplicate check happen after searching B?

A: Finding an element in B only proves that it belongs to the intersection. I still need to check whether it was already added to the result.

Q5

Why does right cyclic rotation shift from right to left?

A: Shifting from the front would overwrite values before they are copied. Right-to-left preserves the original values.

Revision Task

For two DSA problems, explain out loud why the approach is O(n) rather than O(n²).

Kadane
One traversal.
Constant work for each element.
Therefore O(n).
Equilibrium Index
First pass → calculate total → O(n)
Second pass → check every index → O(n)

O(n) + O(n) = O(n)

Two linear passes are still:

O(n)

not:

O(n²)
GitHub Task

Repository:

java-dsa-prep

Folder:

day05

Commit:

Day 5: arrays deep-dive, Big-O, 6 DSA problems
Coverage Check
Exact Java Topics
 1D arrays — declaration, initialization, traversal
 2D arrays — declaration, initialization, traversal
 Arrays of objects
 Insert
 Delete
 Search
 Reverse
 O(1)
 O(log n)
 O(n)
 O(n²)
Coding Questions
 Bubble Sort
 Linear Search
 Print Matrix and Transpose
DSA Questions
 Union and Intersection
 Alternate Positive/Negative
 Maximum Subarray Sum — Kadane
 Find All Pairs With Given Sum
 Cyclically Rotate Array by One
 Equilibrium Index
Interview Questions
 Time complexity
 Why array access is O(1)
 Second-largest in one pass
Still Shaky On
1. Bubble Sort Loop Bounds

I needed multiple explanations before n - 1 - i became clear.

I should be able to explain it without help.

2. Intersection Nested Loops

The placement of:

foundInB
alreadyExists
size++

was confusing.

I need to remember that each a[i] must be fully processed before deciding whether to add it.

3. ArrayList

I have not formally learned ArrayList yet.

Only the minimum GFG syntax was used.

This should be learned separately later.

4. Kadane All-Negative Case

I understand the normal algorithm, but I should practice:

[-5, -2, -8]

again to make the initialization automatic.

Correct answer:

-2
5. Second-Largest Negative Values

Remember:

secondLargest = 0;

is unsafe if negative values are allowed.

Final Day 05 Assessment
Stronger Areas
Array traversal
Array index access
2D arrays
Matrix transpose
Linear search
Bubble Sort implementation
Union
Pair-sum brute force
Cyclic rotation
Equilibrium Index
Basic Big-O reasoning
Kadane's core logic
Needs Reinforcement
Bubble Sort loop bounds
Nested-loop structure
Variable/flag scope
All-negative edge cases
Second-largest initialization
ArrayList syntax when encountered in GFG
Main Takeaway

The biggest lesson from Day 5 is not a particular algorithm.

It is learning to connect the structure of the code to its complexity:

Direct index access  → O(1)

Single traversal     → O(n)

Nested loops         → O(n²)

Running sum          → avoid repeated calculations

Right-to-left shift  → prevents overwriting

Kadane               → running sum + greedy decision

Equilibrium Index    → total sum + running left sum