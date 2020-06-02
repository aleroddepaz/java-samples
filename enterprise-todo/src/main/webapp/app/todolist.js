define([ 'Backbone', 'app/todo' ], function(Backbone, Todo) {
  'use strict';

  var TodoList = Backbone.Collection.extend({
    url : window.location.pathname + 'api/todos',
    model : Todo
  });

  return TodoList;
});