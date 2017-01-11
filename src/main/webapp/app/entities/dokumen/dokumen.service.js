(function() {
    'use strict';
    angular
        .module('kemmonApp')
        .factory('Dokumen', Dokumen);

    Dokumen.$inject = ['$resource', 'DateUtils'];

    function Dokumen ($resource, DateUtils) {
        var resourceUrl =  'api/dokumen/:id';

        return $resource(resourceUrl, {}, {
            'query': { method: 'GET', isArray: true},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    if (data) {
                        data = angular.fromJson(data);
                        data.inputed_date = DateUtils.convertLocalDateFromServer(data.inputed_date);
                        data.ppk_approved_date = DateUtils.convertLocalDateFromServer(data.ppk_approved_date);
                        data.spm_approved_date = DateUtils.convertLocalDateFromServer(data.spm_approved_date);
                        data.kppn_approved_date = DateUtils.convertLocalDateFromServer(data.kppn_approved_date);
                        data.last_process_date = DateUtils.convertLocalDateFromServer(data.last_process_date);
                        data.last_modified_date = DateUtils.convertLocalDateFromServer(data.last_modified_date);
                        data.deleted_date = DateUtils.convertLocalDateFromServer(data.deleted_date);
                    }
                    return data;
                }
            },
            'update': {
                method: 'PUT',
                transformRequest: function (data) {
                    var copy = angular.copy(data);
                    copy.inputed_date = DateUtils.convertLocalDateToServer(copy.inputed_date);
                    copy.ppk_approved_date = DateUtils.convertLocalDateToServer(copy.ppk_approved_date);
                    copy.spm_approved_date = DateUtils.convertLocalDateToServer(copy.spm_approved_date);
                    copy.kppn_approved_date = DateUtils.convertLocalDateToServer(copy.kppn_approved_date);
                    copy.last_process_date = DateUtils.convertLocalDateToServer(copy.last_process_date);
                    copy.last_modified_date = DateUtils.convertLocalDateToServer(copy.last_modified_date);
                    copy.deleted_date = DateUtils.convertLocalDateToServer(copy.deleted_date);
                    return angular.toJson(copy);
                }
            },
            'save': {
                method: 'POST',
                transformRequest: function (data) {
                    var copy = angular.copy(data);
                    copy.inputed_date = DateUtils.convertLocalDateToServer(copy.inputed_date);
                    copy.ppk_approved_date = DateUtils.convertLocalDateToServer(copy.ppk_approved_date);
                    copy.spm_approved_date = DateUtils.convertLocalDateToServer(copy.spm_approved_date);
                    copy.kppn_approved_date = DateUtils.convertLocalDateToServer(copy.kppn_approved_date);
                    copy.last_process_date = DateUtils.convertLocalDateToServer(copy.last_process_date);
                    copy.last_modified_date = DateUtils.convertLocalDateToServer(copy.last_modified_date);
                    copy.deleted_date = DateUtils.convertLocalDateToServer(copy.deleted_date);
                    return angular.toJson(copy);
                }
            }
        });
    }
})();
