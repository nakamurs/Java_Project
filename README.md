# Shape Intersection Project

This project involves implementing and testing a collection of shape classes and their intersection methods using Java. The project is divided into two parts: **Part 1** (shape creation and intersection implementation) and **Part2** (testing the implementation and handling exceptions).

## Table of Contents
- [Overview](#overview)
- [Part 1: Shape Classes and Intersection Methods](#assignment-1-shape-classes-and-intersection-methods)
  - [Class Diagram](#class-diagram)
  - [Shape Classes](#shape-classes)
  - [Intersection Detection](#intersection-detection)
- [Part 2: Testing and Exception Handling](#assignment-2-testing-and-exception-handling)
  - [Task 1: Test of Intersection](#task-1-test-of-intersection)
  - [Task 2: Exception Handling](#task-2-exception-handling)

---

## Overview

This project consists of creating and testing various shape classes, such as circles, rectangles, and line segments, to detect their intersections. The project emphasizes object-oriented principles, unit testing, and exception handling in Java.

---

## Part 1: Shape Classes and Intersection Methods

### Class Diagram

The provided UML class diagram describes the structure and relationships between the shape classes, including `AbstractShape`, `Rectangle`, `LineSeg`, `Point`, and `Circle`. 

### Shape Classes

- **AbstractShape**: An abstract class that defines common properties and methods for all shapes.
- **Point**: Represents a 2D point with `x` and `y` coordinates.
- **Circle**: Defined by a center point and radius.
- **Rectangle**: Defined by its left, right, top, and bottom boundaries.
- **LineSeg**: A line segment defined by two endpoints (`Point`).

Each shape is designed to be created with valid parameters, such as a positive radius for circles and non-overlapping boundaries for rectangles. All shapes are placed in their own separate Java files:
- [`AbstractShape.java`](./AbstractShape.java)
- [`Point.java`](./Point.java)
- [`Circle.java`](./Circle.java)
- [`Rectangle.java`](./Rectangle.java)
- [`LineSeg.java`](./LineSeg.java)

### Intersection Detection

Each shape class implements an `intersect` method to detect whether two shapes intersect. The method checks for solid intersection, meaning that edges touching without overlap are not considered intersections. For example:
- `rectangle.intersect(circle)` and `circle.intersect(rectangle)` should return `true` if they overlap.
- If two shapes only connect at the edges, `rectangle.intersect(circle)` and `circle.intersect(rectangle)` should return `false`.

---

## Part 2: Testing and Exception Handling

### Task 1: Test of Intersection

In this part of the project, we test the intersection methods for all shape classes using **JUnit**.

#### Test Case Design

Test cases are designed to cover a variety of intersection situations for each shape. Each test case includes:
- The two shapes involved.
- Expected result (true/false).
- Code result (true/false).
- Test result (pass/fail).

The tests are implemented in the file [`A2Test.java`](./A2Test.java), while the detailed test cases are documented in the file [`A2Test.pdf`](./A2Test.pdf).

### Task 2: Exception Handling

In **Task 2**, we handle invalid shapes and throw custom exceptions. The goal is to catch illegal shape construction scenarios, such as:
- A line segment with coincided begin and end points.
- A circle with radius less than or equal to 0.
- A rectangle where `left >= right` or `bottom >= top`.

For these cases, we define a custom exception class [`ShapeArgumentException.java`](./ShapeArgumentException.java), which inherits from `Exception`. The illegal cases are handled using a `try-catch` block, and if an exception is caught, the message printed is:  
`ShapeArgumentException in constructing <ShapeClassName>`.
