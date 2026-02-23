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


