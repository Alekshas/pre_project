const newUserForm = document.getElementById("newUserForm");
newUserForm.addEventListener("submit", handleFormSubmit);


document.addEventListener('click', async event => {

    const editBtn = event.target.dataset.btn === "edit";
    const deleteBtn = event.target.dataset.btn === "delete";
    const add = event.target.dataset.btn === "add";
    const dismiss = event.target.dataset.btn === "dismiss";

    if (editBtn) {
        //event.preventDefault();
        let editModal = await editUser(event);
        editModal.init();
        editModal.showModal();

        const editForm = document.getElementById("editFORM");
        editForm.addEventListener("submit", handleFormSubmit);
    }
    if (deleteBtn) {
        event.preventDefault();
        let deleteModal = await deleteUser(event);
        deleteModal.init();
        deleteModal.showModal();

        const deleteUserForm = document.getElementById("deleteFORM");
        deleteUserForm.addEventListener("submit", handleFormSubmit);

    }
    if (add) {
        $.ajax({
            type: 'POST',
            url: 'http://localhost:8080/promoteRequest/',
            async: true,
            contentType: "application/json; charset=utf-8",
            data: JSON.stringify({
                "id": event.target.dataset.id,
                "wantToBeAdmin": "false",
                "admin": "admin",
            }),
            'success': function () {
                let event = new Event("update-info");
                document.dispatchEvent(event);

            }
        });
    }
    if (dismiss) {
        $.ajax({
            type: 'POST',
            url: 'http://localhost:8080/promoteRequest/',
            async: true,
            contentType: "application/json; charset=utf-8",
            data: JSON.stringify({
                "id": event.target.dataset.id,
                "wantToBeAdmin": "false"
            }),
            success : function () {
                let event = new Event("update-info");
                document.dispatchEvent(event);
            }
        });
    }


})

