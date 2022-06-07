getUsersFromBD()
document.addEventListener("update-info",getUsersFromBD);

async function getUsersFromBD() {
    let promise = await fetch("http://localhost:8080/users")
    promise.json()
        .then(users => {
            let table = document.getElementById("userlist");
            table.innerHTML = "";

            for (let i = 0; i < users.length; i++) {
                let roles = '';
                for (let j = 0; j < users[i]['authorities'].length; j++) {
                    roles += users[i]['authorities'][j].role + " ";
                }

                table.insertAdjacentHTML('beforeend', `
                 <tr>
                    <td> ${users[i]['id']} </td>
                    <td> ${users[i]['name']} </td>
                    <td> ${users[i]['lastname']} </td>
                    <td> ${users[i]['age']} </td>
                    <td> ${users[i]['email']} </td>
                    <td> ${roles} </td>
                    <td>
                        <button type="button" class="btn btn-sm btn-info"
                        id="editButton"
                        data-btn="edit"
                        data-id=${users[i]['id']}>
                        Edit
                        </button>
                    </td>  
                    <td>
                        <button type="button" class="btn btn-sm btn-danger"
                        id="deleteButton"
                        data-btn="delete"
                        data-id=${users[i]['id']}>
                        Delete
                        </button>
                    </td>   
                </tr>`);
            }
        })
        .catch(error => {
            console.log(error)
        })
        .finally(() => {
        })
}

