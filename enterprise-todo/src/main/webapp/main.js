require.config({
  paths : {
    jquery : 'vendor/jquery/dist/jquery',
    underscore : 'vendor/underscore/underscore',
    Backbone : 'vendor/backbone/backbone',
    domReady : 'vendor/domReady/domReady',
  },
  shim : {
    Backbone: {
      deps: ['underscore', 'jquery'],
      exports: 'Backbone'
    },
    underscore: {
      exports: '_'
    }
  }
});

require([ 'app/todoview', 'app/todolist', '!domReady' ], function(TodoView, TodoList) {
  var todoList = new TodoList;
  var todoView = new TodoView({
    el : 'body',
    collection : todoList
  });
});