<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="Creative - Bootstrap 3 Responsive Admin Template">
    <meta name="author" content="GeeksLabs">
    <meta name="keyword" content="Creative, Dashboard, Admin, Template, Theme, Bootstrap, Responsive, Retina, Minimal">
    <link rel="shortcut icon" href="img/favicon.png">

    <title>WSO2 App Factory - Demo</title>

    <!-- Bootstrap CSS -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <!-- bootstrap theme -->
    <link href="css/bootstrap-theme.css" rel="stylesheet">
    <!--external css-->
    <!-- font icon -->
    <link href="css/elegant-icons-style.css" rel="stylesheet"/>
    <link href="css/font-awesome.min.css" rel="stylesheet"/>
    <!-- full calendar css-->
    <link href="assets/fullcalendar/fullcalendar/bootstrap-fullcalendar.css" rel="stylesheet"/>
    <link href="assets/fullcalendar/fullcalendar/fullcalendar.css" rel="stylesheet"/>
    <!-- owl carousel -->
    <link rel="stylesheet" href="css/owl.carousel.css" type="text/css">
    <link href="css/jquery-jvectormap-2.0.3.css" rel="stylesheet">
    <!-- Custom styles -->
    <link rel="stylesheet" href="css/fullcalendar.css">
    <link href="css/widgets.css" rel="stylesheet">
    <link href="css/style.css" rel="stylesheet">
    <link href="css/style-responsive.css" rel="stylesheet"/>
    <link href="css/jquery-ui-1.10.4.min.css" rel="stylesheet">
    <style>
        .chart-legend li {
            text-align: left;
            height: 10px;
            font-size: 10px;
        }
        .chart-legend li span{
            display: inline-block;
            width: 12px;
            height: 8px;
            margin-right: 5px;
        }
    </style>
    <!-- HTML5 shim and Respond.js IE8 support of HTML5 -->
    <!--[if lt IE 9]>
    <script src="js/html5shiv.js"></script>
    <script src="js/respond.min.js"></script>
    <script src="js/lte-ie7.js"></script>
    <![endif]-->
</head>

<body>
<!-- container section start -->
<section id="container" class="">


    <header class="header dark-bg">
        <div class="toggle-nav">
            <div class="icon-reorder tooltips" data-original-title="Toggle Navigation" data-placement="bottom"><i
                    class="icon_menu"></i></div>
        </div>

        <!--logo start-->
        <a href="#" class="logo">WSO2 <span class="lite">AF Demo</span></a>
        <!--logo end-->
    </header>
    <!--header end-->

    <!--sidebar start-->
    <aside>
        <div id="sidebar" class="nav-collapse ">
            <!-- sidebar menu start-->
            <ul class="sidebar-menu">
                <li class="active">
                    <a class="" href="index.jsp">
                        <i class="icon_house_alt"></i>
                        <span>Dashboard</span>
                    </a>
                </li>
            </ul>
            <!-- sidebar menu end-->
        </div>
    </aside>
    <!--sidebar end-->

    <!--main content start-->
    <section id="main-content">
        <section class="wrapper">
            <!--overview start-->
            <div class="row">
                <div class="col-lg-12">
                    <h3 class="page-header"><i class="fa fa-laptop"></i> Dashboard</h3>
                    <ol class="breadcrumb">
                        <li><i class="fa fa-home"></i><a href="#">Home</a></li>
                        <li><i class="fa fa-laptop"></i>Dashboard</li>
                    </ol>
                </div>
            </div>

            <c:if test="${not empty stats}">
                <div class="row">
                    <div class="col-lg-3 col-md-3 col-sm-12 col-xs-12">
                        <div class="info-box blue-bg">
                            <i class="fa fa-bell"></i>
                            <div class="count">${stats.totalLeads}</div>
                            <div class="title">Total Leads</div>
                        </div><!--/.info-box-->
                    </div><!--/.col-->

                    <div class="col-lg-3 col-md-3 col-sm-12 col-xs-12">
                        <div class="info-box dark-bg">
                            <i class="fa fa-thumbs-o-up"></i>
                            <div class="count">${stats.newCustomers}</div>
                            <div class="title">New Customers</div>
                        </div><!--/.info-box-->
                    </div><!--/.col-->

                    <div class="col-lg-3 col-md-3 col-sm-12 col-xs-12">
                        <div class="info-box brown-bg">
                            <i class="fa fa-money"></i>
                            <div class="count">${stats.totalSales}</div>
                            <div class="title">Total Sales(K)</div>
                        </div><!--/.info-box-->
                    </div><!--/.col-->

                    <div class="col-lg-3 col-md-3 col-sm-12 col-xs-12">
                        <div class="info-box green-bg">
                            <i class="fa fa-cubes"></i>
                            <div class="count">${stats.stock}</div>
                            <div class="title">Stock</div>
                        </div><!--/.info-box-->
                    </div><!--/.col-->

                </div>
                <!--/.row-->
            </c:if>


            <div class="row">
                <div class="col-lg-9 col-md-12">

                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <h2><i class="fa fa-map-marker red"></i><strong>Countries</strong></h2>

                        </div>
                        <div class="panel-body-map">
                            <div id="map" style="height:380px;"></div>
                        </div>

                    </div>
                </div>
                <div class="col-md-3">
                    <!-- List starts -->
                    <section class="panel panel-default">
                        <div class="panel-heading">
                            <h2><i class="fa fa-share-alt red"></i><strong>Global Market Share</strong></h2>
                        </div>
                        <div class="panel-body text-center">
                            <div id="pieLegend" class="chart-legend"></div>
                            <canvas id="pie" height="300" width="400"></canvas>

                        </div>
                    </section>
                </div>
            </div>


            <!-- Today status end -->


            <div class="row">

                <c:choose>
                    <c:when test="${not empty countryDataList}">
                        <div class="col-lg-9 col-md-12">
                            <div class="panel panel-default">
                                <div class="panel-heading">
                                    <h2><i class="fa fa-flag-o red"></i><strong>Regional Data</strong></h2>
                                </div>
                                <div class="panel-body">
                                    <table class="table bootstrap-datatable countries">
                                        <thead>
                                        <tr>

                                            <th>Country</th>
                                            <th>Target Sales(M)</th>
                                            <th>Current Sales(M)</th>
                                            <th>Market Share (%)</th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <c:forEach var="country" items="${countryDataList}">
                                            <tr>
                                                <td>${country.name}</td>
                                                <td>${country.targetSales}</td>
                                                <td>${country.currentSales}</td>
                                                <td>
                                                    <div class="progress thin">
                                                        <div class="progress-bar progress-bar-danger" role="progressbar"
                                                             aria-valuenow="${country.marketShare}" aria-valuemin="0"
                                                             aria-valuemax="100" style="width: ${country.marketShare}%">
                                                        </div>
                                                        <div class="progress-bar progress-bar-warning"
                                                             role="progressbar"
                                                             aria-valuenow="${100-country.marketShare}"
                                                             aria-valuemin="0"
                                                             aria-valuemax="100"
                                                             style="width: ${100-country.marketShare}%">
                                                        </div>
                                                    </div>
                                                    <span class="sr-only">73%</span>
                                                </td>

                                            </tr>
                                        </c:forEach>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </div>
                        <!--/col-->
                    </c:when>
                    <c:otherwise>
                        <br>
                        <div class="col-lg-9 col-md-12">
                            <div class="alert alert-info">
                                No Registered Users found
                            </div>
                        </div>

                    </c:otherwise>
                </c:choose>
                <div class="col-md-3">
                    <!-- List starts -->
                    <section class="panel panel-default">
                        <div class="panel-heading">
                            <h2><i class="fa fa-usd red"></i><strong>Sales By Month</strong></h2>
                        </div>
                        <div class="panel-body text-center">
                            <div id="barLegend" class="chart-legend"></div>
                            <canvas id="bar" height="300" width="400"></canvas>
                        </div>
                    </section>
                </div>


            </div>
        </section>
    </section>
    <!--main content end-->
</section>
<!-- container section start -->

<!-- javascripts -->
<script src="js/jquery.js"></script>
<script src="js/jquery-ui-1.10.4.min.js"></script>
<script src="js/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="js/jquery-ui-1.9.2.custom.min.js"></script>
<!-- bootstrap -->
<script src="js/bootstrap.min.js"></script>
<!-- nice scroll -->
<script src="js/jquery.scrollTo.min.js"></script>
<script src="js/jquery.nicescroll.js" type="text/javascript"></script>
<!-- charts scripts -->
<script src="js/Chart.js"></script>
<!-- jQuery full calendar -->
<
<script src="js/fullcalendar.min.js"></script> <!-- Full Google Calendar - Calendar -->
<script src="assets/fullcalendar/fullcalendar/fullcalendar.js"></script>
<!--script for this page only-->
<script src="js/calendar-custom.js"></script>
<script src="js/jquery.rateit.min.js"></script>
<!-- custom select -->
<script src="js/jquery.customSelect.min.js"></script>

<!--custome script for all page-->
<script src="js/scripts.js"></script>
<!-- custom script for this page-->

<script src="js/jquery-jvectormap-2.0.3.min.js"></script>
<script src="js/jquery-jvectormap-world-mill.js"></script>
<script src="js/jquery.autosize.min.js"></script>
<script src="js/jquery.placeholder.min.js"></script>
<script src="js/morris.min.js"></script>
<script src="js/jquery.slimscroll.min.js"></script>
<script>

    function getRandomColor() {
        var letters = '0123456789ABCDEF'.split('');
        var color = '#';
        for (var i = 0; i < 6; i++) {
            color += letters[Math.floor(Math.random() * 16)];
        }
        return color;
    }

    /* ---------- Map ---------- */
    $(function () {
        var mapDataList = eval('(' + '${mapData}' + ')');
        //var gdpDataList = eval('(' + '${gdpData}' + ')').gdpData;
        //console.log(gdpDataList);
        console.log(mapDataList);
        $('#map').vectorMap({
                                map: 'world_mill',
                                series: {
                                    regions: [{
                                        values: mapDataList,
                                        scale: ['#99ff99', '#003300'],
                                        normalizeFunction: 'polynomial'
                                    }]
                                },
                                backgroundColor: '#eef3f7',
                                onRegionTipShow: function (e, el, code) {
                                    var marketShareVal = mapDataList[code];
                                    if (marketShareVal) {
                                        el.html(el.html() + ' (Market Share - ' + mapDataList[code] + ')');
                                    } else {
                                        el.html(el.html() + ' - Not touched');
                                    }

                                }
                            });
    });

    $(function () {
        var pieData = [];
        var globalMarketShare = eval('(' + '${globalMarketShare}' + ')');
        for (var company in globalMarketShare) {
            if (globalMarketShare.hasOwnProperty(company)) {
                var data = {
                    value: globalMarketShare[company],
                    color: getRandomColor(),
                    label: company,
                    labelColor : 'white',
                    labelFontSize : '16'
                };
                pieData.push(data);
            }
        }
        var pieChart = new Chart(document.getElementById("pie").getContext("2d")).Pie(pieData);
        document.getElementById('pieLegend').innerHTML = pieChart.generateLegend();

    });

    function generateMonthlyData(monthlySalesData){
        var data = {
            labels: [],
            datasets: [
                {
                    label: "Last Year",
                    fillColor: "rgba(220,220,220,0.5)",
                    strokeColor: "rgba(220,220,220,0.8)",
                    highlightFill: "rgba(220,220,220,0.75)",
                    highlightStroke: "rgba(220,220,220,1)",
                    data: []
                },
                {
                    label: "This Year",
                    fillColor: "rgba(151,187,205,0.5)",
                    strokeColor: "rgba(151,187,205,0.8)",
                    highlightFill: "rgba(151,187,205,0.75)",
                    highlightStroke: "rgba(151,187,205,1)",
                    data: []
                }
            ]
        };

        for (var i = 0; i < monthlySalesData.length; i++) {
            data.labels.push(monthlySalesData[i]["month"]);
            data.datasets[0].data.push(monthlySalesData[i]["lastYear"]);
            data.datasets[1].data.push(monthlySalesData[i]["thisYear"]);
        }
        return data;
    }

    $(function () {
        var monthlySalesData = eval('(' + '${monthlySalesData}' + ')');
        var barData = generateMonthlyData(monthlySalesData);
        var barChart = new Chart(document.getElementById("bar").getContext("2d")).Bar(barData);
        document.getElementById('barLegend').innerHTML = barChart.generateLegend();

    });


</script>

</body>
</html>
