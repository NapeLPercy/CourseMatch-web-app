<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Menu</title>
        <!-- Bootstrap CSS -->
        <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet">
        <style>
            span {
                font-weight: bold;
                font-family: Sans-Serif;
                text-decoration: underline;
                letter-spacing: 1.50px;
                margin-bottom: 2px;
            }

            body {
                font-family: Arial;
            }

            #navbarNav {
                margin-left: 600px;
            }

            .content-container {
                margin-top: 200px; /* 350px from the navigation menu */
            }
        </style>
    </head>
    <body>
        <!-- Navigation Bar -->
        <nav class="navbar navbar-expand-lg navbar-light bg-light">
            <a class="navbar-brand" href="#">Course<span>Match</span></a>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarNav">
                <ul class="navbar-nav">
                    <li class="nav-item">
                        <a class="nav-link" href="/CourseMatch/AddSubjectsValidatorController">Add Subjects</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="/CourseMatch/ShowSubjectsController">Edit/View Subjects</a>
                    </li>

                    <li class="nav-item">
                        <a class="nav-link" href="/CourseMatch/QualificationController">View Courses</a>
                    </li>
                    
                    <li class="nav-item">
                        <a class="nav-link" href="/CourseMatch/LogoutController">Logout</a>
                    </li>
                </ul>
            </div>
        </nav>

        <%
            String name =  session.getAttribute("student_name").toString();
            String surname = session.getAttribute("student_surname").toString();
        %>

        <!-- Content -->
        <div class="container content-container">
            <p>Hello <%= surname %> <%= name %>, </p>
            <h1>Welcome to CourseMatch</h1>
            <p>Use the navigation bar to add or edit subjects,  and view courses.</p>
        </div>

        <!-- Bootstrap JS and dependencies -->
        <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.11.0/umd/popper.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
    </body>
</html>
<script type="text/javascript"></script>
