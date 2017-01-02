(function() {
    'use strict';

    angular
        .module('kemmonApp')
        .controller('DokumenDialogController', DokumenDialogController);

    DokumenDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'Dokumen'];

    function DokumenDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, Dokumen) {
        var vm = this;

        vm.dokumen = entity;
        vm.clear = clear;
        vm.save = save;

        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function save () {
            vm.isSaving = true;
            if (vm.dokumen.id !== null) {
                Dokumen.update(vm.dokumen, onSaveSuccess, onSaveError);
            } else {
                Dokumen.save(vm.dokumen, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('kemmonApp:dokumenUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();
