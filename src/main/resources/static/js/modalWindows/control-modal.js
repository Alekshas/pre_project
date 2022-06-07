var ModalApp = {};
ModalApp.ModalProcess = function (parameters) {
    this.id = parameters['id'] || 'modal';
    this.selector = parameters['selector'] || '';
    this.title = parameters['title'] || 'Заголовок модального окна';
    this.body = parameters['body'] || 'Содержимое модального окна';
    this.formMethod = parameters['formMethod'] || ''
    this.formURL = parameters['formURL'] || ''
    this.formID = parameters['formID'] || ''
    this.footer = parameters['footer'] || ' <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Закрыть"></button>';
    this.content = '<div class="modal fade" id="' + this.id + '" tabindex="-1" role="dialog">' +
        '<div class="modal-dialog" role="document">' +
        '<div class="modal-content">' +
        '<div class="modal-header">' +
        '<h5 class="modal-title">' + this.title + '</h5>' +
        ' <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Закрыть"></button>' +
        '</div>' +
        '<form action="' + this.formURL + '" id = "' + this.formID + '" method="'+ this.formMethod + '">' +
        '<input type="hidden" name="_method" value="'+ this.formMethod + '">' +
        '<div class="modal-body">' + this.body + '</div>' +
        '<div class="modal-footer">' + this.footer + '</div>' +
        '</form>' +
        '</div>' +
        '</div>' +
        '</div>';
    this.init = function () {
        if ($('#' + this.id).length === 0) {
            $('body').prepend(this.content);
        }
        if (this.selector) {
            $(document).on('click', this.selector, $.proxy(this.showModal, this));
        }
    }
};
ModalApp.ModalProcess.prototype.changeTitle = function (content) {
    $('#' + this.id + ' .modal-title').html(content);
};
ModalApp.ModalProcess.prototype.changeBody = function (content) {
    $('#' + this.id + ' .modal-body').html(content);
};
ModalApp.ModalProcess.prototype.changeFooter = function (content) {
    $('#' + this.id + ' .modal-footer').html(content);
};
ModalApp.ModalProcess.prototype.showModal = function () {
    $('#' + this.id).modal('show');
};
ModalApp.ModalProcess.prototype.hideModal = function () {
    $('#' + this.id).modal('hide');
};
ModalApp.ModalProcess.prototype.updateModal = function () {
    $('#' + this.id).modal('handleUpdate');
};