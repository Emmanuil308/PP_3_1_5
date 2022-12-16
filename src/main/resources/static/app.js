async function getUserAuth() {
    return (await fetch('http://localhost:8080/api/users/1')).text();
}

async function getAllUsers() {
    return (await fetch('http://localhost:8080/api/users')).text();
}

let jsonObject = JSON.parse(await getUserAuth());
let jsonObjectAllUsers = JSON.parse(await getAllUsers());

// ________________________________________________PageLoader ____________________________________

if (document.readyState === 'loading') {
    document.addEventListener('DOMContentLoaded', setInfoUserNavbarHeader);
    document.addEventListener('DOMContentLoaded', setAllUserTable);

} else {
    setInfoUserNavbarHeader();
    setAllUserTable();

}

// ________________________________________________Header ____________________________________

function setInfoUserNavbarHeader() {
    let textAllRole = "";
    for (let i in jsonObject.roleSet) {
        if (jsonObject.roleSet[i].roleName === "ROLE_ADMIN") {
            textAllRole += "Admin" + " ";
        }
        if (jsonObject.roleSet[i].roleName === "ROLE_USER") {
            textAllRole += "User" + " ";
        }
    }
    document.getElementById("navbarHeaderEmail").innerHTML = jsonObject.email;
    document.getElementById("navbarHeaderRoles").innerHTML = textAllRole;
}

// ________________________________________________AllUsersTable ____________________________________

function setAllUserTable() {
    let textAllRole = ""
    for (let i in jsonObjectAllUsers) {
        let row = document.createElement('tr');
        textAllRole = "";
        for (let r in jsonObjectAllUsers[i].roleSet) {

            if (jsonObjectAllUsers[i].roleSet[r].roleName === "ROLE_ADMIN") {
                textAllRole += "Admin" + " ";
            }
            if (jsonObjectAllUsers[i].roleSet[r].roleName === "ROLE_USER") {
                textAllRole += "User" + " ";
            }
        }
        row.innerHTML = `<td>${jsonObjectAllUsers[i].id}</td>
        <td>${jsonObjectAllUsers[i].name}</td> 
        <td>${jsonObjectAllUsers[i].surname}</td> 
        <td>${jsonObjectAllUsers[i].age}</td> 
        <td>${jsonObjectAllUsers[i].email}</td>
        <td>${textAllRole}</td>
        <td>
            <button data-row="${jsonObjectAllUsers[i].id}"  data-target="#modalEdit"
                class="btn btn-info rounded" data-toggle="modal" type="button">Edit
            </button>
        </td>
        <td>
            <button data-row="${jsonObjectAllUsers[i].id}" data-target="#modalDelete"
                class="btn btn-danger rounded" data-toggle="modal" type="button">Delete
            </button>
        </td>`

        document.getElementById("TableAllUser").appendChild(row);
    }
    goBtnEdit();
    goBtnDelete();
}

let urlForDelete;
let formEdit;
let formDelete;
let formSaveNew = document.forms.formSaveNewUserName;
let roles = "";
// let allOptions;
let selectEdit = document.getElementById("selectRoleEdit");
let selectedOptionsEdit = selectEdit.selectedOptions;
let selectSave = document.getElementById("saveNewSelectRole");
let selectedOptionsSave = selectSave.selectedOptions;
let textForUpdate;

// ________________________________________________SaveNewUser/Submit ____________________________________

// let btnAddNewUser = document.getElementById("btnAddNewUser");
formSaveNew.addEventListener('submit', handleFormSubmitSave);

async function handleFormSubmitSave(event) {
    event.preventDefault();
    let data = serializeForm(formSaveNew);

    Array.from(selectedOptionsSave).forEach(function (option) {
        roles += " " + option.value;
    });
    if (roles === "") {
        roles = "ROLE_USER";
    }
    data.append("roles", roles);

    textForUpdate = {};
    data.forEach(function (value, key) {
        textForUpdate[key] = value;
    });

    await sendDataSaveNew(data);

    await reloadTable();

    document.getElementById("TabAllUsersTable").click();

    // document.getElementById("CloseButtonEdit").click();
}

async function sendDataSaveNew(data) {
    return await fetch('http://localhost:8080/api/users', {
        method: 'POST',
        headers: {'Content-Type': 'application/json'},
        body: JSON.stringify(textForUpdate),
    })
}

// ________________________________________________ModalWindowEdit ____________________________________

function goBtnEdit() {
    let allBtnEdit = document.querySelectorAll('.btn.btn-info');
    allBtnEdit.forEach(function (btn) {
        btn.addEventListener("click", async function() {

            let url = new URL("http://localhost:8080/api/users/" + btn.getAttribute("data-row"));

            let userText = async function getUserById() {
                return ((await fetch(url)).text());
            }

            let user = JSON.parse(await (userText()));

            formEdit = document.forms.formModalEditName;

            formEdit.elements.id.value = user.id;
            formEdit.elements.idDis.value = user.id;
            formEdit.elements.name.value = user.name;
            formEdit.elements.surname.value = user.surname;
            formEdit.elements.age.value = user.age;
            formEdit.elements.email.value = user.email;
            formEdit.elements.password.value = "";

            // allOptions = Array.from(document.getElementsByTagName('option'));

            formEdit.addEventListener('submit', handleFormSubmitEdit);
        });
    });
}

// ________________________________________________ModalWindowDelete ____________________________________

function goBtnDelete() {
    let allBtnDelete = document.querySelectorAll('.btn.btn-danger.rounded');

    allBtnDelete.forEach(function (btn) {
        btn.addEventListener("click", async function() {

            let url = new URL("http://localhost:8080/api/users/" + btn.getAttribute("data-row"));

            let userText = async function getUserById() {
                return ((await fetch(url)).text());
            }
                let user = JSON.parse(await (userText()));

                formDelete = document.forms.formModalDeleteName;

                formDelete.elements.id.value = user.id;
                formDelete.elements.name.value = user.name;
                formDelete.elements.surname.value = user.surname;
                formDelete.elements.age.value = user.age;
                formDelete.elements.email.value = user.email;

                urlForDelete = new URL( "http://localhost:8080/api/users/" + btn.getAttribute("data-row"));

                formDelete.addEventListener('submit', handleFormSubmitDelete);

        });
    });
}


// ________________________________________________SubmitEdit____________________________________

async function handleFormSubmitEdit(event) {
    event.preventDefault();
    let data = serializeForm(formEdit);

    Array.from(selectedOptionsEdit).forEach(function (option) {
        roles += " " + option.value;
    });
    if (roles === "") {
        roles = "ROLE_USER";
    }
    data.append("roles", roles);

    textForUpdate = {};
    data.forEach(function (value, key) {
        textForUpdate[key] = value;
    });

    await sendDataEdit(data);

    await reloadTable();

    document.getElementById("CloseButtonEdit").click();
}

function serializeForm(form) {
    return new FormData(form);
}

async function sendDataEdit(data) {
    return await fetch('http://localhost:8080/api/users', {
        method: 'PUT',
        headers: {'Content-Type': 'application/json'},
        body: JSON.stringify(textForUpdate),
    })
}

// Array.from(document.getElementsByName("close")).forEach(function (closeBtn) {
//     closeBtn.addEventListener("click", function () {
//         $("select").val([]);
//     });
// });

// ________________________________________________SubmitDelete____________________________________

async function handleFormSubmitDelete(event) {
    event.preventDefault();

    await sendDelete();
    await reloadTable();
    document.getElementById("CloseButtonDelete").click()
}

async function sendDelete() {
    return await fetch(urlForDelete, {
        method: 'DELETE',
    });
}

// ________________________________________________ReloadTable____________________________________

async function reloadTable() {

    const old_tbody = document.getElementById("TableAllUser")
    const new_tbody = document.createElement('tbody');
    new_tbody.setAttribute('id', 'TableAllUser');
    old_tbody.parentNode.replaceChild(new_tbody, old_tbody);

    await getAllUsers();
    jsonObjectAllUsers = JSON.parse(await getAllUsers());
    await setAllUserTable();

    roles = "";
}

