<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>

        <select id="sort">
            <option value="name">Name</option> 
            <option value="aps">APS</option>
            <option value="endorsement">Endorsement</option>      
        </select>

        <input  type="search" id="search"/>
        <button>Search</button>
        <div id="main-container">

        </div>
    </body>

    <script type="text/javascript">

        //  SEARCHING
        document.querySelector("button")
                .addEventListener("", (event) => {
                    event.preventDefault();

                    // nameSearch();
                });

        document.querySelector("#search")
                .addEventListener("input", (event) => {
                    event.preventDefault();


                    document.getElementById("main-container").innerHTML = "";

                    //   console.log(event.target.value);
                    nameSearch(event.target.value);
                });
//   xhr.open("GET", "/PollHub/LoginController?username=" + encodeURIComponent(username) +
        //  "&password=" + encodeURIComponent(password), true);

        const nameSearch = (key) => {

            var xhr = new XMLHttpRequest();
            xhr.open("GET", "/CourseMatch/Controller?search=" + encodeURIComponent(key), true);
            xhr.setRequestHeader("type", "search");

            xhr.onreadystatechange = function () {
                if (this.readyState === 4 && this.status === 200) {
                    var data = JSON.parse(xhr.responseText).data;
                    // console.log(data[i]);

                    for (let i = 0; i < data.length; i++) {

                        var main = document.getElementById("main-container");

                        var inner = document.createElement("div");
                        var name = document.createElement("p");
                        name.innerHTML = data[i].name;
                        var aps = document.createElement("p");
                        aps.innerHTML = data[i].APS;

                        inner.appendChild(name);
                        inner.appendChild(aps);

                        main.appendChild(inner);
                    }
                }
            };

            xhr.send();
        };





        //SORTING
        var sortOption = document.querySelector("#sort");
        sortOption.addEventListener("change", (event) => {

            document.getElementById("main-container").innerHTML = "";

            const target = event.target.value;
            //Adjust the ui according to use choice
            if (target === "name") {
                nameSort();
            } else if (target === "endorsement") {
                endorsementSort();
            } else if (target === "aps") {
                apsSort();
            }
        });

        const nameSort = () => {

            var xhr = new XMLHttpRequest();
            xhr.open("GET", "/CourseMatch/Controller", true);
            xhr.setRequestHeader("type", "name");

            xhr.onreadystatechange = function () {
                if (this.readyState === 4 && this.status === 200) {
                    var data = JSON.parse(xhr.responseText).data;
                    // console.log(data[i]);

                    for (let i = 0; i < data.length; i++) {

                        var main = document.getElementById("main-container");

                        var inner = document.createElement("div");
                        var name = document.createElement("p");
                        name.innerHTML = data[i].name;
                        var aps = document.createElement("p");
                        aps.innerHTML = data[i].APS;

                        inner.appendChild(name);
                        inner.appendChild(aps);

                        main.appendChild(inner);
                    }
                }
            };
            xhr.send();
        };

        const endorsementSort = () => {

            var xhr = new XMLHttpRequest();
            xhr.open("GET", "/CourseMatch/Controller", true);
            xhr.setRequestHeader("type", "endorsement");

            xhr.onreadystatechange = function () {
                if (this.readyState === 4 && this.status === 200) {
                    
                    var data = JSON.parse(xhr.responseText).data;
                    // console.log(data[i]);

                    for (let i = 0; i < data.length; i++) {

                        var main = document.getElementById("main-container");


                        var inner = document.createElement("div");
                        var name = document.createElement("p");
                        name.innerHTML = data[i].name;
                        var aps = document.createElement("p");
                        aps.innerHTML = data[i].APS;

                        inner.appendChild(name);
                        inner.appendChild(aps);

                        main.appendChild(inner);
                    }
                }
            };

            xhr.send();
        };


        const apsSort = () => {

            var xhr = new XMLHttpRequest();
            xhr.open("GET", "/CourseMatch/Controller", true);
            xhr.setRequestHeader("type", "aps");

            xhr.onreadystatechange = function () {
                if (this.readyState === 4 && this.status === 200) {
                    var data = JSON.parse(xhr.responseText).data;
                    // console.log(data[i]);

                    for (let i = 0; i < data.length; i++) {

                        var main = document.getElementById("main-container");


                        var inner = document.createElement("div");
                        var name = document.createElement("p");
                        name.innerHTML = data[i].name;
                        var aps = document.createElement("p");
                        aps.innerHTML = data[i].APS;

                        inner.appendChild(name);
                        inner.appendChild(aps);

                        main.appendChild(inner);
                    }
                }
            };

            xhr.send();
        };

    </script>

</html>
