<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="core" %>

<div class="row" style="margin-bottom: 40px;">
    <div class="col-lg-12">
        <h1>Server side destination-finder implementation.</h1>
    </div>
</div>
<div class="row">
    <div class="col-lg-offset-2 col-lg-8">
        <form:form class="form-horizontal" method="POST"
                   action="/find-destination"
                   modelAttribute="destination-finder-params">
            <div class="form-group">
                <form:label path="origin" class="col-sm-3 control-label">Origin</form:label>

                <div class="col-sm-9">
                    <form:input type="text" class="form-control" id="origin"
                                placeholder="Enter your origin code i.e. AMS." path="origin"
                                required="true" maxlength="3"/>
                </div>
            </div>
            <div class="form-group">
                <form:label path="pos" class="col-sm-3 control-label">POS</form:label>

                <div class="col-sm-9">
                    <form:input type="text" class="form-control" id="pos"
                                placeholder="Enter your point of sale code i.e. NL." path="pos"
                                required="true"/>
                </div>
            </div>
            <div class="form-group">
                <form:label path="minBudget" class="col-sm-3 control-label">Min. Budget</form:label>

                <div class="col-sm-9">
                    <form:input type="number" class="form-control" id="minBudget"
                                placeholder="Provide a minimum budget." path="minBudget"/>
                </div>
            </div>
            <div class="form-group">
                <form:label path="maxBudget" class="col-sm-3 control-label">Max. Budget</form:label>

                <div class="col-sm-9">
                    <form:input type="number" class="form-control" id="maxBudget"
                                placeholder="Provide a maximum budget." path="maxBudget"
                                required="true"/>
                </div>
            </div>
            <div class="form-group">
                <form:label path="maxBudget" class="col-sm-3 control-label">Sort by</form:label>

                <div class="col-sm-9">
                    <form:radiobutton path="sortByPrice" value="false" class="form-control"/>Destination Name
                    <form:radiobutton path="sortByPrice" value="true" class="form-control"/>Price
                </div>
            </div>

            <div class="form-group">
                <div class="col-sm-offset-3 col-sm-9">
                    <button type="submit" class="btn btn-default" value="Submit">Find
                        Destinations
                    </button>
                </div>
            </div>
        </form:form>
    </div>
</div>

<div class="row" ng-show="response">
    <div class="col-lg-offset-2 col-lg-8">
        <div class="col-sm-offset-3 col-sm-9">
            <table class="table table-condensed">
                <thead>
                <th>destination</th>
                <th>fare</th>
                </thead>
                <tbody>
                <core:forEach var="route" items="${paginatedDestinations.pageList}">
                    <tr>
                        <td>${route.destination.description}</td>
                        <td>${route.lowestFare.currency}${route.lowestFare.value},-</td>
                    </tr>
                </core:forEach>
                </tbody>
            </table>
        </div>
        <div class="col-sm-offset-3 col-sm-9">
            <table class="table table-condensed">

                <tr>
                    <form:form method="POST" action="/find-destination"
                               modelAttribute="destination-finder-params">
                        <td>Select page:
                        </td>
                        <td>
                            <form:select path="page">
                                <form:options items="${pageIndexes}"/>
                            </form:select>
                        </td>
                        <td>
                            <button type="submit" class="btn btn-default" value="Submit">Go</button>
                        </td>
                    </form:form>
                </tr>

            </table>
        </div>
    </div>
</div>