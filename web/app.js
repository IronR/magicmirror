(function() {
  angular.module("magicmirror", [])
  .controller('AppCtrl', function($scope, User, $timeout, $compile) {
    $scope.test = "Hi!";
    $scope.currentUser = "";
    $scope.handleTimer = function() {
      User.identified().then(function(user) {
        if($scope.currentUser !== user) {
          $scope.currentUser = user;
          $scope.loadUser(user);
          $timeout($scope.handleTimer, 15000);
        } else {
          $timeout($scope.handleTimer, 1000);

        }
      });

    };

    $scope.loadUser = function(username) {
      User.modules(username).then(function(modules) {
        modules.forEach(function(module) {
          angular.element(document.getElementById("modules")).append($compile("<"+module.id+"> </" + module.id + ">")($scope));
        });
      });
    };

    $scope.handleTimer();
  });

  console.log("Loaded");
})();
