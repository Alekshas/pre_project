const newUserForm = document.getElementById("newUserForm");
newUserForm.addEventListener("submit",handleFormSubmit);


document.addEventListener('click',async event => {

    const editBtn = event.target.dataset.btn === "edit";
    const deleteBtn = event.target.dataset.btn === "delete";

    if (editBtn) {
        //event.preventDefault();
        let editModal = await editUser(event);
        editModal.init();
        editModal.showModal();

        const editForm = document.getElementById("editFORM");
        editForm.addEventListener("submit",handleFormSubmit);
    }
    if (deleteBtn) {
        event.preventDefault();
        let deleteModal = await deleteUser(event);
        deleteModal.init();
        deleteModal.showModal();

        const deleteUserForm = document.getElementById("deleteFORM");
        deleteUserForm.addEventListener("submit",handleFormSubmit);

    }

})

