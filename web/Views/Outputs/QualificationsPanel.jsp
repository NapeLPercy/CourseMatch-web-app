<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="Entities.Qualification"%>
<%@page import="java.util.*"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Qualification Results</title>
        <!-- Bootstrap CSS -->
        <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet">
        <style>
            body {
                font-family: Arial, sans-serif;
                margin: 20px;
            }
            h1, h3 {
                color: #333;
                margin-top: 20px;
            }

            body {
                font-family: Arial;
            }

            span {
                font-weight: bold;
                font-family: Sans-Serif;
                text-decoration: underline;
                letter-spacing: 1.50px;
                margin-bottom: 2px;
            }

            #navbarNav {
                margin-left: 300px;
            }

            .content-container {
                margin-top: 200px; /* 350px from the navigation menu */
            }

            .navbar-nav{
                margin-left: 600px;
            }

            .navbar navbar-expand-lg navbar-light bg-light{
                width: 140vw;
            }

            h2{
                margin-top: 20px;
            }
            #reasons{
                margin-left: 10px;
                margin-top: 10px;

            }

            #no_courses{
                margin: 20px;

            }

            #course_header{
                margin: 50px;
                text-align: center;
            }

        </style>
    </head>
    <body>
        <!-- Navigation Bar -->
        <nav class="navbar navbar-expand-lg navbar-light bg-light">
            <a class="navbar-brand" href="/CourseMatch/Views/Menu.jsp">Course<span>Match</span></a>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarNav">
                <ul class="navbar-nav">
                    <li class="nav-item">
                        <a class="nav-link" href="/CourseMatch/LogoutController" id="logout">Logout</a>
                    </li>
                </ul>
            </div>
        </nav>
        <!-- Body -->
        <%
            ArrayList<Qualification> qualifications = (ArrayList<Qualification>) request.getAttribute("qualifications");

            if (qualifications == null || qualifications.isEmpty()) {
        %>
        <div id="no_courses">
            <h1>No qualifications to show</h1>
            <h2>Possible reason(s): </h2>
            <p id="reasons">1. You did not submit your matrix results</p>
            <p id="reasons">2. You do not meet any minimum qualifications requirement(s)</p>
        </div>
        <%
            } else {
           String courses_unlocked =  request.getAttribute("number_of_courses").toString();
        %>
        <div class="container mt-4">
            <h3 id="course_header">You have unlocked <%=courses_unlocked%> qualification(s)</h3>
            <div class="row">
                <%
                    for (int i = 0; i < qualifications.size(); i++) {
                        Qualification qualification = qualifications.get(i);
                        String[] facultyInfo = qualification.getFaculty().split("#");
                        String faculty = facultyInfo[0];
                        String university = facultyInfo[1];

                        String logo = "";
                        if (university.equals("TUT")) {
                            logo = "tut.jpg";
                        } else if (university.equals("UCT")) {
                            logo = "uct.jpg";  // Example for other universities
                        } // Add other universities as needed
                %>
                <div class="col-md-4">
                    <div class="card mb-4">
                        <div class="row no-gutters">
                            <div class="col-md-4">
                                <img src="Icons/<%= logo %>" class="card-img" alt="logo">
                            </div>
                            <div class="col-md-8">
                                <div class="card-body">
                                    <p class="card-text"><strong>University:</strong> <%= university %></p>
                                    <p class="card-text"><strong>Course:</strong> <%= qualification.getName() %></p>
                                    <p class="card-text"><strong>Course Code:</strong> <%= qualification.getCode() %></p>
                                    <p class="card-text"><strong>Minimum APS:</strong> <%= qualification.getMinimun_aps() %></p>
                                    <p class="card-text"><strong>Faculty:</strong> <%= faculty %></p>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <%
                    }
                %>
            </div>
        </div>
        <%
            }
        %>

        <!-- Bootstrap JS and dependencies -->
        <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.11.0/umd/popper.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
    </body>
</html>