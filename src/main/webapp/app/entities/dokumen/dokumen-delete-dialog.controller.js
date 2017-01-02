(function() {
    'use strict';

    angular
        .module('kemmonApp')
        .controller('DokumenDeleteController',DokumenDeleteController);

    DokumenDeleteController.$inject = ['$uibModalInstance', 'entity', 'Dokumen'];

    function DokumenDeleteController($uibModalInstance, entity, Dokumen) {
        var vm = this;

        vm.dokumen = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            Dokumen.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
