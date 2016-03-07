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
            <form action="country" method="post"  role="form" data-toggle="validator" >
                <c:if test ="${empty action}">                        	
                    <c:set var="action" value="add"/>
                </c:if>
                <input type="hidden" id="action" name="action" value="${action}">
                <input type="hidden" id="idCountry" name="idCountry" value="${country.id}">
                <h2>Country</h2>
                <div class="form-group col-xs-6">
                    <label for="code" class="control-label col-xs-6">Name:</label>
                    <select class="form-control" id="code" name="code">
                        <c:if test ="${not empty country}">
                            <option value="${country.code}">${country.name}</option>
                        </c:if>
                    <c:forEach var="countryObj" items="${remainingCountryList}">

                        <option value="${countryObj.code}">${countryObj.name}</option>
                    </c:forEach>
                    </select>
                    <label for="targetSales" class="control-label col-xs-6">Target Sales:</label>
                    <input type="number" name="targetSales" id="targetSales" class="form-control"
                           value="${country.targetSales}" required="true"/>


                        <label for="currentSales" class="control-label col-xs-6">Current Sales:</label>
                        <input type="number" name="currentSales" id="currentSales" class="form-control"
                               value="${country.currentSales}" required="true"/>


                    <label for="marketShare" class="control-label col-xs-6">Market Share(%):</label>
                    <input type="number" name="marketShare" id="marketShare" class="form-control" max="100" min="1"
                           value="${country.marketShare}" required="true"/>

                    <br></br>
                    <button type="submit" class="btn btn-primary  btn-md">Save</button>
                </div>                                                      
            </form>
        </div>
    </body>
</html>