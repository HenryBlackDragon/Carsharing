var fileMulti = document.getElementById('carPhotos'),
    // countImg = 0,
    idBlock = 0;

fileMulti.addEventListener('change', handleFileSelectMulti, false);

function handleFileSelectMulti(evt) {
    var files = evt.target.files;

    // if (countImg >= 8 || files.length > 8) {
    //     moreThenEight();
    //
    //     return
    // } else {
    //     countImg += files.length;
    //
    //     if (countImg > 8) {
    //         countImg -= files.length;
    //         moreThenEight();
    //
    //         return
    //     }
    // }

    if (files.length > 8) {
        moreThenEight();

        return
    }

    document.getElementById('outputMulti').innerHTML = "";

    for (var i = 0, f; f = files[i]; i++) {
        var reader = new FileReader();
        reader.onload = (function () {

            return function (e) {
                var div = document.createElement('div');
                div.setAttribute("id", "block-with-img-" + idBlock);
                div.className = "d-flex flex-column mr-3 mt-1";
                div.innerHTML = [
                    // '<button id="main-', idBlock, '" class="button-main" onclick="makeMain(this.id)">Сделать заглавной</button>',
                    // '<button id="del-', idBlock, '" class="button-del" onclick="removeDiv(this.id)">Удалить</button>'
                    '<img id="main-photo-', idBlock, '" class="photo" src="', e.target.result, '"/>']
                    .join('');

                document.getElementById('outputMulti').insertBefore(div, null);
                // firstChild().innerHTML = "Заглавная";
                // fileMulti.value = "";
                idBlock++
            };
        })();

        reader.readAsDataURL(f);
    }
}

function moreThenEight() {
    alert("Вы выбрали больше 8 изображений");
    fileMulti.value = "";
}

// function firstChild() {
//     var firstButtonChild = document.getElementById('outputMulti').firstChild;
//
//     if (firstButtonChild != null) {
//         if (firstButtonChild.firstChild != null) {
//             return firstButtonChild.firstChild
//         }
//     }
// }

// function findBlockDiv(id) {
//     var arr = id.split('-');
//
//     return document.getElementById('block-with-img-' + arr[1]);
// }

// function removeDiv(id) {
//     findBlockDiv(id).remove();
//     countImg--;
//     fileMulti.value = "";
//
//     if (firstChild() != null) {
//         firstChild().innerHTML = "Заглавная";
//     }
// }

// function makeMain(id) {
//     var buttons = document.getElementsByClassName("button-main");
//
//     for (var i = 0; i < buttons.length; i++) {
//         buttons[i].innerHTML = "Сделать заглавной";
//
//         if (buttons[i].id === id) {
//             buttons[i].innerHTML = "Заглавная";
//             changePlace(id)
//         }
//     }
// }

// function changePlace(id) {
//     var parent = document.getElementById("outputMulti"),
//         first = parent.firstChild,
//         div = findBlockDiv(id);
//
//     parent.insertBefore(div, first);
// }
