//Create Account button event handler
document.getElementById("create_account_btn")
        .addEventListener("click", (event) => {

            event.preventDefault();

            // Validate First Name entry
            const first_name = document.getElementById("name");
            const first_name_error = document.getElementById("name_error");

            if (first_name.value === "") {
                updateErrorView(first_name_error, "First Name is required.");
                isValid = false;
            } else if (checkDigitPresence(first_name.value)) {
                updateErrorView(first_name_error, "Invalid First Name, no numbers allowed.");
                isValid = false;
            } else {
                updateErrorView(first_name_error, "");
                isValid = true;
            }

            //Validate username entry
            const username = document.getElementById("username");
            const username_error = document.getElementById("username_error");

            if (username.value === "") {
                updateErrorView(username_error, "Username  is required.");
                isValid = false;
            } else {
                updateErrorView(username_error, "");
                isValid = true;
            }

            // Validate Last Name entry
            const last_name = document.getElementById("surname");
            const last_name_error = document.getElementById("surname_error");

            if (last_name.value === "") {
                updateErrorView(last_name_error, "Last Name  is required.");
                isValid = false;
            } else if (checkDigitPresence(last_name.value)) {
                updateErrorView(last_name_error, "Invalid Last Name, no numbers allowed.");
                isValid = false;
            } else {
                updateErrorView(last_name_error, "");
                isValid = true;
            }


            //Validate ID number entry
            const sa_id = document.getElementById("sa_id");
            const sa_id_error = document.getElementById("sa_id_error");

            if (sa_id.value === "") {
                updateErrorView(sa_id_error, "ID Number  is required.");
                isValid = false;
            } else if (!validateUserIdNumber(sa_id.value)) {
                updateErrorView(sa_id_error, "Invalid ID Number");
                isValid = false;
            } else {
                updateErrorView(sa_id_error, "");
                isValid = true;
            }


            //Validate password entry
            const password = document.getElementById("password");
            const confirm_password = document.getElementById("confirm_password");
            const password_error = document.getElementById("password_error");

            if (password.value === "" || confirm_password === "") {
                updateErrorView(password_error, "Password is required.");
                isValid = false;
            } else if (!passwordSimilar(password.value, confirm_password.value)) {
                updateErrorView(password_error, "Password and Confirm Password don't match");
                isValid = false;

            } else if (!validatePassword(password.value)) {
                updateErrorView(password_error, "Invalid Password");
                isValid = false;
            } else {
                updateErrorView(password_error, "");
                isValid = true;
            }

            console.log(isValid);
            if (isValid) {
                document.getElementById("form").submit();
            }

        });

//Validates password
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


//Checks if password and confirm password are equal
function passwordSimilar(password, confirm_password) {
    return password === confirm_password;
}//end

//Validate South African ID  number
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


//Ensures that a username is valid(No digits)
function checkDigitPresence(user_details) {
    for (let i = 0; i < user_details.length; i++) {
        let single_char = user_details.charAt(i);
        if (!isNaN(single_char)) {
            return true;
        }
    }
    return false;
}

//Updates the UI(error messages)
function updateErrorView(element_name, error_message) {
    element_name.style.color = "red";
    element_name.innerText = error_message;
}
