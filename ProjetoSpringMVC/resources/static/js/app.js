var app = angular.module('app', ['ngResource']);

app.factory('MyService', function() {
	  return $resource('/api/student/:id');
	});

app.controller('home', function($scope, $http, $resource) {
	var resource = $resource('/api/student/:id');
	  
	  var student = {};
	  var students = [];
	  $scope.student = student;
	  
	  
	  students = resource.query(function() {
		  console.log(students);
	  });
		  
    $scope.deletar = function(student) {
    	resource.delete({id: student.id});
    	var index = students.indexOf(student);
    	students.splice(index, 1);
    }

    $scope.salvar = function(student) {
    	resource.save(student);
    	students.push(student);
    	student = {};
    }
    
    $scope.students = students;


})