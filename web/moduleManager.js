(function() {
  angular.module("magicmirror")
  .factory('moduleManager', [], function() {
    this.addModule = function() {
      console.log("Adding!!");
    };
  });
})();
