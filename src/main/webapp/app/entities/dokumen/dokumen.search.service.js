(function() {
    'use strict';

    angular
        .module('kemmonApp')
        .factory('DokumenSearch', DokumenSearch);

    DokumenSearch.$inject = ['$resource'];

    function DokumenSearch($resource) {
        var resourceUrl =  'api/_search/dokumen/:id';

        return $resource(resourceUrl, {}, {
            'query': { method: 'GET', isArray: true}
        });
    }
})();
