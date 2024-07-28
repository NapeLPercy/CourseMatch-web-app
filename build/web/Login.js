
document.getElementById("login_btn")
        .addEventListener("click", (event) => {
            event.preventDefault();

           //Ensures a user enter a username and a password
            const username = document.getElementById("username");
            const password = document.getElementById("password");

            var password_error = document.getElementById("password_error");
            var username_error = document.getElementById("username_error");

            if (username.value === "" || password.value === "") {

                if (username.value === "") {
                    updateErrorView(username_error, "Username is required.");
                } else {
                    updateErrorView(username_error, "");
                }

                if (password.value === "") {
                    updateErrorView(password_error, "Password is required.");
                } else {
                    updateErrorView(password_error, "");
                }

            } else {//user has entered something, not necessarily valid credentials
                updateErrorView(username_error, "");
                updateErrorView(password_error, "");
                login(username.value, password.value);
            }


            username.addEventListener("input", () => {
                updateErrorView(username_error, "");
            });

            password.addEventListener("input", () => {
                updateErrorView(password_error, "");
            });
        });




//Validates user's Login through xhr and updates UI incase of invalid login. Otherwise the user will login
function login(username, password) {

    const xhr = new XMLHttpRequest();

    xhr.open("GET", "/CourseMatch/LoginController?username="
            + encodeURIComponent(username) + "&password=" + encodeURIComponent(password), false);

    xhr.onreadystatechange = function () {
        if (xhr.readyState === 4) {
            if (xhr.status === 200) {
                try {
                    const response = JSON.parse(xhr.responseText);

                    if (response.valid) {
                        document.querySelector("form").submit();//logs in the user
                    } else {
                        updateErrorView(document.getElementById("invalid_login"), "Wrong username or password.");
                    }
                } catch (e) {
                    console.error("Invalid JSON response:", e);
                    alert("Server error: Invalid response format.");
                }
            } else {
                console.error("Request failed with status:", xhr.status);
                alert("Server error: " + xhr.status);
            }
        }
    };

    xhr.send();
}

//Updated the UI incase invalid login and Empty field submission
function updateErrorView(element_name, error_message) {
    element_name.style.color = "red";
    element_name.innerText = error_message;
}

