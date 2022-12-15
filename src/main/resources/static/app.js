async function getUserAuth() {
    return (await fetch('http://localhost:8080/api/users/1')).text();
}

async function getAllUsers() {
    return (await fetch('http://localhost:8080/api/users')).text();
}

const jsonObject = JSON.parse(await getUserAuth());
const jsonObjectAllUsers = JSON.parse(await getAllUsers());
let allBtnEdit;


if (document.readyState === 'loading') {
    document.addEventListener('DOMContentLoaded', setInfoUserNavbarHeader);
    document.addEventListener('DOMContentLoaded', setAllUserTable);

} else {
    setInfoUserNavbarHeader();
    setAllUserTable();

};



export function setInfoUserNavbarHeader() {                                                 //Header
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



function setAllUserTable() {                                                                //AllUsersTable
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
            <button data-row="${jsonObjectAllUsers[i].id}" id="${'btn' + jsonObjectAllUsers[i].id}" data-target="#modalEdit"
                class="btn btn-info rounded" data-toggle="modal" type="button">Edit
            </button>
        </td>
        <td>
            <button th:data-row="${jsonObjectAllUsers[i]}" data-target="${'#deleteuser' + jsonObjectAllUsers[i].id}"
                class="btn btn-danger rounded" data-toggle="modal" type="button">Delete
            </button>
        </td>`

        document.getElementById("TableAllUser").appendChild(row);
    }
    allBtnEdit = document.querySelectorAll('.btn.btn-info');
}

let form;

allBtnEdit.forEach(function (btn) {                                         //ButtonEdit
    btn.addEventListener("click", async function () {

        let idUser = btn.getAttribute("data-row");

        let url = new URL("http://localhost:8080/api/users/" + btn.getAttribute("data-row"));

        let userText = async function getUserById() {
            return ((await fetch(url)).text());
        }


        let user = JSON.parse(await (userText()));

        form = document.forms.formModalEditName;

        form.elements.id.value = user.id;
        form.elements.idDis.value = user.id;
        form.elements.name.value = user.name;
        form.elements.surname.value = user.surname;
        form.elements.age.value = user.age;
        form.elements.email.value = user.email;
        form.elements.password.value = "";

        form.addEventListener('submit', handleFormSubmit);

    })
})

let textForUpdate;

async function handleFormSubmit(event) {
    event.preventDefault();
    let data = serializeForm(form);
    data.delete("selectRole");

    textForUpdate = {};
    data.forEach(function(value, key){
        textForUpdate[key] = value;
    });

    let response = await sendData(data);
}

function serializeForm(form) {
    return new FormData(form);
}

async function sendData(data) {
    return await fetch('http://localhost:8080/api/users', {
        method: 'PUT',
        // headers: { 'Content-Type': 'multipart/form-data' },
        headers: {
            'Content-Type': 'application/json'
        },
        // body: data,
        body: JSON.stringify(textForUpdate),
        // disposition: name="MyForm",
    })
}


