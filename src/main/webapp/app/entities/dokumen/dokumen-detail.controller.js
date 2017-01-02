(function() {
    'use strict';

    angular
        .module('kemmonApp')
        .controller('DokumenDetailController', DokumenDetailController);

    DokumenDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Dokumen'];

    function DokumenDetailController($scope, $rootScope, $stateParams, previousState, entity, Dokumen) {
        var vm = this;

        vm.dokumen = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('kemmonApp:dokumenUpdate', function(event, result) {
            vm.dokumen = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
