function onFileSelect(e) {
    var
        f = e.target.files[0], // Первый выбранный файл
        reader = new FileReader,
        place = document.getElementById("imageTest") // Сюда покажем картинку
    ;
    reader.readAsDataURL(f);
    reader.onload = function (e) { // Как только картинка загрузится
        place.src = e.target.result;
    };

    $('#modalCenter').modal('show');
}

document.getElementById('userPhoto').addEventListener('change', onFileSelect, false);

function closeModal() {
    document.getElementById("userPhoto").value = "";
    $('#modalCenter').modal('hide');
}

$('#modalCenter').on('hide.bs.modal', function () {
    document.getElementById("userPhoto").value = "";
});