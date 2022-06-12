async function deleteUser(event) {
    let promise = await fetch("http://localhost:8080/users/" + event.target.dataset.id);
    let user = await promise.json();

    let admin_check = "";
    let user_check = "";
    let roles = '';

    for (let j = 0; j < user['authorities'].length; j++) {
        roles += user['authorities'][j].role + " ";
    }

    if (roles.includes("ADMIN")) {

        admin_check = "checked";
    }
    if (roles.includes("USER")) {

        user_check = "checked";
    }

    let deleteModal = new ModalApp.ModalProcess({
        id: 'deleteModal' + event.target.dataset.id,
        title: 'Delete user',
        formID: 'deleteFORM',
            formURL: 'http:\/\/localhost:8080\/users\/' + event.target.dataset.id,
        formMethod: 'DELETE',
        body: `
              <label for="id0"><b>ID</b></label>
                    <input name="id" type="text" class="form-control"
                           id="id0" value="${user.id}" disabled />
                    <br>
                    <label for="name0"><b>First name</b></label>
                    <input name="name" type="text"
                           class="form-control" id="name0"
                           value="${user.name}" disabled/>
                    <br>
                    <label for="surname0"><b>Last name</b></label>
                    <input name="lastname" type="text"
                           class="form-control" id="surname0"
                           value="${user.lastname}" disabled/>
                    <br>
                    <label for="age0"><b>Age</b></label>
                    <input name="age" type="number"
                           class="form-control" id="age0"
                           value="${user.age}" disabled/>
                    <br>
                    <label for="username0"><b>E-mail</b></label>
                    <input name="email" type="text"
                           class="form-control" id="username0"
                           value="${user.email}" disabled/>
                    <br>
                    <label><b>Role</b></label>
                    <input type="checkbox" id="2" name="admin"
                           value="ADMIN" ${admin_check} disabled/>
                    <label for="ADMIN">Admin</label>
                    
                    <input type="checkbox" id="1" name="user"
                           value="USER" ${user_check} disabled>
                    <label for="USER">User</label>
                    <br><br>
        `,
        footer: `
            <button type="button" class="btn btn-secondary"
                data-bs-dismiss="modal">Close
            </button>
            <button type="submit" class="btn btn-danger">
                 Delete
            </button>
        `

    })

    return deleteModal;
}