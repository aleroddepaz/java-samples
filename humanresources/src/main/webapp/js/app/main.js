require.config({
  baseUrl: 'js/app'
});

require(['controllers/department.controller'], function(departmentCtrl) {
  departmentCtrl.init();
});