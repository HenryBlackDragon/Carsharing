document.getElementById('userPhoto').addEventListener('change', onFileSelect, false);

function onFileSelect(evt) {
    var
        f = evt .target.files[0],
        reader = new FileReader,
        place = document.getElementById("imgUserPhoto")
    ;
    reader.readAsDataURL(f);
    reader.onload = function (e) {
        place.src = e.target.result;
    };

    $('#modalCenter').modal('show');
}

function closeModal() {
    document.getElementById("userPhoto").value = "";
    $('#modalCenter').modal('hide');
}

$('#modalCenter').on('hide.bs.modal', function () {
    document.getElementById("userPhoto").value = "";
});