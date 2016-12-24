(function() {
  "use strict";

  // See https://developer.mozilla.org/en-US/docs/Web/API/WebSockets_API
  var socket = new WebSocket("ws://" + document.location.host + "/chat");
  var conversation = document.getElementById("messages");
  var client = new ChatClient(socket, conversation);

  document.getElementById("new-message").addEventListener("keypress", function(event) {
    if (event.keyCode === 13 && this.value) {
      event.preventDefault();
      client.send(this.value);
      this.value = "";
    }
  });

  window.onbeforeunload = () => client.close();

  /**
   * Utility class that wraps the websocket connection.
   */
  function ChatClient(socket, conversation) {
    var lastMessage = {
      username : undefined,
      element : undefined
    };

    socket.onerror = (error) => console.log("WebSocket.onerror:" + error);
    socket.onmessage = function(event) {
      try {
        appendMessage(JSON.parse(event.data));
      } catch (e) {
        console.log(e);
      }
    };

    this.send = (message) => socket.send(message);
    this.close = () => socket.close();
    
    function appendMessage(message) {
    	if (lastMessage.username !== message.username) {
        var div = document.createElement("div");
        div.className = "message";
        div.innerHTML = "<div><strong>" + message.username + "</strong></div>";
        lastMessage.username = message.username;
        lastMessage.element = conversation.appendChild(div);
      }
      var text = document.createElement("div");
      text.appendChild(document.createTextNode(message.text));
      lastMessage.element.appendChild(text);
    }
  }
})();