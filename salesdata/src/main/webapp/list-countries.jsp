<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
    <head>
        <link rel="shortcut icon" href="icon/grow_favicon.ico" type="image/x-icon"/>
        <link rel="stylesheet" href="css/bootstrap.min.css">
        <script src="js/jquery-1.12.1.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
    </head>

    <body>          
        <div class="container">
            <h2>Countries</h2>
            <!--Search Form -->
            <form action="country" method="get" id="seachCountryForm" role="form">
                <input type="hidden" id="doAction" name="doAction" value="searchByName">
                <div class="form-group col-xs-5">
                    <input type="text" name="countryName" id="countryName" class="form-control" required="true" placeholder="Type the Name of the country"/>
                </div>
                <button type="submit" class="btn btn-info">
                    <span class="glyphicon glyphicon-search"></span> Search
                </button>
                <br></br>
                <br></br>
            </form>

            <!--Countries List-->
            <c:if test="${not empty message}">                
                <div class="alert alert-success">
                    ${message}
                </div>
            </c:if> 
            <form action="country" method="post" id="countryForm" role="form" >
                <input type="hidden" id="idCountry" name="idCountry">
                <input type="hidden" id="action" name="action">
                <c:choose>
                    <c:when test="${not empty countriesList}">
                        <table  class="table table-striped">
                            <thead>
                                <tr>
                                    <td>Name</td>
                                    <td>Target Sales</td>
                                    <td>Current Sales</td>
                                    <td>Market Share(%)</td>
                                </tr>
                            </thead>
                            <c:forEach var="country" items="${countriesList}">
                                <c:set var="classSucess" value=""/>
                                <c:if test ="${idCountry == country.id}">
                                    <c:set var="classSucess" value="info"/>
                                </c:if>
                                <tr class="${classSucess}">
                                    <td>
                                        <a href="country?idCountry=${country.id}&doAction=searchById">${country.name}</a>
                                    </td>
                                    <td>${country.targetSales}</td>
                                    <td>${country.currentSales}</td>
                                    <td>${country.marketShare}</td>
                                    <td><a href="#" id="remove" 
                                           onclick="document.getElementById('action').value = 'remove';document.getElementById('idCountry').value = '${country.id}';
                                                    
                                                    document.getElementById('countryForm').submit();">
                                            <span class="glyphicon glyphicon-trash"/>
                                        </a>
                                                   
                                    </td>
                                </tr>
                            </c:forEach>               
                        </table>  
                    </c:when>                    
                    <c:otherwise>
                        <br>           
                        <div class="alert alert-info">
                            No people found matching your search criteria
                        </div>
                    </c:otherwise>
                </c:choose>                        
            </form>
            <form action ="country" method="get" role="form">
                <input type="hidden" id="doAction" name="doAction" value="addNew">
                <br></br>
                <button type="submit" class="btn btn-primary  btn-md">New country</button>
            </form>
        </div>
    </body>
</html>