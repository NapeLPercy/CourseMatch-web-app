<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <!-- Bootstrap CSS -->
        <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
        <script src="/CourseMatch/CreateAccount.js" defer></script>
        <title>Create Account</title>

        <style>
            .custom-input {
                width: 350px; /* Set width to 200px */
            }
            button{
                width: 200px;
                margin-bottom: 100px;
            }

            body{
                font-family: Arial;
            }
            p{
                letter-spacing: 1.2px;
                font-size: 14px;
                transition: color 0.5s ease, opacity 5s ease, font-size 10s ease;
                margin-left: 5px;
            }
        </style>
    </head>
    <body>
        <div class="container mt-5">
            <form action="/CourseMatch/UserController" method="POST" id="form">
                <fieldset>
                    <legend>Create Account</legend>

                    <div class="form-group">
                        <label for="name">First Name</label>
                        <p id="name_error"></p>
                        <input type="text" class="form-control custom-input" id="name" name="name">
                    </div>

                    <div class="form-group">
                        <label for="surname">Last Name</label>
                        <p id="surname_error"></p>
                        <input type="text" class="form-control custom-input" id="surname" name="surname">
                    </div>

                    <div class="form-group">
                        <label for="sa_id">ID Number</label>
                        <p id="sa_id_error"></p>
                        <input type="number" class="form-control custom-input" id="sa_id" name="sa_id">
                    </div>

                    <div class="form-group">
                        <label for="username">Username</label>
                        <p id="username_error"></p>
                        <input type="text" class="form-control custom-input" id="username" name="username">
                    </div>

                    <div class="form-group">
                        <label for="gender">Gender</label>
                        <select class="form-control custom-input" id="gender" name="gender">
                            <option value="Male">Male</option>
                            <option value="Female">Female</option>
                        </select>
                    </div>


                    <div class="form-group">
                        <p id="password_error"></p>
                        <label for="password">Password</label>
                        <input type="password" class="form-control custom-input" id="password" name="password" >
                    </div>

                    <div class="form-group">
                        <label for="confirm_password">Confirm Password</label>
                        <input type="password" class="form-control custom-input" id="confirm_password" name="confirm_password" >
                    </div>

                    <button type="submit" id="create_account_btn" class="btn btn-primary">Create Account</button>

                </fieldset>
            </form>

    </body>
</html>

<!-- Include jQuery -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>

<!-- Bootstrap JS (make sure this comes after jQuery) -->
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
