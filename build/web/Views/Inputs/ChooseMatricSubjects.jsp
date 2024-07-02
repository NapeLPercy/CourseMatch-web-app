<!DOCTYPE html>
<%@page import="Entities.Student"%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Choose Subjects</title>
        <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">

        <style>
            input[type="range"] {
                width: 300px;
            }

            legend {
                text-align: center;
                margin-bottom: 30px;
            }

            .subject-label {
                width: 150px; /* Ensures all labels have the same width */
            }

            #submit_btn{
                width: 200px;
                margin: 25px 0px 100px 160px;
            }


            body {
                margin: 0;
                padding: 0;
                font-family: Arial, sans-serif;
            }

            #dialogue_container {
                position: fixed;
                top: 0;
                left: 0;
                width: 100%;
                height: 100%;
                background: rgba(0, 0, 0, 0.5);
                backdrop-filter: blur(5px);
                display: none;
                justify-content: center;
                align-items: center;
                z-index: 999;
            }

            #dialogue {
                background: white;
                padding: 20px;
                box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
                border-radius: 10px;
                text-align: center;
                width: 800px;
                margin-top: 200px;
                margin-left: 20vw;
            }

            #dialogue p {
                margin: 0 0 20px 0;
            }

            #hide_dialogue {
                padding: 10px 20px;
                background: #007bff;
                color: white;
                border: none;
                border-radius: 5px;
                cursor: pointer;
                font-size: 16px;
            }

            #hide_dialogue:hover {
                background: #0056b3;
            }

            #already_submitted{
                text-align: center;
                margin-top: 30px;
            }

            span {
                font-weight: bold;
                font-family: Sans-Serif;
                text-decoration: underline;
                letter-spacing: 1.50px;
                margin-bottom: 2px;
            }

            .navbar-nav{
                margin-left: 600px;
            }
            <%-- #navbarNav {
          margin-left: 600px;
      }--%>
        </style>
    </head>
    <body>

        <%
         if(session.getAttribute("username") == null){
                             response.sendRedirect("/CourseMatch/index.html");
                             return;
            }
        
        if((Student)request.getAttribute("student")!= null){
        %>
        <h4 id="already_submitted">You already <b>submitted</b> your subjects, click <a href="/CourseMatch/ShowSubjectsController">here</a> to Edit or View your current subjects.</h4>
        <%
          }else{

        %>
        <!-- Navigation Bar -->
        <nav class="navbar navbar-expand-lg navbar-light bg-light">
            <a class="navbar-brand" href="/CourseMatch/Views/Menu.jsp">Course<span>Match</span></a>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarNav">
                <ul class="navbar-nav">

                    <li class="nav-item">
                        <a class="nav-link" href="/CourseMatch/LogoutController">Logout</a>
                    </li>
                </ul>
            </div>
        </nav>

        <div id="dialogue_container">
            <div id="dialogue">
                <p id="dialogue_message"></p>
                <button id="hide_dialogue">I Understand</button>
            </div>
        </div>

        <div class="container mt-4">
            <form action="/CourseMatch/SubjectController" id="form" method="POST">
                <fieldset>
                    <legend>Select Subject</legend>
                    <div class="d-flex align-items-center mb-2">
                        <p class="mb-0 mr-2 subject-label">Sepedi HL</p>
                        <input type="range" class="form-control-range mr-2" min="0" max="100" value="0" id="sepedi_range" name="subjects[Sepedi HL]"/>
                        <p id="sepedi_message" class="mb-0">0%</p>
                    </div>

                    <div class="d-flex align-items-center mb-2">
                        <p class="mb-0 mr-2 subject-label">Sesotho HL</p>
                        <input type="range" class="form-control-range mr-2" min="0" max="100" value="0" id="sotho_range" name="subjects[Sesotho HL]"/>
                        <p id="sotho_message" class="mb-0">0%</p>
                    </div>

                    <div class="d-flex align-items-center mb-2">
                        <p class="mb-0 mr-2 subject-label">iSizulu HL</p>
                        <input type="range" class="form-control-range mr-2" min="0" max="100" value="0" id="zulu_range" name="subjects[iSizulu HL]"/>
                        <p id="zulu_message" class="mb-0">0%</p>
                    </div>
                    <div class="d-flex align-items-center mb-2">
                        <p class="mb-0 mr-2 subject-label">iSixhosa HL</p>
                        <input type="range" class="form-control-range mr-2" min="0" max="100" value="0" id="xhosa_range" name="subjects[iSixhosa HL]"/>
                        <p id="xhosa_message" class="mb-0">0%</p>
                    </div>

                    <div class="d-flex align-items-center mb-2">
                        <p class="mb-0 mr-2 subject-label">English HL</p>
                        <input type="range" class="form-control-range mr-2" min="0" max="100" value="0" id="english_hl_range" name="subjects[English HL]"/>
                        <p id="english_hl_message" class="mb-0">0%</p>
                    </div>

                    <div class="d-flex align-items-center mb-2">
                        <p class="mb-0 mr-2 subject-label">Afrikaans HL</p>
                        <input type="range" class="form-control-range mr-2" min="0" max="100" value="0" id="afrikaans_hl_range" name="subjects[Afrikaans HL]"/>
                        <p id="afrikaans_hl_message" class="mb-0">0%</p>
                    </div>

                    <div class="d-flex align-items-center mb-2">
                        <p class="mb-0 mr-2 subject-label">English FAL</p>
                        <input type="range" class="form-control-range mr-2" min="0" max="100" value="0" id="english_fal_range" name="subjects[English FAL]"/>
                        <p id="english_fal_message" class="mb-0">0%</p>
                    </div>


                    <div class="d-flex align-items-center mb-2">
                        <p class="mb-0 mr-2 subject-label">Afrikaans FAL</p>
                        <input type="range" class="form-control-range mr-2" min="0" max="100" value="0" id="afrikaans_fal_range" name="subjects[Afrikaans FAL]"/>
                        <p id="afrikaans_fal_message" class="mb-0">0%</p>
                    </div>

                    <div class="d-flex align-items-center mb-2">
                        <p class="mb-0 mr-2 subject-label">Life Orientation</p>
                        <input type="range" class="form-control-range mr-2" min="0" max="100" value="0" id="life_orientation_range" name="subjects[Life Orientation]"/>
                        <p id="life_orientation_message" class="mb-0">0%</p>
                    </div>

                    <div class="d-flex align-items-center mb-2">
                        <p class="mb-0 mr-2 subject-label">Mathematics</p>
                        <input type="range" class="form-control-range mr-2" min="0" max="100" value="0" id="mathematics_range" name="subjects[Mathematics]"/>
                        <p id="mathematics_message" class="mb-0">0%</p>
                    </div>

                    <div class="d-flex align-items-center mb-2">
                        <p class="mb-0 mr-2 subject-label">Math Literacy</p>
                        <input type="range" class="form-control-range mr-2" min="0" max="100" value="0" id="math_literacy_range" name="subjects[Math Literacy]"/>
                        <p id="math_literacy_studies_message" class="mb-0">0%</p>
                    </div>

                    <div id="elective_subjects">
                        <div class="d-flex align-items-center mb-2">
                            <p class="mb-0 mr-2 subject-label">Life Sciences</p>
                            <input type="range" class="form-control-range mr-2" min="0" max="100" value="0" id="life_sciences_range" name="subjects[Life Sciences]"/>
                            <p id="life_science_message" class="mb-0">0%</p>
                        </div>

                        <div class="d-flex align-items-center mb-2">
                            <p class="mb-0 mr-2 subject-label">Physical Sciences</p>
                            <input type="range" class="form-control-range mr-2" min="0" max="100" value="0" id="physical_sciences_range" name="subjects[Physical Sciences]"/>
                            <p id="physical_science_message" class="mb-0">0%</p>
                        </div>
                        <div class="d-flex align-items-center mb-2">
                            <p class="mb-0 mr-2 subject-label">Geography</p>
                            <input type="range" class="form-control-range mr-2" min="0" max="100" value="0" id="geography_range" name="subjects[Geography]"/>
                            <p id="geography_message" class="mb-0">0%</p>
                        </div>

                        <div class="d-flex align-items-center mb-2">
                            <p class="mb-0 mr-2 subject-label">Accounting</p>
                            <input type="range" class="form-control-range mr-2" min="0" max="100" value="0" id="accounting_range" name="subjects[Accounting]"/>
                            <p id="accounting_message" class="mb-0">0%</p>
                        </div>

                        <div class="d-flex align-items-center mb-2">
                            <p class="mb-0 mr-2 subject-label">Economics</p>
                            <input type="range" class="form-control-range mr-2" min="0" max="100" value="0" id="economics_range" name="subjects[Economics]"/>
                            <p id="econimics_message" class="mb-0">0%</p>
                        </div>

                        <div class="d-flex align-items-center mb-2">
                            <p class="mb-0 mr-2 subject-label">Business Studies</p>
                            <input type="range" class="form-control-range mr-2" min="0" max="100" value="0" id="business_studies_range" name="subjects[Business Studies]"/>
                            <p id="business_studies_message" class="mb-0">0%</p>
                        </div>

                        <div class="d-flex align-items-center mb-2">
                            <p class="mb-0 mr-2 subject-label">Agriculture</p>
                            <input type="range" class="form-control-range mr-2" min="0" max="100" value="0" id="agriculture_range" name="subjects[Agriculture]"/>
                            <p id="agriculture_message" class="mb-0">0%</p>
                        </div>

                        <div class="d-flex align-items-center mb-2">
                            <p class="mb-0 mr-2 subject-label">Tourism</p>
                            <input type="range" class="form-control-range mr-2" min="0" max="100" value="0" id="tourism_range" name="subjects[Tourism]"/>
                            <p id="toursim_message" class="mb-0">0%</p>
                        </div>
                    </div>

                    <button type="submit" id="submit_btn" class="btn btn-primary">ADD SUBJECTS</button>
                </fieldset>
            </form>
        </div>
        <%
}
        %>

        <script type="text/javascript">

            //home languages
            const sepedi_hl = document.getElementById("sepedi_range");
            const isizulu_hl = document.getElementById("zulu_range");
            const isixhosa_hl = document.getElementById("xhosa_range");
            const sesotho_hl = document.getElementById("sotho_range");
            const english_hl = document.getElementById("english_hl_range");
            const afrikaans_hl = document.getElementById("afrikaans_hl_range");
            //contains all home languages, play a role in ensuring only one home language is selected
            const home_language_ranges = [sepedi_hl, isizulu_hl, isixhosa_hl, sesotho_hl, english_hl, afrikaans_hl];
            //first additional languages
            const english_fal = document.getElementById("english_fal_range");
            const afrikaans_fal = document.getElementById("afrikaans_fal_range");
            //contains all first additional languages, play a role in ensuring only one first additional language is selected
            const first_additional_language_ranges = [english_fal, afrikaans_fal];
            //Mathematics and Math Literacy
            const mathematics = document.getElementById("mathematics_range");
            const math_literacy = document.getElementById("math_literacy_range");
            //contains mathematics and math lit, play a role in ensuring only one math is selected
            const math_ranges = [math_literacy, mathematics];
            //Elective subjects
            const life_sciences = document.getElementById("life_sciences_range");
            const physical_sciences = document.getElementById("life_sciences_range");
            const geography = document.getElementById("geography_range");
            const accounting = document.getElementById("accounting_range");
            const economics = document.getElementById("economics_range");
            const agriculture = document.getElementById("agriculture_range");
            const business_studies = document.getElementById("business_studies_range");
            const tourism = document.getElementById("tourism_range");

            //LIFE ORIENTAION 
            const lo = document.getElementById("life_orientation_range");


            //dialogue elements
            const dialogue_container = document.getElementById("dialogue_container");
            const page_body = document.querySelector("body");
            const dialogue_error_message = document.getElementById("dialogue_message");
            //Submit Subject button onClick Listener
            document.getElementById("submit_btn").addEventListener("click", (event) => {

                event.preventDefault();
                if (sesotho_hl.value === "0" && isixhosa_hl.value === "0" && isizulu_hl.value === "0"
                        && sepedi_hl.value === "0" && english_hl.value === "0" && afrikaans_hl.value === "0") {

                    dialogue_error_message.innerText = "You have to select a Home Language";
                    displayDialogue(dialogue_container, page_body);
                } else if (english_fal.value === "0" && afrikaans_fal.value === "0") {
                    dialogue_error_message.innerText = "You have to select a First Additional Language";
                    displayDialogue(dialogue_container, page_body);
                } else if (mathematics.value === "0" && math_literacy.value === "0") {
                    dialogue_error_message.innerText = "You have to select a Mathematics or Math Literacy.";
                    displayDialogue(dialogue_container, page_body);
                } else if (lo.value === "0") {
                    dialogue_error_message.innerText = "You have to select Life Orientation.";
                    displayDialogue(dialogue_container, page_body);
                } else {
                    //I WILL CHANGE THIS TO QUERY SELECTOR ALL
                    const elective_subjects =
                            [{"value": life_sciences.value},
                                {"value": physical_sciences.value},
                                {"value": geography.value},
                                {"value": accounting.value},
                                {"value": economics.value},
                                {"value": business_studies.value},
                                {"value": tourism.value},
                                {"value": agriculture.value}
                            ];

                    let numberOf_selected_ranges = 0;
                    //loops through an array of elective subjects to determine if atleast 3 subjects have been selected
                    for (let i = 0; i < elective_subjects.length; i++) {
                        let target_range_value = elective_subjects[i].value;

                        if (target_range_value !== "0") {
                            numberOf_selected_ranges++;
                        }

                        if (numberOf_selected_ranges >= 3) {
                            break;
                        }
                    }

                    //Checks if the user selected atleats 3 subject, if not, a dialogue message is shown
                    if (numberOf_selected_ranges >= 3) {
                        document.getElementById("form").submit();
                    } else {
                        dialogue_error_message.innerText = "You have to select atleast Three Elective Subjects.";
                        displayDialogue(dialogue_container, page_body);
                    }
                }
            });
            //Make the dialogue visible
            function displayDialogue(dialogue_container, page_body) {
                dialogue_container.style.display = "block";
                dialogue_container.style.width = "100";
                page_body.style.overflow = "hidden";
            }

            //Dialogue button, hides the whole dialogu when clicked
            document.getElementById("hide_dialogue").addEventListener("click", () => {
                dialogue_container.style.display = "none";
                page_body.style.overflow = "visible";
            });
            //Range onInput Listener
            document.querySelectorAll("input").forEach(range => {

                range.addEventListener("input", (event) => {

                    let value = event.target.value;
                    let message = event.target.nextElementSibling;
                    message.innerText = value + "%";
                    let target_range = event.target;
                    if (isHomeLanguage(target_range)) {
                        resetOtherRanges(target_range, home_language_ranges);
                    } else if (isFirstAdditional(target_range)) {
                        resetOtherRanges(target_range, first_additional_language_ranges);
                    } else if (target_range === mathematics || target_range === math_literacy) {
                        resetOtherRanges(target_range, math_ranges);
                    }
                });
            });
            //Checks if a range is for home language
            function isHomeLanguage(range) {
                return range === sesotho_hl || range === isixhosa_hl || range === isizulu_hl
                        || range === sepedi_hl || range === english_hl || range === afrikaans_hl;
            }

            //Checks if a range is for home language
            function isFirstAdditional(range) {
                return range === english_fal || range === afrikaans_fal;
            }

            //Reset a range to ensure that only one of a specific range is selected at a time.
            function resetOtherRanges(changed_range, range_type) {
                range_type.forEach(range => {
                    if (range !== changed_range) {
                        range.value = 0;
                        range.nextElementSibling.innerText = "0%";
                    }
                });
            }

        </script>

    </body>
</html>