var fileMulti = document.getElementById('carPhotos'),
    countImg = 0,
    idBlock = 0,
    allFiles;



fileMulti.addEventListener('change', handleFileSelectMulti, false);

function handleFileSelectMulti(evt) {

    console.log(fileMulti);

    var files = evt.target.files; // попробывать сделать переменную отдельно и отправить на сервер
    console.log(files)

    if (countImg >= 8 || files.length > 8) {
        moreThenEight();
        return
    } else {
        countImg += files.length;

        if (countImg > 8) {
            countImg -= files.length;
            moreThenEight();
            return
        }
    }

    for (var i = 0, f; f = files[i]; i++) {
        var reader = new FileReader();
        reader.onload = (function () {

            return function (e) {
                var div = document.createElement('div');
                div.setAttribute("id", "block-with-img-" + idBlock);
                div.className = "d-flex flex-column mr-3 mt-1";
                console.log(e.target.result);
                div.innerHTML = [
                    '<button id="main-', idBlock, '" class="button-main" onclick="makeMain(this.id)">Сделать заглавной</button>',
                    '<img id="main-photo-', idBlock, '" class="photo" src="', e.target.result, '"/>',
                    '<button id="del-', idBlock, '" class="button-del" onclick="removeDiv(this.id)">Удалить</button>']
                    .join('');

                document.getElementById('outputMulti').insertBefore(div, null);
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

function findBlockDiv(id) {
    var arr = id.split('-');
    return document.getElementById('block-with-img-' + arr[1]);
}

function removeDiv(id) {
    findBlockDiv(id).remove();
    countImg--;
    fileMulti.value = "";
}

function makeMain(id) {
    var test2 = document.getElementsByClassName("button-main");

    for (var i = 0; i < test2.length; i++) {
        test2[i].innerHTML = "Сделать заглавной";

        if (test2[i].id === id) {
            test2[i].innerHTML = "Заглавная";
            changePlace(id)
        }
    }
}

function changePlace(id) {
    var parentDOM = document.getElementById("outputMulti"),
        first = parentDOM.firstChild,
        div = findBlockDiv(id);

    parentDOM.insertBefore(div, first);
}

function f() {
    var findAll = document.getElementsByClassName("photo");

    var input = document.createElement('input');
    input.type = "file";
    input.setAttribute("value", findAll[0].currentSrc);

    console.log(input);
}
