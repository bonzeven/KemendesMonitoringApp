(function() {
    'use strict';

    angular
        .module('kemmonApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('dokumen', {
            parent: 'entity',
            url: '/dokumen?page&sort&search',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'Dokumen'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/dokumen/dokumen.html',
                    controller: 'DokumenController',
                    controllerAs: 'vm'
                }
            },
            params: {
                page: {
                    value: '1',
                    squash: true
                },
                sort: {
                    value: 'id,asc',
                    squash: true
                },
                search: null
            },
            resolve: {
                pagingParams: ['$stateParams', 'PaginationUtil', function ($stateParams, PaginationUtil) {
                    return {
                        page: PaginationUtil.parsePage($stateParams.page),
                        sort: $stateParams.sort,
                        predicate: PaginationUtil.parsePredicate($stateParams.sort),
                        ascending: PaginationUtil.parseAscending($stateParams.sort),
                        search: $stateParams.search
                    };
                }],
            }
        })
        .state('dokumen-detail', {
            parent: 'entity',
            url: '/dokumen/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'Dokumen'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/dokumen/dokumen-detail.html',
                    controller: 'DokumenDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                entity: ['$stateParams', 'Dokumen', function($stateParams, Dokumen) {
                    return Dokumen.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'dokumen',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('dokumen-detail.edit', {
            parent: 'dokumen-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/dokumen/dokumen-dialog.html',
                    controller: 'DokumenDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Dokumen', function(Dokumen) {
                            return Dokumen.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('dokumen.new', {
            parent: 'dokumen',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/dokumen/dokumen-dialog.html',
                    controller: 'DokumenDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                dokumen_id: null,
                                dokumen_number: null,
                                dokumen_name: null,
                                is_inputed: null,
                                inputed_date: null,
                                inputed_by: null,
                                is_ppk_approved: null,
                                ppk_approved_date: null,
                                ppk_approved_by: null,
                                is_spm_approved: null,
                                spm_approved_date: null,
                                spm_approved_by: null,
                                is_kppn_approved: null,
                                kppn_approved_date: null,
                                kppn_approved_by: null,
                                last_process: null,
                                last_process_date: null,
                                last_modified_date: null,
                                last_modified_by: null,
                                is_deleted: null,
                                deleted_date: null,
                                deleted_by: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('dokumen', null, { reload: 'dokumen' });
                }, function() {
                    $state.go('dokumen');
                });
            }]
        })
        .state('dokumen.edit', {
            parent: 'dokumen',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/dokumen/dokumen-dialog.html',
                    controller: 'DokumenDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Dokumen', function(Dokumen) {
                            return Dokumen.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('dokumen', null, { reload: 'dokumen' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('dokumen.delete', {
            parent: 'dokumen',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/dokumen/dokumen-delete-dialog.html',
                    controller: 'DokumenDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['Dokumen', function(Dokumen) {
                            return Dokumen.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('dokumen', null, { reload: 'dokumen' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
