# Fastfood Ordering Management System (app)

## Introduction

This project was done as part of our SC2002 Object-oriented Programming module at NTU. app is a Java application designed to manage orders and staff interactions within a fast food restaurant environment. It is an intuitive interface to streamline the ordering process for customers and the management of business operations and transactions. With error handling and input validation, it enhances system reliability and the user's experience.

## Contributors

Tutorial Group: FDAB<br/>
Group No.: Group 3

<div align="center">
  <a href="https://github.com/gwenionna" target="_blank"><img src="https://github.com/Racheltmz/SC2002_OOP_FOMS/raw/main/your_cool_intro%20(3).gif" width="100px;" alt="" style="border-radius:50%;"/></a>
  <a href="https://github.com/Priyarekah" target="_blank"><img src="https://github.com/Racheltmz/SC2002_OOP_FOMS/raw/main/your_cool_intro%20(1).gif" width="100px;" alt="" style="border-radius:50%;"/></a>
  <a href="https://github.com/lousyway" target="_blank"><img src="https://github.com/Racheltmz/SC2002_OOP_FOMS/raw/main/your_cool_intro%20(2).gif" width="100px;" alt="" style="border-radius:50%;"/></a>
  <a href="https://github.com/afreenrafi" target="_blank"><img src="https://github.com/Racheltmz/SC2002_OOP_FOMS/raw/main/your_cool_intro%20(5).gif" width="100px;" alt="" style="border-radius:50%;"/></a>
  <a href="https://github.com/snnjana" target="_blank"><img src="https://github.com/Racheltmz/SC2002_OOP_FOMS/raw/main/your_cool_intro%20(4).gif" width="100px;" alt="" style="border-radius:50%;"/></a>
</div>

<div align="center">
  <sub><b>Gwen</b></sub>
  <sub><b>Priya</b></sub>
  <sub><b>Rachel</b></sub>
  <sub><b>Afreen</b></sub>
  <sub><b>Sanjana</b></sub>
</div>



## Project Directory

| Folder name  | Purpose |
| -------- | ------- |
| app| Contains application's source code and libraries|
| UML | Contains class diagrams of our application |
| Docs | Contains java docs | 

## Features

[TO ADD: cover it based on our packages, and do so in an order that makes sense, not alphabetical order]

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
