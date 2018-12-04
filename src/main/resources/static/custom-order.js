var clientTablePrefix = "client_";
var machinistTablePrefix = "machinist_";
var statusTablePrefix = "status_";
var clientEditPrefix = "editClient_";
var machinistEditPrefix = "editMachinist_";
var statusEditPrefix = "editStatus_";
var clientAddPrefix = "addClient_";
var machinistAddPrefix = "addMachinist_";
var statusAddPrefix = "addStatus_";

$('#editOrderTable').on('click', '.clickable-row', function(event) {
    if($(this).hasClass('table-active')){
        $(this).removeClass('table-active');
    } else {
        $(this).addClass('table-active').siblings().removeClass('table-active');
        setOrderFields();
    }
});

$("#clientEditSelect").change(function () {
    $("#editClientHiddenId").val(
        $(this).children(":selected").attr("id").replace(clientEditPrefix, "")
    );
});

$("#machinistEditSelect").change(function () {
    $("#editMachinistHiddenId").val(
        $(this).children(":selected").attr("id").replace(machinistEditPrefix, "")
    );
});

$("#statusEditSelect").change(function () {
    $("#editStatusHiddenId").val(
        $(this).children(":selected").attr("id").replace(statusEditPrefix, "")
    );
});

$("#clientAddSelect").change(function () {
    $("#addClientHiddenId").val(
        $(this).children(":selected").attr("id").replace(clientAddPrefix, "")
    );
});

$("#machinistAddSelect").change(function () {
    $("#addMachinistHiddenId").val(
        $(this).children(":selected").attr("id").replace(machinistAddPrefix, "")
    );
});

$("#statusAddSelect").change(function () {
    $("#addStatusHiddenId").val(
        $(this).children(":selected").attr("id").replace(statusAddPrefix, "")
    );
});

function setOrderFields() {
    var activeRow = $(".table-active");
    var clientId = activeRow.children()[1].id.replace(clientTablePrefix, "");
    var machinistId = activeRow.children()[2].id.replace(machinistTablePrefix, "");
    var statusId = activeRow.children()[6].id.replace(statusTablePrefix, "");
    $("#editId").get(0).value = activeRow.get(0).id;
    $("#deleteId").get(0).value = activeRow.get(0).id;
    $("#descriptionEdit").get(0).value = activeRow.children()[0].innerText;
    $("#clientEditSelect").val(
        $("#"+ clientEditPrefix + clientId).val()
    );
    $("#machinistEditSelect").val(
        $("#" + machinistEditPrefix + machinistId).val()
    );
    $("#startDateEdit").get(0).value = activeRow.children()[3].innerText;
    $("#endDateEdit").get(0).value = activeRow.children()[4].innerText;
    $("#valueCostEdit").get(0).value = activeRow.children()[5].innerText;
    $("#statusEditSelect").val(
        $("#" + statusEditPrefix + statusId).val()
    );
    /*$("#clientHiddenId").val(clientId);
    $("#machinistHiddenId").val(machinistId);
    $("#statusHiddenId").val(statusId);*/
}

