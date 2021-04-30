/**
 * view-controller for gameredit.html
 *
 * M133: gamersehlf
 *
 * @author  Kaushall Vimalrajah
 */

/**
 * register listeners and load the gamer data
 */
$(document).ready(function () {
    loadSpiel();
    loadGamer();

    /**
     * listener for submitting the form
     */
    $("#bookeditForm").submit(saveGamer);

    /**
     * listener for button [abbrechen], redirects to gamershelf
     */
    $("#cancel").click(function () {
        window.location.href = "./gamershelf.html";
    });


});

/**
 *  loads the data of this Gamer
 *
 */
function loadGamer() {
    let gamerUUID = $.urlParam("uuid");
    if (gamerUUID!== null) {
        $.ajax({
                url: "./resource/gamer/read?uuid=" + gamerUUID,
                dataType: "json",
                type: "GET"
            })
            .done(showGamer)
            .fail(function (xhr, status, errorThrown) {
                if (xhr.status == 403) {
                    window.location.href = "./login.html";
                } else if (xhr.status == 404) {
                    $("#message").text("Kein Gamer gefunden");
                } else {
                   console.log(xhr.status+" Ich bin hier")
                    // window.location.href = "./gamershelf.html";
                }
            })
    }

}

/**
 * shows the data of this gamer
 * @param  gamer  the gamer data to be shown
 */
function showGamer(gamer) {
    $("#gamerUUID").val(gamer.gamerUUID);
    $("#nachname").val(gamer.nachname);
    $("#vorname").val(gamer.vorname);
    $("#alter").val(gamer.alter);
    $("#spiel").val(gamer.spiel.spielUUID);


}

/**
 * sends the gamer data to the webservice
 * @param form the form being submitted
 */
function saveGamer(form) {
    form.preventDefault();

    let url = "./resource/gamer/";
    let type;

    let gamerUUID = $("#gamerUUID").val();
    if (gamerUUID) {
        type= "PUT";
        url += "update"
    } else {
        type = "POST";
        url += "create";
    }

    $
        .ajax({
            url: url,
            dataType: "text",
            type: type,
            data: $("#bookeditForm").serialize()
        })
        .done(function (jsonData) {
            window.location.href = "./gamershelf.html"
        })
        .fail(function (xhr, status, errorThrown) {
            if (xhr.status == 404) {
                $("#message").text("Dieses Buch existiert nicht");
            } else {
                $("#message").text("Fehler beim Speichern des Buchs");
            }
        })
}

function loadSpiel() {
    $
        .ajax({
            url: "./resource/spiel/list",
            dataType: "json",
            type: "GET"
        })
        .done(showSpiel)
        .fail(function (xhr, status, errorThrown) {
            if (xhr.status == 404) {
                $("#message").text("Kein Verlag gefunden");
            } else {
                window.location.href = "./gamershelf.html";
            }
        })
}

function showSpiel(spiele) {

    $.each(spiele, function (uuid, spiel) {
        $('#spiel').append($('<option>', {
            value: spiel.spielUUID,
            text: spiel.spiel
        }));
    });
}
