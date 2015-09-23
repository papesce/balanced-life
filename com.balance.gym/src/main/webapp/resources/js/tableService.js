(function () {
	'use strict';
	
    angular.module('app')
        .service('GymService', ['$http', GymService]);

    function GymService($http) {

        var self = this;
        
        //var baseUrl = 'https://api.backand.com/1/objects/';
        //var anonymousToken = {
        //    'AnonymousToken': '78020290-5df3-44b8-9bdb-7b3b4fea2f25'
        //};

        //var objectName = 'products';

        self.readOne = function () {
            return $http({
                method: 'GET',
                url: './excercise',//baseUrl + objectName,
                //headers: anonymousToken
            }).then(function (response) {
                return response;
            });
        };

        self.readAll = function () {
            return $http({
                method: 'GET',
                url: './excercises',//baseUrl + objectName,
                //headers: anonymousToken
            }).then(function (response) {
                return response;
            });
        };

//        self.readOne = function (id) {
//            return $http({
//                method: 'GET',
//                url: baseUrl + objectName + '/' + id,
//                headers: anonymousToken
//            }).then(function (response) {
//                return response.data;
//            });
//        };
//
//        self.create = function (data) {
//            return $http({
//                method: 'POST',
//                url: baseUrl + objectName,
//                data: data,
//                params: {
//                    returnObject: true
//                },
//                headers: anonymousToken
//            }).then(function (response) {
//                return response.data;
//            });
//        };
//
//        self.update = function (id, data) {
//            return $http({
//                method: 'PUT',
//                url: baseUrl + objectName + '/' + id,
//                data: data,
//                headers: anonymousToken
//            }).then(function (response) {
//                return response.data;
//            });
//        };
//
//        self.delete = function (id) {
//            return $http({
//                method: 'DELETE',
//                url: baseUrl + objectName + '/' + id,
//                headers: anonymousToken
//            });
//        };

    }
}());