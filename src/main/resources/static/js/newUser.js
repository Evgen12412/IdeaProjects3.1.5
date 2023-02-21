// const url = "http://localhost:34512/api/admin/";
const dbRoles = [{id: 1, name: "ROLE_ADMIN"}, {id: 2, name: "ROLE_USER"}];

const newUserForm = document.getElementById('formNewUser');
const newRoles = document.getElementById('roles');

newRoles.innerHTML = `
                   <option value="${dbRoles[0].id}">${dbRoles[0].name}</option>
                    <option value="${dbRoles[1].id}">${dbRoles[1].name}</option>
                   `

newUserForm.addEventListener('submit', addNewUser);

async function addNewUser(event) {
    event.preventDefault();

    const newRole = document.querySelector('#roles').selectedOptions;
    let listOfRole = [];
    for (let i = 0; i < newRole.length; i++) {
        listOfRole.push({
            id: newRole[i].value
        });
    }
    let method = {
        method: 'POST',
        headers: {
            "Content-Type": "application/json",
        },
        body: JSON.stringify({
            surname: newUserForm.surname.value,
            username: newUserForm.username.value,
            age: newUserForm.age.value,
            password: newUserForm.password.value,
            roles: listOfRole
        })
    }
    await fetch(url, method).then(() => {
        newUserForm.reset();
        $('[href="#usersTable"]').tab('show');

    });

}
