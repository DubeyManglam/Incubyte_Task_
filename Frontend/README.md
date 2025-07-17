# Sweet Shop Management System - Frontend

This is the frontend application for the Sweet Shop Management System, built using ReactJS.  
It communicates with the Spring Boot backend through REST APIs.

## Features Implemented

- List all sweets in a card-based layout.
- Add new sweets with details like name, category, price, and quantity.
- Search sweets by name, category, and price range.
- Sort sweets by price (ascending/descending) and quantity (ascending/descending).
- Purchase sweets and reduce stock accordingly.
- Restock sweets and increase stock.
- Delete sweets from the list.
- Display success and error notifications for all actions.
- Responsive user interface with Bootstrap.

## Technologies Used

- ReactJS
- Axios for API communication
- Bootstrap 5 for styling
- React-hot-toast for notifications
- Spring Boot REST API (backend)

## Project Structure
```
/Frontend
├── src
│ ├── components
│ │ ├── Navbar.jsx
│ │ ├── SweetListPage.jsx
│ │ ├── AddSweetForm.jsx
│ │ └── SweetCard.jsx
│ ├── service
│ │ └── ApiSweet.js
│ ├── App.jsx
│ └── main.jsx
├── public
├── package.json
├── README.md
```
## Author

Preksha Divraniya  
This project is built as part of the Incubyte TDD Kata Assessment.
