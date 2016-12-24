(function() {
  "use strict";

  var table = document.getElementById("users-table");
  var form = document.getElementById("form-create-user");

  fetchUsers(table);
  form.addEventListener("submit", function(event) {
    event.preventDefault();
    var submit = form.querySelector("button[type=submit]");
    submit.disabled = true;

    createUser(form).then(function() {
      form.reset();
      submit.disabled = false;
      fetchUsers(table);
    }, function() {
      submit.disabled = false;
    });
  });

  /**
   * Retrieve all users from /api/users.
   */
  function fetchUsers(table) {
    // See https://developer.mozilla.org/en-US/docs/Web/API/XMLHttpRequest
    var req = new XMLHttpRequest();
    req.open("GET", "/api/users");
    req.onload = function(e) {
      if (req.status === 200) {
        var users = JSON.parse(req.responseText);

        table.innerHTML = "";
        for (var i = 0; i < users.length; i++) {
          var user = users[i];
          var tr = document.createElement("tr");
          table.appendChild(tr);

          var td1 = document.createElement("td");
          td1.appendChild(document.createTextNode(user.name));
          tr.appendChild(td1);

          var td2 = document.createElement("td");
          td2.appendChild(document.createTextNode(user.password));
          tr.appendChild(td2);
        }
      }
    }
    req.send();
  }

  /**
   * Post the form information to /api/users to create a new user.
   */
  function createUser(form) {
    var username = form.querySelector("#username").value;
    var password = form.querySelector("#password").value;
    var confirmPassword = form.querySelector("#confirm-password").value;
    if (password !== confirmPassword) {
      return;
    }

    // See https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Global_Objects/Promise
    return new Promise(function(resolve, reject) {
      var req = new XMLHttpRequest();
      req.open("POST", "/api/users");
      req.setRequestHeader("Content-Type", "application/json;charset=UTF-8");
      req.onload = function() {
        if (req.status === 200) {
          resolve();
        }
      }
      req.onerror = (e) => reject(e);
      req.send(JSON.stringify({
        "name": username,
        "password": password
      }));
    });
  }
})();