<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page isELIgnored ="false" %>

<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="utf-8">
    <title>meTro-Systems - Tickets</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <link rel="shortcut icon" href="/static/images/sw.png" type="image/png">


    <!-- css -->
    <link href="/static/css/bootstrap2.min.css" rel="stylesheet" />
    <link href="/static/css/style-tickets.css" rel="stylesheet" />
    <link href="/static/css/vypad-spiski-dlya-form.css" rel="stylesheet" />
    <link rel="stylesheet" href="/static/css/sweetalert2.css">
    <!-- ==================================================
                   javascript
================================================== -->
    <script src="/static/js/jquery-3.2.1.js"></script>
    <script src="/static/js/jcanvas.js"></script>
    <script src="/static/js/main.js"></script> <!-- Resource jQuery -->
    <script src="/static/js/custom.js"></script>
    <script src="/static/js/velocity.min.js"></script>
    <script src="/static/js/bootstrap.min.js"></script>
    <script src="/static/js/sweetalert2.js"></script>



</head>

<body>
<div id="site-content" class="tickets-page">
    <div class="all-menu navbar-fixed-top">

        <!-- Основное меню -->
        <header class="header-tickets">
            <div class="navbar">
                <div class="container">
                    <a class="navbar-brand" href="/"><i class="icon-logo"></i></a>
                    <div class="navbar-collapse collapse">
                        <ul class="nav navbar-nav">
                            <li><a href="/">Home</a></li>
                            <li class="active"><a href="/tickets">Tickets</a></li>
                            <li><a href="/schedule">Schedule</a></li>
                        </ul>
                    </div>


                    <!-- КНОПКИ -->

                    <div class="register-signin-tickets pull-right main-nav1">

                        <ul>
                            <li class="sign-out dropdown">
                                <a href="#" data-toggle="dropdown" class="dropdown-toggle user-avatar"><span class="avatarka"><i class="fa fa-user-circle-o"></i></span><sec:authentication property="principal.username" /> <i class="fa fa-caret-down"></i></a>
                                <ul class="dropdown-menu dash-user">
                                    <li><a href="/myTickets">My tickets</a></li>
                                    <br>
                                    <sec:authorize access="hasRole('ROLE_ADMIN')">
                                        <li><a href="/dashtrain">Admin Panel</a></li>
                                        <br>
                                    </sec:authorize>
                                    <li><a href="/logout">Log out</a></li>
                                </ul>
                            </li>
                        </ul>

                    </div>


                    <!-- гамбургерное меню  -->

                    <div class="ham-menu">
                        <input id="hamburger" class="hamburger" type="checkbox"/>
                        <label class="hamburger" for="hamburger">
                            <i></i>
                        </label>
                        <div class="drawer-list">
                            <ul>
                                <li><a href="/">Home</a></li>
                                <li class="active"><a href="tickets.html">Tickets</a></li>
                                <li><a href="/schedule">Schedule</a></li>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
        </header>
    </div>
    <!-- конец основного меню -->

    <!-- контент -->


    <div class="wrapper-tickets">
        <p class="hero-text text-center">Search for tickets</p>

        <div id='content'>
            <div id='input_box'>
                <form action="/tickets" method="POST" class="cd-form cd-form1">
                    <input class="full-width has-padding has-border" type='text' placeholder='Start Station' name='begin' value='' autocomplete='off' list='stations_list' required id='station1'>

                    <input class="full-width has-padding has-border" type='text' placeholder='End Station' name='end' value='' autocomplete='off' list='stations_list' required id='station2'>
                    <datalist id='stations_list' class='options'>

                        <option> Avtovo </option>

                        <option> Admiralteyskaya </option>

                        <option> Akademicheskaya </option>

                        <option> Baltiyskaya </option>

                        <option> Begovaya </option>

                        <option> Bukharestskaya </option>

                        <option> Vasileostrovskaya </option>

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

                        <option> Zvyozdnaya </option>

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

                        <option> Novokrestovskaya </option>

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

                    <p class="fieldset" style="display: inline-block; width: 20%">
                        <label class="image-replace cd-password" for="signup-date">Date</label>
                        <input class="full-width has-padding-icon has-border" name="date" id="date" type="text"  placeholder="Date" onClick="xCal(this)" onKeyUp="xCal()" required>
                        <a href="#0" class="date-icon"><i class="fa fa-calendar"></i></a>
                    </p>

                    <input type='submit' value='SEARCH' id='submit'>
                    <br> <br>
                    <div id='result'>


                    </div>
                </form>
                <c:if test="${not empty noTrainsOnDate}">
                    <script>
                        swal({
                            title: 'WE DEEPLY SORRY FOR OUR ADMIN...',
                            text: '..but there is no trains',
                            type: 'error'
                        });
                    </script>
                </c:if>
                <c:if test="${not empty beginStationClosed}">
                    <script>
                        swal({
                            title: 'CLOSED',
                            text: 'First station is closed. Try another one',
                            type: 'error'
                        });
                    </script>
                </c:if>
                <c:if test="${not empty endStationClosed}">
                    <script>
                        swal({
                            title: 'CLOSED',
                            text: 'End station is closed. Try another one',
                            type: 'error'
                        });
                    </script>
                </c:if>
                <c:if test="${not empty ATSClosed}">
                    <script>
                        swal({
                            title: 'CLOSED',
                            text: 'Transition stations is closed. Save God our souls',
                            type: 'error'
                        });
                    </script>
                </c:if>
                <c:if test="${not empty systemError}">
                    <script>
                        swal({
                            title: 'OOOOPS...',
                            text: 'Something very bad happened. Try again',
                            type: 'error'
                        });
                    </script>
                </c:if>




            </div>
            <div id='map'>
                <span><img src="/static/images/map_clear.png" id='image'/></span>
                <div id='stat_1' class="violet">Komendantsky Prospekt</div>
                <div id='stat_2' class="violet">Staraya Derevnya</div>
                <div id='stat_3' class="violet">Krestovsky Ostrov</div>
                <div id='stat_4' class="violet">Chkalovskaya</div>
                <div id='stat_5' class="violet">Sportivnaya</div>
                <div id='stat_6' class="violet">Admiralteyskaya</div>
                <div id='stat_7' class="violet">Sadovaya</div>
                <div id='stat_8' class="violet">Zvenigorodskaya</div>
                <div id='stat_9' class="violet">Obvodny Kanal</div>
                <div id='stat_10' class="violet">Volkovskaya</div>
                <div id='stat_11' class="violet">Bukharestskaya</div>
                <div id='stat_12' class="violet">Mezhdunarodnaya</div>

                <div id='stat_13' class="blue">Parnas</div>
                <div id='stat_14' class="blue">Prospekt Prosvescheniya</div>
                <div id='stat_15' class="blue">Ozerki</div>
                <div id='stat_16' class="blue">Udelnaya</div>
                <div id='stat_17' class="blue">Pionerskaya</div>
                <div id='stat_18' class="blue">Chornaya Rechka</div>
                <div id='stat_19' class="blue">Petrogradskaya</div>
                <div id='stat_20' class="blue">Gorkovskaya</div>
                <div id='stat_21' class="blue">Nevsky Prospekt</div>
                <div id='stat_22' class="blue">Sennaya Ploschad</div>
                <div id='stat_23' class="blue">Tekhnologichesky Institut-2</div>
                <div id='stat_24' class="blue">Frunzenskaya</div>
                <div id='stat_25' class="blue">Moskovskiye Vorota</div>
                <div id='stat_26' class="blue">Elektrosila</div>
                <div id='stat_27' class="blue">Park Pobedy</div>
                <div id='stat_28' class="blue">Moskovskaya</div>
                <div id='stat_29' class="blue">Zvyozdnaya</div>
                <div id='stat_30' class="blue">Kupchino</div>

                <div id='stat_31' class="red">Devyatkino</div>
                <div id='stat_32' class="red">Grazhdansky Prospekt</div>
                <div id='stat_33' class="red">Akademicheskaya</div>
                <div id='stat_34' class="red">Politekhnicheskaya</div>
                <div id='stat_35' class="red">Ploschad Muzhestva</div>
                <div id='stat_36' class="red">Lesnaya</div>
                <div id='stat_37' class="red">Vyborgskaya</div>
                <div id='stat_38' class="red">Ploshchad Lenina</div>
                <div id='stat_39' class="red">Chernyshevskaya</div>
                <div id='stat_40' class="red">Ploshchad Vosstaniya</div>
                <div id='stat_41' class="red">Vladimirskaya</div>
                <div id='stat_42' class="red">Pushkinskaya</div>
                <div id='stat_43' class="red">Tekhnologichesky Institut-1</div>
                <div id='stat_44' class="red">Baltiyskaya</div>
                <div id='stat_45' class="red">Narvskaya</div>
                <div id='stat_46' class="red">Kirovskiy Zavod</div>
                <div id='stat_47' class="red">Avtovo</div>
                <div id='stat_48' class="red">Leninsky Prospekt</div>
                <div id='stat_49' class="red">Prospekt Veteranov</div>

                <div id='stat_68' class="green">Begovaya</div>
                <div id='stat_69' class="green">Novokrestovskaya</div>
                <div id='stat_50' class="green">Primorskaya</div>
                <div id='stat_51' class="green">Vasileostrovskaya</div>
                <div id='stat_52' class="green">Gostiny Dvor</div>
                <div id='stat_53' class="green">Mayakovskaya</div>
                <div id='stat_54' class="green">Ploshchad Alexandra Nevskogo-1</div>
                <div id='stat_55' class="green">Yelizarovskaya</div>
                <div id='stat_56' class="green">Lomonosovskaya</div>
                <div id='stat_57' class="green">Proletarskaya</div>
                <div id='stat_58' class="green">Obukhovo</div>
                <div id='stat_59' class="green">Rybatskoye</div>


                <div id='stat_60' class="orange">Spasskaya</div>
                <div id='stat_61' class="orange">Dostoyevskaya</div>
                <div id='stat_62' class="orange">Ligovsky Prospekt</div>
                <div id='stat_63' class="orange">Ploshchad Alexandra Nevskogo-2</div>
                <div id='stat_64' class="orange">Novocherkasskaya</div>
                <div id='stat_65' class="orange">Ladozhskaya</div>
                <div id='stat_66' class="orange">Prospekt Bolshevikov</div>
                <div id='stat_67' class="orange">Ulitsa Dybenko</div>
            </div>

            <c:if test="${not empty noTickets}">
                <script>
                    swal({
                        title: 'NO TICKETS',
                        text: 'You are not fast, boy. Sorry!',
                        type: 'error'
                    });
                </script>
            </c:if>

        </div>



        <script>
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
            ;(function() {
                var lang = { // lang[a.lang].x
                    "en": {
                        x: "Close", nex: "Next month", pre: "Previous month", clear: "Clear",
                        m: ["Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Okt","Nov","Dec"],
                        mo: ["January","February","March","April","May","June","July","August","September","October","November","December"],
                        w: "Mo</td><td>Tu</td><td>We</td><td>Th</td><td>Fr</td><td>Sa</td><td>Su"
                    }
                }
                var def = {
                    lang: "en",
                    id: "",
                    "class": "xcalend",
                    delim: ".",
                    order: 0, // 0/1/2
                    o: {value: ""},
                    year: -1, month: -1, dat: -1, day: -1, dop: "",
                    autoOn: 1, autoOff: 0, now:1, set0: 0, x: 1, hide: 1, to: "", fn: ""
                }
                function iss(n){if(typeof n==="undefined" || n===null)return false;return true}
                function Nod(n){if(typeof n=='string') n=document.getElementById(n);return n}
                function Del(n){n=Nod(n); if(!n)return; if(typeof(n.remove)=="function") n.remove(); else n.parentNode.removeChild(n)}
                function HTM(n,h,b){if(!iss(b))b="beforeend"; Nod(n).insertAdjacentHTML(b,h)}
                function Eve(e,evTyp,fn) {
                    if(e.addEventListener) e.addEventListener(evTyp,fn,false)
                    else if(e.attachEvent) e.attachEvent('on'+evTyp,fn)
                    else e['on'+evTyp]=fn
                }
                function scrol(y) {
                    var a={'X': 'scrollLeft', 'Y': 'scrollTop'}
                    if(typeof y==="undefined" || !(y in a)) y='Y';
                    return (window['page'+y+'Offset'] || document.documentElement[a[y]] || document.body[a[y]]);
                }

                window.xCal = function(ob, delim, order) { // xCal(this,'-',1) // xCal() == close
                    var a={};
                    for(var key in def) a[key] = def[key];
                    if(typeof delim==="object") {
                        for(var key in delim) a[key] = delim[key];
                    } else {
                        if(iss(delim)) a.delim = delim;
                        if(iss(order)) a.order = order;
                    }
                    if(a.id==="") a.id = a['class'];

                    if(typeof ob!=="object") {
                        if((typeof ob==="undefined" || ob==0 || ob==1) && Nod(a.id)) { // Close
                            if(a.hide==1) {
                                if(typeof delim!=="string") Nod(a.id).style.display="none";
                                else Nod(delim).style.display="none";
                            }
                            return false;
                        }
                        if(ob==2 || ob==="now") { // Return Now
                            var D = new Date(), d = D.getDate(), m = D.getMonth()+1, y = D.getFullYear();
                            if(d<10) d = "0"+d;
                            if(m<10) m = "0"+m;
                            if(a.order==1) return y+a.delim+m+a.delim+d;
                            if(a.order==2) return m+a.delim+d+a.delim+y;
                            else return d+a.delim+m+a.delim+y;
                        }
                    }
                    a.o = Nod(ob);
                    a.f = function() {
                        var m = a.month+1, d = a.dat, y = a.year;
                        a.sdat = a.dat; a.smonth = a.month; a.syear = a.year;
                        if(a.order==1) {d = y; y = a.dat}
                        else if(a.order==2) {d = m; m = a.dat}
                        if(y<10) y="0"+y;
                        if(m<10) m="0"+m;
                        if(d<10) d="0"+d;
                        d += a.delim+m+a.delim+y;
                        if(a.dop!=="") d += " "+a.dop;
                        if(a.o) a.o.value = xCal.value = d;
                        if(a.hide==1) Nod(a.id).style.display="none";
                        if(typeof a.fn==="function") a.fn(d, a);
                        else if(typeof a.fn==="string" && a.fn!=="") eval(a.fn+"('"+d+"');");
                    }

                    if(typeof a.o==="undefined") return false;
                    a=xCal.gVal(a);
                    a=xCal.gDat(a);


                    if(!Nod(a.id)) {
                        if(a.to=="") a.to=document.body; else a.to=Nod(a.to);
                        HTM(a.to, '<table id="'+a.id+'" class="'+a['class']+'"><thead></thead><tbody></tbody><tfoot></tfoot></table>');
                    } else Nod(a.id).style.display="";

                    var oo = a.o.getBoundingClientRect();
                    Nod(a.id).style.left=oo.left+scrol('X')+"px";
                    Nod(a.id).style.top=oo.bottom+scrol()+"px";

                    xCal.fM = function(a) {
                        var m=a.month, ca='', j=1,
                            months=lang[a.lang].m;
                        ca += '<td colspan=2 title="12"';
                        if(m==11) ca += ' class="today"';
                        else if(a.tmonth==(11)) ca += ' class="tday"';
                        ca += '>'+lang[a.lang].m[11]+'</td>';
                        for(var i=0; i<(months.length-1); i++) {
                            j++;
                            ca += '<td colspan=2 title="'+(i+1)+'"';
                            if(m==i) ca += ' class="today"';
                            else if(a.tmonth==(i)) ca += ' class="tday"';
                            ca += '>'+months[i]+"</td>";
                            if(j>2) {ca += '</tr><tr>'; j=0}
                        }
                        Del(document.querySelector("#"+a.id+" tbody"));
                        HTM(a.id, '<tbody><tr><th rowspan=4 valign=bottom></th>'+ca+'</tr></tbody>');
                        var k = document.querySelectorAll("#"+a.id+" tbody td");
                        for(var i=0; i<k.length; i++) {
                            k[i].onclick = function() {
                                var t = this.title;
                                if(t=='') return;
                                a.month=parseInt(t)-1;
                                xCal.f(a);
                            }
                        }
                    }

                    xCal.fY = function(a) {
                        var y=a.year, ca='', j=0;
                        for(var i=-8; i<7; i++) {
                            j++;
                            ca += '<td colspan=2';
                            if(y==(y+i)) ca += ' class="today"';
                            else if(a.tyear==(y+i)) ca += ' class="tday"';
                            ca += '>'+(y+i)+"</td>";
                            if(j>2) {ca += '</tr><tr>'; j=0}
                        }
                        Del(document.querySelector("#"+a.id+" tbody"));
                        HTM(a.id, '<tbody><tr><th rowspan=5 valign=bottom></th>'+ca+'</tr></tbody>');
                        var k = document.querySelectorAll("#"+a.id+" tbody td");
                        for(var i=0; i<k.length; i++) {
                            k[i].onclick = function() {
                                var t = this.innerHTML;
                                if(t=='' || t=="&nbsp;") return;
                                a.year=parseInt(t);
                                xCal.f(a);
                            }
                        }
                    }

                    xCal.f = function(a) {
                        Del(document.querySelector("#"+a.id+" thead"));
                        Del(document.querySelector("#"+a.id+" tbody"));
                        Del(document.querySelector("#"+a.id+" tfoot"));
                        var mm, y=a.year, m=a.month, dat=a.dat,
                            Dlast = new Date(y, m+1, 0).getDate(),
                            DNlast = new Date(y, m, Dlast).getDay(),
                            DNfirst = new Date(y, m, 1).getDay(),
                            ca = '', j=0,
                            months=lang[a.lang].mo;
                        if(DNfirst != 0) j = DNfirst-1;
                        else j = 6;

                        HTM(a.id, '<thead><tr><td class="cal-l" title="'+lang[a.lang].pre+'"><<</td><td colspan=3 class="cal-m"></td><td colspan=2 class="cal-y"></td><td class="cal-r" title="'+lang[a.lang].nex+'">>></td></tr><tr><td>'+lang[a.lang].w+'</td></tr></thead>');

                        if(j>0) {//
                            if(m==0) mm = 11; else mm = m-1;
                            if(j>1) {
                                ca += '<td colspan='+j+' class="cal-l" align=left title="'+lang[a.lang].pre+' '+lang[a.lang].mo[mm]+'"><b><< '+lang[a.lang].m[mm]+'</b></td>';
                            } else ca += '<td class="cal-l" title="'+lang[a.lang].pre+' '+lang[a.lang].mo[mm]+'"><b>>></b></td>';
                        }

                        for(var i=1; i<=Dlast; i++) {
                            j++;
                            ca += '<td';
                            if(i==dat) {
                                if(m==a.smonth && y==a.syear) ca += ' class="today"';
                                else ca += ' class="tday"';
                            } else if(i==a.tdat && m==a.tmonth && y==a.tyear) ca += ' class="tday"';
                            ca += '>'+i+'</td>';
                            if(j>6) {ca += '</tr><tr>'; j=0}
                        }
                        if(DNlast>0) {
                            if(m>10) mm = 0; else mm = m+1;
                            if(DNlast<6) {
                                ca += '<td colspan='+(7-DNlast)+' class="cal-r" align=right title="'+lang[a.lang].nex+' '+lang[a.lang].mo[mm]+'"><b>'+lang[a.lang].m[mm]+' >></b></td>';
                            } else ca += '<td class="cal-r" title="'+lang[a.lang].nex+' '+lang[a.lang].mo[mm]+'"><b>NEXT</b></td>';
                        }


                        HTM(a.id, '<tbody><tr class="cal-first">'+ca+'</tr></tbody>');
                        ca='';
                        var k, kk=[3,3,1];
                        if(!a.now) kk[0]=0;
                        if(!a.set0) kk[1]=0;
                        if(!a.x) kk[2]=0;
                        if(a.now) {
                            k=kk[0]+kk[1]+kk[2];
                            if(k<7) kk[0] += (7-k);
                            ca += '<td colspan='+kk[0]+' class="cal-nw"></td>';
                        }
                        if(a.set0) {
                            k=kk[0]+kk[1]+kk[2];
                            if(k<7) kk[1] += (7-k);
                            ca += '<td colspan='+kk[1]+' class="cal-s0">'+lang[a.lang].clear+'</td>';
                        }
                        if(a.x) {
                            k=kk[0]+kk[1]+kk[2];
                            if(k<7) kk[2] += (7-k);
                            ca += '<td colspan='+kk[2]+' onClick="document.getElementById(\''+a.id+'\').style.display=\'none\'" title="'+lang[a.lang].x+'" class="bold">'+(kk[2]>2 ? lang[a.lang].x : '&#215;')+'</td>';
                        }
                        HTM(a.id, '<tfoot><tr>'+ca+'</tr></tfoot>');


                        document.querySelector("#"+a.id+" thead td.cal-m").innerHTML = months[m];
                        document.querySelector("#"+a.id+" thead td.cal-y").innerHTML = y;
                        document.querySelector("#"+a.id+" thead td.cal-l").onclick = function() {xCal.mmm(a)}
                        document.querySelector("#"+a.id+" thead td.cal-r").onclick = function() {xCal.mpp(a)}
                        document.querySelector("#"+a.id+" thead td.cal-m").onclick = function() {xCal.fM(a)}
                        document.querySelector("#"+a.id+" thead td.cal-y").onclick = function() {xCal.fY(a)}

                        k = document.querySelector("#"+a.id+" tfoot td.cal-nw");
                        if(k) { // a.now
                            k.innerHTML = xCal(2, a);
                            k.onclick = function() {
                                var dop="";
                                a=xCal.gDop(a);
                                if(a.dop!="") dop = " "+a.dop;
                                if(a.o) a.o.value=xCal.value=this.innerHTML+dop; // xCal(2, a);
                                if(a.hide==1) {
                                    document.getElementById(a.id).style.display='none';
                                } else {
                                    var o=document.querySelectorAll("#"+a.id+" .today");
                                    for(var j=0; j<o.length; j++) {o[j].className=""}
                                    a=xCal.gDat(a);
                                    var D = new Date(); a.year = D.getFullYear(); a.month = D.getMonth(); a.dat = D.getDate();
                                }
                                a.sdat = a.dat; a.smonth = a.month; a.syear = a.year;
                                if(typeof a.fn==="function") a.fn(this.innerHTML, a);
                                else if(typeof a.fn==="string" && a.fn!=="") eval(a.fn+"('"+this.innerHTML+"');");
                                if(a.hide!=1) xCal.f(a);//xCal(a.id, a);
                            }
                        }
                        k = document.querySelector("#"+a.id+" tfoot td.cal-s0");
                        if(k) { // a.set0
                            k.onclick = function() {
                                var t='00'+a.delim+'00'+a.delim+'00';
                                if(a.order==1) t='00'+t; else t+='00';
                                if(a.o) a.o.value=xCal.value=t;
                                a.sdat = a.dat; a.smonth = a.month; a.syear = a.year;
                                if(a.hide==1) document.getElementById(a.id).style.display='none';
                            }
                        }
                        if(a.autoOff){Nod(a.id).onmouseleave = function() {xCal()}}

                        var k = document.querySelectorAll("#"+a.id+" tbody td");
                        for(var i=0; i<k.length; i++) {
                            k[i].onclick = function() {
                                var t = this.innerHTML;
                                if(t=='' || t=="&nbsp;") return;
                                if(a.hide!=1) {
                                    var o=document.querySelectorAll("#"+a.id+" .today");
                                    for(var j=0; j<o.length; j++) {o[j].className=""}
                                    this.className="today";
                                }
                                a=xCal.gDop(a);
                                a.dat=t;
                                if(typeof a.f==="function") a.f(a.id);
                            }
                        }
                        var r=document.querySelector("#"+a.id+" tbody td.cal-l");
                        if(r!=null){r.onclick = function() {xCal.mmm(a)}}
                        r=document.querySelector("#"+a.id+" tbody td.cal-r");
                        if(r!=null){r.onclick = function() {xCal.mpp(a)}}
                    }

                    xCal.f(a);

                    return false;
                }

                xCal.mmm = function(a) {
                    a.month-=1;
                    if(a.month<0) {a.month=11; a.year--}
                    xCal.f(a);
                }
                xCal.mpp = function(a) {
                    a.month+=1;
                    if(a.month>11) {a.month=0; a.year++}
                    xCal.f(a);
                }

                xCal.gDop = function(a) {
                    if(typeof a.o==="undefined") a.o={value: ""}
                    if(typeof a.o.value==="undefined" || a.o.value==="") return a;
                    var b = a.o.value.split(" ");
                    if(iss(b[1])) a.dop = b[1]; else a.dop = "";
                    return a;
                }
                xCal.gVal = function(a) {
                    if(typeof a.o==="undefined") a.o={value: ""}
                    if(typeof a.o.value==="undefined" || a.o.value==="") return a;
                    var b = a.o.value.split(" ");
                    if(iss(b[1])) a.dop = b[1]; else a.dop = "";
                    b = b[0].split(a.delim);
                    for(var i=0;i<3;i++) if(!iss(b[i])) b[i] = -1;
                    if(a.order==2) {
                        b[3] = b[1]; b[1] = b[0]; b[0] = b[3];
                        b[1] = parseInt(b[1])-1;
                    } else {
                        if(a.order==1) {b[3] = b[2]; b[2] = b[0]; b[0] = b[3]}
                        b[1] = parseInt(b[1])-1;
                    }
                    a.syear = a.year = parseInt(b[2]);
                    a.smonth = a.month = b[1];
                    a.sdat = a.dat = parseInt(b[0]);
                    return a;
                }

                xCal.gDat = function(a) {
                    var D = new Date();
                    a.tyear = D.getFullYear();
                    a.tmonth = D.getMonth();
                    a.tdat = D.getDate();
                    a.tday = D.getDay();
                    if(a.month>-1) {
                        if(a.year<0) a.year = D.getFullYear();
                        if(a.dat>0) D.setFullYear(a.year, a.month, a.dat);
                        else D.setFullYear(a.year, a.month);
                    }
                    a.year = D.getFullYear();
                    a.month = D.getMonth();
                    a.dat = D.getDate();
                    a.day = D.getDay();
                    a.sdat = a.dat; a.smonth = a.month; a.syear = a.year;
                    return a;
                }

                xCal.set = function(o) {for(var key in o) def[key]=o[key]}

                xCal.all = function(cl, delim, order) {
                    if(typeof cl==="undefined" || cl=="") return;
                    var oo;
                    if(document.getElementsByClassName) oo = document.getElementsByClassName(cl);
                    else oo = document.querySelectorAll("."+cl);
                    if(oo.length<1) return;
                    for(var i=0; i<oo.length; i++) {
                        var o=oo[i];
                        Eve(o, "input", function() {xCal()});
                        Eve(o, "keyup", function() {xCal()});
                        Eve(o, "click", function() {xCal(this, delim, order)});
                        if(typeof delim==="object") {
                            if(("autoOn" in delim) && delim.autoOn) Eve(o, "mouseenter", function() {xCal(this, delim, order)});
                        } else if(def.autoOn) Eve(o, "mouseenter", function() {xCal(this, delim, order)});
                    }
                }

            })();
        </script>

    </div>



    <!--