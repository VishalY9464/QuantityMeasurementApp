# Quantity Measurement App

🧾 Project Overview

The Quantity Measurement App is a Test-Driven Development (TDD) based project designed to demonstrate how to build scalable, maintainable software by starting simple and progressively adding complexity.

The application focuses on comparing and converting length measurements across different units while strictly following:

Test Driven Development (TDD)

Incremental Development

Clean Code Principles

DRY (Don't Repeat Yourself)

Proper Git Workflow (feature branches + PR)

The project is built step-by-step through Use Cases (UCs).
Each UC introduces a small feature and refactors the design to keep the code maintainable and extensible.

🧪 Development Methodology

This project follows the TDD Cycle:

🔴 Write failing test

🟢 Write minimal code to pass

🔵 Refactor without breaking tests

This ensures:

Safety

Maintainability

Scalability

🌳 Git Workflow Used

We followed a professional branching strategy:

main → stable production code

dev → integration branch

feature/UCx-* → individual feature branches

Each UC was:

Developed in feature branch

Tested locally

Pushed & PR created

Merged into dev

📚 USE CASE IMPLEMENTATION
## 🟢 UC1 — Feet Equality
🎯 Goal

Compare two Feet measurements for equality.

🧪 Tests Written

We validated the equals contract:

Same value → equal

Different value → not equal

Null comparison → false

Different object type → false

Same reference → true

💻 Implementation

Created Feet class with:

value field

equals() method

🧠 Learning Outcome

Understanding equality contract

First step of TDD

## 🟢 UC2 — Inches Equality
🎯 Goal

Support Inches unit in addition to Feet.

🧪 Tests Written

Repeated same equality tests for:

Inches = Inches

💻 Implementation

Created Inches class similar to Feet.

⚠️ Problem Observed

Huge code duplication:

Feet and Inches had identical logic.

🧠 Learning Outcome

Recognized need for refactoring (DRY violation).

## 🔵 UC3 — Refactor to Generic Length Class
🎯 Goal

Remove duplication by introducing a generic measurement model.

🛠 Refactoring Done

Removed:

❌ Feet class

❌ Inches class

Introduced:

✅ Length class

✅ LengthUnit enum

🧠 Core Design Change

Instead of multiple classes:

Feet
Inches

We created one generic model:

Length(value, LengthUnit)
📐 Base Unit Concept

All units converted internally to INCHES (base unit).

FEET  → 12 inches
INCHES → 1 inch

Added method:

convertToBaseUnit()
🧪 Tests Covered

✔ Feet = Feet
✔ Inches = Inches
✔ 1 Foot = 12 Inches
✔ Symmetry
✔ Transitive equality
✔ equals contract validation

🧠 Learning Outcome

Refactoring safely using tests

Generic design

Domain modeling

DRY principle
