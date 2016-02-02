(function() {
  angular.module("magicmirror")
  .controller("time", function($scope, $interval) {
    $scope.time = "";

    $interval(function () {
      var date = new Date();
      var hours = date.getHours() > 12 ? date.getHours() - 12 : date.getHours();
      $scope.time = ""+hours+":"+date.getMinutes();


    }, 500, 500);
  })
  .directive("time", function() {
    return {
      template: "<div>{{time}}</div>",
      controller: "time",
    };
  });
})();
