//Home Hanguages
const sepedi_hl = document.getElementById("sepedi_range");
const isizulu_hl = document.getElementById("zulu_range");
const isixhosa_hl = document.getElementById("xhosa_range");
const sesotho_hl = document.getElementById("sotho_range");
const english_hl = document.getElementById("english_hl_range");
const afrikaans_hl = document.getElementById("afrikaans_hl_range");

//Contains all the Home Languages, plays a role in ensuring only one Home Language is selected
const home_language_ranges = [sepedi_hl, isizulu_hl, isixhosa_hl, sesotho_hl, english_hl, afrikaans_hl];

//First Additional Languages
const english_fal = document.getElementById("english_fal_range");
const afrikaans_fal = document.getElementById("afrikaans_fal_range");

//Contains all First Additional Languages, play a role in ensuring only one First Additional Language is selected
const first_additional_language_ranges = [english_fal, afrikaans_fal];

//Mathematics and Math Literacy
const mathematics = document.getElementById("mathematics_range");
const math_literacy = document.getElementById("math_literacy_range");

//Contains Mathematics and Math Literacy, plays a role in ensuring only one Math subject is selected
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

//Life Orientation 
const lo = document.getElementById("life_orientation_range");

//Dialogue elements
const dialogue_container = document.getElementById("dialogue_container");
const page_body = document.querySelector("body");
const dialogue_error_message = document.getElementById("dialogue_message");

//Submit Subject button Listener
document.getElementById("submit_btn")
        .addEventListener("click", (event) => {

            event.preventDefault();
            if (sesotho_hl.value === "0" && isixhosa_hl.value === "0" && isizulu_hl.value === "0"
                    && sepedi_hl.value === "0" && english_hl.value === "0" && afrikaans_hl.value === "0") {

                dialogue_error_message.innerText = "A Home Language(HL) is required.";
                displayDialogue(dialogue_container, page_body);
            } else if (english_fal.value === "0" && afrikaans_fal.value === "0") {
                dialogue_error_message.innerText = "First Additional Language(FAL) is required.";
                displayDialogue(dialogue_container, page_body);
            } else if (mathematics.value === "0" && math_literacy.value === "0") {
                dialogue_error_message.innerText = "Mathematics or Math Literacy is required.";
                displayDialogue(dialogue_container, page_body);
            } else if (lo.value === "0") {
                dialogue_error_message.innerText = "Life Orientation is required..";
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
                //Loops through an array of elective subjects to determine if atleast 3 subjects have been selected
                for (let i = 0; i < elective_subjects.length; i++) {
                    let target_range_value = elective_subjects[i].value;

                    if (target_range_value !== "0") {
                        numberOf_selected_ranges++;
                    }

                    if (numberOf_selected_ranges >= 3) {
                        break;
                    }
                }

                //Checks if the user selected atleast 3 Elective Subjects, if not, a dialogue message is shown
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

//Dialogue button, hides the whole dialogue when clicked
document.getElementById("hide_dialogue").addEventListener("click", () => {
    dialogue_container.style.display = "none";
    page_body.style.overflow = "visible";
});

//Range onInput Listener
document.querySelectorAll("input").forEach(range => {

    range.addEventListener("input", (event) => {

        event.target.nextElementSibling.innerText = event.target.value + "%";
        let target_range = event.target;
        if (isHomeLanguage(target_range)) {
            resetOtherRanges(target_range, home_language_ranges);
        } else
        if (isFirstAdditional(target_range)) {
            resetOtherRanges(target_range, first_additional_language_ranges);
        } else
        if (target_range === mathematics || target_range === math_literacy) {
            resetOtherRanges(target_range, math_ranges);
        }
    });
});
//Checks if a range is for Home Language
function isHomeLanguage(range) {
    return range === sesotho_hl || range === isixhosa_hl || range === isizulu_hl
            || range === sepedi_hl || range === english_hl || range === afrikaans_hl;
}

//Checks if a range is for First Additiona Language
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

