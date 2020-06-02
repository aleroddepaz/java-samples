define([ 'Backbone', 'underscore', 'app/todo' ], function(Backbone, _, Todo) {
  'use strict';

  var TodoView = Backbone.View.extend({
    initialize : function initialize() {
      this.template = _.template(this.$('#table-tpl').html()),
      this.listenTo(this.collection, 'sync', this.render, this);
      this.collection.fetch();
    },

    events : {
      'submit' : 'create',
      'click .remove' : 'remove'
    },
    
    create : function addNew() {
      var input = this.$el.find('#new-todo');
      var todo = new Todo({
        text : input.val()
      });
      
      todo.save(null, {
        success : function() {
          input.val('');
          this.collection.fetch();
        }.bind(this)
      });
      return false;
    },
    
    remove : function remove(event) {
      var todoId = this.$(event.target).data('id');
      var todo = new Todo({ id : todoId });
      todo.destroy({
        contentType : false,
        processData : false,
        success : function() {
          this.collection.fetch();
        }.bind(this)
      });
    },
    
    render : function render() {
      var tbody = this.$el.find('#todo-table tbody');
      tbody.html(this.template({ todos: this.collection.toJSON() }));
    }
  });

  return TodoView;
});