function hideButtonsIfNotSelected() {
    setTimeout(function () {
        if($(".table-active").length === 0) {
            $("#editBtn").attr('disabled', 'disabled');
            $("#deleteBtn").attr('disabled', 'disabled');
        } else {
            $("#editBtn").removeAttr('disabled');
            $("#deleteBtn").removeAttr('disabled');
        }
        hideButtonsIfNotSelected();
    }, 100)
}

function submitDelete() {
    $("#deleteFormBtn").click();
}

hideButtonsIfNotSelected();