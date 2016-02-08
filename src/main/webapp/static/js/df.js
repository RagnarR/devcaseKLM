'use strict';

var dfServices = angular.module('df.services', []);

dfServices.factory('destinationFinderClient', function ($http) {
    return {
        findDestinations: function (origin, pos, maxBudget) {
            return $http.jsonp('http://www.klm.com/destinations/destination-finder/?origin=' + origin + '&pos=' + pos + '&maxBudget=' + maxBudget + '&callback=JSON_CALLBACK');
        }
    }
});

var dfControllers = angular.module('df.controllers', ['df.services']);

dfControllers.controller('DestinationsController', ['$scope', '$http', 'destinationFinderClient', function ($scope, $http, destinationFinderClient) {
    $scope.error = null;
    $scope.loading = false;
    $scope.findDestinations = function (origin, pos, maxBudget) {
        if (origin && pos && maxBudget) {
            $scope.error = null;
            $scope.response = null;
            $scope.loading = true;
            var promise = destinationFinderClient.findDestinations(origin.toUpperCase(), pos.toUpperCase(), maxBudget);
            promise.success(function (response) {
                $scope.response = response;
                $scope.loading = false;
            });
            promise.error(function (response, status, headers) {
                $scope.error = 'Unable to retrieve destinations at this time, please try again later.';
                $scope.loading = false;
            });
        }
    }
}]);

var df = angular.module('df', ['df.services', 'df.controllers', 'ngRoute']);

df.config(['$routeProvider', function ($routeProvider) {
    $routeProvider.when("/destinations", {templateUrl: 'partials/destinations.html', controller: 'DestinationsController'});
    $routeProvider.when("/assignment", {templateUrl: 'partials/assignment.html'});
    $routeProvider.when("/service-details", {templateUrl: 'partials/service_details.html'});
    $routeProvider.when("/find-destination", {templateUrl: '/WEB_INF/jsp/destination-finder.jsp'});
    $routeProvider.otherwise({redirectTo: "/assignment"});
}]);