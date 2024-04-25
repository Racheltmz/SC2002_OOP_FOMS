# Fastfood Ordering Management System (FOMS Application)

## Introduction

This project was done as part of our SC2002 Object-oriented Programming module. FOMS is a Java application designed to manage orders and staff interactions within a fast food restaurant environment. It is an intuitive interface to streamline the ordering process for customers and the management of business operations and transactions. With error handling and input validation, it enhances system reliability and the user's experience.

## Contributors

Tutorial Group: FDAB  
Group No: Group 3

| Name                        | GitHub Username   |
|-----------------------------|-------------------|
| Gwendalene Ionna            | [gwenionna](https://github.com/gwenionna) |
| Priya Rekah                 | [Priyarekah](https://github.com/Priyarekah) |
| Rachel Tan                  | [Racheltmz](https://github.com/Racheltmz) |
| Rafiabdul Subuhan Afreen    | [afreenrafi](https://github.com/afreenrafi) |
| Sanjana Shanmugasundaram    | [snnjana](https://github.com/snnjana) |


## Project Directory

| Folder name  | Purpose |
| -------- | ------- |
| FOMS | Contains application's source code and libraries|
| UML | Contains class diagrams of our application |
| docs/javadocs | Contains java docs |

## Features

1. App Interface: The main app interface to conduct the implemented actions in respective interfaces.
2. User Interfaces: The system provides separate interfaces for customers and staff, allowing for efficient navigation and interaction.
3. User Management: Utilizes ActiveFactory interface for user instance management, along with hashing passwords for enhanced security.
4. File Operations: Utilizes FileIOHelper class for file input/output operations related to XLSX files, ensuring data integrity, accessibility and persistence.
5. Utilities: Includes Helper classes to verify valid inputs, to format Strings and to initialise an instance of InputScanner; also includes the IFilter interface for implementation in Filtering.
6. Serialization: Implements IXlsxSerializable interface for converting objects to an array of strings representing comma-separated data to be written to an XLSX file.
7. Excel Handling: Utilizes BaseXlsxHelper class and its extensions (StaffXlsxHelper, BranchXlsxHelper, OrderXlsxHelper, MenuItemXlsxHelper) for reading from and writing to Excel (XLSX) files, with specific methods for each data type.
8. Data Management: Includes classes such as Branch, Menu, MenuItem, Staff, Manager, Admin, and related directories (BranchDirectory, MenuDirectory, StaffDirectory) for managing branch, menu, and staff records. Authorises access to specific management actions based on staff roles.
9. Authentication and Authorization: Implements Authentication and Authorization classes for verifying login credentials and authorizing access based on staff roles.
10. Filtering: Provides filtering functionalities for staff members based on age, branch, gender and role using StaffFilterAge, StaffFilterBranch, StaffFilterGender and StaffFilterRole classes respectively.
11. Order Management: Manages orders through the Order class, OrderQueue class, and OrderTimerTask class for processing orders and updating order status.
12. Payment Handling: Includes Payment and PaymentDirectory classes for managing payment methods, with authorization based on staff roles.
13. User Interaction: Utilizes ManagerActionsMenu class for accessing branch and menu information.
14. Exception Handling: Implements exception handling throughout the system to ensure effective error management.

## Set Up

To run the app application locally, follow these steps:

1. Clone the repository to your local machine.

       https://github.com/Racheltmz/SC2002_OOP_FOMS.git

2. Ensure you have Java installed on your system.
3. Open the app folder in your preferred Java IDE.
4. Compile and run the app.FOMSApp.java file.
5. Upon running the application, users will be presented with a menu to choose between the customer interface, staff interface, or quitting the application. Follow the prompts to navigate through the system and interact with its features.

## Dependencies

| Library  | Version | Purpose                             |
| -------- | ------- | ----------------------------------- |
| poi, poi-ooxml, poi-ooxml-schemas | 4.1.2 | Apache POI for Excel file handling. |
| commons-collections | 4.3 | Apache Commons to create and maintain reusable Java Components, |
| commons-compress | 1.18 | Apache Commons Compress to utilise widely used compression and archiving formats. |
| xmlbeans | 3.1.0 | To access XML by binding it to Java types |
