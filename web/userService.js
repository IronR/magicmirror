(function() {
  angular.module("magicmirror")
  .service('User', function($http) {
    this.identified = function() {
      return $http.get("/user/identified").then(function(result) {
        return result.data.name;
      });
    };

    this.modules = function(user) {
      return $http.get("/user/"+user+"/modules").then(function(result) {
        return result.data;
      });
    };
  });

})();
