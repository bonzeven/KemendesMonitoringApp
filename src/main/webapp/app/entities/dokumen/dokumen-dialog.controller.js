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
        vm.datePickerOpenStatus = {};
        vm.openCalendar = openCalendar;
        vm.save = save;

        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear () {
           // alert(vm.dokumen.dokumen_number);
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

        vm.datePickerOpenStatus.inputed_date = false;
        vm.datePickerOpenStatus.ppk_approved_date = false;
        vm.datePickerOpenStatus.spm_approved_date = false;
        vm.datePickerOpenStatus.kppn_approved_date = false;
        vm.datePickerOpenStatus.last_process_date = false;
        vm.datePickerOpenStatus.last_modified_date = false;
        vm.datePickerOpenStatus.deleted_date = false;

        function openCalendar (date) {
            vm.datePickerOpenStatus[date] = true;
        }
    }
})();
