<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Account Created</title>
        <!-- Include Bootstrap CSS -->
        <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
        <style>
            .centered-container {
                display: flex;
                flex-direction: column;
                align-items: center;
                justify-content: center;
                height: 100vh;
                text-align: center;
            }
            .centered-container > * {
                margin: 20px 0; /* 40px total spacing (20px top + 20px bottom) */
            }
            .get-started-button {
                display: inline-block;
                padding: 10px 20px;
                font-size: 16px;
                font-weight: bold;
                color: #fff;
                background-color: #007bff;
                border-radius: 5px;
                text-decoration: none;
                transition: background-color 0.3s ease;
            }
            .get-started-button:hover {
                text-decoration: none;
                background-color: #0056b3; /* Change to a darker blue on hover */
                color: #fff; /* Ensure the text color remains white */
            }
        </style>
    </head>
    <body>
        <%
         if(session.getAttribute("username") == null){
                             response.sendRedirect("/CourseMatch/index.html");
                             return;
            }
        %>

        <div class="centered-container">
            <img src="Icons/success.png" width="100" height="100" alt="Check"/>
            <p>Subjects successfully updated!</p>
            <div>
                <a href="/CourseMatch/ShowSubjectsController" class="get-started-button">View Subjects</a>
            </div>
        </div>
        <!-- Include Bootstrap JS and dependencies (Optional for some components) -->
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    </body>
</html>

