
async function getUserAuth() {
    return (await fetch('http://localhost:8080/api/users/name')).text();
}

const jsonObject = JSON.parse(await getUserAuth())
let textAllRole;

function setInfoAboutUserTable() {
    textAllRole = ""
    document.getElementById("id").innerHTML = jsonObject.id;
    document.getElementById("name").innerHTML = jsonObject.name;
    document.getElementById("surname").innerHTML = jsonObject.surname;
    document.getElementById("age").innerHTML = jsonObject.age;
    document.getElementById("email").innerHTML = jsonObject.email;
    for (let i in jsonObject.roleSet) {
        if (jsonObject.roleSet[i].roleName === "ROLE_ADMIN") {
            textAllRole += "Admin" + " ";
        }
        if (jsonObject.roleSet[i].roleName === "ROLE_USER") {
            textAllRole += "User" + " ";
        }
    }
    document.getElementById("roles").innerHTML = textAllRole;
}

function setInfoUserNavbarHeader() {
    textAllRole = "";
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


if (document.readyState === 'loading') {
    document.addEventListener('DOMContentLoaded', setInfoAboutUserTable);
    document.addEventListener('DOMContentLoaded', setInfoUserNavbarHeader);

} else {
    setInfoAboutUserTable();
    setInfoUserNavbarHeader();
}