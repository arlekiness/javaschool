<%--
  Created by IntelliJ IDEA.
  User: belo4ka_new
  Date: 07.09.2018
  Time: 18:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="utf-8">
    <script type="text/javascript" src="static/js/jquery-1.11.2.js"></script>
    <script type="text/javascript" src="static/js/jcanvas.js"></script>
    <link href="static/css/main.css" rel="stylesheet" />
    <title> Метро СПб </title>
</head>


<body>
<div id='top'>

</div>
<div id='content'>
    <div id='input_box'>
        <form action="/giveOptions" method="POST">
            <input type='text' placeholder='From' name='begin' value='' autocomplete='off' list='stations_list' required id='station1'>
            <br> <br>
            <input type='text' placeholder='To' name='end' value='' autocomplete='off' list='stations_list' required id='station2'>
            <br> <br>
            <input type="date" id="start" name="date" value="2018-11-11" min="2018-01-01" max="2018-12-31" />
            <br> <br>
            <datalist id='stations_list' class='options'>

                <option> Avtovo </option>
                <option> Admiralteyskaya </option>
                <option> Akademicheskaya </option>
                <option> Baltiyskaya </option>
                <option> Bukharestskaya </option>
                <option> Vasileostrovskaya	</option>
                <option> Vladimirskaya </option>
                <option> Volkovskaya </option>
                <option> Vyborgskaya </option>
                <option> Gorkovskaya </option>
                <option> Gostiny Dvor </option>
                <option> Grazhdansky Prospekt </option>
                <option> Devyatkino </option>
                <option> Dostoyevskaya </option>
                <option> Yelizarovskaya </option>
                <option> Zvenigorodskaya </option>
                <option> Zvyozdnaya	</option>
                <option> Kirovskiy Zavod </option>
                <option> Komendantsky Prospekt </option>
                <option> Krestovsky Ostrov </option>
                <option> Kupchino </option>
                <option> Ladozhskaya </option>
                <option> Leninsky Prospekt </option>
                <option> Lesnaya </option>
                <option> Ligovsky Prospekt </option>
                <option> Lomonosovskaya </option>
                <option> Mayakovskaya </option>
                <option> Mezhdunarodnaya </option>
                <option> Moskovskaya </option>
                <option> Moskovskiye Vorota </option>
                <option> Narvskaya </option>
                <option> Nevsky Prospekt </option>
                <option> Novocherkasskaya </option>
                <option> Obvodny Kanal </option>
                <option> Obukhovo </option>
                <option> Ozerki </option>
                <option> Park Pobedy </option>
                <option> Parnas </option>
                <option> Petrogradskaya </option>
                <option> Pionerskaya </option>
                <option> Ploshchad Alexandra Nevskogo-1 </option>
                <option> Ploshchad Alexandra Nevskogo-2 </option>
                <option> Ploshchad Vosstaniya </option>
                <option> Ploshchad Lenina </option>
                <option> Ploschad Muzhestva </option>
                <option> Politekhnicheskaya </option>
                <option> Primorskaya </option>
                <option> Proletarskaya </option>
                <option> Prospekt Bolshevikov </option>
                <option> Prospekt Veteranov </option>
                <option> Prospekt Prosvescheniya </option>
                <option> Pushkinskaya </option>
                <option> Rybatskoye </option>
                <option> Sadovaya </option>
                <option> Sennaya Ploschad </option>
                <option> Spasskaya </option>
                <option> Sportivnaya </option>
                <option> Staraya Derevnya </option>
                <option> Tekhnologichesky Institut-1 </option>
                <option> Tekhnologichesky Institut-2 </option>
                <option> Udelnaya </option>
                <option> Ulitsa Dybenko </option>
                <option> Frunzenskaya </option>
                <option> Chernyshevskaya </option>
                <option> Chkalovskaya </option>
                <option> Chornaya Rechka </option>
                <option> Elektrosila </option>

            </datalist>
            <input type='submit' value='Показать маршрут' id='submit'>
            <br> <br>
            <div id='result'>


            </div>
        </form>
    </div>
    <div id='map'>
        <img src="static/images/map_clear.png" id='image'/>
        <div id='stat_1'>Komendantsky Prospekt</div>
        <div id='stat_2'>Staraya Derevnya</div>
        <div id='stat_3'>Krestovsky Ostrov</div>
        <div id='stat_4'>Chkalovskaya</div>
        <div id='stat_5'>Sportivnaya</div>
        <div id='stat_6'>Admiralteyskaya</div>
        <div id='stat_7'>Sadovaya</div>
        <div id='stat_8'>Zvenigorodskaya</div>
        <div id='stat_9'>Obvodny Kanal</div>
        <div id='stat_10'>Volkovskaya</div>
        <div id='stat_11'>Bukharestskaya</div>
        <div id='stat_12'>Mezhdunarodnaya</div>

        <div id='stat_13'>Parnas</div>
        <div id='stat_14'>Prospekt Prosvescheniya</div>
        <div id='stat_15'>Ozerki</div>
        <div id='stat_16'>Udelnaya</div>
        <div id='stat_17'>Pionerskaya</div>
        <div id='stat_18'>Chornaya Rechka</div>
        <div id='stat_19'>Petrogradskaya</div>
        <div id='stat_20'>Gorkovskaya</div>
        <div id='stat_21'>Nevsky Prospekt</div>
        <div id='stat_22'>Sennaya Ploschad</div>
        <div id='stat_23'>Tekhnologichesky Institut-2</div>
        <div id='stat_24'>Frunzenskaya</div>
        <div id='stat_25'>Moskovskiye Vorota</div>
        <div id='stat_26'>Elektrosila</div>
        <div id='stat_27'>Park Pobedy</div>
        <div id='stat_28'>Moskovskaya</div>
        <div id='stat_29'>Zvyozdnaya</div>
        <div id='stat_30'>Kupchino</div>

        <div id='stat_31'>Devyatkino</div>
        <div id='stat_32'>Grazhdansky Prospekt</div>
        <div id='stat_33'>Akademicheskaya</div>
        <div id='stat_34'>Politekhnicheskaya</div>
        <div id='stat_35'>Ploschad Muzhestva</div>
        <div id='stat_36'>Lesnaya</div>
        <div id='stat_37'>Vyborgskaya</div>
        <div id='stat_38'>Ploshchad Lenina</div>
        <div id='stat_39'>Chernyshevskaya</div>
        <div id='stat_40'>Ploshchad Vosstaniya</div>
        <div id='stat_41'>Vladimirskaya</div>
        <div id='stat_42'>Pushkinskaya</div>
        <div id='stat_43'>Tekhnologichesky Institut-1</div>
        <div id='stat_44'>Baltiyskaya</div>
        <div id='stat_45'>Narvskaya</div>
        <div id='stat_46'>Kirovskiy Zavod</div>
        <div id='stat_47'>Avtovo</div>
        <div id='stat_48'>Leninsky Prospekt</div>
        <div id='stat_49'>Prospekt Veteranov</div>

        <div id='stat_50'>Primorskaya</div>
        <div id='stat_51'>Vasileostrovskaya</div>
        <div id='stat_52'>Gostiny Dvor</div>
        <div id='stat_53'>Mayakovskaya</div>
        <div id='stat_54'>Ploshchad Alexandra Nevskogo-1</div>
        <div id='stat_55'>Yelizarovskaya</div>
        <div id='stat_56'>Lomonosovskaya</div>
        <div id='stat_57'>Proletarskaya</div>
        <div id='stat_58'>Obukhovo</div>
        <div id='stat_59'>Rybatskoye</div>


        <div id='stat_60'>Spasskaya</div>
        <div id='stat_61'>Dostoyevskaya</div>
        <div id='stat_62'>Ligovsky Prospekt</div>
        <div id='stat_63'>Ploshchad Alexandra Nevskogo-2</div>
        <div id='stat_64'>Novocherkasskaya</div>
        <div id='stat_65'>Ladozhskaya</div>
        <div id='stat_66'>Prospekt Bolshevikov</div>
        <div id='stat_67'>Ulitsa Dybenko</div>
    </div>
</div>
<script>




    $("div[id^='stat_']").hover(
        function() {
            $(this).css('background-color', '#C5D1FA');
        },
        function() {
            $(this).css('background-color', 'white');
        });

    $("div[id^='stat_']").click(function() {
        if ($('#station1').val()) {
            if ($('#station2').val()) {
                $('#station1').val($(this).text());
                $('#station2').val('');
            } else {
                if ($('#station1').val() != $(this).text()) {
                    $('#station2').val($(this).text());
//							$('#submit').click();
                }
            }
        } else {
            $('#station1').val($(this).text());
        }
    });
</script>
</body>
</html>
