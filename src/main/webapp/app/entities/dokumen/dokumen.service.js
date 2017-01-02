(function() {
    'use strict';
    angular
        .module('kemmonApp')
        .factory('Dokumen', Dokumen);

    Dokumen.$inject = ['$resource'];

    function Dokumen ($resource) {
        var resourceUrl =  'api/dokumen/:id';

        return $resource(resourceUrl, {}, {
            'query': { method: 'GET', isArray: true},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    if (data) {
                        data = angular.fromJson(data);
                    }
                    return data;
                }
            },
            'update': { method:'PUT' }
        });
    }
})();
