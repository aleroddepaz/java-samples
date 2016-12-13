"use strict";

var connection = new WebSocket("ws://" + document.location.host + "/chat");

connection.onopen = function() {
  console.log("WebSocket.onopen");
};

connection.onerror = function(error) {
  console.log("WebSocket.onerror");
  console.log(error);
};

var lastMessage = {
  username : undefined,
  element : undefined
};

var conversation = document.getElementById("messages");

connection.onmessage = function(event) {
  console.log("WebSocket.onmessage");
  try {
    var message = JSON.parse(event.data);

    if (lastMessage.username !== message.username) {
      var div = document.createElement("div");
      div.className = "message";
      div.innerHTML = "<div><strong>" + message.username + "</strong></div>";

      lastMessage.element = div;
      lastMessage.username = message.username;
      conversation.appendChild(div);
    }

    var text = document.createElement("div");
    text.appendChild(document.createTextNode(message.text));
    lastMessage.element.appendChild(text);
  } catch (e) {
    console.log(e);
  }
};

var newMessage = document.getElementById("new-message");

newMessage.addEventListener("keypress", function(event) {
  if (event.keyCode === 13) {
    event.preventDefault();
    var message = this.value;
    if (message) {
      connection.send(message);
      this.value = "";
    }
  }
});
