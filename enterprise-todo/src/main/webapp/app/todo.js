define([ 'Backbone' ], function(Backbone) {
  'use strict';
  
  var Todo = Backbone.Model.extend({
    urlRoot : window.location.pathname + 'api/todos'
  });
  
  return Todo;
});