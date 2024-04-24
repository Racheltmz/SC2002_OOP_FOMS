# Fastfood Ordering Management System (FOMS)

## Introduction

FOMS is a Java application designed to manage orders and staff interactions within a fast food restaurant environment. It provides interfaces for both customers and staff members to interact with the system.

## Project Directory

[TO ADD]

## Authors

Tutorial Group: FDAB<br/>
Group No.: Group 3

- Gwendalene Ionna
- Priya Rekah
- Rachel Tan
- Rafiabdul Subuhan Afreen
- Sanjana Shanmugasundaram

## Features

1. User Interfaces: The system provides separate interfaces for customers and staff, allowing for efficient navigation and interaction.
2. User Management: Utilizes ActiveFactory interface for user instance management, along with hashing passwords for enhanced security.
3. File Operations: Utilizes FileIOHelper class for file input/output operations related to XLSX files, ensuring data integrity and accessibility.
4. Serialization: Implements IXlsxSerializable interface for converting objects to an array of strings representing data to be written to an XLSX file.
5. Excel Handling: Utilizes BaseXlsxHelper class and its extensions (StaffXlsxHelper, BranchXlsxHelper, OrderXlsxHelper, MenuItemXlsxHelper) for reading from and writing to Excel (XLSX) files, with specific methods for each data type.
6. Data Management: Includes classes such as Branch, Menu, MenuItem, Staff, Manager, Admin, and related directories (BranchDirectory, MenuDirectory, StaffDirectory) for managing branch, menu, and staff records.
7. Authentication and Authorization: Implements Authentication and Authorization classes for verifying login credentials and authorizing access based on staff roles.
8. Filtering: Provides filtering functionalities for staff members based on age, branch, and role using StaffFilterAge, StaffFilterBranch, and StaffFilterRole classes.
9. Order Management: Manages orders through the Order class, OrderQueue class, and OrderTimerTask class for processing orders and updating order status.
10. Payment Handling: Includes Payment and PaymentDirectory classes for managing payment methods, with authorization based on staff roles.
11. User Interaction: Utilizes ManagerActionsMenu class for accessing branch and menu information, with user input handled by the InputScanner class.
12. Exception Handling: Implements exception handling throughout the system to ensure effective error management.

## Usage

Upon running the application, users will be presented with a menu to choose between the customer interface, staff interface, or quitting the application. Follow the prompts to navigate through the system and interact with its features.

## Installation

To run the FOMS application locally, follow these steps:

1. Clone the repository to your local machine.

        https://github.com/Racheltmz/SC2002_OOP_FOMS.git

2. Ensure you have Java installed on your system.
3. Open the project in your preferred Java IDE.
4. Compile and run the FOMSApp.java file.

## Dependencies

- Java Development Kit (JDK)
- Apache POI for Excel file handling

## License

This project is licensed under the [MIT License](LICENSE).
