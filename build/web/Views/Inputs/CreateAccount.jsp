<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <!-- Bootstrap CSS -->
        <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
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

<script type="text/javascript">

//ELEMENTS
    const create_account_btn = document.getElementById("create_account_btn");
    const first_name = document.getElementById("name");
    const last_name = document.getElementById("surname");
    const username = document.getElementById("username");
    const sa_id = document.getElementById("sa_id");
    const password = document.getElementById("password");
    const confirm_password = document.getElementById("confirm_password");
    const form = document.getElementById("form");
//ERROR ELEMENTS
    const first_name_error = document.getElementById("name_error");
    const username_error = document.getElementById("username_error");
    const last_name_error = document.getElementById("surname_error");
    const sa_id_error = document.getElementById("sa_id_error");
    const passsword_error = document.getElementById("password_error");


    let password_valid = false;
    let username_valid = false;
    let first_name_valid = false;
    let last_name_valid = false;
    let id_valid = false;

    //button event handler
    create_account_btn.addEventListener("click", (event) => {
        event.preventDefault(); // Prevent form submission

        // Validate First Name entry
        if (first_name.value === "") {
            updateErrorView(first_name_error, "You must enter a first name.");
        } else if (checkDigitPresence(first_name.value)) {
            updateErrorView(first_name_error, "Invalid first name, no numbers allowed.");
        } else {
            updateErrorView(first_name_error, ""); // Clear error message if valid
            first_name_valid = true;
        }

        //validate username entry
        if (username.value === "") {
            updateErrorView(username_error, "You must fill the username field");
        } else {
            updateErrorView(username_error, "");
            username_valid = true;

        }

        // Validate Last Name entry
        if (last_name.value === "") {
            updateErrorView(last_name_error, "You must enter a last name.");
        } else if (checkDigitPresence(last_name.value)) {
            updateErrorView(last_name_error, "Invalid last name, no numbers allowed.");
        } else {
            updateErrorView(last_name_error, ""); // Clear error message if valid
            last_name_valid = true;
        }


        //validate id entry
        if (sa_id.value === "") {
            updateErrorView(sa_id_error, "You must fill the id field");
        } else if (!validateUserIdNumber(sa_id.value)) {
            updateErrorView(sa_id_error, "Invalid ID Number");
        } else {
            updateErrorView(sa_id_error, "");
            id_valid = true;
        }

        ///validate password entry
        if (password.value === "" || confirm_password === "") {
            updateErrorView(password_error, "You must fill the pasword fields");
        } else if (!passwordSimilar(password.value, confirm_password.value)) {
            updateErrorView(password_error, "Password and Confirm Password don't match");

        } else if (!validatePassword(password.value)) {
            updateErrorView(password_error, "Invalid Password");
        } else {
            updateErrorView(password_error, "");
            password_valid = true;
        }


        if (password_valid && username_valid && first_name_valid && last_name_valid && id_valid) {
            document.getElementById("form").submit();
        }

    });
    //validates password
    function validatePassword(user_password) {
        let upper = 0, lower = 0, special = 0, digit = 0;
        for (let i = 0; i < user_password.length; i++) {
            let single_char = user_password.charAt(i);
            if (/[A-Z]/.test(single_char)) {
                upper++;
            } else if (/[a-z]/.test(single_char)) {
                lower++;
            } else if (/[0-9]/.test(single_char)) {
                digit++;
            } else if (/[^A-Za-z0-9]/.test(single_char)) {
                special++;
            }
        }

        let characters = user_password.length;
        return (upper >= 1 && lower >= 1 && special >= 2 && digit >= 2 && characters >= 8);
    }//end


    //check if password and confirm password are equals
    function passwordSimilar(password, confirm_password) {
        return password === confirm_password;
    }//end

    //validate South African ID  number
    function validateUserIdNumber(user_id) {

        if (user_id.length !== 13)
            return false;
        const birth_month = Number(user_id.substring(2, 4));
        if (birth_month < 1 || birth_month > 12)
            return false;
        const birth_day = Number(user_id.substring(4, 6));
        if (birth_day < 1 || birth_day > 31)
            return false;
        return true;
    }//end


    function checkDigitPresence(user_details) {
        for (let i = 0; i < user_details.length; i++) {
            let single_char = user_details.charAt(i);
            if (!isNaN(single_char)) { // Check if the character is a digit
                return true; // Digit found, return true
            }
        }
        return false; // No digits found, return false
    }

    function updateErrorView(element_name, error_message) {
        element_name.style.color = "red";
        element_name.innerText = error_message;
    }

</script>
