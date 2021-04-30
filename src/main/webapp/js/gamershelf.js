/**
 * view-controller for gamershelf.html
 *
 * M133: Bookshelf
 *
 * @author  Marcel Suter
 */

/**
 * register listeners and load all books
 */
$(document).ready(function () {
    loadGamer();

    /**
     * listener for buttons within shelfForm
     */
    $("#shelfForm").on("click", "button", function () {
        if (confirm("Wollen Sie diesen Gamer wirklich löschen?")) {
            deleteGamer(this.value);
        }
    });



});

function loadGamer() {
    $
        .ajax({
            url: "./resource/gamer/list",
            dataType: "json",
            type: "GET"
        })
        .done(showGamer)
        .fail(function (xhr, status, errorThrown) {
            if (xhr.status == 403) {
                window.location.href = "./login.html";
            } else if (xhr.status == 404) {
                $("#message").text("keine Gamer vorhanden");
            }else {
                $("#message").text("Fehler beim Lesen der Gamer");
            }
        })

}

/**
 * shows all books as a table
 *
 * @param gamerData all books as an array
 */
function showGamer(gamerData) {

    let table = document.getElementById("gamershelf");
    clearTable(table);

    $.each(gamerData, function (uuid, gamer) {
        if (gamer.nachname) {
            let row = table.insertRow(-1);
            let cell = row.insertCell(-1);
            cell.innerHTML = gamer.nachname;

            cell = row.insertCell(-1);
            cell.innerHTML = gamer.vorname;

            cell = row.insertCell(-1);
            cell.innerHTML = gamer.alter;

            cell = row.insertCell(-1);
            cell.innerHTML = gamer.spiel.spiel;


            cell = row.insertCell(-1);
            cell.innerHTML = "<a href='./gameredit.html?uuid=" + uuid + "'>Bearbeiten</a>";

            cell = row.insertCell(-1);
            cell.innerHTML = "<button type='button' id='delete_" + uuid + "' value='" + uuid + "'>Löschen</button>";


        }
    });
}

function clearTable(table) {
    while (table.hasChildNodes()) {
        table.removeChild(table.firstChild);
    }
}

/**
 * send delete request for a gamer
 * @param gamerUUID
 */
function deleteGamer(gamerUUID) {
    $
        .ajax({
            url: "./resource/gamer/delete?uuid=" + gamerUUID,
            dataType: "text",
            type: "DELETE",
        })
        .done(function (data) {
            loadGamer();
            $("#message").text("Gamer gelöscht");

        })
        .fail(function (xhr, status, errorThrown) {
            $("#message").text("Fehler beim Löschen des Gamers");
        })
}
